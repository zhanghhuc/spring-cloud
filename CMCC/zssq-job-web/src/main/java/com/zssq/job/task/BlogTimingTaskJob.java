package com.zssq.job.task;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.api.AbstractOneOffElasticJob;
import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.zssq.constants.BlogConstants;
import com.zssq.constants.CreditConstants;
import com.zssq.constants.RelationConstants;
import com.zssq.constants.StatisticConstants;
import com.zssq.constants.TeamConstants;
import com.zssq.dao.pojo.BlogInfo;
import com.zssq.dao.pojo.MessageIntegral;
import com.zssq.dao.pojo.RelationDynamic;
import com.zssq.dao.pojo.RelationDynamicTeamRel;
import com.zssq.dao.pojo.RelationSubjectInfo;
import com.zssq.dao.pojo.StatisticCommon;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.service.BlogService;
import com.zssq.service.IStatisticService;
import com.zssq.service.ISysUserService;
import com.zssq.service.ITeamElectService;
import com.zssq.service.ITeamInfoService;
import com.zssq.service.RelationThirdDynamicService;
import com.zssq.util.SpringContextUtil;
import com.zssq.util.UUIDHelper;
import com.zssq.vo.BlogThirdDataVO;
import com.zssq.vo.BlogVO;

/**
 * 
 * @ClassName: BlogTimingTaskJob  
 * @Description: 定时任务  
 * @author ZKZ  
 * @date 2017年4月26日  
 *
 */
@Component
public class BlogTimingTaskJob extends AbstractOneOffElasticJob {
	
	private Logger log = Logger.getLogger(this.getClass());
	
//	@Autowired
	private BlogService blogService = SpringContextUtil.getBean(BlogService.class);
//	@Autowired
	private RelationThirdDynamicService relationThirdDynamicService = SpringContextUtil.getBean(RelationThirdDynamicService.class);
//	@Autowired
	private ISysUserService iSysUserService = SpringContextUtil.getBean(ISysUserService.class);
//	@Autowired
	private ITeamInfoService iTeamInfoService = SpringContextUtil.getBean(ITeamInfoService.class);
//	@Autowired
	private ITeamElectService iTeamElectService = SpringContextUtil.getBean(ITeamElectService.class);
//	@Autowired
	private IStatisticService iStatisticService = SpringContextUtil.getBean(IStatisticService.class);
	
	@SuppressWarnings("rawtypes")
//	@Autowired
	protected KafkaProducerTemplate producerTeplate = SpringContextUtil.getBean(KafkaProducerTemplate.class);
	
	/**
	 * 
	 * @Title: publishPlanBlog  
	 * @Description: 发布定时发布博客
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void process(JobExecutionMultipleShardingContext context){
		try {
			log.info("BlogTimingTaskController.process：发布定时发布博客");
			
			// 拼接参数
			BlogVO blogListVO = new BlogVO();
			blogListVO.setQueryTime(new Date().getTime());
			
			// 获取未发布的博客列表
			List<BlogInfo> blogInfoList = blogService.getPlanBlogAllList(blogListVO);
			
			// 如果列表为空则退出
			if (blogInfoList == null || blogInfoList.isEmpty()) {
				log.info("BlogTimingTaskController.process：发布定时发布博客成功");
				return;
			}
			
			// 循环处理
			for (BlogInfo blogInfo : blogInfoList) {
				// 定时发布时间
				Long blogPlanPublishTime = blogInfo.getBlogPlanPublishTime(); 
				
				// 拼接参数
				BlogVO blogVO = new BlogVO();
				blogVO.setBlogCode(blogInfo.getBlogCode()); // 博客编号
				blogVO.setBlogDepend(blogInfo.getBlogDepend()); // 从属关系
				blogVO.setUserCode(blogInfo.getUserCode()); // 人员编号
				blogVO.setTeamCode(blogInfo.getTeamCode()); // 班组编号
				blogVO.setModifyTime(blogPlanPublishTime); // 修改时间
				
				// 修改博客
				boolean updateFlag = blogService.updateBlogPublishTime(blogVO);
				if (!updateFlag) {
					log.error("BlogTimingTaskController.process：发布失败");
					return;
				}
				
				// 动态信息
				RelationDynamic relationDynamic = new RelationDynamic();
				relationDynamic.setDynamicCode(blogInfo.getDynamicCode());
				relationDynamic.setTenantCode(blogInfo.getTenantCode());
				relationDynamic.setOrgCode(blogInfo.getOrgCode());
				relationDynamic.setCreateTime(blogPlanPublishTime);
				relationDynamic.setModifyTime(blogPlanPublishTime);
				relationDynamic.setDynamicClass(RelationConstants.RELATION_DYNAMIC_SEND_BLOG);
				if (BlogConstants.BLOG_DEPEND_USER.equals(blogInfo.getBlogDepend())) {
					relationDynamic.setDynamicDepend(RelationConstants.RELATION_DYNAMIC_DEPEND_USER);
					relationDynamic.setUserCode(blogInfo.getUserCode());
				} else if (BlogConstants.BLOG_DEPEND_TEAM.equals(blogInfo.getBlogDepend())) {
					relationDynamic.setDynamicDepend(RelationConstants.RELATION_DYNAMIC_DEPEND_TEAM);
					relationDynamic.setUserCode(blogInfo.getUserCode());
					relationDynamic.setTeamCode(blogInfo.getTeamCode());
				}
				relationDynamic.setOperateTime(blogPlanPublishTime);
				relationDynamic.setSubjectCode(blogInfo.getBlogCode());
				relationDynamic.setDynamicIsDelete(RelationConstants.RELATION_NO);
				relationDynamic.setDynamicIsShield(RelationConstants.RELATION_NO);
				relationDynamic.setIsSubjectShow(RelationConstants.RELATION_YES);
				relationDynamic.setIsSubjectDataShow(RelationConstants.RELATION_YES);
				
				// 动态班组关系
				RelationDynamicTeamRel relationDynamicTeamRel = new RelationDynamicTeamRel();
				// 获取班组信息
				TeamInfo teamInfo = null;
				if (BlogConstants.BLOG_DEPEND_TEAM.equals(blogInfo.getBlogDepend())) {
					teamInfo = iTeamInfoService.selectByCode(blogInfo.getTeamCode());
					if (teamInfo == null) {
						log.error("BlogTimingTaskController.process：获取不到班组信息teamCode=" + blogInfo.getTeamCode());
						return;
					}
					
					relationDynamicTeamRel.setRelCode(UUIDHelper.getUUID());
					relationDynamicTeamRel.setTenantCode(teamInfo.getTenantCode());
					relationDynamicTeamRel.setOrgCode(teamInfo.getOrgCode());
					relationDynamicTeamRel.setCreateTime(blogPlanPublishTime);
					relationDynamicTeamRel.setModifyTime(blogPlanPublishTime);
					relationDynamicTeamRel.setDynamicCode(blogInfo.getDynamicCode());
					relationDynamicTeamRel.setTeamCode(blogInfo.getTeamCode());
					boolean isExcelent = iTeamElectService.isExcelent(blogInfo.getTeamCode());
					relationDynamicTeamRel.setTeamIsExcellent(isExcelent ? RelationConstants.RELATION_YES : RelationConstants.RELATION_NO);
					relationDynamicTeamRel.setTeamIsNoOne(TeamConstants.TEAM_TYPE_1.equals(teamInfo.getTeamType()) ? RelationConstants.RELATION_YES : RelationConstants.RELATION_NO);
					relationDynamicTeamRel.setTeamIsDissolve(TeamConstants.BOOLEAN_TRUE.equals(teamInfo.getIsDissolve()) ? RelationConstants.RELATION_YES : RelationConstants.RELATION_NO);
					relationDynamicTeamRel.setRelIsHomeShow(RelationConstants.RELATION_YES);
					relationDynamicTeamRel.setRelIsQuality(RelationConstants.RELATION_NO);
					relationDynamicTeamRel.setRelIsRecommend(RelationConstants.RELATION_NO);
				}
				
				// 内容信息
				RelationSubjectInfo relationSubjectInfo = new RelationSubjectInfo();
				relationSubjectInfo.setSubjectCode(blogInfo.getBlogCode());
				relationSubjectInfo.setTenantCode(blogInfo.getTenantCode());
				relationSubjectInfo.setOrgCode(blogInfo.getOrgCode());
				relationSubjectInfo.setCreateTime(blogInfo.getCreateTime());
				relationSubjectInfo.setModifyTime(blogInfo.getModifyTime());
				relationSubjectInfo.setSubjectClass(RelationConstants.RELATION_SUBJECT_BLOG);
				if (BlogConstants.BLOG_DEPEND_USER.equals(blogInfo.getBlogDepend())) {
					relationSubjectInfo.setSubjectDepend(RelationConstants.RELATION_SUBJECT_DEPEND_USER);
					relationSubjectInfo.setUserCode(blogInfo.getUserCode());
				} else if (BlogConstants.BLOG_DEPEND_TEAM.equals(blogInfo.getBlogDepend())) {
					relationSubjectInfo.setSubjectDepend(RelationConstants.RELATION_SUBJECT_DEPEND_TEAM);
					relationSubjectInfo.setUserCode(blogInfo.getUserCode());
					relationSubjectInfo.setTeamCode(blogInfo.getTeamCode());
				}
				relationSubjectInfo.setSubjectPublishTime(blogPlanPublishTime);
				relationSubjectInfo.setSubjectTitle(blogInfo.getBlogTitle());
				relationSubjectInfo.setSubjectDigest(blogInfo.getBlogDigest());
				relationSubjectInfo.setSubjectUrl(blogInfo.getBlogLogo());
				relationSubjectInfo.setSubjectSource(RelationConstants.RELATION_SUBJECT_SOURCE_ORIGINAL);
				relationSubjectInfo.setSubjectIsDelete(RelationConstants.RELATION_NO);
				relationSubjectInfo.setSubjectIsShield(RelationConstants.RELATION_NO);
				
				// 保存动态信息
				boolean saveDynamicFlag = relationThirdDynamicService.saveDynamic(relationDynamic, relationDynamicTeamRel, relationSubjectInfo, null);
				if (!saveDynamicFlag) {
					log.error("BlogTimingTaskController.process：保存动态失败");
					return;
				}
				
				// 统计操作
				StatisticCommon statisticCommon = new StatisticCommon();
				statisticCommon.setOrgCode(blogInfo.getOrgCode());
				if (BlogConstants.BLOG_DEPEND_USER.equals(blogInfo.getBlogDepend())) {
					// 获取人员信息
					SysUserInfo sysUserInfo = iSysUserService.selectByCode(blogInfo.getUserCode());
					statisticCommon.setPeopleCode(sysUserInfo == null ? "" : sysUserInfo.getUserCode());
					statisticCommon.setPeopleName(sysUserInfo == null ? "" : sysUserInfo.getUserName());
				} else if (BlogConstants.BLOG_DEPEND_TEAM.equals(blogInfo.getBlogDepend())) {
					statisticCommon.setTeamCode(teamInfo.getTeamCode());
					statisticCommon.setTeamName(teamInfo.getTeamName());
				}
				statisticCommon.setBlog(StatisticConstants.BLOG);
				iStatisticService.addRecord(statisticCommon);
				
				// 积分操作
				MessageIntegral MessageIntegral = new MessageIntegral();
				MessageIntegral.setAccountCode(CreditConstants.COMMAND_BLOG_PUBLISH);
				if (BlogConstants.BLOG_DEPEND_USER.equals(blogInfo.getBlogDepend())) {
					MessageIntegral.setAccountCode(blogInfo.getUserCode());
					MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
				} else if (BlogConstants.BLOG_DEPEND_TEAM.equals(blogInfo.getBlogDepend())) {
					MessageIntegral.setAccountCode(blogInfo.getTeamCode());
					MessageIntegral.setAccountType(CreditConstants.TYPE_TEAM);
				}
				MessageIntegral.setManageOrgCode(blogInfo.getOrgCode());
				producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
				
				// 修改博客分类下博客数量
				BlogThirdDataVO blogThirdDataVO = new BlogThirdDataVO();
				blogThirdDataVO.setSubjectCode(blogInfo.getClassCode());
				blogThirdDataVO.setUpdateNumber(1);
				blogThirdDataVO.setUpdateClass(BlogConstants.BLOG_THIRD_CLASS_BLOG);
				blogThirdDataVO.setModifyTime(blogPlanPublishTime);
				producerTeplate.send(BlogConstants.BLOG_TOPIC, JSONObject.toJSONString(blogThirdDataVO));
			}

			log.info("BlogTimingTaskController.process：发布定时发布博客成功");
		} catch (Exception e) {
			log.error("BlogTimingTaskController.process", e);
		}
	}

}

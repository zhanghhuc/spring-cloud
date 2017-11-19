package com.zssq.news.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.*;
import com.zssq.dao.pojo.*;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.news.dao.pojo.NewsInfoComment;
import com.zssq.news.dao.pojo.NewsInfoContent;
import com.zssq.news.dao.pojo.NewsInfoHistory;
import com.zssq.news.dao.pojo.NewsReply;
import com.zssq.news.model.*;
import com.zssq.news.newsvo.*;
import com.zssq.news.service.NewsCommentService;
import com.zssq.news.service.NewsInfoContentService;
import com.zssq.news.service.NewsInfoHistoryService;
import com.zssq.news.service.NewsReplyService;
import com.zssq.pojo.ResultJSON;
import com.zssq.search.service.SolrQueryService;
import com.zssq.service.IStatisticService;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.service.RelationThirdSubjectService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.PageBean;
import com.zssq.vo.RelationSubjectVO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SharlaCheung
 * @ClassName: NewsCommentController
 * @Description: 新闻Controller
 * @date 2017年4月13日
 */
@Controller
@RequestMapping("/news")
public class NewsController {
	
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private NewsInfoContentService newsInfoContentService;
    @Autowired
    private NewsInfoHistoryService newsInfoHistoryService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysOrgService sysOrgService;
    @Autowired
    private NewsCommentService newsCommentService;
    @Autowired
    private NewsReplyService newsReplyService;
    @Autowired
    private RelationThirdSubjectService relationThirdSubjectService;
    @Autowired
    private SolrQueryService solrQueryService;
    @Autowired
    private IStatisticService statisticService;

    @SuppressWarnings("rawtypes")
	@Autowired
    protected KafkaProducerTemplate producerTeplate;


    /**
     * 
     * @Title: getNewsInfos  
     * @Description: 查询新闻列表
     * @param newsInfoListVO
     * @throws BusinessException    参数  
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/getNewsInfos")
    @ResponseBody
    public ResultJSON getNewsInfos(@RequireValid NewsInfoListVO newsInfoListVO) throws BusinessException {
        NewsQuery newsQuery = new NewsQuery();
        ResultJSON resJson = null;
        JSONObject json = null;
        JSONArray jsonArray = new JSONArray();
        JSONObject body = new JSONObject();
        try {
        	// 拼接查询条件
            SysUserInfo sysUser = sysUserService.selectByCode(newsInfoListVO.getUserCode());
            newsQuery.setUserCode(newsInfoListVO.getUserCode());
            newsQuery.setPageNo(Integer.valueOf(newsInfoListVO.getPageNo()));
            newsQuery.setPageSize(Integer.valueOf(newsInfoListVO.getPageSize()));
            newsQuery.setLimitStart(newsQuery.getPageNo() * newsQuery.getPageSize());
            newsQuery.setInfoTitle(newsInfoListVO.getInfoTitle());
            newsQuery.setOrgCode(sysUser.getManageOrgInfo().getManOrgCode());
            newsQuery.setIsDelete(NewsConstants.NEWS_NO_DELETE);
            newsQuery.setIsShield(NewsConstants.NEWS_NO_SHIELD);
            newsQuery.setSelfFlag(Integer.valueOf(newsInfoListVO.getSelfFlag()));
            if (null != newsInfoListVO.getInfoTitle() && !"".equals(newsInfoListVO.getInfoTitle())) {
                newsQuery.setInfoTitle(newsInfoListVO.getInfoTitle());
            }
            if (null != newsInfoListVO.getInfoStatus() && !"".equals(newsInfoListVO.getInfoStatus())) {
                newsQuery.setInfoStatus(Integer.valueOf(newsInfoListVO.getInfoStatus()));
            }
            if (null != newsInfoListVO.getStartTime() && !"".equals(newsInfoListVO.getStartTime())) {
                newsQuery.setStartTime(Long.valueOf(newsInfoListVO.getStartTime()));
            }
            if (null != newsInfoListVO.getEndTime()&& !"".equals(newsInfoListVO.getEndTime())) {
                newsQuery.setEndTime(Long.valueOf(newsInfoListVO.getEndTime()));
            }
            
            // 查询
            PageBean pageBean = newsInfoContentService.getNewsInfos(newsQuery);
            
            //返回值封装
            List<NewsInfoContent> list = pageBean.getRecordList();
            if (list.size() > 0) {
                List<String> usercodes = new ArrayList<>();
                for (NewsInfoContent newsInfoContent : list) {
                    usercodes.add(newsInfoContent.getInfoOperatorCode());
                }
                List<SysUserInfo> userInfos = sysUserService.selectByCodes(usercodes);
                Map<String, SysUserInfo> userMap = generateUserInfoMap(userInfos);
                for (int i = 0; i < list.size(); i++) {
                    json = new JSONObject();
                    NewsInfoContent temp = list.get(i);
                    json.put("newsCode", temp.getNewsCode());
                    json.put("infoTitle", temp.getInfoTitle());
                    json.put("infoContentText", temp.getInfoContentText());
                    json.put("infoContentHtml", temp.getInfoContentHtml());
                    json.put("fileUrl", temp.getFileUrl());
                    json.put("infoStatus", temp.getInfoStatus());
                    json.put("infoOperatorCode", temp.getInfoOperatorCode());
                    SysUserInfo userInfo = userMap.get(temp.getInfoOperatorCode());
                    json.put("infoOperatorName", userInfo == null ? "" : userInfo.getUserName());
                    json.put("infoCreatorCode", temp.getInfoCreatorCode());
                    json.put("isTop", temp.getIsTop());
                    json.put("infoSort", temp.getInfoSort());
                    json.put("isHidden", temp.getIsHidden());
                    json.put("modifyTime", temp.getModifyTime());
                    json.put("publishTime", temp.getPublishTime());
                    jsonArray.add(json);
                }
            }
            
            // 返回值
            resJson = new ResultJSON("COMMON_200");
            body.put("totalCount", pageBean.getTotalCount());
            body.put("topTotal", pageBean.getNumPerPage());
            body.put("list", jsonArray);
            resJson.setBody(body);

            log.info("NewsCommentController.page:获取新闻列表成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "获取新闻列表");
        }
        return resJson;
    }

    /**
     * 
     * @Title: addNewsInfo  
     * @Description: 新增新闻
     * @param newsInfoContentVo
     * @throws BusinessException    参数  
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/addNewsInfo")
    @ResponseBody
    public ResultJSON addNewsInfo(@RequireValid NewsInfoContentVo newsInfoContentVo) throws BusinessException {
        NewsModel newsModel = null;
        ResultJSON resJson = null;
        JSONObject body = new JSONObject();
        try {
            //获取用户信息
            SysUserInfo sysUserInfo = sysUserService.selectByCode(newsInfoContentVo.getUserCode());

            //敏感词过滤
			JSONArray jsonArray = solrQueryService.CheckSentence(sysUserInfo.getManageOrgInfo().getSysOrgCode(),
					newsInfoContentVo.getInfoTitle() + newsInfoContentVo.getInfoContentHtml(),
					SolrCoreConstants.SENSITIVE_WORD_CORE);
			if (jsonArray.size() > 0) {
				resJson = new ResultJSON("COMMON_999");
				body.put("totalCount", jsonArray.size());
				body.put("isPass", false);
				body.put("list", jsonArray);
				resJson.setBody(body);
				return resJson;
			}
			
			// 拼接新闻信息
            newsModel = convertVoToModel(newsInfoContentVo, sysUserInfo, newsInfoContentVo.getUserCode());
            newsModel.setNewsCode(UUIDHelper.getUUID());
            
            // 保存新闻信息
            boolean flag = newsInfoContentService.addNewsInfo(newsModel);
            if (flag) {
                resJson = new ResultJSON("COMMON_200");
            }else {
                resJson = new ResultJSON("COMMON_999");
            }
            resJson.setBody(body);
            log.info("NewsCommentController.addNewsInfo:新闻添加成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻添加");
        }
        return resJson;
    }

    /**
     * 
     * @Title: updateNewsInfo  
     * @Description: 修改新闻
     * @param newsInfoContentVo
     * @throws BusinessException    参数  
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/updateNewsInfo")
    @ResponseBody
    public ResultJSON updateNewsInfo(@RequireValid NewsInfoContentVo newsInfoContentVo) throws BusinessException {
        NewsModel newsModel = null;
        ResultJSON resJson = null;
        JSONObject body = new JSONObject();
        try {
            //获取用户信息
            SysUserInfo sysUserInfo = sysUserService.selectByCode(newsInfoContentVo.getUserCode());

            //敏感词过滤
			JSONArray jsonArray = solrQueryService.CheckSentence(sysUserInfo.getManageOrgInfo().getSysOrgCode(),
					newsInfoContentVo.getInfoTitle() + newsInfoContentVo.getInfoContentHtml(),
					SolrCoreConstants.SENSITIVE_WORD_CORE);
			if (jsonArray.size() > 0) {
				resJson = new ResultJSON("COMMON_999");
				body.put("totalCount", jsonArray.size());
				body.put("isPass", false);
				body.put("list", jsonArray);
				resJson.setBody(body);
				return resJson;
			}

            // 拼接新闻信息
            newsModel = convertVoToModel(newsInfoContentVo, sysUserInfo, null);
            
            // 修改新闻信息
            boolean flag = newsInfoContentService.updateNewsInfo(newsModel);
            if (flag) {
                resJson = new ResultJSON("COMMON_200");
            } else {
                resJson = new ResultJSON("COMMON_999");
            }
            resJson.setBody(body);
            log.info("NewsCommentController.updateNewsInfo:新闻修改成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻修改");
        }
        return resJson;
    }

    /**
     * 
     * @Title: deleteNewsInfo  
     * @Description: 删除新闻
     * @param newsInfoDeleteVO
     * @throws BusinessException    参数  
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/deleteNewsInfo")
    @ResponseBody
    public ResultJSON deleteNewsInfo(@RequireValid NewsInfoDeleteVO newsInfoDeleteVO) throws BusinessException {
        NewsModel newsModel = null;
        ResultJSON resJson = null;
        JSONObject body = new JSONObject();
        try {
        	// 拼接查询条件
            String userCode = newsInfoDeleteVO.getUserCode();
            SysUserInfo sysUserInfo = sysUserService.selectByCode(userCode);
            newsModel = new NewsModel();
            newsModel.setInfoOperatorCode(userCode);
            newsModel.setNewsCode(newsInfoDeleteVO.getNewsCode());
            newsModel.setInfoOperatorOrg(sysUserInfo.getManageOrgInfo().getManOrgCode());
            newsModel.setTenantCode(sysUserInfo.getTenantCode());
            newsModel.setIsDelete(Integer.valueOf(newsInfoDeleteVO.getIsDelete()));
            
            // 删除
            boolean flag = newsInfoContentService.deleteNewsInfo(newsModel);
            if (flag) {
            	// 修改关系服务中新闻状态信息
            	RelationSubjectVO relationSubjectVO = new RelationSubjectVO();
            	relationSubjectVO.setActionClass(RelationConstants.RELATION_ACTION_YES);
            	relationSubjectVO.setSubjectCode(newsInfoDeleteVO.getNewsCode());
            	relationSubjectVO.setSubjectClass(RelationConstants.RELATION_SUBJECT_NEWS);
            	relationSubjectVO.setModifyTime(new Date().getTime());
            	relationThirdSubjectService.updateNewsDeleteStatus(relationSubjectVO);
            	
                resJson = new ResultJSON("COMMON_200");
                resJson.setBody(body);
            }
            log.info("NewsCommentController.deleteNewsInfo:新闻删除成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻删除");
        }
        return resJson;
    }

    /**
     * 
     * @Title: getNewsInfoHistorys  
     * @Description: 查询新闻历程列表
     * @param newsInfoHandlerVO
     * @throws BusinessException    参数  
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/getNewsInfoHistorys")
    @ResponseBody
    public ResultJSON getNewsInfoHistorys(@RequireValid NewsInfoHandlerVO newsInfoHandlerVO) throws BusinessException {
        ResultJSON resJson = null;
        JSONObject json = null;
        JSONArray jsonArray = new JSONArray();
        JSONObject body = new JSONObject();
        try {
        	// 拼接查询条件
        	NewsHistoryQuery newsHistoryQuery = new NewsHistoryQuery();
            newsHistoryQuery.setUserCode(newsInfoHandlerVO.getUserCode());
            newsHistoryQuery.setNewsCode(newsInfoHandlerVO.getNewsCode());
            
            // 查询
            List<NewsInfoHistory> list = newsInfoHistoryService.getNewsInfoHistorys(newsHistoryQuery);
            
            // 拼接返回值
            if (list.size() > 0) {
                List<String> usercodes = new ArrayList<>();
                for (NewsInfoHistory newsInfoHistory : list) {
                    usercodes.add(newsInfoHistory.getInfoOperatorCode());
                }
                List<SysUserInfo> userInfos = sysUserService.selectByCodes(usercodes);
                Map<String, SysUserInfo> userMap = generateUserInfoMap(userInfos);
                for (int i = 0; i < list.size(); i++) {
                    json = new JSONObject();
                    NewsInfoHistory temp = list.get(i);
                    json.put("infoHistoryCode", temp.getInfoHistoryCode());
                    json.put("creatTime", temp.getNewsCode());
                    json.put("infoOperatorCode", temp.getInfoOperatorCode());
                    SysUserInfo sysUserInfo = userMap.get(temp.getInfoOperatorCode());
                    json.put("infoOperatorName", sysUserInfo == null ? "" : sysUserInfo.getUserName());
                    json.put("infoStatus", temp.getInfoStatus());
                    json.put("infoRemark", temp.getInfoRemark());
                    json.put("createTime", temp.getCreatTime());
                    jsonArray.add(json);
                }
            }
            resJson = new ResultJSON("COMMON_200");
            body.put("list", jsonArray);
            resJson.setBody(body);

            log.info("NewsCommentController.getNewsInfoHistorys:获取新闻历程列表成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "获取新闻历程列表");
        }
        return resJson;
    }

    /**
     * 
     * @Title: addNewsInfoHistory  
     * @Description: 新闻审核
     * @param newsInfoAuditVo
     * @throws BusinessException    参数  
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/addNewsInfoHistory")
    @ResponseBody
    public ResultJSON addNewsInfoHistory(@RequireValid NewsInfoAuditVo newsInfoAuditVo) throws BusinessException {
        NewsModel newsModel = null;
        ResultJSON resJson = null;
        JSONObject body = new JSONObject();
        try {
            // 拼接参数
            SysUserInfo sysUserInfo = sysUserService.selectByCode(newsInfoAuditVo.getUserCode());
            newsModel = new NewsModel();
            newsModel.setUserCode(newsInfoAuditVo.getUserCode());
            newsModel.setNewsCode(newsInfoAuditVo.getNewsCode());
            newsModel.setInfoStatus(Integer.valueOf(newsInfoAuditVo.getInfoStatus()));
            newsModel.setInfoRemark(newsInfoAuditVo.getInfoRemark());
            newsModel.setInfoOperatorOrg(sysUserInfo.getManageOrgInfo().getManOrgCode());
            newsModel.setTenantCode(sysUserInfo.getTenantCode());
            newsModel.setInfoTitle(newsInfoAuditVo.getInfoTitle());
            newsModel.setInfoContentText(newsInfoAuditVo.getInfoContentText());
            newsModel.setFileUrl(newsInfoAuditVo.getFileUrl());
            
            // 保存
            boolean flag = newsInfoContentService.addNewsInfoHistory(newsModel);
			if (flag) {
				// 如果是审核通过，则进行如下操作
				if (newsModel.getInfoStatus() == NewsConstants.NEWS_STATUS_PUBLISHED) {
					SysUserInfo author = sysUserService.selectByCode(newsInfoAuditVo.getInfoCreatorCode());
					// 添加新闻统计
					StatisticCommon record = generateStatisticCommon(author);
					statisticService.addRecord(record);
					
					// 添加积分消息
					sendCreditMsg(author.getOrgCode(), author.getManageOrgInfo().getManOrgCode(), (byte) 3,
							CreditConstants.COMMAND_NEWS_CITYPUBLISH);
					
					// 保存关系内容信息
					RelationSubjectInfo relationSubjectInfo = generateRelationInfo(newsModel, sysUserInfo);
					boolean relationFlag = relationThirdSubjectService.saveSubject(relationSubjectInfo);
					if (relationFlag) {
						resJson = new ResultJSON("COMMON_200");
					} else {
						throw BusinessException.build("NEWS_14009", "新闻关系新增");
					}
				} else {
					resJson = new ResultJSON("COMMON_200");
				}
			}
            resJson.setBody(body);
            log.info("NewsCommentController.addNewsInfoHistory:新闻审核操作成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻审核操作");
        }
        return resJson;
    }

    /**
     * 
     * @Title: updateNewsInfosMove  
     * @Description: 新闻上移下移操作
     * @param newsInfoMoveVo
     * @throws BusinessException    参数  
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/updateNewsInfosMove")
    @ResponseBody
    public ResultJSON updateNewsInfosMove(@RequireValid NewsInfoMoveVo newsInfoMoveVo) throws BusinessException {
        NewsModel newsModel = null;
        ResultJSON resJson = null;
        JSONObject body = new JSONObject();
        try {
            // 拼接参数
            SysUserInfo sysUserInfo = sysUserService.selectByCode(newsInfoMoveVo.getUserCode());
            newsModel = new NewsModel();
            newsModel.setNewsCode(newsInfoMoveVo.getNewsCode());
            newsModel.setUserCode(newsInfoMoveVo.getUserCode());
            newsModel.setInfoOperatorCode(newsInfoMoveVo.getUserCode());
            newsModel.setMoveType(Integer.valueOf(newsInfoMoveVo.getMoveType()));
            newsModel.setInfoOperatorOrg(sysUserInfo.getManageOrgInfo().getManOrgCode());
            newsModel.setTenantCode(sysUserInfo.getTenantCode());
            newsModel.setInfoSort(Integer.valueOf(newsInfoMoveVo.getInfoSort()));
            if(null != newsInfoMoveVo.getInfoTitle() && !"".equals(newsInfoMoveVo.getInfoTitle())){
                newsModel.setInfoTitle(newsInfoMoveVo.getInfoTitle());
            }
            if(null != newsInfoMoveVo.getInfoStatus() && !"".equals(newsInfoMoveVo.getInfoStatus())){
                newsModel.setInfoStatus(Integer.valueOf(newsInfoMoveVo.getInfoStatus()));
            }
            if(null != newsInfoMoveVo.getStartTime() && !"".equals(newsInfoMoveVo.getStartTime())){
                newsModel.setStartTime(Long.valueOf(newsInfoMoveVo.getStartTime()));
            }
            if(null != newsInfoMoveVo.getEndTime()&& !"".equals(newsInfoMoveVo.getEndTime())){
                newsModel.setEndTime(Long.valueOf(newsInfoMoveVo.getEndTime()));
            }

            // 修改排序
            boolean flag = newsInfoContentService.updateNewsInfoSort(newsModel);
            if (flag) {
                resJson = new ResultJSON("COMMON_200");
                resJson.setBody(body);
            }
            log.info("NewsCommentController.updateNewsInfosMove:新闻移动成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻移动");
        }
        return resJson;
    }

    /**
     * 
     * @Title: updateNewsInfosHidden  
     * @Description: 隐藏/取消隐藏新闻
     * @param newsInfoHiddenVo
     * @throws BusinessException    参数  
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/updateNewsInfosHidden")
    @ResponseBody
    public ResultJSON updateNewsInfosHidden(@RequireValid NewsInfoHiddenVo newsInfoHiddenVo) throws BusinessException {
        NewsModel newsModel = null;
        ResultJSON resJson = null;
        JSONObject body = new JSONObject();
        String hiddenStatus = "隐藏";
        try {
            if (newsInfoHiddenVo.getIsHidden() == NewsConstants.NEWS_NO_HIDDEN) {
                hiddenStatus = "取消隐藏";
            }
            
            // 拼接参数
            SysUserInfo sysUserInfo = sysUserService.selectByCode(newsInfoHiddenVo.getUserCode());
            newsModel = new NewsModel();
            newsModel.setNewsCode(newsInfoHiddenVo.getNewsCode());
            newsModel.setUserCode(newsInfoHiddenVo.getUserCode());
            newsModel.setInfoOperatorCode(newsInfoHiddenVo.getUserCode());
            newsModel.setIsHidden(newsInfoHiddenVo.getIsHidden());
            newsModel.setInfoOperatorOrg(sysUserInfo.getManageOrgInfo().getManOrgCode());
            newsModel.setTenantCode(sysUserInfo.getTenantCode());
            
            // 修改隐藏状态
            boolean flag = newsInfoContentService.updateNewsInfosHidden(newsModel);
            if (flag) {
            	// 修改关系服务中新闻状态信息
            	RelationSubjectVO relationSubjectVO = new RelationSubjectVO();
            	if (newsInfoHiddenVo.getIsHidden() == NewsConstants.NEWS_NO_HIDDEN) {
                	relationSubjectVO.setActionClass(RelationConstants.RELATION_ACTION_NO);
            	} else {
                	relationSubjectVO.setActionClass(RelationConstants.RELATION_ACTION_YES);
            	}
            	relationSubjectVO.setSubjectCode(newsInfoHiddenVo.getNewsCode());
            	relationSubjectVO.setSubjectClass(RelationConstants.RELATION_SUBJECT_NEWS);
            	relationSubjectVO.setModifyTime(new Date().getTime());
            	relationThirdSubjectService.updateNewsDeleteStatus(relationSubjectVO);
            	
                resJson = new ResultJSON("COMMON_200");
                resJson.setBody(body);
            }
            log.info("NewsCommentController.updateNewsInfosMove:新闻" + hiddenStatus + "成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻" + hiddenStatus);
        }
        return resJson;
    }

    /**
     * 
     * @Title: updateNewsInfosUp  
     * @Description: 新闻置顶/取消置顶
     * @param newsInfoTopVo
     * @throws BusinessException    参数  
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/updateNewsInfosUp")
    @ResponseBody
    public ResultJSON updateNewsInfosUp(@RequireValid NewsInfoTopVo newsInfoTopVo) throws BusinessException {
        NewsModel newsModel = null;
        ResultJSON resJson = null;
        JSONObject body = new JSONObject();
        String topStatus = "置顶";
        try {
            if (NewsConstants.NEWS_NO_TOP==Integer.valueOf(newsInfoTopVo.getActionType())) {
                topStatus = "取消置顶";
            }
            
            // 拼接参数
            SysUserInfo sysUserInfo = sysUserService.selectByCode(newsInfoTopVo.getUserCode());
            newsModel = new NewsModel();
            newsModel.setNewsCode(newsInfoTopVo.getNewsCode());
            newsModel.setUserCode(newsInfoTopVo.getUserCode());
            newsModel.setInfoOperatorCode(newsInfoTopVo.getUserCode());
            newsModel.setIsTop(Integer.valueOf(newsInfoTopVo.getActionType()));
            newsModel.setInfoOperatorOrg(sysUserInfo.getManageOrgInfo().getManOrgCode());
            newsModel.setTenantCode(sysUserInfo.getTenantCode());
            
            // 修改置顶状态
            boolean flag = newsInfoContentService.updateNewsInfosUp(newsModel);
            if (flag) {
                resJson = new ResultJSON("COMMON_200");
                resJson.setBody(body);
            }
            log.info("NewsCommentController.updateNewsInfosUp:新闻" + topStatus + "成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻" + topStatus);
        }
        return resJson;
    }

    /**
     * 
     * @Title: updateNewsInfosRepeal  
     * @Description: 新闻撤销、提交审核
     * @param newsInfoRepealVo
     * @throws BusinessException    参数  
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/updateNewsInfosRepeal")
    @ResponseBody
    public ResultJSON updateNewsInfosRepeal(@RequireValid NewsInfoRepealVo newsInfoRepealVo) throws BusinessException {
        NewsModel newsModel = null;
        ResultJSON resJson = null;
        JSONObject body = new JSONObject();
        try {
            // 拼接参数
            SysUserInfo sysUserInfo = sysUserService.selectByCode(newsInfoRepealVo.getUserCode());
            newsModel = new NewsModel();
            newsModel.setNewsCode(newsInfoRepealVo.getNewsCode());
            newsModel.setUserCode(newsInfoRepealVo.getUserCode());
            newsModel.setInfoStatus(Integer.valueOf(newsInfoRepealVo.getInfoStatus()));
            newsModel.setInfoOperatorCode(newsInfoRepealVo.getUserCode());
            newsModel.setInfoOperatorOrg(sysUserInfo.getManageOrgInfo().getManOrgCode());
            newsModel.setTenantCode(sysUserInfo.getTenantCode());
            
            // 修改新闻状态
            boolean flag = newsInfoContentService.updateNewsInfo(newsModel);
            if (flag) {
                resJson = new ResultJSON("COMMON_200");
            }else{
                resJson = new ResultJSON("COMMON_999");
            }
            resJson.setBody(body);
            log.info("NewsCommentController.updateNewsInfosRepeal:新闻操作成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻操作");
        }
        return resJson;
    }

    /**
     * 
     * @Title: getNewsInfosMonitoring  
     * @Description: 内容监控--新闻查询列表
     * @param newsInfoListVO
     * @throws BusinessException    参数  
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/getNewsInfosMonitoring")
    @ResponseBody
    public ResultJSON getNewsInfosMonitoring(@RequireValid NewsInfoListVO newsInfoListVO) throws BusinessException {
        NewsQuery newsQuery = new NewsQuery();
        ResultJSON resJson = null;
        JSONObject json = null;
        JSONArray jsonArray = new JSONArray();
        JSONObject body = new JSONObject();
        try {
        	// 拼接参数
            SysUserInfo sysUser = sysUserService.selectByCode(newsInfoListVO.getUserCode());
            newsQuery.setUserCode(newsInfoListVO.getUserCode());
            newsQuery.setPageNo(Integer.valueOf(newsInfoListVO.getPageNo()));
            newsQuery.setPageSize(Integer.valueOf(newsInfoListVO.getPageSize()));
            newsQuery.setLimitStart(newsQuery.getPageNo() * newsQuery.getPageSize());
            newsQuery.setInfoTitle(newsInfoListVO.getInfoTitle());
            newsQuery.setOrgCode(sysUser.getManageOrgInfo().getManOrgCode());
            newsQuery.setIsDelete(NewsConstants.NEWS_NO_DELETE);
            newsQuery.setIsShield(NewsConstants.NEWS_NO_SHIELD);
            newsQuery.setInfoStatus(NewsConstants.NEWS_STATUS_PUBLISHED);//查询已发布新闻
            
            // 查询
            PageBean pageBean = newsInfoContentService.getNewsInfos(newsQuery);
            
            //返回值封装
            List<NewsInfoContent> list = pageBean.getRecordList();
            if (list.size() > 0) {
                List<String> usercodes = new ArrayList<>();
                List<String> orgCodes = new ArrayList<>();

                for (NewsInfoContent newsInfoContent : list) {
                    usercodes.add(newsInfoContent.getInfoOperatorCode());
                    usercodes.add(newsInfoContent.getInfoCreatorCode()) ;
                    orgCodes.add(newsInfoContent.getOrgCode()) ;
                }
                List<SysUserInfo> userInfos = sysUserService.selectByCodes(usercodes);
                Map<String, SysUserInfo> userMap = generateUserInfoMap(userInfos);
                List<SysOrgInfo> sysOrgInfos = sysOrgService.selectByCodes(orgCodes) ;
                Map<String, SysOrgInfo> orgMap = generateOrgInfoMap(sysOrgInfos);
                for (int i = 0; i < list.size(); i++) {
                    json = new JSONObject();
                    NewsInfoContent temp = list.get(i);
                    json.put("id", temp.getId());
                    json.put("newsCode", temp.getNewsCode());
                    json.put("infoTitle", temp.getInfoTitle());
                    json.put("infoContentText", temp.getInfoContentText());
                    json.put("infoContentHtml", temp.getInfoContentHtml());
                    json.put("fileUrl", temp.getFileUrl());
                    json.put("infoCreatorCode", temp.getInfoOperatorCode());
                    SysUserInfo creator = userMap.get(temp.getInfoCreatorCode());
                    json.put("infoCreatorName", creator == null ? "" : creator.getUserName());
                    json.put("infoOperatorCode", temp.getInfoOperatorCode());
                    SysUserInfo userInfo = userMap.get(temp.getInfoOperatorCode());
                    json.put("infoOperatorName", userInfo == null ? "" : userInfo.getUserName());
                    json.put("isTop", temp.getIsTop());
                    json.put("infoSort", temp.getInfoSort());
                    json.put("infoOperatorOrg",temp.getOrgCode());
                    SysOrgInfo sysOrgInfo = orgMap.get(temp.getOrgCode()) ;
                    json.put("orgName", sysOrgInfo==null?"":sysOrgInfo.getSysOrgName());
                    json.put("createTime", temp.getCreateTime());
                    json.put("modifyTime", temp.getModifyTime());
                    json.put("publishTime", temp.getPublishTime());
                    jsonArray.add(json);
                }
            }

            resJson = new ResultJSON("COMMON_200");
            body.put("totalCount", pageBean.getTotalCount());
            body.put("topTotal", pageBean.getNumPerPage());
            body.put("list", jsonArray);
            resJson.setBody(body);

            log.info("NewsCommentController.getNewsInfosMonitoring:获取新闻列表成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "获取新闻列表");
        }
        return resJson;
    }

    /**
     * 
     * @Title: getNewsByCode  
     * @Description: 查询新闻详情
     * @param newsBaseVo
     * @throws BusinessException    参数  
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/getNewsByCode")
    @ResponseBody
    public ResultJSON getNewsByCode(@RequireValid NewsBaseVo newsBaseVo) throws BusinessException{
        NewsQuery newsQuery=new NewsQuery();
        ResultJSON resJson = null ;
        JSONObject json = new JSONObject();
        try {
        	// 拼接参数
            newsQuery.setIsHidden(NewsConstants.NEWS_NO_HIDDEN);//只查询未被隐藏的新闻
            newsQuery.setIsDelete(NewsConstants.NEWS_NO_DELETE);//只查询未被隐藏的新闻
            newsQuery.setNewsCode(newsBaseVo.getNewsCode());
            newsQuery.setUserCode(newsBaseVo.getUserCode());
            
            // 查询
            NewsInfoContent newsInfoContent=newsInfoContentService.getNewsInfoDetail(newsQuery);
            
            //返回值封装
            if(null != newsInfoContent){
                SysOrgInfo sysOrgInfo = sysOrgService.selectByCode(newsInfoContent.getOrgCode()) ;
                SysUserInfo sysUser = sysUserService.selectByCode(newsInfoContent.getInfoCreatorCode());
                json.put("newsCode", newsInfoContent.getNewsCode());
                json.put("infoTitle",newsInfoContent.getInfoTitle());
                json.put("infoContentText",newsInfoContent.getInfoContentText());
                json.put("infoContentHtml",newsInfoContent.getInfoContentHtml());
                json.put("fileUrl",newsInfoContent.getFileUrl());
                json.put("infoStatus", newsInfoContent.getInfoStatus());
                json.put("infoOperatorCode", newsInfoContent.getInfoOperatorCode());
                json.put("infoOperatorName", sysUser==null?"":sysUser.getUserName());
                json.put("infoCreatorCode", newsInfoContent.getInfoCreatorCode());
                json.put("infoCreatorName", sysUser==null?"":sysUser.getUserName());
//                json.put("orgLevel", newsInfoContent.getOrgLevel());
                json.put("orgCode", newsInfoContent.getOrgCode());
                json.put("orgName", sysOrgInfo.getSysOrgName());
//                json.put("isTop", newsInfoContent.getIsTop());
//                json.put("infoSort", newsInfoContent.getInfoSort());
                json.put("infoGoodCount", newsInfoContent.getInfoGoodCount());
                json.put("infoCommentCount", newsInfoContent.getInfoCommentCount());
                json.put("collectNumber", newsInfoContent.getCollectNumber());
                json.put("transmitNumber", newsInfoContent.getTransmitNumber());
                json.put("createTime", newsInfoContent.getCreateTime());
                json.put("modifyTime", newsInfoContent.getModifyTime());
                json.put("publishTime", newsInfoContent.getPublishTime());
            }
            resJson=new ResultJSON("COMMON_200");
            resJson.setBody(json);

            log.info("NewsCommentController.getNewsByCode:获取新闻列表成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "获取新闻列表");
        }
        return resJson;
    }

    /**
     * 
     * @Title: getNewsCommentListById  
     * @Description: 内容监控 - 新闻评论列表查询
     * @param newsCommentListVO
     * @throws BusinessException    参数  
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/getNewsCommentListById")
    @ResponseBody
    public ResultJSON getNewsCommentListById(@RequireValid NewsCommentListVO newsCommentListVO) throws BusinessException{
        NewsCommentQuery newsCommentQuery=null;
        ResultJSON resJson = null ;
        JSONObject json = null ;
        JSONArray jsonArray=new JSONArray();
        JSONObject body = new JSONObject();
        try {
        	// 拼接参数
            newsCommentQuery = new NewsCommentQuery() ;
            newsCommentQuery.setNewsCode(newsCommentListVO.getNewsCode());
            newsCommentQuery.setId(newsCommentListVO.getId());
            newsCommentQuery.setUserCode(newsCommentListVO.getUserCode());
            newsCommentQuery.setPageNo(newsCommentListVO.getPageNo());
            newsCommentQuery.setPageSize(newsCommentListVO.getPageSize());
            newsCommentQuery.setLimitStart(newsCommentListVO.getPageNo()*newsCommentListVO.getPageSize());
            
            // 查询
            PageBean pageBean=newsCommentService.getNewsComments(newsCommentQuery);
            
            //返回值封装
            List<NewsInfoComment> list = pageBean.getRecordList();
            if(list.size()>0){
                List<String> usercodes = new ArrayList<>();
                for(NewsInfoComment newsInfoComment : list){
                    usercodes.add(newsInfoComment.getUserCode()) ;
                }
                Map<String,Object> userMap = sysUserService.selectMapByCodes(usercodes);
                for(int i=0;i<list.size();i++){
                    json=new JSONObject();
                    NewsInfoComment temp=list.get(i);
                    json.put("id", temp.getId());
                    json.put("infoCommentCode", temp.getCommentCode());
                    json.put("newsCode",temp.getNewsCode());
                    json.put("userCode",temp.getUserCode());
                    SysUserInfo creator = (SysUserInfo)userMap.get(temp.getUserCode());
                    json.put("userName", creator == null ? "" : creator.getUserName());
                    json.put("commentContent",temp.getCommentContent());
                    json.put("commentGoodCount", temp.getCommentLikeNum());
                    json.put("createTime", temp.getCreateTime());
                    json.put("modifyTime", temp.getModifyTime());
                    jsonArray.add(json) ;
                }
            }
            resJson=new ResultJSON("COMMON_200");
            body.put("totalCount", pageBean.getTotalCount());
            body.put("list", jsonArray);
            resJson.setBody(body);

            log.info("NewsCommentController.getNewsCommentListById:新闻评论列表查询成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "获取新闻评论列表");
        }
        return resJson;
    }

    /**
     * 
     * @Title: getNewsReplyListById  
     * @Description: 内容监控 - 新闻评论回复查询列表
     * @param newsReplyVo
     * @throws BusinessException    参数  
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/getNewsReplyListById")
    @ResponseBody
    public ResultJSON getNewsReplyListById(@RequireValid NewsReplyVo newsReplyVo) throws BusinessException {
        NewsReplyQuery newsReplyQuery = new NewsReplyQuery();
        ResultJSON resJson = null;
        JSONObject json = null;
        JSONArray jsonArray = new JSONArray();
        JSONObject body = new JSONObject();
        try {
        	// 拼接参数
            newsReplyQuery.setNewsCommentCode(newsReplyVo.getNewsCommentCode());
            newsReplyQuery.setPageNo(Integer.valueOf(newsReplyVo.getPageNo()));
            newsReplyQuery.setPageSize(Integer.valueOf(newsReplyVo.getPageSize()));
            newsReplyQuery.setLimitStart(newsReplyQuery.getPageNo() * newsReplyQuery.getPageSize());
            newsReplyQuery.setId(Integer.valueOf(newsReplyVo.getId()));
            newsReplyQuery.setReplyIsDelete(NewsReplyConstants.NEWS_REPLY_NOT_DELETE);
            newsReplyQuery.setReplyIsShield(NewsReplyConstants.NEWS_REPLY_NOT_SHIELD);
            
            // 查询
            PageBean pageBean = newsReplyService.getNewsReplys(newsReplyQuery);
            
            //返回值封装
            List<NewsReply> list = pageBean.getRecordList();
            if (list.size() > 0) {
                List<String> usercodes = new ArrayList<>();
                List<String> orgCodes = new ArrayList<>();

                for (NewsReply newsReply : list) {
                    usercodes.add(newsReply.getUserCode());
                    orgCodes.add(newsReply.getOrgCode()) ;
                }
                List<SysUserInfo> userInfos = sysUserService.selectByCodes(usercodes);
                Map<String, SysUserInfo> userMap = generateUserInfoMap(userInfos);
                List<SysOrgInfo> sysOrgInfos = sysOrgService.selectByCodes(orgCodes) ;
                Map<String, SysOrgInfo> orgMap = generateOrgInfoMap(sysOrgInfos);
                for (int i = 0; i < list.size(); i++) {
                    json = new JSONObject();
                    NewsReply temp = list.get(i);
                    json.put("id", temp.getId());
                    json.put("replyCode", temp.getReplyCode());
                    json.put("replyContent", temp.getReplyContent());
                    json.put("commentCode", temp.getCommentCode());
                    json.put("userCode", temp.getUserCode());
                    SysUserInfo creator = userMap.get(temp.getUserCode());
                    json.put("userName", creator == null ? "" : creator.getUserName());
                    json.put("orgCode",temp.getOrgCode());
                    SysOrgInfo sysOrgInfo = orgMap.get(temp.getOrgCode()) ;
                    json.put("orgName", sysOrgInfo==null?"":sysOrgInfo.getSysOrgName());
                    json.put("createTime", temp.getCreateTime());
                    json.put("modifyTime", temp.getModifyTime());
                    jsonArray.add(json);
                }
            }

            resJson = new ResultJSON("COMMON_200");
            body.put("totalCount", pageBean.getTotalCount());
            body.put("topTotal", pageBean.getNumPerPage());
            body.put("list", jsonArray);
            resJson.setBody(body);

            log.info("NewsCommentController.getNewsReplyListById:新闻评论回复查询列表成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻评论回复查询列表");
        }
        return resJson;
    }

    /**
     * 
     * @Title: generateStatisticCommon  
     * @Description: 添加统计
     * @param author
     * @return: StatisticCommon    返回类型
     */
	private StatisticCommon generateStatisticCommon(SysUserInfo author) {
		// 统计操作
		StatisticCommon statisticCommon = new StatisticCommon();
		statisticCommon.setOrgCode(author.getManageOrgInfo().getSysOrgCode());
		statisticCommon.setPeopleCode(author.getUserCode());
		statisticCommon.setPeopleName(author.getUserName());
		statisticCommon.setNews(NewsConstants.NEWS);
		return statisticCommon;
	}

	/**
	 * 
	 * @Title: convertVoToModel  
	 * @Description: 拼接新闻信息
	 * @param newsInfoContentVo
	 * @param sysUserInfo
	 * @param createUserCode
	 * @return: NewsModel    返回类型
	 */
    private NewsModel convertVoToModel(NewsInfoContentVo newsInfoContentVo, SysUserInfo sysUserInfo, String createUserCode) {
        NewsModel newsModel = new NewsModel();
        newsModel.setUserCode(newsInfoContentVo.getUserCode());
        newsModel.setNewsCode(newsInfoContentVo.getNewsCode());
        if (null != createUserCode) {
            newsModel.setInfoCreatorCode(newsInfoContentVo.getUserCode());
        } else {
            newsModel.setInfoCreatorCode(newsInfoContentVo.getInfoCreatorCode());
        }
        newsModel.setInfoOperatorCode(newsInfoContentVo.getUserCode());
        newsModel.setInfoTitle(newsInfoContentVo.getInfoTitle());
        newsModel.setInfoContentHtml(newsInfoContentVo.getInfoContentHtml());
        newsModel.setInfoContentText(newsInfoContentVo.getInfoContentText());
        newsModel.setInfoStatus(Integer.valueOf(newsInfoContentVo.getInfoStatus()));
        newsModel.setInfoRemark(newsInfoContentVo.getInfoRemark());
        newsModel.setInfoOperatorOrg(sysUserInfo.getManageOrgInfo().getManOrgCode());
        newsModel.setTenantCode(sysUserInfo.getTenantCode());
        newsModel.setFileUrl(newsInfoContentVo.getFileUrl());
        return newsModel;
    }

    /**
     * 
     * @Title: generateRelationInfo  
     * @Description: 拼装关系内容信息
     * @param newsModel
     * @param sysUserInfo
     * @return: RelationSubjectInfo    返回类型
     */
    private RelationSubjectInfo generateRelationInfo(NewsModel newsModel, SysUserInfo sysUserInfo) {
        RelationSubjectInfo relationSubjectInfo = new RelationSubjectInfo();
        relationSubjectInfo.setSubjectCode(newsModel.getNewsCode());
        relationSubjectInfo.setTenantCode(sysUserInfo.getTenantCode());
        long currentTiem = System.currentTimeMillis() ;
        relationSubjectInfo.setCreateTime(currentTiem);
        relationSubjectInfo.setModifyTime(currentTiem);
        relationSubjectInfo.setDynamicCode("");
        relationSubjectInfo.setSubjectClass((byte)5);//新闻公告
        relationSubjectInfo.setSubjectDepend((byte)3);//组织
        relationSubjectInfo.setUserCode(newsModel.getUserCode());
        relationSubjectInfo.setOrgCode(sysUserInfo.getManageOrgInfo().getManOrgCode());
        relationSubjectInfo.setSubjectPublishTime(currentTiem);
        relationSubjectInfo.setSubjectTitle(newsModel.getInfoTitle());
        relationSubjectInfo.setSubjectDigest(newsModel.getInfoContentText());
        relationSubjectInfo.setSubjectUrl(newsModel.getFileUrl());//链接
        relationSubjectInfo.setSubjectSource((byte)1);//原创
        relationSubjectInfo.setSourceIsDelete((byte)0);
        relationSubjectInfo.setSourceIsShield((byte)0);
        relationSubjectInfo.setSubjectIsDelete((byte)0);
        relationSubjectInfo.setSubjectIsShield((byte)0);
        return relationSubjectInfo ;
    }

    /**
     * 
     * @Title: generateUserInfoMap  
     * @Description: 将List类型的用户信息转换为Map类型
     * @param userInfos
     * @return: Map<String,SysUserInfo>    返回类型
     */
    private Map<String, SysUserInfo> generateUserInfoMap(List<SysUserInfo> userInfos) {
        Map<String, SysUserInfo> users = new HashMap<String, SysUserInfo>();
        for (SysUserInfo sysUserInfo : userInfos) {
            if(null !=sysUserInfo){
                users.put(sysUserInfo.getUserCode(), sysUserInfo);
            }
        }
        return users;
    }

    /**
     * 
     * @Title: generateOrgInfoMap  
     * @Description: 将List类型的组织信息转换为Map类型
     * @param sysOrgInfos
     * @return: Map<String,SysOrgInfo>    返回类型
     */
    private Map<String,SysOrgInfo> generateOrgInfoMap(List<SysOrgInfo> sysOrgInfos) {
        Map<String, SysOrgInfo> orgInfos = new HashMap<String, SysOrgInfo>();
        for (SysOrgInfo sysOrgInfo : sysOrgInfos) {
            if(null != sysOrgInfo){
                orgInfos.put(sysOrgInfo.getManOrgCode(), sysOrgInfo);
            }
        }
        return orgInfos;
    }

    /**
     *
     * @Title: sendCreditMsg
     * @Description: 	发送积分信息
     * @param accountCode	账号CODE
     * @param orgCode 		组织CODE
     * @param accountType	账号类型
     * @param actionCode		动作类型
     * @return void    返回类型
     */
    @SuppressWarnings("unchecked")
    protected void sendCreditMsg(String accountCode,String orgCode,Byte accountType,String actionCode){
        MessageIntegral msg = new MessageIntegral();
        msg.setAccountCode(accountCode);
        msg.setActionCode(actionCode);
        msg.setAccountType(accountType);
        msg.setManageOrgCode(orgCode);
        producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(msg));
    }

}
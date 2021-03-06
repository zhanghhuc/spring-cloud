package com.zssq.relation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zssq.relation.dao.mapper.RelationRecommendMapper;
import com.zssq.relation.dao.mapper.RelationSubjectDataMapper;
import com.zssq.relation.dao.mapper.RelationSubjectInfoMapper;
import com.zssq.relation.dao.mapper.RelationSubjectResourceMapper;
import com.zssq.relation.service.IRelationService;
/**
 * 
 * @ClassName: RelationServiceImpl  
 * @Description: 关系service  
 * @author sry  
 * @date 2017年6月22日  
 *
 */
@Service("relationService")
public class RelationServiceImpl implements IRelationService {
	
	/*@Autowired
	private RelationKcMblogRecommendMapper kcMblogRecommendMapper;
	
	@Autowired
	private RelationKcBlogRecommendMapper kcBlogRecommendMapper;*/
	
	@Autowired
	private RelationRecommendMapper relationRecommendMaper;
	
	@Autowired
	private RelationSubjectInfoMapper relationSubjectInfoMapper;
	
	@Autowired
	private RelationSubjectDataMapper relationSubjectDataMapper;
	
	/*@Autowired
	private RelationMblogInfoMapper mblogInfoMapper;*/
	
	@Autowired
	private RelationSubjectResourceMapper relationSubjectResourceMapper;
	
	/*@Autowired
	private RelationTeamInfoMapper teamInfoMapper;
	
	@Autowired
	private RelationSysOrgInfoMapper sysOrgInfoMapper;
	
	@Autowired
	private RelationSysUserInfoMapper sysUserInfoMapper;
	
	@Autowired
	private RelationBlogInfoMapper blogInfoMapper;*/
	/**
	 * 导入微博博推荐
	 *//*
	@Override
	public long importMblogRecommend(int pageNo, int pageSize) {
		
		List<KcMblogRecommend> lkr = kcMblogRecommendMapper.ListRecommendPage(pageNo*pageSize,pageSize);		// 1, 导入微博
		List<RelationRecommend> list = new ArrayList<RelationRecommend>();
		long selectedDataCount=0;
		long importedDataCount=0;
		long invalidDataCount=0;
		for (KcMblogRecommend mblogRecommend : lkr) {
			RelationRecommend relationRecommend = new RelationRecommend();
			relationRecommend.setRecCode(UUIDHelper.getUUID());
			// 获取微博信息
			//1，判断内容信息表中是 已插入对应的 博客信息
			RelationSubjectInfo rsi = relationSubjectInfoMapper.getSubjectBaseInfo(mblogRecommend.getmBlogCode());
			if(rsi==null){
				//插入内容信息:1,获取微博信息
				MblogModel mblogModel = mblogInfoMapper.getInfoByCode(mblogRecommend.getmBlogCode());
				if(mblogModel==null){
					continue;
				}
				boolean subjectFlag = toSaveSubject(mblogModel);
			}
			
			try {
				selectedDataCount++;
				relationRecommend.setSubjectCode(mblogRecommend.getmBlogCode());
				relationRecommend.setSubjectClass((byte) 1);
				relationRecommend.setCreateTime(mblogRecommend.getCreatetime().getTime());
				relationRecommend.setModifyTime(mblogRecommend.getCreatetime().getTime());
			// 调用授权获取组织信息
			relationRecommend.setRecUserCode(mblogRecommend.getUserCode());
			// 根据推荐类型 state 1 班组推荐 2 地市推荐 3 省推荐4集团
			if (mblogRecommend.getState() == 1) {
				
				String teamOrgCode = teamInfoMapper.getTeamInfo(mblogRecommend.getTeamCode()).getOrgCode();
				if("".equals(teamOrgCode)){
					invalidDataCount++;
					continue;
				}
				// 查询所属门户
				SysOrgInfo soi= sysOrgInfoMapper.getOrgInfoByCode(teamOrgCode);
				relationRecommend.setRecOrgCode(soi.getManOrgCode());
				relationRecommend.setRecToOrgCode(soi.getParentCode());
			} else {
				
				SysUserInfo	sysUserInfo = sysUserInfoMapper.getByCode(mblogRecommend.getUserCode());
				SysOrgInfo soi= sysOrgInfoMapper.getOrgInfoByCode(sysUserInfo.getOrgCode());
				String orgCode = soi.getManOrgCode();
				String parentOrgCode = soi.getParentCode();
				if (mblogRecommend.getState() == 2) {
					relationRecommend.setRecOrgCode(orgCode);
					
					relationRecommend.setRecToOrgCode(parentOrgCode);
				} else if (mblogRecommend.getState() == 3) {
					relationRecommend.setRecOrgCode(orgCode);
					relationRecommend.setRecToOrgCode(parentOrgCode);
				}
			}

				relationRecommend.setTenantCode("#############");
				relationRecommend.setRecSource((byte) (mblogRecommend.getState() != 1 ? 2 : 1));
				relationRecommend.setTeamCode(mblogRecommend.getTeamCode());
				relationRecommend.setQualityCode("");
				relationRecommend.setRecQualityTime(0L);
				relationRecommend.setRecStatus((byte) 1);
				relationRecommend.setRemark("");
				list.add(relationRecommend);
				importedDataCount++;
			} catch (Exception e) {
				System.err.println("导入推荐时，对应微博记录获取不到，放弃该条记录");
				invalidDataCount++;
				continue;
			}
		}
		relationRecommendMaper.batchInsert(list);
		return 0;
	}
	private boolean toSaveSubject(MblogModel qo) {
		try{
			RelationSubjectInfo reInfo = new RelationSubjectInfo();
			reInfo.setSubjectCode(qo.getMblogCode());
			reInfo.setTenantCode("");
			reInfo.setOrgCode(qo.getOrgCode());
			reInfo.setCreateTime(qo.getCreateTime());
			reInfo.setModifyTime(qo.getModifyTime());
			if(StringUtils.isNotBlank(qo.getContent())){
				reInfo.setSubjectTitle(qo.getContent().substring(0, 20));
			}
			reInfo.setRemark("");
			reInfo.setSubjectClass(RelationConstants.RELATION_SUBJECT_MBLOG);
			reInfo.setSubjectDepend(qo.getMblogDepend());
			reInfo.setTeamCode(qo.getTeamCode());
			reInfo.setUserCode(qo.getUserCode());
			reInfo.setSubjectPublishTime(qo.getPublishTime());
			reInfo.setSubjectDigest(qo.getContent());
			reInfo.setSourceCode(qo.getSourceMblogCode());
			reInfo.setSourceUserCode(qo.getSourceUserCode());
			reInfo.setSourcePublishTime(MblogConstants.MBLOG_TIME_ZERO);
			reInfo.setSourceIsDelete(RelationConstants.RELATION_NO);
			reInfo.setSourceIsShield(RelationConstants.RELATION_NO);
			reInfo.setContentTips(MblogConstants.MBLOG_BLANK);
			reInfo.setSubjectIsDelete(RelationConstants.RELATION_NO);
			reInfo.setSubjectIsShield(RelationConstants.RELATION_NO);
			reInfo.setSubjectSource(RelationConstants.RELATION_SUBJECT_SOURCE_ORIGINAL);
			
			//liang
			// 内容数据信息
			RelationSubjectData relationSubjectData = new RelationSubjectData();
			relationSubjectData.setSubjectDataCode(UUIDHelper.getUUID());
			relationSubjectData.setTenantCode("");
			relationSubjectData.setOrgCode(qo.getOrgCode());
			relationSubjectData.setCreateTime(qo.getCreateTime());
			relationSubjectData.setModifyTime(qo.getModifyTime());
			relationSubjectData.setSubjectCode(qo.getMblogCode());
			relationSubjectData.setTeamQualityNum(qo.getTeamQualityNum());
			relationSubjectData.setGroupQualityNum(qo.getGroupQualityNum());
			relationSubjectData.setProvinceQualityNum(qo.getProvinceQualityNum());
			relationSubjectData.setCityQualityNum(qo.getCityQualityNum());
			relationSubjectData.setJoinNum(0);
			relationSubjectData.setReadNum(0);
			relationSubjectData.setLikeNum(qo.getPraiseNum());
			relationSubjectData.setCollectNum(qo.getCollectNum());
			relationSubjectData.setForwardNum(qo.getForwardNum());
			relationSubjectData.setCommentNum(qo.getCommentNum());
			relationSubjectData.setShareNum(0);
			//资源
			// 内容资源信息
			List<RelationSubjectResource> resList = new ArrayList<RelationSubjectResource>();
			for(MblogResource mr:qo.getResList()){
				if(mr.getResType()==MblogConstants.MBLOG_RESOURCE_IMG||
						mr.getResType()==MblogConstants.MBLOG_RESOURCE_AUDIO||
						mr.getResType()==MblogConstants.MBLOG_RESOURCE_VIDEO){
					RelationSubjectResource temp = new RelationSubjectResource();
					temp.setSubjectResCode(UUIDHelper.getUUID());
					temp.setTenantCode("");
					temp.setOrgCode(mr.getOrgCode());
					temp.setCreateTime(qo.getCreateTime());
					temp.setModifyTime(qo.getModifyTime());
					temp.setRemark(MblogConstants.MBLOG_BLANK);
					temp.setSubjectCode(qo.getMblogCode());
					temp.setResUrl(mr.getContent());
					Byte resourceType = null;
					if(mr.getResType()==MblogConstants.MBLOG_RESOURCE_IMG){
						resourceType = RelationConstants.RELATION_SUBJECT_RES_PICTURE;
					}else if(mr.getResType()==MblogConstants.MBLOG_RESOURCE_AUDIO){
						resourceType = RelationConstants.RELATION_SUBJECT_RES_AUDIO;
					}else if(mr.getResType()==MblogConstants.MBLOG_RESOURCE_VIDEO){
						resourceType = RelationConstants.RELATION_SUBJECT_RES_VIDEO;
					}
					temp.setResClass(resourceType);
					resList.add(temp);
				}
			}
			relationSubjectInfoMapper.insert(reInfo);
			relationSubjectDataMapper.insert(relationSubjectData);
			relationSubjectResourceMapper.batchInsert(resList);
		}catch(Exception e){
			return false;
		}
		// 资源
		return true;
	}
	
	*//**
	 * 导入博客推荐
	 *//*
	@Override
	public long importBlogRecommend(Integer pageNo, Integer pageSize) {
		List<KcBlogRecommend> lkr = kcBlogRecommendMapper.ListRecommendPage(pageNo*pageSize,pageSize);		// 1, 导入微博
		List<RelationRecommend> list = new ArrayList<RelationRecommend>();
		long selectedDataCount=0;
		long importedDataCount=0;
		long invalidDataCount=0;
		for (KcBlogRecommend blogRecommend : lkr) {
			
			
			RelationRecommend relationRecommend = new RelationRecommend();
			relationRecommend.setRecCode(UUIDHelper.getUUID());
			// 获取微博信息
			//1，判断内容信息表中是 已插入对应的 博客信息
			
			RelationSubjectInfo rsi = relationSubjectInfoMapper.getSubjectBaseInfo(blogRecommend.getBlogCode());
			if(rsi==null){
				//插入内容信息:1,获取微博信息
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("blogCode", blogRecommend.getBlogCode());
				BlogModel blogModel = blogInfoMapper.select(paramMap);
				if(blogModel==null){
					continue;
				}
				boolean subjectFlag = toSaveBlogSubject(blogModel);
			}
			
			try {
				selectedDataCount++;
				relationRecommend.setSubjectCode(blogRecommend.getBlogCode());
				relationRecommend.setSubjectClass((byte) 2);
				relationRecommend.setCreateTime(blogRecommend.getCreateTime().getTime());
				relationRecommend.setModifyTime(blogRecommend.getCreateTime().getTime());
			// 调用授权获取组织信息
			relationRecommend.setRecUserCode(blogRecommend.getUserCode());
			// 根据推荐类型 state 1 班组推荐 2 地市推荐 3 省推荐4集团
			if (blogRecommend.getState() == 1) {
				
				String teamOrgCode = teamInfoMapper.getTeamInfo(blogRecommend.getTeamCode()).getOrgCode();
				if("".equals(teamOrgCode)){
					invalidDataCount++;
					continue;
				}
				// 查询所属门户
				SysOrgInfo soi= sysOrgInfoMapper.getOrgInfoByCode(teamOrgCode);
				relationRecommend.setRecOrgCode(soi.getManOrgCode());
				relationRecommend.setRecToOrgCode(soi.getParentCode());
			} else {
				
				SysUserInfo	sysUserInfo = sysUserInfoMapper.getByCode(blogRecommend.getUserCode());
				SysOrgInfo soi= sysOrgInfoMapper.getOrgInfoByCode(sysUserInfo.getOrgCode());
				String orgCode = soi.getManOrgCode();
				String parentOrgCode = soi.getParentCode();
				if (blogRecommend.getState() == 2) {
					relationRecommend.setRecOrgCode(orgCode);
					
					relationRecommend.setRecToOrgCode(parentOrgCode);
				} else if (blogRecommend.getState() == 3) {
					relationRecommend.setRecOrgCode(orgCode);
					relationRecommend.setRecToOrgCode(parentOrgCode);
				}
			}

				relationRecommend.setTenantCode("#############");
				relationRecommend.setRecSource((byte) (blogRecommend.getState() != 1 ? 2 : 1));
				relationRecommend.setTeamCode(blogRecommend.getTeamCode());
				relationRecommend.setQualityCode("");
				relationRecommend.setRecQualityTime(0L);
				relationRecommend.setRecStatus((byte) 1);
				relationRecommend.setRemark("");
				list.add(relationRecommend);
				importedDataCount++;
			} catch (Exception e) {
				System.err.println("导入推荐时，对应微博记录获取不到，放弃该条记录");
				invalidDataCount++;
				continue;
			}
		}
		relationRecommendMaper.batchInsert(list);
		return 0;
	}
	*//**
	 * 
	 * @Title: toSaveBlogSubject  
	 * @Description: 保存
	 * @param blogModel
	 * @return    参数  
	 * @return: boolean    返回类型
	 *//*
	private boolean toSaveBlogSubject(BlogModel blogModel) {
		try{
			RelationSubjectInfo reInfo = new RelationSubjectInfo();
			reInfo.setSubjectCode(blogModel.getBlogCode());
			reInfo.setTenantCode("");
			reInfo.setOrgCode(blogModel.getOrgCode());
			reInfo.setCreateTime(blogModel.getCreateTime());
			reInfo.setModifyTime(blogModel.getModifyTime());
			reInfo.setSubjectTitle(blogModel.getBlogTitle());
			reInfo.setRemark("");
			reInfo.setSubjectClass(RelationConstants.RELATION_SUBJECT_BLOG);
			reInfo.setSubjectDepend(blogModel.getBlogDepend());
			reInfo.setTeamCode(blogModel.getTeamCode());
			reInfo.setUserCode(blogModel.getUserCode());
			reInfo.setSubjectPublishTime(blogModel.getBlogPublishTime());
			reInfo.setSubjectDigest(blogModel.getBlogDigest());
			reInfo.setSourceCode(blogModel.getSourceBlogCode());
			reInfo.setSourceUserCode(blogModel.getSourceUserCode());
			reInfo.setSourcePublishTime(MblogConstants.MBLOG_TIME_ZERO);
			reInfo.setSourceIsDelete(RelationConstants.RELATION_NO);
			reInfo.setSourceIsShield(RelationConstants.RELATION_NO);
			reInfo.setContentTips(blogModel.getBlogTags());
			reInfo.setSubjectIsDelete(RelationConstants.RELATION_NO);
			reInfo.setSubjectIsShield(RelationConstants.RELATION_NO);
			reInfo.setSubjectSource(RelationConstants.RELATION_SUBJECT_SOURCE_ORIGINAL);
			
			//liang
			// 内容数据信息
			RelationSubjectData relationSubjectData = new RelationSubjectData();
			relationSubjectData.setSubjectDataCode(UUIDHelper.getUUID());
			relationSubjectData.setTenantCode("");
			relationSubjectData.setOrgCode(blogModel.getOrgCode());
			relationSubjectData.setCreateTime(blogModel.getCreateTime());
			relationSubjectData.setModifyTime(blogModel.getModifyTime());
			relationSubjectData.setSubjectCode(blogModel.getBlogCode());
			relationSubjectData.setTeamQualityNum(blogModel.getTeamQualityNum());
			relationSubjectData.setGroupQualityNum(blogModel.getGroupQualityNum());
			relationSubjectData.setProvinceQualityNum(blogModel.getProvinceQualityNum());
			relationSubjectData.setCityQualityNum(blogModel.getCityQualityNum());
			relationSubjectData.setJoinNum(0);
			relationSubjectData.setReadNum(blogModel.getReadNum());
			relationSubjectData.setLikeNum(blogModel.getLikeNum());
			relationSubjectData.setCollectNum(blogModel.getCollectNum());
			relationSubjectData.setForwardNum(blogModel.getForwardNum());
			relationSubjectData.setCommentNum(blogModel.getCommentNum());
			relationSubjectData.setShareNum(blogModel.getShareNum());
			//资源
			// 内容资源信息
			List<RelationSubjectResource> resList = new ArrayList<RelationSubjectResource>();
			for(MblogResource mr:qo.getResList()){
				if(mr.getResType()==MblogConstants.MBLOG_RESOURCE_IMG||
						mr.getResType()==MblogConstants.MBLOG_RESOURCE_AUDIO||
						mr.getResType()==MblogConstants.MBLOG_RESOURCE_VIDEO){
					RelationSubjectResource temp = new RelationSubjectResource();
					temp.setSubjectResCode(UUIDHelper.getUUID());
					temp.setTenantCode("");
					temp.setOrgCode(mr.getOrgCode());
					temp.setCreateTime(qo.getCreateTime());
					temp.setModifyTime(qo.getModifyTime());
					temp.setRemark(MblogConstants.MBLOG_BLANK);
					temp.setSubjectCode(qo.getMblogCode());
					temp.setResUrl(mr.getContent());
					Byte resourceType = null;
					if(mr.getResType()==MblogConstants.MBLOG_RESOURCE_IMG){
						resourceType = RelationConstants.RELATION_SUBJECT_RES_PICTURE;
					}else if(mr.getResType()==MblogConstants.MBLOG_RESOURCE_AUDIO){
						resourceType = RelationConstants.RELATION_SUBJECT_RES_AUDIO;
					}else if(mr.getResType()==MblogConstants.MBLOG_RESOURCE_VIDEO){
						resourceType = RelationConstants.RELATION_SUBJECT_RES_VIDEO;
					}
					temp.setResClass(resourceType);
					resList.add(temp);
				}
			}
			relationSubjectInfoMapper.insert(reInfo);
			relationSubjectDataMapper.insert(relationSubjectData);
			relationSubjectResourceMapper.batchInsert(resList);
		}catch(Exception e){
			return false;
		}
		// 资源
		return true;
	}
	@Override
	public int recBlogCount() {
		return kcBlogRecommendMapper.countRec();
	}
	@Override
	public int recMblogCount() {
		return kcMblogRecommendMapper.countRec();
	}*/
	/**********************************new import**************************************************/
	@Transactional
	@Override
	public boolean batchImportRecommend() {
		relationRecommendMaper.insertBanZuBlog();
		relationRecommendMaper.insertshiBlog();
		relationRecommendMaper.insertshengBlog();
		
		relationRecommendMaper.insertBanZuMBlog();
		relationRecommendMaper.insertshiMBlog();
		relationRecommendMaper.insertshengMBlog();
		return true;
	}
	@Transactional
	@Override
	public void batchImportBlog() {
		relationSubjectInfoMapper.insertBlog();
		relationSubjectDataMapper.insertBlog();
	}
	@Transactional
	@Override
	public void batchImportMblog() {
		relationSubjectInfoMapper.insertMBlog();
		relationSubjectDataMapper.insertMBlog();
		relationSubjectResourceMapper.batchInsertResource();
	}
	
}

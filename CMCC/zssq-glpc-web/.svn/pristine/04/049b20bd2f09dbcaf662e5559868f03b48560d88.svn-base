package com.zssq.knowledgeLib.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.knowledgeLib.vo.CheckLibTitleForGLVO;
import com.zssq.knowledgeLib.vo.DeleteKnowledgeListForGLVO;
import com.zssq.knowledgeLib.vo.DeleteLibForGLVO;
import com.zssq.knowledgeLib.vo.EditKnowledgeLibForGLVO;
import com.zssq.knowledgeLib.vo.GetKnowledgeAppendForGLVO;
import com.zssq.knowledgeLib.vo.GetKnowledgeByTitleForGLVO;
import com.zssq.knowledgeLib.vo.GetKnowledgeInfoForGLVO;
import com.zssq.knowledgeLib.vo.GetKnowledgeLibListForGLVO;
import com.zssq.knowledgeLib.vo.GetKnowledgeListByLibForGLVO;
import com.zssq.knowledgeLib.vo.GetSearchKnowledgeListForGLVO;
import com.zssq.knowledgeLib.vo.MoveKnowledgeForGLVO;
import com.zssq.model.GetKnowledgeAppendForGLModel;
import com.zssq.model.GetKnowledgeByTitleForGLModel;
import com.zssq.model.GetKnowledgeInfoForGLModel;
import com.zssq.model.GetKnowledgeLibListForGLModel;
import com.zssq.model.GetKnowledgeListByLibForGLModel;
import com.zssq.model.RepositoryKnowledgeModelForSearch;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISysUserService;
import com.zssq.service.RelationThirdDynamicService;
import com.zssq.service.RepositoryInfoService;
import com.zssq.service.RepositoryKnowledgeAppendService;
import com.zssq.service.RepositoryKnowledgeContentService;
import com.zssq.service.RepositoryKnowledgeService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.StringTools;
import com.zssq.vo.CheckLibTitleForGLForSerVO;
import com.zssq.vo.DeleteKnowledgeListForGLForSerVO;
import com.zssq.vo.DeleteLibForGLForSerVO;
import com.zssq.vo.EditKnowledgeLibForForInGLVO;
import com.zssq.vo.GetKnowledgeAppendForGLVo;
import com.zssq.vo.GetKnowledgeByTitleForGLForSerVo;
import com.zssq.vo.GetKnowledgeInfoForGLForSerVo;
import com.zssq.vo.GetKnowledgeLibListForGLForSerVo;
import com.zssq.vo.GetKnowledgeListByLibForGLForSerVo;
import com.zssq.vo.MoveKnowledgeForGLSerVO;
import com.zssq.vo.RelationDynamicVO;
import com.zssq.vo.RepositorySearchVo;
/**
 * 
 * @ClassName: knowledgeLib  
 * @Description: 知识库  
 * @author guoyang  
 * @date 2017年4月28日  
 *
 */
@Controller  
@RequestMapping("/knowledgeLib")
public class KnowledgeLibController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private RepositoryInfoService repositoryInfoService;
	
	@Autowired
	private RepositoryKnowledgeAppendService repositoryKnowledgeAppendService;
	
	@Autowired
	private RepositoryKnowledgeContentService repositoryKnowledgeContentService;
	
	@Autowired
	private RepositoryKnowledgeService repositoryKnowledgeService;
	
	@Autowired
	private RelationThirdDynamicService relationThirdDynamicService;
	
	
	
	
	
	@Autowired
	private ISysUserService sysUserService;
	/**
	 * 
	 * @Title: getKnowledgeLibListForGL  
	 * @Description:查询公司下知识库列表
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getKnowledgeLibListForGL")
	@ResponseBody
	public ResultJSON getKnowledgeLibListForGL(@RequireValid GetKnowledgeLibListForGLVO param) throws BusinessException {
		
		//返回值
		ResultJSON resultJSON = null;
		
		try {
			log.info("knowledgeLibController.getKnowledgeLibListForGL:查询知识库开始");
			
			//获取参数
			String orgCode = param.getOrgCode();
			
			
			//封装参数
			GetKnowledgeLibListForGLForSerVo getKnowledgeLibListForGLForSerVo = new GetKnowledgeLibListForGLForSerVo();
			if(StringTools.isNotEmpty(orgCode)){
				getKnowledgeLibListForGLForSerVo.setOrgCode(orgCode);
			}
			List<GetKnowledgeLibListForGLModel> repositoryInfoList = repositoryInfoService.getKnowledgeLibListForGL(getKnowledgeLibListForGLForSerVo);
			JSONArray libList = new JSONArray();
			for (GetKnowledgeLibListForGLModel model : repositoryInfoList) {
				JSONObject jo = new JSONObject();
				jo.put("repositoryCode", model.getRepositoryCode());
				jo.put("repositoryTitle", model.getRepositoryTitle());
				jo.put("repositorySummary", model.getRepositorySummary());
				jo.put("createTime", model.getCreateTime());
				libList.add(jo);
			}
			JSONObject body = new JSONObject();
			body.put("libList", libList);
			resultJSON = new ResultJSON("COMMON_200", "操作成功");
			resultJSON.setBody(body);
			log.info("knowledgeLibController.getKnowledgeLibListForGL:查询知识库成功");
		} catch (Exception e) {
			log.error("knowledgeLibController.getKnowledgeLibListForGL", e);
			throw BusinessException.build("COMMON_400");
		}
		return resultJSON;
		
	}
	
	/**
	 * 
	 * @Title: getKnowledgeListByLibForGL  
	 * @Description: 查询知识库下知识列表
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getKnowledgeListByLibForGL")
	@ResponseBody
	public ResultJSON getKnowledgeListByLibForGL(@RequireValid GetKnowledgeListByLibForGLVO param) throws BusinessException {
		//返回值
		ResultJSON resultJSON = null;
		try {
			log.info("KnowledgeLibController.getKnowledgeListByLibForGL:查询知识库下知识列表,查询开始");
			//获取参数
			String repositoryCode = StringTools.formatToString(param.getRepositoryCode());//知识库编号
			String orgCode = StringTools.formatToString(param.getOrgCode());//知识库编号
			String keyWords = StringTools.formatToString(param.getKeyWords());//知识库编号
			//封装参数数据
			GetKnowledgeListByLibForGLForSerVo getKnowledgeListByLibForGLForSerVo = new GetKnowledgeListByLibForGLForSerVo();
			getKnowledgeListByLibForGLForSerVo.setRepositoryCode(repositoryCode);
			getKnowledgeListByLibForGLForSerVo.setOrgCode(orgCode);
			getKnowledgeListByLibForGLForSerVo.setKeyWords(keyWords);
			PageParam pp = new PageParam(param.getPageNo(), param.getPageSize());
			
			
			PageBean pageBean = repositoryKnowledgeService.getKnowledgeListByLibForGL(pp , getKnowledgeListByLibForGLForSerVo);
			List<GetKnowledgeListByLibForGLModel> list = pageBean.getRecordList();
			JSONArray kgList = new JSONArray();
			for (GetKnowledgeListByLibForGLModel model : list) {
				
				SysUserInfo tempUser = sysUserService.selectByCode(StringTools.formatToString(model.getUserCode()));
				String userName = "";
				String userPhotoUrl = "";
				String orgCodeNew = "";
				String orgName = "";
				if(tempUser != null){
					userName = tempUser.getUserName();
					userPhotoUrl = tempUser.getHeadPortrait();
					orgCodeNew = tempUser.getManageOrgInfo().getSysOrgCode();
					orgName = tempUser.getManageOrgInfo().getSysOrgFullname();
				}
				
				JSONObject jo = new JSONObject();
				jo.put("knowledgeCode", model.getKnowledgeCode());
				jo.put("knowledgeTitle", model.getKnowledgeTitle());
				jo.put("knowledgeDigest", model.getKnowledgeDigest());
				jo.put("contentCode", model.getContentCode());
				jo.put("createTime", model.getCreateTime());
				jo.put("appendNum", model.getAppendNum());
				jo.put("userCode", model.getUserCode());
				jo.put("userName", userName);
				jo.put("userPhotoUrl", userPhotoUrl);
				jo.put("orgCode", orgCodeNew);
				jo.put("orgName", orgName);
				kgList.add(jo);
			}
			JSONObject body = new JSONObject();
			body.put("totalCount", pageBean.getTotalCount());
			body.put("kgList", kgList);
			resultJSON = new ResultJSON("COMMON_200", "操作成功");
			resultJSON.setBody(body);
		} catch (Exception e) {
			log.error("KnowledgeLibController.getKnowledgeListByLibForGL", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return resultJSON;
	}
	
	
	/**
	 * 
	 * @Title: getKnowledgeInfoForGL  
	 * @Description: 获取知识正文
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getKnowledgeInfoForGL")
	@ResponseBody
	public ResultJSON getKnowledgeInfoForGL(@RequireValid GetKnowledgeInfoForGLVO param) throws BusinessException {
		//返回值
				ResultJSON resultJSON = null;
				
				try {
					log.info("KnowledgeLibController.getKnowledgeInfoForGL:查询知识正文开始");
					
					//获取参数
					String contentCode = param.getContentCode();	//内容编号
					//封装参数
					GetKnowledgeInfoForGLForSerVo getKnowledgeInfoForGLForSerVo = new GetKnowledgeInfoForGLForSerVo();
					if(StringTools.isNotEmpty(contentCode)){
						getKnowledgeInfoForGLForSerVo.setContentCode(contentCode);;
					}
					GetKnowledgeInfoForGLModel getKnowledgeInfoForGLModel = repositoryKnowledgeContentService.getKnowledgeInfoForGL(getKnowledgeInfoForGLForSerVo);
					
					if(getKnowledgeInfoForGLModel == null){
						log.error("KnowledgeLibController.getKnowledgeInfoForGL:查询知识正文失败");
						throw BusinessException.build("KNOWLEDGELIB_27001", "操作");
					}
					
					JSONObject body = new JSONObject();
					body.put("contentInfo", getKnowledgeInfoForGLModel.getContentInfo());
					resultJSON = new ResultJSON("COMMON_200", "操作成功");
					resultJSON.setBody(body);
					log.info("knowledgeLibController.getKnowledgeInfoForGL:查询知识正文成功");
				} catch (Exception e) {
					log.error("knowledgeLibController.getKnowledgeLibListForGL", e);
					throw BusinessException.build("COMMON_400");
				}
				return resultJSON;
	}
	
	
	/**
	 * 
	 * @Title: getKnowledgeAppendForGL  
	 * @Description: 获取知识追加内容列表
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getKnowledgeAppendForGL")
	@ResponseBody
	public ResultJSON getKnowledgeAppendForGL(@RequireValid GetKnowledgeAppendForGLVO param) throws BusinessException {
		//返回值
		ResultJSON resultJSON = null;
		try {
			log.info("knowledgeLibController.getKnowledgeAppendForGL:获取知识追加内容列表开始");
			//获取参数
			String knowledgeCode = StringTools.formatToString(param.getKnowledgeCode());
			Integer pageNo = param.getPageNo();
			Integer pageSize = param.getPageSize();
			Integer isSelfAppend = param.getIsSelfAppend();
			
			PageParam pageParam = new PageParam(pageNo, pageSize);
			GetKnowledgeAppendForGLVo getKnowledgeAppendForGLVo = new GetKnowledgeAppendForGLVo();
			getKnowledgeAppendForGLVo.setKnowledgeCode(knowledgeCode);
			if(isSelfAppend != null){
				getKnowledgeAppendForGLVo.setIsSelfAppend(isSelfAppend);
			}
			
			PageBean pageBean = repositoryKnowledgeAppendService.getKnowledgeAppendForGL(pageParam,getKnowledgeAppendForGLVo);
			
			List<GetKnowledgeAppendForGLModel> list = pageBean.getRecordList();
			JSONArray kgList = new JSONArray();
			for (GetKnowledgeAppendForGLModel model : list) {
				
				SysUserInfo tempUser = sysUserService.selectByCode(StringTools.formatToString(model.getUserCode()));
				String userName = "";
				String userPhotoUrl = "";
				String orgCode = "";
				String orgName = "";
				if(tempUser != null){
					userName = tempUser.getUserName();
					userPhotoUrl = tempUser.getHeadPortrait();
					orgCode = tempUser.getManageOrgInfo().getSysOrgCode();
					orgName = tempUser.getManageOrgInfo().getSysOrgFullname();
				}
				
				JSONObject jo = new JSONObject();
				jo.put("appendCode", model.getAppendCode());
				jo.put("createTime", model.getCreateTime());
				jo.put("appendContent", model.getAppendContent());
				jo.put("isSelfAppend", model.getIsSelfAppend());
				jo.put("userCode", model.getUserCode());
				jo.put("userName", userName);
				jo.put("userPhotoUrl", userPhotoUrl);
				jo.put("orgCode", orgCode);
				jo.put("orgName", orgName);
				kgList.add(jo);
			}
			JSONObject body = new JSONObject();
			body.put("totalCount", pageBean.getTotalCount());
			body.put("appendList", kgList);
			resultJSON = new ResultJSON("COMMON_200", "操作成功");
			resultJSON.setBody(body);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("knowledgeLibController.getKnowledgeAppendForGL", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return resultJSON;
	}
	
	
	
	/**
	 * 
	 * @Title: editKnowledgeLibForGL  
	 * @Description: 创建或修改知识库
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="editKnowledgeLibForGL")
	@ResponseBody
	public ResultJSON editKnowledgeLibForGL(@RequireValid EditKnowledgeLibForGLVO param) throws BusinessException {
		//返回值
		ResultJSON resultJSON = null;
		try {
			log.info("knowledgeLibController.editKnowledgeLibForGL:创建或修改知识库开始");
			//获取参数
			String orgCode = StringTools.formatToString(param.getOrgCode());			
			String repositoryCode = StringTools.formatToString(param.getRepositoryCode());	
			String repositoryTitle = StringTools.formatToString(param.getRepositoryTitle());		
			String repositorySummary = StringTools.formatToString(param.getRepositorySummary());		
			String userCode = StringTools.formatToString(param.getUserCode());		
			String token = StringTools.formatToString(param.getToken());		
			
			if(repositoryCode.isEmpty()){
				EditKnowledgeLibForForInGLVO editKnowledgeLibForForInGLVO = new EditKnowledgeLibForForInGLVO();
				editKnowledgeLibForForInGLVO.setOrgCode(orgCode);
				editKnowledgeLibForForInGLVO.setRepositoryCode(repositoryCode);
				editKnowledgeLibForForInGLVO.setRepositoryTitle(repositoryTitle);
				editKnowledgeLibForForInGLVO.setRepositorySummary(repositorySummary);
				editKnowledgeLibForForInGLVO.setUserCode(userCode);
				editKnowledgeLibForForInGLVO.setToken(token);
				Integer i = repositoryKnowledgeService.insertKnowledgeLib(editKnowledgeLibForForInGLVO);
				if(i != null && i == 1){
					resultJSON = new ResultJSON("COMMON_200", "创建或修改知识库成功");
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("isUnique", "0");
					resultJSON.setBody(jsonObject);
				}else{
					resultJSON = new ResultJSON("COMMON_400", "创建或修改知识库失败");
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("isUnique", "1");
					resultJSON.setBody(jsonObject);
				}
				
			}else if(!repositoryCode.isEmpty()){
				EditKnowledgeLibForForInGLVO editKnowledgeLibForForInGLVO = new EditKnowledgeLibForForInGLVO();
				editKnowledgeLibForForInGLVO.setOrgCode(orgCode);
				editKnowledgeLibForForInGLVO.setRepositoryCode(repositoryCode);
				editKnowledgeLibForForInGLVO.setRepositoryTitle(repositoryTitle);
				editKnowledgeLibForForInGLVO.setRepositorySummary(repositorySummary);
				editKnowledgeLibForForInGLVO.setUserCode(userCode);
				editKnowledgeLibForForInGLVO.setToken(token);
				Integer i = repositoryKnowledgeService.updateKnowledgeLib(editKnowledgeLibForForInGLVO);
				if(i != null && i == 1){
					resultJSON = new ResultJSON("COMMON_200", "创建或修改知识库成功");
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("isUnique", "0");
					resultJSON.setBody(jsonObject);
				}else{
					resultJSON = new ResultJSON("COMMON_400", "创建或修改知识库失败");
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("isUnique", "1");
					resultJSON.setBody(jsonObject);
				}
				
			}
			
		} catch (Exception e) {
			log.error("RelationHotController.updateHotStatus", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return resultJSON;
	}
	
	
	
	/**
	 * 
	 * @Title: checkLibTitleForGL  
	 * @Description: 知识库名称验重
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="checkLibTitleForGL")
	@ResponseBody
	public ResultJSON checkLibTitleForGL(@RequireValid CheckLibTitleForGLVO param) throws BusinessException {
		//返回值
		ResultJSON resultJSON = new ResultJSON("COMMON_200", "知识库名称验重成功");
		try {
			log.info("KnowledgeLibController.checkLibTitleForGL:知识库名称验重");
			//获取参数
			String repositoryTitle = StringTools.formatToString(param.getRepositoryTitle());
			String orgCode = StringTools.formatToString(param.getOrgCode());
			CheckLibTitleForGLForSerVO checkLibTitleForGLForSerVO = new CheckLibTitleForGLForSerVO();
			checkLibTitleForGLForSerVO.setRepositoryTitle(repositoryTitle);
			checkLibTitleForGLForSerVO.setOrgCode(orgCode);
			boolean checkFlag = repositoryInfoService.checkLibTitleForGL(checkLibTitleForGLForSerVO);
			JSONObject jsonObjectBody = new JSONObject();
			if (checkFlag == false) {
				jsonObjectBody.put("isExsit", "0");
				resultJSON.setBody(jsonObjectBody);
			}else{
				jsonObjectBody.put("isExsit", "1");
				resultJSON.setBody(jsonObjectBody);
			}
			log.info("KnowledgeLibController.checkLibTitleForGL:知识库名称验重成功");
		} catch (Exception e) {
			log.error("KnowledgeLibController.checkLibTitleForGL", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return resultJSON;
	}
	
	
	
	/**
	 * 
	 * @Title: deleteLibForGL  
	 * @Description: 知识库删除
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="deleteLibForGL")
	@ResponseBody
	public ResultJSON deleteLibForGL(@RequireValid DeleteLibForGLVO param) throws BusinessException {
		//返回值
		ResultJSON resultJSON = null;
		try {
			log.info("KnowledgeLibController.deleteLibForGL:知识库删除");
			//获取参数
			String repositoryCode = StringTools.formatToString(param.getRepositoryCode());
			String orgCode = StringTools.formatToString(param.getOrgCode());
			DeleteLibForGLForSerVO deleteLibForGLForSerVO = new DeleteLibForGLForSerVO();
			deleteLibForGLForSerVO.setRepositoryCode(repositoryCode);
			deleteLibForGLForSerVO.setOrgCode(orgCode);
			Integer count = repositoryInfoService.deleteLibForGL(deleteLibForGLForSerVO);
			
			if (count == 0) {
				log.error("KnowledgeLibController.deleteLibForGL:知识库删除失败");
				throw BusinessException.build("KNOWLEDGELIB_27001", "知识库删除失败");
			}else{
				resultJSON = new ResultJSON("COMMON_200", "知识库删除成功");
				resultJSON.setBody(new JSONObject());
				log.info("KnowledgeLibController.deleteKnowledgeListForGL:知识库删除成功");
			}
			
			
			//删除知识库的同时，删除动态
			Date date = new Date();
			long now = date.getTime();
			RelationDynamicVO relationDynamicVO = new RelationDynamicVO();
			relationDynamicVO.setRepositoryCode(repositoryCode);
			relationDynamicVO.setModifyTime(now);
			Boolean isSucc = relationThirdDynamicService.deleteRepoDynamic(relationDynamicVO);
			if (isSucc == false) {
				log.error("KnowledgeLibController.deleteLibForGL:");
				throw BusinessException.build("KNOWLEDGELIB_27001", "知识库删除失败,删除动态失败");
			}
			
			
			
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("KnowledgeLibController.deleteKnowledgeListForGL", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return resultJSON;
	}
	
	
	
	
	
	
	
	/**
	 * 
	 * @Title: deleteKnowledgeForGL  
	 * @Description: 知识删除
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="deleteKnowledgeListForGL")
	@ResponseBody
	public ResultJSON deleteKnowledgeListForGL(@RequireValid DeleteKnowledgeListForGLVO param) throws BusinessException {
		//返回值
		ResultJSON resultJSON = null;
		try {
			log.info("KnowledgeLibController.deleteKnowledgeListForGL:知识删除");
			//获取参数
			String knowledgeCodes = StringTools.formatToString(param.getKnowledgeCodes());			
			String orgCode = StringTools.formatToString(param.getOrgCode());
			DeleteKnowledgeListForGLForSerVO deleteKnowledgeListForGLForSerVO = new DeleteKnowledgeListForGLForSerVO();
			deleteKnowledgeListForGLForSerVO.setKnowledgeCodes(knowledgeCodes);
			deleteKnowledgeListForGLForSerVO.setOrgCode(orgCode);
			boolean deleteFlag = repositoryKnowledgeService.deleteKnowledgeListForGL(deleteKnowledgeListForGLForSerVO);
			if (deleteFlag == false) {
				log.error("KnowledgeLibController.deleteKnowledgeListForGL:知识删除失败");
				throw BusinessException.build("KNOWLEDGELIB_27001", "知识删除失败");
			}else{
				resultJSON = new ResultJSON("COMMON_200", "知识删除成功");
				resultJSON.setBody(new JSONObject());
				log.info("KnowledgeLibController.deleteKnowledgeListForGL:知识删除成功");
			}
			
			//删除知识的同时删除动态
			ArrayList<String> arrayList = new ArrayList<String>();
			String[] split = knowledgeCodes.split(",");
			for(int i = 1 ; i <= split.length ;i++){
				arrayList.add(split[i-1]);
			}
			Date date = new Date();
			long now = date.getTime();
			RelationDynamicVO relationDynamicVO = new RelationDynamicVO();
			relationDynamicVO.setSubjectCodeList(arrayList);
			relationDynamicVO.setModifyTime(now);
			Boolean isSucc =  relationThirdDynamicService.deleteBatchDynamic(relationDynamicVO);
			if (isSucc == false) {
				log.error("KnowledgeLibController.deleteKnowledgeListForGL:知识删除失败，删除动态失败");
				throw BusinessException.build("KNOWLEDGELIB_27001", "知识删除失败，删除动态失败");
			}
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("KnowledgeLibController.deleteKnowledgeListForGL", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return resultJSON;
	}
	
	
	
	
	/**
	 * 
	 * @Title: moveKnowledgeForGL  
	 * @Description: 知识移动到
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="moveKnowledgeForGL")
	@ResponseBody
	public ResultJSON moveKnowledgeForGL(@RequireValid MoveKnowledgeForGLVO param) throws BusinessException {
		//返回值
		ResultJSON resultJSON = null;
		try {
			log.info("moveKnowledgeForGL.moveKnowledgeForGL:知识移动到");
			//获取参数
			String knowledgeCodes = StringTools.formatToString(param.getKnowledgeCodes());			
			String repositoryCode = StringTools.formatToString(param.getRepositoryCode());
			String orgCode = StringTools.formatToString(param.getOrgCode());
			//封装参数数据
			MoveKnowledgeForGLSerVO moveKnowledgeForGLSerVO = new MoveKnowledgeForGLSerVO();
			moveKnowledgeForGLSerVO.setKnowledgeCodes(knowledgeCodes);
			moveKnowledgeForGLSerVO.setRepositoryCode(repositoryCode);
			moveKnowledgeForGLSerVO.setOrgCode(orgCode);
		/*	List<String> hadNameList = repositoryKnowledgeService.hadName(moveKnowledgeForGLSerVO);
			
			if(hadNameList.size() == 0){*/
				boolean updateFlag = repositoryKnowledgeService.moveKnowledgeForGL(moveKnowledgeForGLSerVO);
				if (!updateFlag) {
					log.error("moveKnowledgeForGL.moveKnowledgeForGL:知识移动到,失败");
					throw BusinessException.build("KNOWLEDGELIB_27001", "知识移动到,失败");
				}else{
					resultJSON = new ResultJSON("COMMON_200", "知识移动到");
					resultJSON.setBody(new JSONObject());
					log.info("moveKnowledgeForGL.moveKnowledgeForGL:知识移动到,成功");
				}
			/*}else{
				StringBuilder stringBuilder = new StringBuilder();
				for (String string : hadNameList) {
					stringBuilder.append(string);
					stringBuilder.append(",");
				}
				String s = stringBuilder.toString();
				s = s.substring(0,s.length() - 1);
				resultJSON = new ResultJSON("COMMON_400", "创建或修改知识库失败");
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("hadTital", s);
				resultJSON.setBody(jsonObject);
			}*/
			
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("moveKnowledgeForGL.moveKnowledgeForGL:知识移动到", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return resultJSON;
	}
	
	
	
	
	/**
	 * 
	 * @Title: getSearchKnowledgeListForGL  
	 * @Description: 搜索知识
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getSearchKnowledgeListForGL")
	@ResponseBody
	public ResultJSON getSearchKnowledgeListForGL(@RequireValid GetSearchKnowledgeListForGLVO param) throws BusinessException {
		//返回值
		ResultJSON resultJSON = null;
		try {
			log.info("KnowledgeLibController.getSearchKnowledgeListForGL:搜索知识");
			//获取参数
			String keyWords = StringTools.formatToString(param.getKeyWords());			
			Integer pageNo = param.getPageNo();
			Integer pageSize = param.getPageSize();	
			String orgCode = param.getOrgCode();
			
			RepositorySearchVo repositorySearchVo = new RepositorySearchVo();
			repositorySearchVo.setKeywords(keyWords);
			repositorySearchVo.setOrgCode(orgCode);
			
			PageParam pageParam = new PageParam(param.getPageNo(), param.getPageSize());
			pageParam.setPageNo(pageNo);
			pageParam.setPageSize(pageSize);
			
			PageBean pageBean = repositoryKnowledgeService.getShowHotListByGL(pageParam,repositorySearchVo);
			
			List<RepositoryKnowledgeModelForSearch> showList = pageBean.getRecordList();
			
			JSONArray kgList = new JSONArray();
			for (RepositoryKnowledgeModelForSearch model : showList) {
				
				SysUserInfo tempUser = sysUserService.selectByCode(StringTools.formatToString(model.getUserCode()));
				String userName = "";
				String userPhotoUrl = "";
				String orgCodeNew = "";
				String orgName = "";
				if(tempUser != null){
					userName = tempUser.getUserName();
					userPhotoUrl = tempUser.getHeadPortrait();
					orgCodeNew = tempUser.getManageOrgInfo().getSysOrgCode();
					orgName = tempUser.getManageOrgInfo().getSysOrgFullname();
				}
				
				JSONObject jo = new JSONObject();
				jo.put("knowledgeCode", model.getKnowledgeCode());
				jo.put("knowledgeTitle", model.getKnowledgeTitle());
				jo.put("knowledgeDigest", model.getKnowledgeDigest());
				jo.put("contentCode", model.getContentCode());
				jo.put("createTime", model.getCreateTime());
				jo.put("appendNum", model.getAppendNum());
				jo.put("userCode", model.getUserCode());
				jo.put("userName", userName);
				jo.put("userPhotoUrl", userPhotoUrl);
				jo.put("orgCode", orgCodeNew);
				jo.put("orgName", orgName);
				kgList.add(jo);
			}
			JSONObject body = new JSONObject();
			body.put("totalCount", pageBean.getTotalCount());
			body.put("kgList", kgList);
			resultJSON = new ResultJSON("COMMON_200", "操作成功");
			resultJSON.setBody(body);
			log.info("KnowledgeLibController.getSearchKnowledgeListForGL:搜索知识成功");
		} catch (Exception e) {
			log.error("KnowledgeLibController.getSearchKnowledgeListForGL", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return resultJSON;
	}
	
	
	
	
	/**
	 * 
	 * @Title: getKnowledgeByTitleForGL  
	 * @Description: 根据知识code查询内容
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getKnowledgeByTitleForGL")
	@ResponseBody
	public ResultJSON getKnowledgeByTitleForGL(@RequireValid GetKnowledgeByTitleForGLVO param) throws BusinessException {
		//返回值
		ResultJSON resultJSON = null;
		try {
			log.info("KnowledgeLibController.getKnowledgeByTitleForGL:根据知识标题查询内容开始");
			//获取参数
			String knowledgeCode = StringTools.formatToString(param.getKnowledgeCode());			
			String orgCode = param.getOrgCode();
			
			GetKnowledgeByTitleForGLForSerVo getKnowledgeByTitleForGLForSerVo = new GetKnowledgeByTitleForGLForSerVo();
			getKnowledgeByTitleForGLForSerVo.setKnowledgeCode(knowledgeCode);
			getKnowledgeByTitleForGLForSerVo.setOrgCode(orgCode);
			
			GetKnowledgeByTitleForGLModel getKnowledgeByTitleForGLModel = repositoryKnowledgeService.getKnowledgeByTitleForGL(getKnowledgeByTitleForGLForSerVo);
			
			
			SysUserInfo tempUser = sysUserService.selectByCode(StringTools.formatToString(getKnowledgeByTitleForGLModel.getUserCode()));
			String userName = "";
			String userPhotoUrl = "";
			String orgCodeNew = "";
			String orgName = "";
			if(tempUser != null){
				userName = tempUser.getUserName();
				userPhotoUrl = tempUser.getHeadPortrait();
				orgCodeNew = tempUser.getManageOrgInfo().getSysOrgCode();
				orgName = tempUser.getManageOrgInfo().getSysOrgFullname();
			}
			
			JSONObject jo = new JSONObject();
			jo.put("knowledgeCode", getKnowledgeByTitleForGLModel.getKnowledgeCode());
			jo.put("knowledgeTitle", getKnowledgeByTitleForGLModel.getKnowledgeTitle());
			jo.put("knowledgeDigest", getKnowledgeByTitleForGLModel.getKnowledgeDigest());
			jo.put("contentCode", getKnowledgeByTitleForGLModel.getContentCode());
			jo.put("createTime", getKnowledgeByTitleForGLModel.getCreateTime());
			jo.put("appendNum", getKnowledgeByTitleForGLModel.getAppendNum());
			jo.put("userCode", getKnowledgeByTitleForGLModel.getUserCode());
			jo.put("userName", userName);
			jo.put("userPhotoUrl", userPhotoUrl);
			jo.put("orgCode", orgCodeNew);
			jo.put("orgName", orgName);
			jo.put("contentInfo",getKnowledgeByTitleForGLModel.getContentInfo());
			
			resultJSON = new ResultJSON("COMMON_200", "操作成功");
			resultJSON.setBody(jo);
			log.info("KnowledgeLibController.getKnowledgeByTitleForGL:根据知识标题查询内容成功");
		} catch (Exception e) {
			log.error("KnowledgeLibController.getKnowledgeByTitleForGL", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return resultJSON;
	}
}

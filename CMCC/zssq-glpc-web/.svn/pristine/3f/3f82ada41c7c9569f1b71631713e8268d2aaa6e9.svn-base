package com.zssq.filing.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.FilingConstants;
import com.zssq.dao.pojo.SensitiveWordInfo;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.filing.tools.ExcelReader;
import com.zssq.filing.vo.SensitiveWordAddVo;
import com.zssq.filing.vo.SensitiveWordDelVo;
import com.zssq.filing.vo.SensitiveWordExcelVo;
import com.zssq.filing.vo.SensitiveWordSearchVo;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISensitiveWordService;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.PropertiesUtil;
import com.zssq.utils.StringTools;
import com.zssq.utils.excel.poi.util.PoiUtil;

/**
 * 
 * @ClassName: SensitiveWordController  
 * @Description: 敏感词相关的服务  
 * @author CaiZhaohui  
 * @date 2017年3月31日  
 *
 */
@Controller
@RequestMapping("/sensitiveWord")
public class SensitiveWordController {
	
	@Autowired
	ISensitiveWordService sensitiveWordService;
	
	@Autowired
	ISysUserService sysUserService;
	
	@Autowired
	ISysOrgService sysOrgService;

	private Logger logger=LoggerFactory.getLogger(SensitiveWordController.class);
	
	/**
	 * 
	 * @Title: addSensitiveWord  
	 * @Description: 添加敏感词
	 * @param @param param
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/add")
	@ResponseBody
	public ResultJSON addSensitiveWord(@RequireValid SensitiveWordAddVo param) throws BusinessException{
		
		String userCode=param.getUserCode();
		String sensitiveType=param.getSensitiveType();
		String word=param.getWord();
		
		SensitiveWordInfo sensitiveWordInfo=new SensitiveWordInfo();
		Message message=null;
		ResultJSON rj=new ResultJSON();
		
		try {
			sensitiveWordInfo.setSensitiveCode(UUIDHelper.getUUID()); //敏感词编码
			sensitiveWordInfo.setSensitiveValue(word); //敏感词内容
			sensitiveWordInfo.setCreateTime(System.currentTimeMillis()); //创建时间
			
			SysUserInfo userInfo=sysUserService.selectByCode(userCode); 
			String orgCode=null;
			if(userInfo!=null){
				SysOrgInfo manageOrgInfo = userInfo.getManageOrgInfo();
				if(manageOrgInfo!=null){
					orgCode="0".equals(sensitiveType)?sensitiveType:manageOrgInfo.getSysOrgCode(); //sensitiveType：0-基础库，1-其他库
				}
			}
			sensitiveWordInfo.setOrgCode(orgCode);
			
			Byte isEnable=1;
			sensitiveWordInfo.setIsEnable(isEnable);
			
			boolean isSuccess=sensitiveWordService.insertSelective(sensitiveWordInfo);
			
			if(isSuccess==true){
				message = PropertiesUtil.getMessage(FilingConstants.RETURNCODE_FILING_200);
			}else{
				message = PropertiesUtil.getMessage(FilingConstants.RETURNCODE_FILING_11002);
			}
			
			rj.setReturnCode(message.getCode());
			rj.setReturnTip(String.format(message.getTip(), "添加敏感词"));
			rj.setBody(new JSONObject());
			
			return rj;
		} catch (Exception e) {
			logger.error("将敏感词添加到数据库表 insert into sys_sensitive_word 异常");
			message = PropertiesUtil.getMessage(FilingConstants.RETURNCODE_FILING_11002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "添加敏感词"));
		}
		
	}
	
	/**
	 * 
	 * @Title: listSensitiveWord  
	 * @Description: 获取敏感词列表
	 * @param @param param
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ResultJSON listSensitiveWord(@RequireValid SensitiveWordSearchVo param) throws BusinessException{
		
		ResultJSON rj=new ResultJSON();
		Message message=null;
		
		try {
			PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
			SensitiveWordInfo record=new SensitiveWordInfo();
			
			String orgCode=null;
			SysUserInfo userInfo=sysUserService.selectByCode(param.getUserCode()); 
			if(userInfo!=null){
				SysOrgInfo manageOrgInfo = userInfo.getManageOrgInfo();
				if(manageOrgInfo!=null){
					orgCode="0".equals(param.getSensitiveType())?param.getSensitiveType():manageOrgInfo.getSysOrgCode(); //sensitiveType：0-基础库，1-其他库
				}
			}
			
			record.setOrgCode(orgCode);
			record.setSensitiveValue(param.getWord());
			
			PageBean pageBean=sensitiveWordService.selectPage(pageParam, record);
			List<SensitiveWordInfo> wordList=pageBean.getRecordList();
			
			JSONArray jsonArray = new JSONArray();
			
			for(SensitiveWordInfo word:wordList){
				JSONObject wordInfoJson = new JSONObject();
				wordInfoJson.put("sensitiveCode", StringTools.formatToString(word.getSensitiveCode()));
				wordInfoJson.put("sensitiveValue", StringTools.formatToString(word.getSensitiveValue()));
				wordInfoJson.put("sensitiveSpellValue", StringTools.formatToString(word.getSensitiveSpellValue()));
				wordInfoJson.put("sensitiveAcronym", StringTools.formatToString(word.getSensitiveAcronym()));
				wordInfoJson.put("createTime", StringTools.formatToString(word.getCreateTime()));
				wordInfoJson.put("isEnable", StringTools.formatToString(word.getIsEnable()));
				wordInfoJson.put("orgCode", StringTools.formatToString(word.getOrgCode()));
				jsonArray.add(wordInfoJson);
			}
			
			JSONObject body = new JSONObject();
			body.put("totalCount", pageBean.getTotalCount());
			body.put("dataList", jsonArray);
			
			message = PropertiesUtil.getMessage(FilingConstants.RETURNCODE_FILING_200);
			
			rj.setReturnCode(message.getCode());
			rj.setReturnTip(String.format(message.getTip(), "获取敏感词列表"));
			rj.setBody(body);
			
			return rj;
		} catch (Exception e) {
			logger.error("分页查询数据库表 selectPage from sys_sensitive_word 异常");
			message = PropertiesUtil.getMessage(FilingConstants.RETURNCODE_FILING_11002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取敏感词列表"));
		}
		
	}
	
	/**
	 * 
	 * @Title: delSensitiveWord  
	 * @Description: 删除敏感词
	 * @param @param param
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/del")
	@ResponseBody
	public ResultJSON delSensitiveWord(@RequireValid SensitiveWordDelVo param) throws BusinessException{
		
		String sensitiveCode=param.getSensitiveCode(); //敏感词编码
		
		ResultJSON rj=new ResultJSON();
		Message message=null;
		
		try {
			message = PropertiesUtil.getMessage(FilingConstants.RETURNCODE_FILING_200);
			
			boolean isSuccess=sensitiveWordService.deleteBySensitiveCode(sensitiveCode);
			
			if(isSuccess==true){
				message = PropertiesUtil.getMessage(FilingConstants.RETURNCODE_FILING_200);
			}else{
				message = PropertiesUtil.getMessage(FilingConstants.RETURNCODE_FILING_11002);
			}
			
			rj.setReturnCode(message.getCode());
			rj.setReturnTip(String.format(message.getTip(), "删除敏感词"));
			rj.setBody(new JSONObject());
			
			return rj;
		} catch (Exception e) {
			logger.error("删除数据库表中记录 delete from sys_sensitive_word 异常");
			message = PropertiesUtil.getMessage(FilingConstants.RETURNCODE_FILING_11002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "删除敏感词"));
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/import")
	public ResultJSON importSensitiveWord(HttpServletRequest request) throws BusinessException{
		
		ResultJSON rj=new ResultJSON();
		Message message=null;
		
		try{
			String isBase = request.getParameter("isBase");
			String orgCode = "";
			if(FilingConstants.BOOLEAN_0.equals(isBase)){
				String userCode = request.getParameter("userCode");
				SysUserInfo userInfo = sysUserService.selectByCode(userCode);
				orgCode = userInfo.getManageOrgInfo().getManOrgCode();
			}else if(FilingConstants.BOOLEAN_1.equals(isBase)){
				orgCode = FilingConstants.BASE_SENSITIVE;
			}else{
				message = PropertiesUtil.getMessage(FilingConstants.RETURNCODE_FILING_11004);
    			throw new BusinessException(message.getCode(), String.format(message.getTip(), "导入敏感词","参数isBase错误"));
			}
			//创建一个通用的多部分解析器  
	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	        if(multipartResolver.isMultipart(request)){  
	        	//转换成多部分request
	        	MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	            //取得request中的所有文件名  
	            Iterator<String> iter = multiRequest.getFileNames();  
	            while(iter.hasNext()){  
	                //取得上传文件  
	                MultipartFile file = multiRequest.getFile(iter.next());  
	                if(null==file){
	                	message = PropertiesUtil.getMessage(FilingConstants.RETURNCODE_FILING_11004);
	        			throw new BusinessException(message.getCode(), String.format(message.getTip(), "导入敏感词","上传文件获取失败"));
	                }
	                if(file != null){
	        			//取得当前上传文件的文件名称  
	                	String myFileName = file.getOriginalFilename();  
	                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	                    if(!"".equals(myFileName.trim())){
	                    	String tempFileName = file.getOriginalFilename();  
	                        String fileExtName =  ExcelReader.getFileExt(tempFileName);  
	                        ExcelReader er = new ExcelReader();
		        			Workbook workbook = er.getWorkbook(file.getInputStream(),fileExtName.toLowerCase());
		        			//业务 默认 只有一个sheet
		        			Sheet sheet = workbook.getSheetAt(0);
		                    //获取Sheet表中所包含的总行数   
		                    int rsRows = sheet.getPhysicalNumberOfRows();  
		                    if (rsRows <= 1){
		                    	message = PropertiesUtil.getMessage(FilingConstants.RETURNCODE_FILING_11004);
			        			throw new BusinessException(message.getCode(), String.format(message.getTip(), "导入敏感词","第一页数据缺失"));
		                    }
		                    //从第2行开始读  第一行为表头
		                    List<SensitiveWordInfo> sensitiveWordInfos = new ArrayList<SensitiveWordInfo>();
		                    for (int i = 1; i < rsRows; i++){
		                    	SensitiveWordInfo sensitiveWordInfo = new SensitiveWordInfo();
		                    	sensitiveWordInfo.setSensitiveCode(UUIDHelper.getUUID());
		                    	sensitiveWordInfo.setSensitiveValue(sheet.getRow(i).getCell(0).getStringCellValue());
		                    	sensitiveWordInfo.setCreateTime(DateUtils.getFormatDateLong());
		                    	sensitiveWordInfo.setIsEnable(FilingConstants.BOOLEAN_TRUE);
		                    	sensitiveWordInfo.setOrgCode(orgCode);
		                    	sensitiveWordInfos.add(sensitiveWordInfo);
		                    	if(i%50 == 0){
		                    		sensitiveWordService.batchInsert(sensitiveWordInfos);
		                    		sensitiveWordInfos.clear();
		                    	}else if(i == rsRows -1){
		                    		sensitiveWordService.batchInsert(sensitiveWordInfos);
		                    	}
		                    }
		                    // 创建出参对象
							JSONObject body = new JSONObject();
							message = PropertiesUtil.getMessage(FilingConstants.RETURNCODE_FILING_200);
							rj.setReturnCode(message.getCode());
							rj.setReturnTip(String.format(message.getTip(), "导入敏感词"));
							rj.setBody(body);
	                    }  
	                }  
	            }  
	        }
		} catch (Exception e) {
			logger.error("导入敏感词 importSensitiveWord  异常");
			message = PropertiesUtil.getMessage(FilingConstants.RETURNCODE_FILING_11002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "导入敏感词"));
		}
		return rj;
	}
	
	
	@ResponseBody
	@RequestMapping("/exportTemplate")
	public ResultJSON exportTemplate(HttpServletRequest request,HttpServletResponse response) throws BusinessException{
		
		ResultJSON rj=new ResultJSON();
		Message message=null;
		try {
			//调用poi组件导出一张只有表头的空记录表
			PoiUtil.exportExcel(response, request,"敏感词导入模板", SensitiveWordExcelVo.class, null, false);
			
			message = PropertiesUtil.getMessage(FilingConstants.RETURNCODE_FILING_200);
			
			rj.setReturnCode(message.getCode());
			rj.setReturnTip(String.format(message.getTip(), "导出敏感词记录模板"));
			rj.setBody(new JSONObject());
		} catch (Exception e) {
			logger.error(" exportSensitiveWordTemplete 异常");
			message = PropertiesUtil.getMessage(FilingConstants.RETURNCODE_FILING_11002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "导出敏感词记录模板"));
		}
		
		return rj;
	}
	
	
}

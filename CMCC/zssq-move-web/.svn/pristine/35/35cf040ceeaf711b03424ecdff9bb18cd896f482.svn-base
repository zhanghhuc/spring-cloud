package com.zssq.disk.controller;

import com.alibaba.fastjson.JSONObject;
import com.zssq.disk.service.DiskService;
import com.zssq.disk.vo.DiskFileDB2;
import com.zssq.disk.vo.DiskFileMySql;
import com.zssq.pojo.ResultJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: DiskController
 * @Description: 网盘数据迁移Controller
 * @author SharlaCheung
 * @date 2017年5月23日
 *
 */
@Controller
@RequestMapping("diskController")
public class DiskController {

	@Autowired
	private DiskService diskService;
	
	/**
	 * 
	 * @Title: transferNewsData
	 * @Description: 迁移网盘数据
	 * @param pageNo
	 * @param pageSize    参数  
	 * @return: void    返回类型
	 */
	@RequestMapping(value = "/transferNewsData")
	@ResponseBody
	public ResultJSON transferNewsData(int pageNo, int pageSize) {
		// 返回值
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		
		try {
			// 参数校验
			if (pageNo <= 0) {
				pageNo = 0;
			}
			if (pageSize <= 0) {
				pageSize = 100;
			}
			
			// 查询DB2库中订阅数据总量
			int DB2SubCount = diskService.getDB2DiskFileCount();
			if (DB2SubCount <= 0) {
				body.put("message", "查询DB2库中网盘数据总量时出错，查询出的个数为：" + DB2SubCount);
				throw new Exception();
			}
			
			// 总页数
			int totalPage = (DB2SubCount+pageSize-1)/pageSize;
			
			// 循环处理数据
			boolean arrangeFlag = true;
			for (int i = pageNo; i < totalPage; i++) {
				arrangeFlag = arrangeSubData(pageNo, pageSize);
				if (!arrangeFlag) {
					body.put("message", "处理数据时失败");
					throw new Exception();
				}
				pageNo++;
			}
		} catch (Exception e) {
			result = new ResultJSON("COMMON_400");
			body.put("pageNo", pageNo);
			result.setBody(body);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: arrangeSubData  
	 * @Description: 整理订阅数据
	 * @param pageNo
	 * @param pageSize    参数  
	 * @return: boolean    返回类型
	 */
	private boolean arrangeSubData(int pageNo, int pageSize) {
		// 返回值
		boolean result = true;
		
		try {
			// 获取DB2库中的订阅数据
			List<DiskFileDB2> DB2NewsList = diskService.getDB2SubList(pageNo, pageSize);
			
			// 校验返回值
			if (DB2NewsList == null || DB2NewsList.isEmpty()) {
				return false;
			}
			
			// 将DB2中的数据整理为MySQL中的数据
			List<DiskFileMySql> mySQLSubList = new ArrayList<DiskFileMySql>();
			for (DiskFileDB2 diskFileDB2 : DB2NewsList) {
				DiskFileMySql diskFileMySql = new DiskFileMySql();
				diskFileMySql.setFileCode(diskFileDB2.getFileCode());
				String db2FileName = diskFileDB2.getFileName() ;
				String fileSuffix = "";
				String fileName = "";
				if(null != db2FileName && !"".equals(db2FileName)){
					int index = db2FileName.lastIndexOf(".") ;
					if(index > 0){
						fileName = db2FileName.substring(0,index) ;
						fileSuffix = db2FileName.substring(index) ;
					}
				}
				diskFileMySql.setFileName(fileName);
				diskFileMySql.setFileSuffix(fileSuffix);
				diskFileMySql.setFileSize(diskFileDB2.getFileSize());
				diskFileMySql.setFileUrl(diskFileDB2.getFileUrl());
				Integer fileType = diskFileDB2.getFileType() ;
				diskFileMySql.setFileType(fileType==1?2:1);
				diskFileMySql.setParentCode(diskFileDB2.getParentCode());
				diskFileMySql.setUserCode(diskFileDB2.getUserCode());
				diskFileMySql.setEditUserCode(diskFileDB2.getUserCode());
				diskFileMySql.setCreatTime(diskFileDB2.getCreatTime().getTime());
				diskFileMySql.setEditTime(diskFileDB2.getEditTime().getTime());
				// TODO: 2017-05-22
//				diskFileMySql.setOrgCode();
				diskFileMySql.setIsDelete(diskFileDB2.getIsDelete());
				diskFileMySql.setReportType(diskFileDB2.getIsDelete());
				diskFileMySql.setTenantCode("");

				mySQLSubList.add(diskFileMySql);
			}
			
			// 插入订阅数据到MySQL库中
			boolean insertFlag = diskService.insertMySQLSubList(mySQLSubList);
			if (!insertFlag) {
				result = false;
			}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}

}

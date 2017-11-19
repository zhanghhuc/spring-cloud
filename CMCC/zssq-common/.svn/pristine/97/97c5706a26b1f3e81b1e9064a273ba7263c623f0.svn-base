package com.zssq.utils;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class AppUtils{
	/**
	 * 
	 * @Title: writePage  
	 * @Description: TODO
	 * @param res
	 * @param codeType
	 * @param obj
	 * @param codeName    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	public static void writePage(HttpServletResponse res, String codeType, Object obj, String codeName) {
		try {
		    res.setCharacterEncoding(codeName);
			res.setContentType(codeType);
			if (codeType.indexOf("text") > -1) {
				res.getWriter().print(obj);
			}
		} catch (Exception e) {
			System.out.println(">>>>>>>>>>>writePage ERROR! >>>>>>>>>>>");
			System.out.println(e.getLocalizedMessage());
			System.out.println("codeType:" + codeType);
			System.out.println("obj:" + obj);
		}
	}
	
	/**
	 * 
	 * @Title: createJSon  
	 * @Description: 创建JSON数据
	 * @param requestStatus
	 * @param returnCode
	 * @param returnTip
	 * @param item
	 * @return    参数  
	 * @return JSONObject    返回类型  
	 * @throws
	 */
	public static JSONObject createJSon(String requestStatus,String returnCode,String returnTip,Object item){
		JSONObject result = new JSONObject();
		result.put("requestStatus", requestStatus);
		result.put("returnCode", returnCode);
		result.put("returnTip", returnTip);
		result.put("body", item);
		return result;
	}
}

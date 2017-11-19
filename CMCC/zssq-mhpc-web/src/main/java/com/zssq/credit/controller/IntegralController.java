package com.zssq.credit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zssq.service.IIntegralService;

/**
 * 
 * @ClassName: IntegralController  
 * @Description: 模拟kafka生产者  
 * @author power  
 * @date 2017年5月5日  
 *
 */
@Controller
@RequestMapping("/Integral")
public class IntegralController {

	/** 模拟kafka生产者服务 */
	@Autowired
	IIntegralService integralService;  
	
	@RequestMapping("/test")
	public void maintainintegral(HttpServletRequest request){
		
		integralService.sendMessage();
	}
	
}

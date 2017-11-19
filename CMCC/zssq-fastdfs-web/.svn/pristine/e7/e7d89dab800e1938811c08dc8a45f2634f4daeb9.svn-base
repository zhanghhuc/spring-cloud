package com.zssq.fastdfs.util.ueditor;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class InitSystemUtil implements ApplicationContextAware{

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		
		Map<String, String> UeditorMap=ResourceUtils.getResource("config").getMap();
		UeditorConstants.setConfig(UeditorMap.get(("ueditro.json")));
		
	}


	

	
	
	
}

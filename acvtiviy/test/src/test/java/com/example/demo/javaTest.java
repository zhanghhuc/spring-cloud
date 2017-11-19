package com.example.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * Created by zhang on 2017/9/21.
 */
public class javaTest {

    @Test
    public void createTable_2() {
        //通过让工作流引擎的全部配置对象来执行配置文件中的内容来创建流程引擎对象
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource(
                        "activiti.cfg.xml").buildProcessEngine();
        System.out.println("processEngine" + processEngine);
    }
}

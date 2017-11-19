package com.zssq.job.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.AbstractSequencePerpetualElasticJob;
import com.dangdang.ddframe.job.api.JobExecutionSingleShardingContext;
import com.zssq.pojo.Foo;

/**
 * 根据分片数多线程得到的数据，随机顺序处理数据
 * @author lmz
 *
 */
@Component
public class SequencePerpetualElasticJob extends AbstractSequencePerpetualElasticJob<Foo> {

	private boolean flag = true;
	
	/**
	 * 抓取数据
	 * 返回值为null，停止该任务
	 */
    @Override
    protected List<Foo> fetchData(JobExecutionSingleShardingContext context) {
    	List<Foo> result = null;
    	
    	System.out.println("flag:" + flag);
    	String item = context.getShardingItemParameter();
    	System.out.println("item:" + item);
    	if("A".equals(item)){
	    	if(flag){
		    	result = new ArrayList<Foo>();
		        
		        Foo f1 = new Foo();
		        f1.setName("张三");
		        f1.setAge("17");
		        result.add(f1);
		        Foo f2 = new Foo();
		        f2.setName("李四");
		        f2.setAge("18");
		        result.add(f2);
	    	}
    	}
        
        return result;
    }
    
    /**
     * 处理数据
     * true：处理数据成功
     * false：处理数据失败
     * 当数据处理成功后，给该数据标记下，以免反复处理
     */
    @Override
    protected boolean processData(JobExecutionSingleShardingContext context, Foo data) {
    	System.out.println(data.getName());
    	System.out.println(data.getAge());
    	
    	if("18".equals(data.getAge())){
	    	flag = false;
    	}
    	
        return true;
    }
}
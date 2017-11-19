package com.zssq.elasticjob;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.zssq.utils.PropertiesUtil;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class JobHttpUtil {
	private static Properties props = new PropertiesUtil("elastic-job.properties");
	
	private static Logger log = Logger.getLogger(JobHttpUtil.class);
	
	public static String addHttpJob(String jobName,String jobClass,String shardingTotalCount,String cron) throws Exception{
    	List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
    	String requestUrl = "";
    	requestUrl = props.getProperty("jobHttpURL");
		
    	HttpClient client = HttpClients.createDefault();
    	HttpPost httpPost = new HttpPost(requestUrl);
    	requestParams.add(new BasicNameValuePair("jobName", jobName));
    	requestParams.add(new BasicNameValuePair("jobClass", jobClass));
    	requestParams.add(new BasicNameValuePair("shardingTotalCount", shardingTotalCount));
    	requestParams.add(new BasicNameValuePair("cron", cron));
    	
    	httpPost.setEntity(new UrlEncodedFormEntity(requestParams,
				Consts.UTF_8));
    	
		HttpResponse httpResponse = client.execute(httpPost);
		
		log.info("Request.httpPost.getURI(): " + httpPost.getURI());
		log.info("Response Code: " + httpResponse.getStatusLine().getStatusCode());
		HttpEntity entity = httpResponse.getEntity();
		String entityStr = EntityUtils.toString(entity);
		log.info("entityStr:" + entityStr);
		
		return entityStr;
    }
    
    public static void main(final String[] args) {
    	
    	try {
			JobHttpUtil.addHttpJob("testOnce", "com.zssq.job.demo.OneOffElasticJob", "3", "0/10 * * * * ?");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}

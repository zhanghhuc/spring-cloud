package com.zssq.search.service.impl;

import com.zssq.constants.SolrCoreConstants;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.ResultJSON;
import com.zssq.search.entity.SearchBean;
import com.zssq.search.po.ZssqResultVo;
import com.zssq.search.service.SolrQueryService;
import com.zssq.solr.basic.SolrQueryBean;
import com.zssq.solr.page.Page;
import com.zssq.solr.po.SensitivewordPo;
import com.zssq.solr.service.CloudSolrQueryHandler;
import com.zssq.solr.service.CloudSolrSensitivewordsHandler;
import org.apache.solr.client.solrj.response.TermsResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.apache.solr.common.util.ExecutorUtil.log;

@Service
public class SolrQueryServiceImpl implements SolrQueryService {

	@Autowired
	private CloudSolrQueryHandler solrQueryHandler ;
	@Autowired
	private CloudSolrSensitivewordsHandler solrSensitivewordsHandler ;
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(SolrQueryServiceImpl.class);

	@Override
	public List<TermsResponse.Term> frequencyStatistics(SolrQueryBean solrQueryBean, String coreName) {
		return solrQueryHandler.frequencyStatistics(solrQueryBean, coreName);
	}

	@Override
	public List<String> spellCheck(SolrQueryBean solrQueryBean, String coreName) {
//		return solrQueryHandler.spellCheck(solrQueryBean, coreName);
		return solrQueryHandler.spellCheck(solrQueryBean, coreName);
	}

	/**
	 * 根据query参数查询所有匹配的索引
	 *
	 * @param query
	 */
	@Override
	public SolrDocumentList query(String query, String coreName){
		return  solrQueryHandler.query(query, coreName);
	}
	

	/**
	 *
	 * function: 查询所有匹配的索引
	 *
	 * @param
	 * @author
	 * @createDate 2017-01-24 16:43:28
	 */
	@Override
	public SolrDocumentList queryAll(SearchBean searchBean, String coreName) {
		return  solrQueryHandler.queryAll(searchBean, coreName);
	}
	
	/**
	 *
	 * function: 分页查询索引 -PO类型自动转换
	 *
	 * @param
	 * @author
	 * @createDate 2017-01-24 16:43:28
	 */
	@Override
	public <T> Page<T> queryPageBean(SearchBean searchBean, int pageNum, int pageSize, Class<T> clzz, String coreName) {
		return  solrQueryHandler.queryPageBean(searchBean, pageNum, pageSize, clzz, coreName);
	}
	
	/**
	 * 分页查询 根据关键字查询 [测试通过 - -PO类型自动转换, 使用 solr内部转换机制]
	 * @param <T>
	 * @param coreName
	 *            solr库名称
	 * @param keyword
	 *            搜索关键字
	 * @param pageNum
	 *            当前页码
	 * @param pageSize
	 *            每页显示的大小
	 * @param clzz
	 *            对象类型
	 * @return
	 */
	@Override
	public <T> Page<T> queryBean(String keyword, int pageNum, int pageSize, Class<T> clzz, String hlFile, String coreName) {
		return  solrQueryHandler.queryBean(keyword, pageNum, pageSize, clzz,hlFile, coreName);
	}

	@Override
	public <T> Page<T> queryBeanHighlighting(String condition, int pageNum, int pageSize, Class<T> clzz, String hlFile, String coreName) {
		Page  page = solrQueryHandler.queryBeanHighlighting(condition, pageNum, pageSize, clzz,hlFile,coreName);
		if(null != page && null != page.getItems() && page.getItems().size() >0){
			List<ZssqResultVo>  zssqResultVos =page.getItems() ;
			Map<String, Map<String, List<String>>> hlFileds =page.getHlFileds();
			for(ZssqResultVo zssqResultVo : zssqResultVos){
				Map<String, List<String>> map =hlFileds.get(zssqResultVo.getCategoryCode()) ;
				List<String> list = map.get(hlFile) ;
				if(null != list){
					zssqResultVo.setHighlightingFiled(list.get(0));
				}
			}
		}else{
			page = new Page() ;
		}
		return page ;
	}


	/**
	 * 分页查询 根据关键字查询 [测试通过 - -PO类型自动转换, 使用 solr内部转换机制]
	 * 
	 * @param <T>
	 * @param coreName
	 *            solr库名称
	 * @param keyword
	 *            搜索关键字
	 * @param pageNum
	 *            当前页码
	 * @param pageSize
	 *            每页显示的大小
	 * @param clzz
	 *            对象类型
	 * @return
	 */
	@Override
	public <T> Page<T> queryBinderBean(String keyword, String condition , int pageNum, int pageSize, Class<T> clzz, String coreName) {
		return  solrQueryHandler.queryBinderBean(keyword,condition, pageNum, pageSize, clzz, coreName);
	}

	@Override
	public List<String> getAnalysis(String sentence, String coreName) {
		return solrSensitivewordsHandler.getAnalysis(sentence,coreName);
	}


	@Override
	public Set<String>  checkSensitiveWords(String sentence, String coreName) {
		Set<String> sensitivewords = new HashSet<>() ;
		String sliceSentence = "";
		List<SensitivewordPo> list= new ArrayList<>() ;
		int length = sentence.length();
		int nextLength = 0 ;
		boolean endFflag = false ;
		for (int index = 0; index < length; ) {
            if(length<index+200){
                nextLength=length;
				endFflag = true ;
            }else {
                nextLength = index+200 ;
            }
			sliceSentence = sentence.substring(index,nextLength)  ;
			list = solrSensitivewordsHandler.checkSensitiveWords(sliceSentence, coreName) ;
			if(null !=list && list.size()>0){
				for (SensitivewordPo sensitivewordPo : list){
					sensitivewords.add(sensitivewordPo.getSensitivewords()) ;
				}
			}
			if(endFflag){
				index = index +200 ;

			}else{
				index = nextLength - 10;
			}
		}
		return sensitivewords;
	}



	@Override
	public JSONArray CheckSentence(String orgCode ,String sentence, String coreName) {
		JSONObject json = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject body = new JSONObject();
		Set<String> set = new HashSet<>();
		try {
			/** 查询功能  */
			if (null != sentence && !"".equals(sentence)) {
				set = wordFilter(orgCode ,sentence, SolrCoreConstants.SENSITIVE_WORD_CORE);
				//根据用户code列表获取用户集合
				Iterator<String> it = set.iterator();
				while (it.hasNext()) {
					json = new JSONObject();
					json.put("sensitivewords", it.next());
					jsonArray.add(json);
				}
			}

			log.info("SearchController.checkSensitiveWords:敏感词过滤成功");
		} catch (Exception e) {
			log.error("敏感词过滤",e);
		}
		return jsonArray;
	}

	/**
	 * 过滤敏感词，与敏感词库比对
	 * @param sentence
	 * @param coreName
	 * @return
	 */
	public Set<String>  wordFilter(String orgCode, String sentence, String coreName) {
		Set<String> sensitivewords = new HashSet<>() ;
		List<SensitivewordPo> list = getSensitiveWords(orgCode,SolrCoreConstants.SENSITIVE_WORD_CORE) ;
		for (SensitivewordPo sensitivewordPo : list ) {
			if(sentence.indexOf(sensitivewordPo.getSensitivewords()) > 0){
				sensitivewords.add(sensitivewordPo.getSensitivewords()) ;
			}
		}
		return sensitivewords;
	}

	public  List<SensitivewordPo> getSensitiveWords(String orgCode,  String sensitiveWordCore) {
		String condition = "orgCode:0 " ;
		if(null != orgCode && !"".equals(orgCode)){
			condition = condition +" OR orgCode:"+orgCode ;
		}
		Page<SensitivewordPo> resultVoPage =solrQueryHandler.queryBinderBean(null,condition, 0, 0, SensitivewordPo.class,
				sensitiveWordCore);
		if(resultVoPage.getTotalCount() > 10){
			resultVoPage =solrQueryHandler.queryBinderBean(null,condition, 1, resultVoPage.getTotalCount(), SensitivewordPo.class,
					sensitiveWordCore);
		}

		List<SensitivewordPo> list = resultVoPage.getItems() ;
		return list ;
	}

}

package com.zssq.search.service;

import com.alibaba.fastjson.JSONArray;
import com.zssq.search.entity.SearchBean;
import com.zssq.solr.basic.SolrQueryBean;
import com.zssq.solr.page.Page;
import com.zssq.solr.po.SensitivewordPo;
import org.apache.solr.client.solrj.response.TermsResponse;
import org.apache.solr.common.SolrDocumentList;

import java.util.List;
import java.util.Set;

public interface SolrQueryService {

	/**
	 * 分页查询
	 * @param searchBean 查询条件
	 * @param pageNum 当前页码
	 * @param pageSize 每页显示数量
	 * @param clzz  PO对象所属类
	 * @param coreName 目标solr库
	 * @param <T>
	 * @return
	 */
	<T> Page<T> queryPageBean(SearchBean searchBean, int pageNum, int pageSize, Class<T> clzz, String coreName);

	/**
	 * 根据关键字进行分页查询 -- 关键字高亮显示
	 * @param keyword 关键字
	 * @param pageNum 当前页码
	 * @param pageSize 每页显示数量
	 * @param clzz     PO对象所属类
	 * @param coreName 目标solr库
	 * @param <T>
	 * @return
	 */
	<T> Page<T> queryBean(String keyword, int pageNum, int pageSize, Class<T> clzz, String hlFile, String coreName);

	/**
	 * 根据关键字进行分页查询 -- 关键字高亮显示
	 * @param condition 关键字
	 * @param pageNum 当前页码
	 * @param pageSize 每页显示数量
	 * @param clzz     PO对象所属类
	 * @param coreName 目标solr库
	 * @param <T>
	 * @return
	 */
	<T> Page<T> queryBeanHighlighting(String condition, int pageNum, int pageSize, Class<T> clzz, String hlFile, String coreName);

	/**
	 *  敏感词过滤
	 * @param sentence 待检查语句
	 * @param coreName 分词词库(不需要拓展分词库)
	 * @return
	 */
	JSONArray CheckSentence(String orgCode , String sentence, String coreName);

	/**
	 *  敏感词过滤
	 * @param sentence 待检查语句
	 * @param coreName 分词词库(敏感词库程序自己维护,需要拓展分词库)
	 * @return
	 */
	Set<String> checkSensitiveWords(String sentence, String coreName);

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
	<T> Page<T> queryBinderBean(String keyword, String condition, int pageNum, int pageSize, Class<T> clzz, String coreName);

	/**
	 * 对给定语句进行分词
	 * @param sentence
	 * @param coreName
	 * @return
	 */
	List<String> getAnalysis(String sentence, String coreName);

	/**
	 * 词频统计
	 * @param solrQueryBean
	 * @param coreName
	 * @return
	 */
	List<TermsResponse.Term> frequencyStatistics(SolrQueryBean solrQueryBean, String coreName);

	/**
	 * 拼写检查
	 * @param solrQueryBean
	 * @param coreName
	 * @return
	 */
	List<String> spellCheck(SolrQueryBean solrQueryBean, String coreName) ;

	/**
	 * 返回文档结构类型的查询结果
	 * @param query
	 * @param coreName
	 * @return
	 */
	SolrDocumentList query(String query, String coreName);

	/**
	 * 返回文档结构类型的查询结果
	 * @param searchBean
	 * @param coreName
	 * @return
	 */
	SolrDocumentList queryAll(SearchBean searchBean, String coreName);

	/**
	 *  敏感词过滤--废弃
	 * @param orgCode
	 * @param sensitiveWordCore
	 * @return
	 */
	public  List<SensitivewordPo> getSensitiveWords(String orgCode, String sensitiveWordCore) ;
}

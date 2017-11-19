package com.zssq.news.service;

import com.zssq.news.dao.mapper.InfoMapper;
import com.zssq.news.dao.mapper.NewsInfoContentMapper;
import com.zssq.news.po.Info;
import com.zssq.news.po.InfoExample;
import com.zssq.news.po.NewsInfoContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SharlaCheung
 * @ClassName: NewsService
 * @Description: 新闻数据迁移Service
 * @date 2017年5月22日
 */
@Service("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private InfoMapper infoMapper;
    @Autowired
    private NewsInfoContentMapper newsInfoContentMapper;

    /**
     * @Title: getDB2SubCount
     * @Description: 查询DB2库中新闻关系数据总个数
     * @return: int    返回类型
     */
    public int getDB2NewsCount() {
        InfoExample example = new InfoExample() ;
        return infoMapper.countByExample(example);
    }

    /**
     * @param
     * @Title: getDB2SubList
     * @Description: 查询DB2库中新闻关系数据
     * @return: List<NewsInfoVoDB2>    返回类型
     */
    public List<Info> getDB2SubList() {
        InfoExample example = new InfoExample() ;

       return infoMapper.selectByExampleWithBLOBs(example) ;
    }

    /**
     * @param infos
     * @Title: insertMySQLSubList
     * @Description: 批量向MySQL库中插入新闻数据
     * @return: boolean    返回类型
     */
    public boolean insertMySQLSubList(List<NewsInfoContent> infos) {
        try{
            for(NewsInfoContent newsInfoContent : infos){
                newsInfoContentMapper.insertSelective(newsInfoContent) ;
            }
            return true ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}

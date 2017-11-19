package com.zssq.disk.service;

import com.zssq.disk.po.DiskFile;
import com.zssq.disk.po.NetdiskFile;

import java.util.List;

/**
 * @author SharlaCheung
 * @ClassName: NewsService
 * @Description: 新闻数据迁移Service
 * @date 2017年5月22日
 */
public interface DiskFileService {
    /**
     * @Title: getDB2SubCount
     * @Description: 查询DB2库中新闻关系数据总个数
     * @return: int    返回类型
     */
    public int getDB2NewsCount();

    /**
     * @param limitStart   页码
     * @param pageSize 每页个数
     * @Title: getDB2SubList
     * @Description: 查询DB2库中新闻关系数据
     * @return: List<NewsInfoVoDB2>    返回类型
     */
    public List<NetdiskFile> getDB2SubList(int limitStart, int pageSize) ;

    /**
     * @param infos
     * @Title: insertMySQLSubList
     * @Description: 批量向MySQL库中插入新闻数据
     * @return: boolean    返回类型
     */
    public boolean insertMySQLSubList(List<DiskFile> infos);

    List<NetdiskFile> getList(int i, int count);
}

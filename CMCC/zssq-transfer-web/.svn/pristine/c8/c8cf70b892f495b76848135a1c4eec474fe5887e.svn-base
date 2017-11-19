package com.zssq.disk.service;

import com.zssq.disk.dao.DiskFileMapper;
import com.zssq.disk.dao.NetdiskFileMapper;
import com.zssq.disk.po.DiskFile;
import com.zssq.disk.po.NetdiskFile;
import com.zssq.disk.po.NetdiskFileExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SharlaCheung
 * @ClassName: DiskFileService
 * @Description: 网盘数据迁移Service
 * @date 2017年5月22日
 */
@Service("diskFileService")
public class DiskFileServiceImpl implements DiskFileService {

    @Autowired
    private DiskFileMapper diskFileMapper;
    @Autowired
    private NetdiskFileMapper netdiskFileMapper ;

    /**
     * @Title: getDB2SubCount
     * @Description: 查询DB2库中新闻关系数据总个数
     * @return: int    返回类型
     */
    public int getDB2NewsCount() {
        NetdiskFileExample example = new NetdiskFileExample() ;
        return netdiskFileMapper.countByExample(example);
    }

    /**
     * @param limitStart   页码
     * @param pageSize 每页个数
     * @Title: getDB2SubList
     * @Description: 查询DB2库中新闻关系数据
     * @return: List<NewsInfoVoDB2>    返回类型
     */
    public List<NetdiskFile> getDB2SubList(int limitStart, int pageSize) {
        NetdiskFileExample example = new NetdiskFileExample() ;
        example.setLimitStart(limitStart);
        example.setLimitEnd(pageSize);
       return netdiskFileMapper.selectByExample(example) ;
    }


    @Override
    public List<NetdiskFile> getList(int i, int count) {
        NetdiskFileExample example = new NetdiskFileExample() ;
        example.setLimitStart(i);
        example.setLimitEnd(count);
        return netdiskFileMapper.selectList(example) ;
    }


    /**
     * @param diskFiles
     * @Title: insertMySQLSubList
     * @Description: 批量向MySQL库中插入新闻数据
     * @return: boolean    返回类型
     */
    public boolean insertMySQLSubList(List<DiskFile> diskFiles) {
        try{
               return  diskFileMapper.insertBatch(diskFiles) ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}

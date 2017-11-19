package com.zssq.service.impl;

import com.alibaba.fastjson.JSON;
import com.mongodb.DBObject;
import com.zssq.constants.StatisticMongoDBConstants;
import com.zssq.dao.mapper.StatisticCommonMapper;
import com.zssq.dao.mapper.StatisticPortalMapper;
import com.zssq.dao.mapper.StatisticVisitDetailMapper;
import com.zssq.dao.pojo.*;
import com.zssq.exceptions.BusinessException;
import com.zssq.mongodb.MongoDBDaoImpl;
import com.zssq.mongodb.config.MongoConfig;
import com.zssq.service.IStatisticService;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service("statisticService")
public class StatisticServiceImpl implements IStatisticService {

    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private MongoConfig mongoConfig;

    @Autowired
    private StatisticCommonMapper statisticCommonMapper;

    @Autowired
    private StatisticVisitDetailMapper statisticVisitDetailMapper;

    @Autowired
    private StatisticPortalMapper statisticPortalMapper;

    //	private MongoDBDaoImpl mongoDBDaoImpl = MongoDBDaoImpl.getMongoDBDaoImplInstance();
    @Autowired
    private MongoDBDaoImpl mongoDBDaoImpl;

    @Override
    public PageBean getTeam(PageParam pageParam, StatisticCommon record) throws BusinessException {
        log.info("StatisticServiceImpl.getTeam:pageParam.getPageNo():" + pageParam.getPageNo());
        log.info("StatisticServiceImpl.getTeam:pageParam.getPageSize():" + pageParam.getPageSize());

        record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
        record.setLimitEnd(pageParam.getPageSize());

        record.setIsFlag(1); //班组
//        record.setIsDelete(0);//班组未对访客删除访问记录的
        List<StatisticCommon> sc = statisticCommonMapper.selectPage(record);

        log.info("StatisticServiceImpl.getTeam:sc.size():" + sc.size());

        for (StatisticCommon statisticCommon : sc) {
            StatisticVisitDetail svd = new StatisticVisitDetail();
            svd.setOrgCode(statisticCommon.getOrgCode());
            svd.setTeamCode(statisticCommon.getTeamCode());
            svd.setType(8); //班组
            svd.setStartTime(record.getStartTime());
            svd.setEndTime(record.getEndTime());
            int visitCount = statisticVisitDetailMapper.selectVisitCount(svd); //获取每个班组空间的访问量
            statisticCommon.setVisitCount(Long.valueOf(visitCount));
        }
//        record.setIsDelete(null);//访客总量则查询全部
        //查询总条数sql
        List<StatisticCommon> count = statisticCommonMapper.selectPageCount(record);

        log.info("StatisticServiceImpl.getTeam:count:" + count);

        return new PageBean(pageParam.getPageNo(), pageParam.getPageSize(), count.size(), sc);
    }

    @Override
    public PageBean getPeople(PageParam pageParam, StatisticCommon record) throws BusinessException {
        log.info("StatisticServiceImpl.getPeople:pageParam.getPageNo():" + pageParam.getPageNo());
        log.info("StatisticServiceImpl.getPeople:pageParam.getPageSize():" + pageParam.getPageSize());

        record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
        record.setLimitEnd(pageParam.getPageSize());

        record.setIsFlag(2); //个人

        List<StatisticCommon> sc = statisticCommonMapper.selectPage(record);

        log.info("StatisticServiceImpl.getPeople:sc.size():" + sc.size());

        for (StatisticCommon statisticCommon : sc) {
            StatisticVisitDetail svd = new StatisticVisitDetail();
            svd.setOrgCode(statisticCommon.getOrgCode());
            svd.setPeopleCode(statisticCommon.getPeopleCode());
            svd.setType(9); //个人
            svd.setStartTime(record.getStartTime());
            svd.setEndTime(record.getEndTime());
            int visitCount = statisticVisitDetailMapper.selectVisitCount(svd);
            statisticCommon.setVisitCount(Long.valueOf(visitCount));
        }

        //查询总条数sql
        List<StatisticCommon> count = statisticCommonMapper.selectPageCount(record);

        log.info("StatisticServiceImpl.getPeople:count:" + count);

        return new PageBean(pageParam.getPageNo(), pageParam.getPageSize(), count.size(), sc);
    }

    @Override
    public StatisticApp getApp(StatisticCommon record) throws BusinessException {
        StatisticPortal recordSP = new StatisticPortal();
        recordSP.setOrgCode(record.getOrgCode());
        recordSP.setStartTime(record.getStartTime());
        recordSP.setEndTime(record.getEndTime());

        List<StatisticCommon> sc = statisticCommonMapper.selectPage(record);
//        List<StatisticPortal> sp = statisticPortalMapper.selectPage(recordSP);

        log.info("StatisticServiceImpl.getApp:sc.size():" + sc.size());
//        log.info("StatisticServiceImpl.getApp:sp.size():" + sp.size());

        StatisticApp sa = new StatisticApp();
        sa.setOrgCode(record.getOrgCode());

        if (sc.size() == 0 || sc.get(0) == null) {
            sa.setMicroblog(0);
            sa.setBlog(0);
            sa.setVote(0);
            sa.setNetworkDisk(0);
            sa.setRepository(0);
            sa.setNews(0);
            sa.setActivity(0);
        } else {
            sa.setMicroblog(sc.get(0).getMicroblog());
            sa.setBlog(sc.get(0).getBlog());
            sa.setVote(sc.get(0).getVote());
            sa.setNetworkDisk(sc.get(0).getNetworkDisk());
            sa.setRepository(sc.get(0).getRepository());
            sa.setNews(sc.get(0).getNews());
            sa.setActivity(sc.get(0).getActivity());
        }

//        if (sp.size() == 0 || sp.get(0) == null) {
//            sa.setNews(0);
//            sa.setActivity(0);
//        } else {
//            sa.setNews(sp.get(0).getNews());
//            sa.setActivity(sp.get(0).getActivity());
//        }

        return sa;


    }

    @Override
    public PageBean getPortal(PageParam pageParam, StatisticPortal record) throws BusinessException {
        log.info("StatisticServiceImpl.getPortal:pageParam.getPageNo():" + pageParam.getPageNo());
        log.info("StatisticServiceImpl.getPortal:pageParam.getPageSize():" + pageParam.getPageSize());

        record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
        record.setLimitEnd(pageParam.getPageSize());

        record.setIsFlag(1); //门户

        List<StatisticPortal> sp = statisticPortalMapper.selectPage(record);

        log.info("StatisticServiceImpl.getPortal:sp.size():" + sp.size());

        for (StatisticPortal statisticPortal : sp) {
            StatisticVisitDetail svd = new StatisticVisitDetail();
            svd.setOrgCode(statisticPortal.getOrgCode());
            svd.setIsFlag(1);
            svd.setStartTime(record.getStartTime());
            svd.setEndTime(record.getEndTime());
            int visitCount = statisticVisitDetailMapper.selectVisitCount(svd);
            statisticPortal.setVisitCount(Long.valueOf(visitCount));
        }

        //查询总条数sql
        List<StatisticPortal> count = statisticPortalMapper.selectPageCount(record);

        log.info("StatisticServiceImpl.getPortal:count:" + count);

        return new PageBean(pageParam.getPageNo(), pageParam.getPageSize(), count.size(), sp);
    }

    @Override
    public int deleteVisit(int id) throws BusinessException {
        return statisticVisitDetailMapper.deleteByPrimaryKey(Long.valueOf(id));
    }

    @Override
    public int deleteVisit(StatisticVisitDetail record) throws BusinessException {
        return statisticVisitDetailMapper.deleteByCommon(record);
    }

    @Override
    public PageBean getVisit(PageParam pageParam, StatisticVisitDetail record) throws BusinessException {
        if (null != pageParam) {
            log.info("StatisticServiceImpl.getVisit:pageParam.getPageNo():" + pageParam.getPageNo());
            log.info("StatisticServiceImpl.getVisit:pageParam.getPageSize():" + pageParam.getPageSize());
            record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
            record.setLimitEnd(pageParam.getPageSize());
        }
        record.setIsDelete(0);
        List<StatisticVisitDetail> svd = statisticVisitDetailMapper.selectPcPage(record); //获取全量数据的当前页数据

        log.info("StatisticServiceImpl.getVisit:svd.size():" + svd.size());
        record.setIsDelete(null);
        int visitCount = statisticVisitDetailMapper.selectVisitCountPc(record); //(访问总人数,展示用)

        log.info("StatisticServiceImpl.getVisit:visitCount:" + visitCount);
        PageBean pageBean = new PageBean();
        pageBean.setPageCount(visitCount);
        pageBean.setRecordList(svd);
        //总条数(分页用)
        int byUserCount = statisticVisitDetailMapper.selectGroupByUser(record);
        pageBean.setNumPerPage(byUserCount);
        return pageBean;
    }

    @Override
    public int getTodayVisit(StatisticVisitDetail record) throws BusinessException {
        int visitTodayCount = statisticVisitDetailMapper.selectVisitCountPc(record); //获取今天数据的班组/个人访问量总数
        log.info("StatisticServiceImpl.getTodayVisit:visitTodayCount:" + visitTodayCount);
        return visitTodayCount;
    }

    @Override
    public int addRecord(StatisticCommon record) {
        int result = 0;
        try {
            if (null == record.getCreateTime() || record.getCreateTime() == 0) {
                record.setCreateTime(System.currentTimeMillis());
            }
            statisticCommonMapper.insert(record);
        } catch (Exception e) {
            result = -1;
            log.info("StatisticServiceImpl.addRecord:添加统计记录失败", e);
        }
        return result;
    }
    
    

    @Override
	public int addRecord(StatisticCommon teamStatistic, StatisticCommon userStatistic) {
    	int result = 0;
        try {
            if (null == teamStatistic.getCreateTime() || teamStatistic.getCreateTime() == 0) {
            	teamStatistic.setCreateTime(System.currentTimeMillis());
            }
            statisticCommonMapper.insert(teamStatistic);
            
            if (null == userStatistic.getCreateTime() || userStatistic.getCreateTime() == 0) {
            	userStatistic.setCreateTime(System.currentTimeMillis());
            }
            statisticCommonMapper.insert(userStatistic);
        } catch (Exception e) {
            result = -1;
            log.info("StatisticServiceImpl.addRecord:添加(班组/个人)统计记录失败", e);
        }
        return result;
	}

	@Override
    public List<StatisticCompany> getCompanyVisit(List<StatisticVisitDetail> record) throws BusinessException {

        List<StatisticCompany> results = new ArrayList<StatisticCompany>();

        for (StatisticVisitDetail statisticVisitDetail : record) {

            StatisticCompany sc = new StatisticCompany();

            int visitCount = statisticVisitDetailMapper.selectVisitCount(statisticVisitDetail); 

            log.info("StatisticServiceImpl.getVisit:visitCount:" + visitCount);

            sc.setOrgCode(statisticVisitDetail.getOrgCode());
            sc.setVisitCount(visitCount);

            results.add(sc);
        }

        //按指定属性正序排序
        Collections.sort(results, new Comparator<StatisticCompany>() {
            public int compare(StatisticCompany arg0, StatisticCompany arg1) {
                return arg0.getVisitCount().compareTo(arg1.getVisitCount());
            }
        });

        //把list倒序排序
        Collections.reverse(results);

        return results;
    }

    @Override
    public boolean addMongoDBVisit(String[] keys, String[] value, int num) throws BusinessException {
        ArrayList<DBObject> dbResults = mongoDBDaoImpl.find(mongoConfig.getDatabase(), mongoConfig.getDbCollectionName(), keys, value, num);
//		ArrayList<DBObject> dbResults = mongoDBDaoImpl.find(props.getProperty("db"), props.getProperty("dbCollectionName"), keys, value, num);

        log.info("StatisticServiceImpl.addMongoDBVisit:dbResults.size():" + dbResults.size());

        for (DBObject dbObject : dbResults) {
            String type = String.valueOf(dbObject.get("type"));

            log.info("StatisticServiceImpl.addMongoDBVisit:dbObject.get(\"userInfo\")：" + dbObject.get("userInfo"));

            StatisticSysUserInfo sysUserInfo = JSON.parseObject(String.valueOf(dbObject.get("userInfo")), StatisticSysUserInfo.class);

            if (StatisticMongoDBConstants.HOMEPAGE.equals(type) || StatisticMongoDBConstants.NEWS.equals(type) || StatisticMongoDBConstants.ACTIVITY.equals(type) || StatisticMongoDBConstants.MARROW.equals(type) || StatisticMongoDBConstants.HOTSPOT.equals(type) || StatisticMongoDBConstants.DYNAMIC.equals(type) || StatisticMongoDBConstants.TOP.equals(type) || StatisticMongoDBConstants.DETAIL.equals(type)) {
                StatisticVisitDetail statisticVisitDetail = new StatisticVisitDetail();
                statisticVisitDetail.setOrgCode(String.valueOf(dbObject.get("orgCode")));
                statisticVisitDetail.setIp(String.valueOf(dbObject.get("clientIp")));
                statisticVisitDetail.setVisitCode(sysUserInfo.getUserCode());
                statisticVisitDetail.setVisitTime(Long.valueOf(String.valueOf(dbObject.get("visitTime"))));
                statisticVisitDetail.setType(Integer.valueOf(type));
                statisticVisitDetail.setCreateTime(DateUtils.getTime());

                statisticVisitDetailMapper.insertSelective(statisticVisitDetail);

                StatisticPortal statisticPortal = new StatisticPortal();
                statisticPortal.setOrgCode(String.valueOf(dbObject.get("orgCode")));
                statisticPortal.setCreateTime(DateUtils.getTime());

                if (StatisticMongoDBConstants.HOMEPAGE.equals(type)) {
                    statisticPortal.setHomePage(1);
                } else if (StatisticMongoDBConstants.NEWS.equals(type)) {
                    statisticPortal.setNews(1);
                } else if (StatisticMongoDBConstants.ACTIVITY.equals(type)) {
                    statisticPortal.setActivity(1);
                } else if (StatisticMongoDBConstants.MARROW.equals(type)) {
                    statisticPortal.setMarrow(1);
                } else if (StatisticMongoDBConstants.HOTSPOT.equals(type)) {
                    statisticPortal.setHotspot(1);
                } else if (StatisticMongoDBConstants.DETAIL.equals(type)) {
                    statisticPortal.setDetail(1);
                } else if (StatisticMongoDBConstants.TOP.equals(type)) {
                    statisticPortal.setTop(1);
                } else if (StatisticMongoDBConstants.DETAIL.equals(type)) {
                    statisticPortal.setDetail(1);
                }

                statisticPortalMapper.insertSelective(statisticPortal);

                mongoDBDaoImpl.delete(mongoConfig.getDatabase(), mongoConfig.getDbCollectionName(), keys, value);
            } else if (StatisticMongoDBConstants.TEAM.equals(type) || StatisticMongoDBConstants.PEOPLE.equals(type)) {
                StatisticVisitDetail statisticVisitDetail = new StatisticVisitDetail();
                statisticVisitDetail.setOrgCode(String.valueOf(dbObject.get("orgCode")));
                statisticVisitDetail.setIp(String.valueOf(dbObject.get("clientIp")));
                statisticVisitDetail.setVisitCode(sysUserInfo.getUserCode());
                statisticVisitDetail.setType(Integer.valueOf(type));
                statisticVisitDetail.setCreateTime(DateUtils.getTime());
                statisticVisitDetail.setVisitTime(Long.valueOf(String.valueOf(dbObject.get("visitTime"))));
                if (StatisticMongoDBConstants.TEAM.equals(type)) {
                    statisticVisitDetail.setTeamCode(String.valueOf(dbObject.get("intervieweeCode")));
                } else {
                    statisticVisitDetail.setPeopleCode(String.valueOf(dbObject.get("intervieweeCode")));
                }

                statisticVisitDetailMapper.insertSelective(statisticVisitDetail);

                mongoDBDaoImpl.delete(mongoConfig.getDatabase(), mongoConfig.getDbCollectionName(), keys, value);
            }
        }
        return true;
    }

}

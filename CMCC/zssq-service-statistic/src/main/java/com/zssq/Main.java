package com.zssq;


import com.zssq.constants.StatisticMongoDBConstants;
import com.zssq.service.IStatisticService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author SharlaCheung
 * @ClassName: DisconfMain
 * @Description: disconf测试类
 * @date 2017年01月09日
 */
public class Main {

    /**
     * 方法名称: main<br>
     * 描述：服务启动 作者: SharlaCheung 修改日期：2017年01月09日下午19:09:48
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                new String[]{"classpath:spring-*.xml"});
        applicationContext.start();
        System.out.println("===========================服务启动成功！===============================");

        /** 查询开始 */
        IStatisticService statisticService =
                (IStatisticService) applicationContext.getBean("statisticService");


        String[] values = new String[]{StatisticMongoDBConstants.HOMEPAGE,StatisticMongoDBConstants.NEWS,StatisticMongoDBConstants.ACTIVITY,StatisticMongoDBConstants.MARROW,StatisticMongoDBConstants.HOTSPOT,StatisticMongoDBConstants.DYNAMIC,StatisticMongoDBConstants.TOP,StatisticMongoDBConstants.DETAIL,StatisticMongoDBConstants.TEAM,StatisticMongoDBConstants.PEOPLE};

        String[] keys = new String[1];
        keys[0] = "type";
        int num = -1; //全部查询

        boolean result = false;

        for (String v : values) {
            String[] value = new String[1];
            value[0] = v;

            result = statisticService.addMongoDBVisit(keys, value, num);

          System.out.println(result);
        }

//        PageParam pageParam = new PageParam(0,20) ;
//        StatisticVisitDetail record = new StatisticVisitDetail() ;
//        record.setPeopleCode("chenxu@hq.cmcc");
////        record.setTeamCode("ace1fc5dbdff4bfaba35576318ff4dcd");
//        PageBean pageBean = statisticService.getVisit(pageParam,record) ;
//        System.out.println("总数  ："+pageBean.getTotalCount());
//
//
//
//        record = new StatisticVisitDetail();
////        record.setTeamCode("ace1fc5dbdff4bfaba35576318ff4dcd");
//        record.setPeopleCode("chenxu@hq.cmcc");
//        record.setVisitCode("liuhong@hq.cmcc");
//        int size = statisticService.deleteVisit(record);
//        System.out.println("影响行数  ：" + size);
//
//        record = new StatisticVisitDetail() ;
//        record.setPeopleCode("chenxu@hq.cmcc");
////        record.setTeamCode("ace1fc5dbdff4bfaba35576318ff4dcd");
//        PageBean pageBean1 = statisticService.getVisit(pageParam,record) ;
//        System.out.println("操作后总数  ："+pageBean1.getTotalCount());


//        StatisticVisitDetail record = new StatisticVisitDetail() ;
//        record.setOrgCode("35a85cac0faf4045a4f5bc35237a8b07");
//        int totalCount = statisticService.getTodayVisit(record) ;
//
//        System.out.println(totalCount);
//        record.setStartTime(DateUtils.getCurDayStart());
//        record.setEndTime(System.currentTimeMillis());
//        int todayVisit = statisticService.getTodayVisit(record) ;
//        System.out.println(todayVisit);


        System.out.println("任意键退出服务");
        System.in.read();
        applicationContext.stop();

    }
}

package com.zssq.news.controller;

import com.alibaba.fastjson.JSONObject;
import com.zssq.news.po.Info;
import com.zssq.news.po.NewsInfoContent;
import com.zssq.news.po.SysUserInfo;
import com.zssq.news.service.NewsService;
import com.zssq.pojo.ResultJSON;
import com.zssq.shiro.mysecurity.UUIDHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SharlaCheung
 * @ClassName: NewsController
 * @Description: 新闻数据迁移Controller
 * @date 2017年5月22日
 */
@Controller
@RequestMapping("newsController")
public class NewsController {

    @Autowired
    private NewsService newsService;

    /**
     * @Title: transferNewsData
     * @Description: 迁移新闻数据
     * @return: void    返回类型
     */
    @RequestMapping(value = "/transferNewsData")
    @ResponseBody
    public ResultJSON transferNewsData() {
        // 返回值
        ResultJSON result = new ResultJSON("COMMON_200");
        JSONObject body = new JSONObject();
        int limitStart = 0;
        int pageSize = 100;

        try {
            // 查询DB2库中新闻数据总量
            int count = newsService.getDB2NewsCount();
            if (count <= 0) {
                body.put("message", "查询DB2库中新闻数据总量时出错，查询出的个数为：" + count);
                throw new Exception();
            }

            // 循环处理数据
            boolean arrangeFlag = arrangeSubData();
            if (!arrangeFlag) {
                body.put("message", "处理数据时失败");
                throw new Exception();
            }
            limitStart = limitStart + pageSize;
        } catch (Exception e) {
            result = new ResultJSON("COMMON_400");
            body.put("limitStart", limitStart);
            result.setBody(body);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param
     * @Title: arrangeSubData
     * @Description: 整理新闻数据
     * @return: boolean    返回类型
     */
    private boolean arrangeSubData() {
        // 返回值
        boolean result = true;
        try {
            // 获取DB2库中的新闻数据
            List<Info> DB2NewsList = newsService.getDB2SubList();

            // 校验返回值
            if (DB2NewsList == null || DB2NewsList.isEmpty()) {
                return false;
            }

            // 将DB2中的数据整理为MySQL中的数据
            List<NewsInfoContent> mySQLSubList = new ArrayList<NewsInfoContent>();
            for (Info info : DB2NewsList) {
                NewsInfoContent newsInfoVoMysql = new NewsInfoContent();
                SysUserInfo sysUserInfo = info.getSysUserInfo();
                if (null == sysUserInfo) {
                    continue;
                }
                newsInfoVoMysql.setNewsCode(UUIDHelper.getUUID());
                newsInfoVoMysql.setInfoTitle(info.getInfoTitle());
//				if(null != newsInfoVoDB2.getInfoContentHtml() &&!"".equals(newsInfoVoDB2.getInfoContentHtml())){
//					int length = newsInfoVoDB2.getInfoContentHtml().length() ;
//					if(length<200){
//						newsInfoVoMysql.setInfoContentText(newsInfoVoDB2.getInfoContentHtml());
//					}else{
//						newsInfoVoMysql.setInfoContentText(newsInfoVoDB2.getInfoContentHtml().substring(0,200)+"...");
//					}
//					newsInfoVoMysql.setInfoContentHtml(newsInfoVoDB2.getInfoContentHtml());
//				}
                newsInfoVoMysql.setInfoContentHtml(info.getInfoText());

                String text = delHTMLTag(info.getInfoText());
                String words = subStringText(text);
                newsInfoVoMysql.setInfoContentText(words);

                newsInfoVoMysql.setFileUrl(info.getImgUrl());
                int infoStatus = info.getInfoState() ;
                //新闻状态原数据与新数据状态不统一，需要挨个判定
                if(infoStatus==3){
                    newsInfoVoMysql.setInfoStatus(4);//已发布
                }else if(infoStatus == 2){
                    newsInfoVoMysql.setInfoStatus(1);//待审核
                }else{
                    newsInfoVoMysql.setInfoStatus(0);//草稿
                }
                newsInfoVoMysql.setInfoCreatorCode(sysUserInfo.getUserCode());
                newsInfoVoMysql.setInfoOperatorCode(sysUserInfo.getUserCode());
                newsInfoVoMysql.setCreateTime(info.getCreatetime() == null ? System.currentTimeMillis() : info.getCreatetime().getTime());
                newsInfoVoMysql.setModifyTime(info.getUpdatetime() == null ? System.currentTimeMillis() : info.getUpdatetime().getTime());
                newsInfoVoMysql.setPublishTime(info.getCreatetime() == null ? System.currentTimeMillis() : info.getCreatetime().getTime());

                newsInfoVoMysql.setOrgCode(sysUserInfo.getSysOrgInfo().getManOrgCode());
                newsInfoVoMysql.setIsTop((byte) 0);
                newsInfoVoMysql.setIsArchived(0);
                newsInfoVoMysql.setIsDelete(info.getDelFlag());
                newsInfoVoMysql.setInfoSort(999);
                newsInfoVoMysql.setIsShield(info.getDelFlag());
                newsInfoVoMysql.setIsHidden(info.getDelFlag());
                newsInfoVoMysql.setInfoGoodCount(0);
                newsInfoVoMysql.setInfoCommentCount(0);
                newsInfoVoMysql.setTenantCode("856966d06b96418fab2e430d71a1e84e");//租户CODE
                mySQLSubList.add(newsInfoVoMysql);
            }

            // 插入新闻数据到MySQL库中
            boolean insertFlag = newsService.insertMySQLSubList(mySQLSubList);
            if (!insertFlag) {
                result = false;
            }
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        String param = "&nbsp;\n" +
                "<p style=\"text-indent:31.5pt;\">\n" +
                "\t<span style=\"font-size:15pt;font-family:仿宋_GB2312;\">1</span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\">月<span>24</span>日，</span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\">宁夏中移通信技术工程有限公司<span>2013</span>年工作会议暨首届三次职工代表大会在银川召开，公司三级副以上经理人员、会员代表、职工代表、先进个人及管理岗以上员工<span>90</span>人参加了会议。会议全面总结了<span>2012</span>年的主要工作，分析了当前面临的竞争形势和发展环境，并结合区公司工作会议精神对<span>2013</span>年各项工作进行了安排部署。<span></span></span> \n" +
                "</p>\n" +
                "<p style=\"text-indent:30pt;\">\n" +
                "\t<span style=\"font-size:15pt;font-family:仿宋_GB2312;\"></span>\n" +
                "</p>\n" +
                "<p style=\"text-indent:30pt;\">\n" +
                "\t<span style=\"font-size:15pt;font-family:仿宋_GB2312;\"></span>\n" +
                "</p>\n" +
                "<p style=\"text-indent:30pt;\">\n" +
                "\t<span style=\"font-size:15pt;font-family:仿宋_GB2312;\"></span>\n" +
                "</p>\n" +
                "<p style=\"text-indent:30pt;\">\n" +
                "\t<span style=\"font-size:15pt;font-family:仿宋_GB2312;\"></span>\n" +
                "</p>\n" +
                "<p style=\"text-indent:30pt;\">\n" +
                "\t<span style=\"font-size:15pt;font-family:仿宋_GB2312;\"></span>\n" +
                "</p>\n" +
                "<p style=\"text-indent:32.25pt;\">\n" +
                "\t<span style=\"font-size:15pt;font-family:仿宋_GB2312;\"></span>\n" +
                "</p>\n" +
                "<p style=\"text-indent:32.25pt;\">\n" +
                "\t<span style=\"font-size:15pt;font-family:仿宋_GB2312;\"></span>\n" +
                "</p>\n" +
                "<p style=\"text-indent:32.25pt;\">\n" +
                "\t<span style=\"font-size:15pt;font-family:仿宋_GB2312;\"></span>\n" +
                "</p>\n" +
                "<p style=\"text-indent:32.25pt;\">\n" +
                "\t<span style=\"font-size:15pt;font-family:仿宋_GB2312;\">会议传达了区公司</span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\">党组书记、董事长、总经理彭晓川在大会上题为</span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\">《提振信心 负重奋进 加快改革创新 推进创业布局 <span>&nbsp;</span>努力推进公司战略转型和持续健康发展》</span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\">的工作报告<span>及<span>2013</span>年工作会议暨三届五次会员代表职工代表大会的会议精神，</span>重点学习了区公司<span>2013</span>年“一三五六”的工作思路。<span>并结合</span>中移工程公司相关工作内容进行了解读。要求全体干部员工以区公司<span>2013</span>年工作会会议精神为指导，</span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\">明确任务、提振信心、鼓足干劲，努力实现</span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\">“四个突破”，一是要统一思想，树立信心，在增强深化改革的紧迫感和责任感上取得突破；二是增强大局意识，力争在业务转型发展上取得新突破；三是增强服务支撑意识，力争在解决企业转型发展的焦点、难点上取得新突破；四是增强责任意识，在企业持续发展中有新突破。<span></span></span> \n" +
                "</p>\n" +
                "<p style=\"text-indent:32.25pt;\">\n" +
                "\t<span style=\"font-size:15pt;font-family:仿宋_GB2312;\">会上，安昊龙总经理作了题为《攻坚克难促改革 稳中求进谋发展<span><span>&nbsp; </span></span>为推进公司业务转型和管理能力持续提升而努力奋斗》的工作报告。报告全面回顾了<span>2012</span>年取得的成绩，客观分析了当前所面临的形势和存在的主要问题，明确了<span>2013</span>年的工作重点和目标任务，</span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\">安排部署了<span>2013</span>年的重点工作。《报告》紧密结合区公司工作部署，指出了全年工作的总体要求：</span><span style=\"font-size:15pt;color:windowtext;font-family:仿宋_GB2312;\">贯彻落实党的十八大精神和区公司<span>2013</span>年工作会议精神，立足“有用、有为、有贡献”的企业发展定位，坚持稳中求进，突出企业价值，积极推进业务改革，全面落实管理提升，统筹安排各项工作，确保各项生产经营目标顺利实现，推动企业持续健康发展。</span><span style=\"font-size:15pt;color:windowtext;font-family:仿宋_GB2312;\"></span> \n" +
                "</p>\n" +
                "<p style=\"text-indent:29.4pt;\">\n" +
                "\t<span style=\"font-size:15pt;font-family:仿宋_GB2312;\">党委书记、工会主席侯随宁在首届三次职代会上作了题为<span>《夯实基础 与时俱进 为推动公司深化改革建功立业》的工会工作报告，报告围绕一年来加强工会组织建设、切实履行工作职责、扎实开展劳动竞赛和“走听转”活动，加快推进班组建设等工作情况进行了全面地总结和回顾，结合上级工会、公司党委的工作要求，提出了<span>2013</span>年工会工作的总体思路和主要任务。动员全体干部员工在新一年的工作中，围绕公司全面转型的经营任务，肩负起为维护员工排忧解难和维护员工权益的义务，为公司全面转型和进一步夯实企业整体业务实力做出应有的贡献。<span></span></span></span> \n" +
                "</p>\n" +
                "<p style=\"text-indent:31.5pt;text-align:left;\" align=\"left\">\n" +
                "\t<span style=\"font-size:15pt;font-family:仿宋_GB2312;\">公司党委委员、总经理助理郭延东在大会上就分管的维护和系统集成业务工作做了专题发言，针对业务发展过程中存在的问题进行了认真查找和剖析<span>,</span>并针对两项业务发展提出了</span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\"> “坚持一个中心，开展两项活动，突出三个重点，抓好四项工作”的<span>具体工作安排及要求，动员</span></span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\">各维护单位要始终坚持“安全第一、常备不懈、预防为主、全力抢险”的工作方针，充分认识通信维护工作的重要性，正视存在的问题，</span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\">团结一心，牢记使命，恪尽职守，发扬成绩，再接再厉，以更加良好的精神面貌，更加扎实的工作作风，更加有力的工作措施，全面推动企业转型和业务发展。<span></span></span> \n" +
                "</p>\n" +
                "<p style=\"text-indent:28.5pt;\">\n" +
                "\t<span style=\"font-size:15pt;font-family:仿宋_GB2312;\">会议中，综合部、工会经费审查委员会分别进行了业务招待</span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\">费使用情况报告和工会经费审查报告，通报了<span>2012</span>年度干部考评情况。大会还表彰<span>了中移工程公司<span>2012</span>年度的先进集体、工会先进班组、先进个人、优秀驾驶员，以及在维护质量提升劳动竞赛中表现突出的参赛单位。<span></span></span></span> \n" +
                "</p>\n" +
                "<p style=\"text-indent:29.25pt;\">\n" +
                "\t<span style=\"font-size:15pt;font-family:仿宋_GB2312;\">同时要求公司全体干部员工要充分认识当前公司在维护业务改革中面临的形势和任务，切实加强加快企业发展的紧迫感和责任感，抢抓继续保持公司管理工作日臻完善、服务保障水平不断提升的良好势头，以发扬风格、不怕困难的工作作风和锐意进取的精神状态，围绕做好“战略转型、改革创新、反腐倡廉”三篇文章，有计划、有方案、有步骤地推进维护业务改革。</span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\">希望公司全体干部员工认真学习，深刻领会，</span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\">以更加饱满的热情、更加坚定的信心、更加务实的作风、更加求实的精神，</span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\">再接再厉，发扬成绩，为促进公司业务转型和管理能力提升，为促进公司健康、稳定发展做出新的更大的贡献。<span></span></span> \n" +
                "</p>\n" +
                "<p style=\"text-indent:30pt;\">\n" +
                "\t<span style=\"font-size:15pt;font-family:仿宋_GB2312;\">此次会议是公司实现全面融合，积极推进企业深化改革阶段召开的一次重要会议，</span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\">为全体干部员工在新的一年里指明了发展方向，明确了工作思路，</span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\">为全面推进公司业务转型和实现管理能力持续提升，提振了</span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\">信心，坚定了决心，积蓄了力量，为确保公司稳定持续健康发展奠定了坚实基础。 </span><span style=\"font-size:15pt;font-family:仿宋_GB2312;\"></span>\n" +
                "</p>";
        String text = delHTMLTag(param);

        String words = subStringText(text);

        System.out.println(words);


    }

    private static String subStringText(String text) {
        if (text.length() >130) {
            return text.substring(0,130) ;
        } else {
            return text ;
        }
    }

    public static String delHTMLTag(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签
        htmlStr = htmlStr.replaceAll("&nbsp;", ""); //过滤html标签

        return htmlStr.trim(); //返回文本字符串
    }

}

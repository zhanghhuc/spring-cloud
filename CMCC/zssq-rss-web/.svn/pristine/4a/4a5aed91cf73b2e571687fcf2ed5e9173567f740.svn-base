package com.zssq.rss.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zssq.constants.BlogConstants;
import com.zssq.constants.MblogConstants;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.news.model.NewsRssQuery;
import com.zssq.news.service.NewsThridService;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.pojo.RssReturnPo;
import com.zssq.rss.command.RssCommand;
import com.zssq.rss.util.RssConstants;
import com.zssq.service.BlogThirdService;
import com.zssq.service.ISysUserService;
import com.zssq.service.MblogThridService;
import com.zssq.vo.BlogThirdVO;
/**
 * 
 * @ClassName: RssController  
 * @Description: rss 列表  
 * @author sry  
 * @date 2017年4月10日  
 *
 */
@Controller
@RequestMapping("/rss")
public class RssController {
	
private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private BlogThirdService blogThirdService;
	
	@Autowired
	private NewsThridService newsThridService;
	
	@Autowired
	private MblogThridService mBlogThirdService;
	
	@Autowired
	private ISysUserService sysUserService;
	
	@RequestMapping("/list")  
	@ResponseBody
    public void list(HttpServletRequest request,HttpServletResponse response,RssCommand rssCommand) throws BusinessException{  
        System.out.println(rssCommand.toString());
        Message m = null;
        ResultJSON rj = new ResultJSON();
        Integer showSize = 10;
       
        try {
        	List<RssReturnPo> lrro = null;
        	if(rssCommand.getRssType()==RssConstants.RSS_TYPE_MICROBLOG_PERSONAL){
        		//个人 微博
        		
        		lrro = mBlogThirdService.getRssMblogList("fanhaibin@hq.cmcc", "", MblogConstants.MBLOG_DEPEND_SELF, showSize);
        	}else if(rssCommand.getRssType()==RssConstants.RSS_TYPE_MICROBLOG_TEAM){
        		//班组 微博
        		lrro = mBlogThirdService.getRssMblogList("fanhaibin@hq.cmcc", "4d5f93f37e6847f69d90d6217c11a2b9", MblogConstants.MBLOG_DEPEND_GROUP, showSize);
        	}else if(rssCommand.getRssType()==RssConstants.RSS_TYPE_BLOG_PERSONAL){
        		//个人 博客
        		BlogThirdVO blogThirdVO = new BlogThirdVO();
        		blogThirdVO.setUserCode("fanhaibin@hq.cmcc");
        		blogThirdVO.setShowSize(showSize);
        		blogThirdVO.setBlogDepend(BlogConstants.BLOG_DEPEND_USER);
        		lrro = blogThirdService.getRssBlogList(blogThirdVO);
        	}else if(rssCommand.getRssType()==RssConstants.RSS_TYPE_BLOG_TEAM){
        		//班组 博客
        		BlogThirdVO blogThirdVO = new BlogThirdVO();
        		blogThirdVO.setTeamCode("4d5f93f37e6847f69d90d6217c11a2b9");
        		blogThirdVO.setShowSize(showSize);
        		blogThirdVO.setBlogDepend(BlogConstants.BLOG_DEPEND_TEAM);
        		lrro = blogThirdService.getRssBlogList(blogThirdVO);
        	}else if(rssCommand.getRssType()==RssConstants.RSS_TYPE_NEWS){
        		NewsRssQuery newsRssQuery = new NewsRssQuery();
        		newsRssQuery.setLimitStart(0);
        		newsRssQuery.setPageSize(showSize);
        		newsRssQuery.setOrgCode("02d8dffa8f0c416cba4702524asdfb12");
        		lrro = newsThridService.getNewsInfosRss(newsRssQuery);
        	}
        	if(lrro!=null){
        		for(RssReturnPo rp:lrro){
        			SysUserInfo tempSysUserInfo = sysUserService.selectByCode(rp.getAuthorCode());
        			if(tempSysUserInfo!=null){
        				rp.setAuthorName(tempSysUserInfo.getUserName());
        			}
        		}
        	}
        	String xmlStr = ProduceRss.cereateRss(rssCommand,lrro);
        	response.setCharacterEncoding("utf-8");
        	response.getWriter().println(xmlStr);
        	response.getWriter().flush();
		} 
        catch (Exception e) {
        	//log.debug();
        	e.printStackTrace();
		}
       // return rj;
        
	}
	
}

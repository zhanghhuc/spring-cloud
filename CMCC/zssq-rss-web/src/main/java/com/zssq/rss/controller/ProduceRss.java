package com.zssq.rss.controller;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.zssq.pojo.RssReturnPo;
import com.zssq.rss.command.RssCommand;

public class ProduceRss {
	
	 public static String cereateRss(RssCommand rssCommand,List<RssReturnPo> lrro){  
         try{
        	 Document doc = new Document(); //创建空白文档       
             Element root = new Element("rss"); //创建一个元素  
             doc.setRootElement(root); //将该元素做为根元素         
             Attribute  version = new Attribute("version","2.0"); //创建属性  
             root.setAttribute(version); //为product设置属性  
             Element channel = new Element("channel");  
             root.addContent(channel); //将product做为productsDetails的子元素                 
              //为product创建子元素，并将其content分别设为100.00,red  
             channel.addContent(new Element("title").setText(rssCommand.getTitle()));   
             channel.addContent(new Element("link").setText(rssCommand.getChannelUrl()));   
             channel.addContent(new Element("description").setText(rssCommand.getDes()));  
             channel.addContent(new Element("language").setText("en-us"));
             channel.addContent(new Element("image").addContent(new Element("url").setText("http://static.blog.csdn.net/images/logo.gif")));
             if(lrro!=null){
            	 for(RssReturnPo rrp:lrro){
                 	DateFormat format=DateFormat.getDateTimeInstance();
           	        String time=format.format(rrp.getCreateTime());
           	        Element item = new Element("item");  
           	        channel.addContent(item); //将product做为productsDetails的子元素         
           	        item.addContent(new Element("title").setText(rrp.getTitle()));   
           	        item.addContent(new Element("link").setText(rssCommand.getItemUrl()+rrp.getObjectCode()));      
           	        item.addContent(new Element("author").setText(rrp.getAuthorName()));
           	        item.addContent(new Element("pubDate").setText(time)); 
           	        item.addContent(new Element("description").setText(rrp.getDescription()));
           	        
           	        //item.addContent(new Element("enclosure").setAttribute("url", "http://www.bobopo.com/video/rss.mp3").setAttribute("type", "audio/mpeg").setAttribute("length", "16131450"));
           	       /* item.addContent(new Element("enclosure").setAttribute("url", "http://wx3.sinaimg.cn/large/6a5ce645ly1fecrljqlp9j20gt0j9q84.jpg").setAttribute("type", "jpeg/jpg"));
           	        item.addContent(new Element("image").addContent(new Element("url").setText("http://static.blog.csdn.net/images/logo.gif")));
           	        item.addContent(new Element("image").addContent(new Element("url").setText("http://static.blog.csdn.net/images/logo.gif")));*/

                  }
             }else{
            	 for(int i=0;i<5;i++){  
         	    	DateFormat format=DateFormat.getDateTimeInstance();
            	        String time=format.format(new Date());
            	        Element item = new Element("item");  
            	        channel.addContent(item); //将product做为productsDetails的子元素         
            	        item.addContent(new Element("title").setText("title"+i));   
            	        item.addContent(new Element("link").setText(rssCommand.getItemUrl()+i));      
            	        item.addContent(new Element("author").setText("author"+i));
            	        item.addContent(new Element("pubDate").setText("2017-4-27 14:33:03")); 
            	        item.addContent(new Element("description").setText("this is <b>bold</b>"));
            	        //item.addContent(new Element("enclosure").setAttribute("url", "http://www.bobopo.com/video/rss.mp3").setAttribute("type", "audio/mpeg").setAttribute("length", "16131450"));
            	        item.addContent(new Element("enclosure").setAttribute("url", "http://wx3.sinaimg.cn/large/6a5ce645ly1fecrljqlp9j20gt0j9q84.jpg").setAttribute("type", "jpeg/jpg"));
            	        item.addContent(new Element("image").addContent(new Element("url").setText("http://static.blog.csdn.net/images/logo.gif")));
            	        item.addContent(new Element("image").addContent(new Element("url").setText("http://static.blog.csdn.net/images/logo.gif")));
         	     }     
            	 return "";
             }
             Format format = Format.getPrettyFormat();     
             //format.setEncoding("UTF-8");// 设置xml文件的字符为UTF-8，解决中文问题     
             XMLOutputter xmlout = new XMLOutputter(format);     
             ByteArrayOutputStream bo = new ByteArrayOutputStream();     
             xmlout.output(doc, bo);     
             return bo.toString();
         }catch(Exception e){
        	 e.printStackTrace();
         }
		 return "";
        }  
    }

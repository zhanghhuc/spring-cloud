package com.zssq.news.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.NewsConstants;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.fastdfs.config.FastDfsConf;
import com.zssq.news.dao.pojo.NewsArchive;
import com.zssq.news.dao.pojo.NewsInfoContent;
import com.zssq.news.dao.pojo.NewsReply;
import com.zssq.news.model.NewsArchiveModel;
import com.zssq.news.model.NewsArchiveQuery;
import com.zssq.news.model.NewsCommentModel;
import com.zssq.news.newsvo.NewsArchiveListVO;
import com.zssq.news.newsvo.NewsArchiveVO;
import com.zssq.news.service.NewsArchiveService;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.PageBean;
import com.zssq.utils.file.FileHandler;
import com.zssq.utils.zip.ZipUtil;

/**
 * @author SharlaCheung
 * @ClassName: NewsCommentController
 * @Description: 新闻Controller
 * @date 2017年4月13日
 */
@Controller
@RequestMapping("/newsarchive")
public class NewsArchiveController {
	
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private NewsArchiveService newsArchiveService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysOrgService sysOrgService;
    @Autowired
    private FastDfsConf fastDfsConf;

    private List<String> usercodes;
    private List<String> orgcodes;
    List<String> filesList = null;

    
    /**
     * 
     * @Title: getNewsArchives  
     * @Description: 查询已归档新闻列表
     * @param newsArchiveListVO
     * @throws BusinessException    参数  
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/getNewsArchives")
    @ResponseBody
    public ResultJSON getNewsArchives(@RequireValid NewsArchiveListVO newsArchiveListVO) throws BusinessException {
        NewsArchiveQuery newsArchiveQuery = new NewsArchiveQuery();
        ResultJSON resJson = new ResultJSON();
        JSONObject json = null;
        JSONArray jsonArray = new JSONArray();
        JSONObject body = new JSONObject();
        try {
            List<String> userCodeList = null;
            if (null != newsArchiveListVO.getUserName() && !"".equals(newsArchiveListVO.getUserName())) {
                userCodeList = sysUserService.selectCodeByName(newsArchiveListVO.getUserName());
            }

            if (null != userCodeList && userCodeList.size() > 0) {
                newsArchiveQuery.setUserCodeList(userCodeList);
            } else if (null != newsArchiveListVO.getUserName() && !"".equals(newsArchiveListVO.getUserName())) {
                body.put("totalCount", 0);
                body.put("list", jsonArray);
                resJson.setBody(body);
                return resJson;
            }
            newsArchiveQuery.setPageNo(Integer.valueOf(newsArchiveListVO.getPageNo()));
            newsArchiveQuery.setPageSize(Integer.valueOf(newsArchiveListVO.getPageSize()));
            newsArchiveQuery.setLimitStart(newsArchiveQuery.getPageNo() * newsArchiveQuery.getPageSize());
            if (null != newsArchiveListVO.getOrgCode() && !"".equals(newsArchiveListVO.getOrgCode())) {
                newsArchiveQuery.setOrgCode(newsArchiveListVO.getOrgCode());
            }
            newsArchiveQuery.setIsDelete(NewsConstants.NEWS_NO_DELETE);
            newsArchiveQuery.setIsShield(NewsConstants.NEWS_NO_SHIELD);

            if (null != newsArchiveListVO.getArchiveTitle() && !"".equals(newsArchiveListVO.getArchiveTitle())) {
                newsArchiveQuery.setArchiveTitle(newsArchiveListVO.getArchiveTitle());
            }
            if (null != newsArchiveListVO.getStartTime() && !"".equals(newsArchiveListVO.getStartTime())) {
                newsArchiveQuery.setStartTime(Long.valueOf(newsArchiveListVO.getStartTime()));
            }
            if (null != newsArchiveListVO.getEndTime() && !"".equals(newsArchiveListVO.getEndTime())) {
                newsArchiveQuery.setEndTime(Long.valueOf(newsArchiveListVO.getEndTime()));
            }
            //查询条件
            PageBean pageBean = newsArchiveService.getNewsArchives(newsArchiveQuery);
            //返回值封装
            List<NewsArchive> list = pageBean.getRecordList();
            //根据用户code列表获取用户集合
            if (list.size() > 0) {
                List<String> usercodes = new ArrayList<>();
                List<String> orgcodes = new ArrayList<>();
                for (NewsArchive newsArchive : list) {
                    usercodes.add(newsArchive.getCreatorCode());
                    orgcodes.add(newsArchive.getOrgCode());
                }
                List<SysUserInfo> userInfos = sysUserService.selectByCodes(usercodes);
                Map<String, SysUserInfo> userMap = generateUserInfoMap(userInfos);

                List<SysOrgInfo> sysOrgInfos = sysOrgService.selectByCodes(orgcodes);
                Map<String, SysOrgInfo> orgMap = generateOrgInfoMap(sysOrgInfos);
                for (int i = 0; i < list.size(); i++) {
                    json = new JSONObject();
                    NewsArchive temp = list.get(i);
                    json.put("archiveCode", temp.getArchiveCode());
                    json.put("archiveTitle", temp.getArchiveTitle());
                    json.put("archiveName", temp.getArchiveName());
                    json.put("archiveUrl", temp.getArchiveUrl());
                    json.put("createTime", temp.getCreateTime());
                    json.put("modifyTime", temp.getModifyTime());
                    json.put("creatorCode", temp.getCreatorCode());
                    SysUserInfo userInfo = userMap.get(temp.getCreatorCode());
                    json.put("creatorName", userInfo == null ? "" : userInfo.getUserName());
                    json.put("orgCode", temp.getOrgCode());
                    SysOrgInfo sysOrgInfo = orgMap.get(temp.getOrgCode());
                    json.put("orgName", sysOrgInfo == null ? "" : sysOrgInfo.getSysOrgName());
                    jsonArray.add(json);
                }
            }
            resJson = new ResultJSON("COMMON_200");
            body.put("totalCount", pageBean.getTotalCount());
            body.put("topTotal", pageBean.getNumPerPage());
            body.put("list", jsonArray);
            resJson.setBody(body);

            log.info("NewsArchiveController.page:获取新闻归档列表成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "获取新闻归档列表");
        }
        return resJson;
    }

    /**
     * 
     * @Title: addNewsArchives  
     * @Description: 新闻归档
     * @param newsArchiveVO
     * @param request
     * @param response
     * @throws BusinessException    参数  
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/addNewsArchives")
    @ResponseBody
    public ResultJSON addNewsArchives(@RequireValid NewsArchiveVO newsArchiveVO, HttpServletRequest request, HttpServletResponse response) throws BusinessException {
        ResultJSON resJson = null;
        JSONObject body = new JSONObject();
        String zipFileName = null;
        try {
//            zipFileName =   newsArchiveVO.getArchiveTitle() ;
//            if(null == zipFileName || "".equals(zipFileName.trim())){
//                throw BusinessException.build("NEWS_14002", "新闻归档参数archiveTitle错误");
//            }
            //获取用户信息
            SysUserInfo sysUserInfo = sysUserService.selectByCode(newsArchiveVO.getUserCode());
            String newsCodeList = newsArchiveVO.getNewsCodes();
            if (null != newsCodeList && !"".equals(newsCodeList)) {
                String[] codes = newsCodeList.split(",");
                for (int index = 0; index < codes.length; index++) {
                	// 获取新闻详情
                    NewsInfoContent newsInfoContent = newsArchiveService.generateNewsData(codes[index]);
                    if (null != newsInfoContent) {
                    	zipFileName = newsInfoContent.getInfoTitle();
                        //生成数据
                        JSONObject js = createNewsArchives(newsArchiveVO, newsInfoContent);
                        String data = "var data = " + js.toString();
                        //下载所有图片相关文件
                        downloadFiles(filesList, request, response);
                        //创建数据文件-data.js
//            String zipPath =request.getContextPath() ;
                        String zipPath = (request.getSession().getServletContext().getRealPath("/"));
                        String filePath = zipPath + "WEB-INF" + File.separator + "archivetemplate";

                        FileHandler.createFile(filePath + File.separator + "news" + File.separator + "js" + File.separator, "data", ".js", data);
                        //打包，上传到文件服务器
                        ZipUtil.zip(filePath, zipPath, zipFileName + ".zip");
                        String result = upload(zipPath + zipFileName + ".zip", request, response);
                        String url = urlAnalysis(result);

                        //更改新闻状态为已归档
                        NewsArchiveModel newsArchiveModel = generateNewsArchiveModel(newsInfoContent, url, sysUserInfo);
                        newsArchiveService.addNewsArchives(newsArchiveModel);
                        //删除本地文件
//                FileHandler.delFile(filePath,zipFileName,".zip") ;
                    } else {
                        throw BusinessException.build("NEWS_14002", "未隐藏新闻归档");
                    }
                }
                resJson = new ResultJSON("COMMON_200");
                resJson.setBody(body);
                log.info("NewsCommentController.addNewsInfo:新闻归档成功");
            } else {
                throw BusinessException.build("NEWS_14002", "待归档新闻编码");
            }

        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻归档");
        }
        return resJson;
    }

    /**
     * 
     * @Title: createNewsArchives  
     * @Description: 整理归档数据
     * @param newsArchiveVO
     * @param temp
     * @throws BusinessException    参数  
     * @return: JSONObject    返回类型
     */
    private JSONObject createNewsArchives(NewsArchiveVO newsArchiveVO, NewsInfoContent temp) throws BusinessException {
        JSONObject json = new JSONObject();
        try {
            String heardPhoto = null;
            collectCodes(temp);
            List<SysUserInfo> userInfos = sysUserService.selectByCodes(usercodes);
            Map<String, SysUserInfo> userMap = generateUserInfoMap(userInfos);
            List<SysOrgInfo> sysOrgInfos = sysOrgService.selectByCodes(orgcodes);
            Map<String, SysOrgInfo> orgMap = generateOrgInfoMap(sysOrgInfos);

            filesList = new ArrayList<>();

            json = new JSONObject();
            json.put("newsCode", temp.getNewsCode());
            json.put("infoTitle", temp.getInfoTitle());
            json.put("infoContentText", temp.getInfoContentText());
            json.put("infoContentHtml", temp.getInfoContentHtml());
            String files = getFileName(temp.getFileUrl());
            json.put("fileUrl", files);
            String fileUrl = temp.getFileUrl();
            if (null != fileUrl) {
                String[] urls = fileUrl.split(",");
                for (int index = 0; index < urls.length; index++) {
                    String str = urls[index];
                    if (null != str && !"".equals(str)) {
                        filesList.add(str);
                    }
                }
            }
            json.put("infoStatus", temp.getInfoStatus());
            json.put("infoOperatorCode", temp.getInfoOperatorCode());
            SysUserInfo userInfo = userMap.get(temp.getInfoOperatorCode());
            json.put("infoOperatorName", userInfo == null ? "" : userInfo.getUserName());
            if (null != userInfo) {
                heardPhoto = getFileName(userInfo.getHeadPortrait());
                if (null != userInfo.getHeadPortrait() && !"".equals(userInfo.getHeadPortrait())) {
                    filesList.add(userInfo.getHeadPortrait());
                }
            }
            json.put("headPortrait", heardPhoto);
            json.put("infoCreatorCode", temp.getInfoCreatorCode());
            json.put("collectNumber", temp.getCollectNumber());
            json.put("infoCommentCount", temp.getInfoCommentCount() == null ? 0 : temp.getInfoCommentCount());
            json.put("transmitNumber", temp.getTransmitNumber() == null ? 0 : temp.getTransmitNumber());
            json.put("isTop", temp.getIsTop());
            json.put("infoSort", temp.getInfoSort());
            json.put("isHidden", temp.getIsHidden());
            json.put("modifyTime", temp.getModifyTime());
            json.put("publishTime", temp.getPublishTime());

            List<NewsCommentModel> newsCommentModels = temp.getNewsCommentModels();
            JSONArray jsoncommentArray = new JSONArray();
            if (null != newsCommentModels && newsCommentModels.size() > 0) {
                JSONObject jsonComment = null;
                for (int index = 0; index < newsCommentModels.size(); index++) {
                    jsonComment = new JSONObject();
                    NewsCommentModel newsCommentModel = newsCommentModels.get(index);
                    jsonComment.put("id", newsCommentModel.getId());
                    jsonComment.put("infoCommentCode", newsCommentModel.getCommentCode());
                    jsonComment.put("newsCode", newsCommentModel.getNewsCode());
                    jsonComment.put("userCode", newsCommentModel.getUserCode());
                    SysUserInfo creator = userMap.get(newsCommentModel.getUserCode());
                    jsonComment.put("userName", creator == null ? "" : creator.getUserName());
                    if (null != creator) {
                        heardPhoto = getFileName(creator.getHeadPortrait());
                        if (null != creator.getHeadPortrait() && !"".equals(creator.getHeadPortrait())) {
                            filesList.add(creator.getHeadPortrait());
                        }
                    }
                    jsonComment.put("headPortrait", heardPhoto);
                    jsonComment.put("orgCode", newsCommentModel.getOrgCode());
                    SysOrgInfo sysOrgnfo = orgMap.get(newsCommentModel.getOrgCode());
                    jsonComment.put("orgName", sysOrgnfo == null ? "" : sysOrgnfo.getSysOrgName());
                    jsonComment.put("commentContent", newsCommentModel.getCommentContent());
                    jsonComment.put("commentReplyNum", newsCommentModel.getCommentReplyNum());
                    jsonComment.put("commentGoodCount", newsCommentModel.getCommentLikeNum());
                    jsonComment.put("isPraise", 0);
                    jsonComment.put("createTime", newsCommentModel.getCreateTime());
                    jsonComment.put("modifyTime", newsCommentModel.getModifyTime());

                    List<NewsReply> newsReplies = newsCommentModel.getNewsReplyList();
                    JSONArray jsonReplyArray = new JSONArray();
                    if (null != newsReplies && newsReplies.size() > 0) {
                        JSONObject jsonReply = null;
                        for (int step = 0; step < newsReplies.size(); step++) {
                            jsonReply = new JSONObject();
                            NewsReply newsReply = newsReplies.get(step);
                            jsonReply.put("replyCode", newsReply.getReplyCode());
                            jsonReply.put("userCode", newsReply.getUserCode());
                            jsonReply.put("replyContent", newsReply.getReplyContent());
                            SysUserInfo replyUser = userMap.get(newsReply.getUserCode());
                            jsonReply.put("userName", replyUser == null ? "" : replyUser.getUserName());
                            if (null != replyUser) {
                                heardPhoto = getFileName(replyUser.getHeadPortrait());
                                if (null != replyUser.getHeadPortrait() && !"".equals(replyUser.getHeadPortrait())) {
                                    filesList.add(replyUser.getHeadPortrait());
                                }
                            }
                            jsonReply.put("headPortrait", heardPhoto);
                            jsonReply.put("orgCode", newsReply.getOrgCode());
                            SysOrgInfo sysOrginfo = orgMap.get(newsReply.getOrgCode());
                            jsonReply.put("orgName", sysOrginfo == null ? "" : sysOrginfo.getSysOrgName());

                            jsonReply.put("revertPeopleCode", newsReply.getToReplyUserCode());
                            SysUserInfo revertUserInfo = userMap.get(newsReply.getToReplyUserCode());
                            jsonReply.put("revertPeopleName", revertUserInfo == null ? "" : revertUserInfo.getUserName());
                            jsonReply.put("revertHeadPortrait", revertUserInfo == null ? "" : revertUserInfo.getHeadPortrait());
                            jsonReply.put("revertOrgCode", newsReply.getToReplyOrgCode());
                            SysOrgInfo sysOrginforevert = orgMap.get(newsReply.getOrgCode());
                            jsonReply.put("revertOrgName", sysOrginforevert == null ? "" : sysOrginforevert.getSysOrgName());
                            jsonReply.put("replyLikeNum", newsReply.getReplyLikeNum());
                            jsonReply.put("isPraise", 0);
                            jsonReply.put("replyReportNum", newsReply.getReplyReportNum());
                            jsonReply.put("createTime", newsReply.getCreateTime());
                            jsonReply.put("modifyTime", newsReply.getModifyTime());
                            jsonReplyArray.add(jsonReply);
                        }
                    }
                    jsonComment.put("jsonReplyArray", jsonReplyArray);
                    jsoncommentArray.add(jsonComment);
                }
            }
            json.put("jsoncommentArray", jsoncommentArray);

        } catch (Exception e) {
            e.printStackTrace();
            throw BusinessException.build("NEWS_14002", "新闻归档");
        }
        return json;
    }

    /**
     * 
     * @Title: upload  
     * @Description: 上传文件到fastDfs
     * @param zipPath
     * @param request
     * @param response
     * @throws IllegalStateException
     * @throws IOException    参数  
     * @return: String    返回类型
     */
    //    @RequestMapping("/upload")
    public String upload(String zipPath, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {

        String resultStr = "";
        // 创建可关闭的httpClient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        File targetFile = new File(zipPath);
        try {

            // 创建post传输方法
            HttpPost post = new HttpPost(fastDfsConf.getFastUrl());//BaseConstants.FASTDFS_FILE_DOWNLOAD_URL
//            String path = request.getSession().getServletContext().getRealPath("upload");

            // 创建浏览器传输模式
            MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            // 设置模式编码
            builder.setCharset(CharsetUtils.get("UTF-8"));

//            String myFileName = file.getOriginalFilename();
            String myFileName = "myFileNametest.zip";
            //如果名称不为“”,说明该文件存在，否则说明该文件不存在
            if (myFileName.trim() != "") {
//                System.out.println(myFileName);
//                targetFile = new File(path, myFileName);
//                if (!targetFile.exists()) {
//                    targetFile.mkdirs();
//                }
//                // 创建临时文件
//                file.transferTo(targetFile);
                // 把文件放到流对象FileBody中
                FileBody fileBody = new FileBody(targetFile);
                // 将文件信息放到模式中
                builder.addPart("multipartFile" + System.currentTimeMillis(), fileBody);

                HttpEntity reqEntity = builder.build();
                post.setEntity(reqEntity);
                // 发送请求，并获取响应
                CloseableHttpResponse res = httpclient.execute(post);
                try {
                    if (org.apache.http.HttpStatus.SC_OK == res.getStatusLine().getStatusCode()) {
                        HttpEntity entitys = res.getEntity();
                        if (null != entitys) {
                            resultStr += EntityUtils.toString(entitys, Charset.forName("UTF-8"));
                        }
                        EntityUtils.consume(entitys);
                    }
                } finally {
                    // 关闭响应
                    res.close();
                }
            }
        } finally {
            // 关闭http连接
            httpclient.close();
            if (null != targetFile) {
                targetFile.delete();
            }
        }

        return resultStr;
    }

    /**
     * 
     * @Title: downloadFiles  
     * @Description: 新闻归档时下载相关资源
     * @param filesList
     * @param request
     * @param response
     * @throws BusinessException    参数  
     * @return: boolean    返回类型
     */
	private boolean downloadFiles(List<String> filesList, HttpServletRequest request, HttpServletResponse response) throws BusinessException {
        try {
            if (null != filesList && filesList.size() > 0) {
                for (String fileUrl : filesList) {
//                    downloads("http://172.16.126.187:8081/zssq-fastdfs-web/file/download?url="+fileUrl+"&filefileName=dfaff",request) ;
                    downloads(fastDfsConf.getDownloadUrl() + fileUrl, request);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


//    public  ResponseEntity<byte[]> download(String filePath, HttpServletRequest request) throws IOException {
//
//        String resultStr = "";
//        // 创建可关闭的httpClient
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        File targetFile = new File(filePath);
//        try {
//
//            // 创建post传输方法
//            HttpPost post = new HttpPost(fastDfsConf.getFastUrl());//BaseConstants.FASTDFS_FILE_DOWNLOAD_URL
//            String path = request.getSession().getServletContext().getRealPath("upload");
//
//            // 创建浏览器传输模式
//            MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//            // 设置模式编码
//            builder.setCharset(CharsetUtils.get("UTF-8"));
//
////            String myFileName = file.getOriginalFilename();
//            String myFileName = "myFileNametest.zip";
//            //如果名称不为“”,说明该文件存在，否则说明该文件不存在
//            if (myFileName.trim() != "") {
////                System.out.println(myFileName);
////                targetFile = new File(path, myFileName);
////                if (!targetFile.exists()) {
////                    targetFile.mkdirs();
////                }
////                // 创建临时文件
////                file.transferTo(targetFile);
//                // 把文件放到流对象FileBody中
//                FileBody fileBody = new FileBody(targetFile);
//                // 将文件信息放到模式中
//                builder.addPart("multipartFile" + System.currentTimeMillis(), fileBody);
//
//                HttpEntity reqEntity = builder.build();
//                post.setEntity(reqEntity);
//                // 发送请求，并获取响应
//                CloseableHttpResponse res = httpclient.execute(post);
//                try {
//                    if (org.apache.http.HttpStatus.SC_OK == res.getStatusLine().getStatusCode()) {
//                        HttpEntity entitys = res.getEntity();
//                        if (null != entitys) {
//                            resultStr += EntityUtils.toString(entitys, Charset.forName("UTF-8"));
//                        }
//                        EntityUtils.consume(entitys);
//                    }
//                } finally {
//                    // 关闭响应
//                    res.close();
//                }
//            }
//        } finally {
//            // 关闭http连接
//            httpclient.close();
//            if (null != targetFile) {
//                targetFile.delete();
//            }
//        }
//
//        return resultStr;
//        URL url = new java.net.URL(path);
//        URLConnection conn = url.openConnection();
//        InputStream is = conn.getInputStream();
//
//        String fileName = path.substring(path.lastIndexOf("/"));
//        String tempPath = request.getSession().getServletContext().getRealPath("upload");
//        File file = new File(tempPath, fileName);
//
//        OutputStream os = new FileOutputStream(file);
//        int bytesRead = 0;
//        byte[] buffer = new byte[8192];
//        while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
//            os.write(buffer, 0, bytesRead);
//        }
//        os.close();
//        is.close();
//
//        HttpHeaders headers = new HttpHeaders();
//        //String fileName=new String("EasyUI+1.3中文帮助手册.chm".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
//        fileName = processFileName(request, fileName);
//        headers.setContentDispositionFormData("attachment", fileName);
//        //headers.set(javax.ws.rs.core.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        byte[] by = FileUtils.readFileToByteArray(file);
////        file.delete();
//        return new ResponseEntity<byte[]>(by, headers, HttpStatus.OK);
//    }

    public boolean downloads(String path, HttpServletRequest request) throws IOException {
//        String path="http://172.16.0.110:8888/group1/M00/00/00/rBB_jFb1BtCAPiMOAAAIR40Azqo554.txt";
        try {
            URL url = new java.net.URL(path);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();

            String fileName = path.substring(path.lastIndexOf("/"));
            String tempPath = request.getSession().getServletContext().getRealPath("/");
            tempPath = tempPath + "WEB-INF" + File.separator + "archivetemplate" + File.separator + "news" + File.separator + "sta_files" + File.separator;
            File file = new File(tempPath, fileName);

            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
//        HttpHeaders headers = new HttpHeaders();
//        //String fileName=new String("EasyUI+1.3中文帮助手册.chm".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
//        fileName = processFileName(request, fileName);
//        headers.setContentDispositionFormData("attachment", fileName);
//        //headers.set(javax.ws.rs.core.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        byte[] by = FileUtils.readFileToByteArray(file);
////        file.delete();
//        return new ResponseEntity<byte[]>(by, headers, HttpStatus.OK);
    }

    /**
     * @Title: processFileName
     * @Description: ie, chrom, firfox下处理文件名显示乱码
     */
    public String processFileName(HttpServletRequest request, String fileNames) {
        String codedfilename = null;
        try {
            String agent = request.getHeader("USER-AGENT");
            System.out.println(agent);

            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident") || null != agent && -1 != agent.indexOf("Edge")) {// ie

                String name = java.net.URLEncoder.encode(fileNames, "UTF8");

                codedfilename = name;
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等


                codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codedfilename;
    }

    /**
     * 
     * @Title: generateNewsArchiveModel  
     * @Description: 拼装更改新闻状态为归档时的条件
     * @param newsInfoContent
     * @param url
     * @param sysUserInfo
     * @return: NewsArchiveModel    返回类型
     */
    private NewsArchiveModel generateNewsArchiveModel(NewsInfoContent newsInfoContent, String url, SysUserInfo sysUserInfo) {
        NewsArchiveModel newsArchiveModel = new NewsArchiveModel();
        long currentTime = System.currentTimeMillis();
        newsArchiveModel.setNewsCodes(newsInfoContent.getNewsCode());
        newsArchiveModel.setArchiveTitle(newsInfoContent.getInfoTitle());
        newsArchiveModel.setArchiveCode(UUIDHelper.getUUID());
        newsArchiveModel.setArchiveName(newsInfoContent.getInfoTitle()+newsInfoContent.getNewsCode() + ".zip");
        newsArchiveModel.setArchiveUrl(url);
        newsArchiveModel.setCreateTime(currentTime);
        newsArchiveModel.setModifyTime(currentTime);
        newsArchiveModel.setCreatorCode(sysUserInfo.getUserCode());
        newsArchiveModel.setOrgCode(sysUserInfo.getManageOrgInfo().getManOrgCode());
        newsArchiveModel.setIsDelete(0);
        newsArchiveModel.setIsShield(0);
        newsArchiveModel.setTenantCode(sysUserInfo.getTenantCode());
        return newsArchiveModel;
    }

    private Map<String, SysUserInfo> generateUserInfoMap(List<SysUserInfo> userInfos) {
        Map<String, SysUserInfo> users = new HashMap<String, SysUserInfo>();
        for (SysUserInfo sysUserInfo : userInfos) {
            if (null != sysUserInfo) {
                users.put(sysUserInfo.getUserCode(), sysUserInfo);
            }
        }
        return users;
    }

    private Map<String, SysOrgInfo> generateOrgInfoMap(List<SysOrgInfo> sysOrgInfos) {
        Map<String, SysOrgInfo> orgInfos = new HashMap<String, SysOrgInfo>();
        for (SysOrgInfo sysOrgInfo : sysOrgInfos) {
            if (null != sysOrgInfo) {
                orgInfos.put(sysOrgInfo.getManOrgCode(), sysOrgInfo);
            }
        }
        return orgInfos;
    }

    private void collectCodes(NewsInfoContent newsInfoContent) {
        usercodes = new ArrayList<>();
        orgcodes = new ArrayList<>();
        Set<String> userSet = new HashSet<>();
        Set<String> orgSet = new HashSet<>();
        userSet.add(newsInfoContent.getInfoOperatorCode());
        userSet.add(newsInfoContent.getInfoOperatorCode());
        orgSet.add(newsInfoContent.getOrgCode());
        List<NewsCommentModel> newsCommentList = newsInfoContent.getNewsCommentModels();
        if (null != newsCommentList && newsCommentList.size() > 0) {
            for (NewsCommentModel newsCommentModel : newsCommentList) {
                userSet.add(newsCommentModel.getUserCode());
                userSet.add(newsCommentModel.getRevertPeopleCode());
                orgSet.add(newsCommentModel.getOrgCode());
                List<NewsReply> newsReplies = newsCommentModel.getNewsReplyList();
                if (null != newsReplies && newsReplies.size() > 0) {
                    for (NewsReply newsReply : newsReplies) {
                        userSet.add(newsReply.getUserCode());
                        userSet.add(newsReply.getToReplyUserCode());
                        orgSet.add(newsReply.getOrgCode());
                        orgSet.add(newsReply.getToReplyOrgCode());
                    }
                }
            }
        }

        for (String userCode : userSet) {
            usercodes.add(userCode);
        }
        for (String orgCode : orgSet) {
            orgcodes.add(orgCode);
        }
    }

    /**
     * 
     * @Title: urlAnalysis  
     * @Description: 解析上传文件后返回的返回值，获取URL
     * @param jsonUrl
     * @throws BusinessException    参数  
     * @return: String    返回类型
     */
    public String urlAnalysis(String jsonUrl) throws BusinessException {
        JSONObject jsonObject = JSONObject.parseObject(jsonUrl);
        Object object = jsonObject.get("body");
        JSONObject body = JSONObject.parseObject(object.toString());

        Object url = body.get("url");
        return url.toString();
    }


    private String getFileName(String fileUrl) {
        try {
            String fileName = "";
            if (null != fileUrl && !"".equals(fileUrl.trim())) {
                String[] urlList = fileUrl.split(",");
                for (int step = 0; step < urlList.length; step++) {
                    String url = urlList[step];
                    int index = url.lastIndexOf("/") + 1;
                    fileName = fileName + url.substring(index, url.length());
                }
            }
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
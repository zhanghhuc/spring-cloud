package com.zssq.news.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.*;
import com.zssq.dao.pojo.*;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.news.dao.pojo.NewsInfoComment;
import com.zssq.news.dao.pojo.NewsInfoContent;
import com.zssq.news.dao.pojo.NewsReply;
import com.zssq.news.model.*;
import com.zssq.news.newsvo.*;
import com.zssq.news.service.*;
import com.zssq.pojo.ResultJSON;
import com.zssq.search.service.SolrQueryService;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.service.RelationThirdDynamicService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.PageBean;
import com.zssq.vo.RelationDataVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SharlaCheung
 * @ClassName: NewsCommentController
 * @Description: PC端新闻Controller
 * @date 2017年4月13日
 */
@Controller
@RequestMapping("/newspc")
public class NewsController {
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private NewsInfoContentService newsInfoContentService;
    @Autowired
    private NewsCommentService newsCommentService;
    @Autowired
    private NewsReplyService newsReplyService;
    @Autowired
    private NewsInfoInformService newsInfoInformService;
    @Autowired
    private NewsCollectService newsCollectService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysOrgService sysOrgService;
    @Autowired
    private RelationThirdDynamicService relationThirdDynamicService;
    @Autowired
    private SolrQueryService solrQueryService;

    @SuppressWarnings("rawtypes")
	@Autowired
    protected KafkaProducerTemplate producerTeplate;


    /**
     * @param newsInfoListVO
     * @return 参数
     * @throws BusinessException
     * @Title: getNewsInfos
     * @Description: 新闻查询列表
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/getNewsInfos")
    @ResponseBody
    public ResultJSON getNewsInfos(@RequireValid NewsInfoListVO newsInfoListVO) throws BusinessException {
        NewsQuery newsQuery = new NewsQuery();
        ResultJSON resJson = null;
        JSONObject json = null;
        JSONArray jsonArray = new JSONArray();
        JSONObject body = new JSONObject();
        try {
            newsQuery.setUserCode(newsInfoListVO.getUserCode());
            newsQuery.setPageNo(Integer.valueOf(newsInfoListVO.getPageNo()));
            newsQuery.setPageSize(Integer.valueOf(newsInfoListVO.getPageSize()));
            newsQuery.setLimitStart(newsQuery.getPageNo() * newsQuery.getPageSize());
            newsQuery.setOrgCode(newsInfoListVO.getOrgCode());
            newsQuery.setIsHidden(NewsConstants.NEWS_NO_HIDDEN);//只查询未被隐藏的新闻
            newsQuery.setIsDelete(NewsConstants.NEWS_NO_DELETE);//只查询未被删除的新闻
            newsQuery.setIsShield(NewsConstants.NEWS_NO_SHIELD);
            newsQuery.setInfoStatus(NewsConstants.NEWS_STATUS_PUBLISHED);
            //查询条件
            PageBean pageBean = newsInfoContentService.getNewsInfosPc(newsQuery);
            //返回值封装
            List<NewsInfoContent> list = pageBean.getRecordList();
            //根据用户code列表获取用户集合
            if (list.size() > 0) {
                List<String> usercodes = new ArrayList<>();
                for (NewsInfoContent newsInfoContent : list) {
                    usercodes.add(newsInfoContent.getInfoOperatorCode());
                }
                List<SysUserInfo> userInfos = sysUserService.selectByCodes(usercodes);
                Map<String, SysUserInfo> userMap = generateUserInfoMap(userInfos);
                for (int i = 0; i < list.size(); i++) {
                    json = new JSONObject();
                    NewsInfoContent temp = list.get(i);
                    json.put("newsCode", temp.getNewsCode());
                    json.put("infoTitle", temp.getInfoTitle());
                    json.put("infoContentText", temp.getInfoContentText());
                    json.put("infoContentHtml", temp.getInfoContentHtml());
                    json.put("fileUrl", temp.getFileUrl());
                    json.put("infoStatus", temp.getInfoStatus());
                    json.put("infoOperatorCode", temp.getInfoOperatorCode());
                    SysUserInfo userInfo = userMap.get(temp.getInfoOperatorCode());
                    json.put("infoOperatorName", userInfo == null ? "" : userInfo.getUserName());
                    json.put("orgLevel", temp.getOrgLevel());
                    json.put("orgCode", temp.getOrgCode());
                    json.put("isTop", temp.getIsTop());
                    json.put("infoSort", temp.getInfoSort());
//					json.put("isHidden",temp.getIsHidden());
                    json.put("infoGoodCount", temp.getInfoGoodCount() == null ? 0 : temp.getInfoGoodCount());
                    json.put("isPraiseGood", temp.getIsPraise());
                    json.put("infoCommentCount", temp.getInfoCommentCount() == null ? 0 : temp.getInfoCommentCount());
                    json.put("collectNumber", temp.getCollectNumber());
                    json.put("isCollect", temp.getIsCollect());
                    json.put("transmitNumber", temp.getTransmitNumber()==null?0:temp.getTransmitNumber());
                    json.put("createTime", temp.getCreateTime());
                    json.put("modifyTime", temp.getModifyTime());
                    json.put("publishTime", temp.getPublishTime());
                    jsonArray.add(json);
                }
            }
            resJson = new ResultJSON("COMMON_200");
            body.put("totalCount", pageBean.getTotalCount());
            body.put("list", jsonArray);
            resJson.setBody(body);

            log.info("NewsCommentController.page:获取新闻列表成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw BusinessException.build("NEWS_14002", "获取新闻列表");
        }
        return resJson;
    }

    /**
     * @param newsCollectListVO
     * @return 参数
     * @throws BusinessException
     * @Title: getUserCollectNews
     * @Description: 我的收藏--新闻列表
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/getUserCollectNews")
    @ResponseBody
    public ResultJSON getUserCollectNews(@RequireValid NewsCollectListVO newsCollectListVO) throws BusinessException {
        NewsQuery newsQuery = new NewsQuery();
        ResultJSON resJson = null;
        JSONObject json = null;
        JSONArray jsonArray = new JSONArray();
        JSONObject body = new JSONObject();
        try {
            SysUserInfo sysUser = sysUserService.selectByCode(newsCollectListVO.getUserCode());
            newsQuery.setUserCode(newsCollectListVO.getUserCode());
            newsQuery.setPageNo(Integer.valueOf(newsCollectListVO.getPageNo()));
            newsQuery.setPageSize(Integer.valueOf(newsCollectListVO.getPageSize()));
            newsQuery.setLimitStart(newsQuery.getPageNo() * newsQuery.getPageSize());
            newsQuery.setOrgCode(sysUser.getManageOrgInfo().getManOrgCode());
            newsQuery.setIsHidden(NewsConstants.NEWS_NO_HIDDEN);//只查询未被隐藏的新闻
            newsQuery.setIsDelete(NewsConstants.NEWS_NO_DELETE);//只查询未被删除的新闻
            newsQuery.setIsShield(NewsConstants.NEWS_NO_SHIELD);
            //查询条件
            PageBean pageBean = newsInfoContentService.getUserCollectNewsPc(newsQuery);
            //返回值封装
            List<NewsInfoContent> list = pageBean.getRecordList();
            //根据用户code列表获取用户集合
            if (list.size() > 0) {
                List<String> usercodes = new ArrayList<>();
                for (NewsInfoContent newsInfoContent : list) {
                    usercodes.add(newsInfoContent.getInfoOperatorCode());
                }
                List<SysUserInfo> userInfos = sysUserService.selectByCodes(usercodes);
                Map<String, SysUserInfo> userMap = generateUserInfoMap(userInfos);
                for (int i = 0; i < list.size(); i++) {
                    json = new JSONObject();
                    NewsInfoContent temp = list.get(i);
                    json.put("newsCode", temp.getNewsCode());
                    json.put("infoTitle", temp.getInfoTitle());
                    json.put("infoContentText", temp.getInfoContentText());
                    json.put("infoContentHtml", temp.getInfoContentHtml());
                    json.put("fileUrl", temp.getFileUrl());
                    json.put("infoStatus", temp.getInfoStatus());
                    json.put("infoOperatorCode", temp.getInfoOperatorCode());
                    SysUserInfo userInfo = userMap.get(temp.getInfoOperatorCode());
                    json.put("infoOperatorName", userInfo == null ? "" : userInfo.getUserName());
                    json.put("orgLevel", temp.getOrgLevel());
                    json.put("orgCode", temp.getOrgCode());
                    json.put("isTop", temp.getIsTop());
                    json.put("infoSort", temp.getInfoSort());
//					json.put("isHidden",temp.getIsHidden());
                    json.put("infoGoodCount", temp.getInfoGoodCount() == null ? 0 : temp.getInfoGoodCount());
                    json.put("isPraiseGood", temp.getIsPraise());
                    json.put("infoCommentCount", temp.getInfoCommentCount() == null ? 0 : temp.getInfoCommentCount());
                    json.put("collectNumber", temp.getCollectNumber());
                    json.put("isCollect", temp.getIsCollect());
                    json.put("transmitNumber", temp.getTransmitNumber() == null ? 0 : temp.getTransmitNumber());
                    json.put("createTime", temp.getCreateTime());
                    json.put("modifyTime", temp.getModifyTime());
                    json.put("publishTime", temp.getPublishTime());
                    jsonArray.add(json);
                }
            }
            resJson = new ResultJSON("COMMON_200");
            body.put("totalCount", pageBean.getTotalCount());
            body.put("list", jsonArray);
            resJson.setBody(body);

            log.info("NewsCommentController.getUserCollectNews:获取新闻列表成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "获取新闻列表");
        }
        return resJson;
    }

    /**
     * @param newsBaseVo
     * @return 参数
     * @throws BusinessException
     * @Title: getNewsInfoDetail
     * @Description: 新闻明细
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/getNewsInfoDetail")
    @ResponseBody
    public ResultJSON getNewsInfoDetail(@RequireValid NewsBaseVo newsBaseVo) throws BusinessException {
        NewsQuery newsQuery = new NewsQuery();
        ResultJSON resJson = null;
        JSONObject json = new JSONObject();
        try {
//			newsQuery.setIsHidden(NewsConstants.NEWS_NO_HIDDEN);//只查询未被隐藏的新闻
//			newsQuery.setIsDelete(NewsConstants.NEWS_NO_DELETE);//只查询未被删除的新闻
//			newsQuery.setIsShield(NewsConstants.NEWS_NO_SHIELD);
//			newsQuery.setOrgCode(sysUserInfo.getManageOrgInfo().getManOrgCode());
            newsQuery.setNewsCode(newsBaseVo.getNewsCode());
            newsQuery.setUserCode(newsBaseVo.getUserCode());
            //查询条件
            NewsInfoContent newsInfoContent = newsInfoContentService.getNewsInfoDetailPC(newsQuery);
            //返回值封装
            if (null != newsInfoContent) {
                SysUserInfo sysUser = sysUserService.selectByCode(newsInfoContent.getInfoOperatorCode());
                json.put("newsCode", newsInfoContent.getNewsCode());
                json.put("infoTitle", newsInfoContent.getInfoTitle());
                json.put("infoContentText", newsInfoContent.getInfoContentText());
                json.put("infoContentHtml", newsInfoContent.getInfoContentHtml());
                json.put("fileUrl", newsInfoContent.getFileUrl());
                json.put("infoStatus", newsInfoContent.getInfoStatus());
                json.put("infoOperatorCode", newsInfoContent.getInfoOperatorCode());
                json.put("infoOperatorName", sysUser == null ? "" : sysUser.getUserName());
                json.put("infoCreatorCode", newsInfoContent.getInfoCreatorCode());
                json.put("orgLevel", newsInfoContent.getOrgLevel());
                json.put("orgCode", newsInfoContent.getOrgCode());
                json.put("isTop", newsInfoContent.getIsTop());
                json.put("infoSort", newsInfoContent.getInfoSort());
                json.put("infoGoodCount", newsInfoContent.getInfoGoodCount());
                json.put("isPraise", newsInfoContent.getIsPraise());
                json.put("infoCommentCount", newsInfoContent.getInfoCommentCount());
                json.put("collectNumber", newsInfoContent.getCollectNumber());
                json.put("isCollect", newsInfoContent.getIsCollect());
                json.put("transmitNumber", newsInfoContent.getTransmitNumber());
                json.put("createTime", newsInfoContent.getCreateTime());
                json.put("modifyTime", newsInfoContent.getModifyTime());
                json.put("publishTime", newsInfoContent.getPublishTime());
            }
            resJson = new ResultJSON("COMMON_200");
            resJson.setBody(json);
            log.info("NewsCommentController.getNewsInfoDetail:获取新闻列表成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw BusinessException.build("NEWS_14002", "获取新闻列表");
        }
        return resJson;
    }

    /**
     * @param newsInfoInformVo
     * @return 参数
     * @throws BusinessException
     * @Title: addNewsInfoReport
     * @Description: 举报新闻
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/addNewsInfoReport")
    @ResponseBody
    public ResultJSON addNewsInfoReport(@RequireValid NewsInfoInformVo newsInfoInformVo) throws BusinessException {
        ResultJSON resJson = null;
        JSONObject body = new JSONObject();
        try {
            String userCode = newsInfoInformVo.getUserCode();
            //获取用户信息
            SysUserInfo sysUserInfo = sysUserService.selectByCode(userCode);
            NewsInfoInformModel newsInfoInformModel = new NewsInfoInformModel();
            newsInfoInformModel.setInformPeopleCode(userCode);
            newsInfoInformModel.setNewsCode(newsInfoInformVo.getNewsCode());
            newsInfoInformModel.setInformType(newsInfoInformVo.getInformType());
            newsInfoInformModel.setInfoCreatorCode(newsInfoInformVo.getInfoCreatorCode());
            newsInfoInformModel.setOrgCode(sysUserInfo.getManageOrgInfo().getManOrgCode());
            newsInfoInformModel.setTenantCode(sysUserInfo.getTenantCode());
            //查询条件
            boolean flag = newsInfoInformService.addNewsInfoReportThird(newsInfoInformModel);
            if (flag) {
                resJson = new ResultJSON("COMMON_200");
                resJson.setBody(body);
            }
            log.info("NewsCommentController.addNewsInfoReport:新闻举报成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻举报列表");
        }
        return resJson;
    }

    /**
     * @param newsCommentVo
     * @return 参数
     * @throws BusinessException
     * @Title: addNewsInfoComment
     * @Description: 新闻评论
     * @return: ResultJSON    返回类型
     */

    @SuppressWarnings("unchecked")
	@RequestMapping("/addNewsInfoComment")
    @ResponseBody
    public ResultJSON addNewsInfoComment(@RequireValid NewsCommentVo newsCommentVo) throws BusinessException {
        NewsCommentModel newsCommentModel = null;
        ResultJSON resJson = null;
        JSONObject json = new JSONObject();
        JSONObject body = new JSONObject();
        try {
            //获取用户信息
            SysUserInfo sysUserInfo = sysUserService.selectByCode(newsCommentVo.getUserCode());


            //敏感词过滤
            JSONArray jsonArray = solrQueryService.CheckSentence(sysUserInfo.getManageOrgInfo().getSysOrgCode(), newsCommentVo.getCommentContent()
                    , SolrCoreConstants.SENSITIVE_WORD_CORE);
            if (jsonArray.size() > 0) {
                resJson = new ResultJSON("COMMON_999");
                body.put("totalCount", jsonArray.size());
                body.put("isPass", false);
                body.put("list", jsonArray);
                resJson.setBody(body);
                return resJson;
            }

            newsCommentModel = new NewsCommentModel();
            newsCommentModel.setNewsCode(newsCommentVo.getNewsCode());
            newsCommentModel.setCommentContent(newsCommentVo.getCommentContent());
            newsCommentModel.setRevertPeopleCode(newsCommentVo.getRevertPeopleCode());
            newsCommentModel.setTenantCode(sysUserInfo.getTenantCode());
            newsCommentModel.setOrgCode(sysUserInfo.getManageOrgInfo().getManOrgCode());
            newsCommentModel.setUserCode(newsCommentVo.getUserCode());
            newsCommentModel.setIsDelete(NewsConstants.NEWS_NO_DELETE);
            newsCommentModel.setIsShield(NewsConstants.NEWS_NO_SHIELD);
            newsCommentModel.setCommentLikeNum(0);
            newsCommentModel.setCommentReplyNum(0);
            newsCommentModel.setCommentReportNum(0);
            //查询条件
            NewsInfoComment newsInfoComment = newsCommentService.addNewsComment(newsCommentModel);
            if (null != newsInfoComment) {
                RelationDataVO relationDataVO = generateRelationDataVO(newsInfoComment.getNewsCode(), RelationConstants
                        .RELATION_THIRD_DATANUM_TYPE_COMMENT, 1);
                producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(relationDataVO));

                sendCreditMsg(sysUserInfo.getUserCode(), sysUserInfo.getManageOrgInfo().getManOrgCode(), (byte) 1, CreditConstants
                        .COMMAND_COMMENT_PUBLISH);

                SysOrgInfo sysOrgInfo = sysOrgService.selectByCode(newsInfoComment.getOrgCode());
                json.put("id", newsInfoComment.getId());
                json.put("infoCommentCode", newsInfoComment.getCommentCode());
                json.put("newsCode", newsInfoComment.getNewsCode());
                json.put("userCode", newsInfoComment.getUserCode());
                json.put("userName", sysUserInfo == null ? "" : sysUserInfo.getUserName());
                json.put("headPortrait", sysUserInfo == null ? "" : sysUserInfo.getHeadPortrait());
                json.put("orgCode", newsInfoComment.getOrgCode());
                json.put("orgName", sysOrgInfo == null ? "" : sysOrgInfo.getSysOrgName());
                json.put("commentContent", newsInfoComment.getCommentContent());
                json.put("commentGoodCount", newsInfoComment.getCommentLikeNum());
                json.put("isPraise", 0);
                json.put("createTime", newsInfoComment.getCreateTime());
                json.put("modifyTime", newsInfoComment.getModifyTime());
                resJson = new ResultJSON("COMMON_200");
                resJson.setBody(json);
            }
            log.info("NewsCommentController.addNewsInfoComment:新闻发布，回复评论成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻发布，回复评论操作");
        }
        return resJson;
    }

    /**
     * @param newsCommentDeleteVo
     * @return 参数
     * @throws BusinessException
     * @Title: deleteNewsInfoComment
     * @Description: 删除新闻评论
     * @return: ResultJSON    返回类型
     */

    @SuppressWarnings("unchecked")
	@RequestMapping("/deleteNewsInfoComment")
    @ResponseBody
    public ResultJSON deleteNewsInfoComment(@RequireValid NewsCommentDeleteVo newsCommentDeleteVo) throws BusinessException {
        NewsCommentModel newsCommentModel = null;
        ResultJSON resJson = null;
        JSONObject body = new JSONObject();
        try {
            //获取用户信息
            newsCommentModel = new NewsCommentModel();
            newsCommentModel.setCommentCode(newsCommentDeleteVo.getCommentCode());
            newsCommentModel.setUserCode(newsCommentDeleteVo.getUserCode());
            newsCommentModel.setNewsCode(newsCommentDeleteVo.getNewsCode());
            //查询条件
            boolean flag = newsCommentService.deleteNewsComment(newsCommentModel);
            if (flag) {

                RelationDataVO relationDataVO = generateRelationDataVO(newsCommentModel.getNewsCode(), RelationConstants
                        .RELATION_THIRD_DATANUM_TYPE_COMMENT, -1);
                producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(relationDataVO));

                //获取用户信息
                SysUserInfo sysUserInfo = sysUserService.selectByCode(newsCommentDeleteVo.getUserCode());
                sendCreditMsg(sysUserInfo.getUserCode(), sysUserInfo.getManageOrgInfo().getManOrgCode(), (byte) 1, CreditConstants
                        .COMMAND_COMMENT_DEL);
                resJson = new ResultJSON("COMMON_200");
                resJson.setBody(body);
            }
            log.info("NewsCommentController.deleteNewsInfoComment:新闻评论删除成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻评论删除");
        }
        return resJson;
    }

    /**
     * @param newsCommentListVO
     * @return 参数
     * @throws BusinessException
     * @Title: getNewsInfoComments
     * @Description: 新闻评论列表查询
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/getNewsInfoComments")
    @ResponseBody
    public ResultJSON getNewsInfoComments(@RequireValid NewsCommentListVO newsCommentListVO) throws BusinessException {
        NewsCommentQuery newsCommentQuery = null;
        ResultJSON resJson = null;
        JSONObject json = null;
        JSONArray jsonArray = new JSONArray();
        JSONObject body = new JSONObject();
        try {
            newsCommentQuery = new NewsCommentQuery();
            newsCommentQuery.setUserCode(newsCommentListVO.getUserCode());
            newsCommentQuery.setPageNo(newsCommentListVO.getPageNo());
            newsCommentQuery.setPageSize(newsCommentListVO.getPageSize());
            newsCommentQuery.setLimitStart(newsCommentListVO.getPageNo() * newsCommentListVO.getPageSize());
            newsCommentQuery.setNewsCode(newsCommentListVO.getNewsCode());
            newsCommentQuery.setIsShield(NewsConstants.NEWS_NO_SHIELD);
            newsCommentQuery.setIsDelete(NewsConstants.NEWS_NO_DELETE);
            //查询条件
            PageBean pageBean = newsCommentService.getNewsComments(newsCommentQuery);
            //返回值封装
            List<NewsInfoComment> list = pageBean.getRecordList();
            //根据用户code列表获取用户集合
            if (list.size() > 0) {
                List<String> usercodes = new ArrayList<>();
                List<String> orgcodes = new ArrayList<>();
                for (NewsInfoComment newsInfoComment : list) {
                    usercodes.add(newsInfoComment.getUserCode());
                    orgcodes.add(newsInfoComment.getOrgCode());
                }
                Map<String, Object> userMap = sysUserService.selectMapByCodes(usercodes);
                List<SysOrgInfo> sysOrgInfos = sysOrgService.selectByCodes(orgcodes);
                Map<String, SysOrgInfo> orgMap = generateOrgInfoMap(sysOrgInfos);
                for (int i = 0; i < list.size(); i++) {
                    json = new JSONObject();
                    NewsInfoComment temp = list.get(i);
                    json.put("id", temp.getId());
                    json.put("infoCommentCode", temp.getCommentCode());
                    json.put("newsCode", temp.getNewsCode());
                    json.put("userCode", temp.getUserCode());
                    SysUserInfo creator = (SysUserInfo) userMap.get(temp.getUserCode());
                    json.put("userName", creator == null ? "" : creator.getUserName());
                    json.put("headPortrait", creator == null ? "" : creator.getHeadPortrait());
                    json.put("orgCode", temp.getOrgCode());
                    SysOrgInfo sysOrgnfo = orgMap.get(temp.getOrgCode());
                    json.put("orgName", sysOrgnfo == null ? "" : sysOrgnfo.getSysOrgName());
                    json.put("commentContent", temp.getCommentContent());
                    json.put("commentReplyNum", temp.getCommentReplyNum());
                    json.put("commentGoodCount", temp.getCommentLikeNum());
                    json.put("isPraise", temp.getIsPraise());
                    json.put("createTime", temp.getCreateTime());
                    json.put("modifyTime", temp.getModifyTime());

                    jsonArray.add(json);
                }
            }
            resJson = new ResultJSON("COMMON_200");
            body.put("totalCount", pageBean.getTotalCount());
            body.put("list", jsonArray);
            resJson.setBody(body);

            log.info("NewsCommentController.getNewsInfoComments:新闻评论列表查询成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "获取新闻评论列表");
        }
        return resJson;
    }


    /**
     * @param newsReplyListVO
     * @return 参数
     * @throws BusinessException
     * @Title: getNewsInfoReples
     * @Description: 新闻回复列表查询
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/getNewsInfoReples")
    @ResponseBody
    public ResultJSON getNewsInfoReples(@RequireValid NewsReplyListVO newsReplyListVO) throws BusinessException {
        NewsReplyQuery newsReplyQuery = null;
        ResultJSON resJson = null;
        JSONObject json = null;
        JSONArray jsonArray = new JSONArray();
        JSONObject body = new JSONObject();
        try {
            newsReplyQuery = new NewsReplyQuery();
            newsReplyQuery.setUserCode(newsReplyListVO.getUserCode());
            newsReplyQuery.setNewsCommentCode(newsReplyListVO.getCommentCode());
            newsReplyQuery.setPageSize(newsReplyListVO.getPageSize());
            newsReplyQuery.setLimitStart(newsReplyListVO.getLimitStart());
            newsReplyQuery.setId(newsReplyListVO.getId());
            newsReplyQuery.setReplyIsDelete(NewsReplyConstants.NEWS_REPLY_NOT_DELETE);
            newsReplyQuery.setReplyIsShield(NewsReplyConstants.NEWS_REPLY_NOT_SHIELD);
            //查询条件
            PageBean pageBean = newsReplyService.getNewsReplesPC(newsReplyQuery);
            //返回值封装
            List<NewsReply> list = pageBean.getRecordList();
            //根据用户code列表获取用户集合
            if (list.size() > 0) {
                List<String> usercodes = new ArrayList<>();
                List<String> orgcodes = new ArrayList<>();
                for (NewsReply newsReply : list) {
                    usercodes.add(newsReply.getUserCode());
                    if (null != newsReply.getToReplyUserCode()) {
                        usercodes.add(newsReply.getToReplyUserCode());
                    }
                    orgcodes.add(newsReply.getOrgCode());
                    if (null != newsReply.getToReplyOrgCode()) {
                        orgcodes.add(newsReply.getToReplyOrgCode());
                    }
                }
                Map<String, Object> userMap = sysUserService.selectMapByCodes(usercodes);
                List<SysOrgInfo> sysOrgInfos = sysOrgService.selectByCodes(orgcodes);
                Map<String, SysOrgInfo> orgMap = generateOrgInfoMap(sysOrgInfos);
                for (int i = 0; i < list.size(); i++) {
                    json = new JSONObject();
                    NewsReply temp = list.get(i);
                    json.put("id", temp.getId());
                    json.put("replyCode", temp.getReplyCode());
                    json.put("userCode", temp.getUserCode());
                    json.put("replyContent", temp.getReplyContent());
                    SysUserInfo replyUser = (SysUserInfo) userMap.get(temp.getUserCode());
                    json.put("userName", replyUser == null ? "" : replyUser.getUserName());
                    json.put("headPortrait", replyUser == null ? "" : replyUser.getHeadPortrait());
                    json.put("orgCode", temp.getOrgCode());
                    SysOrgInfo sysOrginfo = orgMap.get(temp.getOrgCode());
                    json.put("orgName", sysOrginfo == null ? "" : sysOrginfo.getSysOrgName());

                    json.put("revertPeopleCode", temp.getToReplyUserCode());
                    SysUserInfo revertUserInfo = (SysUserInfo) userMap.get(temp.getToReplyUserCode());
                    json.put("revertPeopleName", revertUserInfo == null ? "" : revertUserInfo.getUserName());
                    json.put("revertHeadPortrait", revertUserInfo == null ? "" : revertUserInfo.getHeadPortrait());
                    json.put("revertOrgCode", temp.getToReplyOrgCode());
                    SysOrgInfo sysOrginforevert = orgMap.get(temp.getOrgCode());
                    json.put("revertOrgName", sysOrginforevert == null ? "" : sysOrginforevert.getSysOrgName());
                    json.put("replyLikeNum", temp.getReplyLikeNum());
                    json.put("isPraise", temp.getIsPraise());
                    json.put("replyReplyNum", temp.getReplyReportNum());
                    json.put("createTime", temp.getCreateTime());
                    json.put("modifyTime", temp.getModifyTime());
                    jsonArray.add(json);
                }
            }
            resJson = new ResultJSON("COMMON_200");
            body.put("totalCount", pageBean.getTotalCount());
            body.put("list", jsonArray);
            resJson.setBody(body);
            log.info("NewsCommentController.getNewsInfoReples:新闻评论列表查询成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "获取新闻评论列表");
        }
        return resJson;
    }


    /**
     * @param newsReplyVo
     * @return 参数
     * @throws BusinessException
     * @Title: addNewsInfoReply
     * @Description: 新闻评论回复
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/addNewsInfoReply")
    @ResponseBody
    public ResultJSON addNewsInfoReply(@RequireValid NewsReplyVo newsReplyVo) throws BusinessException {
        NewsReplyModel newsReplyModel = null;
        ResultJSON resJson = null;
        JSONObject json = new JSONObject();
        JSONObject body = new JSONObject();
        try {
            //获取用户信息
            SysUserInfo sysUserInfo = sysUserService.selectByCode(newsReplyVo.getUserCode());

            //敏感词过滤
            JSONArray jsonArray = solrQueryService.CheckSentence(sysUserInfo.getManageOrgInfo().getSysOrgCode(), newsReplyVo.getReplyContent()
                    , SolrCoreConstants.SENSITIVE_WORD_CORE);
            if (jsonArray.size() > 0) {
                resJson = new ResultJSON("COMMON_999");
                body.put("totalCount", jsonArray.size());
                body.put("isPass", false);
                body.put("list", jsonArray);
                resJson.setBody(body);
                return resJson;
            }

            newsReplyModel = new NewsReplyModel();
            newsReplyModel.setNewsCode(newsReplyVo.getNewsCode());
            newsReplyModel.setCommentCode(newsReplyVo.getCommentCode());
            newsReplyModel.setReplyContent(newsReplyVo.getReplyContent());
            newsReplyModel.setRevertPeopleCode(newsReplyVo.getRevertPeopleCode());
            SysUserInfo revertUserInfo = null;
            if (!"".equals(newsReplyVo.getRevertPeopleCode())) {
                revertUserInfo = sysUserService.selectByCode(newsReplyVo.getRevertPeopleCode());
                newsReplyModel.setRevertOrgCode(revertUserInfo.getManageOrgInfo().getManOrgCode());
            }
            newsReplyModel.setTenantCode(sysUserInfo.getTenantCode());
            newsReplyModel.setOrgCode(sysUserInfo.getManageOrgInfo().getManOrgCode());
            newsReplyModel.setUserCode(newsReplyVo.getUserCode());
            newsReplyModel.setIsDelete(NewsConstants.NEWS_NO_DELETE);
            newsReplyModel.setIsShield(NewsConstants.NEWS_NO_SHIELD);
            newsReplyModel.setCommentLikeNum(0);
            newsReplyModel.setCommentReplyNum(0);
            newsReplyModel.setCommentReportNum(0);
            //查询条件
            NewsReply newsReply = newsReplyService.addNewsReplyPC(newsReplyModel);
            if (null != newsReply) {
                sendCreditMsg(sysUserInfo.getUserCode(), sysUserInfo.getManageOrgInfo().getManOrgCode(), (byte) 1, CreditConstants
                        .COMMAND_COMMENT_REPLY);
                List<String> orgCodes = new ArrayList<>();
                orgCodes.add(newsReply.getOrgCode());
                orgCodes.add(newsReply.getToReplyOrgCode());
                List<SysOrgInfo> sysOrgInfos = sysOrgService.selectByCodes(orgCodes);
                Map<String, SysOrgInfo> orgInfoMap = generateOrgInfoMap(sysOrgInfos);
                json.put("replyCode", newsReply.getReplyCode());
                json.put("userCode", newsReply.getUserCode());
                json.put("replyContent", newsReply.getReplyContent());
                json.put("userName", sysUserInfo == null ? "" : sysUserInfo.getUserName());
                json.put("headPortrait", sysUserInfo == null ? "" : sysUserInfo.getHeadPortrait());
                json.put("orgCode", newsReply.getOrgCode());
                SysOrgInfo sysOrgInfo = orgInfoMap.get(newsReply.getOrgCode());
                json.put("orgName", sysOrgInfo == null ? "" : sysOrgInfo.getSysOrgName());

                json.put("revertPeopleCode", newsReply.getToReplyUserCode());
                json.put("revertPeopleName", revertUserInfo == null ? "" : revertUserInfo.getUserName());
                json.put("revertHeadPortrait", revertUserInfo == null ? "" : revertUserInfo.getHeadPortrait());
                json.put("revertOrgCode", newsReply.getToReplyOrgCode());
                SysOrgInfo sysOrginforevert = orgInfoMap.get(newsReply.getToReplyOrgCode());
                json.put("revertOrgName", sysOrginforevert == null ? "" : sysOrginforevert.getSysOrgName());

                json.put("replyLikeNum", newsReply.getReplyLikeNum());
                json.put("isPraise", 0);//当前用户对新增的没有点赞记录
                json.put("replyReplyNum", newsReply.getReplyReportNum());
                json.put("createTime", newsReply.getCreateTime());
                json.put("modifyTime", newsReply.getModifyTime());
                resJson = new ResultJSON("COMMON_200");
                resJson.setBody(json);
            }
            log.info("NewsCommentController.addNewsInfoReply:新闻评论回复成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻评论回复操作");
        }
        return resJson;
    }

    /**
     * @param newsReplyDeleteVO
     * @return 参数
     * @throws BusinessException
     * @Title: deleteNewsInfoReply
     * @Description: 删除新闻回复
     * @return: ResultJSON    返回类型
     */

    @RequestMapping("/deleteNewsInfoReply")
    @ResponseBody
    public ResultJSON deleteNewsInfoReply(@RequireValid NewsReplyDeleteVO newsReplyDeleteVO) throws BusinessException {
        NewsReplyModel newsReplyModel = null;
        ResultJSON resJson = null;
        JSONObject body = new JSONObject();
        try {
            //获取用户信息
            newsReplyModel = new NewsReplyModel();
            newsReplyModel.setReplyCode(newsReplyDeleteVO.getReplyCode());
            newsReplyModel.setCommentCode(newsReplyDeleteVO.getCommentCode());
            newsReplyModel.setUserCode(newsReplyDeleteVO.getUserCode());
            //查询条件
            boolean flag = newsReplyService.deleteNewsReply(newsReplyModel);
            if (flag) {
                SysUserInfo sysUserInfo = sysUserService.selectByCode(newsReplyDeleteVO.getUserCode());
                sendCreditMsg(sysUserInfo.getUserCode(), sysUserInfo.getManageOrgInfo().getManOrgCode(), (byte) 1, CreditConstants
                        .COMMAND_COMMENT_REPLYDEL);
                resJson = new ResultJSON("COMMON_200");
                resJson.setBody(body);
            }
            log.info("NewsCommentController.deleteNewsInfoReply:新闻评论删除成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻评论删除");
        }
        return resJson;
    }

    /**
     * @param newsPraiseVo
     * @return 参数
     * @throws BusinessException
     * @Title: updateNewsInfoGood
     * @Description: 新闻点赞
     * @return: ResultJSON    返回类型
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("/updateNewsInfoGood")
    @ResponseBody
    public ResultJSON updateNewsInfoGood(@RequireValid NewsPraiseVo newsPraiseVo) throws BusinessException {
        NewsPraiseModel newsPraiseModel = null;
        ResultJSON resJson = null;
        JSONObject body = new JSONObject();
        try {
            //获取用户信息
            SysUserInfo sysUserInfo = sysUserService.selectByCode(newsPraiseVo.getUserCode());
            newsPraiseModel = new NewsPraiseModel();
            newsPraiseModel.setNewsCode(newsPraiseVo.getNewsCode());
            newsPraiseModel.setUserCode(newsPraiseVo.getUserCode());
            newsPraiseModel.setPraisePeopleCode(sysUserInfo.getUserCode());
            newsPraiseModel.setPraiseOrgCode(sysUserInfo.getManageOrgInfo().getManOrgCode());
            newsPraiseModel.setTenantCode(sysUserInfo.getTenantCode());

            newsPraiseModel.setOrgCode(sysUserInfo.getManageOrgInfo().getManOrgCode());
            newsPraiseModel.setActionType(Integer.valueOf(newsPraiseVo.getActionType()));
            //查询条件
            boolean flag = newsCommentService.updateNewsInfoGood(newsPraiseModel);
            if (flag) {
                String actionCode = CreditConstants.COMMAND_ADMIRE_PUBLISH;
                if ("0".equals(newsPraiseVo.getActionType())) {
                    actionCode = CreditConstants.COMMAND_ADMIRE_DEL;
                }
                sendCreditMsg(sysUserInfo.getUserCode(), sysUserInfo.getManageOrgInfo().getManOrgCode(), (byte) 1, actionCode);

                RelationDataVO relationDataVO = generateRelationDataVO(newsPraiseVo.getNewsCode(), RelationConstants.RELATION_THIRD_DATANUM_TYPE_LIKE, Integer.valueOf
                        (newsPraiseVo.getActionType()));
                producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(relationDataVO));
                resJson = new ResultJSON("COMMON_200");
                resJson.setBody(body);
            }
            log.info("NewsCommentController.updateNewsInfoGood:新闻点赞成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻点赞");
        }
        return resJson;
    }

    /**
     * @param newsCommentPraiseVo
     * @return 参数
     * @throws BusinessException
     * @Title: updateNewsInfoCommentGood
     * @Description: 新闻评论点赞
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/updateNewsInfoCommentGood")
    @ResponseBody
    public ResultJSON updateNewsInfoCommentGood(@RequireValid NewsCommentPraiseVo newsCommentPraiseVo) throws BusinessException {
        NewsCommentPraiseModel newsCommentPraiseModel = null;
        ResultJSON resJson = null;
        JSONObject body = new JSONObject();
        try {
            //获取用户信息
            SysUserInfo sysUserInfo = sysUserService.selectByCode(newsCommentPraiseVo.getUserCode());
            newsCommentPraiseModel = new NewsCommentPraiseModel();
            newsCommentPraiseModel.setNewsCode(newsCommentPraiseVo.getNewsCode());
            newsCommentPraiseModel.setUserCode(newsCommentPraiseVo.getUserCode());
            newsCommentPraiseModel.setPraisePeopleCode(newsCommentPraiseVo.getUserCode());
            newsCommentPraiseModel.setPraisePeopleOrg(sysUserInfo.getManageOrgInfo().getManOrgCode());
            newsCommentPraiseModel.setTenantCode(sysUserInfo.getTenantCode());
            newsCommentPraiseModel.setCommentCode(newsCommentPraiseVo.getCommentCode());
            SysUserInfo revertPeople = sysUserService.selectByCode(newsCommentPraiseVo.getCommentUserCode());
            newsCommentPraiseModel.setRevertPeopleCode(newsCommentPraiseVo.getCommentUserCode());
            newsCommentPraiseModel.setRevertPeopleOrg(revertPeople.getManageOrgInfo().getManOrgCode());
            newsCommentPraiseModel.setOrgCode(sysUserInfo.getManageOrgInfo().getManOrgCode());
            newsCommentPraiseModel.setActionType(Integer.valueOf(newsCommentPraiseVo.getActionType()));
            //查询条件
            boolean flag = newsCommentService.updateNewsInfoCommentGood(newsCommentPraiseModel);
            if (flag) {
                String actionCode = CreditConstants.COMMAND_ADMIRE_PUBLISH;
                if ("0".equals(newsCommentPraiseVo.getActionType())) {
                    actionCode = CreditConstants.COMMAND_ADMIRE_DEL;
                }
                sendCreditMsg(sysUserInfo.getUserCode(), sysUserInfo.getManageOrgInfo().getManOrgCode(), (byte) 1, actionCode);
                resJson = new ResultJSON("COMMON_200");
                resJson.setBody(body);
            }
            log.info("NewsCommentController.updateNewsInfoCommentGood:新闻评论点赞成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻评论");
        }
        return resJson;
    }

    /**
     * @param newsReplyPraiseVo
     * @return 参数
     * @throws BusinessException
     * @Title: updateNewsReplyGood
     * @Description: 新闻回复点赞
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/updateNewsReplyGood")
    @ResponseBody
    public ResultJSON updateNewsReplyGood(@RequireValid NewsReplyPraiseVo newsReplyPraiseVo) throws BusinessException {
        NewsReplyPraiseModel newsReplyPraiseModel = null;
        ResultJSON resJson = null;
        JSONObject body = new JSONObject();
        try {
            //获取用户信息
            SysUserInfo sysUserInfo = sysUserService.selectByCode(newsReplyPraiseVo.getUserCode());
            newsReplyPraiseModel = new NewsReplyPraiseModel();
            newsReplyPraiseModel.setNewsCode(newsReplyPraiseVo.getNewsCode());
            newsReplyPraiseModel.setUserCode(newsReplyPraiseVo.getUserCode());
            newsReplyPraiseModel.setPraisePeopleCode(newsReplyPraiseVo.getUserCode());
            newsReplyPraiseModel.setPraiseOrgCode(sysUserInfo.getManageOrgInfo().getManOrgCode());
            newsReplyPraiseModel.setTenantCode(sysUserInfo.getTenantCode());
            newsReplyPraiseModel.setNewsReplyCode(newsReplyPraiseVo.getReplyCode());
            newsReplyPraiseModel.setNewsCommentCode(newsReplyPraiseVo.getCommentCode());
            SysUserInfo revertPeople = sysUserService.selectByCode(newsReplyPraiseVo.getRevertUserCode());
            newsReplyPraiseModel.setRevertPeopleCode(newsReplyPraiseVo.getRevertUserCode());
            newsReplyPraiseModel.setRevertPeopleOrg(revertPeople.getManageOrgInfo().getManOrgCode());
            newsReplyPraiseModel.setOrgCode(sysUserInfo.getManageOrgInfo().getManOrgCode());
            newsReplyPraiseModel.setActionType(Integer.valueOf(newsReplyPraiseVo.getActionType()));
            //查询条件
            boolean flag = newsReplyService.updateNewsReplyGood(newsReplyPraiseModel);
            if (flag) {
                String actionCode = CreditConstants.COMMAND_ADMIRE_PUBLISH;
                if ("0".equals(newsReplyPraiseVo.getActionType())) {
                    actionCode = CreditConstants.COMMAND_ADMIRE_DEL;
                }
                sendCreditMsg(sysUserInfo.getUserCode(), sysUserInfo.getManageOrgInfo().getManOrgCode(), (byte) 1, actionCode);
                resJson = new ResultJSON("COMMON_200");
            } else {
                resJson = new ResultJSON("NEWS_14002");
            }
            resJson.setBody(body);
            log.info("NewsCommentController.updateNewsReplyGood:新闻回复点赞成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻回复点赞");
        }
        return resJson;
    }

    /**
     * @param newsPraiseVo
     * @return 参数
     * @throws BusinessException
     * @Title: addNewsInfoCollect
     * @Description: 新闻收藏
     * @return: ResultJSON    返回类型
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("/addNewsInfoCollect")
    @ResponseBody
    public ResultJSON addNewsInfoCollect(@RequireValid NewsPraiseVo newsPraiseVo) throws BusinessException {
        NewsCollectModel newsCollectModel = null;
        ResultJSON resJson = null;
        JSONObject body = new JSONObject();
        try {
            //获取用户信息
            SysUserInfo sysUserInfo = sysUserService.selectByCode(newsPraiseVo.getUserCode());
            newsCollectModel = new NewsCollectModel();
            newsCollectModel.setNewsCode(newsPraiseVo.getNewsCode());
            newsCollectModel.setUserCode(newsPraiseVo.getUserCode());
            newsCollectModel.setOrgCode(sysUserInfo.getManageOrgInfo().getManOrgCode());
            newsCollectModel.setTenantCode(sysUserInfo.getTenantCode());
            newsCollectModel.setActionType(Integer.valueOf(newsPraiseVo.getActionType()));

            newsCollectModel.setCollectCode(UUIDHelper.getUUID());
            //查询条件
            boolean flag = newsCollectService.addNewsInfoCollect(newsCollectModel);
            if (flag) {
                String actionCode = CreditConstants.COMMAND_CONTENT_COLLECT;
                if ("0".equals(newsPraiseVo.getActionType())) {
                    actionCode = CreditConstants.COMMAND_CONTENT_COLLECTCANCEL;
                }
                sendCreditMsg(sysUserInfo.getUserCode(), sysUserInfo.getManageOrgInfo().getManOrgCode(), (byte) 1, actionCode);

                RelationDataVO relationDataVO = generateRelationDataVO(newsCollectModel.getNewsCode(), RelationConstants.RELATION_THIRD_DATANUM_TYPE_COLLECT, Integer.valueOf
                        (newsPraiseVo.getActionType()));
                producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(relationDataVO));
                resJson = new ResultJSON("COMMON_200");
                resJson.setBody(body);
            }
            log.info("NewsCommentController.addNewsInfoCollect:新闻收藏成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻收藏");
        }
        return resJson;
    }

    /**
     * @param newsPraiseVo
     * @return 参数
     * @throws BusinessException
     * @Title: addNewsInfoTransmit
     * @Description: 新闻转发
     * @return: ResultJSON    返回类型
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("/addNewsInfoTransmit")
    @ResponseBody
    public ResultJSON addNewsInfoTransmit(@RequireValid NewsPraiseVo newsPraiseVo) throws BusinessException {
        NewsTransmitModel newsTransmitModel = null;
        ResultJSON resJson = null;
        JSONObject body = new JSONObject();
        try {
            //获取用户信息
            SysUserInfo sysUserInfo = sysUserService.selectByCode(newsPraiseVo.getUserCode());
            newsTransmitModel = new NewsTransmitModel();
            newsTransmitModel.setNewsCode(newsPraiseVo.getNewsCode());
            newsTransmitModel.setUserCode(newsPraiseVo.getUserCode());
            newsTransmitModel.setOrgCode(sysUserInfo.getManageOrgInfo().getManOrgCode());
            newsTransmitModel.setTenantCode(sysUserInfo.getTenantCode());
            newsTransmitModel.setTransmitCode(UUIDHelper.getUUID());
            //查询条件
            boolean flag = newsCollectService.addNewsInfoTransmit(newsTransmitModel);
            if (flag) {
                //积分
                String actionCode = CreditConstants.COMMAND_CONTENT_RELAY;
                if ("0".equals(newsPraiseVo.getActionType())) {
                    actionCode = CreditConstants.COMMAND_CONTENT_RELAYDEL;
                }
                sendCreditMsg(sysUserInfo.getUserCode(), sysUserInfo.getManageOrgInfo().getManOrgCode(), (byte) 1, actionCode);
                //动态
                NewsQuery newsQuery = new NewsQuery();
                newsQuery.setNewsCode(newsPraiseVo.getNewsCode());
//                NewsInfoContent newsInfoContent = newsInfoContentService.getNewsInfoDetail(newsQuery);
                RelationDynamic relationDynamic = generateRelationDynamic(newsTransmitModel, sysUserInfo);
                // RelationSubjectInfo relationSubjectInfo = generateRelationInfo(newsTransmitModel,newsInfoContent,sysUserInfo);
                boolean relationFlag = relationThirdDynamicService.saveDynamic(relationDynamic, null, null, null);
                RelationDataVO relationDataVO = generateRelationDataVO(newsPraiseVo.getNewsCode(), RelationConstants.RELATION_THIRD_DATANUM_TYPE_SHARE, Integer.valueOf
                        (newsPraiseVo.getActionType()));
                producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(relationDataVO));
                if (relationFlag) {
                    resJson = new ResultJSON("COMMON_200");
                } else {
                    throw BusinessException.build("NEWS_14009", "转发新闻");
                }
            }
            resJson.setBody(body);
            log.info("NewsCommentController.addNewsInfoTransmit:新闻转发成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "新闻转发");
        }
        return resJson;
    }


    /**
     * @param newsCommentListVO
     * @return 参数
     * @throws BusinessException
     * @Title: getNewsCommentListById
     * @Description: 新闻评论列表查询
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/getNewsCommentListById")
    @ResponseBody
    public ResultJSON getNewsCommentListById(@RequireValid NewsCommentListVO newsCommentListVO) throws BusinessException {
        NewsCommentQuery newsCommentQuery = null;
        ResultJSON resJson = null;
        JSONObject json = null;
        JSONArray jsonArray = new JSONArray();
        JSONObject body = new JSONObject();
        try {
            newsCommentQuery = new NewsCommentQuery();
            newsCommentQuery.setCommentCode(newsCommentListVO.getCommentCode());
            NewsInfoComment commentDetail = newsCommentService.getNewsCommentByQuery(newsCommentQuery);
            if (null != commentDetail) {
                SysUserInfo userInfo = sysUserService.selectByCode(commentDetail.getUserCode());
                json = new JSONObject();
                json.put("id", commentDetail.getId());
                json.put("infoCommentCode", commentDetail.getCommentCode());
                json.put("newsCode", commentDetail.getNewsCode());
                json.put("userCode", commentDetail.getUserCode());
                json.put("userName", userInfo == null ? "" : userInfo.getUserName());
                json.put("commentContent", commentDetail.getCommentContent());
                json.put("commentGoodCount", commentDetail.getCommentLikeNum());
                json.put("commentReplyNum", commentDetail.getCommentReplyNum());
                json.put("createTime", commentDetail.getCreateTime());
                json.put("modifyTime", commentDetail.getModifyTime());
                jsonArray.add(json);
            }
            newsCommentQuery = new NewsCommentQuery();
            newsCommentQuery.setNewsCode(newsCommentListVO.getNewsCode());
//            newsCommentQuery.setId(newsCommentListVO.getId());
            newsCommentQuery.setUserCode(newsCommentListVO.getUserCode());
            newsCommentQuery.setPageNo(newsCommentListVO.getPageNo());
            newsCommentQuery.setPageSize(newsCommentListVO.getPageSize());
            newsCommentQuery.setLimitStart(newsCommentListVO.getPageNo() * newsCommentListVO.getPageSize());
            newsCommentQuery.setExcludeCommentCode(newsCommentListVO.getCommentCode());
            //查询条件
            PageBean pageBean = newsCommentService.getNewsComments(newsCommentQuery);

            //返回值封装
            List<NewsInfoComment> list = pageBean.getRecordList();
            //根据用户code列表获取用户集合
            if (list.size() > 0) {
                List<String> usercodes = new ArrayList<>();
                for (NewsInfoComment newsInfoComment : list) {
                    usercodes.add(newsInfoComment.getUserCode());
                }
                Map<String, Object> userMap = sysUserService.selectMapByCodes(usercodes);
                for (int i = 0; i < list.size(); i++) {
                    json = new JSONObject();
                    NewsInfoComment temp = list.get(i);
                    json.put("id", temp.getId());
                    json.put("infoCommentCode", temp.getCommentCode());
                    json.put("newsCode", temp.getNewsCode());
                    json.put("userCode", temp.getUserCode());
                    SysUserInfo creator = (SysUserInfo) userMap.get(temp.getUserCode());
                    json.put("userName", creator == null ? "" : creator.getUserName());
                    json.put("commentContent", temp.getCommentContent());
                    json.put("commentGoodCount", temp.getCommentLikeNum());
                    json.put("createTime", temp.getCreateTime());
                    json.put("modifyTime", temp.getModifyTime());
                    jsonArray.add(json);
                }
            }
            resJson = new ResultJSON("COMMON_200");
            body.put("totalCount", pageBean.getTotalCount());
            body.put("list", jsonArray);
            resJson.setBody(body);

            log.info("NewsCommentController.getNewsCommentListById:新闻评论列表查询成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw BusinessException.build("NEWS_14002", "获取新闻评论列表");
        }
        return resJson;
    }

    /**
     * @param newsReplyListVO
     * @return 参数
     * @throws BusinessException
     * @Title: getNewsReplyListById
     * @Description: 内容监控--新闻评论回复查询列表
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/getNewsReplyListById")
    @ResponseBody
    public ResultJSON getNewsReplyListById(@RequireValid NewsReplyListVO newsReplyListVO) throws BusinessException {
        NewsReplyQuery newsReplyQuery = null;
        ResultJSON resJson = null;
        JSONObject json = null;
        JSONArray jsonArray = new JSONArray();
        JSONObject body = new JSONObject();
        try {
            newsReplyQuery = new NewsReplyQuery();
            newsReplyQuery.setReplyCode(newsReplyListVO.getReplyCode());
            NewsReply replyDetail = newsReplyService.getNewsReplyDetail(newsReplyQuery);
            if (null != replyDetail) {
                SysUserInfo user = sysUserService.selectByCode(replyDetail.getUserCode());
                json = new JSONObject();
                json.put("id", replyDetail.getId());
                json.put("replyCode", replyDetail.getReplyCode());
                json.put("replyContent", replyDetail.getReplyContent());
                json.put("commentCode", replyDetail.getCommentCode());
                json.put("userCode", replyDetail.getUserCode());
                json.put("userName", user == null ? "" : user.getUserName());
                SysOrgInfo sysOrgInfo = user.getManageOrgInfo();
                json.put("orgName", sysOrgInfo == null ? "" : sysOrgInfo.getSysOrgName());
                json.put("createTime", replyDetail.getCreateTime());
                json.put("modifyTime", replyDetail.getModifyTime());
                jsonArray.add(json);
            }
            newsReplyQuery = new NewsReplyQuery();
            newsReplyQuery.setNewsCommentCode(newsReplyListVO.getCommentCode());
            newsReplyQuery.setPageNo(Integer.valueOf(newsReplyListVO.getLimitStart()));//后加接口，vo里的limitStart暂时当pageNo使用
            newsReplyQuery.setPageSize(Integer.valueOf(newsReplyListVO.getPageSize()));
            newsReplyQuery.setLimitStart(newsReplyQuery.getPageNo() * newsReplyQuery.getPageSize());
//            newsReplyQuery.setId(Integer.valueOf(newsReplyListVO.getId()));
            newsReplyQuery.setReplyIsDelete(NewsReplyConstants.NEWS_REPLY_NOT_DELETE);
            newsReplyQuery.setReplyIsShield(NewsReplyConstants.NEWS_REPLY_NOT_SHIELD);
            newsReplyQuery.setExcludeReplyCode(newsReplyListVO.getReplyCode());
            //查询条件
            PageBean pageBean = newsReplyService.getNewsReplys(newsReplyQuery);
            //返回值封装
            List<NewsReply> list = pageBean.getRecordList();
            //根据用户code列表获取用户集合
            if (list.size() > 0) {
                List<String> usercodes = new ArrayList<>();
                List<String> orgCodes = new ArrayList<>();

                for (NewsReply newsReply : list) {
                    usercodes.add(newsReply.getUserCode());
                    orgCodes.add(newsReply.getOrgCode());
                }
                List<SysUserInfo> userInfos = sysUserService.selectByCodes(usercodes);
                Map<String, SysUserInfo> userMap = generateUserInfoMap(userInfos);
                List<SysOrgInfo> sysOrgInfos = sysOrgService.selectByCodes(orgCodes);
                Map<String, SysOrgInfo> orgMap = generateOrgInfoMap(sysOrgInfos);
                for (int i = 0; i < list.size(); i++) {
                    json = new JSONObject();
                    NewsReply temp = list.get(i);
                    json.put("id", temp.getId());
                    json.put("replyCode", temp.getReplyCode());
                    json.put("replyContent", temp.getReplyContent());
                    json.put("commentCode", temp.getCommentCode());
                    json.put("userCode", temp.getUserCode());
                    SysUserInfo creator = userMap.get(temp.getUserCode());
                    json.put("userName", creator == null ? "" : creator.getUserName());
                    json.put("orgCode", temp.getOrgCode());
                    SysOrgInfo sysOrgInfo = orgMap.get(temp.getOrgCode());
                    json.put("orgName", sysOrgInfo == null ? "" : sysOrgInfo.getSysOrgName());
                    json.put("createTime", temp.getCreateTime());
                    json.put("modifyTime", temp.getModifyTime());
                    jsonArray.add(json);
                }
            }

            resJson = new ResultJSON("COMMON_200");
            body.put("totalCount", pageBean.getTotalCount());
            body.put("topTotal", pageBean.getNumPerPage());
            body.put("list", jsonArray);
            resJson.setBody(body);

            log.info("NewsCommentController.getNewsReplyListById:新闻评论回复查询列表成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw BusinessException.build("NEWS_14002", "新闻评论回复查询列表");
        }
        return resJson;
    }


    private RelationDataVO generateRelationDataVO(String subjectCode, int updateClass, int actionType) {
        RelationDataVO relationDataVO = new RelationDataVO();
        relationDataVO.setSubjectCode(subjectCode);
        relationDataVO.setUpdateClass((byte) updateClass);
        relationDataVO.setModifyTime(System.currentTimeMillis());
        if (actionType == 0) {
            relationDataVO.setUpdateNumber(-1);
        } else {
            relationDataVO.setUpdateNumber(1);
        }
        return relationDataVO;
    }


    private RelationDynamic generateRelationDynamic(NewsTransmitModel newsTransmitModel, SysUserInfo sysUserInfo) {
        RelationDynamic relationDynamic = new RelationDynamic();
        relationDynamic.setUserCode(sysUserInfo.getUserCode());
        relationDynamic.setOrgCode(newsTransmitModel.getOrgCode());
        relationDynamic.setTenantCode(sysUserInfo.getTenantCode());
        relationDynamic.setDynamicCode(newsTransmitModel.getTransmitCode());
        long currentTime = System.currentTimeMillis();
        relationDynamic.setCreateTime(currentTime);
        relationDynamic.setModifyTime(currentTime);
        relationDynamic.setDynamicClass((byte) 11);
        relationDynamic.setDynamicDepend((byte) 1);
        relationDynamic.setUserCode(sysUserInfo.getUserCode());
        relationDynamic.setOperateTime(currentTime);
        relationDynamic.setSubjectCode(newsTransmitModel.getNewsCode());
        relationDynamic.setDynamicIsDelete((byte) 0);
        relationDynamic.setDynamicIsShield((byte) 0);
        relationDynamic.setIsSubjectShow((byte) 1);
        relationDynamic.setIsSubjectDataShow((byte) 0);
        return relationDynamic;
    }

/*    private RelationSubjectInfo generateRelationInfo(NewsTransmitModel newsTransmitModel, NewsInfoContent newsInfoContent, SysUserInfo
            sysUserInfo) {
        RelationSubjectInfo relationSubjectInfo = new RelationSubjectInfo();
        relationSubjectInfo.setSubjectCode(newsInfoContent.getNewsCode());
        relationSubjectInfo.setTenantCode(sysUserInfo.getTenantCode());
        long currentTiem = System.currentTimeMillis();
        relationSubjectInfo.setCreateTime(currentTiem);
        relationSubjectInfo.setModifyTime(currentTiem);
        relationSubjectInfo.setSubjectClass((byte) 5);//新闻公告
        relationSubjectInfo.setSubjectDepend((byte) 3);//组织
        relationSubjectInfo.setUserCode(newsInfoContent.getInfoCreatorCode());
        relationSubjectInfo.setSubjectPublishTime(currentTiem);
        relationSubjectInfo.setSubjectTitle(newsInfoContent.getInfoTitle());
        relationSubjectInfo.setSubjectDigest(newsInfoContent.getInfoContentText());
        relationSubjectInfo.setSubjectUrl(newsInfoContent.getFileUrl());//链接
        relationSubjectInfo.setSubjectSource((byte) 1);//原创
        relationSubjectInfo.setSourceIsDelete((byte) 0);
        relationSubjectInfo.setSourceIsShield((byte) 0);
        relationSubjectInfo.setSubjectIsDelete((byte) 0);
        relationSubjectInfo.setSubjectIsShield((byte) 0);
        return relationSubjectInfo;
    }*/

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


    /**
     * @param accountCode 账号CODE
     * @param orgCode     组织CODE
     * @param accountType 账号类型
     * @param actionCode  动作类型
     * @return void    返回类型
     * @Title: sendCreditMsg
     * @Description: 发送积分信息
     */
    @SuppressWarnings("unchecked")
    protected void sendCreditMsg(String accountCode, String orgCode, Byte accountType, String actionCode) {
        MessageIntegral msg = new MessageIntegral();
        msg.setAccountCode(accountCode);
        msg.setActionCode(actionCode);
        msg.setAccountType(accountType);
        msg.setManageOrgCode(orgCode);
        producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(msg));
    }


}
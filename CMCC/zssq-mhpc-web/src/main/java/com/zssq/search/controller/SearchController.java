package com.zssq.search.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.SolrCoreConstants;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.ResultJSON;
import com.zssq.search.po.ZssqResultVo;
import com.zssq.search.service.SolrQueryService;
import com.zssq.search.vo.SearchConditionVo;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.solr.page.Page;
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
 * @ClassName: SearchController
 * @Description: 搜索Controller
 * @date 2017年5月4日
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private SolrQueryService solrQueryService;

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysOrgService sysOrgService;

    /**
     * @param searchConditionVo
     * @return 参数
     * @throws BusinessException
     * @Title: getResultByTitle
     * @Description: 根据标题查询
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/getResultByTitle")
    @ResponseBody
    public ResultJSON getResultByTitle(@RequireValid SearchConditionVo searchConditionVo) throws BusinessException {
        ResultJSON resJson = null;
        JSONObject json = null;
        JSONArray jsonArray = new JSONArray();
        JSONObject body = new JSONObject();
        try {
            Integer pageNo = Integer.valueOf(searchConditionVo.getPageNo()) + 1;//默认从第一页开始
            Integer pageSize = Integer.valueOf(searchConditionVo.getPageSize());
            String hlFiled = "categoryTitle" ;
            String condition = "categoryType:1 AND -moduleType: 1 AND isDelete:0";
            condition = generateOrgCondition(searchConditionVo, condition,hlFiled);

            //查询条件
            /** 查询功能  */ //content:*震惊* OR
            Page<ZssqResultVo> resultVoPage = solrQueryService.queryBeanHighlighting(condition, pageNo, pageSize, ZssqResultVo.class,
                    hlFiled,SolrCoreConstants.ZSSQ_CORE);

            //返回值封装
            List<ZssqResultVo> list = resultVoPage.getItems();
            //根据用户code列表获取用户集合
            if (null != list && list.size() > 0) {
                List<String> usercodes = new ArrayList<>();
                List<String> orgcodes = new ArrayList<>();
                for (ZssqResultVo zssqResultVo : list) {
                    usercodes.add(zssqResultVo.getCreatorCode());
                    if (null != zssqResultVo.getOrgCode() && !"".equals(zssqResultVo.getOrgCode())) {
                        orgcodes.add(zssqResultVo.getOrgCode());
                    }
                }
                List<SysUserInfo> userInfos = sysUserService.selectByCodes(usercodes);
                Map<String, SysUserInfo> userMap = generateUserInfoMap(userInfos);
                List<SysOrgInfo> sysOrgInfos = sysOrgService.selectByCodes(orgcodes);
                Map<String, SysOrgInfo> orgMap = generateOrgInfoMap(sysOrgInfos);
                for (int i = 0; i < list.size(); i++) {
                    json = new JSONObject();
                    ZssqResultVo temp = list.get(i);
                    json.put("categoryCode", temp.getCategoryCode());
                    json.put("categoryTitle", temp.getCategoryTitle());
                    json.put("contentDigest", temp.getContentDigest());
                    json.put("contentText", temp.getContentText());
                    json.put("highlightingFiled", temp.getHighlightingFiled());
                    json.put("rootCode", temp.getRootCode());
                    json.put("parentCode", temp.getParentCode());
                    json.put("creatorCode", temp.getCreatorCode());
                    json.put("moduleType", temp.getModuleType());
                    json.put("categoryType", temp.getCategoryType());
                    SysUserInfo userInfo = userMap.get(temp.getCreatorCode());
                    json.put("creatorName", userInfo == null ? "" : userInfo.getUserName());
                    json.put("headPortrait", userInfo == null ? "" : userInfo.getHeadPortrait());
                    json.put("orgCode", temp.getOrgCode());
                    SysOrgInfo sysOrgnfo = orgMap.get(temp.getOrgCode());
                    json.put("orgName", sysOrgnfo == null ? "" : sysOrgnfo.getSysOrgName());

                    json.put("createTime", temp.getCreateTime());
                    json.put("modifyTime", temp.getModifyTime());
                    jsonArray.add(json);
                }
            }
            resJson = new ResultJSON("COMMON_200");
            body.put("totalCount", resultVoPage.getTotalCount());
            body.put("list", jsonArray);
            resJson.setBody(body);

            log.info("SearchController.getResultByTitle:搜索成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
//            throw BusinessException.build("NEWS_14002", "搜索");
        }
        return resJson;
    }

    private String generateOrgCondition(SearchConditionVo searchConditionVo, String condition,String hlFiled) {
        try {
            String key = searchConditionVo.getKeyword() ;
            if (null != key && !"".equals(key)) {
                List<String> words = solrQueryService.getAnalysis(key, SolrCoreConstants.SENSITIVE_WORD_CORE);
                String keyword = hlFiled+":*" + key + "* OR ";
                for (String word : words) {
                    if(!key.equals(word)){
                        keyword = keyword + hlFiled+":*" + word + "* OR ";
                    }
                }
                keyword = " (" + keyword.substring(0, keyword.lastIndexOf("OR")) + ")";
                if (!"".equals(condition.trim())) {
                    condition = condition + " AND " + keyword;
                } else {
                    condition = keyword;
                }
            }

            if (null != searchConditionVo.getOrgCode() && !"".equals(searchConditionVo.getOrgCode())) {
                if (searchConditionVo.getOrgType() == 1) {//只要传递值为1，表示本省所有的，调用接口获取本省下所有orgCode
                    List<String> orgCodes = sysOrgService.searchManOrgCodesInProvince(searchConditionVo.getOrgCode());//
                    if (null != orgCodes && orgCodes.size() > 0) {
                        String orgCondition = "";
                        for (String orgCode : orgCodes) {
                            orgCondition = orgCondition + " orgCode:" + orgCode + " OR ";
                        }
                        orgCondition = "  (" + orgCondition.substring(0, orgCondition.lastIndexOf("OR")) + ")";
                        if (!"".equals(condition.trim())) {
                            condition = condition + " AND " + orgCondition;
                        } else {
                            condition = orgCondition;
                        }
                    }
                } else if (searchConditionVo.getOrgType() == 2) {//查询市一级
                    if (!"".equals(condition.trim())) {
                        condition = condition + " AND orgCode:" + searchConditionVo.getOrgCode();
                    } else {
                        condition = " orgCode:" + searchConditionVo.getOrgCode();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("solr检索拼装参数出错",e);
        }
        return condition;
    }

    /**
     * @param searchConditionVo
     * @return 参数
     * @throws BusinessException
     * @Title: getResultByContent
     * @Description: 根据内容查询
     * @return: ResultJSON    返回类型
     */
    @RequestMapping("/getResultByContent")
    @ResponseBody
    public ResultJSON getResultByContent(@RequireValid SearchConditionVo searchConditionVo) throws BusinessException {
        ResultJSON resJson = null;
        JSONObject json = null;
        JSONArray jsonArray = new JSONArray();
        JSONObject body = new JSONObject();
        List<SysOrgInfo> sysOrgInfos = null;
        Map<String, SysOrgInfo> orgMap = new HashMap<>();
        try {
            Integer pageNo = Integer.valueOf(searchConditionVo.getPageNo()) + 1;//默认从第一页开始
            Integer pageSize = Integer.valueOf(searchConditionVo.getPageSize());
            String hlFiled = "contentText" ;
            String condition = generateOrgCondition(searchConditionVo, "" ,hlFiled);
            //查询条件
            /** 查询功能  */ //content:*震惊* OR
            Page<ZssqResultVo> resultVoPage = solrQueryService.queryBeanHighlighting( condition, pageNo, pageSize, ZssqResultVo.class,
                    hlFiled,SolrCoreConstants.ZSSQ_CORE);

            //返回值封装
            List<ZssqResultVo> list = resultVoPage.getItems();
            //根据用户code列表获取用户集合
            if (null != list && list.size() > 0) {
                List<String> usercodes = new ArrayList<>();
                List<String> orgcodes = new ArrayList<>();
                for (ZssqResultVo zssqResultVo : list) {
                    usercodes.add(zssqResultVo.getCreatorCode());

                    if (null != zssqResultVo.getOrgCode() && !"".equals(zssqResultVo.getOrgCode())) {
                        orgcodes.add(zssqResultVo.getOrgCode());
                    }
                }
                List<SysUserInfo> userInfos = sysUserService.selectByCodes(usercodes);
                Map<String, SysUserInfo> userMap = generateUserInfoMap(userInfos);
                if (orgcodes != null && orgcodes.size() > 0) {
                    sysOrgInfos = sysOrgService.selectByCodes(orgcodes);
                    orgMap = generateOrgInfoMap(sysOrgInfos);
                }

                for (int i = 0; i < list.size(); i++) {
                    json = new JSONObject();
                    ZssqResultVo temp = list.get(i);
                    json.put("categoryCode", temp.getCategoryCode());
                    json.put("categoryTitle", temp.getCategoryTitle());
                    json.put("contentDigest", temp.getContentDigest());
                    json.put("contentText", temp.getContentText());
                    json.put("highlightingFiled", temp.getHighlightingFiled());
                    json.put("rootCode", temp.getRootCode());
                    json.put("parentCode", temp.getParentCode());
                    json.put("creatorCode", temp.getCreatorCode());
                    json.put("moduleType", temp.getModuleType());
                    json.put("categoryType", temp.getCategoryType());
                    SysUserInfo userInfo = userMap.get(temp.getCreatorCode());
                    json.put("creatorName", userInfo == null ? "" : userInfo.getUserName());
                    json.put("headPortrait", userInfo == null ? "" : userInfo.getHeadPortrait());
                    json.put("orgCode", temp.getOrgCode());
                    SysOrgInfo sysOrgnfo = orgMap.get(temp.getOrgCode());
                    json.put("orgName", sysOrgnfo == null ? "" : sysOrgnfo.getSysOrgName());

                    json.put("createTime", temp.getCreateTime());
                    json.put("modifyTime", temp.getModifyTime());
                    jsonArray.add(json);
                }
            }
            resJson = new ResultJSON("COMMON_200");
            body.put("totalCount", resultVoPage.getTotalCount());
            body.put("list", jsonArray);
            resJson.setBody(body);

            log.info("SearchController.getResultByContent:搜索成功");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw BusinessException.build("NEWS_14002", "搜索");
        }
        return resJson;
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


}
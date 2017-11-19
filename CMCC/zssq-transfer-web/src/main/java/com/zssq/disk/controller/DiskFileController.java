package com.zssq.disk.controller;

import com.alibaba.fastjson.JSONObject;
import com.zssq.news.enums.UnitUtil;
import com.zssq.disk.po.DiskFile;
import com.zssq.disk.po.NetdiskFile;
import com.zssq.disk.service.DiskFileService;
import com.zssq.news.po.SysUserInfo;
import com.zssq.pojo.ResultJSON;
import com.zssq.shiro.mysecurity.UUIDHelper;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author SharlaCheung
 * @ClassName: DiskFileController
 * @Description: 网盘数据迁移Controller
 * @date 2017年5月22日
 */
@Controller
@RequestMapping("diskFile")
public class DiskFileController {

    @Autowired
    private DiskFileService diskFileService;

    private Map<String, NetdiskFile> netDiskMap = new HashedMap();

    /**
     * @Title: transferNewsData
     * @Description: 迁移网盘数据
     * @return: void    返回类型
     */
    @RequestMapping(value = "/transferDiskFileData")
    @ResponseBody
    public ResultJSON transferDiskFileData() {
        // 返回值
        ResultJSON result = new ResultJSON("COMMON_200");
        JSONObject body = new JSONObject();
        int limitStart = 0;
        int pageSize = 100;

        try {

            // 查询DB2库中网盘数据总量
            int count = diskFileService.getDB2NewsCount();
            if (count <= 0) {
                body.put("message", "查询DB2库中网盘数据总量时出错，查询出的个数为：" + count);
                throw new Exception();
            }

            //分配code和parentCode
            generateCode(count);

            // 总页数
//			int totalPage = (count+pageSize-1)/pageSize;

            // 循环处理数据
            boolean arrangeFlag = true;
            for (; limitStart < count; ) {
                arrangeFlag = arrangeSubData(limitStart, pageSize);
                if (!arrangeFlag) {
                    body.put("diskfile", "处理数据时失败");
                    throw new Exception();
                }
                limitStart = limitStart + pageSize;
            }
        } catch (Exception e) {
            result = new ResultJSON("COMMON_400");
            body.put("limitStart", limitStart);
            result.setBody(body);
            e.printStackTrace();
        }
        return result;
    }

    private void generateCode(int count) {
        List<NetdiskFile> DB2NewsList = diskFileService.getList(0, count);
        for (NetdiskFile netDiskFile : DB2NewsList) {
            netDiskFile.setNewCode(UUIDHelper.getUUID());
            netDiskMap.put(netDiskFile.getFileId(), netDiskFile);

        }
    }

    /**
     * @param limitStart
     * @param pageSize   参数
     * @Title: arrangeSubData
     * @Description: 整理网盘数据
     * @return: boolean    返回类型
     */
    private boolean arrangeSubData(int limitStart, int pageSize) {
        // 返回值
        boolean result = true;

        try {
            // 获取DB2库中的网盘数据
            List<NetdiskFile> DB2NewsList = diskFileService.getDB2SubList(limitStart, pageSize);

            // 校验返回值
            if (DB2NewsList == null || DB2NewsList.isEmpty()) {
                return false;
            }

            // 将DB2中的数据整理为MySQL中的数据
            List<DiskFile> mySQLSubList = new ArrayList<DiskFile>();
            NetdiskFile parentNetDistFile = null;
            NetdiskFile netDisk = null;
            for (NetdiskFile netdiskFile : DB2NewsList) {

                SysUserInfo sysUserInfo = netdiskFile.getSysUserInfo();
                if (null == sysUserInfo) {
                    System.out.println("user not be found : " + netdiskFile.getUserId());
                    continue;
                }
                String fileName = netdiskFile.getFileName();
                if (null == fileName || "".equals(fileName)) {
                    continue;
                }
                DiskFile diskFile = new DiskFile();
//                newsInfoVoMysql.setFileCode(netdiskFile.getFileId());
                netDisk = netDiskMap.get(netdiskFile.getFileId());
                diskFile.setFileCode(netDisk.getNewCode());
                String fileSize = getFileSize(netdiskFile.getFileSize());
                diskFile.setFileSize(fileSize);
                Integer type = 1;
                if (netdiskFile.getFileType() == 1) {
                    type = 2;
                }
                diskFile.setFileType(type);
                if (type == 2) {
                    diskFile.setFileName(netdiskFile.getFileName());
                    diskFile.setFileSuffix("");
                } else {
                    int index = fileName.lastIndexOf(".");
                    if (index > 0) {
                        diskFile.setFileName(fileName.substring(0, index));
                        diskFile.setFileSuffix(fileName.substring(index, fileName.length()));
                    } else {
                        diskFile.setFileName(netdiskFile.getFileName());
                        diskFile.setFileSuffix("");
                    }
                }
                diskFile.setFileUrl(netdiskFile.getFilePath());
                if (null != netdiskFile.getFilePid() && !"".equals(netdiskFile.getFilePid())) {
                    parentNetDistFile = netDiskMap.get(netdiskFile.getFilePid());
                    diskFile.setParentCode(parentNetDistFile.getNewCode());
                } else {
                    diskFile.setParentCode("-1");//网盘部分根节点必须为-1，查询时带有该过滤条件
                }
                diskFile.setCreatTime(netdiskFile.getCreateTime().getTime());
                diskFile.setEditTime(netdiskFile.getUpdateTime().getTime());
                diskFile.setIsDelete(netdiskFile.getFileState());
                diskFile.setUserCode(sysUserInfo.getUserCode());
                diskFile.setEditUserCode(sysUserInfo.getUserCode());
                diskFile.setReportType(netdiskFile.getFileState());
                diskFile.setOrgCode(sysUserInfo.getSysOrgInfo().getManOrgCode());
                diskFile.setTenantCode("856966d06b96418fab2e430d71a1e84e");

                mySQLSubList.add(diskFile);
            }

            // 插入网盘数据到MySQL库中
            boolean insertFlag = diskFileService.insertMySQLSubList(mySQLSubList);
            if (!insertFlag) {
                result = false;
            }
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }

        return result;
    }

    private String getFileSize(Long fileSize) {
        String size;
        int step = 1;
        BigDecimal data = new BigDecimal(fileSize);
        while (fileSize / 1024 > 0) {
            data = BigDecimal.valueOf(fileSize).divide(BigDecimal.valueOf(1024), 2, RoundingMode.HALF_UP);
            fileSize = data.longValue();
            step = step + 1;
            if (step >= 4) {
                break;
            }
        }
        size = data + UnitUtil.getName(step);
        return size;
    }

}

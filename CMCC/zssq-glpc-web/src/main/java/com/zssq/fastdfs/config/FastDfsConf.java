package com.zssq.fastdfs.config;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;

/**
 *
 */
@Service
@Scope("singleton")
@DisconfFile(filename = "serveraddress.properties")
public class FastDfsConf {

    // 代表连接地址
    private String fastUrl;
    // 下载地址
    private String downloadUrl;
    //班组模板路径
    private String teamTemplate;
    //班组归档存放路径
    private String teamArchive;

    @DisconfFileItem(name = "fastdfs.url", associateField = "fastUrl")
    public String getFastUrl() {
        return fastUrl;
    }

    public void setFastUrl(String fastUrl) {
        this.fastUrl = fastUrl;
    }

    @DisconfFileItem(name = "fastdfs.downloadUrl", associateField = "downloadUrl")
    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @DisconfFileItem(name = "team_template", associateField = "teamTemplate")
	public String getTeamTemplate() {
		return teamTemplate;
	}

	public void setTeamTemplate(String teamTemplate) {
		this.teamTemplate = teamTemplate;
	}

	@DisconfFileItem(name = "team_archive", associateField = "teamArchive")
	public String getTeamArchive() {
		return teamArchive;
	}

	public void setTeamArchive(String teamArchive) {
		this.teamArchive = teamArchive;
	}
}

package com.zssq.vote.pojo;

public class TransVoteOptions {
    private Integer optionsId;

    private Integer voteId;

    private String optionsContent;

    private Integer position;

    private Integer voteNum;

    private String thumbnailsPath;

    private String imgPath;

    public Integer getOptionsId() {
        return optionsId;
    }

    public void setOptionsId(Integer optionsId) {
        this.optionsId = optionsId;
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public String getOptionsContent() {
        return optionsContent;
    }

    public void setOptionsContent(String optionsContent) {
        this.optionsContent = optionsContent == null ? null : optionsContent.trim();
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getVoteNum() {
        return voteNum;
    }

    public void setVoteNum(Integer voteNum) {
        this.voteNum = voteNum;
    }

    public String getThumbnailsPath() {
        return thumbnailsPath;
    }

    public void setThumbnailsPath(String thumbnailsPath) {
        this.thumbnailsPath = thumbnailsPath == null ? null : thumbnailsPath.trim();
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }
}
package com.zssq.activity.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
import com.zssq.annotation.validation.IntType;

public class Prize {
	
	@NotBlank(message = "{empty.message}")
	private String prizeName;
	
	@EnumType(allow={"0","1"})
	private String isJoinPrize;
	
	@IntType(expression=">=0")
	private String sequenceNo;
	
	@NotBlank(message = "{empty.message}")
	private String prizeIntro;
	
	@NotBlank(message = "{empty.message}")
	private String rule;
	
	@EnumType(allow={"0","1"})
	private String isRewardGold;
	
	@IntType(required=false,expression=">0")
	private String rewardGoldNum;
	
	@EnumType(allow={"0","1"})
	private String isAwardHonor;
	
	private String awardHonorCode;
	
	private String awardHonorName;
	
	private String awardHonorUrl;

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getIsJoinPrize() {
		return isJoinPrize;
	}

	public void setIsJoinPrize(String isJoinPrize) {
		this.isJoinPrize = isJoinPrize;
	}

	public String getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getPrizeIntro() {
		return prizeIntro;
	}

	public void setPrizeIntro(String prizeIntro) {
		this.prizeIntro = prizeIntro;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getIsRewardGold() {
		return isRewardGold;
	}

	public void setIsRewardGold(String isRewardGold) {
		this.isRewardGold = isRewardGold;
	}

	public String getRewardGoldNum() {
		return rewardGoldNum;
	}

	public void setRewardGoldNum(String rewardGoldNum) {
		this.rewardGoldNum = rewardGoldNum;
	}

	public String getIsAwardHonor() {
		return isAwardHonor;
	}

	public void setIsAwardHonor(String isAwardHonor) {
		this.isAwardHonor = isAwardHonor;
	}

	public String getAwardHonorCode() {
		return awardHonorCode;
	}

	public void setAwardHonorCode(String awardHonorCode) {
		this.awardHonorCode = awardHonorCode;
	}

	public String getAwardHonorName() {
		return awardHonorName;
	}

	public void setAwardHonorName(String awardHonorName) {
		this.awardHonorName = awardHonorName;
	}

	public String getAwardHonorUrl() {
		return awardHonorUrl;
	}

	public void setAwardHonorUrl(String awardHonorUrl) {
		this.awardHonorUrl = awardHonorUrl;
	}
}

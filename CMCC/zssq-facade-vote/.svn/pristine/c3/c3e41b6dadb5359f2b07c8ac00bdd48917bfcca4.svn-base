package com.zssq.dao.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 查询投票详细信息的返回对象
 * @ClassName VoteDetailModel
 * @Description 
 * @author liurong
 * @date 2017年4月5日 下午6:12:39
 * @version 1.0
 * @since JDK 1.7
 */
public class VoteDetailModel implements Serializable {

	private static final long serialVersionUID = -6437763547401949809L;
	// 投票信息
	private VoteInfo voteInfo;
	// 投票所有的选项被选择的总次数
	private int totalSelectedNum;
	// 投票选项集合
	private List<VoteOptions> options;
	// 用户选择的选项
	private VoteJoin voteJoin;
	
	public VoteInfo getVoteInfo() {
		return voteInfo;
	}
	public void setVoteInfo(VoteInfo voteInfo) {
		this.voteInfo = voteInfo;
	}
	public List<VoteOptions> getOptions() {
		return options;
	}
	public void setOptions(List<VoteOptions> options) {
		this.options = options;
	}
	public VoteJoin getVoteJoin() {
		return voteJoin;
	}
	public void setVoteJoin(VoteJoin voteJoin) {
		this.voteJoin = voteJoin;
	}
	public int getTotalSelectedNum() {
		return totalSelectedNum;
	}
	public void setTotalSelectedNum(int totalSelectedNum) {
		this.totalSelectedNum = totalSelectedNum;
	}
}

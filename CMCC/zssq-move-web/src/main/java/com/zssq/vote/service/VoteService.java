package com.zssq.vote.service;

public interface VoteService {

	public void init();
	
	public void voteInfoParse();

	public void voteJoinParse();

	public void voteCommentParse();
	
	public void countCommentAndJoinNum();
}

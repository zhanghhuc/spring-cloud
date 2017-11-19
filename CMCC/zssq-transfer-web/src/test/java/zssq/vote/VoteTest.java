package zssq.vote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.StringTools;
import com.zssq.vote.dao.mapper.TransVoteCommentMapper;
import com.zssq.vote.dao.mapper.VoteCommentMapper;
import com.zssq.vote.dao.mapper.VoteCommentReplyMapper;
import com.zssq.vote.dao.mapper.VoteInfoMapper;
import com.zssq.vote.dao.mapper.VoteJoinMapper;
import com.zssq.vote.pojo.CountResult;
import com.zssq.vote.pojo.VoteComment;
import com.zssq.vote.pojo.VoteCommentReply;
import com.zssq.vote.pojo.VoteInfo;
import com.zssq.vote.pojo.VoteInfoExample;
import com.zssq.vote.service.ITransferVoteService;
import com.zssq.vote.service.IVoteCountService;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:/spring/spring.xml" })
public class VoteTest extends TestCase {
	
	@Autowired
	private TransVoteCommentMapper transVoteCommentMapper;
	@Autowired
	private VoteCommentMapper voteCommentMapper;
	@Autowired
	private VoteCommentReplyMapper voteCommentReplyMapper;
	@Autowired
	private VoteJoinMapper voteJoinMapper;
	@Autowired
	private VoteInfoMapper voteInfoMapper;
	
	@Autowired
	private ITransferVoteService voteService;
	
	@Autowired
	private IVoteCountService voteCountService;
	
	@Test
	public void start(){
		try {
			voteCountService.addOneJoinAuth("e16035dc0e7e43a293715076cc0c9440");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test(){
		VoteInfoExample example = new VoteInfoExample();
		int count = voteInfoMapper.countByExample(example);
		System.out.println(count);
	}
	
	@Test
	public void voteComment(){
		
		int voteId = 1527;
		String voteInfoCode = "awefawefawefewfaw";
		
		ArrayList<VoteComment> commentList = new ArrayList<>();
		ArrayList<VoteCommentReply> replyList = new ArrayList<>();

		// 查询投票的评论
		List<Map<String, Object>> cs = transVoteCommentMapper.selectComment(voteId);
		for (Map<String, Object> c : cs) {
			VoteComment comment = new VoteComment();
			// 评论code
			String commentCode = UUIDHelper.getUUID();
			comment.setCode(commentCode);
			String tenantCode = (String) c.get("saasTenantCode");
			if (tenantCode == null) {
				comment.setTenantCode("");
			} else {
				comment.setTenantCode(tenantCode);
			}

			String manOrgCode = (String) c.get("manOrgCode");
			if (manOrgCode == null) {
				comment.setOrgCode("");
			} else {
				comment.setOrgCode(manOrgCode);
			}

			Date cDate = (Date) c.get("COMMENT_DATE");
			comment.setCreateTime(cDate.getTime());
			comment.setModifyTime(cDate.getTime());
			comment.setVoteInfoCode(voteInfoCode);

			String userCode = (String) c.get("userCode");
			if (userCode == null) {
				comment.setCommenterCode("");
			} else {
				comment.setCommenterCode(userCode);
			}
			String cont = (String) c.get("COMMENT_CONTENT");
			if(StringTools.isNotEmpty(cont)){
				comment.setContent(cont);
			}else{
				comment.setContent("和谐");
			}
			

			int cid = Integer.parseInt((String) c.get("COMMENT_ID"));
			// 查询评论的回复
			List<Map<String, Object>> rs = transVoteCommentMapper.selectReply(cid);
			for (Map<String, Object> r : rs) {
				VoteCommentReply reply = new VoteCommentReply();
				// 回复code
				String replyCode = UUIDHelper.getUUID();
				reply.setCode(replyCode);

				String rTenantCode = (String) r.get("saasTenantCode");
				if (rTenantCode == null) {
					reply.setTenantCode("");
				} else {
					reply.setTenantCode(rTenantCode);
				}

				String rManOrgCode = (String) r.get("manOrgCode");
				if (rManOrgCode == null) {
					reply.setOrgCode("");
				} else {
					reply.setOrgCode(rManOrgCode);
				}

				Date rDate = (Date) r.get("COMMENT_DATE");
				reply.setCreateTime(rDate.getTime());
				reply.setModifyTime(rDate.getTime());
				reply.setVoteInfoCode(voteInfoCode);
				reply.setCommentCode(commentCode);

				String rUserCode = (String) r.get("userCode");
				if (rUserCode == null) {
					reply.setReplierCode("");
				} else {
					reply.setReplierCode(rUserCode);
				}
				String questCode = (String) r.get("questCode");
				if (questCode == null) {
					reply.setQuestionerCode("");
				} else {
					reply.setQuestionerCode(questCode);
				}
				String rCon = (String) r.get("COMMENT_CONTENT");
				if(StringTools.isNotEmpty(rCon)){
					reply.setContent(rCon);
				}else{
					reply.setContent("和谐");
				}
				

				replyList.add(reply);
			}

			// 批量插入评论的回复
			if (replyList.size() > 0) {
				comment.setReplyCount(replyList.size());
				
				//批量操作
				//voteCommentReplyMapper.batchInsert(replyList);
				
				
				for (VoteCommentReply record : replyList) {
					voteCommentReplyMapper.insertSelective(record);
				}
				
				replyList.clear();
			}

			commentList.add(comment);
		}

		// 批量插入评论
		if(commentList.size()>0){
			
			//voteCommentMapper.batchInsert(commentList);
			
			for (VoteComment record : commentList) {
				voteCommentMapper.insertSelective(record);
			}
			commentList.clear();
		}
	}
	
	@Test
	public void count(){
		try {
			List<CountResult> commentList = voteCommentMapper.countComment();
			
			List<CountResult> joinList = voteJoinMapper.countJoin();
			
			if(commentList.size()>0){
				voteInfoMapper.batchUpdateCommentCount(commentList);
			}
			if(joinList.size()>0){
				voteInfoMapper.batchUpdateJoinCount(joinList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

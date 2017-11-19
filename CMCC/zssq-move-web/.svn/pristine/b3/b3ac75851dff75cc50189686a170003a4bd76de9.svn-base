package com.zssq.mblog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.mblog.service.MblogDb2Service;
import com.zssq.mblog.service.MblogIBMService;
import com.zssq.mblog.service.MblogService;
import com.zssq.mblog.service.MblogTaskService;
import com.zssq.mblog.service.MblogTeamService;
import com.zssq.mblog.service.MblogUserService;
import com.zssq.mblog.vo.Db2AtVo;
import com.zssq.mblog.vo.Db2CommentVo;
import com.zssq.mblog.vo.Db2MblogVo;
import com.zssq.mblog.vo.Db2SubVo;
import com.zssq.mblog.vo.Db2TopicVo;
import com.zssq.mblog.vo.MblogForward;
import com.zssq.mblog.vo.MysqlMblogIdCodeVo;
import com.zssq.mblog.vo.TeamInfoVo;
import com.zssq.mblog.vo.ThreadPoolVo;
import com.zssq.mblog.vo.TransferTaskConstants;
import com.zssq.mblog.vo.TransferTaskVo;
import com.zssq.mblog.vo.UserInfoVo;
import com.zssq.pojo.ResultJSON;
import com.zssq.shiro.mysecurity.UUIDHelper;

@Controller
@RequestMapping("/mblog")
public class MblogController {
	
	/** log对象 **/
	private Logger log = Logger.getLogger(this.getClass());

	/*@Resource
	private MblogDb2Service mblogDb2Service;*/
	
	@Resource
	private MblogIBMService mblogIBMService;
	
	@Resource
	private MblogService mblogService;
	
	@Resource
	private MblogUserService mblogUserService;
	
	@Resource
	private MblogTaskService mblogTaskService;
	
	@Resource
	private MblogTeamService mblogTeamService;
	
	/*@RequestMapping(value = "/index") 
	public String mblog(){
		return "mblog/index";
	}*/
	
	@RequestMapping(value = "/transfer/executeTask")  
	@ResponseBody
    public ResultJSON executeTask(HttpServletRequest req,HttpServletResponse res){
		int pageNo = 0;
		int pageSize = 100;
		executeTransfer(pageNo, pageSize);
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		result.setBody(body);
		return result;
	}
	
	/**
	 * 
	    * @Title: init  
	    * @Description: 初始化任务并且执行任务
	    * @param req
	    * @param res
	    * @return
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/transfer/init")  
	@ResponseBody
    public ResultJSON init(HttpServletRequest req,HttpServletResponse res){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		// 初始化任务
		mblogTaskService.initTask();
		// 执行任务
		int pageNo = 0;
		int pageSize = 100;
		executeTransfer(pageNo, pageSize);
		result.setBody(body);
		return result;
	}
	
	/**
	 * 
	    * @Title: getTaskList  
	    * @Description: 获取任务列表
	    * @param req
	    * @param res
	    * @return
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/transfer/getTaskList")  
	@ResponseBody
    public ResultJSON getTaskList(HttpServletRequest req,HttpServletResponse res){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		body.put("taskList", mblogTaskService.getTaskList());
		result.setBody(body);
		return result;
	}
	
	/**
	 * 
	    * @Title: check  
	    * @Description: 校验
	    * @param req
	    * @param res
	    * @return
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/transfer/check")  
	@ResponseBody
    public ResultJSON check(HttpServletRequest req,HttpServletResponse res){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		try{
			List<TransferTaskVo> taskList = mblogTaskService.getTaskList();
			if(null != taskList && taskList.size() > 0){
				body.put("taskStatus", 1);
			}else{
				body.put("taskStatus", 0);
			}
		}catch(Exception e){
			body.put("taskStatus", 0);
		}
		result.setBody(body);
		return result;
	}
	
	/**
	 * 
	    * @Title: execute  
	    * @Description: 重新执行任务
	    * @param req
	    * @param res
	    * @param taskType
	    * @param pageNo
	    * @return
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/transfer/execute")  
	@ResponseBody
    public ResultJSON execute(HttpServletRequest req,HttpServletResponse res,Byte taskType,Integer pageNo){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		final int no = pageNo;
		final int startNo = 0;
		final int rows = 100;
		switch(taskType){
			case TransferTaskConstants.TASK_TYPE_SELF:{ // 原创微博
				ThreadPoolVo.execute(new Runnable() {
					@Override
					public void run() {
						// 执行 --- 迁移原创微博
						if(transferMblogSelf(no,rows)){
							// 执行 --- 迁移转发微博
							if(transferMblogFor(startNo,rows)){
								ThreadPoolVo.execute(new Runnable() {
									public void run() {
										// 执行---迁移转发微博历程
										transferMblogForPro(startNo,rows);
									}
								});
								ThreadPoolVo.execute(new Runnable() {
									@Override
									public void run() {
										// 执行 --- 迁移微博评论
										transferComment(startNo,rows);
									}
								});
								ThreadPoolVo.execute(new Runnable() {
									public void run() {
										// 执行 --- 迁移AT
										transferAt(startNo,rows);
									}
								});
							}
						}
					}
				});
				break;
			}
			case TransferTaskConstants.TASK_TYPE_FOR:{ // 转发微博
				ThreadPoolVo.execute(new Runnable() {
					@Override
					public void run() {
						// 执行 --- 迁移转发微博
						if(transferMblogFor(no,rows)){
							ThreadPoolVo.execute(new Runnable() {
								public void run() {
									// 执行---迁移转发微博历程
									transferMblogForPro(startNo,rows);
								}
							});
							ThreadPoolVo.execute(new Runnable() {
								@Override
								public void run() {
									// 执行 --- 迁移微博评论
									transferComment(startNo,rows);
								}
							});
							ThreadPoolVo.execute(new Runnable() {
								public void run() {
									// 执行 --- 迁移AT
									transferAt(startNo,rows);
								}
							});
						}
					}
				});
				break;
			}
			case TransferTaskConstants.TASK_TYPE_COMMENT:{	// 评论
				ThreadPoolVo.execute(new Runnable() {
					public void run() {
						transferComment(no,rows);
					}
				});
				break;
			}
			case TransferTaskConstants.TASK_TYPE_TOPIC:{ // 话题
				ThreadPoolVo.execute(new Runnable() {
					public void run() {
						transferTopic(no,rows);
					}
				});
				break;
			}
			case TransferTaskConstants.TASK_TYPE_AT:{ // At
				ThreadPoolVo.execute(new Runnable() {
					public void run() {
						transferAt(no,rows);
					}
				});
				break;
			}
			case TransferTaskConstants.TASK_TYPE_SUB:{	// 订阅
				ThreadPoolVo.execute(new Runnable() {
					public void run() {
						transferSub(no,rows);
					}
				});
				break;
			}
			case TransferTaskConstants.TASK_TYPE_FOR_PRO:{ // 转发历程
				ThreadPoolVo.execute(new Runnable() {
					public void run() {
						transferMblogForPro(no,rows);
					}
				});
				break;
			}
		}
		//body.put("list", mblogDb2Service.getDb2MblogPageRow(331, 100, 1));
		result.setBody(body);
		return result;
	}
	
	/**
	 * 
	    * @Title: executeSelf  
	    * @Description: 执行原创微博
	    * @param req
	    * @param res
	    * @param pageNo
	    * @param pageSize
	    * @return
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/transfer/self")  
	@ResponseBody
    public ResultJSON executeSelf(HttpServletRequest req,HttpServletResponse res,Integer pageNo,Integer pageSize){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		final int no = pageNo;
		final int rows = pageSize;
		ThreadPoolVo.execute(new Runnable() {
			public void run() {
				transferMblogSelf(no,rows);
			}
		});
		//body.put("list", mblogDb2Service.getDb2MblogPageRow(331, 100, 1));
		result.setBody(body);
		return result;
	}
	
	/**
	 * 
	    * @Title: executeFor  
	    * @Description: 执行转发
	    * @param req
	    * @param res
	    * @param pageNo
	    * @param pageSize
	    * @return
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/transfer/for")  
	@ResponseBody
    public ResultJSON executeFor(HttpServletRequest req,HttpServletResponse res,Integer pageNo,Integer pageSize){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		final int no = pageNo;
		final int rows = pageSize;
		ThreadPoolVo.execute(new Runnable() {
			public void run() {
				transferMblogFor(no,rows);
			}
		});
		//body.put("list", mblogDb2Service.getDb2MblogPageRow(331, 100, 1));
		result.setBody(body);
		return result;
	}
	
	/**
	 * 
	    * @Title: executePro  
	    * @Description: 执行转发历程
	    * @param req
	    * @param res
	    * @param pageNo
	    * @param pageSize
	    * @return
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/transfer/pro")  
	@ResponseBody
    public ResultJSON executePro(HttpServletRequest req,HttpServletResponse res,Integer pageNo,Integer pageSize){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		final int no = pageNo;
		final int rows = pageSize;
		ThreadPoolVo.execute(new Runnable() {
			public void run() {
				transferMblogForPro(no,rows);
			}
		});
		//body.put("list", mblogDb2Service.getDb2MblogPageRow(331, 100, 1));
		result.setBody(body);
		return result;
	}
	
	/**
	 * 
	    * @Title: executeComment  
	    * @Description: 执行评论
	    * @param req
	    * @param res
	    * @param pageNo
	    * @param pageSize
	    * @return
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/transfer/comment")  
	@ResponseBody
    public ResultJSON executeComment(HttpServletRequest req,HttpServletResponse res,Integer pageNo,Integer pageSize){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		final int no = pageNo;
		final int rows = pageSize;
		ThreadPoolVo.execute(new Runnable() {
			public void run() {
				transferComment(no,rows);
			}
		});
		//body.put("list", mblogDb2Service.getDb2CommentPageRow(2, 100));
		result.setBody(body);
		return result;
	}
	
	/**
	 * 
	    * @Title: executeTopic  
	    * @Description: 执行话题
	    * @param req
	    * @param res
	    * @param pageNo
	    * @param pageSize
	    * @return
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/transfer/topic")  
	@ResponseBody
    public ResultJSON executeTopic(HttpServletRequest req,HttpServletResponse res,Integer pageNo,Integer pageSize){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		final int no = pageNo;
		final int rows = pageSize;
		ThreadPoolVo.execute(new Runnable() {
			public void run() {
				transferTopic(no,rows);
			}
		});
		//body.put("list", mblogDb2Service.getDb2TopicPageRow(2, 100));
		result.setBody(body);
		return result;
	}
	
	/**
	 * 
	    * @Title: executeAt  
	    * @Description: 执行AT
	    * @param req
	    * @param res
	    * @param pageNo
	    * @param pageSize
	    * @return
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/transfer/at")  
	@ResponseBody
    public ResultJSON executeAt(HttpServletRequest req,HttpServletResponse res,Integer pageNo,Integer pageSize){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		final int no = pageNo;
		final int rows = pageSize;
		ThreadPoolVo.execute(new Runnable() {
			public void run() {
				transferAt(no,rows);
			}
		});
		//body.put("list", mblogDb2Service.getDb2AtPageRow(2, 100));
		result.setBody(body);
		return result;
	}
	
	/**
	 * 
	    * @Title: executeSub  
	    * @Description: 执行订阅
	    * @param req
	    * @param res
	    * @param pageNo
	    * @param pageSize
	    * @return
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/transfer/sub")  
	@ResponseBody
    public ResultJSON executeSub(HttpServletRequest req,HttpServletResponse res,Integer pageNo,Integer pageSize){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		final int no = pageNo;
		final int rows = pageSize;
		ThreadPoolVo.execute(new Runnable() {
			public void run() {
				transferSub(no,rows);
			}
		});
		//body.put("list", mblogDb2Service.getDb2SubPageRow(2, 100));
		result.setBody(body);
		return result;
	}
	
	/**
	 * 
	    * @Title: executeTransfer  
	    * @Description: 执行任务
	    * @param pageNo	页码
	    * @param pageSize	每页条数
		* @return void    返回类型
	 */
    private void executeTransfer(final int pageNo, final int pageSize ){
		// 线程 ===1
		ThreadPoolVo.execute(new Runnable() {
			@Override
			public void run() {
				// 执行 --- 迁移原创微博
				if(transferMblogSelf(pageNo,pageSize)){
					// 执行 --- 迁移转发微博
					if(transferMblogFor(pageNo,pageSize)){
						ThreadPoolVo.execute(new Runnable() {
							public void run() {
								// 执行---迁移转发微博历程
								transferMblogForPro(pageNo,pageSize);
							}
						});
						ThreadPoolVo.execute(new Runnable() {
							@Override
							public void run() {
								// 执行 --- 迁移微博评论
								transferComment(pageNo,pageSize);
							}
						});
						ThreadPoolVo.execute(new Runnable() {
							public void run() {
								// 执行 --- 迁移AT
								transferAt(pageNo,pageSize);
							}
						});
					}
				}
			}
		});
		
		// 线程 ===2
		ThreadPoolVo.execute(new Runnable() {

			@Override
			public void run() {
				// 执行 --- 迁移话题
				transferTopic(pageNo,pageSize);
			}
			
		});
		
		// 线程 ===3
		ThreadPoolVo.execute(new Runnable() {

			@Override
			public void run() {
				// 执行 --- 迁移订阅
				transferSub(pageNo,pageSize);
			}
			
		});
	}
	
	/**
	 * 
	    * @Title: transferMblogSelf  
	    * @Description: 迁移原创微博
		* @return boolean   返回类型
	 */
	private boolean transferMblogSelf(int pageNo,int pageSize){
		
		try{
			if(pageNo < 0){
				pageNo = 0;
			}
			if(pageSize<=0){
				pageSize = 100;
			}
			// 获取原创微博总数
			int count = mblogIBMService.getDb2MblogTotal(1);
			log.info("获取到原创微博总数：=====>>>>"+count);
			int totalPage = count/pageSize + 1;
			log.info("获取到原创微博总页数：=====>>>>"+totalPage);
			// 更新任务状态
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_SELF, TransferTaskConstants.TASK_STATUS_RUNING);
			// 更新任务数量
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_SELF,totalPage,count);
			// 获取数据
			for(int no = pageNo ; no < totalPage;no++){
				// 获取微博信息列表
				//List<Db2MblogVo> mblogList = mblogDb2Service.getDb2MblogPage(pageNo, pageSize, 1,count);
				List<Db2MblogVo> mblogList = mblogIBMService.getDb2MblogPageRow(no, pageSize, 1);
				// 创建微博中间表信息
				List<MysqlMblogIdCodeVo> idCodeList = new ArrayList<MysqlMblogIdCodeVo>();
				// 遍历微博信息
				for(Db2MblogVo vo : mblogList){
					// 创建微博IDCODE 中间表信息
					MysqlMblogIdCodeVo idCode = new MysqlMblogIdCodeVo();
					
					// 获取发布用户信息
					UserInfoVo pubUser = mblogUserService.getUserInfo(vo.getUserId());
					if(null != pubUser){
						vo.setUserCode(pubUser.getUserCode());
						vo.setOrgCode(pubUser.getOrgCode());
						vo.setTenantCode(pubUser.getTenantCode());
						
						idCode.setUserCode(pubUser.getUserCode());
					}
					// 获取代发信息
					if(null != vo.getInsteadUser() && 0 != vo.getInsteadUser()  && vo.getInsteadFlag() == 1){
						UserInfoVo agentUser = mblogUserService.getUserInfo(vo.getInsteadUser());
						if(null != agentUser){
							vo.setUserCode(agentUser.getUserCode());
							vo.setOrgCode(agentUser.getOrgCode());
							vo.setTenantCode(agentUser.getTenantCode());
						}
						if(null != pubUser){
							vo.setAgentUserCode(pubUser.getUserCode());
							vo.setAgentOrgCode(pubUser.getOrgCode());
						}
					}
					
					// 获取班组信息
					TeamInfoVo team  = mblogTeamService.getTeamInfo(vo.getTeamId());
					if(null != team){
						vo.setTeamCode(team.getTeamCode());
						vo.setOrgCode(team.getOrgCode());
					}
					
					String mblogCode = UUIDHelper.getUUID();
					vo.setMblogCode(mblogCode);
					vo.setDynamicCode(UUIDHelper.getUUID());
					
					// 设置ID_CODE信息
					idCode.setMblogCode(mblogCode);
					idCode.setMblogId(vo.getId());
					idCodeList.add(idCode);
					
				}
				// 插入微博数据
				mblogService.insertBatchMblog(mblogList);
				// 插入中间表数据
				mblogService.insertBatchMblogIdCode(idCodeList);
				// 插入微博资源信息
				mblogService.insertBatchMblogResource(mblogList);
				// 插入微博数目信息
				mblogService.insertBatchMblogNum(mblogList);
				// 任务---执行中
				mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_SELF, no+1);
			}
			// 任务---成功
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_SELF, TransferTaskConstants.TASK_STATUS_END);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			// 任务---失败
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_SELF, TransferTaskConstants.TASK_STATUS_FAIL);
			return false;
		}
	}
	
	/**
	 * 
	    * @Title: transferMblogFor  
	    * @Description: 迁移转发微博
		* @return boolean    返回类型
	 */
	private boolean transferMblogFor(int pageNo,int pageSize){
		
		try{
			if(pageNo < 0){
				pageNo = 0;
			}
			if(pageSize <= 0){
				pageSize = 100;
			}
			// 获取转发微博数量
			int count = mblogIBMService.getDb2MblogTotal(2);
			log.info("获得到转发微博总数：=========>>>>"+count);
			int totalPage = count/pageSize + 1;
			log.info("获得到转发微博总页数：=========>>>>"+totalPage);
			// 更新任务状态
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_FOR, TransferTaskConstants.TASK_STATUS_RUNING);
			// 更新任务数量
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_FOR,totalPage,count);
			// 获取数据
			for(int no = pageNo ; no < totalPage;no++){
				// 获取微博列表
				List<Db2MblogVo> mblogList = mblogIBMService.getDb2MblogPageRow(no, pageSize, 2);
				// 创建微博中间表信息
				List<MysqlMblogIdCodeVo> idCodeList = new ArrayList<MysqlMblogIdCodeVo>();
				List<Db2MblogVo> newMblogList = new ArrayList<Db2MblogVo>(); 
				// 遍历微博信息
				for(Db2MblogVo vo : mblogList){
					// 创建微博IDCODE 中间表信息
					MysqlMblogIdCodeVo idCode = new MysqlMblogIdCodeVo();
					// 获取被转发的微博信息
					MysqlMblogIdCodeVo rootMb = mblogService.getIdCode(vo.getRootTid()+"");
					if(null != rootMb){
						vo.setSourceMblogCode(rootMb.getMblogCode());
						vo.setSourceUserCode(rootMb.getUserCode());
					}else{
						continue;
					}
					
					// 获取发布用户信息
					UserInfoVo pubUser = mblogUserService.getUserInfo(vo.getUserId());
					if(null != pubUser){
						vo.setUserCode(pubUser.getUserCode());
						vo.setOrgCode(pubUser.getOrgCode());
						vo.setTenantCode(pubUser.getTenantCode());
						
						idCode.setUserCode(pubUser.getUserCode());
					}
					
					String mblogCode = UUIDHelper.getUUID();
					vo.setMblogCode(mblogCode);
					vo.setDynamicCode(UUIDHelper.getUUID());
					
					// 设置ID_CODE信息
					idCode.setMblogCode(mblogCode);
					idCode.setMblogId(vo.getId());
					
					
					idCodeList.add(idCode);
					newMblogList.add(vo);
					
				}
				// 插入微博数据
				mblogService.insertBatchForMblog(newMblogList);
				// 插入中间表数据
				mblogService.insertBatchMblogIdCode(idCodeList);
				// 插入微博资源信息
				mblogService.insertBatchMblogResource(newMblogList);
				// 插入微博数目信息
				mblogService.insertBatchMblogNum(newMblogList);
				// 任务---执行中
				mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_FOR, no+1);
			}
			// 任务---成功
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_FOR, TransferTaskConstants.TASK_STATUS_END);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			// 任务---失败
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_FOR, TransferTaskConstants.TASK_STATUS_FAIL);
			return false;
		}
	}
	
	/**
	 * 
	    * @Title: transferMblogForPro  
	    * @Description: 迁移转发历程
		* @return boolean    返回类型
	 */
	private boolean transferMblogForPro(int pageNo,int pageSize){
		
		try{
			if(pageNo < 0){
				pageNo = 0;
			}
			if(pageSize <= 0){
				pageSize = 100;
			}
			// 获取转发微博数量
			int count = mblogIBMService.getDb2MblogTotal(2);
			log.info("获得到转发微博总数：=========>>>>"+count);
			int totalPage = count/pageSize + 1;
			log.info("获得到转发微博总页数：=========>>>>"+totalPage);
			// 更新任务状态
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_FOR_PRO, TransferTaskConstants.TASK_STATUS_RUNING);
			// 更新任务数量
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_FOR_PRO,totalPage,count);
			List<MblogForward> fowList = new ArrayList<MblogForward>();
			// 获取数据
			for(int no = pageNo ; no < totalPage;no++){
				// 获取微博列表
				List<Db2MblogVo> mblogList = mblogIBMService.getDb2MblogPageRow(no, pageSize, 2);
				// 遍历微博信息
				for(Db2MblogVo vo : mblogList){
					MblogForward mf = new MblogForward();
					MysqlMblogIdCodeVo curMb = mblogService.getIdCode(vo.getId()+"");
					if(null == curMb){
						continue;
					}
					mf.setCurMblogCode(curMb.getMblogCode());
					mf.setUserCode(curMb.getUserCode());
					String sourceMblogCode = "";
					MysqlMblogIdCodeVo rootMb = mblogService.getIdCode(vo.getRootTid()+"");
					if(null != rootMb){
						sourceMblogCode = rootMb.getMblogCode();
						if(!vo.getRootTid().equals(vo.getToMblogId())){
							MysqlMblogIdCodeVo toMb = mblogService.getIdCode(vo.getToMblogId()+"");
							if(null != toMb){
								sourceMblogCode += "," + toMb.getMblogCode();
							}
						}
						mf.setSourceMblogCode(sourceMblogCode);
						fowList.add(mf);
					}
				}
				// 插入微博转发信息
				mblogService.insertBatchMblogForward(fowList);
				// 任务----执行中
				mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_FOR_PRO, no+1);
			}
			// 任务---成功
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_FOR_PRO, TransferTaskConstants.TASK_STATUS_END);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			// 任务---失败
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_FOR_PRO, TransferTaskConstants.TASK_STATUS_FAIL);
			return false;
		}
	}
	
	
	/**
	 * 
	    * @Title: transferComment  
	    * @Description: 迁移评论信息
		* @return void    返回类型
	 */
	private void transferComment(int pageNo,int pageSize){
		
		try{
			if(pageNo < 0){
				pageNo = 0;
			}
			if(pageSize <= 0){
				pageSize = 100;
			}
			int count = mblogIBMService.getDb2CommentTotal();
			log.info("获取评论总数：======>>>>>>"+count);
			int totalPage = count/pageSize + 1;
			log.info("获取评论的总页数：======>>>>>>>"+totalPage);
			// 更新任务状态
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_COMMENT, TransferTaskConstants.TASK_STATUS_RUNING);
			// 更新任务数量
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_COMMENT,totalPage,count);

			// 获取数据
			for(int no = pageNo ; no < totalPage;no++){
				// 获取评论信息列表
				List<Db2CommentVo> commentList = mblogIBMService.getDb2CommentPageRow(no, pageSize);
				List<Db2CommentVo> newCommentList = new ArrayList<Db2CommentVo>();
				// 遍历评论信息
				for(Db2CommentVo vo : commentList){
					// 获取微博的信息
					MysqlMblogIdCodeVo mb = mblogService.getIdCode(vo.getMblogId()+"");
					if(null != mb){
						vo.setMblogCode(mb.getMblogCode());
					}else{
						continue;
					}
					// 获取评论用户信息
					UserInfoVo commentUser = mblogUserService.getUserInfo(vo.getUserId());
					if(null != commentUser){
						vo.setUserCode(commentUser.getUserCode());
						vo.setOrgCode(commentUser.getOrgCode());
						vo.setTenantCode(commentUser.getTenantCode());
					}
					String commentCode = UUIDHelper.getUUID();
					vo.setCommentCode(commentCode);
					
					newCommentList.add(vo);
				}
				// 插入评论信息
				mblogService.insertBatchComment(newCommentList);
				// 插入评论数据
				mblogService.insertBatchCommentNum(newCommentList);
				// 任务---执行中
				mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_COMMENT, no+1);
			}
			// 任务---成功
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_COMMENT, TransferTaskConstants.TASK_STATUS_END);
		}catch(Exception e){
			e.printStackTrace();
			// 任务---失败
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_COMMENT, TransferTaskConstants.TASK_STATUS_FAIL);
			return;
		}
	}
	
	/**
	 * 
	    * @Title: transferTopic  
	    * @Description: 迁移话题信息
		* @return void    返回类型
	 */
	private void transferTopic(int pageNo,int pageSize){
		try{
			if(pageNo < 0){
				pageNo = 0;
			}
			if(pageSize <= 0){
				pageSize = 100;
			}
			int count = mblogIBMService.getDb2TopicTotal();
			log.info("获取的话题总数：=======>>>>>>"+count);
			int totalPage = count/pageSize + 1;
			log.info("获取的话题的总页数：=======>>>>>>>"+totalPage);
			// 更新任务状态
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_TOPIC, TransferTaskConstants.TASK_STATUS_RUNING);
			// 更新任务数量
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_TOPIC,totalPage,count);

			// 获取数据
			for(int no = pageNo ; no < totalPage;no++){
				// 获取话题信息列表
				List<Db2TopicVo> topicList = mblogIBMService.getDb2TopicPageRow(no, pageSize);
				// 遍历话题信息
				for(Db2TopicVo vo : topicList){
					// 获取用户信息
					UserInfoVo topicUser = mblogUserService.getUserInfo(vo.getUserId());
					if(null != topicUser){
						vo.setOrgCode(topicUser.getOrgCode());
						vo.setTenantCode(topicUser.getTenantCode());
					}
				}
				// 插入话题信息
				mblogService.insertBatchTopic(topicList);
				// 任务---执行中
				mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_TOPIC, no+1);
			}
			// 任务---成功
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_TOPIC, TransferTaskConstants.TASK_STATUS_END);
		}catch(Exception e){
			e.printStackTrace();
			// 任务---失败
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_TOPIC, TransferTaskConstants.TASK_STATUS_FAIL);
			return;
		}

	}
	
	/**
	 * 
	    * @Title: transferAt  
	    * @Description: 迁移At信息
		* @return void    返回类型
	 */
	private void transferAt(int pageNo,int pageSize){
		try{
			if(pageNo < 0){
				pageNo = 0;
			}
			if(pageSize <= 0){
				pageSize = 100;
			}
			int count = mblogIBMService.getDb2AtTotal();
			log.info("获取AT信息总数：======>>>>>>"+count);
			int totalPage = count/pageSize + 1;
			log.info("获取AT信息总页数:=======>>>>>>>"+totalPage);
			// 更新任务状态
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_AT, TransferTaskConstants.TASK_STATUS_RUNING);
			// 更新任务数量
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_AT,totalPage,count);

			// 获取数据
			for(int no = pageNo ; no < totalPage;no++){
				// 获取At信息列表
				List<Db2AtVo> atList = mblogIBMService.getDb2AtPageRow(no, pageSize);
				// 遍历At信息
				for(Db2AtVo vo : atList){
					MysqlMblogIdCodeVo mb = mblogService.getIdCode(vo.getItemId()+"");
					if(null != mb){
						vo.setMblogCode(mb.getMblogCode());
					}else{
						continue;
					}
					// 获取AT用户信息
					UserInfoVo atUser = mblogUserService.getUserInfo(vo.getUserId());
					if(null != atUser){
						vo.setUserCode(atUser.getUserCode());
						vo.setOrgCode(atUser.getOrgCode());
						vo.setTenantCode(atUser.getTenantCode());
					}
					
					
				}
				// 插入at信息
				mblogService.insertBatchAt(atList);
				// 任务---执行中
				mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_AT, no+1);
			}
			// 任务---成功
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_AT, TransferTaskConstants.TASK_STATUS_END);
		}catch(Exception e){
			e.printStackTrace();
			// 任务---失败
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_AT, TransferTaskConstants.TASK_STATUS_FAIL);
			return;
		}

	}
	
	/**
	 * 
	    * @Title: transferSub  
	    * @Description: 迁移Sub信息
		* @return void    返回类型
	 */
	private void transferSub(int pageNo,int pageSize){
		try{
			if(pageNo < 0){
				pageNo = 0;
			}
			if(pageSize <= 0){
				pageSize = 100;
			}
			int count = mblogIBMService.getDb2SubTotal();
			log.info("获取的订阅总数：======>>>>>>"+count);
			int totalPage = count/pageSize + 1;
			log.info("获取的订阅总页数：=======>>>>>>"+totalPage);
			// 更新任务状态
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_SUB, TransferTaskConstants.TASK_STATUS_RUNING);
			// 更新任务数量
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_SUB,totalPage,count);

			// 获取数据
			for(int no = pageNo ; no < totalPage;no++){
				// 获取订阅信息列表
				List<Db2SubVo> subList = mblogIBMService.getDb2SubPageRow(no, pageSize);
				// 遍历订阅信息
				for(Db2SubVo vo : subList){
					// 获取订阅用户信息
					UserInfoVo subUser = mblogUserService.getUserInfo(vo.getSubscribeUser());
					if(null != subUser){
						vo.setUserCode(subUser.getUserCode());
						vo.setOrgCode(subUser.getOrgCode());
						vo.setTenantCode(subUser.getTenantCode());
					}
					// 获取被订阅用户信息
					UserInfoVo subedUser = mblogUserService.getUserInfo(vo.getPublishUser());
					if(null != subedUser){
						vo.setSubUserCode(subedUser.getUserCode());
						vo.setSubOrgCode(subedUser.getOrgCode());
					}
					
				}
				// 插入订阅信息
				mblogService.insertBatchSub(subList);
				// 任务---执行中
				mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_SUB,no+1);

			}
			// 任务---成功
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_SUB, TransferTaskConstants.TASK_STATUS_END);
		}catch(Exception e){
			e.printStackTrace();
			// 任务---失败
			mblogTaskService.updateTask(TransferTaskConstants.TASK_TYPE_SUB, TransferTaskConstants.TASK_STATUS_FAIL);
			return;
		}

	}
}

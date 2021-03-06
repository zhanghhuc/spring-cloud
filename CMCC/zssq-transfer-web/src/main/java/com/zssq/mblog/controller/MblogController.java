package com.zssq.mblog.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.mblog.pojo.ThreadPoolVo;
import com.zssq.mblog.pojo.TransferTaskConstants;
import com.zssq.mblog.pojo.TransferTaskVo;
import com.zssq.mblog.service.MblogService;
import com.zssq.pojo.ResultJSON;

@Controller
@RequestMapping("/mblog")
public class MblogController {
	
	/** log对象 **/
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MblogService mblogService;
	
	@RequestMapping(value = "/transfer/executeTask")  
	@ResponseBody
    public ResultJSON executeTask(HttpServletRequest req,HttpServletResponse res){
		int pageNo = 0;
		int pageSize = 1000;
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
		// 执行任务
		int pageNo = 0;
		int pageSize = 1000;
		
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
		body.put("taskList", mblogService.getTaskList());
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
    public ResultJSON check(HttpServletRequest req,HttpServletResponse res,Byte taskType){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		try{
			int runStatus = 0;
			List<TransferTaskVo> taskList = mblogService.getTaskList();
			for(TransferTaskVo vo : taskList){
				if(vo.getTaskStatus() != TransferTaskConstants.TASK_STATUS_NEW){
					runStatus = 1;
					break;
				}
				/*switch(taskType){
					case TransferTaskConstants.TASK_TYPE_FOR:{
						if(vo.getTaskType() == TransferTaskConstants.TASK_TYPE_SELF){
							if(vo.getTaskStatus() == TransferTaskConstants.TASK_STATUS_END){
								runStatus = 1;
							}
						}
					}
					case TransferTaskConstants.TASK_TYPE_FOR_PRO:{
						if(vo.getTaskType() == TransferTaskConstants.TASK_TYPE_FOR){
							if(vo.getTaskStatus() == TransferTaskConstants.TASK_STATUS_END){
								runStatus = 1;
							}
						}
					}
					case TransferTaskConstants.TASK_TYPE_COMMENT:{
						if(vo.getTaskType() == TransferTaskConstants.TASK_TYPE_FOR){
							if(vo.getTaskStatus() == TransferTaskConstants.TASK_STATUS_END){
								runStatus = 1;
							}
						}
					}
					case TransferTaskConstants.TASK_TYPE_AT:{
						if(vo.getTaskType() == TransferTaskConstants.TASK_TYPE_FOR){
							if(vo.getTaskStatus() == TransferTaskConstants.TASK_STATUS_END){
								runStatus = 1;
							}
						}
					}
				}*/
			}
			body.put("runStatus", runStatus);
		}catch(Exception e){
			body.put("runStatus", 0);
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
		final int rows = 1000;
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
									public void run() {
										// 执行---迁移图片
										transferImg(startNo,rows);
									}
								});
								ThreadPoolVo.execute(new Runnable() {
									public void run() {
										// 执行---迁移视频
										transferVideo(startNo, rows);
									}
								});
								ThreadPoolVo.execute(new Runnable() {
									public void run() {
										// 执行---迁移音频
										transferMusic(startNo, rows);
									}
								});
								ThreadPoolVo.execute(new Runnable() {
									public void run() {
										// 执行---迁移数量
										transferNum(startNo, rows);
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
								public void run() {
									// 执行---迁移图片
									transferImg(startNo,rows);
								}
							});
							ThreadPoolVo.execute(new Runnable() {
								public void run() {
									// 执行---迁移视频
									transferVideo(startNo, rows);
								}
							});
							ThreadPoolVo.execute(new Runnable() {
								public void run() {
									// 执行---迁移音频
									transferMusic(startNo, rows);
								}
							});
							ThreadPoolVo.execute(new Runnable() {
								public void run() {
									// 执行---迁移数量
									transferNum(startNo, rows);
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
			case TransferTaskConstants.TASK_TYPE_IMG:{ // 图片
				ThreadPoolVo.execute(new Runnable() {
					public void run() {
						transferImg(no,rows);
					}
				});
				break;
			}
			case TransferTaskConstants.TASK_TYPE_VIDEO:{ // 视频
				ThreadPoolVo.execute(new Runnable() {
					public void run() {
						transferVideo(no,rows);
					}
				});
				break;
			}
			case TransferTaskConstants.TASK_TYPE_MUSIC:{ // 音频
				ThreadPoolVo.execute(new Runnable() {
					public void run() {
						transferMusic(no,rows);
					}
				});
				break;
			}
			case TransferTaskConstants.TASK_TYPE_NUM:{ // 音频
				ThreadPoolVo.execute(new Runnable() {
					public void run() {
						transferNum(no,rows);
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
	
	@RequestMapping(value = "/transfer/img")  
	@ResponseBody
    public ResultJSON executeImg(HttpServletRequest req,HttpServletResponse res,Integer pageNo,Integer pageSize){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		final int no = pageNo;
		final int rows = pageSize;
		ThreadPoolVo.execute(new Runnable() {
			public void run() {
				transferImg(no, rows);
			}
		});
		//body.put("list", mblogDb2Service.getDb2SubPageRow(2, 100));
		result.setBody(body);
		return result;
	}
	
	@RequestMapping(value = "/transfer/video")  
	@ResponseBody
    public ResultJSON executeVideo(HttpServletRequest req,HttpServletResponse res,Integer pageNo,Integer pageSize){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		final int no = pageNo;
		final int rows = pageSize;
		ThreadPoolVo.execute(new Runnable() {
			public void run() {
				transferVideo(no, rows);
			}
		});
		//body.put("list", mblogDb2Service.getDb2SubPageRow(2, 100));
		result.setBody(body);
		return result;
	}
	
	@RequestMapping(value = "/transfer/music")  
	@ResponseBody
    public ResultJSON executeMusic(HttpServletRequest req,HttpServletResponse res,Integer pageNo,Integer pageSize){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		final int no = pageNo;
		final int rows = pageSize;
		ThreadPoolVo.execute(new Runnable() {
			public void run() {
				transferMusic(no, rows);
			}
		});
		//body.put("list", mblogDb2Service.getDb2SubPageRow(2, 100));
		result.setBody(body);
		return result;
	}
	
	@RequestMapping(value = "/transfer/num")  
	@ResponseBody
    public ResultJSON executeNum(HttpServletRequest req,HttpServletResponse res,Integer pageNo,Integer pageSize){
		ResultJSON result = new ResultJSON("COMMON_200");
		JSONObject body = new JSONObject();
		final int no = pageNo;
		final int rows = pageSize;
		ThreadPoolVo.execute(new Runnable() {
			public void run() {
				transferNum(no, rows);
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
							public void run() {
								// 执行---迁移图片
								transferImg(pageNo,pageSize);
							}
						});
						ThreadPoolVo.execute(new Runnable() {
							public void run() {
								// 执行---迁移视频
								transferVideo(pageNo, pageSize);
							}
						});
						ThreadPoolVo.execute(new Runnable() {
							public void run() {
								// 执行---迁移音频
								transferMusic(pageNo, pageSize);
							}
						});
						ThreadPoolVo.execute(new Runnable() {
							public void run() {
								// 执行---迁移数量
								transferNum(pageNo, pageSize);
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
				pageSize = 1000;
			}
			// 获取原创微博总数
			int count = mblogService.getSelfMblogCount();
			log.info("获取到原创微博总数：=====>>>>"+count);
			int totalPage = count/pageSize + 1;
			log.info("获取到原创微博总页数：=====>>>>"+totalPage);
			// 更新任务状态
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_SELF, TransferTaskConstants.TASK_STATUS_RUNING);
			// 更新任务数量
			mblogService.updateTaskTotal(TransferTaskConstants.TASK_TYPE_SELF,totalPage,count);
			// 获取数据
			for(int no = pageNo ; no < totalPage;no++){
				log.info("执行原创微博信息start：=====>>>>"+no);
				mblogService.transferSelfMblogInfo(no, pageSize);
				log.info("执行原创微博信息end：=====>>>>"+no);
				// 任务---执行中
				mblogService.updateTaskPage(TransferTaskConstants.TASK_TYPE_SELF, no+1);
			}
			// 任务---成功
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_SELF, TransferTaskConstants.TASK_STATUS_END);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			// 任务---失败
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_SELF, TransferTaskConstants.TASK_STATUS_FAIL);
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
				pageSize = 1000;
			}
			// 获取转发微博数量
			int count = mblogService.getForMblogCount();
			log.info("获得到转发微博总数：=========>>>>"+count);
			int totalPage = count/pageSize + 1;
			log.info("获得到转发微博总页数：=========>>>>"+totalPage);
			// 更新任务状态
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_FOR, TransferTaskConstants.TASK_STATUS_RUNING);
			// 更新任务数量
			mblogService.updateTaskTotal(TransferTaskConstants.TASK_TYPE_FOR,totalPage,count);
			// 获取数据
			for(int no = pageNo ; no < totalPage;no++){
				log.info("执行转发微博信息start：=====>>>>"+no);
				mblogService.transferForMblogInfo(no, pageSize);
				log.info("执行转发微博信息end：=====>>>>"+no);
				// 任务---执行中
				mblogService.updateTaskPage(TransferTaskConstants.TASK_TYPE_FOR, no+1);
			}
			// 任务---成功
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_FOR, TransferTaskConstants.TASK_STATUS_END);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			// 任务---失败
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_FOR, TransferTaskConstants.TASK_STATUS_FAIL);
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
				pageSize = 1000;
			}
			// 获取转发微博数量
			int count = mblogService.getForMblogCount();
			log.info("获得到转发微博总数：=========>>>>"+count);
			int totalPage = count/pageSize + 1;
			log.info("获得到转发微博总页数：=========>>>>"+totalPage);
			// 更新任务状态
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_FOR_PRO, TransferTaskConstants.TASK_STATUS_RUNING);
			// 更新任务数量
			mblogService.updateTaskTotal(TransferTaskConstants.TASK_TYPE_FOR_PRO,totalPage,count);
			// 获取数据
			for(int no = pageNo ; no < totalPage;no++){
				log.info("执行转发微博历程start：=====>>>>"+no);
				mblogService.transferForPro(no, pageSize);
				log.info("执行转发微博历程end：=====>>>>"+no);
				// 任务----执行中
				mblogService.updateTaskPage(TransferTaskConstants.TASK_TYPE_FOR_PRO, no+1);
			}
			// 任务---成功
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_FOR_PRO, TransferTaskConstants.TASK_STATUS_END);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			// 任务---失败
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_FOR_PRO, TransferTaskConstants.TASK_STATUS_FAIL);
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
				pageSize = 1000;
			}
			int count = mblogService.getCommentCount();
			log.info("获取评论总数：======>>>>>>"+count);
			int totalPage = count/pageSize + 1;
			log.info("获取评论的总页数：======>>>>>>>"+totalPage);
			// 更新任务状态
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_COMMENT, TransferTaskConstants.TASK_STATUS_RUNING);
			// 更新任务数量
			mblogService.updateTaskTotal(TransferTaskConstants.TASK_TYPE_COMMENT,totalPage,count);

			// 获取数据
			for(int no = pageNo ; no < totalPage;no++){
				log.info("执行评论start：=====>>>>"+no);
				mblogService.transferComment(no, pageSize);
				log.info("执行评论end：=====>>>>"+no);
				// 任务---执行中
				mblogService.updateTaskPage(TransferTaskConstants.TASK_TYPE_COMMENT, no+1);
			}
			// 任务---成功
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_COMMENT, TransferTaskConstants.TASK_STATUS_END);
		}catch(Exception e){
			e.printStackTrace();
			// 任务---失败
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_COMMENT, TransferTaskConstants.TASK_STATUS_FAIL);
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
				pageSize = 1000;
			}
			int count = mblogService.getTopicCount();
			log.info("获取的话题总数：=======>>>>>>"+count);
			int totalPage = count/pageSize + 1;
			log.info("获取的话题的总页数：=======>>>>>>>"+totalPage);
			// 更新任务状态
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_TOPIC, TransferTaskConstants.TASK_STATUS_RUNING);
			// 更新任务数量
			mblogService.updateTaskTotal(TransferTaskConstants.TASK_TYPE_TOPIC,totalPage,count);

			// 获取数据
			for(int no = pageNo ; no < totalPage;no++){
				log.info("执行话题Start：=====>>>>"+no);
				mblogService.transferTopic(no, pageSize);
				log.info("执行话题end：=====>>>>"+no);
				// 任务---执行中
				mblogService.updateTaskPage(TransferTaskConstants.TASK_TYPE_TOPIC, no+1);
			}
			// 任务---成功
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_TOPIC, TransferTaskConstants.TASK_STATUS_END);
		}catch(Exception e){
			e.printStackTrace();
			// 任务---失败
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_TOPIC, TransferTaskConstants.TASK_STATUS_FAIL);
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
				pageSize = 1000;
			}
			int count = mblogService.getAtCount();
			log.info("获取AT信息总数：======>>>>>>"+count);
			int totalPage = count/pageSize + 1;
			log.info("获取AT信息总页数:=======>>>>>>>"+totalPage);
			// 更新任务状态
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_AT, TransferTaskConstants.TASK_STATUS_RUNING);
			// 更新任务数量
			mblogService.updateTaskTotal(TransferTaskConstants.TASK_TYPE_AT,totalPage,count);

			// 获取数据
			for(int no = pageNo ; no < totalPage;no++){
				log.info("执行atStart：=====>>>>"+no);
				mblogService.transferAt(no, pageSize);
				log.info("执行atEnd：=====>>>>"+no);
				// 任务---执行中
				mblogService.updateTaskPage(TransferTaskConstants.TASK_TYPE_AT, no+1);
			}
			// 任务---成功
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_AT, TransferTaskConstants.TASK_STATUS_END);
		}catch(Exception e){
			e.printStackTrace();
			// 任务---失败
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_AT, TransferTaskConstants.TASK_STATUS_FAIL);
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
				pageSize = 1000;
			}
			int count = mblogService.getSubCount();
			log.info("获取的订阅总数：======>>>>>>"+count);
			int totalPage = count/pageSize + 1;
			log.info("获取的订阅总页数：=======>>>>>>"+totalPage);
			// 更新任务状态
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_SUB, TransferTaskConstants.TASK_STATUS_RUNING);
			// 更新任务数量
			mblogService.updateTaskTotal(TransferTaskConstants.TASK_TYPE_SUB,totalPage,count);

			// 获取数据
			for(int no = pageNo ; no < totalPage;no++){
				log.info("执行订阅Start：=====>>>>"+no);
				mblogService.transferSub(no, pageSize);
				log.info("执行订阅End：=====>>>>"+no);
				// 任务---执行中
				mblogService.updateTaskPage(TransferTaskConstants.TASK_TYPE_SUB,no+1);

			}
			// 任务---成功
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_SUB, TransferTaskConstants.TASK_STATUS_END);
		}catch(Exception e){
			e.printStackTrace();
			// 任务---失败
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_SUB, TransferTaskConstants.TASK_STATUS_FAIL);
			return;
		}

	}
	
	/**
	 * 
	    * @Title: transferImg  
	    * @Description: 迁移图片
	    * @param pageNo
	    * @param pageSize
		* @return void    返回类型
	 */
	private void transferImg(int pageNo,int pageSize){
		try{
			if(pageNo < 0){
				pageNo = 0;
			}
			if(pageSize <= 0){
				pageSize = 1000;
			}
			int count = mblogService.getImgCount();
			log.info("获取的图片总数：======>>>>>>"+count);
			int totalPage = count/pageSize + 1;
			log.info("获取的图片总页数：=======>>>>>>"+totalPage);
			// 更新任务状态
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_IMG, TransferTaskConstants.TASK_STATUS_RUNING);
			// 更新任务数量
			mblogService.updateTaskTotal(TransferTaskConstants.TASK_TYPE_IMG,totalPage,count);

			// 获取数据
			for(int no = pageNo ; no < totalPage;no++){
				log.info("执行图片Start：=====>>>>"+no);
				mblogService.transferSub(no, pageSize);
				log.info("执行图片End：=====>>>>"+no);
				// 任务---执行中
				mblogService.updateTaskPage(TransferTaskConstants.TASK_TYPE_IMG,no+1);

			}
			// 任务---成功
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_IMG, TransferTaskConstants.TASK_STATUS_END);
		}catch(Exception e){
			e.printStackTrace();
			// 任务---失败
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_IMG, TransferTaskConstants.TASK_STATUS_FAIL);
			return;
		}

	}
	
	/**
	 * 
	    * @Title: transferVideo  
	    * @Description: 迁移视频
	    * @param pageNo
	    * @param pageSize
		* @return void    返回类型
	 */
	private void transferVideo(int pageNo,int pageSize){
		try{
			if(pageNo < 0){
				pageNo = 0;
			}
			if(pageSize <= 0){
				pageSize = 1000;
			}
			int count = mblogService.getVideoCount();
			log.info("获取的视频总数：======>>>>>>"+count);
			int totalPage = count/pageSize + 1;
			log.info("获取的视频总页数：=======>>>>>>"+totalPage);
			// 更新任务状态
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_VIDEO, TransferTaskConstants.TASK_STATUS_RUNING);
			// 更新任务数量
			mblogService.updateTaskTotal(TransferTaskConstants.TASK_TYPE_VIDEO,totalPage,count);

			// 获取数据
			for(int no = pageNo ; no < totalPage;no++){
				log.info("执行视频Start：=====>>>>"+no);
				mblogService.transferSub(no, pageSize);
				log.info("执行视频End：=====>>>>"+no);
				// 任务---执行中
				mblogService.updateTaskPage(TransferTaskConstants.TASK_TYPE_VIDEO,no+1);

			}
			// 任务---成功
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_VIDEO, TransferTaskConstants.TASK_STATUS_END);
		}catch(Exception e){
			e.printStackTrace();
			// 任务---失败
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_VIDEO, TransferTaskConstants.TASK_STATUS_FAIL);
			return;
		}

	}
	
	/**
	 * 
	    * @Title: transferMusic  
	    * @Description: 迁移音频
	    * @param pageNo
	    * @param pageSize
		* @return void    返回类型
	 */
	private void transferMusic(int pageNo,int pageSize){
		try{
			if(pageNo < 0){
				pageNo = 0;
			}
			if(pageSize <= 0){
				pageSize = 1000;
			}
			int count = mblogService.getMusicCount();
			log.info("获取的音频总数：======>>>>>>"+count);
			int totalPage = count/pageSize + 1;
			log.info("获取的音频总页数：=======>>>>>>"+totalPage);
			// 更新任务状态
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_MUSIC, TransferTaskConstants.TASK_STATUS_RUNING);
			// 更新任务数量
			mblogService.updateTaskTotal(TransferTaskConstants.TASK_TYPE_MUSIC,totalPage,count);

			// 获取数据
			for(int no = pageNo ; no < totalPage;no++){
				log.info("执行视频Start：=====>>>>"+no);
				mblogService.transferSub(no, pageSize);
				log.info("执行视频End：=====>>>>"+no);
				// 任务---执行中
				mblogService.updateTaskPage(TransferTaskConstants.TASK_TYPE_MUSIC,no+1);

			}
			// 任务---成功
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_MUSIC, TransferTaskConstants.TASK_STATUS_END);
		}catch(Exception e){
			e.printStackTrace();
			// 任务---失败
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_MUSIC, TransferTaskConstants.TASK_STATUS_FAIL);
			return;
		}

	}
	
	/**
	 * 
	    * @Title: transferNum  
	    * @Description: 迁移数量
	    * @param pageNo
	    * @param pageSize
		* @return void    返回类型
	 */
	private void transferNum(int pageNo,int pageSize){
		try{
			if(pageNo < 0){
				pageNo = 0;
			}
			if(pageSize <= 0){
				pageSize = 1000;
			}
			int count = mblogService.getNumCount();
			log.info("获取的数量总数：======>>>>>>"+count);
			int totalPage = count/pageSize + 1;
			log.info("获取的数量总页数：=======>>>>>>"+totalPage);
			// 更新任务状态
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_NUM, TransferTaskConstants.TASK_STATUS_RUNING);
			// 更新任务数量
			mblogService.updateTaskTotal(TransferTaskConstants.TASK_TYPE_NUM,totalPage,count);

			// 获取数据
			for(int no = pageNo ; no < totalPage;no++){
				log.info("执行数量Start：=====>>>>"+no);
				mblogService.transferNum(no, pageSize);
				log.info("执行数量End：=====>>>>"+no);
				// 任务---执行中
				mblogService.updateTaskPage(TransferTaskConstants.TASK_TYPE_NUM,no+1);

			}
			// 任务---成功
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_NUM, TransferTaskConstants.TASK_STATUS_END);
		}catch(Exception e){
			e.printStackTrace();
			// 任务---失败
			mblogService.updateTaskStatus(TransferTaskConstants.TASK_TYPE_NUM, TransferTaskConstants.TASK_STATUS_FAIL);
			return;
		}

	}
}

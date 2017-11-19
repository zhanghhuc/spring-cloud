<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务列表</title>
<style type="text/css">
	*{
		margin:0;
		padding:0;
	}
	.wrap{
		width:100%;
		position: absolute;
		background-color: aquamarine;
		border: solid 2px #00ff00;
		/* top:50%;
		left:50%;
		-moz-transform: translate(-50%,-50%);
		-webkit-transform:translate(-50%,-50%); */
	}
	.content{
		margin:0 auto;
		width:100%;
		height:100%;
		/* border:solid 2px #fed654; */
	}
	.content table{
		width:95%;
		margin-left:5%;
		border:solid #fde645; 
		border-width:1px 0px 0px 1px;
	}
	.content table td,th{
		border:solid #fde645; 
		border-width:0px 1px 1px 0px; 
		padding:10px 0px;
	}
	a{  
	    cursor:pointer;  
	    text-decoration:none;  
	    hide-focus: expression(this.hideFocus=true);  
	    outline:none;  
	}  
	a:link,a:visited,a:hover,a:active{  
	    text-decoration:none;  
	}  
  
	a:focus{  
	    outline:0;   
	}  
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath }/mblog/res/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/mblog/res/js/ejs.js"></script>
	<script type="text/javascript">
		//时间格式转换
		Date.prototype.format = function(format){
			var o = {
				"M+" : this.getMonth()+1, //month
				"d+" : this.getDate(), //day
				"h+" : this.getHours(), //hour
				"m+" : this.getMinutes(), //minute
				"s+" : this.getSeconds(), //second
				"q+" : Math.floor((this.getMonth()+3)/3), //quarter
				"S" : this.getMilliseconds() //millisecond
			}
	
			if(/(y+)/.test(format)) {
				format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
			}
	
			for(var k in o) {
				if(new RegExp("("+ k +")").test(format)) {
					format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
				}
			}
			return format;
		}
		$(function(){
			// 访问数据
			_requestPost = function (url,params,callback){
				$.ajax({  
					url:"${pageContext.request.contextPath }/mblog/"+url, 
					type:'post', 
					dataType:'json', 
					data:params,
					contentType: 'application/x-www-form-urlencoded; charset=utf-8', 
					error:function(){ 
					},  
					success:function(data){
						callback(data);
					}
				});
			};
			// 校验是否开始任务
			_check = function(){
				// 获取数据
				_requestPost("transfer/check",{},function(data){
					var taskStatus = data.body.runStatus;
					if(0 == taskStatus){
						$("#btn_div").html('<a href="javascript:void(0);" onclick="_init();" style="margin-left:10px;margin-top:10px;">初始化</a>');
					}else{
						$("#btn_div").html("任务列表");
						// 获取任务列表
						_taskList();
					}
				});
			}
			// 获取任务列表数据
			_taskList = function(){
				// 初始化任务
				_requestPost("transfer/getTaskList",{},function(data){
					var taskList = data.body.taskList;
					for(var i = 0;i < taskList.length;i++){
						if(taskList[i]["startTime"] > 0){
							taskList[i]["startTime"] = new Date(taskList[i]["startTime"]).format("yyyy-MM-dd hh:mm:ss");
						}else{
							taskList[i]["startTime"] =  "";
						}
						if(taskList[i]["endTime"] > 0){
							taskList[i]["endTime"] = new Date(taskList[i]["endTime"]).format("yyyy-MM-dd hh:mm:ss");
						}else{
							taskList[i]["endTime"] = "";
						}
						if(taskList[i]["failTime"] > 0){
							taskList[i]["failTime"] = new Date(taskList[i]["failTime"]).format("yyyy-MM-dd hh:mm:ss");
						}else{
							taskList[i]["failTime"] = "";
						}
					}
					// 成功
					var taskEjs = new EJS({url:"res/ejs/task.ejs"});
					var opts = {"taskList":taskList};
					$("#taskList").html(taskEjs.render(opts));
				});
			}
			// 初始化任务数据
			_init = function(){
				// 初始化任务
				_requestPost("transfer/init",{},function(data){
					// 获取任务列表
					_taskList();
				});
			}
			// 执行任务
			_execute = function(taskType,pageNo){
				var params = {
						"taskType":taskType,
						"pageNo":pageNo
				}
				// 初始化任务
				_requestPost("transfer/execute",params,function(data){
					// 获取任务列表
					_taskList();
				});
			}
			// 页面初始化
			initPage = function(){
				_check();
				//_taskList();
			}
			initPage();
		});
	</script>
</head>
<body>
	<div class="wrap">
		<div class="content">
			<div style="border-bottom: solid #fde645" id="btn_div">
				
			</div>
			<table id="taskList">
			</table>
		</div>
	</div>
</body>
</html>
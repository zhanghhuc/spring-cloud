<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	pageContext.setAttribute("basePath", basePath);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>知识社区项目源数据 </title>
	<script type="text/javascript" src="${pageScope.basePath }js/jquery-1.9.1.min.js"></script>
	<style type="text/css">
	.divcss5 {
		width: 49%;
		height: 350px;
		border: 1px solid #F00;
		float: left
	}
	.divcss6 {
		width: 98%;
		height: 800px;
		border: 1px solid #F00;
		float: left
	}
	</style>
	<script type="text/javascript">
		$(function(){
			$("#transferAllVote").click(function(){
				if(confirm("迁移所有投票源数据吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }transfer/vote/all',
			    	    type:'POST', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    data:{
			    	        threadCount:$("#vote_thread_count").val()
			    	    },
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	alert(data.msg);
			    	    }
			    	})
				}
			});
			$("#transferSingleVote").click(function(){
				var voteId = $("#voteId").val();
				if(voteId==''){
					alert("请输入投票ID");
					$("#voteId").focus();
					return;
				}
				if(confirm("确定迁移vote_id="+voteId+"投票源数据吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }transfer/vote/single',
			    	    type:'POST', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    data:{
			    	        voteId:voteId
			    	    },
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	alert(data.msg);
			    	    }
			    	})
				}
			});
			
			//组织机构迁移
			$("#transferOrgBase").click(function(){
				if(confirm("确定开始迁移组织机构吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }orgTransfer/transferBase',
			    	    type:'GET', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'text', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	alert(data);
			    	    }
			    	})
				}
			});
			//建立组织上下级关系
			$("#updateParentCode").click(function(){
				if(confirm("确定建立组织上下级关系吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }orgTransfer/processPraentCodeAndOrgType',
			    	    type:'GET', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'text', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	alert(data);
			    	    }
			    	})
				}
			});
			//变更所属行政组织
			$("#updateManOrgCode").click(function(){
				if(confirm("确定变更所属行政组织吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }orgTransfer/processManOrgCode',
			    	    type:'GET', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'text', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	alert(data);
			    	    }
			    	})
				}
			});
			//迁移用户
			$("#transferUser").click(function(){
				if(confirm("确定开始迁移用户吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }userTransfer/executeTransfer',
			    	    type:'GET', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'text', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	alert(data);
			    	    }
			    	})
				}
			});
			//个人的积分、金币账户迁移
			$("#transferUserAccount").click(function(){
				if(confirm("确定开始迁移个人的积分、金币账户吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }creditAccountTransfer/user',
			    	    type:'GET', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'text', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	alert(data);
			    	    }
			    	})
				}
			});
			//班组的积分、金币账户迁移
			$("#transferTeamAccount").click(function(){
				if(confirm("确定开始迁移班组的积分、金币账户吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }creditAccountTransfer/team',
			    	    type:'GET', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'text', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	alert(data);
			    	    }
			    	})
				}
			});
			//金币明细迁移
			$("#transferIntegralDetail").click(function(){
				if(confirm("确定开始迁移金币明细吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }creditDetailTransfer/integral',
			    	    type:'GET', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'text', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	alert(data);
			    	    }
			    	})
				}
			});
			//积分明细迁移
			$("#transferCoinDetail").click(function(){
				if(confirm("确定开始迁移积分明细吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }creditDetailTransfer/coin',
			    	    type:'GET', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'text', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	alert(data);
			    	    }
			    	})
				}
			});
			//好友分组迁移
			$("#transferFriendGroup").click(function(){
				if(confirm("确定开始迁移好友分组吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }userRelationTransfer/transferUserFriendGroup',
			    	    type:'GET', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'text', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	alert(data);
			    	    }
			    	})
				}
			});
			//用户好友迁移
			$("#transferFriend").click(function(){
				if(confirm("确定开始迁移用户好友吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }userRelationTransfer/transferUserFriends',
			    	    type:'GET', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'text', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	alert(data);
			    	    }
			    	})
				}
			});
			//用户关注迁移
			$("#transferUserConcern").click(function(){
				if(confirm("确定开始迁移用户关注吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }userRelationTransfer/transferUserConcerns',
			    	    type:'GET', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'text', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	alert(data);
			    	    }
			    	})
				}
			});
			//敏感词迁移
			$("#transferSenstiveWord").click(function(){
				if(confirm("确定开始迁移敏感词吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }userRelationTransfer/transferSenstiveWord',
			    	    type:'GET', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'text', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	alert(data);
			    	    }
			    	})
				}
			});
			//班组信息迁移
			$("#transferTeamInfo").click(function(){
				if(confirm("确定开始迁移班组信息吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }team/insert/info',
			    	    type:'GET', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'text', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	alert(data);
			    	    }
			    	})
				}
			});
			//班组成员迁移
			$("#transferTeamMember").click(function(){
				if(confirm("确定开始迁移班组成员吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }team/insert/member',
			    	    type:'GET', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'text', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	alert(data);
			    	    }
			    	})
				}
			});
			//百强班组迁移
			$("#transferHonorTeam").click(function(){
				if(confirm("确定开始迁移百强班组吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }team/insert/honor',
			    	    type:'GET', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'text', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	alert(data);
			    	    }
			    	})
				}
			});
			//班组论坛迁移
			$("#transferForum").click(function(){
				if(confirm("确定开始迁移班组论坛吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }forum/plate',
			    	    type:'GET', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	alert(data.msg);
			    	    }
			    	})
				}
			});
			
			//推荐数据迁移
			$("#transferRecommend").click(function(){
				if(confirm("确定开始迁移推荐数据吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }relationRecommend/batchImportRecommend',
			    	    type:'GET', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	$("#tRecommend").html(data.body.message);
			    	    	alert(data.body.message);
			    	    }
			    	})
				}
			});
			
			//迁移推荐微博信息到内容数据表
			$("#transferRecommendWeiBo").click(function(){
				if(confirm("确定开始迁移推荐微博信息到内容数据吗？")){
					$.ajax({
			    	    url:'${pageScope.basePath }relationRecommend/batchImportMblog',
			    	    type:'GET', //GET
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	$("#tRecommendWeibo").html(data.body.message);
			    	    	alert(data.body.message);
			    	    }
			    	})
				}
			});
			
			// 博客订阅信息迁移
			$("#transferBlogSubData").click(function(){
				if(confirm("确定开始迁移博客订阅信息吗？")){
					$('#transferBlogSubDataMessage').html("处理中...");
					$.ajax({
			    	    url:'${pageScope.basePath }blogSubController/transferSubData',
			    	    type:'POST', //POST
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	$('#transferBlogSubDataMessage').html(data.body.message);
			    	    	alert(data.body.message);
			    	    }
			    	})
				}
			});
			// 博客分类信息迁移
			$("#transferBlogClassData").click(function(){
				var blogClassId = $('#blogClassId').val();
				if(confirm("确定开始迁移博客分类信息吗？")){
	    	    	$('#transferBlogClassDataMessage').html("处理中...");
					$.ajax({
			    	    url:'${pageScope.basePath }blogClassController/transferClassData',
			    	    type:'POST', //POST
			    	    data:{startClassId:blogClassId},
			    	    async:true,    // ture为异步请求，false为同步请求
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	$('#transferBlogClassDataMessage').html(data.body.message);
			    	    	alert(data.body.message);
			    	    }
			    	})
				}
			});
			// 个人博客信息迁移
			$("#transferUserBlogData").click(function(){
				var userBlogPageNo = $('#userBlogPageNo').val();
				var userBlogPageSize = $('#userBlogPageSize').val();
				if(confirm("确定开始迁移个人博客信息吗？")){
	    	    	$('#transferUserBlogDataMessage').html("处理中...");
					$.ajax({
			    	    url:'${pageScope.basePath }blogController/transferUserBlogData',
			    	    type:'POST', //POST
			    	    data:{pageNo:userBlogPageNo,pageSize:userBlogPageSize},
			    	    async:true,	// ture为异步请求，false为同步请求
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	$('#transferUserBlogDataMessage').html(data.body.message);
			    	    	alert(data.body.message);
			    	    }
			    	})
				}
			});
			// 班组博客信息迁移
			$("#transferTeamBlogData").click(function(){
				var teamBlogPageNo = $('#teamBlogPageNo').val();
				var teamBlogPageSize = $('#teamBlogPageSize').val();
				if(confirm("确定开始迁移班组博客信息吗？")){
	    	    	$('#transferTeamBlogDataMessage').html("处理中...");
					$.ajax({
			    	    url:'${pageScope.basePath }blogController/transferTeamBlogData',
			    	    type:'POST', //POST
			    	    data:{pageNo:teamBlogPageNo,pageSize:teamBlogPageSize},
			    	    async:true,	// ture为异步请求，false为同步请求
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	$('#transferTeamBlogDataMessage').html(data.body.message);
			    	    	alert(data.body.message);
			    	    }
			    	})
				}
			});
			// 个人草稿信息迁移
			$("#transferUserBlogDraftData").click(function(){
				var userDraftPageNo = $('#userDraftPageNo').val();
				var userDraftPageSize = $('#userDraftPageSize').val();
				if(confirm("确定开始迁移个人草稿信息吗？")){
	    	    	$('#transferUserBlogDraftDataMessage').html("处理中...");
					$.ajax({
			    	    url:'${pageScope.basePath }blogDraftController/transferUserDraftData',
			    	    type:'POST', //POST
			    	    data:{pageNo:userDraftPageNo,pageSize:userDraftPageSize},
			    	    async:true,	// ture为异步请求，false为同步请求
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	$('#transferUserBlogDraftDataMessage').html(data.body.message);
			    	    	alert(data.body.message);
			    	    }
			    	})
				}
			});
			// 班组草稿信息迁移
			$("#transferTeamBlogDraftData").click(function(){
				if(confirm("确定开始迁移班组草稿信息吗？")){
	    	    	$('#transferTeamBlogDraftDataMessage').html("处理中...");
					$.ajax({
			    	    url:'${pageScope.basePath }blogDraftController/transferTeamDraftData',
			    	    type:'POST', //POST
			    	    async:true,	// ture为异步请求，false为同步请求
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	$('#transferTeamBlogDraftDataMessage').html(data.body.message);
			    	    	alert(data.body.message);
			    	    }
			    	})
				}
			});
			// 博客内容信息迁移
			$("#transferBlogContent").click(function(){
				if(confirm("确定开始迁移博客内容信息吗？")){
	    	    	$('#transferBlogContentMessage').html("处理中...");
					$.ajax({
			    	    url:'${pageScope.basePath }blogController/transferBlogContent',
			    	    type:'POST', //POST
			    	    async:true,	// ture为异步请求，false为同步请求
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	$('#transferBlogContentMessage').html(data.body.message);
			    	    	alert(data.body.message);
			    	    }
			    	})
				}
			});
			// 草稿内容信息迁移
			$("#transferBlogDraftContent").click(function(){
				if(confirm("确定开始迁移草稿内容信息吗？")){
	    	    	$('#transferBlogDraftContentMessage').html("处理中...");
					$.ajax({
			    	    url:'${pageScope.basePath }blogDraftController/transferDraftContent',
			    	    type:'POST', //POST
			    	    async:true,	// ture为异步请求，false为同步请求
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	$('#transferBlogDraftContentMessage').html(data.body.message);
			    	    	alert(data.body.message);
			    	    }
			    	})
				}
			});
			// 博客评论信息迁移
			$("#transferBlogCommentData").click(function(){
				if(confirm("确定开始迁移博客评论信息吗？")){
	    	    	$('#transferBlogCommentDataMessage').html("处理中...");
					$.ajax({
			    	    url:'${pageScope.basePath }blogCommentController/transferCommentData',
			    	    type:'POST', //POST
			    	    async:true,	// ture为异步请求，false为同步请求
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	$('#transferBlogCommentDataMessage').html(data.body.message);
			    	    	alert(data.body.message);
			    	    }
			    	})
				}
			});
			// 评论回复信息迁移
			$("#transferBlogReplyData").click(function(){
				if(confirm("确定开始迁移评论回复信息吗？")){
	    	    	$('#transferBlogReplyDataMessage').html("处理中...");
					$.ajax({
			    	    url:'${pageScope.basePath }blogReplyController/transferReplyData',
			    	    type:'POST', //POST
			    	    async:true,	// ture为异步请求，false为同步请求
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	$('#transferBlogReplyDataMessage').html(data.body.message);
			    	    	alert(data.body.message);
			    	    }
			    	})
				}
			});
			// 博客数据信息迁移
			$("#transferBlogData").click(function(){
				if(confirm("确定开始迁移博客数据信息吗？")){
	    	    	$('#transferBlogDataMessage').html("处理中...");
					$.ajax({
			    	    url:'${pageScope.basePath }blogDataController/transferBlogData',
			    	    type:'POST', //POST
			    	    async:true,	// ture为异步请求，false为同步请求
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	$('#transferBlogDataMessage').html(data.body.message);
			    	    	alert(data.body.message);
			    	    }
			    	})
				}
			});
			// 博客关系信息迁移
			$("#transferRelationBlogData").click(function(){
				if(confirm("确定开始迁移博客关系信息吗？")){
	    	    	$('#transferRelationBlogDataMessage').html("处理中...");
					$.ajax({
			    	    url:'${pageScope.basePath }relationBlogController/transferRelationBlogData',
			    	    type:'POST', //POST
			    	    async:true,	// ture为异步请求，false为同步请求
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	$('#transferRelationBlogDataMessage').html(data.body.message);
			    	    	alert(data.body.message);
			    	    }
			    	})
				}
			});
			// 新闻关系信息迁移
			$("#transferRelationNewsData").click(function(){
				if(confirm("确定开始迁移新闻关系信息吗？")){
	    	    	$('#transferRelationNewsDataMessage').html("处理中...");
					$.ajax({
			    	    url:'${pageScope.basePath }relationNewsController/transferRelationNewsData',
			    	    type:'POST', //POST
			    	    async:true,	// ture为异步请求，false为同步请求
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	$('#transferRelationNewsDataMessage').html(data.body.message);
			    	    	alert(data.body.message);
			    	    }
			    	})
				}
			});
			// 网盘关系信息迁移
			$("#transferRelationDiskData").click(function(){
				if(confirm("确定开始迁移网盘关系信息吗？")){
	    	    	$('#transferRelationDiskDataMessage').html("处理中...");
					$.ajax({
			    	    url:'${pageScope.basePath }relationDiskController/transferRelationDiskData',
			    	    type:'POST', //POST
			    	    async:true,	// ture为异步请求，false为同步请求
			    	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    	    success:function(data){
			    	    	$('#transferRelationDiskDataMessage').html(data.body.message);
			    	    	alert(data.body.message);
			    	    }
			    	})
				}
			});


			//好友分组迁移
			$("#transferNewsInfo").click(function(){
				if(confirm("确定开始迁移好友分组吗？")){
					$.ajax({
						url:'${pageScope.basePath }newsController/transferNewsData?limitStart=0&pageSize=0',
						type:'GET', //GET
						async:true,    // ture为异步请求，false为同步请求
						dataType:'text', //返回的数据格式：json/xml/html/script/jsonp/text
						success:function(data){
							alert(data);
						}
					})
				}
			});

			//好友分组迁移
			$("#transferDisk").click(function(){
				if(confirm("确定开始迁移好友分组吗？")){
					$.ajax({
						url:'${pageScope.basePath }diskFile/transferDiskFileData?limitStart=0&pageSize=0',
						type:'GET', //GET
						async:true,    // ture为异步请求，false为同步请求
						dataType:'text', //返回的数据格式：json/xml/html/script/jsonp/text
						success:function(data){
							alert(data);
						}
					})
				}
			});


	});

		
	</script>
</head>
<body>
	<div class="divcss5">
		<h2 align="center">投票模块源数据迁移</h2>
		迁移所有投票源数据：用<input value="35" id="vote_thread_count" style="width:50px">个线程执行&nbsp;<input type="button" id="transferAllVote" value="开始"/><br>
		<br>
		迁移单条投票源数据：请输入源投票ID(vote_id)：<input id="voteId"/>&nbsp;&nbsp;<input type="button" id="transferSingleVote" value="开始"/><br>
	</div>
	<div class="divcss5">
		<h2 align="center">微博模块源数据迁移</h2>
	</div>
	<div class="divcss6">
		<h2 align="center">博客模块源数据迁移</h2>
		<h3 style="color: red;">(1)</h3>
		迁移博客订阅信息：&nbsp;&nbsp;<input type="button" id="transferBlogSubData" value="开始"/>&nbsp;&nbsp;<label style="color: red" id="transferBlogSubDataMessage"></label><br>
		迁移博客分类信息：原分类开始id<input id="blogClassId" value="0">&nbsp;&nbsp;<input type="button" id="transferBlogClassData" value="开始"/>&nbsp;&nbsp;<label style="color: red" id="transferBlogClassDataMessage"></label> 
		<h3 style="color: red;">(2)</h3>
		迁移个人博客信息：开始页码<input id="userBlogPageNo" value="0">每页条数<input id="userBlogPageSize" value="3000">&nbsp;&nbsp;<input type="button" id="transferUserBlogData" value="开始"/>&nbsp;&nbsp;<label style="color: red" id="transferUserBlogDataMessage"></label><br>
		迁移班组博客信息：开始页码<input id="teamBlogPageNo" value="0">每页条数<input id="teamBlogPageSize" value="3000">&nbsp;&nbsp;<input type="button" id="transferTeamBlogData" value="开始"/>&nbsp;&nbsp;<label style="color: red" id="transferTeamBlogDataMessage"></label><br>
		迁移个人草稿信息：开始页码<input id="userDraftPageNo" value="0">每页条数<input id="userDraftPageSize" value="3000">&nbsp;&nbsp;<input type="button" id="transferUserBlogDraftData" value="开始"/>&nbsp;&nbsp;<label style="color: red" id="transferUserBlogDraftDataMessage"></label><br>
		迁移班组草稿信息：&nbsp;&nbsp;<input type="button" id="transferTeamBlogDraftData" value="开始"/>&nbsp;&nbsp;<label style="color: red" id="transferTeamBlogDraftDataMessage"></label>
		<h3 style="color: red;">(3)</h3>
		迁移博客内容信息：&nbsp;&nbsp;<input type="button" id="transferBlogContent" value="开始"/>&nbsp;&nbsp;<label style="color: red" id="transferBlogContentMessage"></label><br>
		迁移草稿内容信息：&nbsp;&nbsp;<input type="button" id="transferBlogDraftContent" value="开始"/>&nbsp;&nbsp;<label style="color: red" id="transferBlogDraftContentMessage"></label><br>
		迁移博客评论信息：&nbsp;&nbsp;<input type="button" id="transferBlogCommentData" value="开始"/>&nbsp;&nbsp;<label style="color: red" id="transferBlogCommentDataMessage"></label>
		<h3 style="color: red;">(4)</h3>
		迁移评论回复信息：&nbsp;&nbsp;<input type="button" id="transferBlogReplyData" value="开始"/>&nbsp;&nbsp;<label style="color: red" id="transferBlogReplyDataMessage"></label>
		<h3 style="color: red;">(5)</h3>
		修改博客数据信息：&nbsp;&nbsp;<input type="button" id="transferBlogData" value="开始"/>&nbsp;&nbsp;<label style="color: red" id="transferBlogDataMessage"></label>
		<h3 style="color: red;">(6)</h3>
		迁移博客关系信息：&nbsp;&nbsp;<input type="button" id="transferRelationBlogData" value="开始"/>&nbsp;&nbsp;<label style="color: red" id="transferRelationBlogDataMessage"></label><br>
		迁移新闻关系信息：&nbsp;&nbsp;<input type="button" id="transferRelationNewsData" value="开始"/>&nbsp;&nbsp;<label style="color: red" id="transferRelationNewsDataMessage"></label><br>
		迁移网盘关系信息：&nbsp;&nbsp;<input type="button" id="transferRelationDiskData" value="开始"/>&nbsp;&nbsp;<label style="color: red" id="transferRelationDiskDataMessage"></label>
	</div>
	<div class="divcss5">
		<h2 align="center">新闻模块源数据迁移</h2>
	</div>
	<div class="divcss5">
		<h2 align="center">授权模块源数据迁移</h2>
		迁移组织机构:&nbsp;&nbsp;<input type="button" id="transferOrgBase" value="开始迁移组织机构"/><br>
		建立组织上下级关系:&nbsp;&nbsp;<input type="button" id="updateParentCode" value="建立组织上下级关系"/><br>
		变更所属行政组织:&nbsp;&nbsp;<input type="button" id="updateManOrgCode" value="变更所属行政组织"/><br>
		迁移用户:&nbsp;&nbsp;<input type="button" id="transferUser" value="开始迁移用户"/><br>
	</div>
	<div class="divcss5">
		<h2 align="center">备案模块源数据迁移</h2>
		迁移好友分组:&nbsp;&nbsp;<input type="button" id="transferFriendGroup" value="开始迁移好友分组"/><br>
		迁移用户好友:&nbsp;&nbsp;<input type="button" id="transferFriend" value="开始迁移用户好友"/><br>
		迁移用户关注:&nbsp;&nbsp;<input type="button" id="transferUserConcern" value="开始迁移用户关注"/><br>
		迁移敏感词:&nbsp;&nbsp;<input type="button" id="transferSenstiveWord" value="开始迁移敏感词"/><br>
	</div>
	<div class="divcss5">
		<h2 align="center">信用模块源数据迁移</h2>
		迁移个人的积分、金币账户:&nbsp;&nbsp;<input type="button" id="transferUserAccount" value="开始迁移个人的积分、金币账户"/><br>
		迁移班组的积分、金币账户:&nbsp;&nbsp;<input type="button" id="transferTeamAccount" value="开始迁移班组的积分、金币账户"/><br>
		迁移积分明细:&nbsp;&nbsp;<input type="button" id="transferIntegralDetail" value="开始迁移积分明细"/><br>
		迁移金币明细:&nbsp;&nbsp;<input type="button" id="transferCoinDetail" value="开始迁移金币明细"/><br>
	</div>
	<div class="divcss5">
		<h2 align="center">班组模块源数据迁移</h2>
		迁移班组信息:&nbsp;&nbsp;<input type="button" id="transferTeamInfo" value="开始迁移班组信息"/><br>
		迁移班组成员:&nbsp;&nbsp;<input type="button" id="transferTeamMember" value="开始迁移班组成员"/><br>
		迁移百强班组:&nbsp;&nbsp;<input type="button" id="transferHonorTeam" value="开始迁移百强班组"/><br>
		迁移班组论坛数据:&nbsp;&nbsp;<input type="button" id="transferForum" value="开始迁移班组论坛数据"/><br>
	</div>
	<div class="divcss5">
		<h2 align="center">推荐源数据迁移</h2>
		迁移推荐数据:&nbsp;&nbsp;<input type="button" id="transferRecommend" value="迁移推荐数据"/><label color="red" id="tRecommend"></label><br>
		迁移推荐微博到内容信息:&nbsp;&nbsp;<input type="button" id="transferRecommendWeiBo" value="迁移推荐微博到内容信息"/><label color="red" id="tRecommendWeibo"></label><br>
	</div>


	<div class="divcss5">
		<h2 align="center">新闻数据迁移</h2>
		迁移新闻数据:&nbsp;&nbsp;<input type="button" id="transferNewsInfo" value="开始迁移新闻数据"/><br>
		迁移网盘数据:&nbsp;&nbsp;<input type="button" id="transferDisk" value="开始迁移网盘数据"/><br>
	</div>

	<div class="divcss5">
		
	</div>
	
	
</body>
</html>
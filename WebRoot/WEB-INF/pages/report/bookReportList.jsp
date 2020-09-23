<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.getSession().setAttribute("sessionid", "");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 2.3.1
Version: 1.3
Author: KeenThemes
Website: http://www.keenthemes.com/preview/?theme=metronic
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> 
<html><!--<![endif]--><!-- BEGIN HEAD -->
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>渠道数据平台>>角色管理</title>
  <link href="${ctx}/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
  <link href="${ctx}/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
  <link href="${ctx}/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
  <link href="${ctx}/media/css/style-metro.css" rel="stylesheet" type="text/css"/>
  <link href="${ctx}/media/css/style.css" rel="stylesheet" type="text/css"/>
  <link href="${ctx}/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
  <link href="${ctx}/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
  <link href="${ctx}/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
  <link rel="shortcut icon" href="${ctx}/media/image/favicon.ico" />
  <link href="${ctx}/clientCss/linkPage/link.css" rel="stylesheet" type="text/css" media="screen"/>
  <link href="${ctx}/clientCss/home/home.css" rel="stylesheet" type="text/css" media="screen"/>
</head>
<body class="page-header-fixed">
	<div style="padding-left: 20px  0  0  0px ;  background-color: white;  height: 795px;  ">
		<table class="table table-bordered table-hover" >
			<thead>
				<tr>
				    <th>序号</th>
					<th>数据类型</th>
					<th>订阅名称 </th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageView.items}"  var="page" varStatus="vs" >
						<tr>
							<td>${vs.count}</td>
							<td>${page.numType}</td>
							<td>${page.name}</td>
							<td>${page.reportTime}</td>
							<td>
							<button class="btn green" onclick="toDelete('${page.id}');">退订</button>

							</td>
						</tr>
				</c:forEach>
			</tbody>
		</table>
		<%@include file="/WEB-INF/pages/layout/page.jsp" %>	
	</div>
	
	
	
<script src="${ctx}/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="${ctx}/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="${ctx}/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
<script src="${ctx}/media/js/bootstrap.min.js" type="text/javascript"></script>  
     
<script>
jQuery(document).ready(function() {    
	App.init(); // initlayout and core plugins
	Index.init();
	Index.initCalendar(); // init index page's custom scripts
	Index.initCharts(); // init index page's custom scripts
	Index.initChat();
	Index.initMiniCharts();
	Index.initDashboardDaterange();
});
</script>
<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script>
<!--引入自己写的js文件  -->
<script type="text/javascript">

function toDelete(id){
	var flag = window.confirm("   是否确认退订此报表的订阅？\n 收件地址：${email}");
	if (flag){
		var url="${ctx}/bookReport/ajaxDeleteReport.do?reportId="+id;
		$.ajax({
			url:url,
			type:'post',
			success:function(res){
				var result=JSON.parse(res);
				if(result.flag==false){
					alert("退订失败");
				}
				window.location.reload();
			},
			error:function(error){
				alert("订阅失败");
			}
		});
	}
}

function page(pageId){
	var pTotal=$('#pageTotal').text();
	
	if( parseInt(pageId)>parseInt(pTotal)){
		pageId=pTotal;
	}
	if(parseInt(pageId)<1){
		pageId=1;
	}
	
	var url="${ctx}/bookReport/toBookReportList.do?currentPage="+pageId;
	location.href = url;
}

function goPage(num){
	var pTotal=$('#pageTotal').text();
	var pageId = $("#pageId").val();
	pageId = parseInt(pageId);
	pageId = pageId + num;
	if(parseInt(pageId)>parseInt(pTotal)){
		pageId=pTotal;
	}
	if(parseInt(pageId)<1){
		pageId=1;
	}
	page(pageId);
}
</script>
</body>
</html>
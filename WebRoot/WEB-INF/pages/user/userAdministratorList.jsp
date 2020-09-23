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
  <title>用户集合</title>
  <link href="${ctx}/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
  <link href="${ctx}/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
  <link href="${ctx}/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
  <link href="${ctx}/media/css/style-metro.css" rel="stylesheet" type="text/css"/>
  <link href="${ctx}/media/css/style.css" rel="stylesheet" type="text/css"/>
  <link href="${ctx}/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
  <link href="${ctx}/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
  <link href="${ctx}/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
  <!-- END GLOBAL MANDATORY STYLES -->
  <!-- BEGIN PAGE LEVEL STYLES --> 
  <link href="${ctx}/media/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
  <link href="${ctx}/media/css/daterangepicker.css" rel="stylesheet" type="text/css" />
  <link href="${ctx}/media/css/fullcalendar.css" rel="stylesheet" type="text/css"/>
  <link href="${ctx}/media/css/jqvmap.css" rel="stylesheet" type="text/css" media="screen"/>
  <link href="${ctx}/media/css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
  <!-- END PAGE LEVEL STYLES -->
  <link rel="shortcut icon" href="${ctx}/media/image/favicon.ico" />
  <link href="${ctx}/clientCss/linkPage/link.css" rel="stylesheet" type="text/css" media="screen"/>
  <link href="${ctx}/clientCss/home/home.css" rel="stylesheet" type="text/css" media="screen"/>
  <link href="${ctx}/clientCss/userPage/userList.css" rel="stylesheet" type="text/css" media="screen"/>
  <link href="${ctx}/clientCss/home/home.css" rel="stylesheet" type="text/css" media="screen"/>
  
</head>
<body class="page-header-fixed">
	<div style="padding-left: 20px  0  0  0px ;  background-color: white;  height: 795px;  ">
					<table class="table table-bordered table-hover" >
								<thead>
									<tr>
										<th>序号</th>
										<th>用户名</th>
										<th>姓名</th>
										<th>公司名称</th>
										<th>部门名称</th>
										<th>角色</th>
										<th>电话</th>
										<th>邮箱</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageView.items}"  var="page" varStatus="vs" >
											<tr>
												<td>${vs.count}</td>
												<td>${page.userName}</td>
												<td>${page.realName}</td>
												<td>${page.resourceAllocation.resName}</td>
												<td>${page.userDepartmentName}</td>
												<td>${page.userGroup.groupName}</td>
												<td>${page.userMobile}</td>
												<td>${page.userEmail}</td>
												<td>
													<a href="#" onclick="editPassWord('${page.userId}');" >修改密码</a>
												</td>
											</tr>
									</c:forEach>
								</tbody>
							</table>
	</div>
	
	
	
<div class="modal fade" id="editPassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
   <div class="modal-dialog">
    <form id="editForm"  method="post">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
            </button>
            <h4 class="modal-title" id="myModalLabel">
               <center>密码修改</center>
            </h4>
         </div>
         
         <div class="modal-body">
          	<table>
          		<tr>
          			<td>原密码：</td>
          			<td><input type="text"  name="oldPassWord" id="oldPassWord" /></td>
          		</tr>
          		<tr>
          			<td>新密码：</td>
          			<td><input type="text"  name="newPassWord" id="newPassWord" /></td>
          		</tr>
          		<tr>
          			<td>确认密码：</td>
          			<td><input type="text"  name="newPassWord2" id="newPassWord2" /></td>
          		</tr>
          	</table>
         </div>
        <div class="modal-footer">
					<button type="button" class="btn green" onclick="editPassWord()">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		</div>
      </div>
      </form>
	</div>
</div>
	
	
	
<script src="${ctx}/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="${ctx}/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="${ctx}/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
<script src="${ctx}/media/js/bootstrap.min.js" type="text/javascript"></script>
<!--[if lt IE 9]>
<script src="${ctx}/media/js/excanvas.min.js"></script>
<script src="${ctx}/media/js/respond.min.js"></script>  
<![endif]-->   
<script src="${ctx}/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${ctx}/media/js/jquery.blockui.min.js" type="text/javascript"></script>  
<script src="${ctx}/media/js/jquery.cookie.min.js" type="text/javascript"></script>
<script src="${ctx}/media/js/jquery.uniform.min.js" type="text/javascript" ></script>
<script src="${ctx}/media/js/jquery.vmap.js" type="text/javascript"></script>   
<script src="${ctx}/media/js/jquery.vmap.russia.js" type="text/javascript"></script>
<script src="${ctx}/media/js/jquery.vmap.world.js" type="text/javascript"></script>
<script src="${ctx}/media/js/jquery.vmap.europe.js" type="text/javascript"></script>
<script src="${ctx}/media/js/jquery.vmap.germany.js" type="text/javascript"></script>
<script src="${ctx}/media/js/jquery.vmap.usa.js" type="text/javascript"></script>
<script src="${ctx}/media/js/jquery.vmap.sampledata.js" type="text/javascript"></script>  
<script src="${ctx}/media/js/jquery.flot.js" type="text/javascript"></script>
<script src="${ctx}/media/js/jquery.flot.resize.js" type="text/javascript"></script>
<script src="${ctx}/media/js/jquery.pulsate.min.js" type="text/javascript"></script>
<script src="${ctx}/media/js/date.js" type="text/javascript"></script>
<script src="${ctx}/media/js/daterangepicker.js" type="text/javascript"></script>     
<script src="${ctx}/media/js/jquery.gritter.js" type="text/javascript"></script>
<script src="${ctx}/media/js/fullcalendar.min.js" type="text/javascript"></script>
<script src="${ctx}/media/js/jquery.easy-pie-chart.js" type="text/javascript"></script>
<script src="${ctx}/media/js/jquery.sparkline.min.js" type="text/javascript"></script>  
<script src="${ctx}/media/js/app.js" type="text/javascript"></script>
<script src="${ctx}/media/js/index.js" type="text/javascript"></script>        
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
<script src="${ctx}/clientScript/userList/userList.js" type="text/javascript" charset=”UTF-8″></script>
<script type="text/javascript">
function page(pageId){
	var pTotal=$('#pageTotal').text();
	
	if( parseInt(pageId)>parseInt(pTotal)){
		pageId=pTotal;
	}
	if(parseInt(pageId)<1){
		pageId=1;
	}
	var userName=$("#userName").val();
	var realName=$("#realName").val();
	var userCompanyName=$("#userCompanyName").val();
	var userDepartmentName=$("#userDepartmentName").val();
	var url="${ctx}/user/toUserList.do?currentPage="+pageId+"&userName="+userName+"&realName="+realName+"&userCompanyName"+userDepartmentName+"&userDepartmentName";
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



function editPassWord(id){
	window.location.href = "../user/toEditPassWord.do?userId="+id;
}


</script>
</body>
</html>
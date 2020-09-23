<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
  <title>数据查询</title>
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
  <link href="${ctx}/media/css/datepicker.css" rel="stylesheet" type="text/css" media="screen"/>
  <link href="${ctx}/media/css/datetimepicker.css" rel="stylesheet" type="text/css" media="screen"/>
  <!-- END PAGE LEVEL STYLES -->
  <link rel="shortcut icon" href="${ctx}/media/image/favicon.ico" />
  <link href="${ctx}/clientCss/linkPage/link.css" rel="stylesheet" type="text/css" media="screen"/>
  <link href="${ctx}/clientCss/home/home.css" rel="stylesheet" type="text/css" media="screen"/>
  <link href="${ctx}/clientCss/userPage/userList.css" rel="stylesheet" type="text/css" media="screen"/>
  <style type="text/css">
	 .selectContent{
			width:128px;
			height:160px;
			border:1px solid black;
			position: absolute;
			left: 3px;
    		top: 28px;
			background-color:#C0FCFC;
			text-align: left;
			display: none;
			overflow:auto;
			z-index:10;
		}
		#chongZhiTableId tbody tr td {text-align: right;}
		#allTableId tbody tr td {text-align: right;}
		#touZhuTableId tbody tr td {text-align: right;}
    </style>
  
</head>
<body class="page-header-fixed">
<c:if test="${requestScope.msg != null}">
    <script type="text/javascript">
        alert('${requestScope.msg}');
        window.close();
    </script>
</c:if>
	<div style="padding-left: 20px  0  0  0px ;  background-color: white;  height: 795px;  ">
					<form  id="form1" name="form1"  method="post"  action="${ctx}/userRate/queryDataByUserRegistrationLogin.do">		
						<table class="table table-bordered "style="margin-bottom: 0px;"  >
							<tbody>
								<tr>
						            <td style="text-align: center;">渠道信息</td>
						            <td style="position:relative;">
						            <select id="sidId" name="sid" >
						            	<option value="" selected="selected">请选择</option>	
							            <c:forEach items="${channelList}"  var="channel"    >
												<option value="${channel.sid}"  <c:if test="${channel.sid==userRegistraList.sid }">selected="selected"</c:if> >${channel.sid}__${channel.name}</option>	
										</c:forEach>
									</select>
						            </td>
						            
						            
						            <td style="text-align: center;">数据类型</td>
									<td>
										<select id="retentionRateFlagId" name="retentionRateFlag" >
											<option value="1" <c:if test="${userRegistraList.retentionRateFlag==1 }">selected="selected"</c:if>>登录保留率</option>
											<option value="2" <c:if test="${userRegistraList.retentionRateFlag==2 }">selected="selected"</c:if>>投注保留率</option>
										</select>
									</td>
								</tr>
								<tr>
									<td style="text-align: center;">起始时间</td>
									<td >
										<input type="text" name="startTime"
											id="startDate" class="datetime" value="${startTime}" >
									</td>
									<td style="text-align: center;">结束时间</td>
									<td > <input type="text" name="endTime"
											id="endDate" class="datetime" value="${endTime}" >
									</td>		
									<td colspan="3" style="position: relative;" >
										<input type="hidden" value="1" name="currentPage" />
										
										<input type="hidden" value="${isAdministratorFlag}" name="isAdministratorFlag" id="isAdministratorFlagId" />
										<input type="hidden" value="${permissionType}" name="permissionType" id="permissionTypeId" />
										
										<a href="#"  onclick="login();"   class="btn green" style="width: 70px;">查询</a>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
					<%@include file="/WEB-INF/pages/userRate/userRegistrLoginInfoAllList.jsp" %>
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
   
<script src="${ctx}/media/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
<script src="${ctx}/media/js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
<script>
		function login(){
			var path = "${ctx}/userRate/queryDataByUserRegistrationLogin.do";  
		    $('#form1').attr("action", path).submit();	
		}
		
		function page(pageId){
			var pTotal=$('#pageTotal').text();
			
			if( parseInt(pageId)>parseInt(pTotal)){
				pageId=pTotal;
			}
			if(parseInt(pageId)<1){
				pageId=1;
			}
			
			var sidId=$("#sidId").val();
			var perId=$("#retentionRateFlagId").val();
			var startTime= $("#startDate").val();
			var endTime= $("#endDate").val();
			var url="${ctx}/userRate/queryDataByUserRegistrationLogin.do?currentPage="+pageId+"&retentionRateFlag="+perId+"&sid="+sidId+"&startTime="+startTime+"&endTime="+endTime;
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
		
		$(".datetime").datetimepicker({
			format: 'yyyy-mm-dd',
			language: 'zh-CN',
			autoclose: true,
			todayBtn: true,
			weekStart: 1,  
			startView: 2,  
			minView: 2,  
			forceParse: false,
			endDate:new Date()
	});
	
	$(".reportdatetime").datetimepicker({
		format: 'yyyy-mm-dd',
		language: 'zh-CN',
		autoclose: true,
		todayBtn: true,
		weekStart: 1,  
		startView: 2,  
		minView: 2,  
		forceParse: false,
		startDate: new Date()
});
	</script>
</body>
</html>

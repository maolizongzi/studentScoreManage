<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> 
<html><!--<![endif]--><!-- BEGIN HEAD -->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>渠道管理系统</title>
	<link href="${ctx}/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/media/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/media/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="${ctx}/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/media/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/media/css/daterangepicker.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/media/css/fullcalendar.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/media/css/jqvmap.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="${ctx}/media/css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="${ctx}/media/image/favicon.ico" rel="shortcut icon" />
</head>
<body class="page-header-fixed" style="overflow: hidden;">
	<div class="header navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid" >
				<a class="brand" href="#">
				<img src="${ctx}/clientImages/logo.gif" alt="logo"/>
				</a>
				<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
				<img src="${ctx}/media/image/menu-toggler.png" alt="" />
				</a>          
				<ul class="nav pull-right">
					<li class="dropdown user">
						<a href="${ctx}/login/loginOut.do" class="dropdown-toggle" data-toggle="dropdown">
						<img alt="" src="${ctx}/media/image/avatar1_small.jpg" />
						<span class="username">退出</span>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="page-container">
		<div class="page-sidebar nav-collapse collapse" style="background-color: #3d3d3d !important;" id="page-containe-left">
			<ul class="page-sidebar-menu">
				<%@include file="/WEB-INF/pages/layout/function.jsp" %>
			</ul>
		</div>
		<div class="page-content">
			<div id="search">
				<c:forEach var="tabPages" items="${functionTabPage}">
					<ul class="nav nav-tabs bordered" id="${tabPages.key}TabPage" style="display: none;">
						<c:forEach var="tabPage" items="${tabPages.value}" varStatus="status">
							<li <c:if test="${status.index == 0}">class="active"</c:if> onclick="toPage('${tabPage.functionScriptName}')">
								<input type="hidden" value="${tabPage.functionScriptName}" name="${tabPages.key}functionScriptName" /> 
								<a href="javascript:void(0)" data-toggle="tab">${tabPage.functionName}</a>
							</li>
						</c:forEach>
					</ul>
				</c:forEach>
			</div>
			<iframe id="pageFrame" src="" width="100%"   height="100%;"  marginheight="0" marginwidth="0" frameborder="0"    ></iframe>
		</div> 
	</div>	
	<script src="${ctx}/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="${ctx}/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<script src="${ctx}/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
	<script src="${ctx}/media/js/bootstrap.min.js" type="text/   javascript"></script>
	<!--[if lt IE 9]>
	<script src="${ctx}/media/js/excanvas.min.js"></script>
	<script src="${ctx}/media/js/respond.min.js"></script>  
	<![endif]-->   
	<script src="${ctx}/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="${ctx}/media/js/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="${ctx}/media/js/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="${ctx}/media/js/jquery.uniform.min.js" type="text/javascript" ></script>
	<script src="${ctx}/media/js/jquery.vmap.sampledata.js" type="text/javascript"></script>  
	<script src="${ctx}/media/js/jquery.flot.js" type="text/javascript"></script>
	<script src="${ctx}/media/js/jquery.flot.resize.js" type="text/javascript"></script>
	<script src="${ctx}/media/js/jquery.pulsate.min.js" type="text/javascript"></script>
	<script src="${ctx}/media/js/date.js" type="text/javascript"></script>
	<script src="${ctx}/media/js/daterangepicker.js" type="text/javascript"></script>     
	<script src="${ctx}/media/js/jquery.gritter.js" type="text/javascript"></script>
	<script src="${ctx}/media/js/fullcalendar.min.js" type="text/javascript"></script>
	<script src="${ctx}/media/js/jquery.easy-pie-chart.js" type="text/javascript"></script>
	<script src="${ctx}/media/js/jquery.spa rkline.min.js" type="text/javascript"></script>  
	<script src="${ctx}/media/js/app.js" type="text/javascript"></script>
	<script src="${ctx}/media/js/index.js" type="text/javascript"></script>      
	<script>
		jQuery(document).ready(function() {    
		   fixedParentTitle();
		 //  freamPageHeight();
		   App.init(); // initlayout and core plugins
		   Index.init();
		   Index.initCalendar(); // init index page's custom scripts
		   Index.initCharts(); // init index page's custom scripts
		   Index.initChat();
		   Index.initMiniCharts();
		   Index.initDashboardDaterange();
		});
		
		
		$(window).load(function(){
			re();
		});
		$(window).resize(function(){ 
			re();
		});
		function re(){
			var ht =  document.documentElement.clientHeight-56 || document.body.clientHeight-56;
			var wt =  document.documentElement.clientWidth || document.body.clientWidth;
			    wt = wt- $("#page-containe-left").width();
			var t =ht-58;
			$("#pageFrame").css({"height":(t+"px"),"width":(wt+"px")});
			$("#page-containe-left").css("height",ht+"px");
		}

		
	</script>
	<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script>
	<script src="${ctx}/clientScript/layout/layout.js" type="text/javascript"></script>        
</body>
</html>
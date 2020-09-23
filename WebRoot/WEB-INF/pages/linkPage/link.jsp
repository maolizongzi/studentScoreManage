<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
  <title>后台登陆</title>
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
</head>
<body class="page-header-fixed">
	<div style="padding-left: 20px  0  0  0px ;  background-color: white;  height: 795px; ">
		<div class="row-fluid">
				<div class="span6" style="width: 18%; margin-left: 5%;">
					<div class="portlet solid blue">
						<div class="portlet-title">
							<div class="caption"><i class="icon-reorder"></i><a href="#" onclick="toOtherUrl('http://ventgw.zch168.com/manages/default.jsp');">TOC后台登陆[正式]</a> </div>
						</div>
					</div>
				</div>
				<div class="span6" style="width: 18%; margin-left: 5%;">
					<div class="portlet solid red">
						<div class="portlet-title">
							<div class="caption"><i class="icon-reorder"></i><a href="#" onclick="toOtherUrl('http://task.zch168.com/');">道具积分登录[正式]</a></div>
						</div>
					</div>
				</div>
				<div class="span6" style="width: 18%; margin-left: 5%;">
					<div class="portlet solid yellow">
						<div class="portlet-title">
							<div class="caption"><i class="icon-reorder"></i><a href="#" onclick="toOtherUrl('http://10.1.4.54:8088/PreCodeSys ');">优惠码后台登录[正式]</a></div>
						</div>
					</div>
				</div>
				<div class="span6" style="width: 18%; margin-left: 5%;">
					<div class="portlet solid green">
						<div class="portlet-title">
							<div class="caption"><i class="icon-reorder"></i><a href="#" onclick="">待定[正式]</a></div>
						</div>
					</div>
				</div>
		</div>
		<div class="row-fluid">
				<div class="span6" style="width: 18%; margin-left: 5%;">
					<div class="portlet solid blue">
						<div class="portlet-title">
							<div class="caption"><i class="icon-reorder"></i><a href="#" onclick="toOtherUrl('http://10.0.3.110:8080/manages/default.jsp');">TOC后台登陆[测试]</a> </div>
						</div>
					</div>
				</div>
				<div class="span6" style="width: 18%; margin-left: 5%;">
					<div class="portlet solid red">
						<div class="portlet-title">
							<div class="caption"><i class="icon-reorder"></i><a href="#" onclick="toOtherUrl('http://10.1.3.16:8090/');">道具积分登录[测试]</a></div>
						</div>
					</div>
				</div>
				<div class="span6" style="width: 18%; margin-left: 5%;">
					<div class="portlet solid yellow">
						<div class="portlet-title">
							<div class="caption"><i class="icon-reorder"></i><a href="#" onclick="toOtherUrl('http://10.1.3.2:7001/PreCodeSys/');">优惠码后台登录[测试]</a></div>
						</div>
					</div>
				</div>
				<div class="span6" style="width: 18%; margin-left: 5%;">
					<div class="portlet solid green">
						<div class="portlet-title">
							<div class="caption"><i class="icon-reorder"></i><a href="#" onclick="">待定[测试]</a></div>
						</div>
					</div>
				</div>
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
<script type="text/javascript">
function toOtherUrl(url){
	window.open(url);
}
</script>
</body>
</html>
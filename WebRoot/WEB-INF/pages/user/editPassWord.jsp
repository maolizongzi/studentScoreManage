<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
  <link href="${ctx}/media/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
  <link href="${ctx}/media/css/daterangepicker.css" rel="stylesheet" type="text/css" />
  <link href="${ctx}/media/css/fullcalendar.css" rel="stylesheet" type="text/css"/>
  <link href="${ctx}/media/css/jqvmap.css" rel="stylesheet" type="text/css" media="screen"/>
  <link href="${ctx}/media/css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
  <link rel="shortcut icon" href="${ctx}/media/image/favicon.ico" />
  <link href="${ctx}/clientCss/linkPage/link.css" rel="stylesheet" type="text/css" media="screen"/>
</head>
<body class="page-header-fixed">
<c:if test="${requestScope.msg != null}">
    <script type="text/javascript">
        alert('${requestScope.msg}');
        window.close();
    </script>
</c:if>
		<div style="padding-left: 0px  0  0  0px ;  background-color: white;  height: 795px; border: 1px solid balck; ">
						<!-- BEGIN SAMPLE FORM PORTLET-->   
						<div class="portlet box blue">
							<div class="portlet-title">
								<div class="caption">修改密码</div>
							</div>
							<div class="portlet-body form">
								<!-- BEGIN FORM-->
								<form action="${ctx}/user/savePassWord.do" id="form1" class="form-horizontal" method="post">
									<input type="hidden" id="userId" name="userId" value="${userId}" />
									<table>
										<tr >
											<td width="20px;"><label class="control-label"><span style="color: red" >*</span>原密码:</label></td>
											<td>&nbsp;<input type="password"  class="span6 m-wrap" id="oldPassWord"  name="oldPassWord" onkeyup="value=value.replace(/[\W]/g,'')" value="${oldPassWord}" /></td>
											<td>&nbsp;</td>
										</tr>
										<tr> 
											<td width="20px;"><label class="control-label">新密码:</label></td>
											<td>&nbsp;<input type="password" class="span6 m-wrap" onkeyup="value=value.replace(/[\W]/g,'')"  id="newPassWord" name="newPassWord" value="${newPassWord}" /></td>
											<td>[不少于6位数字或字母]</td>
										</tr>
										
										<tr >  
											<td width="20px;"><label class="control-label"><span style="color: red" >*</span> 确认密码:</label></td>
											<td>&nbsp;<input  type="password" onkeyup="value=value.replace(/[\W]/g,'')"  class="span6 m-wrap" id="newPassWord2" name="newPassWord2" value="${newPassWord2}"/></td>
											<td>&nbsp;</td>
										</tr>
										
										<tr> 
											<td  colspan="3" align="right" >
												<input type="button"  class="btn green" style="width:70px;" onclick="ifLink();"; value="保存"  />
												<input type="button"  class="btn blue"  style="width:70px;" onclick="javascript:history.go(-1);" value="返回"  />
											</td>
										</tr>
									</table>
								</form>
								<!-- END FORM-->       
							</div>
						</div>
						<!-- END SAMPLE FORM PORTLET-->
			</div>
<script src="${ctx}/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="${ctx}/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="${ctx}/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
<script src="${ctx}/media/js/bootstrap.min.js" type="text/javascript"></script>
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

function ifLink(){
	
	
	var p1=$("#oldPassWord").val();
	var p2=$("#newPassWord").val();
	var p3=$("#newPassWord2").val();
	if(p1==""){
		alert("原密码不能为空！");
		return;
	}
	
	if(p2==""){
		alert("新密码不能为空！");
		return;
	}
	if(p2.length<6){
		alert("新密码不能少于6位数！");
		return;
	}
	
	if(p2!=p3){
		
		alert(p2+"::"+p3+"新密码两次输入值不同");
		return;
	}

	
    $('#form1').submit();		
	
}

</script>

</body>
</html>
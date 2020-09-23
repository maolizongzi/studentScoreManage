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
					<form  id="form1" name="form1"  method="post"  action="${ctx}/dataInfo/queryData.do">		
						<table class="table table-bordered "style="margin-bottom: 0px;"  >
							<tbody>
								<tr>
						            <td style="text-align: center;">渠道信息</td>
						            <td style="position:relative;">
						            <select id="sidId" name="sid" >
							            <c:forEach items="${channelList}"  var="channel"    >
													<option value="${channel.sid}"  <c:if test="${channel.sid==dataInfo.sid }">selected="selected"</c:if> >${channel.sid}__${channel.name}</option>	
										</c:forEach>
									</select>
						            </td>
						            
						            
						            <td style="text-align: center;">数据类型</td>
									<td>
										<select id="permissionType" name="permissionType" >
											<c:forEach items="${permissionList}"  var="item"    >
												<option value="${item}" <c:if test="${permissionType==item }">selected="selected"</c:if> >
													<c:if test="${item=='fa'}">综合数据</c:if>
													<c:if test="${item=='fb'}">渠道充值数据</c:if>
													<c:if test="${item=='fc'}">渠道投注数据</c:if>
												</option>	
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td style="text-align: center;">起始时间</td>
									<td >
										<input type="text" name="startTime"
											id="startDate" class="datetime" value="${dataInfo.startTime}" >
									</td>
									<td style="text-align: center;">结束时间</td>
									<td > <input type="text" name="endTime"
											id="endDate" class="datetime" value="${dataInfo.endTime}" >
									</td>		
									<td colspan="3" style="position: relative;" >
										<input type="hidden" value="1" name="currentPage" />
										
										<input type="hidden" value="${isAdministratorFlag}" name="isAdministratorFlag" id="isAdministratorFlagId" />
										<input type="hidden" value="${permissionType}" name="permissionType" id="permissionTypeId" />
										
										<a href="#"  onclick="login();"   class="btn green" style="width: 70px;">查询</a>
										<a href="#"  onclick="getSubscribeName();"   class="btn blue" style="width: 70px;">邮件订阅</a>
										<button class="btn green" data-toggle="modal"  data-target="#exportFileModal">导出 </button>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
					<c:if test="${permissionType=='fa' }">
						<%@include file="/WEB-INF/pages/data/dataInfoAllList.jsp" %>
					</c:if>
					<c:if test="${permissionType=='fb' }">
						<%@include file="/WEB-INF/pages/data/dataInfoChongzhiList.jsp" %>
					</c:if>
					<c:if test="${permissionType=='fc' }">
						<%@include file="/WEB-INF/pages/data/dataInfoTouZhuList.jsp" %>					
					</c:if>
					<c:if test="${empty permissionType  }">
						<table class="table table-bordered table-hover" >
							<thead>
								<tr>
									<td colspan="12" style="text-align: center;" >请在上面填写筛选条件，并点击查询按钮来查询数据！</td>
								</tr>
							</thead>
						</table>
					</c:if>
	</div>
	
<button class="btn blue" data-toggle="modal" id="subscribeModalId"  data-target="#subscribeModal" style="display: none;"  > 邮件订阅 </button>
<!-- 订阅模态框-->
<div class="modal fade" id="subscribeModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
            </button>
            <h4 class="modal-title" id="myModalLabel">
               <center>邮件订阅</center>
            </h4>
         </div>
         <div class="modal-body">
          	<form action="${ctx}/bookReport/addReport.do" method="post" id="formReport" name="formReport"   >
          		<table>
	          		<tr>
	          			<td>订阅名称:</td>
	          			<td>
	          				<span id="subscribeNameId"  ></span>
	          				<input type="hidden" name="name" id="nameId"  />
	          				<input type="hidden" name="numType" id="numTypeId"  />
	          				<input type="hidden" name="isAdmin" id="isAdminId"  />
	          				<input type="hidden" name="sid" id="sidReportId"   />
	          				<input type="hidden" name="permissionType" id="permissionTypeReportId"  />
	          			</td>
	          		</tr>
					<tr>
	          			<td>开始时间:</td>
	          			<td>
	          				<input type="text" name="reportTime" id="datetimeId"  class="reportdatetime"  >
	          			</td>
	          		</tr>
	          		<tr>
	          			<td>收件地址:</td>
	          			<td>
	          				<span id="userEmailId">${userInfo.userEmail}</span>
	          				<input type="hidden" name="emailAddress" value="${userInfo.userEmail}"  /> 
	          			</td>
	          		</tr>          	
	          	</table> 
          	</form>
         </div>
        <div class="modal-footer">
					<button type="button" class="btn green" id="subButton"  >订阅</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		</div>
				
				
      </div>
</div>
</div>


<!-- 导出框（Modal） -->
<div class="modal fade" id="exportFileModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
            </button>
            <h4 class="modal-title" id="myModalLabel">
               <center>文件导出</center>
            </h4>
         </div>
         <div class="modal-body">
          			<input type="radio"   name="fileFormat" value="txt"  checked="checked" />txt 
					<input type="radio"   name="fileFormat" value="csv" />csv 
					<input type="radio"   name="fileFormat" value="xlsx" />xls
         </div>
        <div class="modal-footer">
					<button type="button" class="btn green"	onclick="exportFile();">导出</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
   
<script src="${ctx}/media/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
<script src="${ctx}/media/js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
<script>
		//获取订阅名称
		function getSubscribeName(){
			var options=$("#sidId  option:selected");
			var permissionTypeId=$("#permissionType").val();
			var permission = permissionTypeId==("fa") ? "综合" : permissionTypeId==("fb") ? "充值" : "投注";
			$("#numTypeId").val(permission+"数据");
			//数据类型
			$("#permissionTypeReportId").val(permissionTypeId);
			
			var sidId=options.text()+permission+"日报";
			$("#sidReportId").val(options.val());
			$("#subscribeNameId").html(sidId);
			$("#nameId").val(sidId);
			
			
			
			
			//是否是管理员 0:渠道用户 1:管理员
			var isAdministratorFlagId=$("#isAdministratorFlagId").val();
			$("#isAdminId").val(isAdministratorFlagId);
			$("#subscribeModalId").click(); 
		}
		//进行订阅功能
		$(function() {
			$("#subButton").click(function(){ 
				var bln =ifLink("确认订阅吗？");
				if(bln){
					var nameId=$("#nameId").val();
					var url="${ctx}/bookReport/ajaxRePortByCodeAndName.do?name="+encodeURIComponent(encodeURIComponent(nameId));
					$.ajax({
						url:url,
						type:'post',
						success:function(res){
							var result=JSON.parse(res);
							if(result.flag=="true"){
								document.formReport.submit(); 
								return true;
							}else{
								alert(result.flag);
								$("form1").bind("submit",function(){
								     return false;
								});
								return false;
							}
						},
						error:function(error){
							alert(error);
						}
					});
				}else{
					$("form1").bind("submit",function(){
					     return false;
					});
				}
			}); 
		});
		
		function ifLink(str) {
			//判断订阅时间是否为空
			var datetimeId=$("#datetimeId").val();
			alert(datetimeId);
			if(datetimeId== ""){
				alert("订阅时间不能为空！");
				return false;
			}
			//判断用户邮箱地址是否为空,为空不让订阅
			var userEmailId=$("#userEmailId").html();
			
			if(userEmailId== ""){
				alert("用户邮箱地址为空,不能进行订阅操作");
				return false;
			}		
			var bln = confirm(str);
			return bln;
		}
		
		
	
		function login(){
			var path = "${ctx}/dataInfo/queryData.do";  
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
			var perId=$("#permissionTypeId").val();
			var startTime= $("#startDate").val();
			var endTime= $("#endDate").val();
			var url="${ctx}/dataInfo/queryData.do?currentPage="+pageId+"&permissionType="+perId+"&sid="+sidId+"&startTime="+startTime+"&endTime="+endTime;
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
		
		
		
		function exportFile(){
			var fileFormat=$("input[type='radio']:checked").val();
			var str="确定导出  "+fileFormat+" 格式的文件";
			var bln = confirm(str);
			if(bln){//dataInfo/queryData.do
				var path = "${ctx}/export/"+fileFormat+".do";  
			    $('#form1').attr("action", path).submit();		
			}
			//login();
		}
		
	</script>
	
	<script type="text/javascript">

	//当鼠标放在书的名称上时背景色改变
	function change1(d){
		d.style.backgroundColor="white";
	}
	
	//当鼠标离开书的名称上时背景色恢复
	function change2(d){
		d.style.backgroundColor="#C0FCFC"; 
	}
	
	//获取XMLHttpRequest对象
	function getXMLHttpRequest() {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for all new browsers
			xmlhttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {// code for IE5 and IE6
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		return xmlhttp;
	}
	

	function searchChaName() {
		var txt = document.getElementById("seaChaName");
		var name = txt.value;
	    console.log("name:"+name);
		var xmlhttp = getXMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var names=eval("("+xmlhttp.responseText+")");
				document.getElementById("chaNameCon").innerHTML=""; 
				
				for(var key in names){
					document.getElementById("chaNameCon").innerHTML+="<div onmouseover='change1(this)' onmouseout='change2(this)' onclick='setChaName(this)'>"+names[key]+"</div>";
					document.getElementById("chaNameCon").innerHTML+="<input type='hidden' value='"+key+"' id='"+ names[key]+"'/>";
				}
				document.getElementById("chaNameCon").style.display="block";
			}
		};
	   var arg="name="+name+"&seaType=NAME";
       xmlhttp.open("POST","${pageContext.request.contextPath}/channel/searchChannel.do",true);  
       xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;");  
       xmlhttp.send(arg);
	}
	function setChaName(d){	
		document.getElementById("seaChaCode").value=document.getElementById(d.innerHTML).value;
		document.getElementById("seaChaName").value=d.innerHTML;
		document.getElementById("chaNameCon").style.display="none";
	}
	
	
	function searchChaCode() {	
		var txt = document.getElementById("seaChaCode");
		var name = txt.value;
	    console.log("name:"+name);
		var xmlhttp = getXMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var names=eval("("+xmlhttp.responseText+")");
				document.getElementById("chaCodeCon").innerHTML=""; 
				codeChannel = names;
				for(var key in names){
					document.getElementById("chaCodeCon").innerHTML+="<div onmouseover='change1(this)' onmouseout='change2(this)' onclick='setChaCode(this)'>"+key+"</div>";
					//document.getElementById("chaNameCon").innerHTML+="<input type='hidden' value='"+key+"' id='"+ names[key]+"'/>";
				}
				document.getElementById("chaCodeCon").style.display="block";
			}
		};
	   var arg="channelCode="+name+"";
       xmlhttp.open("POST","${pageContext.request.contextPath}/channel/searchChannel.do",true);  
       xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;");  
       xmlhttp.send(arg);
	}
	
	function setChaCode(d){	
		document.getElementById("seaChaName").value="";
		document.getElementById("seaChaCode").value=d.innerHTML;
		document.getElementById("chaCodeCon").style.display="none";
	}
	
	//关闭下拉列表
	function closeSelect(inputEle,id){   
	   setTimeout(function(){
		   document.getElementById(id).style.display="none";
		},1000);
	   
	   var v=$(inputEle).val();
	    if(null==v||""==v){
	     if(id=="chaNameCon"){
	 		    document.getElementById("seaChaCode").value="";
	 			document.getElementById("seaChaName").value="";
	 	   }else if(id=="chaCodeCon"){
	 		  document.getElementById("seaChaName").value="";
	 		  document.getElementById("seaChaCode").value="";
	 	   }
	    }
	  
	} 	  
</script>
	
</body>
</html>

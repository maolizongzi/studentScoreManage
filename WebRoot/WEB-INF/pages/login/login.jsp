<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>中彩汇后台系统</title>
		<style type="text/css">
			*{padding:0px;margin:0px;}
			.header{
				font-size: 48px;
				font-weight: 800;
				color: #3498db;
				padding:87px 50px;
			}
			.content{
				height: 443px;
				background: url(../media/img/bg.png) 0 0 no-repeat;
				background-size: 100% 100%;
				position: relative;
			}
			.login{
				margin:0 auto;
				width: 272px;
    			height: 218px;
				background:#fff;
				position: absolute;
				top:50%;
				left:50%;
				margin-top: -134px;
    			margin-left: -171px;
    			border-radius: 10px;
    			padding:25px 35px;
			}
			.login ul{
				list-style:none;
				text-align: right;
				color: #797979;
				font-family: "微软雅黑";
			}
			.login ul li{
				margin-top: 15px;
			}
			.login ul input{
				height: 30px;
				width: 200px;
				border: 1px solid #cecece;
				border-radius: 10px;
				outline: none;
				text-indent: 1em;
			}
			.login .code{
				font-size: 24px;
				color: #e74c3c;
				vertical-align: middle;
			}
			.login .btn{
				margin: 0 auto;
				height: 40px;
				margin-top: 20px;
				background: #2ecc71;
				color: #fff;
				border:none;
				font-size: 16px;
				text-indent: 0;
			}
		</style>
	<script language="javascript" type="text/javascript">
	
	if (window != top)
		top.location.href = location.href;

	
	      function reloadImage(imgurl){
	          var getimagecode=document.getElementById("codeimg");
	          getimagecode.src= imgurl + "?id=" + Math.random();
	      }
	</script>
	</head>
	<body>
		<div class="header">
			<span>中彩汇后台系统</span>
		</div>
		<div class="content">
			<div class="login">
			<form action="${ctx}/login/login.do" class="form-vertical login-form" method="post">
				<ul>
					<li>
						用户名：
						<input type="text"  name="userName" value="${userName}"/>
					</li>
					<li>
						密码：<input type="password" value="" name="userPassword"  value="${userPassword}"/>
					</li>
					<li>			
						验证码：<input type="text" name="code" value="" style="width:135px;"/>
						<span class="code">
							<img alt="点击刷新" src="<%=basePath%>vo.jsp"  id="codeimg" onclick="javascript:reloadImage('<%=basePath%>vo.jsp')" />
						</span>
					</li>
					<li style="text-align: center;">
						${mes}
					</li>
					<li style="text-align: center;">
						<input type="submit"  value="登录" />	
					</li>
				</ul>
				</form>
			</div>
		</div>
	</body>
</html>

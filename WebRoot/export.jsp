<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件导出测试</title>
</head>
<body>
<script type="text/javascript" src="script/jquery.js"></script>
<script type="text/javascript">
        function exportExcel() {
           // var maxPage = ${exportMaxPage};
           // var pageTotal = '${pageBean.pageTotal}';
           // var startPage = $("#inputStart").val();
          //  var endPage = $("#inputEnd").val();
          //  if (parseInt(startPage) > parseInt(pageTotal) || parseInt(endPage) > parseInt(pageTotal)) {
           //     alert("页码不能大于记录最大页码！");
          //      return;
          //  }
          //  if (parseInt(startPage) > parseInt(endPage)) {
         //       alert("页码输入错误！");
           //     return;
           // }
           // if (parseInt(endPage) - parseInt(startPage) > parseInt(maxPage)) {
           //     alert("一次最多只能导出3000条");
           //     return;
           // }
           // $("#startPage").val(startPage);
           // $("#endPage").val(endPage);
            var action = $("#form1").attr("action");
            $("#form1").attr("action", "${pageContext.request.contextPath}/export/cvs.do");
            $("#form1").submit();
            $("#form1").attr("action", action);
        }   
        
        
    </script>
  <a href="javascript:void(0)" onclick="exportExcel();" style="font-weight: bold;">导出</a>
  <a href="${pageContext.request.contextPath}/export/toList.do"  style="font-weight: bold;">company</a>
  
  	

  <form name="form1" id="form1" method="post" action=""></form>
</body>
</html>


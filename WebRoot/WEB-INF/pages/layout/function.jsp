<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/commons/taglibs.jsp" %>
<c:forEach items="${functions}" var="function">
	<c:choose>
		<c:when test="${fn:length(function.childFunctions) > 0}">
			<c:set var="functions" value="${function.childFunctions}" scope="request"></c:set>
			<li class="">
				<a href="javascript:;">
					<span class="title">${function.functionName} </span>
					<span class="arrow "></span>
				</a>
				<ul class="sub-menu">
					<c:import url="/WEB-INF/pages/layout/function.jsp"></c:import>
				</ul>
			</li>
		</c:when>
		<c:otherwise>
			<c:if test="${function.functionStyle!='no-show'}">
					<li class="">
						<a href="#" onclick="showTabPage('${function.functionId}')">
							<span <c:if test="${function.functionStyle == ''}"></c:if>>${function.functionName}  </span>
						</a>
					</li>
			</c:if>
		</c:otherwise>
	</c:choose>
</c:forEach>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/commons/taglibs.jsp"%>
<c:forEach items="${functionList}" var="function" varStatus="vs">
	<c:choose>
		<c:when test="${fn:length(function.childFunctions) > 0}">
			<br />
			<c:set var="functionList" value="${function.childFunctions}"
				scope="request"></c:set>
			<span title="Expand this branch" id="${function.functionParentFlag}">
				<i> <input type="checkbox" id="${function.functionParentFlag}"
					onclick="checkChildAll('${function.functionParentFlag}');"
					<c:if test="${fn:contains(userGroup.functionParentFlag,function.functionParentFlag) }">checked="checkbox"</c:if>
					value="${function.functionParentFlag}" name="functionParentFlag" />
			</i> ${function.functionName}
			</span>
			<br />
			<c:import url="/WEB-INF/pages/user/purviewList.jsp"></c:import>
		</c:when>
		<c:otherwise>
			<%-- <c:if test="${function.functionParentFlag=='a'}">
					<span	title="Expand this branch"><i><input type="checkbox" value="${function.functionParentFlag}" onclick="checkAll(this);" name="functionParentFlag" /> </i>全部</span>
				</c:if>
				<c:if test="${function.functionParentFlag!='a'}"> --%>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<span title="Expand this branch" id="${function.functionParentFlag}">
				<i> <input type="checkbox" id="${function.functionParentFlag}"
					<c:if test="${fn:contains(userGroup.functionParentFlag,function.functionParentFlag) }">checked="checkbox"</c:if>
					value="${function.functionParentFlag}" name="functionParentFlag" />
			</i> ${function.functionName}
			</span>
			<%-- </c:if> --%>
		</c:otherwise>
	</c:choose>
</c:forEach>
<script type="text/javascript">
function checkChildAll(id){
	
	if($("INPUT[id='"+id+"']").attr('checked') == 'checked'){
		$("INPUT[id^='"+id+"']").each( function() {
	        $(this).attr('checked', true);
	        $("span[id^='"+id+"']").find('span').addClass('checked') ;
		});
	}  else{
		$("INPUT[id^='"+id+"']:checkbox").attr("checked", false);
		$("INPUT[id^='"+id+"']").each( function() {
	        $(this).attr('checked', false);
	        $("span[id^='"+id+"']").find('span').removeClass('checked') ;
	});
	}
}
</script>
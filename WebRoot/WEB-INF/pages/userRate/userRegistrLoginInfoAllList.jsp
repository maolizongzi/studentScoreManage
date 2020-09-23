<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body class="page-header-fixed" style=" ">
	<div style="padding-left: 20px  0  0  0px ;  background-color: white;  height: 795px;  ">
				<table  class="table table-bordered table-hover"  id="allTableId"  style="overflow: scroll;min-width:300px" >
							<thead>
								<tr>
									<th>日期</th>
									<th>渠道              </th> 
									<th>用户量               </th>
									<th>二             </th> 
									<th>三             </th> 
									<th>四             </th> 
									<th>五        </th> 
									<th>六           </th> 
									<th>七           </th> 
									<th>十        </th> 
									<th>十五   </th> 
									<th>二十          </th> 
									<th>二十五          </th> 
									<th>三十  </th> 
								</tr>
							</thead>
							<tbody>
									<c:forEach items="${pageView.items}"  var="page" varStatus="vs" >
											<tr>
												<td style="text-align: left;">${page.timeFormat}</td>
												<td style="text-align: left;">
													<c:if test="${empty page.sid  }">汇总</c:if>
													<c:if test="${not empty page.sid  }">${page.sid}</c:if>                	
												</td>
												<td> ${page.registerdCount }</td>
												<td><fmt:formatNumber value="${page.twoDayRate}" pattern="0.00"/><c:if test="${not empty page.twoDayRate  }">%</c:if></td>
												<td><fmt:formatNumber value="${page.threeDayRate}" pattern="0.00"/><c:if test="${not empty page.threeDayRate  }">%</c:if></td>
												<td><fmt:formatNumber value="${page.fourDayRate}" pattern="0.00"/><c:if test="${not empty page.fourDayRate  }">%</c:if></td>
												<td><fmt:formatNumber value="${page.fiveDayRate}" pattern="0.00"/><c:if test="${not empty page.fiveDayRate  }">%</c:if></td>
												<td><fmt:formatNumber value="${page.sixDayRate}" pattern="0.00"/><c:if test="${not empty page.sixDayRate  }">%</c:if></td>
												<td><fmt:formatNumber value="${page.sevenDayRate}" pattern="0.00"/><c:if test="${not empty page.sevenDayRate  }">%</c:if></td>
												<td><fmt:formatNumber value="${page.tenDayRate}" pattern="0.00"/><c:if test="${not empty page.tenDayRate  }">%</c:if></td>
												<td><fmt:formatNumber value="${page.fifteenDayRate}" pattern="0.00"/><c:if test="${not empty page.fifteenDayRate  }">%</c:if></td>
												<td><fmt:formatNumber value="${page.twentyDayRate}" pattern="0.00"/><c:if test="${not empty page.twentyDayRate  }">%</c:if></td>
												<td><fmt:formatNumber value="${page.twentyFiveDayRate}" pattern="0.00"/><c:if test="${not empty page.twentyFiveDayRate  }">%</c:if></td>
												<td><fmt:formatNumber value="${page.thirtyDayRate}" pattern="0.00"/><c:if test="${not empty page.thirtyDayRate  }">%</c:if></td>
											</tr>
									</c:forEach>
								</tbody>
					</table>
					<%@include file="/WEB-INF/pages/layout/page.jsp" %>	
		</div>
</body>
</html>
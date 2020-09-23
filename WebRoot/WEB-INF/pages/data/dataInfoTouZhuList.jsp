<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body class="page-header-fixed">
	<div style="padding-left: 20px  0  0  0px ;  background-color: white;  height: 795px;  width: 100%; ">
					<table class="table table-bordered table-hover" style="overflow: scroll;min-width:1300px" id="touZhuTableId">
							<thead>
								<tr>
									<th> 日期 </th>
									<th> 渠道名称              </th> 
									<th> 激活数                </th>
									<th> 注册用户数              </th>
									<th> 投注用户数              </th>
									<th> 总投注金额              </th>
									<th> 投注新用户数             </th>
									<th> 新用户投注金额            </th>
									<th> 新用户注册投注转化率         </th>
									<th> 全部用户投注ARPU         </th>
									<th> 新用户投注ARPU          </th>
									<th> 赠金消耗               </th>
								</tr>
							</thead>
							<tbody>
									<c:forEach items="${pageView.items}"  var="page" varStatus="vs" >
											<tr>
												<td style="text-align: left;"> ${page.timeFormat              	}</td>
												<td style="text-align: left;"> ${page.channelName                  	}</td>
												<td> ${page.jiHuo               	}</td>
												<td> ${page.registeredUserCount                 	}</td>
												<td> ${page.touZhuUserCount      }</td>
												<td> <fmt:formatNumber value="${page.touZhuAllMoney}" pattern="0.00"/></td>
												<td> ${page.touZhuNewUserCount   }</td>
												<td> <fmt:formatNumber value="${page.touZhuNewUserMonery}" pattern="0.00"/></td>
												<td> <fmt:formatNumber value="${page.touZhuNewUserRate}" pattern="0.00"/>%</td>
												<td> <fmt:formatNumber value="${page.touZhuAllUserArpu}" pattern="0.00"/></td>
												<td> <fmt:formatNumber value="${page.touZhuNewUserArpu}" pattern="0.00"/></td>
												<td> <fmt:formatNumber value="${page.grantsConsume}" pattern="0.00"/></td>
											</tr>
									</c:forEach>
								</tbody>
					</table>
					<%@include file="/WEB-INF/pages/layout/page.jsp" %>	
	</div>
</body>
</html>
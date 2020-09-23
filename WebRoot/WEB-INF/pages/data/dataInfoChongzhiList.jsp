<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body class="page-header-fixed">
	<div style="padding-left: 20px  0  0  0px ;  background-color: white;  height: 795px;  width: 100%; ">
					<table class="table table-bordered table-hover" style="overflow: scroll;min-width:1300px"  id="chongZhiTableId" >
							<thead>
								<tr>
									<th>日期 </th>
									<th>渠道名称            </th>
									<th>激活数            </th>
									<th>注册用户数          </th>
									<th>充值用户数          </th>
									<th>总充值金额          </th>
									<th>充值新用户数         </th>
									<th>新用户充值金额        </th>
									<th>新用户注册充值转化率     </th>
									<th>全部用户人均充值金额     </th>
									<th>新用户人均充值金额      </th>
									<th>赠金             </th>
								</tr>
							</thead>
							<tbody>
									<c:forEach items="${pageView.items}"  var="page" varStatus="vs" >
											<tr  >
												<td style="text-align: left;" > ${page.timeFormat              	}</td>
												<td style="text-align: left;"> ${page.channelName                  	}</td>
												<td> ${page.jiHuo               	}</td>
												<td> ${page.registeredUserCount                 	}</td>
												<td> ${page.chongZhiUserCount      }</td>
												<td> <fmt:formatNumber value="${page.chongZhiAllMoney}" pattern="0.00"/></td>
												<td> ${page.chongZhiNewUserCount   }</td>
												<td> <fmt:formatNumber value="${page.chongZhiNewUserMonery}" pattern="0.00"/></td>
												<td> <fmt:formatNumber value="${page.chongZhiNewUserRate}" pattern="0.00"/>%</td>
												<td> <fmt:formatNumber value="${page.userAvgChongZhiMoney}" pattern="0.00"/></td>
												<td> <fmt:formatNumber value="${page.chongZhiNewUserAvgMoney}" pattern="0.00"/></td>
												<td> <fmt:formatNumber value="${page.grants}" pattern="0.00"/></td>
											</tr>
									</c:forEach>
								</tbody>
					</table>
					<%@include file="/WEB-INF/pages/layout/page.jsp" %>	
	</div>
</body>
</html>
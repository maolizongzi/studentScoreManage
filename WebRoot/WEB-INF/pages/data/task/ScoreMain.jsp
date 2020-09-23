<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body class="page-header-fixed">
	<div style="padding-left: 20px  0  0  0px ;  background-color: white;  height: 795px;  width: 100%; ">
					<table class="table table-bordered table-hover" style="overflow: scroll;min-width:1300px"  id="chongZhiTableId" >
							<thead>
								<tr>
									<th colspan="6">积分汇总日报</th>
								</tr>
								<tr>
									<th rowspan="2">日期</th>
									<th colspan="3">产生积分数</th>
									<th colspan="3">消耗积分数</th>
									<th rowspan="2">当天登录用户积分数量</th>
									<th rowspan="2">当天积分存量</th>
									<th rowspan="2">积分存量</th>
								</tr>
								<tr>
									<th>人数 </th>
									<th>积分数            </th>
									<th>人均产生积分            </th>
									<th>消耗人数          </th>
									<th>消耗积分数          </th>
									<th>人均消耗积分          </th>
								</tr>
							</thead>
							<tbody>
									<c:forEach items="${data}"  var="page" varStatus="vs" >
											<tr>
												<td >${page.currentDate}</td>
												<td >${page.makeScorePeopleCount}</td>
												<td >${page.makeScoreTotalScore}</td>
												<td >${page.makeScoreAverageScore}</td>
												<td >${page.costScorePeopleCount}</td>
												<td >${page.costScoreTotalScore}</td>
												<td >${page.costScoreAverageScore}</td>
												<td >${page.loginScoreTotalScore}</td>
												<td >${page.changeScore}</td>
												<td >${page.totalScoreInPool}</td>
											</tr>
									</c:forEach>
								</tbody>
					</table>
	</div>
</body>
</html>
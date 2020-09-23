<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body class="page-header-fixed">
	<div style="padding-left: 20px  0  0  0px ;  background-color: white;  height: 795px;  width: 100%; ">
					<table class="table table-bordered table-hover" style="overflow: scroll;min-width:1300px"  id="chongZhiTableId" >
							<thead>
								<tr>
									<th colspan="15">新增道具及碎片来源日报</th>
								</tr>
								<tr>
									<th rowspan="2">日期</th>
									<th colspan="3">积分兑换道具</th>
									<th colspan="6">积分抽奖道具</th>
									<th colspan="3">碎片合成道具</th>
									<th colspan="3">奖不停活动道具</th>
								</tr>
								<tr>
									<th>人数 </th>
									<th>道具数 </th>
									<th>人均兑换数 </th>
									<th>道具人数 </th>
									<th>道具数 </th>
									<th>人均道具数 </th>
									<th>碎片人数 </th>
									<th>碎片数 </th>
									<th>人均碎片数 </th>
									<th>人数 </th>
									<th>道具数 </th>
									<th>人均合成数 </th>
									<th>人数 </th>
									<th>道具数 </th>
									<th>人均抽中数 </th>
								</tr>
							</thead>
							<tbody>
									<c:forEach items="${data}"  var="page" varStatus="vs" >
											<tr>
												<td >${page.currentDate}</td>
												<td >${page.buyPropPeopleCount}</td>
												<td >${page.buyPropPropCount}</td>
												<td >${page.buyPropAverageCount}</td>
												<td >${page.drawPropPeopleCount}</td>
												<td >${page.drawPropCount}</td>
												<td >${page.drawPropAverageCount}</td>
												<td >${page.drawFragPeopleCount}</td>
												<td >${page.drawFragCount}</td>
												<td >${page.drawFragAverageCount}</td>
												<td >${page.hechengPeopleCount}</td>
												<td >${page.hechengPropCount}</td>
												<td >${page.hechengAverageCount}</td>
												<td >${page.jiangbutingPeopleCont}</td>
												<td >${page.jiangbutingPropCount}</td>
												<td >${page.jiangbutingAverageCount}</td>
											</tr>
									</c:forEach>
								</tbody>
					</table>
	</div>
</body>
</html>
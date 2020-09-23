<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body class="page-header-fixed">
	<div style="padding-left: 20px  0  0  0px ;  background-color: white;  height: 795px;  width: 100%; ">
					<table class="table table-bordered table-hover" style="overflow: scroll;min-width:1300px"  id="chongZhiTableId" >
							<thead>
								<tr>
									<th colspan="20">任务汇总日报</th>
								</tr>
								<tr>
									<th rowspan="2">日期 </th>
									<th colspan="6">任务完成情况汇总 </th>
									<th colspan="6">今日任务完成情况汇总 </th>
									<th colspan="8">成就任务完成情况汇总 </th>
								</tr>
								<tr>
									<th>完成人数 </th>
									<th>完成任务数 </th>
									<th>领取奖励任务数 </th>
									<th>奖励积分数 </th>
									<th>奖励领取人数 </th>
									<th>人均奖励积分 </th>
									<th>完成人数 </th>
									<th>完成任务数 </th>
									<th>领取奖励任务数 </th>
									<th>奖励积分数 </th>
									<th>奖励领取人数 </th>
									<th>人均奖励积分 </th>
									<th>完成人数 </th>
									<th>完成任务数 </th>
									<th>领取奖励任务数 </th>
									<th>奖励积分数 </th>
									<th>奖励领取人数 </th>
									<th>人均奖励积分 </th>
									<th>获得称号人数 </th>
									<th>获得称号数 </th>
								</tr>
							</thead>
							<tbody>
									<c:forEach items="${data}"  var="page" varStatus="vs" >
											<tr>
												<td >${page.currentDate}</td>
												<td >${page.taskPeopleCount}</td>
												<td >${page.taskTaskCount}</td>
												<td >${page.taskRecoverTaskCount}</td>
												<td >${page.taskScore}</td>
												<td >${page.taskRecoverPeopleCount}</td>
												<td >${page.taskAverageScore}</td>
												<td >${page.dayTaskPeopleCount}</td>
												<td >${page.dayTaskTaskCount}</td>
												<td >${page.dayTaskRecoverTaskCount}</td>
												<td >${page.dayTaskScore}</td>
												<td >${page.dayTaskRecoverPeopleCount}</td>
												<td >${page.dayTaskAverageScore}</td>
												<td >${page.achieveTaskPeopleCount}</td>
												<td >${page.achieveTaskTaskCount}</td>
												<td >${page.achieveTaskRecoverTaskCount}</td>
												<td >${page.achieveTaskScore}</td>
												<td >${page.achieveTaskRecoverPeopleCount}</td>
												<td >${page.achieveTaskAverageScore}</td>
												<td >${page.catchTitlePeopleCount}</td>
												<td >${page.catchTitleTitleCount}</td>
											</tr>
									</c:forEach>
								</tbody>
					</table>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body class="page-header-fixed">
	<div style="padding-left: 20px  0  0  0px ;  background-color: white;  height: 795px;  width: 100%; ">
					<table class="table table-bordered table-hover" style="overflow: scroll;min-width:1300px"  id="chongZhiTableId" >
							<thead>
								<tr>
									<th colspan="32">积分详情日报</th>
								</tr>
								<tr>
									<th rowspan="2">日期 </th>
									<th colspan="3">任务产生积分 </th>
									<th colspan="3">积分抽奖消耗积分 </th>
									<th colspan="3">兑换道具消耗积分 </th>
									<th colspan="3">兑换实物消耗积分 </th>
									<th colspan="6">猜神驾到产生积分 </th>
									<th colspan="3">猜神驾到消耗积分 </th>
									<th colspan="1">猜神驾到回收 </th>
									<th colspan="3">道具回收产生积分 </th>
								</tr>
								<tr>
									<th>奖励积分数 </th>
									<th>奖励领取人数 </th>
									<th>人均任务积分 </th>
									<th>抽奖人数 </th>
									<th>消耗积分数 </th>
									<th>人均抽奖积分 </th>
									<th>兑换人数 </th>
									<th>消耗积分数 </th>
									<th>人均兑换积分 </th>
									<th>兑换人数</th>
									<th>消耗积分</th>
									<th>人均消耗积分</th>
									<th>猜中人数 </th>
									<th>奖励积分数 </th>
									<th>人均奖励积分 </th>
									<th>购买好运值人数 </th>
									<th>购买积分数 </th>
									<th>人均购买积分数 </th>
									<th>竞猜人数 </th>
									<th>消耗积分数 </th>
									<th>人均竞猜积分 </th>
									<th>回收积分数 </th>
									<th>人数 </th>
									<th>产生积分数 </th>
									<th>人均积分数 </th>
								</tr>
							</thead>
							<tbody>
									<c:forEach items="${data}"  var="page" varStatus="vs" >
											<tr>
												<td >${page.currentDate}</td>
												<td >${page.taskCompleteRewardScore}</td>
												<td >${page.taskCompleteRecivePeopleCount}</td>
												<td >${page.taskCompleteAverageTaskScore}</td>
												<td >${page.costDrawPeopleCount}</td>
												<td >${page.costDrawCostScore}</td>
												<td >${page.costDrawAverageDrawScore}</td>
												<td >${page.buyPropPeopleCount}</td>
												<td >${page.buyPropCostScore}</td>
												<td >${page.buyPropAverageCostScore}</td>
												<td >${page.buyInventedPropPeopleCount}</td>
												<td >${page.buyInventedPropScoreCount}</td>
												<td >${page.buyInventedPropAverageCount}</td>
												<td >${page.guessGodMakeSuccessPeopleCount}</td>
												<td >${page.guessGodMakeSocre}</td>
												<td >${page.guessGodMakeAverageScore}</td>
												<td >${page.buyLuckySocrePeopleCount}</td>
												<td >${page.buyLuckyScoreScore}</td>
												<td >${page.buyLuckyScoreAverageScore}</td>
												<td >${page.guessGodCostPeopleCount}</td>
												<td >${page.guessGodCostSocre}</td>
												<td >${page.guessGodCostAverageScore}</td>
												<td >${page.guessGodReciveScore}</td>
												<td >${page.reclaimPeople}</td>
												<td >${page.reclaimScore}</td>
												<td >${page.reclaimAverageScore}</td>
											</tr>
									</c:forEach>
								</tbody>
					</table>
	</div>
</body>
</html>
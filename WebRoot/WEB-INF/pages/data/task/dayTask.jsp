<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body class="page-header-fixed">
	<div style="padding-left: 20px  0  0  0px ;  background-color: white;  height: 795px;  width: 100%; ">
					<table class="table table-bordered table-hover" style="overflow: scroll;min-width:1300px"  id="chongZhiTableId" >
							<thead>
								<tr>
									<th colspan="19">今日任务完成人数分布</th>
								</tr>
								<tr>
									<th>日期 </th>
									<th>今日登录 </th>
									<th>分享1次投注记录 </th>
									<th>今日成功充值10元 </th>
									<th>今日成功充值20元 </th>
									<th>今日成功充值50元 </th>
									<th>今日成功充值100元 </th>
									<th>今日成功充值200元 </th>
									<th>今日成功充值500元 </th>
									<th>今日投注满10元 </th>
									<th>今日投注满30元 </th>
									<th>今日投注满50元 </th>
									<th>今日投注满100元 </th>
									<th>今日投注满300元 </th>
									<th>今日投注满500元 </th>
									<th>今日投注满1000元 </th>
									<th>今日成功投注1次 </th>
									<th>今日成功投注5次 </th>
									<th>今日成功投注10次 </th>
								</tr>
							</thead>
							<tbody>
									<c:forEach items="${data}"  var="page" varStatus="vs" >
											<tr>
												<td >${page.currentDate}</td>
												<td >${page.login}</td>
												<td >${page.shareBetting1}</td>
												<td >${page.recharge10}</td>
												<td >${page.recharge20}</td>
												<td >${page.recharge50}</td>
												<td >${page.recharge100}</td>
												<td >${page.recharge200}</td>
												<td >${page.recharge500}</td>
												<td >${page.betting10}</td>
												<td >${page.betting30}</td>
												<td >${page.betting50}</td>
												<td >${page.betting100}</td>
												<td >${page.betting300}</td>
												<td >${page.betting500}</td>
												<td >${page.betting1000}</td>
												<td >${page.bettingTimes1}</td>
												<td >${page.bettingTimes5}</td>
												<td >${page.bettingTimes10}</td>
											</tr>
									</c:forEach>
								</tbody>
					</table>
	</div>
</body>
</html>
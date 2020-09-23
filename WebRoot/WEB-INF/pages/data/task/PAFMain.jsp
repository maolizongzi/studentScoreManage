<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body class="page-header-fixed">
	<div style="padding-left: 20px  0  0  0px ;  background-color: white;  height: 795px;  width: 100%; ">
					<table class="table table-bordered table-hover" style="overflow: scroll;min-width:1300px"  id="chongZhiTableId" >
							<thead>
								<tr>
									<th colspan="13">道具及碎片汇总日报</th>
								</tr>
								<tr>
									<th rowspan="2">日期 </th>
									<th colspan="3">新增道具 </th>
									<th colspan="3">使用道具 </th>
									<th colspan="3">返利卡使用情况 </th>
									<th colspan="3">保底卡使用情况 </th>
									<th colspan="3">加奖卡使用情况 </th>
									<th colspan="3">活动道具使用情况 </th>
									<th colspan="3">道具回收情况</th>
									<th colspan="2">过期道具 </th>
									<th colspan="2">剩余道具 </th>
									<th colspan="3">新增碎片 </th>
									<th colspan="3">碎片回收情况</th>
									<th >剩余碎片 </th>
								</tr>
								<tr>
									<th>人数 </th>
									<th>道具数 </th>
									<th>人均新增数 </th>
									<th>使用人数 </th>
									<th>使用道具数 </th>
									<th>人均使用数 </th>
									<th>人数 </th>
									<th>道具数 </th>
									<th>人均使用数 </th>
									<th>人数 </th>
									<th>道具数 </th>
									<th>人均使用数 </th>
									<th>人数 </th>
									<th>道具数 </th>
									<th>人均使用数 </th>
									<th>人数 </th>
									<th>道具数 </th>
									<th>人均使用数 </th>
									<th>回收人数 </th>
									<th>回收道具数 </th>
									<th>人均回收道具数 </th>
									<th>过期道具数 </th>
									<th>累计过期道具数 </th>
									<th>当天剩余道具数 </th>
									<th>累计剩余道具数 </th>
									<th>人数 </th>
									<th>碎片数 </th>
									<th>人均新增数 </th>
									<th>回收人数 </th>
									<th>回收碎片数 </th>
									<th>人均回收碎片数 </th>
									<th>累计剩余碎片数 </th>
								</tr>
							</thead>
							<tbody>
									<c:forEach items="${data}"  var="page" varStatus="vs" >
											<tr>
												<td >${page.currentDate}</td>
												<td >${page.newPropPeopleCount}</td>
												<td >${page.newPropPropCount}</td>
												<td >${page.newPropAverageCount}</td>
												<td >${page.usedPropPeopleCount}</td>
												<td >${page.usedPropPropCount}</td>
												<td >${page.usedPropAverageCount}</td>
												<td >${page.fanliPeopleCount}</td>
												<td >${page.fanliPropCount}</td>
												<td >${page.fanliAverageCount}</td>
												<td >${page.baodiPeopleCount}</td>
												<td >${page.baodiPropCount}</td>
												<td >${page.baodiAverageCount}</td>
												<td >${page.jiajiangPeopleCount}</td>
												<td >${page.jiajiangPropCount}</td>
												<td >${page.jiajiangAverageCount}</td>
												<td >${page.huodongPeopleCount}</td>
												<td >${page.huodongPropCount}</td>
												<td >${page.huodongAverageCount}</td>
												<td >${page.reclaimPropPeopleCount}</td>
												<td >${page.reclaimPropPropCount}</td>
												<td >${page.reclaimPropAverageCount}</td>
												<td >${page.guoqiDayPropCount}</td>
												<td >${page.guoqiTotalPropCount}</td>
												<td >${page.shengyuDayPropCount}</td>
												<td >${page.shengyuTotalPropCount}</td>
												<td >${page.newFragPeopleCount}</td>
												<td >${page.newFragFragCount}</td>
												<td >${page.newFragAverageCount}</td>
												<td >${page.reclaimFragPeopleCount}</td>
												<td >${page.reclaimFragCount}</td>
												<td >${page.reclaimAverageFragCount}</td>
												<td >${page.shengyuFragCount}</td>
											</tr>
									</c:forEach>
								</tbody>
					</table>
	</div>
</body>
</html>
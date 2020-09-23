<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body class="page-header-fixed">
	<div style="padding-left: 20px  0  0  0px ;  background-color: white;  height: 795px;  width: 100%; ">
					<table class="table table-bordered table-hover" style="overflow: scroll;min-width:1300px"  id="chongZhiTableId" >
							<thead>
								<tr>
									<th colspan="60">称号获得人数分布</th>
								</tr>
								<tr>
									<th>日期 </th>
									<th>初出茅庐 </th>
									<th>新手上路 </th>
									<th>扬帆起航 </th>
									<th>财大气粗 </th>
									<th>有福同享 </th>
									<th>慧眼识珠 </th>
									<th>初学乍练 </th>
									<th>熟能生巧 </th>
									<th>得心应手 </th>
									<th>精益求精 </th>
									<th>炉火纯青 </th>
									<th>出神入化 </th>
									<th>小试牛刀 </th>
									<th>势不可挡 </th>
									<th>乘风破浪 </th>
									<th>风雷涌动 </th>
									<th>雷霆万钧 </th>   
									<th>龙威虎震 </th>
									<th>降龙伏虎 </th>
									<th>气吞山河 </th>
									<th>叱咤风云 </th>
									<th>盖世无双 </th>
									<th>惊天动地 </th>
									<th>初露锋芒 </th>
									<th>时来运转 </th>
									<th>运势渐佳 </th>
									<th>好运连连 </th>
									<th>顺风顺水 </th>
									<th>鸿运当头 </th>
									<th>洪福齐天 </th>
									<th>小有成就 </th>
									<th>崭露头角 </th>
									<th>略有小成 </th>
									<th>出类拔萃 </th>
									<th>不同凡响 </th>
									<th>一鸣惊人 </th>
									<th>大有所为 </th>
									<th>一举成名 </th>
									<th>硕果累累 </th>
									<th>功成名就 </th>
									<th>震古烁今 </th>
									<th>乐善好施 </th>
									<th>福杯满溢 </th>
									<th>仗义疏财 </th>
									<th>扶危济困 </th>
									<th>广结善缘 </th>
									<th>积善成德 </th>
									<th>默默无闻 </th>
									<th>引人注目 </th>
									<th>小有名气 </th>
									<th>远近闻名 </th>
									<th>声名远扬 </th>
									<th>大名鼎鼎 </th>
									<th>名满天下 </th>
									<th>初来乍到 </th>
									<th>再接再厉 </th>
									<th>坚持不懈 </th>
									<th>一如既往 </th>
									<th>持之以恒 </th>

								</tr>
							</thead>
							<tbody>
									<c:forEach items="${data}"  var="page" varStatus="vs" >
											<tr>
												<td >${page.currentDate}</td>
												<td >${page.chuChuMaoLu}</td>
												<td >${page.xinShouShangLu}</td>
												<td >${page.yangFanQiHang}</td>
												<td >${page.caiDaQiCu}</td>
												<td >${page.youFuTongXiang}</td>
												<td >${page.huiYanShiZhu}</td>
												<td >${page.chuXueZhaLian}</td>
												<td >${page.shuNengShengQiao}</td>
												<td >${page.deXinYingShou}</td>
												<td >${page.jingYiQiuJing}</td>
												<td >${page.luHuoChunQing}</td>
												<td >${page.chuShenRuHua}</td>
												<td >${page.xiaoShiNiuDao}</td>
												<td >${page.shiBuKeDang}</td>
												<td >${page.chengFengPoLang}</td>
												<td >${page.fengLeiYongDong}</td>
												<td >${page.leiTingWanJun}</td>
												<td >${page.longWeiHuZhen}</td>
												<td >${page.xiangLongFuHu}</td>
												<td >${page.qiTunShanHe}</td>
												<td >${page.chiChaFengYun}</td>
												<td >${page.gaiShiWuShuang}</td>
												<td >${page.jingTianDongDi}</td>
												<td >${page.chuLouFengMang}</td>
												<td >${page.shiLaiYunZhuan}</td>
												<td >${page.yunShiJianJia}</td>
												<td >${page.haoYunLianLian}</td>
												<td >${page.ShunFengShunShui}</td>
												<td >${page.hongYunDangTou}</td>
												<td >${page.hongFuQiTian}</td>
												<td >${page.xiaoYouChengJiu}</td>
												<td >${page.zhanLuTouJiao}</td>
												<td >${page.lueYouXiaoCheng}</td>
												<td >${page.chuLeiBaCui}</td>
												<td >${page.buTongFanXaing}</td>
												<td >${page.yiMingJingRen}</td>
												<td >${page.daYouSuoWei}</td>
												<td >${page.yiJuChengMing}</td>
												<td >${page.shuoGuoLeiLei}</td>
												<td >${page.gongChengMingJiu}</td>
												<td >${page.zhenGuShuoJin}</td>
												<td >${page.leShanHaoShi}</td>
												<td >${page.fuBeiManYi}</td>
												<td >${page.zhangYiShuCai}</td>
												<td >${page.fuWeiJiKun}</td>
												<td >${page.guangJieShanYuan}</td>
												<td >${page.jiShanChengDe}</td>
												<td >${page.moMoWuWen}</td>
												<td >${page.yinRenZhuMu}</td>
												<td >${page.xiaoYouMingQi}</td>
												<td >${page.yuanJinWenMing}</td>
												<td >${page.shengMingYuanYang}</td>
												<td >${page.daMingDingDing}</td>
												<td >${page.mingManTianXia}</td>
												<td >${page.chuLaiZhaDao}</td>
												<td >${page.zaiJieZaiLi}</td>
												<td >${page.jianChiBuxie}</td>
												<td >${page.yiRuJiWang}</td>
												<td >${page.chiZhiYiHeng}</td>
											</tr>
									</c:forEach>
								</tbody>
					</table>
	</div>
</body>
</html>
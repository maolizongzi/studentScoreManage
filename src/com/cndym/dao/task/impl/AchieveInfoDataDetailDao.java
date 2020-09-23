package com.cndym.dao.task.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.NumberUtils;

import com.cndym.dao.task.IDataTableDetailDao;
import com.cndym.entity.data.task.AchieveInfoData;
import com.cndym.entity.data.task.DataDetail;
import com.cndym.util.export.Utils;

@Repository
public class AchieveInfoDataDetailDao implements IDataTableDetailDao {

	@Resource
	private JdbcTemplate taskJdbcTemplate;
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public DataDetail loadFromSource(Date start, Date end) {
		String sql = "SELECT \"count\"(*) count,t.title title FROM t_user_title ut LEFT JOIN t_title t ON t.\"id\" = ut.title_id WHERE create_time = ? GROUP BY t.\"id\"";
		List<Map<String,Object>> queryForList = taskJdbcTemplate.queryForList(sql,start);
		Map<String,Integer> data = new HashMap<String, Integer>();
		for(Map<String, Object> m : queryForList){
			String taskName = m.get("title").toString();
			Integer count = NumberUtils.parseNumber(m.get("count").toString(), Integer.class);
			data.put(taskName, count);
		}
		AchieveInfoData d = formatData(data);
		d.setCurrentDate(start);
		return d;
	}

	@Override
	public void saveToLocal(DataDetail data) {
		String sql = "insert into TASK_ACHIEVE_INFO_DATA\n" +
			"(id,c_date,title_count,title_name)\n" + 
			"values\n" +
			"(SEQ_TASK_ACHIEVE_INFO_DATA.NEXTVAL,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		AchieveInfoData d= (AchieveInfoData) data;
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getChuChuMaoLu(),"初出茅庐");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getXinShouShangLu(),"新手上路");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getYangFanQiHang(),"扬帆起航");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getCaiDaQiCu(),"财大气粗");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getYouFuTongXiang(),"有福同享");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getHuiYanShiZhu(),"慧眼识珠");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getChuXueZhaLian(),"初学乍练");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getShuNengShengQiao(),"熟能生巧");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getDeXinYingShou(),"得心应手");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getJingYiQiuJing(),"精益求精");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getLuHuoChunQing(),"炉火纯青");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getChuShenRuHua(),"出神入化");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getXiaoShiNiuDao(),"小试牛刀");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getShiBuKeDang(),"势不可挡");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getChengFengPoLang(),"乘风破浪");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getFengLeiYongDong(),"风雷涌动");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getLeiTingWanJun(),"雷霆万钧");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getLongWeiHuZhen(),"龙威虎震");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getXiangLongFuHu(),"降龙伏虎");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getQiTunShanHe(),"气吞山河");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getChiChaFengYun(),"叱咤风云");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getGaiShiWuShuang(),"盖世无双");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getJingTianDongDi(),"惊天动地");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getChuLouFengMang(),"初露锋芒");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getShiLaiYunZhuan(),"时来运转");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getYunShiJianJia(),"运势渐佳");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getHaoYunLianLian(),"好运连连");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getShunFengShunShui(),"顺风顺水");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getHongYunDangTou(),"鸿运当头");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getHongFuQiTian(),"洪福齐天");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getXiaoYouChengJiu(),"小有成就");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getZhanLuTouJiao(),"崭露头角");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getLueYouXiaoCheng(),"略有小成");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getChuLeiBaCui(),"出类拔萃");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getBuTongFanXaing(),"不同凡响");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getYiMingJingRen(),"一鸣惊人");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getDaYouSuoWei(),"大有所为");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getYiJuChengMing(),"一举成名");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getShuoGuoLeiLei(),"硕果累累");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getGongChengMingJiu(),"功成名就");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getZhenGuShuoJin(),"震古烁今");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getLeShanHaoShi(),"乐善好施");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getFuBeiManYi(),"福杯满溢");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getZhangYiShuCai(),"仗义疏财");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getFuWeiJiKun(),"扶危济困");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getGuangJieShanYuan(),"广结善缘");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getJiShanChengDe(),"积善成德");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getMoMoWuWen(),"默默无闻");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getYinRenZhuMu(),"引人注目");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getXiaoYouMingQi(),"小有名气");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getYuanJinWenMing(),"远近闻名");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getShengMingYuanYang(),"声名远扬");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getDaMingDingDing(),"大名鼎鼎");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getMingManTianXia(),"名满天下");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getChuLaiZhaDao(),"初来乍到");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getZaiJieZaiLi(),"再接再厉");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getJianChiBuxie(),"坚持不懈");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getYiRuJiWang(),"一如既往");
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getChiZhiYiHeng(),"持之以恒");
		
	}

	@Override
	public List<DataDetail> loadFromLoacl(Date start, Date end) {
		String sql = "select * from task_achieve_info_data where c_date>=? and c_date<=? order by c_date desc";
		List<Map<String,Object>> queryForList = jdbcTemplate.queryForList(sql,start,end);
		Map<String,Integer> data = new HashMap<String, Integer>();
		List<DataDetail> result = new ArrayList<DataDetail>();
		String lastDate = "";
		AchieveInfoData d = null;
		for(Map<String,Object> map : queryForList){
			String date = map.get("C_DATE").toString();
			if(!date.equals(lastDate)&&!"".equals(lastDate)){
				d = formatData(data);
				d.setCurrentDate(Utils.getDate(lastDate));
				result.add(d);
				data.clear();
			}
			lastDate = date;
			String titleName = map.get("TITLE_NAME")==null?"":map.get("TITLE_NAME").toString();
			Integer count = map.get("TITLE_COUNT")==null?0:NumberUtils.parseNumber(map.get("TITLE_COUNT").toString(), Integer.class);
			data.put(titleName, count);
		}
		d = formatData(data);
		d.setCurrentDate(Utils.getDate(lastDate));
		result.add(d);
		return result;
	}
	
	private AchieveInfoData formatData(Map<String,Integer> data){
		AchieveInfoData d= new AchieveInfoData();
		d.setChuChuMaoLu(data.get("初出茅庐")==null?0:data.get("初出茅庐"));
		d.setXinShouShangLu(data.get("新手上路")==null?0:data.get("新手上路"));
		d.setYangFanQiHang(data.get("扬帆起航")==null?0:data.get("扬帆起航"));
		d.setCaiDaQiCu(data.get("财大气粗")==null?0:data.get("财大气粗"));
		d.setYouFuTongXiang(data.get("有福同享")==null?0:data.get("有福同享"));
		d.setHuiYanShiZhu(data.get("慧眼识珠")==null?0:data.get("慧眼识珠"));
		d.setChuXueZhaLian(data.get("初学乍练")==null?0:data.get("初学乍练"));
		d.setShuNengShengQiao(data.get("熟能生巧")==null?0:data.get("熟能生巧"));
		d.setDeXinYingShou(data.get("得心应手")==null?0:data.get("得心应手"));
		d.setJingYiQiuJing(data.get("精益求精")==null?0:data.get("精益求精"));
		d.setLuHuoChunQing(data.get("炉火纯青")==null?0:data.get("炉火纯青"));
		d.setChuShenRuHua(data.get("出神入化")==null?0:data.get("出神入化"));
		d.setXiaoShiNiuDao(data.get("小试牛刀")==null?0:data.get("小试牛刀"));
		d.setShiBuKeDang(data.get("势不可挡")==null?0:data.get("势不可挡"));
		d.setChengFengPoLang(data.get("乘风破浪")==null?0:data.get("乘风破浪"));
		d.setFengLeiYongDong(data.get("风雷涌动")==null?0:data.get("风雷涌动"));
		d.setLeiTingWanJun(data.get("雷霆万钧")==null?0:data.get("雷霆万钧"));
		d.setLongWeiHuZhen(data.get("龙威虎震")==null?0:data.get("龙威虎震"));
		d.setXiangLongFuHu(data.get("降龙伏虎")==null?0:data.get("降龙伏虎"));
		d.setQiTunShanHe(data.get("气吞山河")==null?0:data.get("气吞山河"));
		d.setChiChaFengYun(data.get("叱咤风云")==null?0:data.get("叱咤风云"));
		d.setGaiShiWuShuang(data.get("盖世无双")==null?0:data.get("盖世无双"));
		d.setJingTianDongDi(data.get("惊天动地")==null?0:data.get("惊天动地"));
		d.setChuLouFengMang(data.get("初露锋芒")==null?0:data.get("初露锋芒"));
		d.setShiLaiYunZhuan(data.get("时来运转")==null?0:data.get("时来运转"));
		d.setYunShiJianJia(data.get("运势渐佳")==null?0:data.get("运势渐佳"));
		d.setHaoYunLianLian(data.get("好运连连")==null?0:data.get("好运连连"));
		d.setShunFengShunShui(data.get("顺风顺水")==null?0:data.get("顺风顺水"));
		d.setHongYunDangTou(data.get("鸿运当头")==null?0:data.get("鸿运当头"));
		d.setHongFuQiTian(data.get("洪福齐天")==null?0:data.get("洪福齐天"));
		d.setXiaoYouChengJiu(data.get("小有成就")==null?0:data.get("小有成就"));
		d.setZhanLuTouJiao(data.get("崭露头角")==null?0:data.get("崭露头角"));
		d.setLueYouXiaoCheng(data.get("略有小成")==null?0:data.get("略有小成"));
		d.setChuLeiBaCui(data.get("出类拔萃")==null?0:data.get("出类拔萃"));
		d.setBuTongFanXaing(data.get("不同凡响")==null?0:data.get("不同凡响"));
		d.setYiMingJingRen(data.get("一鸣惊人")==null?0:data.get("一鸣惊人"));
		d.setDaYouSuoWei(data.get("大有所为")==null?0:data.get("大有所为"));
		d.setYiJuChengMing(data.get("一举成名")==null?0:data.get("一举成名"));
		d.setShuoGuoLeiLei(data.get("硕果累累")==null?0:data.get("硕果累累"));
		d.setGongChengMingJiu(data.get("功成名就")==null?0:data.get("功成名就"));
		d.setZhenGuShuoJin(data.get("震古烁今")==null?0:data.get("震古烁今"));
		d.setLeShanHaoShi(data.get("乐善好施")==null?0:data.get("乐善好施"));
		d.setFuBeiManYi(data.get("福杯满溢")==null?0:data.get("福杯满溢"));
		d.setZhangYiShuCai(data.get("仗义疏财")==null?0:data.get("仗义疏财"));
		d.setFuWeiJiKun(data.get("扶危济困")==null?0:data.get("扶危济困"));
		d.setGuangJieShanYuan(data.get("广结善缘")==null?0:data.get("广结善缘"));
		d.setJiShanChengDe(data.get("积善成德")==null?0:data.get("积善成德"));
		d.setMoMoWuWen(data.get("默默无闻")==null?0:data.get("默默无闻"));
		d.setYinRenZhuMu(data.get("引人注目")==null?0:data.get("引人注目"));
		d.setXiaoYouMingQi(data.get("小有名气")==null?0:data.get("小有名气"));
		d.setYuanJinWenMing(data.get("远近闻名")==null?0:data.get("远近闻名"));
		d.setShengMingYuanYang(data.get("声名远扬")==null?0:data.get("声名远扬"));
		d.setDaMingDingDing(data.get("大名鼎鼎")==null?0:data.get("大名鼎鼎"));
		d.setMingManTianXia(data.get("名满天下")==null?0:data.get("名满天下"));
		d.setChuLaiZhaDao(data.get("初来乍到")==null?0:data.get("初来乍到"));
		d.setZaiJieZaiLi(data.get("再接再厉")==null?0:data.get("再接再厉"));
		d.setJianChiBuxie(data.get("坚持不懈")==null?0:data.get("坚持不懈"));
		d.setYiRuJiWang(data.get("一如既往")==null?0:data.get("一如既往"));
		d.setChiZhiYiHeng(data.get("持之以恒")==null?0:data.get("持之以恒"));
		return d;
	}

	@Override
	public void deleteFromLocal(Date start, Date end) {
		String sql = "delete from TASK_ACHIEVE_INFO_DATA where c_date>=? and c_date<=? ";
		jdbcTemplate.update(sql,start,end);
	}

}

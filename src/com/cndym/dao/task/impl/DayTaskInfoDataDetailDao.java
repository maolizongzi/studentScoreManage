package com.cndym.dao.task.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.NumberUtils;

import com.cndym.dao.task.IDataTableDetailDao;
import com.cndym.entity.data.task.DataDetail;
import com.cndym.entity.data.task.DayTaskInfoData;

@Repository
public class DayTaskInfoDataDetailDao implements IDataTableDetailDao {

	@Resource
	private JdbcTemplate taskJdbcTemplate;
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public DataDetail loadFromSource(Date start, Date end) {
		String sql = "select \"count\"(*) count,c.task_name \"name\" FROM t_task_conf c LEFT JOIN t_user_task_rec r ON r.task_id = c.\"id\" WHERE c.task_type = '0' AND r.finish_date=? GROUP BY c.\"id\"";
		List<Map<String, Object>> q = taskJdbcTemplate.queryForList(sql,start);
		Map<String,Integer> data = new HashMap<String, Integer>();
		for(Map<String, Object> m : q){
			String taskName = m.get("name").toString();
			Integer count = NumberUtils.parseNumber(m.get("count").toString(), Integer.class);
			data.put(taskName, count);
		}
		DayTaskInfoData d= new DayTaskInfoData();
		d.setCurrentDate(start);
		d.setLogin(data.get("今日登录")==null?0:data.get("今日登录"));
		d.setShareBetting1(data.get("分享1次投注记录")==null?0:data.get("分享1次投注记录"));
		d.setRecharge10(data.get("今日成功充值10元")==null?0:data.get("今日成功充值10元"));
		d.setRecharge20(data.get("今日成功充值20元")==null?0:data.get("今日成功充值20元"));
		d.setRecharge50(data.get("今日成功充值50元")==null?0:data.get("今日成功充值50元"));
		d.setRecharge100(data.get("今日成功充值100元")==null?0:data.get("今日成功充值100元"));
		d.setRecharge200(data.get("今日成功充值200元")==null?0:data.get("今日成功充值200元"));
		d.setRecharge500(data.get("今日成功充值500元")==null?0:data.get("今日成功充值500元"));
		d.setBetting10(data.get("今日投注满10元")==null?0:data.get("今日投注满10元"));
		d.setBetting30(data.get("今日投注满30元")==null?0:data.get("今日投注满30元"));
		d.setBetting50(data.get("今日投注满50元")==null?0:data.get("今日投注满50元"));
		d.setBetting100(data.get("今日投注满100元")==null?0:data.get("今日投注满100元"));
		d.setBetting300(data.get("今日投注满300元")==null?0:data.get("今日投注满300元"));
		d.setBetting500(data.get("今日投注满500元")==null?0:data.get("今日投注满500元"));
		d.setBetting1000(data.get("今日投注满1000元")==null?0:data.get("今日投注满1000元"));
		d.setBettingTimes1(data.get("今日成功投注1次")==null?0:data.get("今日成功投注1次"));
		d.setBettingTimes5(data.get("今日成功投注5次")==null?0:data.get("今日成功投注5次"));
		d.setBettingTimes10(data.get("今日成功投注10次")==null?0:data.get("今日成功投注10次"));
		return d;
	}

	@Override
	public void saveToLocal(DataDetail data) {
		String sql = "insert into TASK_DAY_TASK_INFO\n" +
			"(id,c_date,login,share_betting1,\n" + 
			"recharge10,recharge20,recharge50,recharge100,recharge200,recharge500,\n" + 
			"betting10,betting30,betting50,betting100,betting300,betting500,betting1000,\n" + 
			"betting_times1,betting_times5,betting_times10)\n" + 
			"values\n" + 
			"(SEQ_TASK_TASK_MAIN_DATA.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		DayTaskInfoData d = (DayTaskInfoData) data;
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getLogin(),d.getShareBetting1()
				,d.getRecharge10(),d.getRecharge20(),d.getRecharge50(),d.getRecharge100(),d.getRecharge200(),d.getRecharge500()
				,d.getBetting10(),d.getBetting30(),d.getBetting50(),d.getBetting100(),d.getBetting300(),d.getBetting500(),d.getBetting1000()
				,d.getBettingTimes1(),d.getBettingTimes5(),d.getBettingTimes10());
	}

	@Override
	public List<DataDetail> loadFromLoacl(Date start, Date end) {
		String sql = "select c_date currentDate,login login,share_betting1 shareBetting1,\n" +
			"      recharge10,recharge20,recharge50,recharge100,recharge200,recharge500,\n" + 
			"      betting10,betting30,betting50,betting100,betting300,betting500,betting1000,\n" + 
			"      betting_times1 bettingTimes1,betting_times5 bettingTimes5,betting_times10 bettingTimes10\n" + 
			"      from TASK_DAY_TASK_INFO\n" + 
			"      where c_date>=? and c_date<=?  order by c_date desc";
		List query = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(DayTaskInfoData.class),start,end);
		return query;
	}

	@Override
	public void deleteFromLocal(Date start, Date end) {
		String sql = "delete from TASK_DAY_TASK_INFO where c_date>=? and c_date<=?";
		jdbcTemplate.update(sql,start,end);
	}

}

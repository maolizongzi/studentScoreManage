package com.cndym.dao.task.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.cndym.dao.task.IDataTableDetailDao;
import com.cndym.entity.data.task.DataDetail;
import com.cndym.entity.data.task.PropAndFragMainData;

@Repository
public class PropAndFragMainDataDetailDao implements IDataTableDetailDao {
	
	@Resource
	private JdbcTemplate taskJdbcTemplate;
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public DataDetail loadFromSource(Date start, Date end) {
		PropAndFragMainData data = new PropAndFragMainData();
		data.setCurrentDate(start);
		String newPropPeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM (SELECT 1 FROM t_shop_user_prop_log WHERE get_time >=? AND get_time<=? AND is_invented=0 AND is_frag = 0 GROUP BY user_code) a";
		String newPropPropCountSql = "SELECT COALESCE(\"sum\"(num),0) FROM t_shop_user_prop_log WHERE get_time >=? AND get_time<=? AND is_invented=0 AND is_frag = 0";
		data.setNewPropPeopleCount(taskJdbcTemplate.queryForObject(newPropPeopleCountSql, Integer.class,start,end));
		data.setNewPropPropCount(taskJdbcTemplate.queryForObject(newPropPropCountSql, Integer.class,start,end));
		data.invokeNewPropAverageCount();
		
		String usedPropPeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM (SELECT 1 FROM t_shop_user_prop up LEFT JOIN t_shop_prop p ON up.prop_id = p.\"id\" WHERE up.use_time >=? AND up.use_time <=? AND (up.\"state\"=1 OR up.\"state\" = 2) AND p.is_frag = 0 GROUP BY user_code) a";
		String usedPropPropCountSql = "SELECT COALESCE(\"sum\"(up.num),0) FROM t_shop_user_prop up LEFT JOIN t_shop_prop p ON up.prop_id = p.\"id\" WHERE up.use_time >=? AND up.use_time <=? AND (up.\"state\"=1 OR up.\"state\" = 2) AND p.is_frag = 0";
		data.setUsedPropPeopleCount(taskJdbcTemplate.queryForObject(usedPropPeopleCountSql, Integer.class,start,end));
		data.setUsedPropPropCount(taskJdbcTemplate.queryForObject(usedPropPropCountSql, Integer.class,start,end));
		data.invokeUsedPropAverageCount();
		
		String fanliPeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM (SELECT 1 FROM t_shop_user_prop up LEFT JOIN t_shop_prop p ON up.prop_id = p.\"id\" WHERE up.use_time >=? AND up.use_time <=? AND (up.\"state\"=1 OR up.\"state\" = 2) AND p.is_frag = 0 AND p.\"type\" = 'dazhe' GROUP BY user_code) a";
		String fanliPropCountSql = "SELECT COALESCE(\"sum\"(up.num),0) FROM t_shop_user_prop up LEFT JOIN t_shop_prop p ON up.prop_id = p.\"id\" WHERE up.use_time >=? AND up.use_time <=? AND (up.\"state\"=1 OR up.\"state\" = 2) AND p.is_frag = 0 AND p.\"type\" = 'dazhe'";
		data.setFanliPeopleCount(taskJdbcTemplate.queryForObject(fanliPeopleCountSql, Integer.class,start,end));
		data.setFanliPropCount(taskJdbcTemplate.queryForObject(fanliPropCountSql, Integer.class,start,end));
		data.invokeFanliAverageCount();
		
		String baodiPeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM (SELECT 1 FROM t_shop_user_prop up LEFT JOIN t_shop_prop p ON up.prop_id = p.\"id\" WHERE up.use_time >=? AND up.use_time <=? AND (up.\"state\"=1 OR up.\"state\" = 2) AND p.is_frag = 0 AND p.\"type\" = 'fushen' GROUP BY user_code) a";
		String baodiPropCountSql = "SELECT COALESCE(\"sum\"(up.num),0) FROM t_shop_user_prop up LEFT JOIN t_shop_prop p ON up.prop_id = p.\"id\" WHERE up.use_time >=? AND up.use_time <=? AND (up.\"state\"=1 OR up.\"state\" = 2) AND p.is_frag = 0 AND p.\"type\" = 'fushen'";
		data.setBaodiPeopleCount(taskJdbcTemplate.queryForObject(baodiPeopleCountSql, Integer.class,start,end));
		data.setBaodiPropCount(taskJdbcTemplate.queryForObject(baodiPropCountSql, Integer.class,start,end));
		data.invokeBaodiPropCount();
		
		String jiajiangPeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM (SELECT 1 FROM t_shop_user_prop up LEFT JOIN t_shop_prop p ON up.prop_id = p.\"id\" WHERE up.use_time >=? AND up.use_time <=? AND (up.\"state\"=1 OR up.\"state\" = 2) AND p.is_frag = 0 AND p.\"type\" = 'caishen' GROUP BY user_code) a";
		String jiajiangPropCountSql = "SELECT COALESCE(\"sum\"(up.num),0) FROM t_shop_user_prop up LEFT JOIN t_shop_prop p ON up.prop_id = p.\"id\" WHERE up.use_time >=? AND up.use_time <=? AND (up.\"state\"=1 OR up.\"state\" = 2) AND p.is_frag = 0 AND p.\"type\" = 'caishen'";
		data.setJiajiangPeopleCount(taskJdbcTemplate.queryForObject(jiajiangPeopleCountSql, Integer.class,start,end));
		data.setJiajiangPropCount(taskJdbcTemplate.queryForObject(jiajiangPropCountSql, Integer.class,start,end));
		data.invokeJiajiangPropCount();
		
		String huodongPeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM (SELECT 1 FROM t_shop_user_prop up LEFT JOIN t_shop_prop p ON up.prop_id = p.\"id\" WHERE up.use_time >=? AND up.use_time <=? AND (up.\"state\"=1 OR up.\"state\" = 2) AND p.is_frag = 0 AND p.\"type\" = 'tianshi' GROUP BY user_code) a";
		String huodongPropCountSql = "SELECT COALESCE(\"sum\"(up.num),0) FROM t_shop_user_prop up LEFT JOIN t_shop_prop p ON up.prop_id = p.\"id\" WHERE up.use_time >=? AND up.use_time <=? AND (up.\"state\"=1 OR up.\"state\" = 2) AND p.is_frag = 0 AND p.\"type\" = 'tianshi'";
		data.setHuodongPeopleCount(taskJdbcTemplate.queryForObject(huodongPeopleCountSql, Integer.class,start,end));
		data.setHuodongPropCount(taskJdbcTemplate.queryForObject(huodongPropCountSql, Integer.class,start,end));
		data.invokeHuodongPropCount();
		
		String reclaimSql = "SELECT COALESCE(\"count\"(DISTINCT user_code),0) people,COALESCE(\"sum\"(num),0) prop FROM t_shop_user_prop_reclaim_log WHERE is_frag = 0 AND reclaim_time >= ? AND reclaim_time <= ?";
		List<Map<String, Object>> queryForList = taskJdbcTemplate.queryForList(reclaimSql,start,end);
		data.setReclaimPropPropCount(Integer.parseInt(queryForList.get(0).get("prop").toString()));
		data.setReclaimPropPeopleCount(Integer.parseInt(queryForList.get(0).get("people").toString()));
		data.invokeReclaimPropAverageCount();
		
		String guoqiDayPropCountSql = "SELECT COALESCE(\"count\"(*),0) FROM t_shop_user_prop WHERE  end_time >=? AND end_time <=? AND \"state\" = 3 ";
		String guoqiTotalPropCountSql = "SELECT COALESCE(\"count\"(*),0) FROM t_shop_user_prop WHERE  \"state\" = 3 ";
		data.setGuoqiDayPropCount(taskJdbcTemplate.queryForObject(guoqiDayPropCountSql, Integer.class,start,end));
		data.setGuoqiTotalPropCount(taskJdbcTemplate.queryForObject(guoqiTotalPropCountSql, Integer.class));
		
		
		String shengyuTotalPropCountSql = "SELECT COALESCE(count(*),0) FROM t_shop_user_prop up LEFT JOIN t_shop_prop p ON up.prop_id = p.id WHERE up.state=0 AND p.is_frag = 0";
		data.setShengyuTotalPropCount(taskJdbcTemplate.queryForObject(shengyuTotalPropCountSql, Integer.class));
		data.invokeShengyuDayPropCount();
		
		String newFragPeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM (SELECT 1 FROM t_shop_user_prop_log WHERE get_time >=? AND get_time<=? AND is_frag = 1 GROUP BY user_code) a";
		String newFragFragCountSql = "SELECT COALESCE(\"sum\"(num),0) FROM t_shop_user_prop_log WHERE get_time >=? AND get_time<=? AND is_frag = 1 ";
		data.setNewFragPeopleCount(taskJdbcTemplate.queryForObject(newFragPeopleCountSql, Integer.class,start,end));
		data.setNewFragFragCount(taskJdbcTemplate.queryForObject(newFragFragCountSql, Integer.class,start,end));
		data.invokeNewFragAverageCount();
		
		String reclaimFragSql = "SELECT COALESCE(\"count\"(DISTINCT user_code),0) people,COALESCE(\"sum\"(num),0) prop FROM t_shop_user_prop_reclaim_log WHERE is_frag = 1 AND reclaim_time >= ? AND reclaim_time <= ?";
		List<Map<String, Object>> reclaimFragList = taskJdbcTemplate.queryForList(reclaimFragSql,start,end);
		data.setReclaimFragCount(Integer.parseInt(reclaimFragList.get(0).get("prop").toString()));
		data.setReclaimFragPeopleCount(Integer.parseInt(reclaimFragList.get(0).get("people").toString()));
		data.invokeReclaimAverageFragCount();
		
		String shengyuFragCountSql = "SELECT COALESCE(\"sum\"(up.num),0) FROM t_shop_user_prop up LEFT JOIN t_shop_prop p ON up.prop_id = p.\"id\" WHERE p.is_frag = 1";
		data.setShengyuFragCount(taskJdbcTemplate.queryForObject(shengyuFragCountSql, Integer.class));
		return data;
	}

	@Override
	public void saveToLocal(DataDetail data) {
		String sql = "insert into task_prop_and_frag_main_data\n" +
			" (id,c_date,np_people_count,np_prop_count,np_average_count,up_people_count,up_prop_count,up_average_count,\n" +
			" fl_people_count,fl_prop_count,fl_average_count,bd_people_count,bd_prop_count,bd_average_count,jj_people_count,\n" + 
			" jj_prop_count,jj_average_count,hd_people_count,hd_prop_count,hd_average_count,gq_day_prop_count,gq_total_prop_count,\n" + 
			" sy_day_prop_count,sy_total_prop_count,nf_people_count,nf_frag_count,nf_average_count,sy_frag_count,"
			+ "rp_people_count,rp_prop_count,rp_average_count,rf_people_count,rf_prop_count,rf_average_count)\n" + 
			"values\n" + 
			" (SEQ_TASK_PAF_MAIN_DATA.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PropAndFragMainData d = (PropAndFragMainData) data;
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getNewPropPeopleCount(),d.getNewPropPropCount(),d.getNewPropAverageCount(),d.getUsedPropPeopleCount(),d.getUsedPropPropCount(),d.getUsedPropAverageCount(),
				d.getFanliPeopleCount(),d.getFanliPropCount(),d.getFanliAverageCount(),d.getBaodiPeopleCount(),d.getBaodiPropCount(),d.getBaodiAverageCount(),d.getJiajiangPeopleCount(),
				d.getJiajiangPropCount(),d.getJiajiangAverageCount(),d.getHuodongPeopleCount(),d.getHuodongPropCount(),d.getHuodongAverageCount(),d.getGuoqiDayPropCount(),d.getGuoqiTotalPropCount(),
				d.getShengyuDayPropCount(),d.getShengyuTotalPropCount(),d.getNewFragPeopleCount(),d.getNewFragFragCount(),d.getNewFragAverageCount(),d.getShengyuFragCount(),
				d.getReclaimPropPeopleCount(),d.getReclaimPropPropCount(),d.getReclaimPropAverageCount(),d.getReclaimFragPeopleCount(),d.getReclaimFragCount(),d.getReclaimAverageFragCount());
	}

	@Override
	public List<DataDetail> loadFromLoacl(Date start, Date end) {
		String sql = "select id,c_date currentDate,np_people_count newPropPeopleCount,np_prop_count newPropPropCount,np_average_count newPropAverageCount,up_people_count usedPropPeopleCount,up_prop_count usedPropPropCount,up_average_count usedPropAverageCount,\n" + 
			" fl_people_count fanliPeopleCount,fl_prop_count fanliPropCount,fl_average_count fanliAverageCount,bd_people_count baodiPeopleCount,bd_prop_count baodiPropCount,bd_average_count baodiAverageCount,jj_people_count jiajiangPeopleCount,\n" + 
			" jj_prop_count jiajiangPropCount,jj_average_count jiajiangAverageCount,hd_people_count huodongPeopleCount,hd_prop_count huodongPropCount,hd_average_count huodongAverageCount,gq_day_prop_count guoqiDayPropCount,gq_total_prop_count guoqiTotalPropCount,\n" + 
			" sy_day_prop_count shengyuDayPropCount,sy_total_prop_count shengyuTotalPropCount,nf_people_count newFragPeopleCount,nf_frag_count newFragFragCount,nf_average_count newFragAverageCount,sy_frag_count shengyuFragCount, " +
			" rp_people_count reclaimPropPeopleCount,rp_prop_count reclaimPropPropCount,rp_average_count reclaimPropAverageCount,rf_people_count reclaimFragPeopleCount,rf_prop_count reclaimFragCount,rf_average_count reclaimAverageFragCount "+
			"from task_prop_and_frag_main_data\n" + 
			"where c_date>=? and c_date<=?  order by c_date desc";
		List res = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(PropAndFragMainData.class),start,end);
		return res;
	}

	@Override
	public void deleteFromLocal(Date start, Date end) {
		String sql = "delete from task_prop_and_frag_main_data where c_date>=? and c_date<=?";
		jdbcTemplate.update(sql,start,end);
	}

}

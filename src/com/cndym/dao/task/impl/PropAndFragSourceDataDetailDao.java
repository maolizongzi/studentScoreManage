package com.cndym.dao.task.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.cndym.dao.task.IDataTableDetailDao;
import com.cndym.entity.data.task.DataDetail;
import com.cndym.entity.data.task.PropAndFragSourceData;

@Repository
public class PropAndFragSourceDataDetailDao implements IDataTableDetailDao{

	@Resource
	private JdbcTemplate taskJdbcTemplate;
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public DataDetail loadFromSource(Date start, Date end) {
		
		PropAndFragSourceData d = new PropAndFragSourceData();
		d.setCurrentDate(start);
		String buyPropPeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM (SELECT 1 FROM t_shop_user_prop_log l WHERE l.get_way = 1 AND l.get_time >=? AND l.get_time<=? AND is_invented=0 AND is_frag = 0 GROUP BY user_code) a";
		String buyPropPropCountSql = "SELECT COALESCE(\"sum\"(num),0) FROM t_shop_user_prop_log l WHERE l.get_way = 1 AND l.get_time >=? AND is_invented=0 AND l.get_time<=? AND is_frag = 0";
		d.setBuyPropPeopleCount(taskJdbcTemplate.queryForObject(buyPropPeopleCountSql, Integer.class, start,end));
		d.setBuyPropPropCount(taskJdbcTemplate.queryForObject(buyPropPropCountSql, Integer.class, start,end));
		d.invokeBuyPropAverageCount();
		
		String drawPropPeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM (SELECT 1 FROM t_shop_user_prop_log l WHERE l.get_way = 2 AND is_invented=0 AND l.get_time >=? AND l.get_time<=? AND is_frag = 0 GROUP BY user_code) a";
		String drawPropCountSql = "SELECT COALESCE(\"sum\"(num),0) FROM t_shop_user_prop_log l WHERE l.get_way = 2 AND is_invented=0 AND l.get_time >=? AND l.get_time<=? AND is_frag = 0";
		d.setDrawPropPeopleCount(taskJdbcTemplate.queryForObject(drawPropPeopleCountSql, Integer.class, start,end));
		d.setDrawPropCount(taskJdbcTemplate.queryForObject(drawPropCountSql, Integer.class, start,end));
		d.invokeDrawPropAverageCount();
		
		String drawFragPeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM (SELECT 1 FROM t_shop_user_prop_log l WHERE l.get_way = 2 AND is_invented=0 AND l.get_time >=? AND l.get_time<=? AND is_frag = 1 GROUP BY user_code) a";
		String drawFragCountSql = "SELECT COALESCE(\"sum\"(num),0) FROM t_shop_user_prop_log l WHERE l.get_way = 2 AND is_invented=0 AND l.get_time >=? AND l.get_time<=? AND is_frag = 1";
		d.setDrawFragPeopleCount(taskJdbcTemplate.queryForObject(drawFragPeopleCountSql, Integer.class, start,end));
		d.setDrawFragCount(taskJdbcTemplate.queryForObject(drawFragCountSql, Integer.class, start,end));
		d.invokeDrawFragAverageCount();
		
		String hechengPeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM (SELECT 1 FROM t_shop_user_prop_log l WHERE l.get_way = 3 AND is_invented=0 AND l.get_time >=? AND l.get_time<=? AND is_frag = 0 GROUP BY user_code) a";
		String hechengPropCountSql = "SELECT COALESCE(\"sum\"(num),0) FROM t_shop_user_prop_log l WHERE l.get_way = 3 AND is_invented=0 AND l.get_time >=? AND l.get_time<=? AND is_frag = 0";
		d.setHechengPeopleCount(taskJdbcTemplate.queryForObject(hechengPeopleCountSql, Integer.class, start,end));
		d.setHechengPropCount(taskJdbcTemplate.queryForObject(hechengPropCountSql, Integer.class, start,end));
		d.invokeHechengAverageCount();
		
		String jiangbutingPeopleContSql = "SELECT COALESCE(\"count\"(*),0) FROM (SELECT 1 FROM t_shop_user_prop_log l WHERE l.get_way = 5 AND is_invented=0 AND get_way_back = 'jiangbuting' AND l.get_time >=? AND l.get_time<=? AND is_frag = 0 GROUP BY user_code) a";
		String jiangbutingPropCountSql = "SELECT COALESCE(\"sum\"(num),0) FROM t_shop_user_prop_log l WHERE l.get_way = 5 AND is_invented=0 AND get_way_back = 'jiangbuting' AND l.get_time >=? AND l.get_time<=? AND is_frag = 0";
		d.setJiangbutingPeopleCont(taskJdbcTemplate.queryForObject(jiangbutingPeopleContSql, Integer.class, start,end));
		d.setJiangbutingPropCount(taskJdbcTemplate.queryForObject(jiangbutingPropCountSql, Integer.class, start,end));
		d.invokeJiangbutingAverageCount();
		return d;
	}

	@Override
	public void saveToLocal(DataDetail data) {
		String sql = "insert into task_prop_and_frag_source_data\n" +
			"       (id,c_date,\n" + 
			"       buy_prop_people_count,buy_prop_prop_count,buy_prop_average_count,\n" + 
			"       draw_prop_people_count,draw_prop_count,draw_prop_average_count,\n" + 
			"       draw_frag_people_count,draw_frag_count,draw_frag_average_count,\n" + 
			"       hecheng_people_count,hecheng_prop_count,hecheng_average_count,\n" + 
			"       jiangbuting_people_cont,jiangbuting_prop_count,jiangbuting_average_count)\n" + 
			"values\n" + 
			"       (SEQ_TASK_PAF_SOURCE_DATA.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PropAndFragSourceData d = (PropAndFragSourceData) data;
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getBuyPropPeopleCount(),d.getBuyPropPropCount(),d.getBuyPropAverageCount()
				,d.getDrawPropPeopleCount(),d.getDrawPropCount(),d.getDrawPropAverageCount()
				,d.getDrawFragPeopleCount(),d.getDrawFragCount(),d.getDrawFragAverageCount()
				,d.getHechengPeopleCount(),d.getHechengPropCount(),d.getHechengAverageCount()
				,d.getJiangbutingPeopleCont(),d.getJiangbutingPropCount(),d.getJiangbutingAverageCount());

	}

	@Override
	public List<DataDetail> loadFromLoacl(Date start, Date end) {
		String sql = "select c_date currentDate,\n" +
			"       buy_prop_people_count buyPropPeopleCount,buy_prop_prop_count buyPropPropCount,buy_prop_average_count buyPropAverageCount,\n" + 
			"       draw_prop_people_count drawPropPeopleCount,draw_prop_count drawPropCount,draw_prop_average_count drawPropAverageCount,\n" + 
			"       draw_frag_people_count drawFragPeopleCount,draw_frag_count drawFragCount,draw_frag_average_count drawFragAverageCount,\n" + 
			"       hecheng_people_count hechengPeopleCount,hecheng_prop_count hechengPropCount,hecheng_average_count hechengAverageCount,\n" + 
			"       jiangbuting_people_cont jiangbutingPeopleCont,jiangbuting_prop_count jiangbutingPropCount,jiangbuting_average_count jiangbutingAverageCount\n" + 
			"from task_prop_and_frag_source_data\n" + 
			"where c_date>=? and c_date<=?  order by c_date desc";
		List query = jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PropAndFragSourceData.class),start,end);
		return query;
	}

	@Override
	public void deleteFromLocal(Date start, Date end) {
		String sql = "delete from task_prop_and_frag_source_data where c_date>=? and c_date<=?";
		jdbcTemplate.update(sql,start,end);
	}

}

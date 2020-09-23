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
import com.cndym.entity.data.task.TaskMainData;

@Repository
public class TaskMainDataDetailDao implements IDataTableDetailDao {

	@Resource
	private JdbcTemplate taskJdbcTemplate;
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public DataDetail loadFromSource(Date start, Date end) {
		TaskMainData d = new TaskMainData();
		d.setCurrentDate(start);
		String taskPeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM (SELECT 1 FROM t_user_task_rec t LEFT JOIN t_task_conf c ON t.task_id = c.\"id\" WHERE t.finish_date = ? GROUP BY t.user_code) a";
		String taskTaskCountSql = "SELECT COALESCE(\"count\"(*),0) FROM t_user_task_rec t LEFT JOIN t_task_conf c ON t.task_id = c.\"id\" WHERE t.finish_date = ?";
//		String taskScoreSql = "SELECT COALESCE(\"sum\"(c.score),0) FROM t_user_task_rec t LEFT JOIN t_task_conf c ON t.task_id = c.\"id\" WHERE t.is_finish = '2' AND t.finish_date = ?";
		String taskRecoverPeopleCountSql = "SELECT COALESCE(count(DISTINCT user_code),0) peopleCount,COALESCE(count(1),0) taskCount,COALESCE(sum(l.score),0) scoreCount FROM (SELECT * FROM t_user_score_log WHERE datetime >= ? AND datetime <= ?) l LEFT JOIN t_task_conf t ON t.task_name = l.remark AND now()<= down_time WHERE t.task_type='0' or t.task_type='1'";
		d.setTaskPeopleCount(taskJdbcTemplate.queryForObject(taskPeopleCountSql, Integer.class,start));
		d.setTaskTaskCount(taskJdbcTemplate.queryForObject(taskTaskCountSql, Integer.class,start));
//		d.setTaskScore(taskJdbcTemplate.queryForObject(taskScoreSql, Integer.class,start));
//		d.setTaskRecoverPeopleCount(taskJdbcTemplate.queryForObject(taskRecoverPeopleCountSql, Integer.class,start,end));
		List<Map<String, Object>> queryForList = taskJdbcTemplate.queryForList(taskRecoverPeopleCountSql,start,end);
		d.setTaskScore(Integer.parseInt(queryForList.get(0).get("scoreCount").toString()));
		d.setTaskRecoverPeopleCount(Integer.parseInt(queryForList.get(0).get("peopleCount").toString()));
		d.setTaskRecoverTaskCount(Integer.parseInt(queryForList.get(0).get("taskCount").toString()));
		d.invokeTaskAverageTaskCount();
		d.invokeTaskAverageScore();
		
		String dayTaskPeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM (SELECT 1 FROM t_user_task_rec t LEFT JOIN t_task_conf c ON t.task_id = c.\"id\" WHERE c.task_type='0' AND t.finish_date = ? GROUP BY t.user_code) a";
		String dayTaskTaskCountSql = "SELECT COALESCE(\"count\"(*),0) FROM t_user_task_rec t LEFT JOIN t_task_conf c ON t.task_id = c.\"id\" WHERE c.task_type='0' AND t.finish_date = ?";
//		String dayTaskScoreSql = "SELECT COALESCE(\"sum\"(c.score),0) FROM t_user_task_rec t LEFT JOIN t_task_conf c ON t.task_id = c.\"id\" WHERE t.is_finish = '2' AND c.task_type='0' AND t.finish_date = ?";
		String dayTaskRecoverPeopleCountSql = "SELECT COALESCE(count(DISTINCT user_code),0) peopleCount,COALESCE(count(1),0) taskCount,COALESCE(sum(l.score),0) scoreCount FROM (SELECT * FROM t_user_score_log WHERE datetime >= ? AND datetime <= ?) l LEFT JOIN t_task_conf t ON t.task_name = l.remark WHERE t.task_type = '0'";
		d.setDayTaskPeopleCount(taskJdbcTemplate.queryForObject(dayTaskPeopleCountSql, Integer.class,start));
		d.setDayTaskTaskCount(taskJdbcTemplate.queryForObject(dayTaskTaskCountSql, Integer.class,start));
//		d.setDayTaskScore(taskJdbcTemplate.queryForObject(dayTaskScoreSql, Integer.class,start));
		queryForList = taskJdbcTemplate.queryForList(dayTaskRecoverPeopleCountSql,start,end);
		d.setDayTaskScore(Integer.parseInt(queryForList.get(0).get("scoreCount").toString()));
		d.setDayTaskRecoverPeopleCount(Integer.parseInt(queryForList.get(0).get("peopleCount").toString()));
		d.setDayTaskRecoverTaskCount(Integer.parseInt(queryForList.get(0).get("taskCount").toString()));
		d.invokeDayTaskAverageTaskCount();
		d.invokeDayTaskAverageScore();
		
		String achieveTaskPeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM (SELECT 1 FROM t_user_task_rec t LEFT JOIN t_task_conf c ON t.task_id = c.\"id\" WHERE c.task_type='1' AND t.finish_date = ? GROUP BY t.user_code) a";
		String achieveTaskTaskCountSql = "SELECT COALESCE(\"count\"(*),0) FROM t_user_task_rec t LEFT JOIN t_task_conf c ON t.task_id = c.\"id\" WHERE c.task_type='1' AND t.finish_date = ?";
//		String achieveTaskScoreSql = "SELECT COALESCE(\"sum\"(c.score),0) FROM t_user_task_rec t LEFT JOIN t_task_conf c ON t.task_id = c.\"id\" WHERE t.is_finish = '2' AND c.task_type='1' AND t.finish_date = ?";
		String achieveTaskRecoverPeopleCountSql = "SELECT COALESCE(count(DISTINCT user_code),0) peopleCount,COALESCE(count(1),0) taskCount,COALESCE(sum(l.score),0) scoreCount FROM (SELECT * FROM t_user_score_log WHERE datetime >= ? AND datetime <= ?) l LEFT JOIN t_task_conf t ON t.task_name = l.remark WHERE t.task_type = '1'";
		d.setAchieveTaskPeopleCount(taskJdbcTemplate.queryForObject(achieveTaskPeopleCountSql, Integer.class,start));
		d.setAchieveTaskTaskCount(taskJdbcTemplate.queryForObject(achieveTaskTaskCountSql, Integer.class,start));
//		d.setAchieveTaskScore(taskJdbcTemplate.queryForObject(achieveTaskScoreSql, Integer.class,start));
//		d.setAchieveTaskRecoverPeopleCount(taskJdbcTemplate.queryForObject(achieveTaskRecoverPeopleCountSql, Integer.class,start,end));
		queryForList = taskJdbcTemplate.queryForList(achieveTaskRecoverPeopleCountSql,start,end);
		d.setAchieveTaskScore(Integer.parseInt(queryForList.get(0).get("scoreCount").toString()));
		d.setAchieveTaskRecoverTaskCount(Integer.parseInt(queryForList.get(0).get("taskCount").toString()));
		d.setAchieveTaskRecoverPeopleCount(Integer.parseInt(queryForList.get(0).get("peopleCount").toString()));
		
		String catchTitlePeopleCount = "SELECT \"count\"(*) FROM (SELECT 1 FROM t_user_title t WHERE t.create_time = ? GROUP BY user_code) a";
		String catchTitleTitileCount = "SELECT \"count\"(*) FROM t_user_title t WHERE t.create_time = ?";
		d.setCatchTitlePeopleCount(taskJdbcTemplate.queryForObject(catchTitlePeopleCount, Integer.class,start));
		d.setCatchTitleTitleCount(taskJdbcTemplate.queryForObject(catchTitleTitileCount, Integer.class,start));
		d.invokeAchieveTaskAverageScore();
		d.invokeAchieveTaskAverageTaskCount();
		return d;
	}

	@Override
	public void saveToLocal(DataDetail data) {
		String sql = "insert into TASK_TASK_MAIN_DATA\n" +
			"(id,c_date,\n" + 
			"task_people_count,task_task_count,task_average_task_count,task_score,task_recover_people_count,task_average_score,task_recover_task_count,\n" + 
			"dt_people_count,dt_task_count,dt_average_task_count,dt_score,dt_recover_people_count,dt_average_score,dt_recover_task_count,\n" + 
			"at_people_count,at_task_count,at_average_task_count,at_score,at_recover_people_count,at_average_score,at_recover_task_count,catch_title_people_count,catch_title_title_count)\n" + 
			"values\n" + 
			"(SEQ_TASK_TASK_MAIN_DATA.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		TaskMainData d = (TaskMainData) data;
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getTaskPeopleCount(),d.getTaskTaskCount(),d.getTaskAverageTaskCount(),d.getTaskScore(),d.getTaskRecoverPeopleCount(),d.getTaskAverageScore(),d.getTaskRecoverTaskCount(),
				d.getDayTaskPeopleCount(),d.getDayTaskTaskCount(),d.getDayTaskAverageTaskCount(),d.getDayTaskScore(),d.getDayTaskRecoverPeopleCount(),d.getDayTaskAverageScore(),d.getDayTaskRecoverTaskCount(),
				d.getAchieveTaskPeopleCount(),d.getAchieveTaskTaskCount(),d.getAchieveTaskAverageTaskCount(),d.getAchieveTaskScore(),d.getAchieveTaskRecoverPeopleCount(),d.getAchieveTaskAverageScore(),d.getAchieveTaskRecoverTaskCount(),
				d.getCatchTitlePeopleCount(),d.getCatchTitleTitleCount());
	}

	@Override
	public List<DataDetail> loadFromLoacl(Date start, Date end) {
		String sql = "select c_date currentDate,\n" +
			"       task_people_count taskPeopleCount,task_task_count taskTaskCount,task_average_task_count taskAverageTaskCount,task_recover_task_count taskRecoverTaskCount,\n" + 
			"       task_score taskScore,task_recover_people_count taskRecoverPeopleCount,task_average_score taskAverageScore,\n" + 
			"       dt_people_count dayTaskPeopleCount,dt_task_count dayTaskTaskCount,dt_average_task_count dayTaskAverageTaskCount,\n" + 
			"       dt_score dayTaskScore,dt_recover_people_count dayTaskRecoverPeopleCount,dt_average_score dayTaskAverageScore,dt_recover_task_count dayTaskRecoverTaskCount,\n" + 
			"       at_people_count achieveTaskPeopleCount,at_task_count achieveTaskTaskCount,at_average_task_count achieveTaskAverageTaskCount,\n" + 
			"       at_score achieveTaskScore,at_recover_people_count achieveTaskRecoverPeopleCount,at_average_score achieveTaskAverageScore,at_recover_task_count achieveTaskRecoverTaskCount,catch_title_people_count catchTitlePeopleCount,catch_title_title_count catchTitleTitleCount\n" + 
			"from TASK_TASK_MAIN_DATA\n" + 
			"where c_date>=? and c_date<=?  order by c_date desc";
		List query = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(TaskMainData.class),start,end);
		return query;
	}

	@Override
	public void deleteFromLocal(Date start, Date end) {
		String sql = "delete from TASK_TASK_MAIN_DATA where c_date>=? and c_date<=?";
		jdbcTemplate.update(sql,start,end);
	}

}

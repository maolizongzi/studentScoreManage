package com.cndym.util.export;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class ListToString {
	private static Logger logger = Logger.getLogger(ListToString.class);
	
	public static String list2String(List list,String format,String... params){
		String res = "";
		if(list==null || list.size()<1){
			return res;
		}
		for(Object obj : list){
			try{
				res += object2String(obj, format, params);
			}catch (Exception e) {
				e.printStackTrace();
				logger.debug("list2String 25 line runtime:",e);
			}
		}
		return res;
	}
	
	public static String object2String(Object obj,String format,String... params) throws IllegalArgumentException, IllegalAccessException{
		String res = "";
		if(obj == null){
			return res;
		}
		List<Object> values = new ArrayList<Object>();
		if(obj instanceof Map){
			Map map = (Map) obj;
			for(String param:params){
//				if(map.get(param) instanceof BigDecimal){
//					values.add(map.get(param).toString());
//				}else{
					values.add(map.get(param));
//				}
				
			}
			res = String.format(format, values.toArray());
			return res;
		}
		for(String param:params){
			for(Field f : obj.getClass().getDeclaredFields()){
			
				if(f.getName().equals(param)){
					f.setAccessible(true);
					Object value = f.get(obj);
					values.add(value);
					break;
				}
			}
		}
		res = String.format(format, values.toArray());
		return res;
	}
}

class EntryA{
	private String a1 = "1111";
	private Integer a2 = 2;
	private String a3 = "3333";
	private Date a4 = null;
	private Map a5 = new HashMap();
	
	public EntryA() {
		a5.put("aa", "123");
	}
}

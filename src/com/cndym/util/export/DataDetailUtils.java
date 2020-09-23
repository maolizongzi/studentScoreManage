package com.cndym.util.export;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cndym.entity.data.task.DataDetail;
import com.cndym.entity.data.task.DataTable;
import com.cndym.entity.data.task.annotation.ExportDataConfig;
import com.cndym.entity.data.task.annotation.Format;

public class DataDetailUtils {
	
	
	public static List<Map<String,Object>> formatDataToMaps(DataTable dataTable) throws IllegalArgumentException, IllegalAccessException{
		List<DataDetail> data = dataTable.getData();
		Iterator<DataDetail> iterator = data.iterator();
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		while(iterator.hasNext()){
			Map<String,Object> map = new HashMap<String, Object>();
			DataDetail next = iterator.next();
			map.putAll(detailToMap(next));
			result.add(map);
		}
		return result;
	}
	
	public static List<Map<String,Object>> formatDataToMaps(DataTable dataTable,String fileType) throws IllegalArgumentException, IllegalAccessException{
		List<DataDetail> data = dataTable.getData();
		Iterator<DataDetail> iterator = data.iterator();
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		while(iterator.hasNext()){
			Map<String,Object> map = new HashMap<String, Object>();
			DataDetail next = iterator.next();
			map.putAll(detailToMapWithFileType(next,fileType));
			result.add(map);
		}
		return result;
	}
	
	private static Map<String,Object> detailToMap(DataDetail detail) throws IllegalArgumentException, IllegalAccessException{
		Class<? extends DataDetail> clazz = detail.getClass();
		Map<String,Object> result = new HashMap<String, Object>();
		Field[] declaredFields = clazz.getDeclaredFields();
		for(Field f : declaredFields){
			if(!f.isAccessible()){
				f.setAccessible(true);
			}
			Object value = f.get(detail);
			String name = f.getName();
			result.put(name, value);
		}
		return result;
	}
	
	private static Map<String,Object> detailToMapWithFileType(DataDetail detail,String fileType) throws IllegalArgumentException, IllegalAccessException{
		Class<? extends DataDetail> clazz = detail.getClass();
		Map<String,Object> result = new HashMap<String, Object>();
		Field[] declaredFields = clazz.getDeclaredFields();
		for(Field f : declaredFields){
			if(!f.isAccessible()){
				f.setAccessible(true);
			}
			Object value = formatField(detail,f,fileType);
			String name = f.getName();
			result.put(name, value);
		}
		return result;
	}
	
	private static Object formatField(Object target,Field field,String fileType) throws IllegalArgumentException, IllegalAccessException{
		if(!field.isAnnotationPresent(ExportDataConfig.class)){
			return field.get(target);
		}
		ExportDataConfig annotation = field.getAnnotation(ExportDataConfig.class);
		Format[] formats = annotation.formats();
		if(formats==null || formats.length==0){
			return field.get(target);
		}
		Map<String,Format> formatMap = new HashMap<String, Format>();
		for(Format format : formats){
			formatMap.put(format.fileType(), format);
		}
		Format format = formatMap.get(fileType);
		if(format==null){
			format = formatMap.get("");
		}
		if(format == null){
			return field.get(target);
		}
		Class<?> clazz = format.formatType();
		Object formatObject = formatObject(clazz,field.get(target),format.value());
		return formatObject;
	}
	
	
	private static Object formatObject(Class toType,Object value,String format){
		//TODO 类型很少  待完善
		if("default".equals(format)){
			return value;
		}
		if(toType.equals(String.class)){
			if(value instanceof Date){
				String formatDate2Str = Utils.formatDate2Str((Date)value, format);
				return formatDate2Str;
			}
			if(value instanceof Integer || value instanceof Double || value instanceof Long || value instanceof Float){
				String format2 = String.format(format, value);
				return format2;
			}
		}
		return value;
	}
	
	public static String formatContent(String source,Object obj){
		Pattern p = Pattern.compile("\\$\\{.*?\\}");
		Matcher m = p.matcher(source);
		StringBuilder builder = new StringBuilder();
		int lastIndex = 0;
		while(m.find()){
			String matchString = m.group();
			builder.append(source.substring(lastIndex,m.start()));
			String formatedMatchString = paramParser(matchString, obj);
			builder.append(formatedMatchString);
			lastIndex = m.end();
		}
		builder.append(source.substring(lastIndex,source.length()));
		return builder.toString();
	}
	
	private static String paramParser(String regex,Object obj){
		Object res = null;
		try{
			res = paramParser(Object.class,regex,obj);
		}catch (Exception e) {
			e.printStackTrace();
		}
		if(res == null){
			return "null";
		}else{
			return res.toString();
		}
		
	}
	
	
	private static <T> T paramParser(Class<T> clazz,String regex,Object obj){
		if(regex == null || "".equals(regex.trim())){
			return null;
		}
		regex = matchFirst(regex, "\\$\\{(.*)\\}").trim();
		String[] regexs = regex.split("\\.");
		try{
			Object o = obj;
			int i = 0 ;
			for(String r : regexs){
				if(regexs.length == 1){
					break;
				}
				if(i==regexs.length-1){break;}
				if(o == null){
					return null;
				}
				o = ObjectParser(regexs[i++], o);
			}
			return (T) ObjectParser(regexs[i], o);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String matchFirst(String content,String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(content);
		m.find();
		String res = m.group(1);
		return res;
	}
	
	private static Object ObjectParser(String regex,Object obj) throws Exception{
		if(obj == null){
			return null;
		}
		if(regex.matches("\\[\\d+\\]")){//ex:[11]
			int index = Integer.parseInt(matchFirst(regex, "\\[(\\d+)\\]"));
			if(obj.getClass().isArray()){
				Object[] arrayObj = (Object[]) obj;
				return arrayObj[index];
			}
			if(obj instanceof List){
				List listObj = (List) obj;
				return listObj.get(index);
			}
			throw new Exception("该值对应的不是数组");
			
		}else if(obj instanceof HttpServletRequest && "session".equals(regex)){
			
			HttpServletRequest request = (HttpServletRequest) obj;
			return request.getSession();
			
			
		}else if(obj instanceof HttpServletRequest){
			HttpServletRequest request = (HttpServletRequest) obj;
			return request.getAttribute(regex);
			
		}else if(obj instanceof HttpSession){
			
			HttpSession session = (HttpSession) obj;
			return session.getAttribute(regex);
			
		}else if(obj instanceof Map){
			
			Map map = (Map) obj;
			return map.get(regex);
			
		}else{
			Method m = obj.getClass().getDeclaredMethod("get"+regex.substring(0,1).toUpperCase()+regex.substring(1, regex.length()));
			Object result = m.invoke(obj, null);
			return result;
		}
		
	}

}

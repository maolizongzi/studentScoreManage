package com.cndym.util.export;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Utils {
	 /**
     * 静态常量
     */
	public static final String C_TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";

	public static final String C_DATE_PATTON_DEFAULT = "yyyy-MM-dd";
	public static final int C_ONE_SECOND = 1000;
	public static final int C_ONE_MINUTE = 60 * C_ONE_SECOND;
	public static final long C_ONE_HOUR = 60 * C_ONE_MINUTE;
	public static final long C_ONE_DAY = 24 * C_ONE_HOUR;

	public static String getClassPath() {
		URL url = Utils.class.getClassLoader().getResource("");
		if (null == url) {
			return "";
		}
		return url.getPath();
	}

	 /**
     * 是否为空白,包括null和""
     *
     * @param str
     * @return
     */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static boolean isNotEmpty(String str) {
		return (null != str && "".equals(str.trim()) == false);
	}
	 /**
     * 是否为空白,包括null和""
     *
     * @param str
     * @return
     */
	public static boolean isNotEmpty(List list) {
		return (null != list && list.size() > 0);
	}

	public static boolean isNotEmpty(Object str) {
		return (null != str);
	}

	public static boolean isNotEmpty(StringBuffer str) {
		return (null != str && str.length() > 0);
	}

	public static boolean isNotEmptyObject(Object str) {
		return (null != str && !"".equals(str));
	}

	/**
     * 转换指定时间为指定格式
     *
     * @param date
     * @param format
     * @return
     */
	public static String formatDate2Str(Date date, String format) {
		if (date == null) {
			return null;
		}

		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		SimpleDateFormat sdf = getSimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
     * 转换指定时间为指定格式
     *
     * @param date
     * @param format
     * @return
     */
	public static String formatDate2Str(String date, String format) {

		DateFormat df = getSimpleDateFormat(C_DATE_PATTON_DEFAULT);
		Date d = null;
		try {
			d = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		DateFormat df2 = getSimpleDateFormat(format);
		return df2.format(d);

	}

	 /**
     * 拿到指定输出格式的SimpleDateFormat
     *
     * @param format
     * @return
     */
	public static SimpleDateFormat getSimpleDateFormat(String format) {
		SimpleDateFormat sdf;
		if (format == null) {
			sdf = new SimpleDateFormat(C_TIME_PATTON_DEFAULT);
		} else {
			sdf = new SimpleDateFormat(format);
		}

		return sdf;
	}

	/**
     * 得到当前日期
     *
     * @return
     */
	public static String today() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(new Date());
	}

	/**
     * 根据格式得到当前日期
     *
     * @param format
     * @return
     */

	public static String today(String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(new Date());
	}

	/**
     * 得到昨天的日期yyyy-MM-dd
     *
     * @return
     */
	public static String yesterday() {
		Date date = new Date();
		date.setTime(date.getTime() - 86400000);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}

	public static Date yesterdayDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(yesterday());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Date getDate(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
     * 得到昨天的日期yyyy-MM-dd
     *
     * @return
     */
	public static String yesterday(String style) {
		Date date = new Date();
		date.setTime(date.getTime() - 86400000);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(style);
		return simpleDateFormat.format(date);
	}

	/**
     * 得到明天的日期yyyy-MM-dd
     *
     * @return
     */
	public static String tomorrow() {
		Date date = new Date();
		date.setTime(date.getTime() + 86400000);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}

	/**
     * 得到前天的日期yyyy-MM-dd
     */
	public static String beforeYesterday() {
		Date date = new Date();
		date.setTime(date.getTime() - 2 * 86400000);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}

	/**
     * 得到后天的日期yyyy-MM-dd
     */
	public static String afterTomorow() {
		Date date = new Date();
		date.setTime(date.getTime() + 2 * 86400000);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}

	/**
     * 得到今天的星期
     *
     * @return 今天的星期
     */
	public static String getWeek() {
		SimpleDateFormat sdf = new SimpleDateFormat("E");
		return sdf.format(new Date());
	}

	/**
     * 获得本周一的日期
     *
     * @return
     */
	public static Date getMondayOFWeek() {
		int mondayPlus = getMondayPlus();
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DATE, mondayPlus);
		currentDate.set(Calendar.HOUR, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		Date monday = currentDate.getTime();
		return monday;
	}

	 /**
     * 获得本周星期日的日期
     *
     * @return
     */
	public static Date getCurrentWeekday() {
		int mondayPlus = getMondayPlus();
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DATE, mondayPlus + 6);
		currentDate.set(Calendar.HOUR, 23);
		currentDate.set(Calendar.MINUTE, 59);
		currentDate.set(Calendar.SECOND, 59);
		Date monday = currentDate.getTime();
		return monday;
	}

	/**
     * 获得当前日期与本周日相差的天数
     *
     * @return
     */
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天.......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	/**
     * 根据日期获取周几
     *
     * @param date
     * @return
     */

	public static String getWeekByDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("E");
		return sdf.format(date);
	}

	public static Date getLastDayOfMonth(Date date) {
		String str = "";
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号 lastDate.add(Calendar.MONTH,
										// 1);//  加一个月，变为下月的1号
										// lastDate.add(Calendar.DATE, -1);//
										// 减去一天，变为当月最后一天
										// lastDate.set(Calendar.HOUR_OF_DAY,
										// 23);
		lastDate.set(Calendar.MINUTE, 59);
		lastDate.set(Calendar.SECOND, 59);
		return lastDate.getTime();
	}

	public static Date getFirstDayOfMonth(Date date) {
		String str = "";
		Calendar firstDate = Calendar.getInstance();
		firstDate.setTime(date);
		firstDate.set(Calendar.DATE, 1);// 设为当前月的1号
										// firstDate.set(Calendar.HOUR_OF_DAY,
										// 0);
		firstDate.set(Calendar.MINUTE, 0);
		firstDate.set(Calendar.SECOND, 0);
		return firstDate.getTime();
	}

	/**
     * 日期的指定域加减
     *
     * @param time  时间戳(长整形字符串)
     * @param field 加减的域,如date表示对天进行操作,month表示对月进行操作,其它表示对年进行操作
     * @param num   加减的数值
     * @return 操作后的时间戳(长整形字符串)
     */
	public static String addDate(String time, String field, int num) {
		int fieldNum = Calendar.YEAR;
		if (field.equals("m")) {
			fieldNum = Calendar.MONTH;
		}
		if (field.equals("d")) {
			fieldNum = Calendar.DATE;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(Long.parseLong(time));
		cal.add(fieldNum, num);
		return String.valueOf(cal.getTimeInMillis());
	}

	/**
     * 日期的指定域加减
     *
     * @param date  时间戳(长整形字符串)
     * @param field 加减的域,如date表示对天进行操作,month表示对月进行操作,其它表示对年进行操作
     * @param num   加减的数值
     * @return 操作后的时间戳(长整形字符串)
     */
	public static Date addDate(Date date, String field, int num) {
		if (date != null) {
			int fieldNum = Calendar.YEAR;
			if (field.equals("m")) {
				fieldNum = Calendar.MONTH;
			}
			if (field.equals("d")) {
				fieldNum = Calendar.DATE;
			}
			if (field.equals("f")) {
				fieldNum = Calendar.MINUTE;
			}
			if (field.equals("h")) {
				fieldNum = Calendar.HOUR_OF_DAY;
			}
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(date.getTime());
			cal.add(fieldNum, num);
			return new Date(cal.getTimeInMillis());
		}
		return null;
	}

	
	/**
	 * 日期间隔
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static  int dateInterval(String startTime,String endTime){
		Date startDate=getDate(startTime);
		Date endDate=getDate(endTime);
		
		Calendar calStartDate = Calendar.getInstance();
		calStartDate.setTimeInMillis(startDate.getTime());
		
		Calendar calEndDate = Calendar.getInstance();
		calEndDate.setTimeInMillis(endDate.getTime());
		long diffDays = (calStartDate.getTimeInMillis() - calEndDate.getTimeInMillis()) / (1000 * 60 * 60 * 24);
		int day=(int) diffDays;
        return day;
	}
    /**
     * 日期的指定域加减
     *
     * @param time  时间类型
     * @param field 加减的域,如date表示对天进行操作,month表示对月进行操作,其它表示对年进行操作
     * @param num   加减的数值
     * @return 时间类型的字符串
     */
    public static String addDateTime(Date time,String field,int num){
    	try {
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        	Date afterDate=addDate(time, field, num);
        	String after=sdf.format(afterDate);
        	return after;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return "";
    }
	/**
     * 日期的指定域加减
     *
     * @param field 加减的域,如date表示对天进行操作,month表示对月进行操作,其它表示对年进行操作
     * @param num   加减的数值
     * @return 操作后的时间戳(长整形字符串)
     */
	public static long addDate(String field, int num) {
		field = field.toLowerCase();
		int fieldNum = Calendar.YEAR;
		if (field.equals("m")) {
			fieldNum = Calendar.MONTH;
		}
		if (field.equals("d")) {
			fieldNum = Calendar.DATE;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(fieldNum, num);

		return cal.getTimeInMillis();
	}

	/**
     * 得到一个日期是否是上午
     *
     * @param date 日期
     * @return 日期为上午时返回true
     */
	public static boolean isAm(Date date) {
		boolean flag = false;
		SimpleDateFormat sdf = new SimpleDateFormat("H");
		if (sdf.format(date).compareTo("12") < 0) {
			flag = true;
		}
		return flag;
	}

	public static Long getMinuteByDate(Date date1, Date date2) {
		try {
			Long num2 = date2.getTime();
			Long num1 = date1.getTime();
			if (num2 > num1) {
				return num2 - num1;
			} else {
				return 0L;
			}
		} catch (Exception e) {
			return 0L;
		}

	}

	/**
     * IP转换成10位数字
     *
     * @param ip IP
     * @return 10位数字
     */
	public static long ip2num(String ip) {
		long ipNum = 0;
		try {
			if (ip != null) {
				String ips[] = ip.split("\\.");
				for (int i = 0; i < ips.length; i++) {
					int k = Integer.parseInt(ips[i]);
					ipNum = ipNum + k * (1L << ((3 - i) * 8));
				}
			}
		} catch (Exception e) {
		}
		return ipNum;
	}

	/**
     * 将十进制整数形式转换成127.0.0.1形式的ip地址
     */
	public static String num2ip(long longIp) {
		StringBuffer sb = new StringBuffer("");
		// 直接右移24位24 sb.append(String.valueOf((longIp >>> 24)));
		sb.append(".");
		// 将高8位置0，然后右移16位 sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
		sb.append(".");
		// 将高16位置0，然后右移8位sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
		sb.append(".");
		// 将高24位置0
		sb.append(String.valueOf((longIp & 0x000000FF)));
		return sb.toString();
	}

	/**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
	public static boolean isInt(String str) {
		if (null == str)
			return false;
		str = str.trim();
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
	public static int formatInt(String str, int def) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return def;
		}
	}

	/**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
	public static long formatLong(String str, int def) {
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
			return def;
		}
	}

	/**
     * 去除号码两边的空格
     *
     * @param para    原字符串
     * @param defalut 如果为空默认值
     * @return
     */
	public static String formatStr(String para, String defalut) {
		if (null != para) {
			para = para.trim();
			if ("".equals(para)) {
				return defalut;
			}
			return para;
		} else {
			return defalut;
		}
	}

	/**
     * 去除号码两边的空格
     *
     * @param para 原字符串
     * @return
     */
	public static String formatStr(String para) {
		if (null != para) {
			para = para.trim();
			if ("".equals(para)) {
				return null;
			}
			return para;
		} else {
			return null;
		}
	}

	/**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
	public static double div(double v1, double v2) {
		return div(v1, v2, 10);
	}

	/**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 */
	public static Double divideObject(BigDecimal v1, BigDecimal v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		return v1.divide(v2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static Double divideDouble(Double v1, Integer v2, int scale) {
		if (v2 == 0) {
			return 0.00d;
		}
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, scale, BigDecimal.ROUND_UP).doubleValue();
	}

	public static Double divideInteger(Integer v1, Integer v2, int scale) {
		if (v2 == 0) {
			return 0.00d;
		}
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 
	 * @param v
	 * @param scale
	 */
	public static Double foramtDouble(Double v, int scale) {
		if (v == 0) {
			return 0.00d;
		}
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v);
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static String formatNumberZ(double amount) {
		DecimalFormat decimalFormat = new DecimalFormat("#0.00");
		String temp = decimalFormat.format(amount);
		return temp;
	}

	public static String formatNumber(double amount) {
		DecimalFormat decimalFormat = new DecimalFormat("#");
		String temp = decimalFormat.format(amount);
		return temp;
	}

	public static String formatNumberEx(double amount) {
		DecimalFormat decimalFormat = new DecimalFormat("#");
		String temp = decimalFormat.format(amount);
		if (temp.indexOf(".") == 0) {
			return "0%";
		}
		return temp + "%";
	}
	
public static Map<String, Object> transBean2Map(Object obj) {    
        if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
  
                    map.put(key, value);  
                }  
            }  
        } catch (Exception e) {  
            System.out.println("transBean2Map Error " + e);  
        }  
        return map;  
    }  

	public static Date getDayStart(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(C_DATE_PATTON_DEFAULT);
		String dateS = sdf.format(date);
		try {
			Date dayDate = sdf.parse(dateS);
			return dayDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date getDayEnd(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(C_DATE_PATTON_DEFAULT);
		String dateS = sdf.format(date);
		try {
			Date dayDate = sdf.parse(dateS);
			dayDate = new Date(dayDate.getTime()+C_ONE_DAY-1);
			return dayDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}

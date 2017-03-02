package com.shenzhenair.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	/**
	 * 获取当前yyyy-MM-dd日期
	 * @return string
	 */
	public static String getDateString(){
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String now = simpleDateFormat.format(date);
		return now;
	}
	
	
	
	public static String getDateUtil(){
		Calendar calendar=Calendar.getInstance();
		//calendar.add(Calendar.DAY_OF_MONTH, -1); 
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String date=sdf.format(calendar.getTime());
		return date;
	}
	
	public static long getDate(String data){
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date parsedata=null;
		try {
			parsedata = sdf.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parsedata.getTime();
	}
	
	public static Date getNow() {
		return  new Date();
	}
}

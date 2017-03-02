package com.shenzhenair.util;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;



/**
 * 保存初始化加载的数据
 * @author wsf
 *
 */
public class FlightDataUtil
{
	/**
	 * FD
	 */
	public static final String FD = "FD";
	/**
	 * 直减
	 */
	public static final String ZHIJIAN = "ZJ";
	
	/**
	 * NFD标示
	 */
	public static final String NFD = "NFD";
	/**
	 * 去程标示
	 */
	public static final String WAYTPYE_GO = "GO";
	/**
	 * 回程标示
	 */
	public static final String WAYTPYE_BACK = "BACK";
	/**
	 *加载webservice属性 
	 */
	public static Properties webProperties;
	/**
	 * 月份标识
	 */
	public static Map<String, String> dateMap = new HashMap<String, String>();
	/**
	 * 舱位名称
	 */
	public static Map<String, String> classCodeMap = new HashMap<String, String>();
	/**
	 * 舱位运价级别
	 */
	public static Map<String, String> classLevelMap = new HashMap<String, String>();
	/**
	 * 舱位运价排序
	 */
	public static Map<String, Integer> classSortMap = new HashMap<String, Integer>();
	static {
		//初始化使用的接口类型
		webProperties = new Properties();
		try {
			webProperties.load(FlightDataUtil.class.getClassLoader().getResourceAsStream("webservice.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//初始化月份
		dateMap.put("JAN", "01");
		dateMap.put("FEB", "02");
		dateMap.put("MAR", "03");
		dateMap.put("APR", "04");
		dateMap.put("MAY", "05");
		dateMap.put("JUN", "06");
		dateMap.put("JUL", "07");
		dateMap.put("AUG", "08");
		dateMap.put("SEP", "09");
		dateMap.put("OCT", "10");
		dateMap.put("NOV", "11");
		dateMap.put("DEC", "12");
		//舱位名称
		classCodeMap.put("F", "尊鹏头等舱");
		classCodeMap.put("A", "超值头等舱");
		classCodeMap.put("P", "超值头等舱");
		classCodeMap.put("C", "尊鹏商务舱");
		classCodeMap.put("D", "超级商务舱");
		classCodeMap.put("G", "舒适经济舱");
		classCodeMap.put("K", "特价舱");
		classCodeMap.put("L", "特价舱");
		//舱位名称
		classLevelMap.put("F", "F");
		classLevelMap.put("A", "F");
		classLevelMap.put("P", "F");
		classLevelMap.put("C", "C");
		classLevelMap.put("D", "C");
		classLevelMap.put("Y", "Y");
		//舱位排序
		classSortMap.put("F", 3);
		classSortMap.put("C", 2);
		classSortMap.put("Y", 1);
		
	}
}

package com.shenzhenair.util;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;



/**
 * �����ʼ�����ص�����
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
	 * ֱ��
	 */
	public static final String ZHIJIAN = "ZJ";
	
	/**
	 * NFD��ʾ
	 */
	public static final String NFD = "NFD";
	/**
	 * ȥ�̱�ʾ
	 */
	public static final String WAYTPYE_GO = "GO";
	/**
	 * �س̱�ʾ
	 */
	public static final String WAYTPYE_BACK = "BACK";
	/**
	 *����webservice���� 
	 */
	public static Properties webProperties;
	/**
	 * �·ݱ�ʶ
	 */
	public static Map<String, String> dateMap = new HashMap<String, String>();
	/**
	 * ��λ����
	 */
	public static Map<String, String> classCodeMap = new HashMap<String, String>();
	/**
	 * ��λ�˼ۼ���
	 */
	public static Map<String, String> classLevelMap = new HashMap<String, String>();
	/**
	 * ��λ�˼�����
	 */
	public static Map<String, Integer> classSortMap = new HashMap<String, Integer>();
	static {
		//��ʼ��ʹ�õĽӿ�����
		webProperties = new Properties();
		try {
			webProperties.load(FlightDataUtil.class.getClassLoader().getResourceAsStream("webservice.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//��ʼ���·�
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
		//��λ����
		classCodeMap.put("F", "����ͷ�Ȳ�");
		classCodeMap.put("A", "��ֵͷ�Ȳ�");
		classCodeMap.put("P", "��ֵͷ�Ȳ�");
		classCodeMap.put("C", "���������");
		classCodeMap.put("D", "���������");
		classCodeMap.put("G", "���ʾ��ò�");
		classCodeMap.put("K", "�ؼ۲�");
		classCodeMap.put("L", "�ؼ۲�");
		//��λ����
		classLevelMap.put("F", "F");
		classLevelMap.put("A", "F");
		classLevelMap.put("P", "F");
		classLevelMap.put("C", "C");
		classLevelMap.put("D", "C");
		classLevelMap.put("Y", "Y");
		//��λ����
		classSortMap.put("F", 3);
		classSortMap.put("C", 2);
		classSortMap.put("Y", 1);
		
	}
}

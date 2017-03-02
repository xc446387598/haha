package com.shenzhenair.util;

import java.math.BigDecimal;
import java.util.Properties;

public class YiSiKaiUtil {

	public static Properties properties = PropertiesUtil.getPropertiesObj("webservice.properties");
	public static String URL = properties.getProperty("dome.flight.yisikai.url");
	public static String ACTION = properties.getProperty("dome.flight.yisikai.action");
	public static String METHOD = properties.getProperty("dome.flight.yisikai.method");
	public static String NAMESPACE = properties.getProperty("dome.flight.yisikai.namespace");
	public static String SENDCOMMAND = properties.getProperty("dome.flight.yisikai.SendCommand");
	public static String CREATESESSION = properties.getProperty("dome.flight.yisikai.CreateSession");
	public static String CLEARSESSION = properties.getProperty("dome.flight.yisikai.ClearSession");
	public static String HTTPURL = URL + "/" + METHOD;
	public static String SENDCOMMANDURL = URL + "/" + SENDCOMMAND;
	public static String CREATESESSIONURL = URL + "/" + CREATESESSION;
	public static String CLEARSESSIONURL = URL + "/" + CLEARSESSION;
	public static final String SUCCESS = "000000";
	public static final String ERROR = "E00000";
	
	/**
	 * 
	 * @return
	 */
	public static String getIdentity() {
		StringBuilder request = new StringBuilder();
		request.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		request.append("<Identity_1_0>");
		request.append("<ABEConnectionString>").append(properties.getProperty("fd.deploy.bigdentity"))
		.append("</ABEConnectionString>");
		request.append("</Identity_1_0>");
		return request.toString();
	}
	
	/**
	 * 数字格式化为两们小数
	 * 
	 * @param number
	 *            要格式化的数字
	 * @return
	 */
	public static String numberFormatTwoPoint(Double number) {
		if (null == number || number==0)
			return "0.00";
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
		return df.format(number);
	}
	public static void main(String[] s){
		System.out.println(HTTPURL);
	}
	
	/**
	 * 数字格式化(四舍五入)
	 * 
	 * @param number
	 *            要格式化的数字
	 * @param len
	 *            保留几位小数
	 * @return
	 */
	public static String numberFormat(Object number, int len) {
		BigDecimal decimal = null;
		String str = null;
		try {
			decimal = new BigDecimal(number.toString());
			decimal = decimal.setScale(len, BigDecimal.ROUND_HALF_UP);
			str = decimal.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str.toString();
	}
}

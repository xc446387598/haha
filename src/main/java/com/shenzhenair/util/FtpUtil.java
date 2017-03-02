package com.shenzhenair.util;

import java.math.BigDecimal;
import java.util.Properties;

public class FtpUtil {

	public static Properties properties = PropertiesUtil.getPropertiesObj("webservice.properties");
	public static String ftpUrl = properties.getProperty("ftpUrl");
	public static String ftpUserName = properties.getProperty("ftpUserName");
	public static String ftpPassWord = properties.getProperty("ftpPassWord");
	public static String pdfPath = properties.getProperty("pdfPath");
	public static String localPath = properties.getProperty("localPath");
	
		
}

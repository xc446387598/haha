package com.shenzhenair.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 对properties文件的操作
 * @author wsf
 *
 */
public class PropertiesUtil
{
	/**
	 * 根据路径获取properties对象
	 * @param path properties文件路径
	 * @return
	 * @throws IOException
	 */
	public static Properties getPropertiesObj(String path)
	{
		Properties properties = null;
		try {
			properties = new Properties();
			InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(path);
			properties.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	/**
	 * 从properties对象中用key获取value
	 * @param properties properties对象
	 * @param key key
	 * @return
	 */
	public static String getProperty(Properties properties, String key)
	{
		String value = properties.getProperty(key);
		return (null != value) ? value.trim() : value;
	}
}

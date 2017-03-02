package com.shenzhenair.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ��properties�ļ��Ĳ���
 * @author wsf
 *
 */
public class PropertiesUtil
{
	/**
	 * ����·����ȡproperties����
	 * @param path properties�ļ�·��
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
	 * ��properties��������key��ȡvalue
	 * @param properties properties����
	 * @param key key
	 * @return
	 */
	public static String getProperty(Properties properties, String key)
	{
		String value = properties.getProperty(key);
		return (null != value) ? value.trim() : value;
	}
}

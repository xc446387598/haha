package com.shenzhenair.util;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

/**
 *@author LGH
 *@date 2013-4-11 14:19:26
 *@desc 字符串判断工具类
 */
public class StringUtil {	
	/**
	 * 判断字符是否为null或者为空
	 * @param str
	 * @return
	 * @author LGH
	 * @date 2013-3-21 14:58:59
	 */
	public static Boolean isNullOrEmpty(String str){
		Boolean flag = (null == str || "".equals(str.trim()) || "null".equals(str)) ? true : false;
		return flag;
	}	
	
	/**
	 * 判断字符是否不为null或者为空
	 * @param str
	 * @return
	 * @author LGH
	 * @date 2013-3-21 14:58:31
	 */
	public static Boolean isNotNullOrEmpty(String str){
		return !isNullOrEmpty(str);
	}
	
	/**
	 * 判断对象是否为空
	 * @param obj
	 * @return
	 * @author LGH
	 * @date 2013-8-21 09:55:53
	 */
	@SuppressWarnings("unchecked")
	public static boolean isNull(Object obj){
		if (obj == null) {
			return true;
		}else  if (obj instanceof String) {//字符串
			return  "".equals(obj) || "".equals(obj.toString().trim()) || "null".equals(obj);
		}else if (obj instanceof Map) {//复合类型Map	
			if (((Map)obj).size()==0) {
				return true;
			}
		}else if (obj instanceof List) {//复合类型List		
			if (((List)obj).size()==0) {
				return true;
			}
		}else if (obj instanceof String[]) {//复合类型String[]		
			if (((String[])obj).length==0) {
				return true;
			}
		}	
	    return false;
	}	
	
	/**
	 * 对象不为空,包括不为null，空格，""
	 * @param obj
	 * @return
	 * @author LGH
	 * @date 2013-8-21 09:57:57
	 */
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}
	
	/**
	 * 判断字符是否为null或者为空
	 * @param str 需要处理的字符串
	 * @return 本身或者空
	 * @author LGH
	 * @date 2013-8-21 10:00:23
	 */
	public static String getStringValue(String str){
		Boolean flag = isNullOrEmpty(str);
		return flag ? "" : str;
	}
	
	/**
	 * 获取xml中某个节点的值
	 * @param element  元素
	 * @param nodeName 节点名称
	 * @param defalutValue 默认值
	 * @return
	 * @author LGH
	 * @date 2013-8-21 10:00:23
	 */
	public static String getNodeValue(Element element, String nodeName, String defalutValue){
		String nodeValue = element.elementTextTrim(nodeName);
		nodeValue = isNullOrEmpty(nodeValue) ? defalutValue : nodeValue;
		return nodeValue;
	}
	
	/**
	 * 获取xml中某个节点的属性值
	 * @param element  元素
	 * @param attributeName 节点属性名称
	 * @param defalutValue 默认值
	 * @return
	 * @author LGH
	 * @date 2013-8-21 10:00:23
	 */
	public static String getAttributeValue(Element element, String attributeName, String defalutValue){
		String attributeValue = element.attributeValue(attributeName);
		attributeValue = isNullOrEmpty(attributeValue) ? defalutValue : attributeValue;
		return attributeValue;
	}
	
	
	/**
	 * 判断是否数字
	 * @param str
	 * @return
	 * @author LGH
	 * @date 2013-8-21 10:00:23
	 */
	public static boolean isNumeric(String str) {
		boolean flag = true;
		try {
			Long.valueOf(str);
		} catch (Exception ex) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 将数组对象拼接成字符串
	 * @param params
	 * @return
	 * @author LGH
	 * @date 2013-9-25 17:12:56
	 */
	public static String objectArrayToString(Object[] params){
		StringBuilder sb = new StringBuilder();
		if(isNotNull(params)){
			for(int i=0; i<params.length; i++){
				sb.append(params[i]);				
			}
		}
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		System.out.println(isNumeric("S"));
		Map map = new HashMap<String,Object>();
		System.out.println("map===>"+isNull(map));
		List list = new ArrayList<String>();
		System.out.println("list===>"+isNull(list));
		System.out.println("list===>"+isNull(""));
	}
}

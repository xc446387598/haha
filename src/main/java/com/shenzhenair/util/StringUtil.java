package com.shenzhenair.util;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

/**
 *@author LGH
 *@date 2013-4-11 14:19:26
 *@desc �ַ����жϹ�����
 */
public class StringUtil {	
	/**
	 * �ж��ַ��Ƿ�Ϊnull����Ϊ��
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
	 * �ж��ַ��Ƿ�Ϊnull����Ϊ��
	 * @param str
	 * @return
	 * @author LGH
	 * @date 2013-3-21 14:58:31
	 */
	public static Boolean isNotNullOrEmpty(String str){
		return !isNullOrEmpty(str);
	}
	
	/**
	 * �ж϶����Ƿ�Ϊ��
	 * @param obj
	 * @return
	 * @author LGH
	 * @date 2013-8-21 09:55:53
	 */
	@SuppressWarnings("unchecked")
	public static boolean isNull(Object obj){
		if (obj == null) {
			return true;
		}else  if (obj instanceof String) {//�ַ���
			return  "".equals(obj) || "".equals(obj.toString().trim()) || "null".equals(obj);
		}else if (obj instanceof Map) {//��������Map	
			if (((Map)obj).size()==0) {
				return true;
			}
		}else if (obj instanceof List) {//��������List		
			if (((List)obj).size()==0) {
				return true;
			}
		}else if (obj instanceof String[]) {//��������String[]		
			if (((String[])obj).length==0) {
				return true;
			}
		}	
	    return false;
	}	
	
	/**
	 * ����Ϊ��,������Ϊnull���ո�""
	 * @param obj
	 * @return
	 * @author LGH
	 * @date 2013-8-21 09:57:57
	 */
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}
	
	/**
	 * �ж��ַ��Ƿ�Ϊnull����Ϊ��
	 * @param str ��Ҫ������ַ���
	 * @return ������߿�
	 * @author LGH
	 * @date 2013-8-21 10:00:23
	 */
	public static String getStringValue(String str){
		Boolean flag = isNullOrEmpty(str);
		return flag ? "" : str;
	}
	
	/**
	 * ��ȡxml��ĳ���ڵ��ֵ
	 * @param element  Ԫ��
	 * @param nodeName �ڵ�����
	 * @param defalutValue Ĭ��ֵ
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
	 * ��ȡxml��ĳ���ڵ������ֵ
	 * @param element  Ԫ��
	 * @param attributeName �ڵ���������
	 * @param defalutValue Ĭ��ֵ
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
	 * �ж��Ƿ�����
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
	 * ���������ƴ�ӳ��ַ���
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

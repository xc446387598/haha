package com.shenzhenair.util;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;



/**
 * xml������
 * @author wsf
 *
 */
public class DocumentUtil {

	/**
	 * ��xml��ʽ���ַ���ת����document����
	 * @param xml
	 * @return
	 */
	public static Document getDocument(String xml)
	{
		Document doc = null;
		try {
			StringReader reader = new StringReader(xml);
			InputSource input = new InputSource(reader);
			SAXReader saxReader = new SAXReader();
			doc = saxReader.read(input);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	/**
	 * ��ָ��·����ȡxml�ļ�����װ��document����
	 * @param path xml�ļ�·��
	 * @return
	 */
	public static Document getDocumentByPath(String path)
	{
		Document doc = null;
		try {
			File file = new File(path);
			SAXReader sax = new SAXReader();
			doc = sax.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	/**
	 *  ����XML ��XMLת��XML��ʽ
	 * @param xml     ������XMl
	 * @param header  �����ǩ
	 * @param element �ڶ�����ǩ
	 * @return
	 */
	public static List<Map<String,String>> parseXML(String xml,String header,String element){
		Document resourceDoc = DocumentUtil.getDocument(xml);
		Element all = (Element) resourceDoc.selectSingleNode("//"+header);
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<Element> listElments = all.elements(element);
		for (Element e : listElments){
			List<Element> childrens=e.elements();
			Map<String,String> map=new HashMap<String,String>();
			for(Element e1:childrens){
				if(StringUtil.isNotNullOrEmpty(e1.getText())){
				map.put(e1.getName(), "".equals(e1.getText())?null:e1.getText());
				}
			}
		list.add(map);
		}
		return list;
	}
}

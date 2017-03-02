package com.shenzhenair.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * URLConnection的基本应用类
 * @author wsf
 *
 */
@SuppressWarnings("unchecked")
public class URLConnectionUtil {
	
	/**
	 * 用map封装参数(http)
	 * @param map
	 * @return
	 */
	public static String getHttpParam(Map paramMap)
	{
		StringBuilder sb = new StringBuilder();
		if (null != paramMap && !paramMap.isEmpty() && 0 != paramMap.size()) {
			for (Entry entry : (Set<Entry>)paramMap.entrySet()) {
				sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
		}
		return sb.substring(0, sb.lastIndexOf("&"));
	}
	
	/**
	 * 用map封装参数(soap)，参数为简单类型
	 * @param paramMap
	 * @param method
	 * @param nameSpace
	 * @return
	 */
	public static String getSoapParam(Map paramMap, String method, String nameSpace)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<").append(method).append(" xmlns=\"").append(nameSpace).append("\">");
		if (null != paramMap && !paramMap.isEmpty() && 0 != paramMap.size()) {
			for (Entry entry : (Set<Entry>)paramMap.entrySet()) {
				sb.append("<").append(entry.getKey()).append(">").append(entry.getValue()).append("</").append(entry.getKey()).append(">");
			}
		}
		sb.append("</").append(method).append(">");
		return sb.toString();
	}
	
	/**
	 * 用map封装参数(soap)，参数为特殊类型，如xml、中文等
	 * @param paramMap
	 * @param method
	 * @param nameSpace
	 * @return
	 */
	public static String getSoapSpecialParam(Map paramMap, String method, String nameSpace)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<").append(method).append(" xmlns=\"").append(nameSpace).append("\">");
		if (null != paramMap && !paramMap.isEmpty() && 0 != paramMap.size()) {
			for (Entry entry : (Set<Entry>)paramMap.entrySet()) {
				sb.append("<").append(entry.getKey()).append("><![CDATA[").append(entry.getValue()).append("]]></").append(entry.getKey()).append(">");
			}
		}
		sb.append("</").append(method).append(">");
		return sb.toString();
	}
	/**
	 * 用map封装参数(soap)，参数为特殊类型，如xml、中文等
	 * @param paramMap
	 * @param method
	 * @param nameSpace
	 * @return
	 * @author suixinlu
	 */
	public static String getSoapSpecialParamByXml(String ticketNo, String method, String nameSpace){
		StringBuilder sb = new StringBuilder();
		String paramXml = "<Request><TicketNo>"+ticketNo+"</TicketNo></Request>";
		sb.append("<").append(method).append(" xmlns=\"").append(nameSpace).append("\">");
		if (ticketNo!=null&&!"".equals(ticketNo)) {
			sb.append("<xml><![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(paramXml).append("]]></xml>");
		}

		/*if (null != paramMap && !paramMap.isEmpty() && 0 != paramMap.size()) {
			for (Entry<?,?> entry : paramMap.entrySet()) {
				sb.append("<").append(entry.getKey()).append("><![CDATA[").append(entry.getValue()).append("]]></").append(entry.getKey()).append(">");
			}
		}*/
		sb.append("</").append(method).append(">");
		return sb.toString();
	}
	/**
	 * 用map封装soapHeader
	 * @param paramMap
	 * @return
	 */
	public static String getSoapSpecialHeader(Map paramMap)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<soap:Header>");
		sb.append("<MySoapHeader xmlns=\"http://tempuri.org/\">");
		if (null != paramMap && !paramMap.isEmpty() && 0 != paramMap.size()) {
			for (Entry entry : (Set<Entry>)paramMap.entrySet()) {
				sb.append("<").append(entry.getKey()).append("><![CDATA[").append(entry.getValue()).append("]]></").append(entry.getKey()).append(">");
			}
		}
		sb.append("</MySoapHeader>");
		sb.append("</soap:Header>");
		return sb.toString();
	}
	
	/**
	 * URLConnection调用(GET)
	 * @param url
	 * @param connectTimeout
	 * @param readTimeout
	 * @return
	 */
	public static String httpConnection(String url, String method, String param, int connectTimeout, int readTimeout)
	{
		StringBuilder sb = new StringBuilder();
		try {
			if (url.endsWith("/")) {
				url = url + method + "?" + param;
			} else {
				url = url + "/" + method + "?" + param;
			}
			
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setDefaultUseCaches(false);
			
			InputStream input = conn.getInputStream();
			int c = -1;
			while (-1 != (c = input.read())) {
				sb.append((char)c);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result = sb.toString().replaceAll("&lt;", "<").replace("&gt;", ">");
		return result;
	}
	
	/**
	 * URLConnection调用(POST)
	 * @param url
	 * @param connectTimeout
	 * @param readTimeout
	 * @return
	 */
	public static String httpConnection(String url, String param, int connectTimeout, int readTimeout)
	{
		StringBuilder sb = new StringBuilder();
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setDefaultUseCaches(false);
			conn.setRequestMethod("POST");
			
//			OutputStream output = conn.getOutputStream();
//
//			if (null != param) {
//				byte[] b = param.getBytes();
//				output.write(b, 0, b.length);
//			}
			
			OutputStreamWriter output = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");

			//System.out.println("传参："+param);
			output.write(param);


			output.flush();
			output.close();
			
			InputStream input = conn.getInputStream();
			int c = -1;
			while (-1 != (c = input.read())) {
				sb.append((char)c);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result = sb.toString().replaceAll("&lt;", "<").replace("&gt;", ">");
		return result;
	}
	
	/**
	 * URLConnection调用(SOAP)，对参数进行编码-去返回时的soap体
	 * @param url
	 * @param connectTimeout
	 * @param readTimeout
	 * @return
	 * @throws IOException 
	 */
	public static String soapSpecialConnectionNoHead(String url, String action, String param, String encode, int connectTimeout, int readTimeout) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		String header_start=
			"<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soap:Body>";

		String header_end=
			"</soap:Body></soap:Envelope>";

		
			StringBuilder soapHeader = new StringBuilder();
			

			
			soapHeader.append(header_start);
			soapHeader.append(param);
			soapHeader.append(header_end);
			
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setDefaultUseCaches(false);
			conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			conn.setRequestProperty("Content-Length", String.valueOf(param.length()));
			conn.setRequestProperty("SOAPAction", action);
			conn.setRequestMethod("POST");
	
			OutputStream output = conn.getOutputStream();
			if (null != soapHeader) {
				byte[] b = soapHeader.toString().getBytes(encode);
				output.write(b, 0, b.length);
			}
			output.flush();
			output.close();
			
			InputStream input = conn.getInputStream();
			int c = -1;
			while (-1 != (c = input.read())) {
				sb.append((char)c);
			}
			input.close();
		
		String result = sb.toString().replaceAll("&lt;", "<").replace("&gt;", ">");
		result=result.replaceAll(header_start, "");
		result=result.replaceAll(header_end, "");
		
		result=result.replaceAll("^<ns1:.*<ns1:out>", "");
		result=result.replaceAll("</ns1:out>.*$", "");
		
		
		return result;
	}
	
	
	/**
	 * URLConnection调用(SOAP)
	 * @param url
	 * @param connectTimeout
	 * @param readTimeout
	 * @return
	 */
	public static String soapConnection(String url, String action, String param, int connectTimeout, int readTimeout)
	{
		StringBuilder sb = new StringBuilder();
		try {
			StringBuilder soapHeader = new StringBuilder();
			soapHeader.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			soapHeader.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
			soapHeader.append("<soap:Body>");
			soapHeader.append(param);
			soapHeader.append("</soap:Body>");
			soapHeader.append("</soap:Envelope>");
			
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setDefaultUseCaches(false);
			conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			conn.setRequestProperty("Content-Length", String.valueOf(param.length()));
			conn.setRequestProperty("SOAPAction", action);
			conn.setRequestMethod("POST");
	
			OutputStream output = conn.getOutputStream();
			if (null != soapHeader) {
				byte[] b = soapHeader.toString().getBytes();
				output.write(b, 0, b.length);
			}
			output.flush();
			output.close();
			
			InputStream input = conn.getInputStream();
			int c = -1;
			while (-1 != (c = input.read())) {
				sb.append((char)c);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result = sb.toString().replaceAll("&lt;", "<").replace("&gt;", ">");
		return result;
	}
	
	/**
	 * URLConnection调用(SOAP)，对参数进行编码
	 * @param url
	 * @param connectTimeout
	 * @param readTimeout
	 * @return
	 */
	public static String soapSpecialConnection(String url, String action, String param, String encode, int connectTimeout, int readTimeout)
	{
		StringBuilder sb = new StringBuilder();
		try {
			StringBuilder soapHeader = new StringBuilder();
			soapHeader.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			soapHeader.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
			soapHeader.append("<soap:Body>");
			soapHeader.append(param);
			soapHeader.append("</soap:Body>");
			soapHeader.append("</soap:Envelope>");
			
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setDefaultUseCaches(false);
			conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			conn.setRequestProperty("Content-Length", String.valueOf(param.length()));
			conn.setRequestProperty("SOAPAction", action);
			conn.setRequestMethod("POST");
	
			OutputStream output = conn.getOutputStream();
			if (null != soapHeader) {
				byte[] b = soapHeader.toString().getBytes(encode);
				output.write(b, 0, b.length);
			}
			output.flush();
			output.close();
			
			InputStream input = conn.getInputStream();
			int c = -1;
			while (-1 != (c = input.read())) {
				sb.append((char)c);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result = sb.toString().replaceAll("&lt;", "<").replace("&gt;", ">");
		return result;
	}
	
	/**
	 * URLConnection调用(SOAP)，对参数进行编码
	 * @param url
	 * @param connectTimeout
	 * @param readTimeout
	 * @return
	 */
	public static String soapSpecialConnection(String url, String action, String header, String param, String encode, int connectTimeout, int readTimeout)
	{
		StringBuilder sb = new StringBuilder();
		try {
			StringBuilder soapHeader = new StringBuilder();
			soapHeader.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			soapHeader.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
			if (null != header) {
				soapHeader.append(header);
			}
			soapHeader.append("<soap:Body>");
			soapHeader.append(param);
			soapHeader.append("</soap:Body>");
			soapHeader.append("</soap:Envelope>");
			
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setDefaultUseCaches(false);
			conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			conn.setRequestProperty("Content-Length", String.valueOf(param.length()));
			conn.setRequestProperty("SOAPAction", action);
			conn.setRequestMethod("POST");
	
			OutputStream output = conn.getOutputStream();
			if (null != soapHeader) {
				byte[] b = soapHeader.toString().getBytes(encode);
				output.write(b, 0, b.length);
			}
			output.flush();
			output.close();
			
			InputStream input = conn.getInputStream();
			int c = -1;
			while (-1 != (c = input.read())) {
				sb.append((char)c);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result = sb.toString().replaceAll("&lt;", "<").replace("&gt;", ">");
		return result;
	}
	
//	/**
//	 * 调用51book的方法
//	 * @param url
//	 * @param encode
//	 * @param connectTimeout
//	 * @param readTimeout
//	 * @return
//	 */
//	public static String bookConnection(Map paramMap, String url, String encode, int connectTimeout, int readTimeout)
//	{
//		StringBuilder sb = new StringBuilder();
//		try {
//			StringBuilder signBuilder = new StringBuilder();
//			signBuilder.append(paramMap.get("agencyCode"));
//			signBuilder.append(paramMap.get("airline"));
//			signBuilder.append(paramMap.get("departure"));
//			signBuilder.append(paramMap.get("arrival"));
//			signBuilder.append(paramMap.get("page"));
//			signBuilder.append(paramMap.get("rowPerPage"));
//			signBuilder.append(paramMap.get("isBusinessUnitPolicy"));
//			signBuilder.append(paramMap.get("isIncludeSpecialPolicy"));
//			signBuilder.append(paramMap.get("safeCode"));
//			
//			String sign = EncodeUtils.getMD5String(signBuilder.toString());
//			
//			StringBuilder soapHeader = new StringBuilder();
//			soapHeader.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:get=\"http://getpolicydata.version2_0.webservice.liantuo.com\" xmlns:ver=\"http://version2_0.webservice.liantuo.com\">");
//			soapHeader.append("<soapenv:Header/>");
//			soapHeader.append("<soapenv:Body>");
//			soapHeader.append("<get:getPolicyData>");
//			soapHeader.append("<get:in0>");
//			soapHeader.append("<get:credential>");
//			soapHeader.append("<ver:agencyCode>").append(paramMap.get("agencyCode")).append("</ver:agencyCode>");
//			soapHeader.append("<ver:sign>").append(sign).append("</ver:sign>");
//			soapHeader.append("</get:credential>");
//			soapHeader.append("<get:airline>").append(paramMap.get("airline")).append("</get:airline>");
//			soapHeader.append("<get:departure>").append(paramMap.get("departure")).append("</get:departure>");
//			soapHeader.append("<get:arrival>").append(paramMap.get("arrival")).append("</get:arrival>");
//			soapHeader.append("<get:page>").append(paramMap.get("page")).append("</get:page>");
//			soapHeader.append("<get:rowPerPage>").append(paramMap.get("rowPerPage")).append("</get:rowPerPage>");
//			soapHeader.append("<get:isBusinessUnitPolicy>").append(paramMap.get("isBusinessUnitPolicy")).append("</get:isBusinessUnitPolicy>");
//			soapHeader.append("<get:isIncludeSpecialPolicy>").append(paramMap.get("isIncludeSpecialPolicy")).append("</get:isIncludeSpecialPolicy>");
//			soapHeader.append("</get:in0>");
//			soapHeader.append("</get:getPolicyData>");
//			soapHeader.append("</soapenv:Body>");
//			soapHeader.append("</soapenv:Envelope>");
//			
//			URL u = new URL(url);
//			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
//			conn.setConnectTimeout(connectTimeout);
//			conn.setReadTimeout(readTimeout);
//			conn.setDoInput(true);
//			conn.setDoOutput(true);
//			conn.setUseCaches(false);
//			conn.setDefaultUseCaches(false);
//			conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
//			conn.setRequestMethod("POST");
//	
//			OutputStream output = conn.getOutputStream();
//			if (null != soapHeader) {
//				byte[] b = soapHeader.toString().getBytes(encode);
//				output.write(b, 0, b.length);
//			}
//			output.flush();
//			output.close();
//			
//			InputStream input = conn.getInputStream();
//			int c = -1;
//			while (-1 != (c = input.read())) {
//				sb.append((char)c);
//			}
//			input.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		String result = sb.toString().replaceAll("&lt;", "<").replace("&gt;", ">");
//		return result;
//	}
}

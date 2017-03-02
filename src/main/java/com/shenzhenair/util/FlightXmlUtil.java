package com.shenzhenair.util;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import com.shenzhenair.bean.RatesClass;
import com.shenzhenair.bean.RatesData;
import com.shenzhenair.bean.Route;

public class FlightXmlUtil {

	/**
	 * 将FD，NFD，直减打包返回XML
	 * 
	 * @param routeList
	 * @return
	 * @throws Exception
	 */
	public static String getZhiJianAndFDAndNFDXml(List<Route> routeList) throws Exception {
		StringBuilder sb = new StringBuilder();
		String resultCode = null != routeList ? YiSiKaiUtil.SUCCESS : YiSiKaiUtil.ERROR;
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<Response>");
		sb.append("<Hmac>").append("").append("</Hmac>");
		sb.append("<ResultCode>").append(resultCode).append("</ResultCode>");
		for (Route route : routeList) {
			List<RatesClass> ratesClassList = route.getRatesClasss();
			sb.append("<RouteData From=\"").append(route.getFrom());
			sb.append("\" Arrive=\"").append(route.getArrive());
			sb.append("\" Date=\"").append(route.getDate());
			sb.append("\" TPM=\"").append(route.getTpm());
			sb.append("\" ClassYPrice=\"").append(YiSiKaiUtil.numberFormatTwoPoint(route.getClassYPrice()));
			sb.append("\" AFuelTax=\"").append( YiSiKaiUtil.numberFormatTwoPoint(route.getAfuelTax()));
			sb.append("\" CFuelTax=\"").append( YiSiKaiUtil.numberFormatTwoPoint(route.getCfuelTax()));
			sb.append("\" IFuelTax=\"").append( YiSiKaiUtil.numberFormatTwoPoint(route.getIfuelTax()));
			sb.append("\" AirportTax=\"").append( YiSiKaiUtil.numberFormatTwoPoint(route.getAirportTax()));
			sb.append("\">");
			for (RatesClass rc : ratesClassList) {
				sb.append("<Class ID=\"").append(rc.getId());
				sb.append("\" Carrier=\"").append(rc.getCarrier() == null ? "" : rc.getCarrier());
				sb.append("\" Code=\"").append(rc.getCode() == null ? "" : rc.getCode());
				sb.append("\" Ext=\"").append(rc.getExt() == null ? "" : rc.getExt());
				sb.append("\" Farebase=\"").append(rc.getFarebase());
				sb.append("\">");
				List<RatesData> rdList = rc.getRatesDataList();
				Map<String, String> nfdflag = new HashMap<String, String>();
				for (RatesData rd : rdList) {
					if ("NFD".equals(rd.getRatesType())) {
						String key = rd.getFlight() + rd.getNotFlight();
						if (null != nfdflag.get(key)) {
							continue;
						} else {
							nfdflag.put(key, rd.getRatesId());
						}
					}
					sb.append("<Rates RatesId=\"").append(rd.getRatesId() == null ? "" : rd.getRatesId());
					sb.append("\" RatesType =\"").append(rd.getRatesType() == null ? "" : rd.getRatesType());
					String ratesName = FlightDataUtil.classCodeMap.get(rc.getCode());
					if (StringUtil.isNullOrEmpty(ratesName)) {
						ratesName = rd.getDiscount() == null ? "" : rd.getDiscount() + "折";
					}
					sb.append("\" RatesName =\"").append(ratesName);
					sb.append("\" Price=\"").append(rd.getPrice() == null ? "0.00" : YiSiKaiUtil.numberFormatTwoPoint(rd.getPrice()));
					sb.append("\" Discount=\"").append(rd.getDiscount() == null ? "" : rd.getDiscount());
					sb.append("\" Totle =\"").append("");
					sb.append("\" Flight  =\"").append(getFlightNo(rd.getFlight(),rc.getCarrier()));
					sb.append("\" NotFlight =\"").append(getFlightNo(rd.getNotFlight(),rc.getCarrier()));
					sb.append("\" ReturnTicket =\"").append(rd.getReturnTicket() == null ? "" : rd.getReturnTicket());
					sb.append("\" Comments =\"").append(rd.getComments() == null ? "" : rd.getComments());
					sb.append("\" Reserve1 =\"");
					sb.append("\" Reserve2 =\"");
					sb.append("\" Reserve3 =\"");
					sb.append("\" Reserve4 =\"");
					sb.append("\" Reserve5 =\"");
					sb.append("\">");
					sb.append("</Rates>");
				}
				sb.append("</Class>");
			}
			sb.append("</RouteData>");
		}
		sb.append("</Response>");
		return sb.toString();
	}
	
	
	/**
	 * 整理av接口返回的数据格式，使其能够转换为document对象
	 * 
	 * @param xml
	 *            av查询结果
	 * @return
	 * @throws Exception
	 */
	public static String formatResult(String xml) throws Exception {
		xml = xml.replaceAll("&gt;", ">").replaceAll("&lt;", "<");
		xml = xml.replaceAll("<string xmlns=\"http://tempuri.org/\">", "<string>");
		xml = xml.replaceAll("<\\?\\s*xml\\s+version=\"1.0\"\\s+encoding=\"gb2312\"\\s*\\?>|<\\?\\s*xml\\s+version=\"1.0\"\\s*\\?>", "");
		xml = xml.replaceAll("[\\r\\n]", "");
		return xml;
	}
	
	public static String getFlightNo(String flight,String carrier){
		return flight == null ? "" : flight.length()==4?carrier+flight:flight;
	}
	
	/**
	 * 拼接接口返回的错误信息
	 * 
	 * @param result
	 *            xml格式
	 * @return
	 * @throws Exception
	 */
	public static String getFDResultErrorXml(String result) throws Exception {
		StringBuilder sb = new StringBuilder();
		Document doc = DocumentUtil.getDocument(result);
		Element errorElement = doc.getRootElement().element("ErrorInfo_1_0");
		String errorCnt = errorElement.elementText("Content");
		String code = errorCnt.substring(errorCnt.indexOf("Code=") + 6, errorCnt.indexOf("Code=") + 11);
		String errorMsg = errorCnt.substring(errorCnt.indexOf("Message=") + 9, errorCnt.indexOf("Message=") + 50);
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<Response>");
		sb.append("<ResultCode>").append(code).append("</ResultCode>");
		sb.append("<ResultMsg>").append(errorMsg).append("</ResultMsg>");
		sb.append("</Response>");
		return sb.toString();
	}
	
}

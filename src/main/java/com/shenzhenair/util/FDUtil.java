package com.shenzhenair.util;

import java.util.ArrayList;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;

import com.shenzhenair.bean.RatesClass;
import com.shenzhenair.bean.RatesData;
import com.shenzhenair.bean.RequestParam;
import com.shenzhenair.bean.Route;



public class FDUtil {
	private Log log = LogFactory.getLog("flightLog");

	/**
	 * 调用FD接口，通过WebServices获取数据
	 * 
	 * @param fdRequest
	 * @return
	 * @throws Exception
	 */
	public String getFdResult(RequestParam fdRequest) throws Exception {
		StringBuilder request = new StringBuilder();
		request.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		request.append("<ABE_FDDataset_1_0>");
		request.append("<From>").append(fdRequest.getFrom()).append("</From>");
		request.append("<Arrive>").append(fdRequest.getArrive()).append("</Arrive>");
		request.append("<Date>").append(fdRequest.getFdDate()).append("</Date>");
		request.append("<Carrier>").append(fdRequest.getCarrier()).append("</Carrier>");
		request.append("</ABE_FDDataset_1_0>");

		Map<String, String> paramMap = new LinkedHashMap<String, String>();
		String identity = YiSiKaiUtil.getIdentity();
		if ("error".equals(identity)) {
			return "I00014";
		}
		paramMap.put("identity", identity);
		paramMap.put("request", request.toString());
		paramMap.put("filter", "");
		String params = URLConnectionUtil.getHttpParam(paramMap);
		log.info("request---" + params);
		String xml = URLConnectionUtil.httpConnection(YiSiKaiUtil.HTTPURL, params, 60000, 60000);
		xml = new String(xml.getBytes("ISO-8859-1"), "UTF-8");
		xml = FlightXmlUtil.formatResult(xml);
		log.info("response---" + xml);
		return xml;
	}

	/**
	 * 将FD接口返回的数据封装成List
	 * 
	 * @param xml
	 * @return
	 */
	public Route getFdResult(String xml, String wayType) {
		Document doc = DocumentUtil.getDocument(xml);
		Element fdElement = doc.getRootElement().element("CRS.CommandSet.FD");
		List<Element> itemList = fdElement.elements("Item");
		String from = fdElement.attributeValue("From");
		String arrive = fdElement.attributeValue("Arrive");
		String date = fdElement.attributeValue("Date");
		String tpm = fdElement.attributeValue("TPM");
		String classYPrice = fdElement.attributeValue("ClassYPrice");

		Route route = new Route();
		route.setFrom(from);
		route.setArrive(arrive);
		route.setDate(formatDate(date));
		route.setTpm(tpm);
		route.setWayType(wayType);
		route.setClassYPrice(Double.parseDouble(classYPrice));
		List<RatesClass> ratesClassList = new ArrayList<RatesClass>();
		for (Element itemElement : itemList) {
			List<RatesData> ratesDataList = new ArrayList<RatesData>();
			RatesClass ratesClass = new RatesClass();
			RatesData ratesData = new RatesData();
			//String id = itemElement.attributeValue("ID");
			String elementNo = itemElement.attributeValue("ElementNo");
			String carrier = itemElement.elementTextTrim("Carrier");
			String farebase = itemElement.elementTextTrim("Farebase");
			String price = itemElement.elementTextTrim("Price");
			String totle = itemElement.elementTextTrim("Totle");
			String notValidBefore = itemElement.elementTextTrim("NotValidBefore");
			String notValidAfter = itemElement.elementTextTrim("NotValidAfter");
			String Class = itemElement.elementTextTrim("Class");
			String classEx = itemElement.elementTextTrim("ClassEx");
			String returnClass = itemElement.elementTextTrim("ReturnClass");
			String fuelTax = itemElement.elementTextTrim("FuelTax");
			String airportTax = itemElement.elementTextTrim("AirportTax");
			ratesClass.setId(0);
			ratesClass.setElementNo(Integer.valueOf(elementNo));
			ratesClass.setCarrier(carrier);
			ratesClass.setFarebase(farebase);
			ratesClass.setExt(classEx);
			ratesClass.setCode(Class);
			route.setAfuelTax(StringUtil.isNotNullOrEmpty(fuelTax)?Double.parseDouble(fuelTax):0.00);
			route.setAirportTax(StringUtil.isNotNullOrEmpty(airportTax)?Double.parseDouble(airportTax):0.00);
			ratesData.setRatesId("");
			ratesData.setRatesType(FlightDataUtil.FD);
			ratesData.setPrice(StringUtil.isNotNullOrEmpty(price) ? Double.parseDouble(price) : null);
			if (null == ratesData.getPrice())
				continue;
			Double discount = Double.parseDouble(YiSiKaiUtil.numberFormat(Double.parseDouble(price) / Double.parseDouble(classYPrice),
					2));
			ratesData.setDiscount(discount);
			ratesData.setTotle(StringUtil.isNotNullOrEmpty(totle) ? Double.parseDouble(totle) : null);
			ratesData.setNotValidBefore(formatDate(notValidBefore));
			ratesData.setNotValidAfter(formatDate(notValidAfter));
			ratesData.setReturnClass(returnClass);
			ratesDataList.add(ratesData);
			ratesClass.setRatesDataList(ratesDataList);
			ratesClassList.add(ratesClass);
		}
		route.setRatesClasss(ratesClassList);
		return route;
	}

	/**
	 * 30MAY16 日期格式转化为YYYY-MM-DD
	 * 
	 * @param a
	 */
	public static String formatDate(String dateStr) {
		if (dateStr.length() == 7) {
			String y = "-";
			String day = dateStr.substring(0, 2);
			String month = dateStr.substring(2, 5);
			String year = "20" + dateStr.substring(5, 7);
			if (FlightDataUtil.dateMap.containsKey(month)) {
				String monthNum = FlightDataUtil.dateMap.get(month);
				dateStr = year + y + monthNum + y + day;
			}
		}
		return dateStr;
	}

	/**
	 * 检查调用易思凯FD接口返回结果是否是包含ErrorInfo_1_0
	 * 
	 * @param xml
	 * @return
	 */
	public static boolean checkFDResult(String xml) {
		boolean isError = false;
		;
		Document doc = DocumentUtil.getDocument(xml);
		Element errorElement = doc.getRootElement().element("ErrorInfo_1_0");
		if (null != errorElement) {
			return true;
		}
		return isError;
	}

	public static void main(String[] a) {
		String date = "30MAY16";
		if (date.length() == 7) {
			String y = "-";
			String day = date.substring(0, 2);
			String month = date.substring(2, 5);
			String year = "20" + date.substring(5, 7);
			if (FlightDataUtil.dateMap.containsKey(month)) {
				String monthNum = FlightDataUtil.dateMap.get(month);
				System.out.print(year + y + monthNum + y + day);
			}
		}
	}
}

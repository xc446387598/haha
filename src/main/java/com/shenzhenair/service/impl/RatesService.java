package com.shenzhenair.service.impl;

import java.io.IOException;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shenzhenair.bean.Fd;
import com.shenzhenair.bean.RatesClass;
import com.shenzhenair.bean.RatesData;
import com.shenzhenair.bean.RequestParam;
import com.shenzhenair.bean.Route;
import com.shenzhenair.mapper.FdMapper;
import com.shenzhenair.service.IRatesService;
import com.shenzhenair.util.DateUtil;
import com.shenzhenair.util.FDUtil;
import com.shenzhenair.util.FileUtil;
import com.shenzhenair.util.FlightDataUtil;
import com.shenzhenair.util.FlightXmlUtil;



/**
 * �˼�service
 * 
 * @author ���沩
 * 
 */
@Service("ratesService")
public class RatesService implements IRatesService {
	private Log log = LogFactory.getLog(RatesService.class);
	//private final static FileUtil FILE_LOG = new FileUtil(FDUtil.class, "flightLog");
	private FDUtil fdutil = new FDUtil();
	@Resource
	private FdMapper fdMapper;
	
	 @Autowired  
	 private SqlSessionTemplate sqlSessionTemplate; 
	
	@Override
	public List<Route> getRates(RequestParam request)throws Exception {
		Long starttime = new Date().getTime();//��ʼ����ʱ��/��
		//FILE_LOG.appendInfo(new Throwable("sssssssssssssssssssssss����FD�ӿڲ�ѯ����==����==>\r\n����:"));
				
		log.info("����FDdddddddddddddddddd�ӿڲ�ѯ������" + request.toString());//��̨��־
		// ����FD�ӿڲ�ѯȥ��
		String resultXml = fdutil.getFdResult(request);
		List<Route> routeList = new ArrayList<Route>();
		String response=null;
		// ��ȥ�̷�װ�ɶ���
		if (FDUtil.checkFDResult(resultXml)) {
			Long endtime = new Date().getTime();
			Long time =(endtime-starttime)/1000;//����ʱ��
			log.info("����FDddddddddddddddddddd�ӿڲ�ѯȥ��ʧ��:"+"����ʱ���ǣ�"+time+"��-------"+",RequestParam:"+request);//��̨��־
			//FILE_LOG.appendInfo(new Throwable("����FD�ӿڲ�ѯȥ��ʧ��==����==>\r\nRequestParam:"+time+"��--"+request +"response---" +  resultXml ));
		} else {
			Route route = fdutil.getFdResult(resultXml, FlightDataUtil.WAYTPYE_GO);
			routeList.add(route);
		}
		Long endtime = new Date().getTime();
		Long time =(endtime-starttime)/1000;//����ʱ��
		log.info("����FDdddddddddddddddddddddd�ӿڷ��أ�"+"����ʱ���ǣ�"+time+"��----------"+routeList.toString());//��̨��־
		
		//FILE_LOG.appendInfo(new Throwable("����FD�ӿڷ��� \r\n����:"+time+"��--"+"response---" + routeList.toString()));

	    return routeList;
	}

	/***
	 * ��ѯ������FD�˼���һ�廯���ݿ���д���ݡ�
	 */
	 
	@Override
	public void insert(List<Route> routeList) throws Exception {
		for(Route route : routeList){
			Fd fd = new Fd();
			fd.setAfueltax(route.getAfuelTax());
			fd.setAirporttax(route.getAirportTax());
			fd.setArrive(route.getArrive());
			fd.setClassyprice(route.getClassYPrice());
			fd.setDepart(route.getFrom());
			fd.setFlightdate(route.getDate());
			fd.setTpm(route.getTpm());
			fd.setWaytype(route.getWayType());
			List<RatesClass> list = route.getRatesClasss();
			for(RatesClass ratesClass : list){
				fd.setCode(ratesClass.getCode());
				fd.setCarrier(ratesClass.getCarrier());
				fd.setFarebase(ratesClass.getFarebase());
				List<RatesData> rList = ratesClass.getRatesDataList();
				for(RatesData ratesData : rList){
					fd.setDiscount(ratesData.getDiscount());
					fd.setNotvalidafter(ratesData.getNotValidAfter());
					fd.setNotvalidbefore(ratesData.getNotValidBefore());
					if(null!=ratesData.getTotle()){
						fd.setTotle(ratesData.getTotle());
					}
					fd.setReturnclass(ratesData.getReturnClass());
					fd.setRatestype(ratesData.getRatesType());
					fd.setPrice(ratesData.getPrice());
				}
				fdMapper.insert(fd);
				
			}
			fdMapper.update(fd);
			
		}
	};
	
	public List queryData(){
		return sqlSessionTemplate.selectList("com.shenzhenair.mapper.FdMapper.queryAirline");
	}
	
	
}
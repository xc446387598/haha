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
 * 运价service
 * 
 * @author 胡益博
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
		Long starttime = new Date().getTime();//开始调用时间/秒
		//FILE_LOG.appendInfo(new Throwable("sssssssssssssssssssssss调用FD接口查询参数==请求==>\r\n参数:"));
				
		log.info("调用FDdddddddddddddddddd接口查询参数：" + request.toString());//后台日志
		// 调用FD接口查询去程
		String resultXml = fdutil.getFdResult(request);
		List<Route> routeList = new ArrayList<Route>();
		String response=null;
		// 把去程封装成对象
		if (FDUtil.checkFDResult(resultXml)) {
			Long endtime = new Date().getTime();
			Long time =(endtime-starttime)/1000;//共用时长
			log.info("调用FDddddddddddddddddddd接口查询去程失败:"+"返回时长是："+time+"秒-------"+",RequestParam:"+request);//后台日志
			//FILE_LOG.appendInfo(new Throwable("调用FD接口查询去程失败==返回==>\r\nRequestParam:"+time+"秒--"+request +"response---" +  resultXml ));
		} else {
			Route route = fdutil.getFdResult(resultXml, FlightDataUtil.WAYTPYE_GO);
			routeList.add(route);
		}
		Long endtime = new Date().getTime();
		Long time =(endtime-starttime)/1000;//共用时长
		log.info("调用FDdddddddddddddddddddddd接口返回："+"返回时长是："+time+"秒----------"+routeList.toString());//后台日志
		
		//FILE_LOG.appendInfo(new Throwable("调用FD接口返回 \r\n参数:"+time+"秒--"+"response---" + routeList.toString()));

	    return routeList;
	}

	/***
	 * 查询出来的FD运价往一体化数据库中写数据。
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
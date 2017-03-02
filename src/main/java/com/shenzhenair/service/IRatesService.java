package com.shenzhenair.service;

import java.util.List;
import java.util.Map;

import com.shenzhenair.bean.RequestParam;
import com.shenzhenair.bean.Route;

public interface IRatesService {

	/**
	 * 运价查询
	 * @throws Exception 
	 */
	public List<Route> getRates(RequestParam request) throws Exception;
	
	/**
	 * 插入FD运价信息
	 * @throws Exception 
	 */
	public void insert(List<Route> routeList) throws Exception;
	
	
}

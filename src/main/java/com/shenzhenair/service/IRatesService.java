package com.shenzhenair.service;

import java.util.List;
import java.util.Map;

import com.shenzhenair.bean.RequestParam;
import com.shenzhenair.bean.Route;

public interface IRatesService {

	/**
	 * �˼۲�ѯ
	 * @throws Exception 
	 */
	public List<Route> getRates(RequestParam request) throws Exception;
	
	/**
	 * ����FD�˼���Ϣ
	 * @throws Exception 
	 */
	public void insert(List<Route> routeList) throws Exception;
	
	
}

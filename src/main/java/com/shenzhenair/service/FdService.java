package com.shenzhenair.service;

import java.util.List;

import com.shenzhenair.bean.FdSyn;

public interface FdService {
	public int batchInsertData(List<FdSyn> list);
	public int batchJdbcInsertData(List<String> list);
	
	public void deleteData();
}

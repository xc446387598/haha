package com.shenzhenair.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shenzhenair.bean.Fd;
import com.shenzhenair.bean.FdSyn;

@Repository
public interface FdMapper {
	
	/**
	 * 插入易思凯FD数据
	 * @param fd
	 */
	void insert(Fd fd);
	
	/**
	 * 更新状态，根据ID停用FD运价
	 * @param fd
	 */
	void update(Fd fd);
	
	
	/**
	 * 查询符合条件的运价
	 * @param fd
	 */
	List<Fd> queryByCondition(Fd fd);
	
	/**
	 * 查询航线
	 * @param fd
	 */
	List<Fd> queryAirline();
	
	void delete(FdSyn fs);
	
	void deleteAll();
	
	List<FdSyn> queryFlag();
	
	
	List<FdSyn> queryFlagForUpdate(FdSyn fs);
}

package com.shenzhenair.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shenzhenair.bean.Fd;
import com.shenzhenair.bean.FdSyn;

@Repository
public interface FdMapper {
	
	/**
	 * ������˼��FD����
	 * @param fd
	 */
	void insert(Fd fd);
	
	/**
	 * ����״̬������IDͣ��FD�˼�
	 * @param fd
	 */
	void update(Fd fd);
	
	
	/**
	 * ��ѯ�����������˼�
	 * @param fd
	 */
	List<Fd> queryByCondition(Fd fd);
	
	/**
	 * ��ѯ����
	 * @param fd
	 */
	List<Fd> queryAirline();
	
	void delete(FdSyn fs);
	
	void deleteAll();
	
	List<FdSyn> queryFlag();
	
	
	List<FdSyn> queryFlagForUpdate(FdSyn fs);
}

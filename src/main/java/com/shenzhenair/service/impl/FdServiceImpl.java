package com.shenzhenair.service.impl;

import java.io.IOException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
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
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.dom4j.Document;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.shenzhenair.bean.Fd;
import com.shenzhenair.bean.FdSyn;
import com.shenzhenair.bean.RatesClass;
import com.shenzhenair.bean.RatesData;
import com.shenzhenair.bean.RequestParam;
import com.shenzhenair.bean.Route;
import com.shenzhenair.mapper.FdMapper;
import com.shenzhenair.service.FdService;

import com.shenzhenair.util.DateUtil;




/**
 * 运价service
 * 
 * @author 胡益博
 * 
 */
@Service("fdService")
public class FdServiceImpl implements FdService {
	private Log log = LogFactory.getLog(FdServiceImpl.class);
	//private final static FileUtil FILE_LOG = new FileUtil(FDUtil.class, "flightLog");
	
	
	
    @Autowired  
	//private SqlSessionTemplate sqlSessionTemplate; 
	private SqlSessionFactory sqlSessionFactory;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Resource
	private FdMapper fdMapper;
    
	public int batchInsertData(List<FdSyn> list){
		 int result = 0;
		 int batchCount = 1000;//每批commit的个数 
		 int num=0;
		 int size=list.size();
		 int del=0;
		 try {
			 SqlSession session = sqlSessionFactory.openSession(false); 
			 Connection connection=session.getConnection();
			 connection.setAutoCommit(false);
			 for(int i = 0; i < size;){
				 if(batchCount<list.size()-del){
					 num=num+batchCount;
					 Map<String,Object> map=new HashMap<String,Object>();
					del=del+batchCount;
					 map.put("list",  list.subList(i, num));
					// size=size-batchCount;
					 result=  session.insert("com.shenzhenair.mapper.FdMapper.addDataBatch", map);
					 connection.commit();
					 session.clearCache();
					// size=size-batchCount;
					 i=i+batchCount;
				 }else{
					 Map<String,Object> map=new HashMap<String,Object>();
					
					 map.put("list",  list.subList(i, size));
					 result=  session.insert("com.shenzhenair.mapper.FdMapper.addDataBatch", map);
					 connection.commit();
					 session.clearCache();
					 break;//数据插入完毕,退出循环
				 }
			 }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 System.out.println("集合的长度:"+list.size());
		
		 
		 
		 
		
		return result;
		 
		
	
		
		
	}
	
	public int batchJdbcInsertData(List<String> list){
		long timeTestStart = System.currentTimeMillis();// 记录开始时间  
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		
		int count = 1;// 计数器  
        String lineTXT = null;  
        PreparedStatement pstmt = null;  
        String[] temp = null;  
        Connection conn=null;
        java.sql.Date nowDate = new java.sql.Date(new java.util.Date().getTime());
        try {
			 conn = jdbcTemplate.getDataSource().getConnection();
			 conn.setAutoCommit(false);// 设置数据手动提交，自己管理事务  
			 String sql = "insert into T_BASEPRICE(ID,AIRLINE, DEPART, ARRIVE, CLASSCODE, STARTDATE, ENDDATE, PRICEOW, PRICERT, TPM, CABIN, SERVICE, FAREBASIS, UPDATETIME, STATUS, UPDATEBY, PREDAYS, ENDDAYS, FARERULE,FLAG,FARESTARTEDAY,FAREENDDAY) values (CLASSAGIO_FD_SEQ.NEXTVAL,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,? )";
	         pstmt = conn.prepareStatement(sql);  
	         for(int i = 0; i < list.size();i++){
	        	
	        	 temp=list.get(i).split(",");
	        	 int ss=temp.length;
	        if(temp!=null&&temp.length>1){
	        		 
	        	 String classcode=temp[8];
	        	 if(classcode.equals("")){
	        		 classcode=temp[7];
	        	 }
	        	 String sDate=temp[18]; //开始日期 
	        	 String eDate=temp[19];//结束日期
	        	 String fsDate=temp[13];//运价失效日期
	        	 String feDate=temp[14];//运价失效日期
	        	 
	        	 int priceOw=Integer.parseInt(temp[11]);//单程价格
	        	 int priceRt=Integer.parseInt(temp[12]);//往返价格                         
	        	 java.sql.Date startDate = new java.sql.Date(DateUtil.getDate(sDate)); 
	        	 java.sql.Date endDate = new java.sql.Date(DateUtil.getDate(eDate)); 
	        	 java.sql.Date fareStartDate = new java.sql.Date(DateUtil.getDate(fsDate)); 
	        	 java.sql.Date fareEndDate = new java.sql.Date(DateUtil.getDate(feDate)); 
	        	
	        	 pstmt.setString(1, temp[0]);  //航空公司
                 pstmt.setString(2, temp[1]);  //出发城市
                 pstmt.setString(3, temp[3]);   //抵达城市
                 
                 pstmt.setString(4, temp[8]);    //舱位代码 
                 pstmt.setDate(5, startDate);   //开始日期 
                 pstmt.setDate(6, endDate);    //结束日期
                 pstmt.setInt(7, priceOw);   //单程价格
                 pstmt.setInt(8, priceRt);   //往返价格
                 pstmt.setNull(9, Types.INTEGER); 
                 pstmt.setString(10, temp[6]);  //舱位等级
                 pstmt.setString(11, temp[7]);  //服务等级
                 pstmt.setString(12, "");    //Fare_basis
                 pstmt.setDate(13, nowDate);  //更新时间
                 pstmt.setString(14, "Y");      //状态 Y/N
                 pstmt.setString(15, "system");  //更新人
                 pstmt.setInt(16, 0);          //最早预订
                 pstmt.setInt(17, 0);       //最晚预订（含）
                 pstmt.setString(18, "");
                 pstmt.setString(19, "0"); // 运价文件
                 pstmt.setDate(20, fareStartDate);//运价生效日期
                 pstmt.setDate(21, fareEndDate);//运价生效日期
                
                 pstmt.addBatch();// 用PreparedStatement的批量处理  
                 if (count % 5000 == 0) {// 当增加了500个批处理的时候再提交  
                     pstmt.executeBatch();// 执行批处理  
                     conn.commit();  
                     pstmt.clearBatch();  
                     
                 }  
                 count++;  
	        	 
	        	 }  	 
	         }
	         pstmt.executeBatch();// 执行批处理  
             conn.commit();  
             pstmt.close();  
             conn.close();  
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        long timeTestEnd = System.currentTimeMillis();// 记录结束时间  
        long time = timeTestEnd - timeTestStart;  
        long secondTime = time / 1000;  
        System.out.println("Time:" + secondTime + " seconds");
		return (int) secondTime;
		
		
	}

	
	
	

	
	@Override
	public void deleteData() {
		//fdMapper.delete();
		
	}


	

	
	
	
}
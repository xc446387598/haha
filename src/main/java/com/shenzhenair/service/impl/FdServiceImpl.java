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
 * �˼�service
 * 
 * @author ���沩
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
		 int batchCount = 1000;//ÿ��commit�ĸ��� 
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
					 break;//���ݲ������,�˳�ѭ��
				 }
			 }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 System.out.println("���ϵĳ���:"+list.size());
		
		 
		 
		 
		
		return result;
		 
		
	
		
		
	}
	
	public int batchJdbcInsertData(List<String> list){
		long timeTestStart = System.currentTimeMillis();// ��¼��ʼʱ��  
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		
		int count = 1;// ������  
        String lineTXT = null;  
        PreparedStatement pstmt = null;  
        String[] temp = null;  
        Connection conn=null;
        java.sql.Date nowDate = new java.sql.Date(new java.util.Date().getTime());
        try {
			 conn = jdbcTemplate.getDataSource().getConnection();
			 conn.setAutoCommit(false);// ���������ֶ��ύ���Լ���������  
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
	        	 String sDate=temp[18]; //��ʼ���� 
	        	 String eDate=temp[19];//��������
	        	 String fsDate=temp[13];//�˼�ʧЧ����
	        	 String feDate=temp[14];//�˼�ʧЧ����
	        	 
	        	 int priceOw=Integer.parseInt(temp[11]);//���̼۸�
	        	 int priceRt=Integer.parseInt(temp[12]);//�����۸�                         
	        	 java.sql.Date startDate = new java.sql.Date(DateUtil.getDate(sDate)); 
	        	 java.sql.Date endDate = new java.sql.Date(DateUtil.getDate(eDate)); 
	        	 java.sql.Date fareStartDate = new java.sql.Date(DateUtil.getDate(fsDate)); 
	        	 java.sql.Date fareEndDate = new java.sql.Date(DateUtil.getDate(feDate)); 
	        	
	        	 pstmt.setString(1, temp[0]);  //���չ�˾
                 pstmt.setString(2, temp[1]);  //��������
                 pstmt.setString(3, temp[3]);   //�ִ����
                 
                 pstmt.setString(4, temp[8]);    //��λ���� 
                 pstmt.setDate(5, startDate);   //��ʼ���� 
                 pstmt.setDate(6, endDate);    //��������
                 pstmt.setInt(7, priceOw);   //���̼۸�
                 pstmt.setInt(8, priceRt);   //�����۸�
                 pstmt.setNull(9, Types.INTEGER); 
                 pstmt.setString(10, temp[6]);  //��λ�ȼ�
                 pstmt.setString(11, temp[7]);  //����ȼ�
                 pstmt.setString(12, "");    //Fare_basis
                 pstmt.setDate(13, nowDate);  //����ʱ��
                 pstmt.setString(14, "Y");      //״̬ Y/N
                 pstmt.setString(15, "system");  //������
                 pstmt.setInt(16, 0);          //����Ԥ��
                 pstmt.setInt(17, 0);       //����Ԥ��������
                 pstmt.setString(18, "");
                 pstmt.setString(19, "0"); // �˼��ļ�
                 pstmt.setDate(20, fareStartDate);//�˼���Ч����
                 pstmt.setDate(21, fareEndDate);//�˼���Ч����
                
                 pstmt.addBatch();// ��PreparedStatement����������  
                 if (count % 5000 == 0) {// ��������500���������ʱ�����ύ  
                     pstmt.executeBatch();// ִ��������  
                     conn.commit();  
                     pstmt.clearBatch();  
                     
                 }  
                 count++;  
	        	 
	        	 }  	 
	         }
	         pstmt.executeBatch();// ִ��������  
             conn.commit();  
             pstmt.close();  
             conn.close();  
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        long timeTestEnd = System.currentTimeMillis();// ��¼����ʱ��  
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
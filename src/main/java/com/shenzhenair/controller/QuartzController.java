package com.shenzhenair.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.junrar.exception.RarException;
import com.shenzhenair.bean.Fd;
import com.shenzhenair.bean.FdSyn;
import com.shenzhenair.bean.Ftp;
import com.shenzhenair.bean.RequestParam;
import com.shenzhenair.bean.Route;
import com.shenzhenair.mapper.FdMapper;
import com.shenzhenair.service.FdService;
import com.shenzhenair.service.impl.RatesService;
import com.shenzhenair.util.DateUtil;
import com.shenzhenair.util.FtpDownLoad;
import com.shenzhenair.util.FtpUtil;
import com.shenzhenair.util.unrar;

public class QuartzController {
	
	private Log log = LogFactory.getLog(QuartzController.class);
	
	@Resource
	private FdService fdService;
	  
	@Resource
	private FdMapper fdMapper;
	
	public void run() throws IOException{
		log.info("��ʼ����FD����:"+new Date());
		/*Ftp f=new Ftp();
		String ftpUrl=FtpUtil.ftpUrl;
		String ftpUserName =FtpUtil.ftpUserName;
		String ftpPassWord = FtpUtil.ftpPassWord;
		String localPath = FtpUtil.localPath;
		String pdfPath = FtpUtil.pdfPath;
		f.setIpAddr(ftpUrl);
		f.setPath(pdfPath);
		f.setUserName(ftpUserName);
		f.setPwd(ftpPassWord);
		String fileName = "FD"+DateUtil.getDateUtil()+".rar";
		String textName = "FD"+DateUtil.getDateUtil()+".txt";
		File file = new File(localPath+"\\"+textName);
		if(file.exists()){
			log.info("�Ѿ������ļ���"+textName);
			return;
		}
		boolean downFile = FtpDownLoad.downFile(f, fileName, localPath);
		if(downFile){
			System.out.println("���سɹ�");
			log.info("���سɹ�");
		}else{
			System.out.println("����ʧ��");
			log.info("����ʧ��");
			return;
		}
		
		log.info("��ʼִ��ɾ�����ݣ�"+new Date());
		if(downFile){
			
			fdMapper.deleteAll();
			log.info("ɾ��ȫ�����ݳɹ���"+new Date());
			
			
		}
		
		try {
			unrar.unrarload(fileName);
			System.out.println("��ѹ�ɹ�");
		} catch (RarException e1) {
			
			e1.printStackTrace();
		}
		List<String> list = FtpDownLoad.addJdbcText(localPath, textName);
		int result=fdService.batchJdbcInsertData(list);
		log.info("��������ִ�н�����"+new Date());
		log.info("����������ʱ"+result+"��");
		log.info("�ɹ���������:"+list.size()+"��");

		List<FdSyn> list1=fdMapper.queryFlag();
		
		if(list!=null&&list1.size()>0){
			log.info("���˹���������һ��"+list1.size()+"��");
			for(int i=0;i<list1.size();i++){
				
				FdSyn fs=new FdSyn();
				fs.setAirLine(list1.get(i).getAirLine());
				fs.setDePart(list1.get(i).getDePart());
				fs.setArRive(list1.get(i).getArRive());
				fs.setClassCode(list1.get(i).getClassCode());
				fs.setCabin(list1.get(i).getCabin());
				fs.setService(list1.get(i).getService());
				List<FdSyn> queryFlagForUpdate = fdMapper.queryFlagForUpdate(fs);
				if(queryFlagForUpdate.size()>1){
					fdMapper.delete(fs);
					log.info(fs.getAirLine()+"ɾ�����ݳɹ���");
				}
			}
			
			
			log.info("ɾ������ִ�н�����"+new Date());
		}		*/
		 
	}
}

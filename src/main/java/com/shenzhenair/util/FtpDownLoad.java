package com.shenzhenair.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;


import com.github.junrar.exception.RarException;
import com.shenzhenair.bean.FdSyn;
import com.shenzhenair.bean.Ftp;

public class FtpDownLoad {
    private static Logger logger=Logger.getLogger(FtpDownLoad.class);
	
	private static FTPClient ftp;

	//public static void main(String[] args) {
	/*
		Ftp f=new Ftp();
		f.setIpAddr("218.17.237.41");
		f.setPath("/");
		f.setUserName("fd_public");
		f.setPwd("3Vp31LTm");
		String fd = "FD"+DateUtil.getDateUtil();
		boolean downFile=downFile(f,fd,"D:\\ftp");
		if(downFile){
			System.out.println("下载成功");
		}else{
			System.out.println("下载失败");
		}
		try {
			unrar.unrarload();
			System.out.println("解压成功");
			
			
		}  catch (Exception e) {
			
			e.printStackTrace();
		}
		
		try {
			List<String> list=addText();
			System.out.println("读取到数据");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	*/
	
	//}
	public static boolean downFile(
          
            Ftp f, 
			String fileName,//要下载的文件名  
            String localPath//下载后保存到本地的路径 

            ) { 
        boolean success = false;    
         
        try {    
            int reply;    
            boolean connect=connectFtp(f);
            reply = ftp.getReplyCode();    
            if (!FTPReply.isPositiveCompletion(reply)) {    
                ftp.disconnect();    
                return success;    
            }   
           
            ftp.changeWorkingDirectory(f.getPath());//转移到FTP服务器目录     
            FTPFile[] fs = ftp.listFiles();  
           
             for(FTPFile ff:fs){
             System.out.println("FTP上获取的文件" + ff.getSize());
             
                if(ff.getName().equals(fileName)){  
                
                    File localFile = new File(localPath+"/"+ff.getName());    
                    OutputStream is = new FileOutputStream(localFile);     
                    boolean retrieveFile= ftp.retrieveFile(ff.getName(), is);  
                    System.out.println("下载的文件名称为：" +ff.getName()+",结果为"+retrieveFile);
                    success = true;  
                    is.close();    
                }    
            }
             ftp.logout();    
             
        
        } catch (Exception e) {    
           System.out.println("下载出现问题"+e.getMessage());    
        } finally {    
            if (ftp.isConnected()) {    
                try {    
                    ftp.disconnect();    
                } catch (IOException ioe) {
                	
                }    
            }    
        }    
        return success;    
    }
	
	/**
	 * 获取ftp连接
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static boolean connectFtp(Ftp f) throws Exception{
		ftp=new FTPClient();
		boolean flag=false;
		int reply;
		if (f.getPort()==null) {
			ftp.connect(f.getIpAddr(),21);
		}else{
			ftp.connect(f.getIpAddr(),f.getPort());
		}
		ftp.login(f.getUserName(), f.getPwd());
		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		reply = ftp.getReplyCode();      
	    if (!FTPReply.isPositiveCompletion(reply)) {      
	          ftp.disconnect();      
	          return flag;      
	    }      
	    ftp.changeWorkingDirectory(f.getPath());      
	    flag = true;      
	    return flag;
	}
	
	/*public static List<String> addText(String localPath,String fileName) throws IOException{
		File file = new File(localPath+"\\"+fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)));
		String str = null;
		List<String> list = new ArrayList<String>();
		try {
			while ((str = br.readLine()) != null) {
				list.add(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		return list;
	}*/
	public static List<FdSyn> addText(String localPath,String fileName) throws IOException{
		
		File file = new File(localPath+"\\"+fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)));
		String str = null;
		List<FdSyn> list = new ArrayList<FdSyn>();
		FdSyn fd=null;
		try {
			while ((str = br.readLine()) != null) {
				fd=new FdSyn();
				String strsplit[]=str.split(",");
				
				fd.setAirLine(strsplit[0]);
				fd.setDePart(strsplit[1]);
				fd.setArRive(strsplit[2]);
				fd.setClassCode(strsplit[3]);
				fd.setStartDate(strsplit[4]);
				fd.setEndDate(strsplit[5]);
				fd.setPriceOw(strsplit[6]);
				fd.setPriceRt(strsplit[7]);
				fd.setTpm(strsplit[8]);
				fd.setCabin(strsplit[9]);
				fd.setService(strsplit[10]);
				fd.setFareBasis(strsplit[11]);
				fd.setUpdateTime(strsplit[12]);
				fd.setStatus(strsplit[13]);
				fd.setUpdateBy(strsplit[14]);
				fd.setPreDays(strsplit[15]);
				fd.setEndDays(strsplit[16]);
				fd.setFareRule(strsplit[17]);
				//fd.setRemark(strsplit[18]);
				//fd.setNote(strsplit[19]);
				list.add(fd);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		return list;
	}
	
	
	
	
	
	
   public static List<String> addJdbcText(String localPath,String fileName) throws IOException{
		
		File file = new File(localPath+"\\"+fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)));
		String str = null;
		String[] temp=null;
		List<String> list = new ArrayList<String>();
		FdSyn fd=null;
		try {
			while ((str = br.readLine()) != null) {
				list.add(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		return list;
	}
	

	
	
	public static void main(String[] args) throws IOException {
		List<FdSyn> addText = addText("D:\\ftp","FD20161230.txt");
		System.out.println(addText.size());
	}
}



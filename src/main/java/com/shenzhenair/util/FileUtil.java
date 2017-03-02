package com.shenzhenair.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * 对文件的简单操作
 * @author <b><font color="red">service</font></b> {@link service@shenzhenair.com}
 */
public class FileUtil {

	private static final Map<Class<?>,String> CLASS_MAP= new Hashtable<Class<?>, String>();
	private final static Log log = LogFactory.getLog(FileUtil.class);
	private final static DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
	private final static DateFormat FORMAT_SSSS =  new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
	private final static String PATH = new File("/").getAbsolutePath();
	private Class<?>classType = null;
	
	public FileUtil(Class<?>classType,String fileName){
		this.classType = classType;
		if(CLASS_MAP.containsKey(classType)){
			if(CLASS_MAP.get(classType).length()>fileName.length()){
				CLASS_MAP.put(classType, fileName);
			}
		}else{
			CLASS_MAP.put(classType, fileName);
		}
		
	}
	
	public FileUtil(Class<?>classType){
		this(classType,classType.getName());
	}
	
	/**
	 * 在指定路径创建文件夹
	 * @param path 路径
	 * @param directoryName 文件夹名称
	 * @return
	 */
	public static boolean createDirectory(String path, String directoryName)
	{
		boolean bool = false;
		try {
			File file = new File(path + "\\" + directoryName);
			bool = (file.exists() || file.mkdirs()) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	/**
	 * 在指定路径写入文件
	 * @param path 路径
	 * @param fileName 文件名
	 * @param str 文件内容
	 * @return
	 */
	public static boolean createFile(String path, String fileName, String msg)
	{
		boolean bool = false;
		try {
			File file = new File(path);
			file.mkdirs();
			FileWriter fw = new FileWriter(new File(path + "\\" + fileName));
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(msg);
			bw.flush();
			fw.flush();
			bw.close();
			fw.close();
			bool = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	/**
	 * 在指定路径写入xml文件
	 * @param path
	 * @param fileName
	 * @param msg
	 * @return
	 */
	public static boolean createFileForXml(String path, String fileName, Document doc)
	{
		boolean bool = false;
		try {
			File file = new File(path);
			file.mkdirs();
			
			//格式化输出，缩进并使用UTF-8编码
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			
			OutputStream output = new FileOutputStream(new File(path + "\\" + fileName));
			XMLWriter xmlWrite = new XMLWriter(output, format);
			xmlWrite.setEscapeText(false);
			xmlWrite.write(doc);
			xmlWrite.flush();
			xmlWrite.close();
			output.flush();
			output.close();
			bool = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	/**
	 * 复制指定路径的文件或文件夹
	 * @param path 源路径
	 * @param fileName 文件名
	 * @param topath 要复制的路径
	 * @return
	 */
	public static boolean copy(String path, String fileName, String topath)
	{
		boolean bool = false;
		try {
			File file = new File(path + "\\" + fileName);
			if (file.isFile()) {
				FileInputStream fis = new FileInputStream(file);
				FileOutputStream fos = new FileOutputStream(topath + "\\" + fileName);
				byte []b = new byte[1024];
				int i = -1;
				while (-1 != (i = fis.read(b))) {
					fos.write(b, 0, i);
				}
				fos.flush();
				fos.close();
				fis.close();
				bool = true;
			}
			if (file.isDirectory()) {
				File tofile = new File(topath + "\\" + fileName);
				if (tofile.exists() || tofile.mkdirs()) {
					File []childFiles = file.listFiles();
					for(int i=0;i<childFiles.length;i++)
					{
						File cfile=childFiles[i];
						boolean b = FileUtil.copy(cfile.getParent(), cfile.getName(), topath + "\\" + fileName);
						if (!b) {
							log.info("复制文件失败...");
							return false;
						}
					}
					bool = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	public static void appendline(String filename,String str)
	{
		append(filename,str+"\r\n");
	}
	
	public void appendError(Throwable t){
		appendMsg(t, "[ERROR]",CLASS_MAP.get(this.classType));
	}
	
	public void appendInfo(Throwable t){
		appendMsg(t, "[INFOR]",CLASS_MAP.get(this.classType));
	}
	
	private static void appendMsg(Throwable t,String leve,String className){
		 Date date = new Date();
		 
		 StringBuilder messageBuilder = new StringBuilder();
		 StackTraceElement element = t.getStackTrace()!=null && t.getStackTrace().length>0?t.getStackTrace()[0]:null;
		 
		 if(element != null){
			 String simpleClassName  =  className.indexOf('.')>0?className.substring(className.lastIndexOf('.')+1):className;
			 String fileName = messageBuilder.append(PATH).append("/").append(simpleClassName).append("/").
			 						append(simpleClassName).append("_").append(format.format(date)).
			 						append(".txt").toString();
			
			 messageBuilder.delete(0, messageBuilder.length());
			 String fullMessage = messageBuilder.append("【").append(FORMAT_SSSS.format(date)).append("】 ").append(") |").append(t.getMessage()).toString();
			 appendline(fileName, fullMessage);
		 }
	}
	
	/*
	 * append string to file
	 * 
	 * @filename
	 *   file real path and name
	 * @str
	 *   string want append
	 */	
	public static void append(String filename,String str)
	{
		File f = new File(filename);
		if(!f.exists())
		{
			MDebug.infoln("//Warning: [" + filename + "] not found. ");
			try 
			{
				String[]dirs = filename.split("/\\\\|\\\\/|\\\\|/");
				if(dirs.length>1){
					StringBuilder stringBuilder = new StringBuilder();
					for (int i = 0; i < dirs.length-1; i++) {
						stringBuilder.append(dirs[i]).append("/");
					}
					new File(stringBuilder.toString()).mkdirs();
				}
				
				
				if(f.createNewFile())
				{
					MDebug.debugln("//Warning: [" + filename + "]created. ");					
				}				
			} catch (IOException e) {
				MDebug.infoln("//Exception: " + e.toString());
			}
		}
		
		if(!f.canWrite())
		{
			MDebug.infoln("//Error: [" + filename + "] can not write. ");
			return;
		}
		
		try {
			FileOutputStream fos = new FileOutputStream(f, true);
			fos.write(str.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
	}	
}
package com.shenzhenair.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;

public class unrar {

	
	 
	 public static void unrarload(String fileName) throws RarException, IOException{
		 //ѹ���ļ�

		 
		// String rarPath = "D:\\ftp\\FD20161230.rar";
		 String rarPath = "D:\\ftp\\"+fileName;
		 //��ѹ�����Ŀ¼

		 String dstDirectoryPath = "D:\\ftp";

		 File dstDiretory = new File(dstDirectoryPath); 

		        if (!dstDiretory.exists()) {

		         dstDiretory.mkdirs();             

		        }  

		 Archive a = new Archive(new File(rarPath));                

		 if (a != null) {  

		 a.getMainHeader().print(); //��ӡ�ļ���Ϣ.                    

		 FileHeader fh = a.nextFileHeader();                     

		 while (fh != null) {                              

		 //�ļ�                                  

		 File out = new File(dstDirectoryPath + File.separator + fh.getFileNameString().trim());

		 System.out.println(out.getAbsolutePath());                                 

		 FileOutputStream os = new FileOutputStream(out);                                     

		 a.extractFile(fh, os);                                     

		 os.close();

		 fh = a.nextFileHeader();

		 }

		 }

		 a.close();
	 }
}

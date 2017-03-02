

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.junrar.exception.RarException;
import com.shenzhenair.bean.FdSyn;
import com.shenzhenair.bean.Ftp;
import com.shenzhenair.mapper.FdMapper;
import com.shenzhenair.service.FdService;
import com.shenzhenair.service.impl.FdServiceImpl;
import com.shenzhenair.service.impl.RatesService;
import com.shenzhenair.util.DateUtil;
import com.shenzhenair.util.FtpDownLoad;
import com.shenzhenair.util.FtpUtil;
import com.shenzhenair.util.unrar;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mvc.xml"})
public class MybaitisDB  {
	
	private Log log = LogFactory.getLog(FdServiceImpl.class);

	@Resource
	private FdService fdService;
	
	@Resource
    private FdMapper fdMapper;

	/*@Test
   public void save() throws Exception {
		long start = System.currentTimeMillis();
		System.out.println("------------");
		String localPath = FtpUtil.localPath;
		//String fileName = "FD"+DateUtil.getDateUtil()+".rar";
		String fileName ="FD20161230.txt";
		try {
			//unrar.unrarload();
			System.out.println("解压成功");
		} catch (RarException e1) {
			
			e1.printStackTrace();
		}
		
		List<FdSyn> list = FtpDownLoad.addText(localPath, fileName);
		for (FdSyn s:list) {
			int seq=fdService.getSeq();
			s.setId(Long.valueOf(seq));
		}
		
		
		//List queryData = fdService.batchInsertData(uidCodeList);
		
		//System.out.print("%%%%%%%%%"+list.get(80).getAirLine());
		
		long end =System.currentTimeMillis();
		
		int result=fdService.batchInsertData(list);
		System.out.println("用时"+(end-start)/1000+"秒");
		System.out.println("成功插入数据:"+list.size()+"条");

		
	
	
	
	}*/
	
	
	   @Test
	   public void savejdbc() throws Exception {
			
			System.out.println("------------");
			log.info("开始执行："+new Date());
			//fdMapper.delete();
			//log.info("删除数据成功！");
			String localPath = FtpUtil.localPath;
			
			String fileName ="FD20170106.txt";
			
			
			/*Ftp f=new Ftp();
			String ftpUrl=FtpUtil.ftpUrl;
			String ftpUserName =FtpUtil.ftpUserName;
			String ftpPassWord = FtpUtil.ftpPassWord;
			//String localPath = FtpUtil.localPath;
			String pdfPath = FtpUtil.pdfPath;
			f.setIpAddr(ftpUrl);
			f.setPath(pdfPath);
			f.setUserName(ftpUserName);
			f.setPwd(ftpPassWord);
			//String fileName = "FD"+DateUtil.getDateUtil()+".rar";
			String textName = "FD"+DateUtil.getDateUtil()+".txt";
			File file = new File(localPath+"\\"+fileName);
			if(file.exists()){
				log.info("已经存在文件："+textName);
				return;
			}
			boolean downFile = FtpDownLoad.downFile(f, fileName, localPath);
			if(downFile){
				System.out.println("下载成功");
			}else{
				System.out.println("下载失败");
				return;
			}
			try {
				unrar.unrarload(fileName);
				System.out.println("解压成功");
			} catch (RarException e1) {
				
				e1.printStackTrace();
			}*/
			 
			String textName = "FD20170112.txt";
			List<String> list = FtpDownLoad.addJdbcText(localPath, textName);

			
			List<FdSyn> list1=fdMapper.queryFlag();
			
			if(list!=null&&list1.size()>0){
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
						log.info(fs.getAirLine()+"删除数据成功！");
					}
					
				}
				
			}
			
			
			
			
			
		
			
			
			
			
			
			int result=fdService.batchJdbcInsertData(list);
			System.out.println("插入数据用时"+result+"秒");
			System.out.println("成功插入数据:"+list.size()+"条");
			log.info("执行结束："+new Date());
			log.info("插入数据用时"+result+"秒");
			log.info("成功插入数据:"+list.size()+"条");
		
		
		
		}
	
	


}

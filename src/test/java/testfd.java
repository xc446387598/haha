import com.shenzhenair.bean.Ftp;
import com.shenzhenair.util.DateUtil;
import com.shenzhenair.util.FtpDownLoad;
import com.shenzhenair.util.FtpUtil;


public class testfd {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test();

	}
	public static void test(){
		Ftp f=new Ftp();
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
		boolean downFile = FtpDownLoad.downFile(f, fileName, localPath);
		if(downFile){
			System.out.println("���سɹ�");
		}else{
			System.out.println("����ʧ��");
			return;
		}
	}

}

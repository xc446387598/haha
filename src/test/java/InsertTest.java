import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fusesource.hawtbuf.BufferOutputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shenzhenair.bean.Fd;
import com.shenzhenair.bean.RequestParam;
import com.shenzhenair.bean.Route;
import com.shenzhenair.mapper.FdMapper;
import com.shenzhenair.service.IRatesService;
import com.shenzhenair.service.impl.RatesService;

/**
 * µ¥Ôª²âÊÔ
 *  
 * @author ºúÒæ²©
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mvc.xml" })
public class InsertTest {

	private Log log = LogFactory.getLog(InsertTest.class);
	@Resource
	private IRatesService ratesService;
	@Resource
	private FdMapper fdMapper;

	@Test
	public  void run() throws IOException {/*
		File file = new File("airline.txt");
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
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String now = simpleDateFormat.format(date);
		for (String airline : list) {
			RequestParam rrParam = new RequestParam();
			rrParam.setFrom(airline.split("-")[0]);
			rrParam.setArrive(airline.split("-")[1]);
			rrParam.setFdDate("2016-11-07");
			rrParam.setCarrier("ZH");
			try {
				List<Route> rList = ratesService.getRates(rrParam);
				ratesService.insert(rList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	*/
		//List<Fd> queryAirline = fdMapper.queryAirline();
		System.out.println("-----");
	}
}

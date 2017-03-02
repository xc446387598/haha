

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.shenzhenair.bean.RequestParam;
import com.shenzhenair.service.impl.RatesService;
import com.shenzhenair.util.DateUtil;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {/*
		GregorianCalendar gca =new GregorianCalendar();
		GregorianCalendar gcTicket =new GregorianCalendar();
		GregorianCalendar gcb =new GregorianCalendar();
		SimpleDateFormat sf  =new SimpleDateFormat("yyyy-MM-dd");
		gca.setTime(new Date());
		gcb.setTime(new Date());
		int i = 7;
		i=i-1;
		int ii = 10;
		ii=ii-1;
		gca.add(5,i);
		gca.set(gca.get(Calendar.YEAR),gca.get(Calendar.MONTH),gca.get(Calendar.DATE));
		gcb.add(5,ii);
		gcb.set(gcb.get(Calendar.YEAR),gcb.get(Calendar.MONTH),gcb.get(Calendar.DATE));
		try {
			gcTicket.setTime(sf.parse("2016-11-20"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(gca.getTimeInMillis() < gcTicket.getTimeInMillis() && gcb.getTimeInMillis() > gcTicket.getTimeInMillis()){
			System.out.println(44);
		}
	*/
		String dateUtil = DateUtil.getDateUtil();
	    System.out.println(dateUtil);
	    
	
	}

}

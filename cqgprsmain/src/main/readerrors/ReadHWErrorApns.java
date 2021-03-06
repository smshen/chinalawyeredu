package main.readerrors;

import java.io.InputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import main.util.DBUtils;

import org.apache.commons.logging.Log;

/**
 * 
 */

/**
 * 
 * 读华为的错误代码情况
 * 
 * @author 华锋 Nov 4, 2009-9:21:28 PM
 * 
 */
public class ReadHWErrorApns {
	
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyyMMdd");
	/**
	 * 处理前放文件的目录
	 */
	private static String SRCDIR = "d:\\home";
	/**
	 * 处理完毕后的备份目录
	 */
	private static String DESTDIR = "d:\\bak";

	public static String SGSNCMD="/export/home/jf/sgsnbill/sgsnbill";
//	private static Log LOG = org.apache.commons.logging.LogFactory.getLog(ReadHWErrorApns.class);


	// public static void errorlog(String msg) {
	// ERRORLOG.println(df.format(new Date()) + "=>" + msg);
	// }

	private static void init() {
		InputStream in = null;
		try {
			in = ReadHWErrorApns.class.getResourceAsStream("/main/readerrors/readhw.properties");
			Properties prop = new Properties();
			prop.load(in);
			SRCDIR = prop.getProperty("SRCDIR");
			DESTDIR = prop.getProperty("DESTDIR");
			SGSNCMD = prop.getProperty("SGSNCMD");
		} catch (Exception e) {
			try {
				if (in != null)
					in.close();
			} catch (Exception ee) {
				// LOG.error("关闭ice.properties异常:" + e);
				e.printStackTrace();
			}
		}
	}



	/**
	 * <pre>
	 * 处理流程: 
	 * 1.到原目录下copy所有文件到某个目录下,每天1个目录,记录下这些个文件copy后的路径
	 * 2.上面copy文件的时候,以休息100毫秒的形式判断这个文件是否有改动,如果有,记录下来,处理完其他的后再处理这个
	 * 3.copy过后的文件名,将之前文件所在的目录带入,同时加入此次处理的时间戳
	 * 4.处理之前,判断原文件名+sgsn号是否已经处理过,如果处理过,拿到行数后忽略掉,否则从0开始
	 * 5.处理完毕后,将之前的都删掉,将处理过的都入库,字段为id,sgsnid,filename,System.currentMillis()
	 * 6.处理时间定为每10分钟1次 
	 * 7.先通过crontab的方式执行
	 * 
	 * </pre>
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		Connection con = null;
		System.out.println("============================================================");
		long begin = System.currentTimeMillis();
		
		String today=args[0]; //对应的目录
		String flag=args[1]; //此次的标志
		
		System.out.println(today+"======"+flag);
	
		try {
//			init();
			HWChrFileHandle filehandle = new HWChrFileHandle();
			
			String destdir="/export/home1/GPRS/JAVABIN/files/chrdest/hw"+today;
			
			Map<String, ErrorFile> mapfiles = filehandle.getHWFile(destdir,flag);
			System.out.println("本次获取的文件个数为：：："+mapfiles.size());
			con = DBUtils.getOracleCon();
			System.out.println("成功获取到数据库连接OK");
			System.out.println("mapfiles.size():" + mapfiles.size());
			
			int weekday = getWeekOfDay();//今天星期几
			int thehour = getNowHour();//现在几点
			String thedate = getNowDate();//今天哪天
			if (thehour == 0) {//如果现在是0点的话，则数据都入库到昨天的表里面，同时要清掉上周今天的数据,并同时统计好今天的按天数据，即group by sgsnid,kuang,cao,flowid的总数
				weekday = getYestardayWeekOfDay();// 0点跑的程序，插入到昨天的数据里面去
				// 这里要清掉表的数据
				int _today = getWeekOfDay();
				String deleteTable = "hwchr_log_" + _today; // truncate 掉这个table
				filehandle.truncateTable(deleteTable);// 执行truncate..同时这里要统计当天的所有程序数据
				thehour = 23;
				thedate = getYestardayDate();
				filehandle.statFlowidDay(con, thedate); //统计一天的数据情况
					

			}else{
				thehour=thehour-1; //统计的都是前1个小时的数据啊
			}
			
			System.out.println("时间:"+thehour+",日期:"+thedate+",星期:"+weekday);
			
			
			if (mapfiles.size() > 0) {

				ReadHandleHistory readHistory = new ReadHandleHistory(con);
//				readHistory.getFromDB(mapfiles);
				readHistory.getFromCHRDB(mapfiles);
				long now = System.currentTimeMillis();
				java.util.Iterator<ErrorFile> files = mapfiles.values().iterator();
				List<HWChrLog> cdrs = null;
				while (files.hasNext()) {

					ErrorFile file = files.next();
					cdrs = filehandle.parseHWChrFile(file);
					
					System.out.println(df.format(new Date())+"=>cdrs.size():::"+cdrs.size());
					
					filehandle.saveHWChr(con,cdrs, weekday, thehour, thedate);

					System.out.println("解析并存储" + file.getSgsnid() + "_" + file.getSrcfilename() + "所花时间:"
							+ (System.currentTimeMillis() - now));
					now = System.currentTimeMillis();
				}

				// 然后这里入库，也就是数据
				readHistory.saveLatestErrors(mapfiles);
				
				filehandle.saveFlowidHourDatas(con, thedate, thehour);
				filehandle.saveFlowidErrorHourDatas(con, thedate, thehour);
				System.out.println("所有文件处理完毕!!!" + (System.currentTimeMillis() - begin));

			} else {
				System.out.println("目录下无文件,跳过处理...");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("处理异常:::"+e);
		} finally {
			DBUtils.closeConnection(con);
		}
	}
	
	/**
	 * 返回当前是星期几,星期日的话为0
	 * 
	 * @return
	 */
	private static int getWeekOfDay() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 
	 * @return
	 */
	private static int getYestardayWeekOfDay() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, -24);
		return c.get(Calendar.DAY_OF_WEEK) - 1;
	}

	private static int getNowHour() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}

	private static String getNowDate() {
		return dfdate.format(new Date());
	}

	private static String getYestardayDate() {
		return dfdate.format(new java.sql.Timestamp(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
	}
	

	public static void test(String[] args) {
		FileHandle filehandle = new FileHandle();
		Map<String, ErrorFile> mapfiles = filehandle.copyFile("d:\\home", "d:\\bak");
		java.util.Iterator<ErrorFile> files = mapfiles.values().iterator();

		while (files.hasNext()) {
			ErrorFile file = files.next();
			System.out.println(file.getDestfile().getName() + ",,," + file.getSgsnid() + ",,," + file.getSrcfilename());
		}
	}

}

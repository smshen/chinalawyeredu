/**
 * 
 */
package com.sxit.stat.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.sxit.common.PaginationSupport;
import com.sxit.stat.models.ApnCellStatModel;
import com.sxit.stat.models.ApnStatModel;
import com.sxit.stat.models.BsnRncStatModel;
import com.sxit.stat.models.CellStatModel;
import com.sxit.stat.models.NsvcStatModel;
import com.sxit.stat.models.SgsnStatModel;
import com.sxit.stat.models.SubCellStatModel;
import com.sxit.stat.models.TotalStatModel;
import com.sxit.stat.util.StatUtil;

/**
 * @author 华锋 Oct 16, 2009-4:54:14 PM
 * 
 */
public class StatService {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfyyyyMMdd = new java.text.SimpleDateFormat("yyyyMMdd");
	private static final DateFormat dftime = new java.text.SimpleDateFormat("HH:00");
	// private static final DateFormat dfyyyyMmddHHmmss = new
	// java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static List<String> ALL_TIME_LIST = new ArrayList<String>();
	public static Map<String, String> ALL_HOUR_LIST = new LinkedHashMap<String, String>();

	static {
		// ALL_TIME_LIST.add("00:00");
		ALL_TIME_LIST.add("01:00");
		ALL_TIME_LIST.add("02:00");
		ALL_TIME_LIST.add("03:00");
		ALL_TIME_LIST.add("04:00");
		ALL_TIME_LIST.add("05:00");
		ALL_TIME_LIST.add("06:00");
		ALL_TIME_LIST.add("07:00");
		ALL_TIME_LIST.add("08:00");
		ALL_TIME_LIST.add("09:00");
		ALL_TIME_LIST.add("10:00");
		ALL_TIME_LIST.add("11:00");
		ALL_TIME_LIST.add("12:00");
		ALL_TIME_LIST.add("13:00");
		ALL_TIME_LIST.add("14:00");
		ALL_TIME_LIST.add("15:00");
		ALL_TIME_LIST.add("16:00");
		ALL_TIME_LIST.add("17:00");
		ALL_TIME_LIST.add("18:00");
		ALL_TIME_LIST.add("19:00");
		ALL_TIME_LIST.add("20:00");
		ALL_TIME_LIST.add("21:00");
		ALL_TIME_LIST.add("22:00");
		ALL_TIME_LIST.add("23:00");

		// ALL_HOUR_LIST.put("00", "00:00");
		ALL_HOUR_LIST.put("01", "01:00");
		ALL_HOUR_LIST.put("02", "02:00");
		ALL_HOUR_LIST.put("03", "03:00");
		ALL_HOUR_LIST.put("04", "04:00");
		ALL_HOUR_LIST.put("05", "05:00");
		ALL_HOUR_LIST.put("06", "06:00");
		ALL_HOUR_LIST.put("07", "07:00");
		ALL_HOUR_LIST.put("08", "08:00");
		ALL_HOUR_LIST.put("09", "09:00");
		ALL_HOUR_LIST.put("10", "10:00");
		ALL_HOUR_LIST.put("11", "11:00");
		ALL_HOUR_LIST.put("12", "12:00");
		ALL_HOUR_LIST.put("13", "13:00");
		ALL_HOUR_LIST.put("14", "14:00");
		ALL_HOUR_LIST.put("15", "15:00");
		ALL_HOUR_LIST.put("16", "16:00");
		ALL_HOUR_LIST.put("17", "17:00");
		ALL_HOUR_LIST.put("18", "18:00");
		ALL_HOUR_LIST.put("19", "19:00");
		ALL_HOUR_LIST.put("20", "20:00");
		ALL_HOUR_LIST.put("21", "21:00");
		ALL_HOUR_LIST.put("22", "22:00");
		ALL_HOUR_LIST.put("23", "23:00");

	}

	// private long getDayStartTime(Date date) {
	// return com.sxit.stat.util.StatUtil.getDateTime(date);
	// }

	private JdbcTemplate jdbcTemplate;

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * <pre>
	 * 得到一段时间之内的总流量、总用户数、以及平均流量、按天计算
	 * 这里要保证1天的记录都是唯一的,也就是stattime以及systemtype
	 * 总流量分析
	 * </pre>
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public List getDaysTotalStream(Date start, Date end) {
		int _from = Integer.parseInt(dfyyyyMMdd.format(start));
		int _to = Integer.parseInt(dfyyyyMMdd.format(end));
		String sql = "select stattime,sum(USERCOUNT) as USERCOUNT,sum(ALLVOLUME) as ALLVOLUME,sum(UPVOLUME) as UPVOLUME,sum(DOWNVOLUME) as DOWNVOLUME,STATTIME  from  STAT_ALLDAY where STATTIME between "
				+ _from + " and " + _to + " group by STATTIME";
		// int _from = (int) (start.getTime() / 1000);
		// int _to = (int) end.getTime() / 1000;

		System.out.println(sql);

		Object[] args = new Object[] { _from, _to };
		int[] argTypes = new int[] { Types.INTEGER, Types.INTEGER };
		// Object object = jdbcTemplate.query(sql, args, argTypes, new
		// ResultSetExtractor() {
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					TotalStatModel model = new TotalStatModel();
					int usercount = rs.getInt("USERCOUNT");

					// System.out.println("rs.getDouble(\"USERCOUNT\"):::"+rs.getDouble("USERCOUNT"));

					double all = rs.getDouble("ALLVOLUME");

					// System.out.println("all:::"+all);

					int stattime = rs.getInt("STATTIME");
					// Date date = new Date();
					// date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setUpvolume(rs.getDouble("upvolume"));
					model.setDownvolume(rs.getDouble("downvolume"));
					model.setTotalUser((int) (usercount));
					// System.out.println("model.getTotalUser()::"+model.getTotalUser());
					// model.setDate(df.format(date));
					model.setDate(stattime + ""); // 总数的按天统计，时间都是20091011的形式
					list.add(model);
				}
				return list;
			}
		});

		return (List) object;
	}

	/**
	 * 同上,只是将数据拆分成2，3g 两种形式 23g的流量分析
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public List getDaysTotalStream23g(Date start, Date end) {
		String sql = "select stattime,NETTYPE,sum(USERCOUNT) as USERCOUNT,sum(ALLVOLUME) as ALLVOLUME,sum(UPVOLUME) as UPVOLUME,sum(DOWNVOLUME) as DOWNVOLUME,STATTIME  from  STAT_ALLDAY  where STATTIME between ? and ? group by stattime,NETTYPE";
		// int _from = (int) (start.getTime() / 1000);
		// int _to = (int) end.getTime() / 1000;
		int _from = Integer.parseInt(dfyyyyMMdd.format(start));
		int _to = Integer.parseInt(dfyyyyMMdd.format(end));
		Object[] args = new Object[] { _from, _to };
		int[] argTypes = new int[] { Types.INTEGER, Types.INTEGER };
		Object object = jdbcTemplate.query(sql, args, argTypes, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					TotalStatModel model = new TotalStatModel();
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					int stattime = rs.getInt("STATTIME");
					String type = rs.getString("NETTYPE");
					// Date date = new Date();
					// date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setUpvolume(rs.getDouble("upvolume"));
					model.setDownvolume(rs.getDouble("downvolume"));
					// model.setTotalUser(usercount);
					model.setTotalUser((int) (usercount));
					// model.setDate(df.format(date));
					model.setDate(stattime + ""); // 总数的按天统计，时间都是20091011的形式
					model.setNettype(type);
					list.add(model);
				}
				return list;
			}
		});
		return (List) object;
	}

	/**
	 * 
	 * 得到某一天的sgsn流量,其实可以分时的方式,得到每个小时的流量
	 * 
	 * @param date
	 * @return
	 */
	public List getDaySgsnStream(Date date) {
		final int _date = Integer.parseInt(dfyyyyMMdd.format(date));
		String sql = "select SGSNID,sum(USERCOUNT) as USERCOUNT,sum(upvolume) as upvolume,sum(downvolume) as downvolume,sum(ALLVOLUME) as ALLVOLUME  from  STAT_SGSN where dayflag=1  and ggsnid!='GGSN其他' and STATTIME="
				+ _date + " group by sgsnid ";

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					SgsnStatModel model = new SgsnStatModel();
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					// int stattime = rs.getInt("STATTIME");
					String sgsnid = rs.getString("SGSNID");
					// Date date = new Date();
					// date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setUpvolume(rs.getDouble("upvolume"));
					model.setDownvolume(rs.getDouble("downvolume"));
					// model.setTotalUser(usercount);
					model.setTotalUser((int) (usercount));
					// model.setDate(df.format(date));
					model.setDate(_date + "");
					model.setSgsnid(sgsnid);
					list.add(model);
				}
				return list;
			}
		});

		return (List) object;
	}

	/**
	 * 
	 * 得到某一天的sgsn流量,其实可以分时的方式,得到每个小时的流量
	 * 
	 * @param date
	 * @return
	 */
	public List getDaySgsnStream23g(Date date) {
		final int _date = Integer.parseInt(dfyyyyMMdd.format(date));
		String sql = "select SGSNID,sum(USERCOUNT) as usercount,sum(ALLVOLUME) as allvolume,sum(upvolume) as upvolume,sum(downvolume) as downvolume,NETTYPE from  STAT_SGSN where dayflag=1 and ggsnid!='GGSN其他' and STATTIME="
				+ _date + " group by sgsnid,NETTYPE";
		System.out.println(sql);

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					SgsnStatModel model = new SgsnStatModel();
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					// int stattime = rs.getInt("STATTIME");
					String sgsnid = rs.getString("SGSNID");
					model.setTotalStream(all);
					model.setTotalUser((int) (usercount));
					model.setDate(_date + "");
					model.setSgsnid(sgsnid);
					model.setUpvolume(rs.getDouble("upvolume"));
					model.setDownvolume(rs.getDouble("downvolume"));
					model.setNettype(rs.getString("NETTYPE"));
					list.add(model);
				}
				return list;
			}
		});
		return (List) object;
	}

	/**
	 * 
	 * @param date
	 * @param sgsnid
	 * @param ggsnid
	 * @param orderby
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PaginationSupport getOtherGgsnStats(Date date, String sgsnid, String ggsnid, String orderby, int pageNo,
			int pageSize) {

		String where = "";
		if (!(sgsnid == null || sgsnid.equals("")))
			where = " and sgsnid='" + sgsnid + "'";
		if (!(ggsnid == null || ggsnid.equals("")))
			where = " and ggsnid='" + ggsnid + "'";
		final String _date = dfyyyyMMdd.format(date);
		int startIndex = (pageNo - 1) * pageSize;
		String sql = "select * from(select a.*,rownum rn from(select * from  STAT_SGSN where ggsnid not like 'GGSN%' "
				+ where + " and dayflag=1 and STATTIME=" + _date + orderby + ") a " + " where rownum<="
				+ (startIndex + pageSize) + ") where rn>" + startIndex;

		System.out.println(sql);
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();

				while (rs.next()) {
					SgsnStatModel model = new SgsnStatModel();
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					String sgsnid = rs.getString("SGSNID");
					String ggsnid = rs.getString("ggsnid");
					model.setTotalStream(all);
					model.setTotalUser((int) (usercount));
					model.setDate(_date + "");
					model.setSgsnid(sgsnid);
					model.setUpvolume(rs.getDouble("upvolume"));
					model.setDownvolume(rs.getDouble("downvolume"));
					model.setGgsnid(ggsnid);
					model.setApnni(rs.getString("apnni"));
					model.setNettype(rs.getString("nettype"));
					list.add(model);
				}

				return list;
			}
		});
		List list = (List) object;
		int totalCount = 0;
		if (pageSize == Integer.MAX_VALUE) {
			totalCount = list.size();
		} else {
			String cntsql = "select count(*) from stat_sgsn where ggsnid not like 'GGSN%' " + where
					+ " and dayflag=1 and stattime=" + _date;
			totalCount = jdbcTemplate.queryForInt(cntsql);
		}
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

	/**
	 * 
	 * 得到某一天的sgsn流量,分ggsn和apn
	 * 
	 * @param date
	 * @return
	 */
	public PaginationSupport getDaySgsnStreamGgsnApn(Date date, String sgsnid, String orderby, int pageNo, int pageSize) {

		String where = "";
		if (!(sgsnid == null || sgsnid.equals("")))
			where = " and sgsnid='" + sgsnid + "'";
		final int _date = Integer.parseInt(dfyyyyMMdd.format(date));
		int startIndex = (pageNo - 1) * pageSize;
		String sql = "select * from(select a.*,rownum rn from(select SGSNID,sum(USERCOUNT) as usercount1,sum(ALLVOLUME) as allvolume1,sum(upvolume) as upvolume1,sum(downvolume) as downvolume1,ggsnid,apnni from  STAT_SGSN where ggsnid like 'GGSN%' "
				+ where
				+ " and dayflag=1 and STATTIME="
				+ _date
				+ " group by sgsnid,ggsnid,apnni"
				+ orderby
				+ ") a "
				+ " where rownum<=" + (startIndex + pageSize) + ") where rn>" + startIndex;

		System.out.println(sql);
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();

				Map<String, Integer> __sgsnid = new HashMap<String, Integer>();
				Map<String, Integer> __ggsnid = new HashMap<String, Integer>();
				while (rs.next()) {
					SgsnStatModel model = new SgsnStatModel();
					int usercount = rs.getInt("USERCOUNT1");
					double all = rs.getDouble("ALLVOLUME1");
					String sgsnid = rs.getString("SGSNID");
					String ggsnid = rs.getString("ggsnid");
					String thetemp = sgsnid + ggsnid;
					if (__sgsnid.containsKey(sgsnid)) {
						model.setSgsnidtr(false);
						int s = __sgsnid.get(sgsnid);
						__sgsnid.remove(sgsnid);
						__sgsnid.put(sgsnid, s + 1);
					} else {
						__sgsnid.put(sgsnid, 1);
					}
					if (__ggsnid.containsKey(thetemp)) {
						model.setGgsnidtr(false);
						int s = __ggsnid.get(thetemp);
						__ggsnid.remove(thetemp);
						__ggsnid.put(thetemp, s + 1);
					} else {
						__ggsnid.put(thetemp, 1);
					}

					model.setTotalStream(all);
					model.setTotalUser((int) (usercount));
					model.setDate(_date + "");
					model.setSgsnid(sgsnid);
					model.setUpvolume(rs.getDouble("upvolume1"));
					model.setDownvolume(rs.getDouble("downvolume1"));
					model.setGgsnid(ggsnid);
					model.setApnni(rs.getString("apnni"));
					list.add(model);
				}
				for (int i = 0; i < list.size(); i++) {
					SgsnStatModel model = (SgsnStatModel) list.get(i);
					model.setSgsnidrowspan(__sgsnid.get(model.getSgsnid()));
					model.setGgsnidrowspan(__ggsnid.get(model.getSgsnid() + model.getGgsnid()));
				}
				__ggsnid.clear();
				__ggsnid.clear();

				return list;
			}
		});
		List list = (List) object;
		int totalCount = 0;
		if (pageSize == Integer.MAX_VALUE) {
			totalCount = list.size();
		} else {
			String cntsql = "select count(count(*)) from stat_sgsn where ggsnid like 'GGSN%' " + where
					+ " and dayflag=1 and stattime=" + _date + " group by sgsnid,ggsnid,apnni";
			totalCount = jdbcTemplate.queryForInt(cntsql);
		}

		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

	/**
	 * 
	 * 得到某一天的sgsn流量,分ggsn和apn,不进行分页
	 * 
	 * @param date
	 * @return
	 */
	public List getDaySgsnStreamGgsnApn(Date date) {

		final String _date = dfyyyyMMdd.format(date);

		String sql = "select SGSNID,sum(USERCOUNT) as usercount1,sum(ALLVOLUME) as allvolume1,sum(upvolume) as upvolume1,sum(downvolume) as downvolume1,ggsnid,apnni from  STAT_SGSN where ggsnid like 'GGSN%' and dayflag=1 and STATTIME="
				+ _date + " group by sgsnid,ggsnid,apnni";

		System.out.println(sql);
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();

				while (rs.next()) {
					SgsnStatModel model = new SgsnStatModel();
					int usercount = rs.getInt("USERCOUNT1");
					double all = rs.getDouble("ALLVOLUME1");
					String sgsnid = rs.getString("SGSNID");
					String ggsnid = rs.getString("ggsnid");
					String thetemp = sgsnid + ggsnid;

					model.setTotalStream(all);
					model.setTotalUser((int) (usercount));
					model.setDate(_date + "");
					model.setSgsnid(sgsnid);
					model.setUpvolume(rs.getDouble("upvolume1"));
					model.setDownvolume(rs.getDouble("downvolume1"));
					model.setGgsnid(ggsnid);
					model.setApnni(rs.getString("apnni"));
					list.add(model);
				}

				return list;
			}
		});
		List list = (List) object;
		return list;
	}

	/**
	 * 
	 * 得到某一天的sgsn流量,只分apn
	 * 
	 * @param date
	 * @return
	 */
	public List getDaySgsnStreamApn(Date date) {
		final int _date = Integer.parseInt(dfyyyyMMdd.format(date));
		String sql = "select SGSNID,sum(USERCOUNT) as usercount,sum(ALLVOLUME) as allvolume,sum(upvolume) as upvolume,sum(downvolume) as downvolume,apnni from  STAT_SGSN where ggsnid like 'GGSN%' and dayflag=1 and STATTIME="
				+ _date + " group by sgsnid,apnni";
		System.out.println(sql);
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					SgsnStatModel model = new SgsnStatModel();
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					String sgsnid = rs.getString("SGSNID");
					model.setUpvolume(rs.getDouble("upvolume"));
					model.setDownvolume(rs.getDouble("downvolume"));
					model.setTotalStream(all);
					model.setTotalUser((int) (usercount));
					model.setDate(_date + "");
					model.setSgsnid(sgsnid);
					model.setApnni(rs.getString("apnni"));
					list.add(model);
				}
				return list;
			}
		});
		return (List) object;
	}

	/**
	 * 统计某天的bsc/rnc流量
	 * 
	 * @param date
	 * @return
	 */
	public PaginationSupport getBscRncStat(Date date, String sgsnid, String orderby, int pageNo, int pageSize) {
		// int _date = (int) (date.getTime() / 1000);
		final String _date = dfyyyyMMdd.format(date);

		String where = " where 1=1";
		if (sgsnid != null && !sgsnid.equals(""))
			where += " and sgsnid='" + sgsnid + "'";

		where += " and dayflag=1 and stattime=" + _date;

		int totalCount = 0;
		if (pageSize != Integer.MAX_VALUE) {
			String countsql = "select count(*) as cnt from  STAT_BSC {0}";
			countsql = countsql.replace("{0}", where);
			totalCount = jdbcTemplate.queryForInt(countsql);
		}
		int startIndex = (pageNo - 1) * pageSize;

		String sql = "select * from(select a.*,rownum rn from(select BSCID,SGSNID,STATTIME,USERCOUNT,ALLVOLUME from  STAT_BSC {0} "
				+ orderby + ") a where rownum<=" + (startIndex + pageSize) + ") where rn>" + startIndex;

		sql = sql.replace("{0}", where);

		System.out.println(sql);

		// Object[] args = new Object[] { _date };
		// int[] argTypes = new int[] { Types.INTEGER };
		// Object object = jdbcTemplate.query(sql, args, argTypes, new
		// ResultSetExtractor() {
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					BsnRncStatModel model = new BsnRncStatModel();
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					// int stattime = rs.getInt("STATTIME");
					String sgsnid = rs.getString("SGSNID");
					// Date date = new Date();
					// date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					// model.setDate(df.format(date));
					model.setDate(_date);
					model.setSgsnid(sgsnid);
					// model.setNettype(rs.getString("NETTYPE"));
					model.setBscrncid(rs.getString("BSCID"));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		if (pageSize == Integer.MAX_VALUE) {
			totalCount = list.size();
		}
		// PaginationSupport ps=new PaginationSupport();
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

	private List findNsvcsByBscid(String bscid) {
		String sql = "select nsvc from set_nsvc where bscid='" + bscid + "'";
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					list.add(rs.getString("nsvc"));
				}
				return list;
			}
		});
		return (List) object;
	}

	/**
	 * 统计某天的gblink流量
	 * 
	 * @param date
	 * @return
	 */
	public PaginationSupport getNsvcStat(Date date, String bscid, String orderby, int pageNo, int pageSize) {

		long start = StatUtil.getDateTime(date);
		long end = StatUtil.getOneDayAfter(start);
		final String _date = df.format(date);

		String where = " where 1=1";
		if (bscid != null && !bscid.equals("")) {
			// where+=" and bscid='"+bscid+"'";
			List list = findNsvcsByBscid(bscid);
			if (list.size() == 0) {
				List resultlist = new ArrayList();
				PaginationSupport ps = new PaginationSupport(resultlist, 0, pageSize, 0);
				return ps;
			}
			where += " and nsvc in(" + StatUtil.list2str(list) + ")";
		}
		where += " and stattime between " + start / 1000 + " and " + end / 1000;

		int totalCount = 0;
		if (pageSize != Integer.MAX_VALUE) {
			String countsql = "select count(*) as cnt from (select nsvc from stat_nsvc {0} group by nsvc) ";
			countsql = countsql.replace("{0}", where);
			totalCount = jdbcTemplate.queryForInt(countsql);
		}
		int startIndex = (pageNo - 1) * pageSize;

		String sql = "select * from(select a.*,rownum rn from(select nsvc,sum(difference) as difference from  stat_nsvc {0} group by nsvc "
				+ orderby + " ) a where rownum<=" + (startIndex + pageSize) + ") where rn>" + startIndex;

		sql = sql.replace("{0}", where);

		System.out.println(sql);

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					NsvcStatModel model = new NsvcStatModel();
					model.setNsvcid(rs.getString("nsvc"));
					model.setDifference(rs.getFloat("difference"));
					model.setDate(_date);

					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		if (pageSize == Integer.MAX_VALUE) {
			totalCount = list.size();
		}
		// PaginationSupport ps=new PaginationSupport();
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

	/**
	 * 得到各个分公司的流量
	 * 
	 * @param date
	 * @param orderby
	 * @return
	 */
	public List getSubCellDayStat(Date date) {
		final int _date = Integer.parseInt(dfyyyyMMdd.format(date));
		String sql = "select subsidiaryid,sum(usercount) as usercount,sum(upvolume) as upvolume,sum(downvolume) as downvolume,sum(allvolume) as allvolume from stat_cellid_day where dayflag=1 and stattime=? group by subsidiaryid order by subsidiaryid";
		Object[] args = new Object[] { _date };
		int[] argTypes = new int[] { Types.INTEGER };

		final List exists = new ArrayList();
		Object object = jdbcTemplate.query(sql, args, argTypes, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					String subsidiaryid = rs.getString("subsidiaryid");
					if (subsidiaryid != null && !subsidiaryid.equals("")) {
						SubCellStatModel model = new SubCellStatModel();
						int usercount = rs.getInt("USERCOUNT");
						double all = rs.getDouble("ALLVOLUME");

						model.setTotalStream(all);
						model.setTotalUser(usercount);
						model.setDate(_date + "");
						model.setSubsidiaryid(rs.getString("subsidiaryid"));
						model.setUpvolume(rs.getDouble("upvolume"));
						model.setDownvolume(rs.getDouble("downvolume"));

						list.add(model);
						exists.add(subsidiaryid);
					}
				}
				return list;
			}
		});

		List result = (List) object;
		// 判断是否所有的都存在
		Iterator<String> subs = com.sxit.netquality.service.BasicSetService.ALL_SUBS.keySet().iterator();
		while (subs.hasNext()) {
			String subid = subs.next();
			if (!exists.contains(subid)) {// 这个subid不在已有的队列里面，那么就所有的都认为是0
				SubCellStatModel model = new SubCellStatModel();
				model.setSubsidiaryid(subid);
				result.add(model);
			}
		}

		return result;
	}

	/**
	 * 按天统计各cellid的流量情况,估计要分页(暂不处理分页)
	 * 
	 * @param date
	 * @return
	 */
	public PaginationSupport getCellDayStat(Date date, String bscid, String orderby, int pageNo, int pageSize) {
		// int _date = (int) (date.getTime() / 1000);
		final int _date = Integer.parseInt(dfyyyyMMdd.format(date));
		int totalCount = 0;
		String where = "";
		if (!(bscid == null || bscid.equals(""))) {
			where = " and bscid='" + bscid + "'";
		}
		if (pageSize != Integer.MAX_VALUE) {
			String countsql = "select count(*) from STAT_CELLID_DAY where dayflag=1 " + where + " and STATTIME="
					+ _date;

			totalCount = jdbcTemplate.queryForInt(countsql);
		}
		int startIndex = (pageNo - 1) * pageSize;
		String sql = "select * from(select a.*,rownum rn from(select CELLID,lac,BSCID,NETTYPE,STATTIME,USERCOUNT,ALLVOLUME from  STAT_CELLID_DAY where dayflag=1 "
				+ where
				+ " and STATTIME=? "
				+ orderby
				+ ") a where rownum<="
				+ (startIndex + pageSize)
				+ ") where rn>"
				+ startIndex;

		System.out.println(sql);

		Object[] args = new Object[] { _date };
		int[] argTypes = new int[] { Types.INTEGER };
		Object object = jdbcTemplate.query(sql, args, argTypes, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					CellStatModel model = new CellStatModel();
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					// int stattime = rs.getInt("STATTIME");
					// String sgsnid=rs.getString("SGSNID");
					// Date date = new Date();
					// date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					model.setDate(_date + "");
					model.setLac(rs.getString("lac"));
					// model.setDate(df.format(date));
					// model.setSgsnid(sgsnid);
					model.setNettype(rs.getString("NETTYPE"));
					model.setBscrncid(rs.getString("BSCID"));
					model.setCellid(rs.getString("CELLID"));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		if (pageSize == Integer.MAX_VALUE) {
			totalCount = list.size();
		}
		// PaginationSupport ps=new PaginationSupport();
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

	/**
	 * 按时间段统计某cellid的流量情况
	 * 
	 * @param date
	 * @return
	 */
	public List getCellDayTimeStat(Date date, String cellid, String lac) {

		long start = com.sxit.stat.util.StatUtil.getDateTime(date);
		long end = start + 24 * 60 * 60 * 1000L;
		int _datestart = (int) (start / 1000);
		int _dateend = (int) (end / 1000);

		String sql = "select CELLID,lac,BSCID,NETTYPE,STATTIME,NETTYPE,USERCOUNT,ALLVOLUME,upvolume,downvolume from  STAT_CELLID where dayflag=0 and CELLID='"
				+ cellid
				+ "' AND LAC='"
				+ lac
				+ "' and STATTIME between "
				+ _datestart
				+ " and "
				+ _dateend
				+ " order by stattime";
		System.out.println(sql);
		// Object[] args = new Object[] { cellid,_datestart,_dateend };
		// int[] argTypes = new int[] { Types.VARCHAR,Types.INTEGER,
		// Types.INTEGER };
		// Object object = jdbcTemplate.query(sql, args, argTypes, new
		// ResultSetExtractor() {
		final Map<String, CellStatModel> timelist = new HashMap<String, CellStatModel>();
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				// List list = new ArrayList();
				while (rs.next()) {
					long stattime = rs.getLong("STATTIME");
					Date date = new Date();
					date.setTime(stattime * 1000);
					String datetime = dftime.format(date);
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					double up = rs.getDouble("UPVOLUME");
					double down = rs.getDouble("DOWNVOLUME");

					if (timelist.containsKey(datetime)) {

						CellStatModel model = timelist.get(datetime);
						model.setTotalStream(model.getTotalStream() + all);
						model.setTotalUser(model.getTotalUser() + usercount);
						model.setUpvolume(model.getUpvolume() + up);
						model.setDownvolume(model.getDownvolume() + down);
						timelist.remove(datetime);
						timelist.put(datetime, model);
						model.setStattime(stattime);

					} else {

						CellStatModel model = new CellStatModel();

						// String sgsnid=rs.getString("SGSNID");
						model.setLac(rs.getString("lac"));
						model.setTotalStream(all);
						model.setTotalUser(usercount);
						model.setDate(df.format(date));
						// model.setSgsnid(sgsnid);
						model.setNettype(rs.getString("NETTYPE"));
						model.setBscrncid(rs.getString("BSCID"));
						model.setCellid(rs.getString("CELLID"));
						model.setDatetime(datetime);
						model.setStattime(stattime);
						timelist.put(datetime, model);
					}
					// System.out.println(timelist);
				}
				return null;
			}
		});
		// System.out.println(timelist);
		List result = new ArrayList();
		for (int i = 0; i < ALL_TIME_LIST.size(); i++) {
			String time = ALL_TIME_LIST.get(i);
			if (timelist.containsKey(time)) {
				result.add(timelist.get(time));
			} else {
				CellStatModel model = new CellStatModel();
				model.setDatetime(time);
				model.setCellid(cellid);
				model.setDate(df.format(date));
				model.setLac(lac);
				result.add(model);
			}
		}

		return result;
	}

	/**
	 * <pre>
	 * apn业务分时段统计
	 * 
	 * SELECT * FROM 
	 * (
	 * SELECT A.*, ROWNUM RN 
	 * FROM (SELECT * FROM TABLE_NAME) A 
	 * WHERE ROWNUM &lt;= 40
	 * )
	 * WHERE RN &gt;= 21
	 * </pre>
	 * 
	 * @param date
	 * @param cellid
	 * @return
	 */
	public PaginationSupport getApnDayStat(String apnni, Date date, String orderby, final int pageNo, final int pageSize) {
		// int _date = (int) (date.getTime() / 1000);

		if (orderby == null || orderby.equals("")) {
			orderby = " order by orderby";
		}
		String where = "";
		String cntwhere = "";
		if (!(apnni == null || apnni.equals(""))) {
			where = " and STAT_APN.apnni='" + apnni + "'";
			cntwhere = " and apnni='" + apnni + "'";
		}

		final int _date = Integer.parseInt(dfyyyyMMdd.format(date));
		int totalCount = 0;
		if (pageSize != Integer.MAX_VALUE) {
			String cntsql = "select count(*) as cnt from STAT_APN where dayflag=1 " + cntwhere
					+ " and apnni not in('cmwap','cmnet') and STATTIME=" + _date + " ";
			totalCount = jdbcTemplate.queryForInt(cntsql);
		}
		int startIndex = (pageNo - 1) * pageSize;
		// String sql = "select * from(select APNNI,STATTIME,USERCOUNT,ALLVOLUME
		// from STAT_APN where STATTIME="+_date+" and dayflag=1)";

		String sql = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (select STAT_APN.APNNI as apnni,STATTIME,USERCOUNT,ALLVOLUME from  STAT_APN,set_apn where stat_apn.apnni=set_apn.apnni and  dayflag=1 "
				+ where
				+ " and STAT_APN.apnni not in('cmwap','cmnet') and  STATTIME="
				+ _date
				+ orderby
				+ " ) A WHERE ROWNUM <= " + (startIndex + pageSize) + " )  WHERE RN > " + startIndex;

		System.out.println(sql);

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					ApnStatModel model = new ApnStatModel();
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					model.setDate(_date + "");
					model.setDate(_date + "");
					model.setApnid(rs.getString("APNNI"));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		if (pageSize == Integer.MAX_VALUE) {
			totalCount = list.size();
		}
		// PaginationSupport ps=new PaginationSupport();
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

	/**
	 * 按时间段统计某ellid的流量情况
	 * 
	 * @param date
	 * @return
	 */
	public List getApnDayTimeStat(final Date date, String apnid) {

		long start = com.sxit.stat.util.StatUtil.getDateTime(date);
		long end = start + 24 * 60 * 60 * 1000L;
		int _datestart = (int) (start / 1000);
		int _dateend = (int) (end / 1000);

		String sql = "select APNNI,STATTIME,USERCOUNT,ALLVOLUME from  STAT_APN where  APNNI='" + apnid
				+ "' and dayflag=0 and STATTIME between " + _datestart + " and " + _dateend;

		System.out.println(sql);
		// Object[] args = new Object[] { apnid, _datestart, _dateend };
		final Map<String, ApnStatModel> timelist = new HashMap<String, ApnStatModel>();

		// Object[] args = new Object[] { _date, apnid };
		// int[] argTypes = new int[] { Types.VARCHAR, Types.INTEGER,
		// Types.INTEGER };
		// Object object = jdbcTemplate.query(sql, args, argTypes, new
		// ResultSetExtractor() {
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				// List list = new ArrayList();
				while (rs.next()) {
					ApnStatModel model = null;
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					long stattime = rs.getInt("STATTIME");
					Date _date = new Date();
					_date.setTime(stattime * 1000);
					String datetime = dftime.format(_date);
					// System.out.println(datetime);
					if (timelist.containsKey(datetime)) {
						model = timelist.get(datetime);
						model.setTotalStream(model.getTotalStream() + all);
						model.setTotalUser(model.getTotalUser() + usercount);
						timelist.remove(datetime);
					} else {
						model = new ApnStatModel();
						model.setTotalStream(all);
						model.setTotalUser(usercount);
						model.setDate(df.format(date));
						model.setDatetime(datetime);
						model.setApnid(rs.getString("APNNI"));
					}

					timelist.put(datetime, model);
				}
				// return list;
				return null;
			}
		});
		// return (List) object;
		List result = new ArrayList();
		for (int i = 0; i < ALL_TIME_LIST.size(); i++) {
			String time = ALL_TIME_LIST.get(i);
			if (timelist.containsKey(time)) {
				result.add(timelist.get(time));
			} else {
				ApnStatModel model = new ApnStatModel();
				model.setDatetime(time);
				model.setApnid(apnid);
				model.setDate(df.format(date));

				result.add(model);
			}
		}

		return result;
	}

	/**
	 * apn的分cell统计
	 * 
	 * @param date
	 * @param apnid
	 * @return
	 */
	public List getApnCellDayStat(Date date, String apnid, String orderby) {
		// int _date = (int) (date.getTime() / 1000);
		final int _date = Integer.parseInt(dfyyyyMMdd.format(date));
		String sql = "select APNNI,CELLID,lac,STATTIME,USERCOUNT,ALLVOLUME,upvolume,downvolume from  STAT_CELLID_APN_DAY  where ALLVOLUME>0 and dayflag=1 AND APNNI='"
				+ apnid + "' and STATTIME=" + _date + orderby;
		System.out.println(sql);

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					ApnCellStatModel model = new ApnCellStatModel();
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					model.setDate(_date + "");
					model.setLac(rs.getString("lac"));
					model.setCellid(rs.getString("CELLID"));
					model.setApnid(rs.getString("APNNI"));
					// model.setSgsnid(sgsnid);
					model.setDatetime(_date + "");
					list.add(model);
				}
				return list;
			}
		});
		return (List) object;
	}

	public PaginationSupport getApnCellDayStat(Date date, String apnid, String orderby, int pageNo, int pageSize) {

		final int _date = Integer.parseInt(dfyyyyMMdd.format(date));
		int startIndex = (pageNo - 1) * pageSize;
		// String sql = "select
		// APNNI,CELLID,lac,STATTIME,USERCOUNT,ALLVOLUME,upvolume,downvolume
		// from STAT_CELLID_APN where dayflag=1 AND APNNI='"
		// + apnid + "' and STATTIME=" + _date + orderby;

		String sql = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (select APNNI,CELLID,lac,STATTIME,USERCOUNT,ALLVOLUME,upvolume,downvolume from  STAT_CELLID_APN_DAY  where ALLVOLUME>0 and dayflag=1 AND APNNI='"
				+ apnid
				+ "' and STATTIME="
				+ _date
				+ orderby
				+ " ) A WHERE ROWNUM <= "
				+ (startIndex + pageSize)
				+ " )  WHERE RN > " + startIndex;

		System.out.println(sql);

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					ApnCellStatModel model = new ApnCellStatModel();
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					model.setDate(_date + "");
					model.setLac(rs.getString("lac"));
					model.setCellid(rs.getString("CELLID"));
					model.setApnid(rs.getString("APNNI"));
					// model.setSgsnid(sgsnid);
					model.setDatetime(_date + "");
					list.add(model);
				}
				return list;
			}
		});

		int totalCount = 0;

		List list = (List) object;
		if (pageSize == Integer.MAX_VALUE) {
			totalCount = list.size();
		} else {
			String cntsql = "select count(*) from STAT_CELLID_APN_day  where dayflag=1 AND APNNI='" + apnid
					+ "' and STATTIME=" + _date;
			totalCount = jdbcTemplate.queryForInt(cntsql);
		}
		// PaginationSupport ps=new PaginationSupport();
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}
}

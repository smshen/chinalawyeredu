/**
 * LessonsService.java
 */

package com.changpeng.lessons.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.context.Globals;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.lessons.dao.LessonsDAO;
import com.changpeng.lessons.util.CommonDatas;
import com.changpeng.lessons.util.Lessonstatics;
import com.changpeng.models.Lessonorder;
import com.changpeng.models.Lessonrecommand;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lessonscore;
import com.changpeng.models.Lessonshared;
import com.changpeng.models.Lessonshoucang;
import com.changpeng.models.Lessontype;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUser;

/**
 * @author 华锋 2008-5-16 上午10:55:55
 * 
 */
public class LessonsService extends BasicService {

	private JdbcTemplate jdbcTemplate;
	private LessonsDAO lessonsDAO;

	// private PlatformTransactionManager transactionManager;

	// public void setTransactionManager(PlatformTransactionManager
	// transactionManager) {
	// this.transactionManager = transactionManager;
	// }

	/**
	 * @param lessonsDAO
	 *            the lessonsDAO to set
	 */
	public void setLessonsDAO(LessonsDAO lessonsDAO) {
		this.lessonsDAO = lessonsDAO;
	}

	public Lessonscore getScorebyLessonUser(long userid, int lessonid) throws ServiceException {
		try {
			return lessonsDAO.getScorebyLessonUser(userid, lessonid);
		} catch (Exception e) {

			throw new ServiceException(e);
		}
	}

	@Transactional
	public void saveLesson(Lessons lesson, List groupids, SysUser user) throws ServiceException {
		lessonsDAO.save(lesson);

		// 保存共享的情况
		if (groupids != null && groupids.size() > 0) {
			for (int i = 0; i < groupids.size(); i++) {
				com.changpeng.models.Lessonshared share = new Lessonshared();
				share.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				share.setCreateuserid(user.getUserid());
				share.setCreateusername(user.getUsername());
				share.setLessons(lesson);
				share.setGroupid(Integer.parseInt(groupids.get(i).toString()));
				share.setXuefen(lesson.getXuefen());

				lessonsDAO.save(share);
			}
		}
	}

	/**
	 * 修改课程的共享情况
	 * 
	 * @param lesson
	 * @param user
	 * @throws ServiceException
	 */
	@Transactional
	public void updateLessonShared(Lessons lesson, List groupids, SysUser user) throws ServiceException {
		// 先清除掉原有的共享
		String hql = "delete from com.changpeng.models.Lessonshared share where share.lessons.lessonid=?";
		lessonsDAO.execute(hql, lesson.getLessonid());

		// 再保存共享的情况
		if (groupids != null && groupids.size() > 0) {
			for (int i = 0; i < groupids.size(); i++) {
				com.changpeng.models.Lessonshared share = new Lessonshared();
				share.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				share.setCreateuserid(user.getUserid());
				share.setCreateusername(user.getUsername());
				share.setLessons(lesson);
				share.setGroupid(Integer.parseInt(groupids.get(i).toString()));
				share.setXuefen(lesson.getXuefen());

				lessonsDAO.save(share);
			}
		}
	}

	/**
	 * 建立课程的共享
	 * 
	 * @param lesson
	 * @param groupids
	 * @param user
	 */
	public void createLessonShare(Lessons lesson, List groupids, SysUser user) {
		// 保存共享的情况
		if (groupids != null && groupids.size() > 0) {
			for (int i = 0; i < groupids.size(); i++) {
				com.changpeng.models.Lessonshared share = new Lessonshared();
				share.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				share.setCreateuserid(user.getUserid());
				share.setCreateusername(user.getUsername());
				share.setLessons(lesson);
				share.setGroupid(Integer.parseInt(groupids.get(i).toString()));
				share.setXuefen(lesson.getXuefen());

				lessonsDAO.save(share);
			}
		}
	}

	public Lessonstatics getFiledLessons(Timestamp _from, Timestamp _end, String field, int fieldvalue)
			throws ServiceException {

		String sql = "";

		if (field != null && !field.equals("")) {
			sql = "select lessonstyle,count(lessonstyle) from lessons where (UNIX_TIMESTAMP(lessondate) between "
					+ _from.getTime() / 1000 + " and " + _end.getTime() / 1000 + ") and " + field + "=" + fieldvalue
					+ " group by learnmode";
		} else {
			sql = "select lessonstyle,count(lessonstyle) from lessons where (UNIX_TIMESTAMP(lessondate) between "
					+ _from.getTime() / 1000 + " and " + _end.getTime() / 1000 + ") group by lessonstyle";

		}

		Lessonstatics statics = new Lessonstatics();

		List tongjilist = lessonsDAO.findBySqlQuery(sql);
		int tongjilength = tongjilist == null ? 0 : tongjilist.size();
		// 1现场课程2在线培训课程3现场和在线的合并4文本课件5补登的积分',

		for (int i = 0; i < tongjilength; i++) {
			Object[] obj = (Object[]) tongjilist.get(i);
			int style = Integer.parseInt(obj[0].toString());
			if (style == 1) {
				statics.setLocal(Integer.parseInt(obj[1].toString()));
			} else if (style == 2) {
				statics.setOnline(Integer.parseInt(obj[1].toString()));
			} else if (style == 3) {
				statics.setOnlineandlocal(Integer.parseInt(obj[1].toString()));
			} else if (style == 4) {
				statics.setWenbenkejian(Integer.parseInt(obj[1].toString()));
			}
		}

		statics.setLocal(statics.getLocal() + statics.getOnlineandlocal());
		statics.setOnline(statics.getOnline() + statics.getOnlineandlocal());

		return statics;
	}

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

//	public com.changpeng.common.PaginationSupport getPages(SysGroup mygroup, int groupid, int audioQuality,
//			int videoQuality, int onlinetype, int lessonstyle, int lessontype, String title, String teachers,
//			int pageSize, int pageNo, Timestamp start, Timestamp end) 
	
	public com.changpeng.common.PaginationSupport getPages(SysGroup mygroup, int groupid,int audioQuality,
			int videoQuality, int onlinetype, int lessonstyle, int lessontype, String title, String teachers,
			String isfree,String orderBy,int pageSize, int pageNo)
	{

		// 现场课程的话，如果我是admin或者group>3，则显示所有的现场课程，否则的话，
		// 我是省的话，显示本省的所有，市的话，只显示市的
		// 1是显示本地的本地课程，100是显示外地的本地课程
		if (lessonstyle == 1 || lessonstyle == 100) {

			DetachedCriteria dc = DetachedCriteria.forClass(Lessons.class).add(Restrictions.eq("deleteflag", false));
			if (mygroup != null && mygroup.getGrouptype() <= 3) {
				if (mygroup.getGrouptype() == 1) { // 无权限
					dc.add(Restrictions.eq("provinceid", -1));
				} else if (mygroup.getGrouptype() == 2) {
					// 自己干的和省律协干的
					dc.add(Restrictions.or(Restrictions.eq("cityid", mygroup.getGroupid()), Restrictions.eq("groupid",
							mygroup.getParentid())));
				} else {
					dc.add(Restrictions.eq("provinceid", mygroup.getGroupid()));
				}
			}
			if (onlinetype != -1) {
				dc.add(Restrictions.eq("onlineType", onlinetype));
			}
			if (audioQuality != -1) {
				dc.add(Restrictions.eq("audioQuality", audioQuality));
			}
			if (videoQuality != -1) {
				dc.add(Restrictions.eq("videoQuality", videoQuality));
			}
			if (title != null && !"".equals(title)) {
				dc.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
			}
			if (teachers != null && !"".equals(teachers)) {
				dc.add(Restrictions.like("teachers", teachers, MatchMode.ANYWHERE));
			}
			if (lessontype != 0) {
				dc.add(Restrictions.eq("lessontype", lessontype));
			}
			if(isfree!=null&&!isfree.equals("")&&!isfree.equals("-1"))
			{
				if(isfree.equals("1"))
				dc.add(Restrictions.eq("price", 0f));
				else
					dc.add(Restrictions.ne("price", 0f));
			}
//			if (groupid != -1) {
//				// dc.add(Restrictions.eq("lessons.groupid", groupid));
//				dc.add(Restrictions.or(Restrictions.eq("groupid", groupid), Restrictions.eq("provinceid", groupid)));
//				// hql += " and a.lessons.groupid=" + groupid;
//			}
//			if (start != null && end != null)
//				dc.add(Restrictions.between("lessondate", start, end));
			dc.add(Restrictions.in("lessonstyle", new Integer[] { 1, 3 }));
			
	
				if (orderBy == null || orderBy.equals(""))
					dc.addOrder(Order.desc("lessondate"));
				else
					dc.addOrder(Order.desc(orderBy));
//			dc.addOrder(Order.desc("lessonid"));
			PaginationSupport page = lessonsDAO.findPageByCriteria(dc, pageSize, pageNo);
			return page;
		} else {

			DetachedCriteria dc = DetachedCriteria.forClass(Lessonshared.class);
			dc.createAlias("lessons", "lessons");
			String hql = "select distinct a.lessons from Lessonshared a where 1=1 ";

//			if (mygroup != null && mygroup.getGrouptype() <= 3) {// mygroup为null的话,能看到所有律协的
//				List<Integer> groupids = new ArrayList<Integer>();
//				String str = "";
//				if (mygroup.getGrouptype() == 1) {
//					groupids.add(mygroup.getGroupid());
//					groupids.add(mygroup.getParentid());
//					groupids.add(mygroup.getDirectgroup());
//					str = mygroup.getGroupid() + "," + mygroup.getParentid() + "," + mygroup.getDirectgroup();
//					dc.add(Restrictions.in("groupid", groupids));
//					hql += " and a.groupid in(" + str + ")";
//				} else if (mygroup.getGrouptype() == 2) {
//					groupids.add(mygroup.getGroupid());
//					groupids.add(mygroup.getParentid());
//					str = mygroup.getGroupid() + "," + mygroup.getParentid();
//					dc.add(Restrictions.in("groupid", groupids));
//					hql += " and a.groupid in(" + str + ")";
//				} else if (mygroup.getGrouptype() == 3) {// 省律协的,我省id是这个就行了吧
//					groupids.add(mygroup.getGroupid());
//					str = mygroup.getGroupid() + "";
//
//					dc.add(Restrictions.or(Restrictions.in("groupid", groupids), Restrictions.eq("lessons.provinceid",
//							mygroup.getGroupid())));
//					hql += " and (a.groupid in(" + str + ") or a.lessons.provinceid =" + mygroup.getGroupid() + ")";
//
//				}
//
//			}
			// 不显示删除的
			dc.add(Restrictions.eq("lessons.deleteflag", false));
			hql += " and a.lessons.deleteflag=false";
			// 具体的来源
//			if (groupid != -1) {
//				// dc.add(Restrictions.eq("lessons.groupid", groupid));
//				dc.add(Restrictions.or(Restrictions.eq("lessons.groupid", groupid), Restrictions.eq(
//						"lessons.provinceid", groupid)));
//
//				hql += " and (a.lessons.groupid=" + groupid + " or a.lessons.provinceid=" + groupid + ")";
//			}

			if (title != null && !"".equals(title)) {
				dc.add(Restrictions.like("lessons.title", title, MatchMode.ANYWHERE));
				hql += " and a.lessons.title like '%" + title + "%'";
			}
			if (teachers != null && !"".equals(teachers)) {
				dc.add(Restrictions.like("lessons.teachers", teachers, MatchMode.ANYWHERE));
				hql += " and a.lessons.teachers like '%" + teachers + "%'";
			}
			if (onlinetype != -1) {
				// dc.add(Restrictions.eq("onlinetype", onlinetype));
				dc.add(Restrictions.eq("lessons.onlineType", onlinetype));
				hql += " and a.lessons.onlineType = " + onlinetype;
			}
			if (audioQuality != -1) {
				dc.add(Restrictions.eq("lessons.audioQuality", audioQuality));
				hql += " and a.lessons.audioQuality = " + audioQuality;
			}
			if (videoQuality != -1) {
				dc.add(Restrictions.eq("lessons.videoQuality", videoQuality));
				hql += " and a.lessons.videoQuality = " + videoQuality;
			}
			if (lessontype != -1) {
				dc.add(Restrictions.eq("lessons.lessontype", lessontype));
				hql += " and a.lessons.lessontype =" + lessontype;
			}
			
			
			if(isfree!=null&&!isfree.equals("")&&!isfree.equals("-1"))
			{
				if(isfree.equals("1")){
				dc.add(Restrictions.eq("lessons.price", 0f));
				hql += " and a.lessons.price =0" ;
				}
				else{
					dc.add(Restrictions.ne("lessons.price", 0f));
					hql += " and a.lessons.price >0" ;
				}
			}
			
			
			if (lessonstyle != 0) {
				// if (lessonstyle == 1 || lessonstyle == 2)
				dc.add(Restrictions.in("lessons.lessonstyle", new Object[] { lessonstyle, 3 }));
				// else
				// dc.add(Restrictions.eq("lessons.lessonstyle", lessonstyle));
				hql += " and a.lessons.lessonstyle in(" + lessonstyle + ",3)";
			}

//			if (start != null && end != null) {
//				String startStr = df.format(start) + " 00:00:00";
//				String endStr = df.format(end) + " 23:59:59";
//				dc.add(Restrictions.between("lessons.lessondate", start, end));
//				// dc.add(Restrictions.between("lessons.lessondate", start,
//				// end));
//				hql += " and a.lessons.lessondate between '" + startStr + "'and '" + endStr + "'";
//			}

			if (orderBy == null || orderBy.equals(""))
//				dc.addOrder(Order.desc("lessondate"));
				hql += " order by a.lessons.lessondate desc,a.lessons.lessonid desc";
			else
//				dc.addOrder(Order.desc(orderBy));
			
			hql += " order by a.lessons."+orderBy+" desc,a.lessons.lessonid desc";

//			System.out.println(hql);
			
			dc.setProjection(Projections.countDistinct("lessons"));
			// dc.addOrder(Order.desc("lessons.lessondate"));
			// dc.addOrder(Order.desc("lessons.lessonid"));
			List list = lessonsDAO.findAllByCriteria(dc);
			int len = list == null ? 0 : list.size();
			int totalCount = len == 0 ? 0 : Integer.parseInt(list.get(0).toString());
			// 根据授课时间进行排序
			// detachedCriteria.addOrder(Order.desc("lessons.lessondate"));
			PaginationSupport page = lessonsDAO.findByQuery(hql, pageSize, pageNo, totalCount);
			return page;
		}
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 收藏课程
	 * 0成功，-1已收藏
	 */
	public int shouchangLesson(int lawyerId, int lessonId) {
		String sql = "select count(*) from lessonshoucang where lawyerid=" + lawyerId + " and lessonid=" + lessonId;
		int s = jdbcTemplate.queryForInt(sql);
		if (s != 0)
			return -1;// 已经选购了
		else {
			Lessonshoucang shoucang = new Lessonshoucang();
			shoucang.setLawyerid(lawyerId);
			shoucang.setLessonid(lessonId);
			shoucang.setRemarks("");
			shoucang.setShoucangdate(new java.sql.Timestamp(System.currentTimeMillis()));
			lessonsDAO.save(shoucang);
			return 0;

		}

	}
	
	public int xuangouLesson(int lawyerId, int lessonId) {
		String sql = "select count(*) from lessonorder where lawyerid=" + lawyerId + " and lessonid=" + lessonId;
		int s = jdbcTemplate.queryForInt(sql);
		if (s != 0)
			return -1;// 已经选购了
		else {
			Lessonorder order = new Lessonorder();
			order.setLawyerid(lawyerId);
			order.setLessonid(lessonId);
			order.setRemarks("");
			Lessons lessons=(Lessons)lessonsDAO.get(Lessons.class, lessonId);
			order.setOrderprice(lessons.getPrice());
			order.setOrderdate(new java.sql.Timestamp(System.currentTimeMillis()));
			lessonsDAO.save(order);
			return 0;
		}
	}

	/**
	 * 得到我收藏的课程
	 * 
	 * @param lawyerId
	 * @param lessonType
	 * @param orderBy
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PaginationSupport getShoucangList(int lawyerId, int lessonType, String orderBy, int pageSize, int pageNo) {

		List list = lessonsDAO.find("select lessonid from Lessonshoucang  where lawyerid=" + lawyerId);
		if (list == null || list.size() == 0)
			return new PaginationSupport();
		DetachedCriteria dc = DetachedCriteria.forClass(Lessons.class);
		dc.add(Restrictions.in("lessonid", list));
		if (lessonType != -1)
			dc.add(Restrictions.eq("lessontype", lessonType));
		if (!(orderBy == null || orderBy.equals("")))
			dc.addOrder(Order.desc("lessonid"));
		return lessonsDAO.findPageByCriteria(dc, pageSize, pageNo);

	}
	
	
	
	/**
	 * 得到推荐的课程数
	 * 推荐进,取消出
	 * @return
	 */
	public List<Integer> getRecommandLessonids(int count){
		
		
//		return lessonsDAO.find("select lessonid from  Lessonrecommand where statusid=1 order by recommanddate");
		
		DetachedCriteria dc=DetachedCriteria.forClass(Lessonrecommand.class);
		dc.add(Restrictions.eq("statusid", 1));
		dc.addOrder(Order.desc("recommanddate"));
		List list=lessonsDAO.findPageByCriteria(dc, count, 1).getItems();
		
		List<Integer> result=new ArrayList<Integer>();
		for(int i=0;i<list.size();i++){
			Lessonrecommand r=(Lessonrecommand)list.get(i);
			result.add(r.getLessonid());
		}
		
		return result;
	}


	/**
	 * 得到我所订购的课程
	 */
	public PaginationSupport getXuangouLessons(int lawyerId, int lessonType, String isfree,String orderBy, int pageSize, int pageNo) {

		List list = lessonsDAO.find("select lessonid from Lessonorder where lawyerid=" + lawyerId);
		
		
	
		
		if (list == null || list.size() == 0)
			return new PaginationSupport();
		DetachedCriteria dc = DetachedCriteria.forClass(Lessons.class);
		dc.add(Restrictions.in("lessonid", list));
		if (lessonType != -1)
			dc.add(Restrictions.eq("lessontype", lessonType));

		

		if(isfree.equals("1")){
			dc.add(Restrictions.eq("price", 0.0f));
		}else if(isfree.equals("0")){
			dc.add(Restrictions.ne("price", 0.0f));
		}
//		System.out.println("orderBy===="+orderBy);

		
		if (orderBy == null || orderBy.equals(""))
			dc.addOrder(Order.desc("lessondate"));
		else
			dc.addOrder(Order.desc(orderBy));
		
		return lessonsDAO.findPageByCriteria(dc, pageSize, pageNo);

	}

	/**
	 * 得到我选购的课程的
	 * 
	 * @param lawyerId
	 * @return
	 */
	public List getXuangouLessontypeTree(int lawyerId) {

		String sql = "select aa.lessontype,count(aa.lessontype) cnt from (select a.* from lessons a inner join lessonorder b on a.lessonid=b.lessonid where b.lawyerid= "
				+ lawyerId + ") aa group by aa.lessontype";
		JdbcTemplate jdbcTemplate = (JdbcTemplate) Globals.getBean("jdbcTemplate");
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Lessontype> list = new ArrayList<Lessontype>();
				while (rs.next()) {

					Lessontype type = new Lessontype();
					type.setLessoncnt(rs.getInt("cnt"));
					type.setTypeid(rs.getInt("lessontype"));
					type.setParentid(CommonDatas.LessonTypeObject.get(type.getTypeid()).getParentid());
					type.setTypename(CommonDatas.LessonTypeObject.get(type.getTypeid()).getTypename());

					list.add(type);
				}
				return list;
			}
		});

		List list = (List) object;
		return getTreeLessontypes(list);
	}

	/**
	 * 根据types列表得到两层树形结构的数据
	 * 
	 * @param lessontypes
	 * @return
	 */
	public List getTreeLessontypes(List lessontypes) {

		List<Lessontype> list = new ArrayList<Lessontype>();

		int len = lessontypes == null ? 0 : lessontypes.size();

		for (int i = 0; i < len; i++) {
			Lessontype type = (Lessontype) lessontypes.get(i);

			if (type.getParentid() >= 0) {

				if (type.getParentid() == 0) {
					list.add(type);
				}

				for (int j = 0; j < len; j++) {
					Lessontype type1 = (Lessontype) lessontypes.get(j);
					if (type1.getParentid() == type.getTypeid() && !type.getChildren().contains(type1)) {
						type.addChild(type1);
						//下级的数量加到上级
						type.setLessoncnt(type.getLessoncnt()+type1.getLessoncnt());

					}
				}
			}
		}

		return list;

	}

	// public com.changpeng.common.PaginationSupport getPages(SysGroup mygroup,
	// int groupid, int lessonstyle,
	// int lessontype, String title, String teachers, int pageSize, int pageNo,
	// Timestamp start, Timestamp end) {
	//
	// // 现场课程的话，如果我是admin或者group>3，则显示所有的现场课程，否则的话，
	// // 我是省的话，显示本省的所有，市的话，只显示市的
	// if (lessonstyle == 1 || lessonstyle == 100) {
	// DetachedCriteria dc = DetachedCriteria.forClass(Lessons.class);
	// // 这里的mygroup都是市律协
	// if (lessonstyle == 1)
	// dc.add(Restrictions.in("groupid", new Integer[] { mygroup.getGroupid(),
	// mygroup.getParentid() }));
	// else {
	// dc.add(Restrictions.not(Restrictions.in("groupid", new Integer[] {
	// mygroup.getGroupid(),
	// mygroup.getParentid() })));
	// }
	// if (groupid != -1) {
	// // dc.add(Restrictions.eq("lessons.groupid", groupid));
	// dc.add(Restrictions.or(Restrictions.eq("groupid", groupid),
	// Restrictions.eq("provinceid", groupid)));
	// // hql += " and a.lessons.groupid=" + groupid;
	// }
	// if (title != null && !"".equals(title)) {
	// dc.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
	// }
	// if (teachers != null && !"".equals(teachers)) {
	// dc.add(Restrictions.like("teachers", teachers, MatchMode.ANYWHERE));
	// }
	// if (lessontype != 0) {
	// dc.add(Restrictions.eq("lessontype", lessontype));
	// }
	// if (start != null && end != null)
	// dc.add(Restrictions.between("lessondate", start, end));
	// dc.add(Restrictions.in("lessonstyle", new Integer[] { 1, 3 }));
	// dc.addOrder(Order.desc("lessondate"));
	// PaginationSupport page = lessonsDAO.findPageByCriteria(dc, pageSize,
	// pageNo);
	// return page;
	// } else {
	//
	// DetachedCriteria dc = DetachedCriteria.forClass(Lessonshared.class);
	// dc.createAlias("lessons", "lessons");
	// String hql = "select distinct a.lessons from Lessonshared a where 1=1 ";
	//
	// if (mygroup != null && mygroup.getGrouptype() <= 3) {//
	// mygroup为null的话,能看到所有律协的
	// List<Integer> groupids = new ArrayList<Integer>();
	// String str = "";
	// if (mygroup.getGrouptype() == 1) {
	// groupids.add(mygroup.getGroupid());
	// groupids.add(mygroup.getParentid());
	// groupids.add(mygroup.getDirectgroup());
	// str = mygroup.getGroupid() + "," + mygroup.getParentid() + "," +
	// mygroup.getDirectgroup();
	//
	// } else if (mygroup.getGrouptype() == 2) {
	// groupids.add(mygroup.getGroupid());
	// groupids.add(mygroup.getParentid());
	// str = mygroup.getGroupid() + "," + mygroup.getParentid();
	// } else if (mygroup.getGrouptype() == 3) {
	// groupids.add(mygroup.getGroupid());
	// str = mygroup.getGroupid() + "";
	// }
	// dc.add(Restrictions.in("groupid", groupids));
	// hql += " and a.groupid in(" + str + ")";
	// }
	// // 不显示删除的
	// dc.add(Restrictions.eq("lessons.deleteflag", false));
	// hql += " and a.lessons.deleteflag=false";
	// // 具体的来源
	// if (groupid != -1) {
	// // dc.add(Restrictions.eq("lessons.groupid", groupid));
	// dc.add(Restrictions.or(Restrictions.eq("lessons.groupid", groupid),
	// Restrictions.eq(
	// "lessons.provinceid", groupid)));
	// // hql += " and a.lessons.groupid=" + groupid;
	// hql += " and (a.lessons.groupid=" + groupid + " or a.lessons.provinceid="
	// + groupid+")";
	// }
	//
	// if (title != null && !"".equals(title)) {
	// dc.add(Restrictions.like("lessons.title", title, MatchMode.ANYWHERE));
	// hql += " and a.lessons.title like '%" + title + "%'";
	// }
	// if (teachers != null && !"".equals(teachers)) {
	// dc.add(Restrictions.like("lessons.teachers", teachers,
	// MatchMode.ANYWHERE));
	// hql += " and a.lessons.teachers like '%" + teachers + "%'";
	// }
	// if (lessontype != 0) {
	// dc.add(Restrictions.eq("lessons.lessontype", lessontype));
	// hql += " and a.lessons.lessontype =" + lessontype;
	// }
	// if (lessonstyle != 0) {
	// if (lessonstyle == 1 || lessonstyle == 2)
	// dc.add(Restrictions.in("lessons.lessonstyle", new Object[] { lessonstyle,
	// 3 }));
	// else
	// dc.add(Restrictions.eq("lessons.lessonstyle", lessonstyle));
	// hql += " and a.lessons.lessonstyle in(" + lessonstyle + ",3)";
	// }
	// if (start != null && end != null) {
	// String startStr = df.format(start) + " 00:00:00";
	// String endStr = df.format(end) + " 23:59:59";
	// dc.add(Restrictions.between("lessons.lessondate", start, end));
	// dc.add(Restrictions.between("lessons.lessondate", start, end));
	// hql += " and a.lessons.lessondate between '" + startStr + "'and '" +
	// endStr + "'";
	// }
	//
	// hql += " order by a.lessons.lessondate desc";
	//
	// dc.setProjection(Projections.countDistinct("lessons"));
	// List list = lessonsDAO.findAllByCriteria(dc);
	// int len = list == null ? 0 : list.size();
	// int totalCount = len == 0 ? 0 : Integer.parseInt(list.get(0).toString());
	// // 根据授课时间进行排序
	// // detachedCriteria.addOrder(Order.desc("lessons.lessondate"));
	// PaginationSupport page = lessonsDAO.findByQuery(hql, pageSize, pageNo,
	// totalCount);
	// return page;
	// }

	// }

}

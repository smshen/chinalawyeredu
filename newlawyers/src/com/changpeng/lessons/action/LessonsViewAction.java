
package com.changpeng.lessons.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Lessons;

public class LessonsViewAction extends AbstractListAction {
	private Lessons lesson;
	private int lessonid;
	
	private List<String> filelist;
	private boolean hasattach;
	public boolean getHasattach(){
		return hasattach;
	}
	public List<String> getFilelist() {
		return filelist;
	}
	
	
	public Lessons getLesson() {
		return lesson;
	}

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public LessonsViewAction() {
		
	}


	
	@Override
	protected String go() throws Exception {

		// System.out.println("run here.......................lessonid:"+lessonid);
	  BasicService basicService =(BasicService)this.getBean("basicService");
		// lessonid=1;
		if (lessonid == 0) {

			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lessons.class);
			// detachedCriteria.add(Restrictions.isNull("onlinefile"));
			detachedCriteria.setProjection(Projections.max("lessonid"));
			// detachedCriteria.addOrder(Order.desc("lessonid"));
			List list = basicService.findAllByCriteria(detachedCriteria);
			lessonid = Integer.parseInt(list.get(0).toString());
			lesson = (Lessons) basicService.get(Lessons.class, lessonid);
			
		}
		else {
			lesson = (Lessons) basicService.get(Lessons.class, lessonid);
		}
		
		DetachedCriteria dc=DetachedCriteria.forClass(com.changpeng.models.Lessonreply.class);
		dc.add(Restrictions.eq("lessonid",lessonid));
		dc.addOrder(Order.desc("replytime"));
		replylist=basicService.findAllByCriteria(dc);
		
		String attach=lesson.getAttach();
		filelist=new java.util.ArrayList<String>();
		if(attach!=null){
			String file[]=attach.split(",");
			for(String str:file){
				if(str!=null&&!"".equals(str.trim()))
					filelist.add(str);
			}
		}
		if(filelist.size()>0)
			hasattach=true;
		
		if(lesson==null){
			this.message="暂时没有培训课程,请联系管理员";
			this.nextPage="../lessons/lessonsCreate!input.pl";//
			return "message";
		}

		return SUCCESS;
	}
	List replylist=null;
	public List getReplylist(){
		return this.replylist;
	}

}
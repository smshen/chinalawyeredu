/**
 * <pre>
 *               
 * </pre>
 */

package com.changpeng.common.listener;

import java.util.List;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.CommonDatas;
import com.changpeng.common.Constants;
import com.changpeng.common.context.Globals;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.Areas;
import com.changpeng.models.SysParameter;
import com.changpeng.models.SysUnionparams;
import com.changpeng.system.service.SysLoginLogService;
import com.changpeng.system.service.SysRightService;
import com.changpeng.system.util.RightTree;

/**
 * 
 * web容器启动和关闭的时候，所需要做的操作。不包括以kill的方式删除进程
 * 
 * @author 华锋 2008-2-22 上午09:16:34
 * 
 */
public class WebContextListener implements ServletContextListener {

	private static final Log LOG = LogFactory.getLog(WebContextListener.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextAttributeListener#attributeAdded(javax.servlet.ServletContextAttributeEvent)
	 */
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		LOG.info("context attributeAdded,name=" + arg0.getName() + ",value=" + arg0.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextAttributeListener#attributeRemoved(javax.servlet.ServletContextAttributeEvent)
	 */
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		LOG.info("context attributeRemoved,name=" + arg0.getName() + ",value=" + arg0.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextAttributeListener#attributeReplaced(javax.servlet.ServletContextAttributeEvent)
	 */
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		LOG.info("context attributeReplaced,name=" + arg0.getName() + ",value=" + arg0.getValue());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {

		String contextid = event.getServletContext().getAttribute("CONTEXTID").toString();
		LOG.info("系统退出,系统ID......" + contextid);

		try {
			// Globals globals = new Globals();
			SysLoginLogService service = (SysLoginLogService) Globals.getBean("sysLoginLogService");

			service.updateLogoutInfo(contextid);
			LOG.info("容器退出时，记录用户的会话失效信息OK！");
		} catch (Exception e) {
			LOG.error("容器退出时，记录用户的会话失效信息失败！" + e);

		}

		// 更新所有登录用户的退出信息，通过contextid来进行

		// Globals globals = new Globals();
		// SysLoginLogService service = (SysLoginLogService)
		// globals.getBean("sysLoginLogService");
		// service.updateLogoutInfo(sysUser.getLoginId(), "系统正常退出");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {

		try {
			// Globals globals = new Globals();

			// 得到所有的rightcode等等
			BasicService service = (BasicService) Globals.getBean("basicService");
			List list = service.find("from com.changpeng.models.Areas a where a.grade<=3");
			for (int i = 0; i < list.size(); i++) {
				Areas area = (Areas) list.get(i);
				CommonDatas.AreasIdParent.put(area.getAreaCode(), area.getParentAreaCode());
				CommonDatas.AreasIdName.put(area.getAreaCode(), area.getAreaName());
			}

			list = service.findAll(SysParameter.class);
			int length = list == null ? 0 : list.size();
			for (int i = 0; i < length; i++) {
				SysParameter param = (SysParameter) list.get(i);
				CommonDatas.SysParameter.put(param.getParamname(), param.getParamvalue());
			}
			LOG.info("参数:" + CommonDatas.SysParameter);
//			list = service.findAll(SysUnionparams.class);
//			length = list == null ? 0 : list.size();
//			for (int i = 0; i < length; i++) {
//				SysUnionparams area = (SysUnionparams) list.get(i);
//				if (area.getDomain() != null &&! area.getDomain().equals("")) {
//					CommonDatas.SysUnionparams.put(area.getDomain(), area);
//				}
//			}
			com.changpeng.system.util.CommonDatas.getGroupParamsUrl();
			LOG.info("URL:" + CommonDatas.SysUnionparams.keySet());

			if (CommonDatas.SysParameter.containsKey("sysname")) {
				Constants.SYS_NAME = CommonDatas.SysParameter.get("sysname").toString();
				Constants.DEFAULT_SYS_NAME=	Constants.SYS_NAME ;
			}

			if (CommonDatas.SysParameter.containsKey("location")) {
				Constants.LOCATION = CommonDatas.SysParameter.get("location").toString();
			}
			if (CommonDatas.SysParameter.containsKey("logopath")) {
				Constants.LOGO_PATH = CommonDatas.SysParameter.get("logopath").toString();
				Constants.DEFAULT_LOGO_PATH=	Constants.LOGO_PATH ;
			}
			if (CommonDatas.SysParameter.containsKey("resourcepath")) {
				Constants.RESOURCE_PATH = CommonDatas.SysParameter.get("resourcepath").toString();
			}
			
			if (CommonDatas.SysParameter.containsKey("indexpic")) {
				Constants.INDEX_PIC = CommonDatas.SysParameter.get("indexpic").toString();
				Constants.DEFAULT_INDEX_PIC=Constants.INDEX_PIC;
			}
			if (CommonDatas.SysParameter.containsKey("topbarpic")) {
				Constants.TOP_BAR_PIC = CommonDatas.SysParameter.get("topbarpic").toString();
				Constants.DEFAULT_TOP_BAR_PIC=Constants.TOP_BAR_PIC;
			}
			
			if (CommonDatas.SysParameter.containsKey("photosavepath")) {
				Constants.PHOTO_SAVE_PATH =CommonDatas.SysParameter.get("photosavepath").toString();
					if(Constants.PHOTO_SAVE_PATH .endsWith("/"))
						Constants.PHOTO_SAVE_PATH =Constants.PHOTO_SAVE_PATH +"/";
				
				
			}

			if (CommonDatas.SysParameter.containsKey("startyear")) {
				Constants.START_YER = Integer.parseInt(CommonDatas.SysParameter.get("startyear").toString());
			}
			if (CommonDatas.SysParameter.containsKey("havelocal")) {
				if (CommonDatas.SysParameter.get("havelocal").toString().equals("true")
						|| CommonDatas.SysParameter.get("havelocal").toString().equals("false"))
					Constants.HAVELOCAL = Boolean.parseBoolean(CommonDatas.SysParameter.get("havelocal").toString());
				else
					Constants.HAVELOCAL = false;
				Constants.DEFAULT_HAVELOCAL=Constants.HAVELOCAL;
			}

			// LOG.warn("CommonDatas.SysParameter.containsKey(\"fromaddr\"):"
			// + CommonDatas.SysParameter.containsKey("fromaddr"));
			// LOG.warn("Constants.FROMADDR:::" + Constants.FROMADDR);

			SysRightService rightService = (SysRightService) Globals.getBean("sysRightService");
			RightTree.setRightList(rightService.findAll());
		
			getSysteNos();
			LOG.info("杭州卡号和系统编号获取成功!");
			
			com.changpeng.system.util.CommonDatas.getGroups();
			com.changpeng.system.util.CommonDatas.getUsers();
			com.changpeng.system.util.CommonDatas.getLawyers();
			com.changpeng.lessons.util.CommonDatas.getAlllessons();

		
		} catch (ServiceException e) {
			LOG.error("系统启动初始化权限列表为空" + e);
		}
		
		String contextid = System.currentTimeMillis() / 1000 + "";
		event.getServletContext().setAttribute("CONTEXTID", contextid);
		LOG.info("系统启动初始化,系统ID......" + contextid);
	}
	
	private void getSysteNos()throws ServiceException{
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		BasicService service=(BasicService)wac.getBean("basicService");
		String sql="select systemno,cardno from sys_systemcardno";
		List list=service.findBySqlQuery(sql);
		if(list!=null&&list.size()>0){
			com.changpeng.common.CommonDatas.AllSystemNos.clear();
			synchronized(com.changpeng.common.CommonDatas.AllSystemNos){
			
				for(int i=0;i<list.size();i++){
					Object[] obj=(Object[])list.get(i);
					com.changpeng.common.CommonDatas.AllSystemNos.put(obj[0].toString(), obj[1].toString());
				}
			}
		}
	}
}
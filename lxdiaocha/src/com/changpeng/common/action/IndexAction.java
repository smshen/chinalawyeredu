/**
 * IndexAction.java
 */
package com.changpeng.common.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.changpeng.common.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 * @author 华锋
 * 2008-3-4 下午10:31:14
 *
 */
public class IndexAction extends ActionSupport {
	private String loginname;
	private String password;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	public String execute() throws Exception {

		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		Cookie[] cookies = request.getCookies();
		int length = cookies == null ? 0 : cookies.length;
		for (int i = 0; i < length; i++) {
			if (cookies[i].getName().equals(Constants.COOKIE_LOGINNAME)) {
				this.loginname = cookies[i].getValue();
			}
			if (cookies[i].getName().equals(Constants.COOKIE_LOGINNAME)) {
				this.password = cookies[i].getValue();
			}
		}
		return SUCCESS;
	}

	public String getPassword() {
		return this.password;
	}

	public String getLoginname() {
		return this.loginname;
	}
}
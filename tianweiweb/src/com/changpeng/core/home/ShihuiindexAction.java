package com.changpeng.core.home;


import java.util.List;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.InfoService;

public class ShihuiindexAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	List shihuilist;
	
	public ShihuiindexAction() {
		this.rightCode="PUBLIC";
		this.moduleid = 11;
	}

	@Override
	protected String go() throws Exception {
		InfoService userservice = (InfoService) this.getBean("infoService");
		shihuilist=userservice.getShihuiindex();
		return SUCCESS;
	}

	public List getShihuilist() {
		return shihuilist;
	}
}

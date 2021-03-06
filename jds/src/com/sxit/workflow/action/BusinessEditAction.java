package com.sxit.workflow.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.*;
import com.sxit.system.model.TsysModule;
import java.util.List;
import org.hibernate.Query;




/**
 *
 * <p>功能： 编辑业务</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-11-13</p>
 * @版本： V1.0
 * @修改：
 */

public class BusinessEditAction extends AbstractAction {

	private TwflBusiness business;
        private List<TsysModule> modulelist;
	public BusinessEditAction() {
          rights="wfl2,4";
	}

	public String go() throws HibernateException {
                getSession().update(business);
		set("business", business);
                nextpage="businessList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TwflBusiness getBusiness() {
         if (business==null)
            business = (TwflBusiness) get("business");
          return business;
	}
        private Query getQuery() throws HibernateException {
                String queryName ;
                queryName="from TsysModule as module order by module.moduleorder";
                Query query = getSession().createQuery(queryName);
                return query;
        }
        public List getModulelist() {
          return modulelist;
        }
        public String input() throws Exception {
            modulelist=getQuery().list();
            return "input";
    }


}

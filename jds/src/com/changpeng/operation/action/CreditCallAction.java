package com.changpeng.operation.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;

import com.changpeng.operation.model.*;
import com.sxit.common.action.AbstractListAction;


/**
 * 电话催收
 * @author sinhoo
 * Jun 12, 2009
 */
public class CreditCallAction extends AbstractListAction  {
        private List tasklist;
        private long bankid;
        private String consigntype;
        private String consignflag;
        private String username;
        private String creditcard;
        private String idcard;
        private String consigndate;
        private String paydate;
        private String canlink;
        private String curdate;
        private int tdflag; //0:否 1:全部 2:是
        private int repaystatus; //0:否 1:全部 2:是
        public int getRepaystatus() {
			return repaystatus;
		}
		public void setRepaystatus(int repaystatus) {
			this.repaystatus = repaystatus;
		}
		public String getCurdate() {
			return curdate;
		}
		public void setCurdate(String curdate) {
			this.curdate = curdate;
		}
        public String getCanlink() {
			return canlink;
		}
		public void setCanlink(String canlink) {
			this.canlink = canlink;
		}
        public String getPaydate() {
			return paydate;
		}
		public void setPaydate(String paydate) {
			this.paydate = paydate;
		}
		public String getIdcard() {
			return idcard;
		}
		public String getConsigndate() {
			return consigndate;
		}
		public void setIdcard(String idcard) {
			this.idcard = idcard;
		}
		public void setConsigndate(String consigndate) {
			this.consigndate = consigndate;
		}
		public CreditCallAction() {
          rights="opr4,1";
        }
        public String go() throws HibernateException {
        	
                /*Criteria criteria = getSession().createCriteria(ToprCredittask.class);
                criteria.add(Expression.eq("userid", curuser.getUserid()));
              //催收中
                criteria.add(Expression.eq("taskstat", 0));    
                
                if(paydate!=null&&!"".equals(paydate)){
                 	//criteria.add(Expression.eq("paydate", paydate));
                	String now=new java.text.SimpleDateFormat("yyyy-MM-dd 00:00").format(new java.util.Date());
                 	criteria.add(Expression.between("paydate", now, paydate+" 23:59"));
                 }
                
                Criteria c2=criteria.createCriteria("toprCreditcard").add(Expression.ne("repaystatus", 2)); //剔除掉已全清的
        		
        		if (bankid != 0)
        			c2.add(Expression.eq("bankid", bankid));        		
        		if (consigntype!= null&&!"".equals(consigntype))
        			c2.add(Expression.eq("consigntype", consigntype));
        		if (consignflag!= null&&!"".equals(consignflag))
            		c2.add(Expression.eq("consignflag", consignflag));
        		if (username!= null&&!"".equals(username))
        			c2.add(Expression.like("username", username,MatchMode.ANYWHERE));
        		if (idcard!= null&&!"".equals(idcard))
        			c2.add(Expression.like("idcard", idcard,MatchMode.ANYWHERE));
        		if (creditcard!= null&&!"".equals(creditcard))
            		c2.add(Expression.like("creditcard", creditcard,MatchMode.ANYWHERE));
        		if (consigndate!= null&&!"".equals(consigndate))
            		c2.add(Expression.eq("consigndate", consigndate));
        		
        		
        		tasklist = c2.setMaxResults(maxperpage).setFirstResult(
        				maxperpage * pagenumber).setCacheable(true).list();*/
        	tasklist= getQuery().list();
                return SUCCESS;
        }
        
        private Query getQuery() throws HibernateException {
            String queryName="from ToprCredittask a,ToprCreditcard b where a.toprCreditcard.creditcardid=b.creditcardid  and a.taskstat=0";
          
            if(repaystatus==0) //不显示全清的
            	queryName+="  and b.repaystatus<>2";	
            else if(repaystatus==2) //仅显示全清的
            	queryName+="  and b.repaystatus=2";	
            
            
            if(tdflag==0) //不显示退单的
            	queryName+="  and b.tdflag=0";	
            else if(tdflag==2) //仅显示退单的
            	queryName+="  and b.tdflag<>0";	
            
            
            if (bankid != 0)
            	queryName+=" and b.bankid="+bankid;	
    		if (consigntype!= null&&!"".equals(consigntype))
    			queryName+=" and b.consigntype='"+consigntype+"'";
    		if (consignflag!= null&&!"".equals(consignflag))
    			queryName+=" and b.consignflag='"+consignflag+"'";
    		if (username!= null&&!"".equals(username))
    			queryName+=" and b.username like '%"+username+"%'";
    		if (creditcard!= null&&!"".equals(creditcard))
    			queryName+=" and b.creditcard like '%"+creditcard+"%'";
    		if (idcard!= null&&!"".equals(idcard))
    			queryName+=" and b.idcard like '%"+idcard+"%'";
    		if (consigndate!= null&&!"".equals(consigndate))
    			queryName+=" and b.consigndate='"+consigndate+"'";
    		if(paydate!=null&&!"".equals(paydate)){
             	//criteria.add(Expression.eq("paydate", paydate));
            	String now=new java.text.SimpleDateFormat("yyyy-MM-dd 00:00").format(new java.util.Date());
             //	criteria.add(Expression.between("paydate", now, paydate));
             	queryName+=" and a.paydate>='"+now+"' and a.paydate<='"+paydate+" 23:59'";
             }
    		if (curdate!= null&&!"".equals(curdate)){
             	queryName+=" and a.paydate>='"+curdate+" 00:00' and a.paydate<='"+curdate+" 23:59'";
    		}
    		
    		if(canlink!=null&&!"".equals(canlink)){
              	 if(!"y".equals(canlink))
              		queryName+=" and a.canlink='"+canlink+"'";
              	 else //能联系上
              		queryName+=" and (a.canlink='y' or a.canlink is null)";
              	 
               }
    		queryName+=" and a.userid="+curuser.getUserid(); 
    		//queryName+=" and b.repaystatus<>2";
    	    basicDao.setSession(getSession());
            recordsize=basicDao.getCountOfQuery(queryName);
            
            System.out.println("recordsize==="+recordsize);
            
    		queryName="select a "+queryName+" order by to_number(b.curcnfee) desc";
    		
    		 System.out.println("queryName==="+queryName);
             
    		
            Query query = getSession().createQuery(queryName);
//            recordsize = query.list().size();
            pagesize = (recordsize - 1) / maxperpage + 1;
            pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
            return query;
    }
        
        public List getTasklist() {
          return tasklist;
        }
		public long getBankid() {
			return bankid;
		}
		public String getConsigntype() {
			return consigntype;
		}
		public String getConsignflag() {
			return consignflag;
		}
		public String getUsername() {
			return username;
		}
		public String getCreditcard() {
			return creditcard;
		}
		public void setBankid(long bankid) {
			this.bankid = bankid;
		}
		public void setConsigntype(String consigntype) {
			this.consigntype = consigntype;
		}
		public void setConsignflag(String consignflag) {
			this.consignflag = consignflag;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public void setCreditcard(String creditcard) {
			this.creditcard = creditcard;
		}

}

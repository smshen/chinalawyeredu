package com.sxit.workflow.model;
// Generated 2007-11-20 15:29:37 by Hibernate Tools 3.2.0.b9


import com.sxit.system.model.TsysUser;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TwflProcess generated by hbm2java
 */
public class TwflProcess  implements java.io.Serializable {


     private int processid;
     private String processname;
     private String description;
     private Timestamp createtime;
     private int statusid;
     private int usetype;
     private int xposition;
     private int yposition;
     private int newnodeid;
     private TwflBusiness business;
     private Set<TwflNode> twflNodes = new HashSet<TwflNode>(0);
     private TsysUser author;

    public TwflProcess() {
    }

    public TwflProcess(String processname, String description, Timestamp createtime, int statusid, int usetype, int xposition, int yposition, int newnodeid, TwflBusiness business, Set<TwflNode> twflNodes, TsysUser author) {
       this.processname = processname;
       this.description = description;
       this.createtime = createtime;
       this.statusid = statusid;
       this.usetype = usetype;
       this.xposition = xposition;
       this.yposition = yposition;
       this.newnodeid = newnodeid;
       this.business = business;
       this.twflNodes = twflNodes;
       this.author = author;
    }
   
    public int getProcessid() {
        return this.processid;
    }
    
    public void setProcessid(int processid) {
        this.processid = processid;
    }
    public String getProcessname() {
        return this.processname;
    }
    
    public void setProcessname(String processname) {
        this.processname = processname;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Timestamp getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
    public int getStatusid() {
        return this.statusid;
    }
    
    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }
    public int getUsetype() {
        return this.usetype;
    }
    
    public void setUsetype(int usetype) {
        this.usetype = usetype;
    }
    public int getXposition() {
        return this.xposition;
    }
    
    public void setXposition(int xposition) {
        this.xposition = xposition;
    }
    public int getYposition() {
        return this.yposition;
    }
    
    public void setYposition(int yposition) {
        this.yposition = yposition;
    }
    public int getNewnodeid() {
        return this.newnodeid;
    }
    
    public void setNewnodeid(int newnodeid) {
        this.newnodeid = newnodeid;
    }
    public TwflBusiness getBusiness() {
        return this.business;
    }
    
    public void setBusiness(TwflBusiness business) {
        this.business = business;
    }
    public Set<TwflNode> getTwflNodes() {
        return this.twflNodes;
    }
    
    public void setTwflNodes(Set<TwflNode> twflNodes) {
        this.twflNodes = twflNodes;
    }
    public TsysUser getAuthor() {
        return this.author;
    }
    
    public void setAuthor(TsysUser author) {
        this.author = author;
    }




}



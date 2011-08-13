package com.sxit.member.model;
// Generated 2008-5-28 10:53:41 by Hibernate Tools 3.2.0.b9


import java.sql.Timestamp;

/**
 * TmemStudyexp generated by hbm2java
 */
public class TmemStudyexp  implements java.io.Serializable {


     private int studyexpid;
     private TmemResume tmemResume;
     private String education;
     private String school;
     private Timestamp begindate;
     private Timestamp enddate;
     private String major;
     private String certification;
     private String skills;

    public TmemStudyexp() {
    }

    public TmemStudyexp(TmemResume tmemResume, String education, String school, Timestamp begindate, Timestamp enddate, String major, String certification, String skills) {
       this.tmemResume = tmemResume;
       this.education = education;
       this.school = school;
       this.begindate = begindate;
       this.enddate = enddate;
       this.major = major;
       this.certification = certification;
       this.skills = skills;
    }
   
    public int getStudyexpid() {
        return this.studyexpid;
    }
    
    public void setStudyexpid(int studyexpid) {
        this.studyexpid = studyexpid;
    }
    public TmemResume getTmemResume() {
        return this.tmemResume;
    }
    
    public void setTmemResume(TmemResume tmemResume) {
        this.tmemResume = tmemResume;
    }
    public String getEducation() {
        return this.education;
    }
    
    public void setEducation(String education) {
        this.education = education;
    }
    public String getSchool() {
        return this.school;
    }
    
    public void setSchool(String school) {
        this.school = school;
    }
    public Timestamp getBegindate() {
        return this.begindate;
    }
    
    public void setBegindate(Timestamp begindate) {
        this.begindate = begindate;
    }
    public Timestamp getEnddate() {
        return this.enddate;
    }
    
    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
    }
    public String getMajor() {
        return this.major;
    }
    
    public void setMajor(String major) {
        this.major = major;
    }
    public String getCertification() {
        return this.certification;
    }
    
    public void setCertification(String certification) {
        this.certification = certification;
    }
    public String getSkills() {
        return this.skills;
    }
    
    public void setSkills(String skills) {
        this.skills = skills;
    }




}



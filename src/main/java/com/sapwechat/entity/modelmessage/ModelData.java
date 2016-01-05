package com.sapwechat.entity.modelmessage;

public class ModelData {

  private DataDes first;

  private DataDes InterviewDate;
  
  private DataDes checkindate;

  private DataDes jobTitle;

  public DataDes getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(DataDes jobTitle) {
    this.jobTitle = jobTitle;
  }

  private DataDes remark;

  public DataDes getFirst() {
    return first;
  }

  public void setFirst(DataDes first) {
    this.first = first;
  }

  public DataDes getCheckindate() {
    return checkindate;
  }

  public void setCheckindate(DataDes checkindate) {
    this.checkindate = checkindate;
  }

  public DataDes getInterviewDate() {
    return InterviewDate;
  }

  public void setInterviewDate(DataDes interviewDate) {
    InterviewDate = interviewDate;
  }

  public DataDes getRemark() {
    return remark;
  }

  public void setRemark(DataDes remark) {
    this.remark = remark;
  }

}

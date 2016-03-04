package com.sapwechat.entity.modelmessage;

public class ModelData {

  private DataDes first;

  private DataDes InterviewDate;

  private DataDes onboardDate;

  private DataDes jobTitle;

  private DataDes remark;

  public DataDes getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(DataDes jobTitle) {
    this.jobTitle = jobTitle;
  }

  public DataDes getFirst() {
    return first;
  }

  public void setFirst(DataDes first) {
    this.first = first;
  }

  public DataDes getOnboardDate() {
    return onboardDate;
  }

  public void setOnboardDate(DataDes onboardDate) {
    this.onboardDate = onboardDate;
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

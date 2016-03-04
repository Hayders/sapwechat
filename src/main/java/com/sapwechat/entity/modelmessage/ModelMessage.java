package com.sapwechat.entity.modelmessage;

public class ModelMessage {

  private String touser;

  private String template_id;

  private String url;

  private String interviewDate;

  private String jobTitle;

  private String onboardDate;

  public String getInterviewDate() {
    return interviewDate;
  }

  public void setInterviewDate(String interviewDate) {
    this.interviewDate = interviewDate;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public String getOnboardDate() {
    return onboardDate;
  }

  public void setOnboardDate(String onboardDate) {
    this.onboardDate = onboardDate;
  }

  private ModelData data;

  public String getTouser() {
    return touser;
  }

  public void setTouser(String touser) {
    this.touser = touser;
  }

  public String getTemplate_id() {
    return template_id;
  }

  public void setTemplate_id(String template_id) {
    this.template_id = template_id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public ModelData getData() {
    return data;
  }

  public void setData(ModelData data) {
    this.data = data;
  }

}

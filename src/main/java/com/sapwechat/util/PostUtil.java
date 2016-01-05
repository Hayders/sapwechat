package com.sapwechat.util;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.ParseException;
import com.sapwechat.entity.modelmessage.ModelMessage;

public class PostUtil {

  public static String touser = "oX7wvwUYGxTRGOs1CJBItBcSf5tA"; //

  // public static String touser = "oX7wvwfMBEbOzuBsMtFbnm4H4cWQ"; // lisa

  // public static String touser = "oX7wvwc-MWrfPJw66yBsu82d1Cl8"; //Fiona


  /**
   * interview Pre
   * @param token
   * @throws ParseException
   * @throws IOException
   */
  public static void postPreModelMessage(String token) throws ParseException,
      IOException {

    ModelMessage modelMessage = new ModelMessage();
    modelMessage.setTemplate_id("C24U97tgWasBpclRdleHZILpKPGue5aH52QThLdKn8M");
    modelMessage.setInterviewDate("2016-03-02 17:00");
    modelMessage.setJobTitle("Software Developer");

    String modelMessage1 = JSONObject.fromObject(
        MessageUtil.initPreModelMessage(touser, modelMessage.getTemplate_id(),
            modelMessage.getInterviewDate(), modelMessage.getJobTitle()))
        .toString();
    int result;
    try {
      result = MessageUtil.postModelMessage(token, modelMessage1);
      if (result == 0) {
        System.out.println("success");
        System.out.println(modelMessage1);
      }
    } catch (ParseException | IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * post message before few minutes
   * @param token
   * @throws ParseException
   * @throws IOException
   */
  public static void postMidModelMessage(String token) throws ParseException,
      IOException {

    ModelMessage modelMessage = new ModelMessage();
    modelMessage.setTemplate_id("48iI_oHltaaw1dZHq0YfunjPcgVW5-k-DkSb6mz4E4A");
    modelMessage.setInterviewDate("2016-03-02 17:00");
    modelMessage.setJobTitle("Software Developer");

    String modelMessage1 = JSONObject.fromObject(
        MessageUtil.initMidModelMessage(touser, modelMessage.getTemplate_id(),
            modelMessage.getInterviewDate(), modelMessage.getJobTitle()))
        .toString();
    int result;
    try {
      result = MessageUtil.postModelMessage(token, modelMessage1);
      if (result == 0) {
        System.out.println("success");
        System.out.println(modelMessage1);
      }
    } catch (ParseException | IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * check in message
   * @param token
   * @throws ParseException
   * @throws IOException
   */
  public static void postLastModelMessage(String token) throws ParseException,
      IOException {

    ModelMessage modelMessage = new ModelMessage();
    modelMessage.setTemplate_id("HR7xot4ZiuyXTSm7cWMfDyruH-9ohdsEKGrnzALqzK8");
    modelMessage.setCheckindate("2016-04-02 8:00");
    modelMessage.setJobTitle("Software Developer");

    String modelMessage1 = JSONObject.fromObject(
        MessageUtil.initLastModelMessage(touser, modelMessage.getTemplate_id(),
            modelMessage.getCheckindate(), modelMessage.getJobTitle()))
        .toString();
    int result;
    try {
      result = MessageUtil.postModelMessage(token, modelMessage1);
      if (result == 0) {
        System.out.println("success");
        System.out.println(modelMessage1);
      }
    } catch (ParseException | IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * create menu
   * @param token
   */
  public static void createMenu(String token) {
    String menuString = JSONObject.fromObject(WechatUtil.initMenu(touser))
        .toString();
    int result;
    try {
      result = WechatUtil.createMenu(token, menuString);
      if (result == 0) {
        System.out.println("success");
        System.out.println(menuString);
      }
    } catch (ParseException | IOException e) {
      e.printStackTrace();
    }
  }

}

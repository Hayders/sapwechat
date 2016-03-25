package com.sapwechat.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;
import net.sf.json.util.NewBeanInstanceStrategy;

import org.apache.http.ParseException;

import com.sapwechat.entity.modelmessage.ModelMessage;

public class PostUtil {

    public static  String to_user = "oX7wvwUYGxTRGOs1CJBItBcSf5tA"; //Xueshi
    
//    public static  String to_user = "oX7wvwVu7bsmY2D4cfdA0WCwYFM0"; //Rechard
  
//    public static String to_user = "oX7wvwfQqKQ2M47G8m9qjyfBa-x8"; //Terry

//    public static String to_user = "oX7wvwfMBEbOzuBsMtFbnm4H4cWQ"; // lisa

//    public static String to_user = "oX7wvwc-MWrfPJw66yBsu82d1Cl8"; //Fiona
   
   //public static String to_user = "oX7wvwbMqeIJWDzZgTQK7EL3fhJo"; //wenchang
   private static SimpleDateFormat dFormat = null;

  /**
   * interview Pre
   * 
   * @param token
   * @throws ParseException
   * @throws IOException
   */
   
   
  public static void postPreModelMessage(String token) throws ParseException,
      IOException {
    dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ModelMessage modelMessage = new ModelMessage();
    modelMessage.setTemplate_id("TQGgFCbx6OiTWRh_BhuZDAIHuK21UqcySc_56PALB2Y"); //English
//    modelMessage.setTemplate_id("dYpxQwaV7RT_D35H7WglVzCVo3dIUgmUpfj1CFJ-BHc"); //Chinese
    modelMessage.setInterviewDate(dFormat.format(new Date()));
    modelMessage.setJobTitle("Senior Service Sales");  
//    modelMessage.setJobTitle("销售总监");

    String modelMessage1 = JSONObject.fromObject(
        MessageUtil.initPreModelMessage(to_user, modelMessage.getTemplate_id(),
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
   * 
   * @param token
   * @throws ParseException
   * @throws IOException
   */
  public static void postMidModelMessage(String token) throws ParseException,
      IOException {

    dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ModelMessage modelMessage = new ModelMessage();
    modelMessage.setTemplate_id("EzpUe9uwC2wdTVWSkBVQaSghKsEcZXEc_bqYs2BGieo"); //English
//    modelMessage.setTemplate_id("Juz-zK1y6y0-SHLnw_MibecxJYueL8RQgiu78aiAG28");
    modelMessage.setInterviewDate(dFormat.format(new Date()));
    modelMessage.setJobTitle("Senior Service Sales");
//    modelMessage.setJobTitle("销售总监");

    String modelMessage1 = JSONObject.fromObject(
        MessageUtil.initMidModelMessage(to_user, modelMessage.getTemplate_id(),
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
   * 
   * @param token
   * @throws ParseException
   * @throws IOException
   */
  public static void postLastModelMessage(String token) throws ParseException,
      IOException {

    ModelMessage modelMessage = new ModelMessage();
    modelMessage.setTemplate_id("MYJLGe9ekwIMA_jKuu2OFF4exT6FAcdv_nSaujkDEes");//English
//    modelMessage.setTemplate_id("ley3Jf-XXus6jDDCiBWhgZotHTbBeNVoeuFCUYyQIFI");//Chinese
    modelMessage.setOnboardDate("2016-04-02 10:00");
    modelMessage.setJobTitle("Senior Service Sales");
//    modelMessage.setJobTitle("销售总监");
    
    String modelMessage1 = JSONObject.fromObject(
        MessageUtil.initLastModelMessage(to_user, modelMessage.getTemplate_id(),
            modelMessage.getOnboardDate(), modelMessage.getJobTitle()))
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
   * 
   * @param token
   */
  public static void createMenu(String token) {
    String menuString = JSONObject.fromObject(WechatUtil.initMenu())
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

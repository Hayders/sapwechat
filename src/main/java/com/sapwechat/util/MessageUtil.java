package com.sapwechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.http.ParseException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sapwechat.entity.message.News;
import com.sapwechat.entity.message.NewsMessage;
import com.sapwechat.entity.message.TextMessage;
import com.sapwechat.entity.modelmessage.DataDes;
import com.sapwechat.entity.modelmessage.ModelData;
import com.sapwechat.entity.modelmessage.ModelMessage;
import com.sapwechat.entity.util.UserBascInfo;
import com.thoughtworks.xstream.XStream;

public class MessageUtil {

  public static final String MESSAGE_TEXT = "text";

  public static final String MESSAGE_NEW = "news";

  public static final String MESSAGE_EVENT = "event";

  public static final String MESSAGE_SUBSCRIBE = "subscribe";

  public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";

  public static final String MESSAGE_CLICK = "CLICK";

  public static final String MESSAGE_VIEW = "VIEW";

  public static final String MESSAGE_SCANCODE = "scancode_push";

  /**
   * xml to map
   * 
   * @param request
   * @return
   * @throws IOException
   * @throws DocumentException
   */
  public static Map<String, String> Xml2Map(HttpServletRequest request)
      throws IOException, DocumentException {
    Map<String, String> map = new HashMap<String, String>();
    SAXReader reader = new SAXReader();

    InputStream ins = request.getInputStream();
    Document doc = reader.read(ins);

    Element root = doc.getRootElement();
    List<Element> list = root.elements();

    for (Element e : list) {
      map.put(e.getName(), e.getText());
    }

    ins.close();
    return map;
  }

  /**
   * textmessage to xml
   * 
   * @param textMessage
   * @return
   */
  public static String TextMessage2Xml(TextMessage textMessage) {

    XStream xStream = new XStream();
    xStream.alias("xml", textMessage.getClass());
    return xStream.toXML(textMessage);

  }

  /**
   * init textmessage
   * 
   * @param toUserName
   * @param fromUserName
   * @param userBascInfo
   * @return
   */
  public static String initTextMessage(String toUserName, String fromUserName,
      UserBascInfo userBascInfo) {
    TextMessage text = new TextMessage();
    // exchange the fromuser and touser
    text.setFromUserName(toUserName);
    text.setToUserName(fromUserName);
    text.setMsgType(MESSAGE_TEXT);
    text.setCreateTime("" + new Date().getTime());
    text.setContent(menuText(userBascInfo));
    return TextMessage2Xml(text);
  }

  /**
   * news message to xml
   * 
   * @param newsMessage
   * @return
   */
  public static String NewsMessage2Xml(NewsMessage newsMessage) {

    XStream xStream = new XStream();
    xStream.alias("xml", newsMessage.getClass());
    xStream.alias("item", new News().getClass());
    return xStream.toXML(newsMessage);
  }

  /**
   * init news message
   * 
   * @param toUserName
   * @param fromUserName
   * @return
   */
  public static String initRecruitNewsMessage(String toUserName,
      String fromUserName, UserBascInfo userBascInfo) {
    String message = null;
    List<News> newsList = new ArrayList<News>();
    NewsMessage newsMessage = new NewsMessage();

    // 中文版
     News news1 = new News();
     news1.setTitle("【SAP热招】寻找机会的你看过来");
     news1.setDescription("如果您具备计算机或者其他理工科背景，具任何中间件技术或基础网络通信的经验或兴趣，具快速学习、分析解决问题能力，良好的英语，请与我们联系。");
     news1.setPicUrl("http://wx.sh-ruida.com/"
     + "sapwechat/static/img/saprecruitment.jpg");
     news1.setUrl(WechatUtil.REDIRECT_URI + "?" + "wechatId="
     + userBascInfo.getOpenid());

    // 英文版
//    News news1 = new News();
//    news1.setTitle("【Hot Jobs】New opening for this week!");
//    news1
//        .setDescription("It’s time you realize your full potential and "
//            + "explore all that SAP has to offer you. Below are various opportunities "
//            + "to grow your career at SAP. If you have friends/contacts in your network "
//            + "with suitable skills for the positions below, please refer them to us.");
//    news1.setPicUrl("http://wx.sh-ruida.com/"
//        + "sapwechat/static/img/saprecruitment.jpg");
//    news1.setUrl(WechatUtil.REDIRECT_URI + "?" + "wechatId="
//        + userBascInfo.getOpenid());

    /*
     * News news1 = new News(); news1.setTitle("SAP Wechat Recruitment");
     * news1.setDescription("Candidates are not willing to " + "maintain their resumes at each company’s recruiting " +
     * "site as  they already have resumes maintained in popular " + "recruitment site ( 51job, zhilian, etc)");
     * news1.setPicUrl("http://wx.sh-ruida.com/" + "sapwechat/static/img/saprecruitment.jpg");
     * news1.setUrl(WechatUtil.REDIRECT_URI + "?" + "wechatId=" + userBascInfo.getOpenid()); News news2 = new News();
     * news2.setTitle("SAP Resume Collection");
     * news2.setDescription("HR seeking a easier way to publish the recruiting " +
     * "information and get talent’s resume at anywhere and anytime"); news2.setPicUrl("http://wx.sh-ruida.com" +
     * "/sapwechat/static/img/sapwechat.jpg"); news2.setUrl("http://wx.sh-ruida.com/rcs" + "?" + "wechatId=" +
     * userBascInfo.getOpenid());
     */

    newsList.add(news1);
    // newsList.add(news2);

    newsMessage.setFromUserName(toUserName);
    newsMessage.setToUserName(fromUserName);
    newsMessage.setMsgType(MESSAGE_NEW);
    newsMessage.setCreateTime("" + new Date().getTime());
    newsMessage.setArticles(newsList);
    newsMessage.setArticleCount(newsList.size());

    message = NewsMessage2Xml(newsMessage);

    return message;
  }

  public static String initNewsMessage(String toUserName, String fromUserName,
      UserBascInfo userBascInfo) {
    String message = null;
    List<News> newsList = new ArrayList<News>();
    NewsMessage newsMessage = new NewsMessage();

    //中文版
    News news1 = new News();
    news1.setTitle("与SAP全球CEO孟鼎铭一起开启你的逐梦之旅!");
    news1
        .setDescription("14日，SAP全球CEO孟鼎铭(Bill McDermott)做客清华大学经济管理学院企业家讲堂，面向数百位青年学子，以”逐梦之旅“(Winners Dream)为题发表了精彩演讲。");
    news1.setPicUrl("http://wx.sh-ruida.com/sapwechat/static/img/Bill.jpg");
    news1.setUrl(WechatUtil.REDIRECT_URI + "?" + "wechatId="
        + userBascInfo.getOpenid());
    
    //英文版
//    News news1 = new News();
//    news1.setTitle("Winning the Next Generation for SAP");
//    news1
//        .setDescription("Bill McDermott seized the opportunity of the FKOM"
//            + " week in Singapore and China to share his life story with young people,"
//            + " and encouraged them to dream and to stay true to their values.");
//    news1.setPicUrl("http://wx.sh-ruida.com/sapwechat/static/img/Bill.jpg");
//    news1.setUrl("https://blogs.wdf.sap.corp/sapnews_en/2016/01/winning-the-next-generation-for-sap/");

    newsList.add(news1);

    newsMessage.setFromUserName(toUserName);
    newsMessage.setToUserName(fromUserName);
    newsMessage.setMsgType(MESSAGE_NEW);
    newsMessage.setCreateTime("" + new Date().getTime());
    newsMessage.setArticles(newsList);
    newsMessage.setArticleCount(newsList.size());

    message = NewsMessage2Xml(newsMessage);

    return message;
  }

  /**
   * init PreModelMessage
   * 
   * @param touser
   * @param template_id
   * @param interviewDate
   * @param interviewLink
   * @return
   */
  public static ModelMessage initPreModelMessage(String touser,
      String template_id, String interviewDateValue, String jobTitleValue) {

    String color = "#173177";

    ModelMessage modelMessage = new ModelMessage();
    ModelData modelData = new ModelData();

    DataDes firstDataDes = new DataDes();
//    firstDataDes.setValue("Your resume has passed the screening process, "
//        + "and now you are invited to our interview!");
    firstDataDes.setValue("你的简历已通过筛选流程，具体面试情况安排如下：");
    
    firstDataDes.setColor(color);

    DataDes interviewDateDataDes = new DataDes();
    interviewDateDataDes.setValue(interviewDateValue);
    interviewDateDataDes.setColor(color);

    DataDes jobTitleValueDataDes = new DataDes();
    jobTitleValueDataDes.setValue(jobTitleValue);
    jobTitleValueDataDes.setColor(color);

    DataDes remarkDataDes = new DataDes();
//    remarkDataDes.setValue("Please be punctual for the  interview! ");
    remarkDataDes.setValue("请准时参加面试，如有疑问，请及时联系HR同事。");
    remarkDataDes.setColor(color);

    modelData.setFirst(firstDataDes);
    modelData.setInterviewDate(interviewDateDataDes);
    modelData.setJobTitle(jobTitleValueDataDes);
    modelData.setRemark(remarkDataDes);

    modelMessage.setTouser(touser);
    modelMessage.setTemplate_id(template_id);
    modelMessage
        .setUrl("http://wx.sh-ruida.com/rcs/wechat/index/sap#/interviewInfo?interviewId=1");
    modelMessage.setData(modelData);

    return modelMessage;

  }

  /**
   * init MidModelMessage
   * 
   * @param touser
   * @param template_id
   * @param interviewDateValue
   * @param jobTitleValue
   * @return
   */
  public static ModelMessage initMidModelMessage(String touser,
      String template_id, String interviewDateValue, String jobTitleValue) {

    String color = "#173177";

    ModelMessage modelMessage = new ModelMessage();
    ModelData modelData = new ModelData();

    DataDes firstDataDes = new DataDes();
//    firstDataDes.setValue("Your interview is coming in a minutes!");
    firstDataDes.setValue("您的面试马上开始啦！");
    firstDataDes.setColor(color);

    DataDes interviewDateDataDes = new DataDes();
    interviewDateDataDes.setValue(interviewDateValue);
    interviewDateDataDes.setColor(color);

    DataDes jobTitleValueDataDes = new DataDes();
    jobTitleValueDataDes.setValue(jobTitleValue);
    jobTitleValueDataDes.setColor(color);

    DataDes remarkDataDes = new DataDes();
//    remarkDataDes.setValue("Please tip me to attend the interview");
    remarkDataDes.setValue("请点击这里参加面试！");
    remarkDataDes.setColor(color);

    modelData.setFirst(firstDataDes);
    modelData.setInterviewDate(interviewDateDataDes);
    modelData.setJobTitle(jobTitleValueDataDes);
    modelData.setRemark(remarkDataDes);

    modelMessage.setTouser(touser);
    modelMessage.setTemplate_id(template_id);
//    modelMessage.setUrl("http://wx.sh-ruida.com/rcs/wechat/sap" + "?"
//        + "wechatId=" + touser + "#/interviewInfo?interviewId=1");
    modelMessage
    .setUrl("http://wx.sh-ruida.com/rcs/wechat/index/sap#/interviewInfo?interviewId=1");
    modelMessage.setData(modelData);

    return modelMessage;

  }

  public static ModelMessage initLastModelMessage(String touser,
      String template_id, String onboardDateValue, String jobTitleValue) {

    String color = "#173177";

    ModelMessage modelMessage = new ModelMessage();
    ModelData modelData = new ModelData();

    DataDes firstDataDes = new DataDes();
//    firstDataDes.setValue("Congratulations! You have passed the interview");
    firstDataDes.setValue("祝贺你，你已通过面试，具体入职安排如下：");
    firstDataDes.setColor(color);

    DataDes onboarddateDataDes = new DataDes();
    onboarddateDataDes.setValue(onboardDateValue);
    onboarddateDataDes.setColor(color);

    DataDes jobTitleValueDataDes = new DataDes();
    jobTitleValueDataDes.setValue(jobTitleValue);
    jobTitleValueDataDes.setColor(color);

    DataDes remarkDataDes = new DataDes();
//    remarkDataDes.setValue("We look forward to your join in SAP!");
    remarkDataDes.setValue("我们期待你的加入！");
    remarkDataDes.setColor(color);

    modelData.setFirst(firstDataDes);
    modelData.setOnboardDate(onboarddateDataDes);
    modelData.setJobTitle(jobTitleValueDataDes);
    modelData.setRemark(remarkDataDes);

    modelMessage.setTouser(touser);
    modelMessage.setTemplate_id(template_id);
    modelMessage.setUrl("http://wx.sh-ruida.com/rcs/wechat/sap?wechatId="
        + touser
        + "#/personalCenter");
    modelMessage.setData(modelData);

    return modelMessage;

  }

  public static int postModelMessage(String token, String modelMessage)
      throws ParseException, IOException {
    int result = 0;
    String url = WechatUtil.POST_MODEL_MESSAGE.replace("ACCESS_TOKEN", token);
    JSONObject jsonObject = WechatUtil.doPostStr(url, modelMessage);
    if (jsonObject != null) {
      result = jsonObject.getInt("errcode");
    }
    return result;
  }

  /**
   * encapsulation text
   * 
   * @param userBascInfo
   * @return
   */
  public static String menuText(UserBascInfo userBascInfo) {
    StringBuffer sb = new StringBuffer();
    sb.append("OpenId:");
    sb.append(userBascInfo.getOpenid()).append("\r\n");
    sb.append("Subscribe:");
    sb.append(userBascInfo.getSubscribe());
    return sb.toString();
  }

}

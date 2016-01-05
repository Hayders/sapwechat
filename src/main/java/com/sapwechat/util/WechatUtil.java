package com.sapwechat.util;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

import com.sapwechat.entity.util.AccessToken;
import com.sapwechat.entity.util.UserBascInfo;
import com.sapwechat.entity.menu.Button;
import com.sapwechat.entity.menu.ClickButton;
import com.sapwechat.entity.menu.Menu;
import com.sapwechat.entity.menu.ViewButton;

public class WechatUtil {

  // wechat public account , portion APIs
  // public static final String APPID = "wxc701e9d4bd0e0b46"; // personal
  // public static final String APPSECRET = "13eb9b3debbe8cf806f4adbab7352679";

  // test wechat account , all APIs
  public static final String APPID = "wxde611500e7346f8d";

  public static final String APPSECRET = "d4624c36b6795d1d99dcf0547af5443d";

  // public static final String SCOPE = "snsapi_base";
  public static final String SCOPE = "snsapi_userinfo";

  // public static final String REDIRECT_URI = "http://139.196.39.17/rcs/wechat/sap";
  public static final String REDIRECT_URI = "http://wx.sh-ruida.com/rcs/wechat/sap";

  // public static final String REDIRECT_URI = "http://www.qq.com/login.html";

  // public APIS
  public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

  public static final String USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

  public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

  // Authorized user basic information page APIS
  public static final String GET_CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

  public static final String POST_MODEL_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

  /**
   * get url
   * 
   * @param url
   * @return
   * @throws ClientProtocolException
   * @throws IOException
   */
  public static JSONObject doGetStr(String url) throws ClientProtocolException,
      IOException {

    HttpClient httpClient = HttpClientBuilder.create().build();
    HttpGet httpGet = new HttpGet(url);

    JSONObject jsonObject = null;

    HttpResponse httpResponse = httpClient.execute(httpGet);

    HttpEntity httpEntity = httpResponse.getEntity();
    if (httpEntity != null) {
      String result = EntityUtils.toString(httpEntity);
      jsonObject = JSONObject.fromObject(result);
    }

    return jsonObject;
  }

  /**
   * Post url
   * 
   * @param url
   * @param outStr
   * @return
   * @throws IOException
   */
  public static JSONObject doPostStr(String url, String outStr)
      throws IOException {

    HttpClient httpClient = HttpClientBuilder.create().build();
    HttpPost httpPost = new HttpPost(url);

    JSONObject jsonObject = null;

    try {
      httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
      HttpResponse httpResponse = httpClient.execute(httpPost);
      String result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
      jsonObject = JSONObject.fromObject(result);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return jsonObject;
  }

  /**
   * get access_token
   * 
   * @return
   * @throws ClientProtocolException
   * @throws IOException
   */
  public static AccessToken getAccessToken() throws ClientProtocolException,
      IOException {

    AccessToken token = new AccessToken();
    String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET",
        APPSECRET);

    JSONObject jsonObject = doGetStr(url);
    if (jsonObject != null) {
      token.setAccess_token(jsonObject.getString("access_token"));
      token.setExpires_in(jsonObject.getInt("expires_in"));
    }
    return token;
  }

  /**
   * get userinfo
   * 
   * @param fromUserName
   * @return
   */
  public static UserBascInfo getUserBascInfo(String fromUserName) {
    UserBascInfo userBascInfo = new UserBascInfo();
    try {
      String userInfo_url = USERINFO_URL.replace("ACCESS_TOKEN",
          getAccessToken().getAccess_token()).replace("OPENID", fromUserName);
      JSONObject jsonObject = doGetStr(userInfo_url);
      if (jsonObject != null) {
        userBascInfo.setOpenid(jsonObject.getString("openid"));
        userBascInfo.setSubscribe(jsonObject.getString("subscribe"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return userBascInfo;
  }

  /**
   * init the menu
   * 
   * @return
   */
  public static Menu initMenu(String touser) {
    Menu menu = new Menu();

    ClickButton button1 = new ClickButton();
    button1.setType("click");
    button1.setName("Resume");
    button1.setKey("Button1");

    ViewButton button2 = new ViewButton();
    button2.setType("view");
    button2.setName("Interview");
    // button21.setKey("Button21");
    button2.setUrl("http://wx.sh-ruida.com/rcs/wechat/sap" + "?" + "wechatId="
        + touser + "#/interviewInfo?interviewId=1");

    ClickButton button31 = new ClickButton();
    button31.setType("scancode_push");
    button31.setName("Scan");
    button31.setKey("Button31");

    ClickButton button32 = new ClickButton();
    button32.setType("location_select");
    button32.setName("Located");
    button32.setKey("Button32");

    Button button3 = new Button();
    button3.setName("Action");
    button3.setSub_button(new Button[] { button31, button32 });

    menu.setButton(new Button[] { button1, button2, button3 });

    return menu;
  }

  /**
   * create menu
   * 
   * @param token
   * @param menu
   * @return
   * @throws ParseException
   * @throws IOException
   */
  public static int createMenu(String token, String menu)
      throws ParseException, IOException {
    int result = 0;
    String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
    JSONObject jsonObject = doPostStr(url, menu);
    if (jsonObject != null) {
      result = jsonObject.getInt("errcode");
    }
    return result;
  }

  /*
   * public static String authorizedUserInfo() throws ClientProtocolException, IOException{ String getCode_url =
   * GET_CODE_URL.replace("APPID", APPID).replace("REDIRECT_URI", urlEnodeUTF8(REDIRECT_URI)).replace("SCOPE", SCOPE);
   * String code = null; HttpClient httpClient = HttpClientBuilder.create().build(); HttpGet httpGet = new
   * HttpGet(getCode_url); HttpResponse httpResponse = httpClient.execute(httpGet); httpGet.getURI().toURL(); return
   * getCode_url; }
   */

  /**
   * url encode
   * 
   * @param str
   * @return
   */
  public static String urlEnodeUTF8(String str) {
    String result = str;
    try {
      result = URLEncoder.encode(str, "UTF-8");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}

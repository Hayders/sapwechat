package com.sapwechat.wechattest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import net.sf.json.JSONObject;

import com.sapwechat.entity.util.AccessToken;
import com.sapwechat.util.WechatUtil;

public class Testsapwechat {

  public static void main(String[] args) {

   // String to_user = "oX7wvwUYGxTRGOs1CJBItBcSf5tA";
    //String to_user = "oX7wvwc-MWrfPJw66yBsu82d1Cl8";

    try {
      AccessToken accessToken = WechatUtil.getAccessToken();
      // UserBascInfo userBascInfo = WechatUtil.getUserBascInfo(fromUserName);

      String menu = JSONObject.fromObject(WechatUtil.initMenu())
          .toString();
      
      int result = WechatUtil.createMenu(accessToken.getAccess_token(), menu);
      
      if(result == 0){
        System.out.println("success");
      }
      
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}

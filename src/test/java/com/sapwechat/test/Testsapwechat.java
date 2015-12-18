package com.sapwechat.test;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;

import com.sapwechat.entity.AccessToken;
import com.sapwechat.util.MessageUtil;
import com.sapwechat.util.WechatUtil;

public class Testsapwechat {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		AccessToken accessToken = WechatUtil.getAccessToken();
		
		createMenu(accessToken.getAccess_token());
		
//		String getCode_url = WechatUtil.GET_CODE_URL.replace("APPID", WechatUtil.APPID).replace("REDIRECT_URI", 
//				WechatUtil.urlEnodeUTF8(WechatUtil.REDIRECT_URI)).replace("SCOPE", WechatUtil.SCOPE);
//		System.out.println(getCode_url);
		
//		String sssString = WechatUtil.authorizedUserInfo();
//		System.out.println(sssString);
	
		
		
	}
	
	private static void createMenu(String token){
		String menuString = JSONObject.fromObject(WechatUtil.initMenu()).toString();
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

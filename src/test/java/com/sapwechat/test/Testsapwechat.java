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
		
//		String message = MessageUtil.initNewsMessage("aaa", "bbb");
//		System.out.println(message);
//		
//		String menuString1 = JSONObject.fromObject(WechatUtil.initMenu()).toString();
//		System.out.println(menuString1);
		
	}
	
	private static void createMenu(String token){
		String menuString = JSONObject.fromObject(WechatUtil.initMenu()).toString();
		int result;
		try {
			result = WechatUtil.createMenu(token, menuString);
			if (result == 0) {
				System.out.println("success");
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}
}

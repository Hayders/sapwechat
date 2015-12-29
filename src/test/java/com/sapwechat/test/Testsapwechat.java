package com.sapwechat.test;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Field;

import net.sf.json.JSONObject;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;

import com.sapwechat.entity.message.News;
import com.sapwechat.entity.util.AccessToken;
import com.sapwechat.entity.util.UserBascInfo;
import com.sapwechat.servlet.WechatServlet;
import com.sapwechat.util.MessageUtil;
import com.sapwechat.util.WechatUtil;

public class Testsapwechat {

	public static String touser = "oX7wvwUYGxTRGOs1CJBItBcSf5tA";
	public static String template_id = "tw2tIGFGB4VOaFnmTuQDHeKJm3vNPyK3NWsk5stzhAw";
	public static String interviewDate = "12/31/2095";
	public static String interviewLink = "http://www.baidu.com";

	public static void main(String[] args) throws ClientProtocolException,
			IOException, NoSuchFieldException, SecurityException {

		AccessToken accessToken = WechatUtil.getAccessToken();

		postModelMessage(accessToken.getAccess_token());

		// createMenu(accessToken.getAccess_token());

		// System.out.println(accessToken.getAccess_token());

		// String sssString = WechatUtil.authorizedUserInfo();
		// System.out.println(sssString);

		// UserBascInfo userBascInfo = WechatUtil.getUserBascInfo(fromUserName);
		// System.out.println(WechatUtil.);

	}

	/*
	 * https://api.weixin.qq.com/cgi-bin/message/template/send?access_token= {
	 * "touser":"oX7wvwUYGxTRGOs1CJBItBcSf5tA",
	 * "template_id":"TaWqHBeBiOy_znVUXFbluZUp009Xe73QrCQtSVbEVxw",
	 * "url":"http://weixin.qq.com/download", "data":{ "first":
	 * {"value":"恭喜你简历筛选成功！","color":"#173177"},
	 * "interviewDate":{"value":"12/31/2095","color":"#173177"},
	 * "interviewLink":{"value":"http://www.baidu.com","color":"#173177"},
	 * "remark": {"value":"请准时参加！","color":"#173177" } } }
	 */
	public static void postModelMessage(String token) throws ParseException,
			IOException {
		String modelMessage = JSONObject.fromObject(
				MessageUtil.initModelMessage(touser, template_id,
						interviewDate, interviewLink)).toString();

		int result;
		try {
			result = MessageUtil.postModelMessage(token, modelMessage);
			if (result == 0) {
				System.out.println("success");
				System.out.println(modelMessage);
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}

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

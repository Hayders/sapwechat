package com.sapwechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.dom4j.DocumentException;

import com.sapwechat.entity.AccessToken;
import com.sapwechat.entity.UserBascInfo;
import com.sapwechat.util.CheckAuthority;
import com.sapwechat.util.MessageUtil;
import com.sapwechat.util.WechatUtil;

public class WechatServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");

		PrintWriter out = resp.getWriter();
		if (CheckAuthority.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}

		// add menu
		AccessToken accessToken = WechatUtil.getAccessToken();
		String menuString = JSONObject.fromObject(WechatUtil.initMenu())
				.toString();
		WechatUtil.createMenu(accessToken.getAccess_token(), menuString);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		PrintWriter out = resp.getWriter();
		try {
			Map<String, String> map = MessageUtil.Xml2Map(req);
			String toUserName = map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");

			String message = null;
			if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
				if ("?".equals(content) || "？".equals(content)) {

					UserBascInfo userBascInfo = WechatUtil
							.getUserBascInfo(fromUserName);
					message = MessageUtil.initTextMessage(toUserName,
							fromUserName, userBascInfo);
				}
			} else if (MessageUtil.MESSAGE_EVENT.equals(msgType)) {
				String eventType = map.get("Event");
				if (MessageUtil.MESSAGE_CLICK.equals(eventType)) {
					String eventKey = map.get("EventKey");
					if(("Button1").equals(eventKey)){
						message = MessageUtil.initNewsMessage(toUserName,
								fromUserName);
					}
				}
			}

			out.print(message);
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}

package com.sapwechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sapwechat.entity.News;
import com.sapwechat.entity.NewsMessage;
import com.sapwechat.entity.TextMessage;
import com.sapwechat.entity.UserBascInfo;
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
	public static String initTextMessage(String toUserName,
			String fromUserName, UserBascInfo userBascInfo) {
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
	public static String initNewsMessage(String toUserName, String fromUserName) {
		String message = null;
		List<News> newsList = new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();

		News news1 = new News();
		news1.setTitle("SAP Recume Collection");
		news1.setDescription("Candidates are not willing to maintain their resumes at each "
				+ "company’s recruiting site as  they already have resumes "
				+ "maintained in popular recruitment site ( 51job, zhilian, etc)");
		news1.setPicUrl("http://wx.sh-ruida.coms/sapwechat/static/img/saprecruitment.jpg");
		news1.setUrl(WechatUtil.GET_CODE_URL.replace("APPID", WechatUtil.APPID).replace("REDIRECT_URI", WechatUtil.urlEnodeUTF8(WechatUtil.REDIRECT_URI)).replace("SCOPE", WechatUtil.SCOPE));
		
		News news2 = new News();
		news2.setTitle("SAP Wechat Recruitment");
		news2.setDescription("HR seeking a easier way to publish the recruiting information"
				+ " and get talent’s resume at anywhere and anytime");
		news2.setPicUrl("http://wx.sh-ruida.com/sapwechat/static/img/sapwechat.jpg");
		news2.setUrl("http://wx.sh-ruida.com/rcs");
		
		newsList.add(news1);
		newsList.add(news2);
		
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

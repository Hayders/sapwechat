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
		xStream.alias("item", newsMessage.getClass());
		return xStream.toXML(newsMessage);
	}

	/**
	 * init news message
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initNewsMessage(String toUserName, String fromUserName) {
		String message = null;
		List<News> newsList = new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();

		News news = new News();
		news.setTitle("SAP Recume Collection");
		news.setDescription("Candidates are not willing to maintain their resumes at each "
				+ "company’s recruiting site as  they already have resumes "
				+ "maintained in popular recruitment site ( 51job, zhilian, etc)");
		news.setPicUrl("http://139.196.39.17/sapwechat/target/sapwechat/static/img/hhdx.gif");
		news.setUrl("http://139.196.39.17/rcs/wechat/sap");
		newsList.add(news);
		
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
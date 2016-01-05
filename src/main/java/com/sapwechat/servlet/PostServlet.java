package com.sapwechat.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapwechat.entity.util.AccessToken;
import com.sapwechat.util.PostUtil;
import com.sapwechat.util.WechatUtil;



public class PostServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    
    AccessToken accessToken = WechatUtil.getAccessToken();
    String sssString1 = req.getParameter("1");
    String sssString2 = req.getParameter("2");
    String sssString3 = req.getParameter("3");
    if (sssString1!=null && req.getParameter("1").equals("Pre")) {
      PostUtil.postPreModelMessage(accessToken.getAccess_token());
      
    } else if (sssString2!=null && req.getParameter("2").equals("Mid")){
      PostUtil.postMidModelMessage(accessToken.getAccess_token());
      
    } else if (sssString3!=null && req.getParameter("3").equals("Last")){
      PostUtil.postLastModelMessage(accessToken.getAccess_token());
    }

  }

  

}

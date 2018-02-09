package com.huashu.myStruts.framework.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huashu.myStruts.framework.acctionManager.ActionMapping;
import com.huashu.myStruts.framework.acctionManager.StrutsReader;

public class LoginServlet extends HttpServlet{
	private StrutsReader struts;
	@Override
	public void init() throws ServletException {
		struts = new StrutsReader();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		String actionName = url.substring(url.lastIndexOf("/")+1,url.indexOf(".action"));
		try {
			ActionMapping action = struts.getActionMapping(actionName);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}

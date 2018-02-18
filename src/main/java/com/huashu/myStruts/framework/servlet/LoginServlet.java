package com.huashu.myStruts.framework.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huashu.myStruts.framework.acctionManager.ActionMapping;
import com.huashu.myStruts.framework.acctionManager.MyContext;
import com.huashu.myStruts.framework.acctionManager.StrutsReader;
import com.huashu.myStruts.framework.annotation.MyAgent;

public class LoginServlet extends HttpServlet{
	private StrutsReader struts;
	private MyContext myContext;
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
			String className = action.getClassName();
			String methodName = action.getMethodName();
			Class<?> clazz = Class.forName(className);
			Object obj = clazz.newInstance();
			Method method = obj.getClass().getMethod(methodName, HttpServletRequest.class);
			Object res = method.invoke(obj, req);
			String result=null;
			if(res instanceof String)
				result = (String)res;
			PrintWriter out = resp.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}
	
	
	
	
}

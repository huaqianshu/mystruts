package com.huashu.myStruts.framework.acctionManager;

import java.util.Map;

public class ActionMapping {
	private String name;
	private String className;
	private String methodName;
	private Map<String,Result> resultMap;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Map<String,Result> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String,Result> resultMap) {
		this.resultMap = resultMap;
	}

}

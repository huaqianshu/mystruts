package com.huashu.myStruts.framework.acctionManager;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class StrutsReader {
	private Map<String,ActionMapping> actions = new HashMap<>();
	public void init(){
		try {
			File f = new File("struts.xml");
			SAXReader reader = new SAXReader();
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			Iterator<Element> it = root.elementIterator("action");
			while(it.hasNext()){
				Element e = it.next();
				ActionMapping action = new ActionMapping();
				action.setName(e.attributeValue("name"));
				action.setClassName(e.attributeValue("class"));
				action.setName(e.attributeValue("method"));
				Map<String,Result> resultMap = new HashMap<>();
				
				Iterator<Element> resultIt = e.elementIterator("Result");
				while(resultIt.hasNext()){
					Element resultE = resultIt.next();
					Result result = new Result();
					result.setName(resultE.attributeValue("name"));
					result.setType(resultE.attributeValue("type"));
					result.setDestination(resultE.getText());
					resultMap.put(result.getName(), result);
				}
				action.setResultMap(resultMap);				
				actions.put(action.getName(), action);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public ActionMapping getActionMapping(String name) throws Exception{
		if(actions.isEmpty())
			init();
		if(!actions.containsKey(name))
			throw new Exception("no action named "+name);
		return actions.get(name);
	}
	 
}

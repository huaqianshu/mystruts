package com.huashu.myStruts.framework.acctionManager;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.huashu.myStruts.framework.annotation.Dao;
import com.huashu.myStruts.framework.annotation.Service;

public class MyContext {
	public Map<String,Object> classNameContext=new HashMap<>();
	public Map<String,Object> spaceNameContext=new HashMap<>();
	public void init(){
		try {
			File f = new File("spring.xml");
			List<Element> list = new ArrayList<>();
			SAXReader reader = new SAXReader(); 
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			list = root.elements("service");
			list.addAll(root.elements("dao"));
			Iterator<Element> it =list.iterator();
			while(it.hasNext()){
				Element service = it.next();
				String packageName = service.attributeValue("package");
				List<Class<?>> classes = getAllClasses(packageName);
				for(Class clazz:classes){
					if(clazz.isInterface())
						continue;
					Object obj = clazz.newInstance();
					classNameContext.put(clazz.getName(),obj );
					Annotation annotation = clazz.getAnnotation(Service.class);
					String spaceName="";
					if(annotation!=null){
						Service serviceAnnotation = (Service) annotation;
						spaceName = serviceAnnotation.value();
						if(spaceName.isEmpty()){
							spaceName = clazz.getName();
						}
					}
					annotation = clazz.getAnnotation(Dao.class);
					if(annotation!=null){
						Dao serviceAnnotation = (Dao) annotation;
						spaceName = serviceAnnotation.value();
						if(spaceName.isEmpty()){
							spaceName = clazz.getName();
						}
					}
					spaceNameContext.put(spaceName, obj);
				}
			}
		} catch (DocumentException | ClassNotFoundException | IOException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private List<Class<?>> getAllClasses(String packageName) throws IOException, ClassNotFoundException{
		List<Class<?>> classes = new ArrayList<Class<?>>(); 
        String packageDirName = packageName.replace('.', '/');  
        Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);  
		while(dirs.hasMoreElements()){
			URL url = dirs.nextElement();
			String protocol = url.getProtocol();
			if("file".equals(protocol)){
				String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
				getClassFromPackage(packageName,classes,filePath);
			}
		}
		
		return classes;
	}
	private void getClassFromPackage(String packageName,List<Class<?>> classes,String filePath) throws UnsupportedEncodingException, ClassNotFoundException{
		
		File dir = new File(filePath);
		File[] dirfiles = dir.listFiles(new FileFilter() {  
	        //自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)  
	              public boolean accept(File file) {  
	                return (file.isDirectory()) || (file.getName().endsWith(".class"));  
	              }  
	            });
		for(File file:dirfiles){
			if(file.isDirectory()){
				
				getClassFromPackage(packageName + "." + file.getName(),classes,file.getAbsolutePath());
			}else{
				String className = file.getName().substring(0, file.getName().length() - 6); 
				classes.add(Class.forName(packageName + '.' + className));  
			}
		}
	}
}

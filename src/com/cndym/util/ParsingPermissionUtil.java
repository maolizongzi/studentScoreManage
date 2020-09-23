package com.cndym.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.cndym.Listener.ApplicationListener;
import com.cndym.entity.Function;

/**
 * @Description:当前类为根据权限解析xml
 * @Author:LiNa
 * @Version:v1.00(版本号)
 * @Create Date: 2016年3月7日14:19:38
 */
public class ParsingPermissionUtil {
	private Map<String, Function> functionLinkedMap = new LinkedHashMap<String, Function>();
	public Map<String, List<Function>> functionTabPage = new LinkedHashMap<String, List<Function>>();
	private Set<String> idSet = new HashSet<String>();

	
	public Collection<Function> getIndexMenu(Set<String> permissionSet) {
		List<Function> functions = new ArrayList<Function>();
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			InputStream inputStream = ApplicationListener.class
					.getClassLoader().getResourceAsStream("xml/function.xml");
			Reader reader = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"));
			parser.setInput(reader);
			int state = parser.getEventType();
			String id = "";
			Function function = null;
			getFunctions(null);
			while (state != XmlPullParser.END_DOCUMENT) {
				switch (state) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					if ("Function".equals(parser.getName())) {
						function = new Function();
						String functionName = parser.getAttributeValue(0);
						String functionStyle = parser.getAttributeValue(1);
						String functionParentFlag = parser.getAttributeValue(3);

						String functionScriptName = "";
						if (functionStyle.equals("tabPage")) {
							functionScriptName = parser.getAttributeValue(2);
						}
						function.setFunctionParentId(getFuncId(id));
						id = getFunctionId(id);
						function.setFunctionId(id);
						function.setFunctionName(functionName);
						function.setFunctionStyle(functionStyle);
						function.setFunctionScriptName(functionScriptName);
						function.setFunctionParentFlag(functionParentFlag);
						if(permissionSet!=null){
							if (checkPermission(functionParentFlag, permissionSet)) {
								functions.add(function);
								functionLinkedMap.put(id, function);
							}
						}else{
							functions.add(function);
							functionLinkedMap.put(id, function);
						}
					}
					break;
				case XmlPullParser.END_TAG:
					if (!id.startsWith("-")) {
						id = subFunctionId(id);
					}
					break;
				}
				state = parser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Function> map = getFunctions(functionLinkedMap.values());
		Collection<Function> functionCollection = map.values();
		return functionCollection;
	}
	
	public Map<String, Function> getFunctions(Collection<Function> functions) {
		Map<String, Function> functionMap = new LinkedHashMap<String, Function>();
		if (functions != null) {
			for (Function function : functions) {
				String functionId = function.getFunctionId();
				if (functionId.indexOf(".") > 0) {
					if (functionId.indexOf(".") == functionId.lastIndexOf(".")) {
						Function parentFunction = functionMap.get(functionId
								.substring(0, functionId.indexOf(".")));
						if (function.getFunctionStyle().equals("tabPage")) {
							insertTabPage(parentFunction, function);
						} else {
							parentFunction.addChildFunction(function);
						}
					} else {
						String parentId = functionId.substring(0,
								functionId.indexOf("."));
						Function parentFunction = functionMap.get(parentId);
						insertFunction(parentFunction, function);
					}
				} else {
					functionMap.put(functionId, function);
				}
			}
		}
		return functionMap;
	}
	
	
	public String getFuncId(String id) {
		String newId = "";
		if (id == null || "".equals(id)) {
			newId = "0";
		} else {
			if (id.startsWith("-")) {
				newId = id.substring(1, id.length());
			} else if (id.indexOf(".") > 0) {
				newId = id.substring(0, id.indexOf("."));
			} else {
				newId = id;
			}
		}
		return newId;
	}
	public String getFunctionId(String id) {
		String newId = "";
		if (id == null || "".equals(id)) {
			newId = "1";
		} else {
			if (id.startsWith("-")) {
				newId = id.substring(1, id.length());
			} else {
				newId = id + ".1";
			}
			newId = createFunctionId(id, newId);
			idSet.add(newId);
		}
		return newId;
	}

	public void insertTabPage(Function parentFunction, Function childFunction) {
		List<Function> tabPage = functionTabPage.get(parentFunction
				.getFunctionId());
		if (tabPage == null) {
			tabPage = new ArrayList<Function>();
		}
		tabPage.add(childFunction);
		functionTabPage.put(parentFunction.getFunctionId(), tabPage);
	}
	

	public void insertFunction(Function parentFunction, Function childFunction) {
		String childFunctionId = childFunction.getFunctionId();
		String parentFunctionId = childFunctionId.substring(0,
				childFunctionId.lastIndexOf("."));
		if (parentFunctionId.equals(parentFunction.getFunctionId())) {
			if (childFunction.getFunctionStyle().equals("tabPage")) {
				insertTabPage(parentFunction, childFunction);
			} else {
				parentFunction.addChildFunction(childFunction);
			}
		} else {
			for (Function fun : parentFunction.getChildFunctions()) {
				insertFunction(fun, childFunction);
			}
		}
	}
	
	public String createFunctionId(String id, String newId) {
		if (idSet.contains(newId)) {
			if (newId.indexOf(".") > 0) {
				while (true) {
					if (idSet.contains(newId)) {
						int tempId = Integer.parseInt(newId.substring(newId
								.lastIndexOf(".") + 1));
						tempId = tempId + 1;
						newId = id + "." + tempId;
					} else {
						break;
					}
				}
			} else {
				int tempId = Integer.parseInt(newId);
				newId = (tempId + 1) + "";
			}
		}
		return newId;
	}
	
	public boolean checkPermission(String permission, Set<String> permissionSet) {
		for (String permissions : permissionSet) {
			if (permissions.startsWith(permission)) {
				return true;
			}
		}
		return false;
	}
	
	public String subFunctionId(String id) {
		String newId = "";
		if (id.indexOf(".") > 0) {
			newId = id.substring(0, id.lastIndexOf("."));
		} else {
			int tempId = Integer.parseInt(id);
			newId = "-" + (tempId + 1) + "";
		}
		return newId;
	}
	
	
}

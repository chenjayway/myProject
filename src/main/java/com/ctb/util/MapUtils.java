/**
 * Copyright (c) 2015 iKang Guobin Healthcare Group. All rights reserved.
 */
package com.ctb.util;

import org.apache.commons.lang.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 将字符串转换成Map集合
 */
public class MapUtils {

	/**
	 * 生成加密数据,规则是:按参数名称a-z排序,空值也参与。
	 */
	public static String createSign(SortedMap<String, String> packageParams) {

		StringBuffer sb = new StringBuffer();
		Set<Map.Entry<String, String>> es = packageParams.entrySet();
		Iterator<Map.Entry<String, String>> it = es.iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			String k = entry.getKey();
			String v = entry.getValue();
			sb.append(k + "=" + v + "&");
		}
		String sign = sb.toString();
		sign = sign.substring(0, sign.length() - 1);
		return sign;

	}
	
	/**
	 * 解析字符串xml 转换成MAP
	 * @param srcXml
	 * @return
	 */
	public static Map<String, String> parseXmlString(String srcXml) {
		Map<String, String> map=new HashMap<String, String>();
		StringReader read = new StringReader(srcXml);
		//创建新的输入源SAX，解析器使用InputSource 对象来确定如何读取xml输入
		InputSource source = new InputSource(read);
		//创建一个SAXBUilder
		SAXBuilder sb=new SAXBuilder();
		try{
			//通过输入源构建一个Document
			Document doc = sb.build(source);
			//取得根元素
			Element root = doc.getRootElement();
			traverseXMLElements2(map,root);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 递归将xml元素名称和值放入map
	 * @param map
	 * @param element
	 */
	public static void traverseXMLElements2(Map<String, String> map,Element element){
		for(Element temp : element.getChildren()){
			if(temp.getChildren() != null && temp.getChildren().size() > 0){
				traverseXMLElements2(map,temp);
			}else{
				map.put(temp.getName().trim(), temp.getValue().trim());
			}
		}
	}
	/**
	 * 返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据
	 * @param map
	 * @param element
	 */
	public static void traverseXMLElements(Map<String, String> map,Element element){
		 for(Element temp : element.getChildren()){
			 String k = temp.getName().trim();
			 String v = "";
			if(temp.getChildren() != null && temp.getChildren().size() > 0){
				v = getChildrenText(temp.getChildren()).trim();
			}else{
				v = temp.getValue().trim();
			}
			map.put(k, v);
		}
	}
	
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List<Element> children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator<Element> it = children.iterator();
			while(it.hasNext()) {
				Element e = it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List<Element> list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}

	/**
	 * 解析json字符串为Map集合
	 * @return
     */
	public static Map<String, String> parseJsonString(String json) {
		Map<String, String> map = JsonUtils.parseObject(json, Map.class);
		return map;
	}

	/**
	 * 将形如key=value&key=value的字符串转换为相应的Map对象
	 * 
	 * @param result
	 * @return
	 */
	public static Map<String, String> stringToMap(String result) {
		Map<String, String> map = null;
		try {
			if (StringUtils.isNotBlank(result)) {
				if (result.startsWith("{") && result.endsWith("}")) {
					result = result.substring(1, result.length() - 1);
				}
				map = parseQString(result);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 解析应答字符串，生成应答要素
	 * 
	 * @param str 需要解析的字符串
	 * @return 解析的结果map
	 * @throws UnsupportedEncodingException
	 */
	public static Map<String, String> parseQString(String str)
			throws UnsupportedEncodingException {

		Map<String, String> map = new HashMap<String, String>();
		int len = str.length();
		StringBuilder temp = new StringBuilder();
		char curChar;
		String key = null;
		boolean isKey = true;
		boolean isOpen = false;// 值里有嵌套
		char openName = 0;
		if (len > 0) {
			for (int i = 0; i < len; i++) {// 遍历整个带解析的字符串
				curChar = str.charAt(i);// 取当前字符
				if (isKey) {// 如果当前生成的是key
					if (curChar == '=') {// 如果读取到=分隔符
						key = temp.toString();
						temp.setLength(0);
						isKey = false;
					} else {
						temp.append(curChar);
					}
				} else {// 如果当前生成的是value
					if (isOpen) {
						if (curChar == openName) {
							isOpen = false;
						}

					} else {// 如果没开启嵌套
						if (curChar == '{') {// 如果碰到，就开启嵌套
							isOpen = true;
							openName = '}';
						}
						if (curChar == '[') {
							isOpen = true;
							openName = ']';
						}
					}
					if (curChar == '&' && !isOpen) {// 如果读取到&分割符,同时这个分割符不是值域，这时将map里添加
						putKeyValueToMap(temp, isKey, key, map);
						temp.setLength(0);
						isKey = true;
					} else {
						temp.append(curChar);
					}
				}

			}
			putKeyValueToMap(temp, isKey, key, map);
		}
		return map;
	}

	private static void putKeyValueToMap(StringBuilder temp, boolean isKey,
			String key, Map<String, String> map)
			throws UnsupportedEncodingException {
		if (isKey) {
			key = temp.toString();
			if (key.length() == 0) {
				throw new RuntimeException("QString format illegal");
			}
			map.put(key, "");
		} else {
			if (key.length() == 0) {
				throw new RuntimeException("QString format illegal");
			}
			map.put(key, temp.toString());
		}
	}

	/**
	 * 将一个 JavaBean 对象转化为一个  Map
	 * @param bean 要转化的JavaBean 对象
	 * @return 转化出来的  Map 对象
	 * @throws IntrospectionException 如果分析类属性失败
	 * @throws IllegalAccessException 如果实例化 JavaBean 失败
	 * @throws InvocationTargetException 如果调用属性的 setter 方法失败
	 */
	public static Map convertBean(Object bean)
			throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		Class type = bean.getClass();
		Map returnMap = new HashMap();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
		for (int i = 0; i< propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result.toString());
				} else {
					returnMap.put(propertyName, "");
				}
			}
		}
		return returnMap;
	}
}

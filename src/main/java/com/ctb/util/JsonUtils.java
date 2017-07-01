/*
 * Copyright (c) 2016 iKang Guobin Healthcare Group. All rights reserved.
 */
package com.ctb.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * FastJson工具.
 */
public abstract class JsonUtils {

	/** slf4j logger api */
	protected final static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	private static SerializerFeature[] serializerFeature = null;

	// private static SerializeConfig mapping = new SerializeConfig();

	private static ParserConfig parserConfig = ParserConfig.getGlobalInstance();

	static {
		serializerFeature = new SerializerFeature[] { SerializerFeature.WriteMapNullValue,
				SerializerFeature.QuoteFieldNames, SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.WriteNullListAsEmpty };

		parserConfig.putDeserializer(Date.class, new CustomDateFormatDeserializer());
	}

	/**
	 * 生成Json string.
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSONString(Object object) {
		return JSON.toJSONString(object, serializerFeature);
	}

	/**
	 * 将JavaBean转换为JSONObject或者JSONArray.
	 * 
	 * @param object
	 * @return
	 */
	public static Object toJSON(Object object) {
		return JSON.toJSON(object);
	}

	/**
	 * 解析json字符串为对象.
	 * 
	 * @param json
	 * @return
	 */
	public static Object parse(String json) {
		if (null == json) {
			return null;
		}
		try {
			return JSON.parse(json);
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	/**
	 * 解析json字符串为指定类型对象.
	 * 
	 * @param json
	 * @param clazz 指定的Class
	 * @return
	 */
	public static <T> T parseObject(String json, Class<T> clazz) {
		if (null == json) {
			return null;
		}
		try {
			// return JSON.parseObject(json, clazz);
			return JSON.parseObject(json, clazz, parserConfig, JSON.DEFAULT_PARSER_FEATURE, new Feature[0]);
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	/**
	 * 解析json字符串为指定Class的List集合.
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> parseList(String json, Class<T> clazz) {
		if (null == json) {
			return null;
		}
		try {
			return JSON.parseArray(json, clazz);
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	/**
	 * 解析json字符串为对象集合
	 */
	public static JSONArray parseArray(String json) {
		return JSON.parseArray(json);
	}

	/**
	 * 解析json字符串为JSONObject对象
	 */
	public static JSONObject parseObject(String json) {
		return JSON.parseObject(json);
	}

	/**
	 * Response 输出 JSON HTTP响应
	 */
	public static void outPutJson(String jsonStr, HttpServletResponse response) {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(jsonStr);
			out.close();
		} catch (IOException e) {
			handleException(e);
		}
	}

	/**
	 * 公共处理异常.
	 * 
	 * @param e
	 */
	public static void handleException(Throwable e) {
		logger.error("Fast JSON出错了", e.getCause());
		// throw new RuntimeException(e.getCause());
	}

	/*public static void main(String[] args) {
	        String json = "[{\"contactName\":\"ccc\",\"gender\":\"eer\",\"position\":\"1001\",\"mobile\":\"123124\",\"workPhone\":\"532423\",\"email\":\"asdas@adf.com\",\"homePhone\":\"3231231\",\"qq\":\"323123\",\"msn\":\"asdfadsg\",\"other\":\"hhhh\"},{\"contactName\":\"ccc\",\"gender\":\"eer\",\"position\":\"1001\",\"mobile\":\"123124\",\"workPhone\":\"532423\",\"email\":\"asdas@adf.com\",\"homePhone\":\"3231231\",\"qq\":\"323123\",\"msn\":\"asdfadsg\",\"other\":\"hhhh\"}]";
	        List<Contact> as = parseList(json, Contact.class);
	        System.out.println(as.get(0).getContactName());
	}*/
}

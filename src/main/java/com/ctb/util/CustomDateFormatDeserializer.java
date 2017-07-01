/*
 * Copyright (c) 2016 iKang Guobin Healthcare Group. All rights reserved.
 */
package com.ctb.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.DateFormatDeserializer;

import java.lang.reflect.Type;

/**
 * 自定义fastjson对日期反序列化处理.
 */
public class CustomDateFormatDeserializer extends DateFormatDeserializer {

	@SuppressWarnings("unchecked")
	@Override
	protected <T> T cast(DefaultJSONParser parser, Type clazz, Object fieldName, Object val) {
		if (val == null) {
			return null;
		}
		if (val instanceof String) {
			String strVal = (String) val;
			if (strVal.length() == 0) {
				return null;
			}
			return (T) DateUtils.parseDate(strVal);
		}
		throw new JSONException("parse error");
	}

}

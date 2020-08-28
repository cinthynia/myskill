package com.my.logdemo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class JsonUtil {
	// 把JSON文本parse为JavaBean 
	public static final <T> T parseObject(String json, Class<T> clazz) {
		return (T) JSON.parseObject(json,clazz);
	}
	// 将JavaBean序列化为JSON文本 
	public static final String toJSONString(Object obj){
		return JSON.toJSONString(obj);
	}
	//把JSON文本parse成JavaBean集合
	public static final <T> List<T> parseArray(String text, Class<T> clazz){
		return JSON.parseArray(text, clazz);
	}
	// 把JSON文本parse为JSONObject或者JSONArray
	public static final Object parse(String text){
		return JSON.parse(text);
	}
	// 把JSON文本parse成JSONObject
	public static final JSONObject parseObject(String text){
		return JSON.parseObject(text);
	}
	// 把JSON文本parse成JSONArray
	public static final JSONArray parseArray(String text){
		return JSON.parseArray(text);
	}
	// 将JavaBean序列化为带格式的JSON文本 
	public static final String toJSONString(Object object, boolean prettyFormat){
		return JSON.toJSONString(object,prettyFormat);
	}
	// 将JavaBean转换为JSONObject或者JSONArray
	public static final Object toJSON(Object javaObject){
		return JSON.toJSON(javaObject);
	}
	public static Map<String, Object> parseJSON2Map(String jsonStr){
		Map<String, Object> map = new HashMap<String, Object>();
		//最外层解析
		com.alibaba.fastjson.JSONObject json = com.alibaba.fastjson.JSONObject.parseObject(jsonStr);
		for(Object k : json.keySet()){
			Object v = json.get(k);
			//如果内层还是数组的话，继续解析
			if(v instanceof com.alibaba.fastjson.JSONArray){
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				Iterator<Object> it = ((com.alibaba.fastjson.JSONArray)v).iterator();
				while(it.hasNext()){
					com.alibaba.fastjson.JSONObject json2 = (com.alibaba.fastjson.JSONObject)it.next();
					list.add(parseJSON2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}
}
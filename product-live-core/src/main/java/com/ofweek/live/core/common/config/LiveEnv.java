package com.ofweek.live.core.common.config;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.ofweek.live.core.common.utils.PropertiesLoader;

/**
 * 展会网配置类
 * 
 * @author tangqian
 * @version 2016-02-24
 */
public class LiveEnv {

	/**
	 * 保存全局属性值
	 */
	private static final Map<String, String> map = Maps.newHashMap();

	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("config:env.properties");

	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null) {
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}
	
	public static String getUploadRoot() {
		return getConfig("upload.root");
	}

	public static class Ofweek {
		public static String getLoginUrl() {
			return getConfig("url.ofweek.login");
		}

		public static String getQueryUrl() {
			return getConfig("url.ofweek.queryUserInfo");
		}

		public static String getRegisterUrl() {
			return getConfig("url.ofweek.register");
		}
	}
}

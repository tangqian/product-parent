package com.ofweek.live.core.modules.sys.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户类型枚举
 */
public enum UserTypeEnum {

	AUDIENCE(1, "观众"), ADMIN(2, "管理员"), SPEAKER(3, "主播"), WAITER(4, "客服"), VISITOR(5, "游客");

	private static final Map<Integer, UserTypeEnum> codes = new HashMap<Integer, UserTypeEnum>();

	static {
		for (UserTypeEnum typeEnum : UserTypeEnum.values()) {
			codes.put(typeEnum.getCode(), typeEnum);
		}
	}

	UserTypeEnum(int code, String meaning) {
		this.code = code;
		this.meaning = meaning;
	}

	public int getCode() {
		return code;
	}

	public String getMeaning() {
		return meaning;
	}

	/**
	 * 根据type获取类型枚举对象
	 * 
	 * @param type
	 * @return
	 */
	public static UserTypeEnum getEnum(Integer type) {
		return codes.get(type);
	}

	/**
	 * 是否是管理员
	 * 
	 * @param type
	 * @return
	 */
	public static boolean isAdmin(Integer type) {
		return type != null && type == ADMIN.code;
	}
	
	/**
	 * 是否是观众
	 * 
	 * @param type
	 * @return
	 */
	public static boolean isAudience(Integer type) {
		return type != null && type == AUDIENCE.code;
	}

	private final int code;
	private final String meaning;

}

package com.ofweek.live.core.modules.sys.utils;

public class SysFileUtils {

	public enum TempEnum {
		NO(0, "否"), YES(1, "是");

		private Integer code;

		private String meaning;

		TempEnum(Integer code, String meaning) {
			this.code = code;
			this.meaning = meaning;
		}

		/**
		 * @return 返回 code
		 */
		public Integer getCode() {
			return code;
		}

		/**
		 * @return 返回 meaning
		 */
		public String getMeaning() {
			return meaning;
		}
	}

	public enum TypeEnum {
		AUDIENCE_LOGO(10000, "audience/logo", "观众头像"),
		
		ROOM_COVER(20000, "room/cover", "直播房间封面图"),
		
		SPEAKER_LOGO(30000, "speaker/logo", "主播头像"),
		SPEAKER_DATA(31000, "speaker/data", "主播资料"),
		SPEAKER_SPEECH(32000, "speaker/speech", "主播演讲稿"),
		SPEAKER_VIDEO(33000, "speaker/video", "主播视频"),
		SPEAKER_VIDEO_COVER(33100, "speaker/videoCover", "主播视频封面图"),
		
		WAITER_LOGO(40000, "waiter/logo", "客服头像");

		/**
		 * code值由两部分组成， 1位逻辑分类+4位业务细项分类组成。<br/>
		 * 比如直播房间相关的文件上传都为1XXXX，主播相关的文件上传都为2XXXX，以此类推。<br/>
		 * 各业务子项按照从高位到低位编码方式，如20XXX，21XXX。
		 * 
		 */
		private Integer code;

		private String classifyDir;

		private String meaning;

		/**
		 * <默认构造函数>
		 */
		TypeEnum(Integer code, String classifyDir, String meaning) {
			this.code = code;
			this.classifyDir = classifyDir;
			this.meaning = meaning;
		}

		/**
		 * @return 返回 code
		 */
		public Integer getCode() {
			return code;
		}

		/**
		 * @return 返回 meaning
		 */
		public String getMeaning() {
			return meaning;
		}

		public String getClassifyDir() {
			return classifyDir;
		}

		/**
		 * 可理解的名称
		 * 
		 * @param code
		 * @return
		 */
		public static String readable(Integer code) {
			TypeEnum ret = null;
			for (TypeEnum e : TypeEnum.values()) {
				if (e.code.equals(code)) {
					ret = e;
					break;
				}
			}
			return ret == null ? "" : ret.meaning;
		}
	}
}

package com.jinfang.graduationproject.constants.teacher;

/**
* @Description: (任务)
* @author lz
* @date 2020年8月20日
* @version V1.0
*/
public class MissionConstants {
	/**
	 * 状态
	 * @author lz
	 *
	 */
	public enum SendStatus {
		TEMP(0, "未下发 "), SENT(1, "已下发 "),RECEIVED(2, "已接收");
		SendStatus(int code, String title) {
			this.code = code;
			this.title = title;
		}

		private int code;
		private String title;

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
	}
}

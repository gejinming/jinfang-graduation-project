
    /**
    * @Description: TODO(用一句话描述该文件做什么)
    * @author lz
    * @date 2020年9月6日
    * @version V1.0  
    */
    
package com.jinfang.graduationproject.constants.teacher;


    /**
    * @Description: TODO(参数设置)
    * @author lz
    * @date 2020年9月6日
    *
    */

public class SettingConstants {
	/**
	 * 状态
	 * @author lz
	 *
	 */
	public enum Status {
		NO(0, "未设置 "), YES(1, "已设置 ");
		Status(int code, String title) {
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

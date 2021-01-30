
/**
* @Description: TODO(用一句话描述该文件做什么)
* @author lz
* @date 2020年9月1日
* @version V1.0  
*/

package com.jinfang.graduationproject.constants.teacher;

/**
 * @Description: TODO(外文翻译)
 * @author lz
 * @date 2020年9月1日
 *
 */

public class TranslationConstants {
	/**
	 * 状态
	 * 
	 * @author lz
	 *
	 */
	public enum Status {
		TEMP(0, "未提交 "), WAITING(1, "未检查  "), PASS(2, "已通过"), REJECT(3, "未通过");

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

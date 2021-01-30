/**
 * 
 */
package com.jinfang.graduationproject.constants.teacher;

/**
* @Description: (课题学生)
* @author lz
* @date 2020年8月19日
* @version V1.0
*/
public class SubjectStudentConstants {

	/**
	 * 状态
	 * @author lz
	 *
	 */
	public enum ApproveStatus {
		WAITING(0, "待处理 "), ACCEPT(1, "已接收 "),REFUSE(2, "已拒绝");

		ApproveStatus(int code, String title) {
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

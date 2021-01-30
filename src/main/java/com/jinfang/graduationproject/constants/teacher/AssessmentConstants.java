
    /**
    * @Description: TODO(用一句话描述该文件做什么)
    * @author lz
    * @date 2020年8月23日
    * @version V1.0  
    */
    
package com.jinfang.graduationproject.constants.teacher;


    /**
    * @Description: TODO(评语)
    * @author lz
    * @date 2020年8月23日
    *
    */

public class AssessmentConstants {
	/**
	 * 状态
	 * @author lz
	 *
	 */
	public enum Status {
		NO_FILL_IN(0, "未填写 "), YES_FILL_IN(1, "已填写");
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

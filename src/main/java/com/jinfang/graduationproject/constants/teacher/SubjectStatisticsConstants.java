/**
 *
 */
package com.jinfang.graduationproject.constants.teacher;

/**
 * @Description: (课题统计)
 * @author lz
 * @date 2020年8月19日
 * @version V1.0
 */
public class SubjectStatisticsConstants {

    /**
     * 状态
     * @author lz
     *
     */
    public enum FinishStatus {
        NO(0, "未完成 "), YES(1, "已完成");

        FinishStatus(int code, String title) {
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

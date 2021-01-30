/**
 *
 */
package com.jinfang.graduationproject.constants.teacher;

/**
 * @Description: (课题)
 * @author lz
 * @date 2020年8月18日
 * @version V1.0
 */
public class SubjectConstants {

    /**
     * 课题状态
     * @author lz
     *
     */
    public enum ApproveStatus {
        TEMP(0, "未提交 "), WAITING(1, "待审核 "), PASS(2, "通过"), REJECT(3,
                "未通过");

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

    /**
     * 复议状态
     * @author lz
     *
     */
    public enum ReModifyStatus {
        INIT(0, "初始化 "), WAITING(1, "申请中 "), ALLOW(2, "允许修改"), REJECT(3,
                "不允许修改");

        ReModifyStatus(int code, String title) {
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

    public enum SubjectType {
        DESIGNER(1, "毕业设计"), PRJECT(1, "毕业论文 ");

        SubjectType(int code, String title) {
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

package com.jinfang.graduationproject.constants.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lz
 * @Description: TODO(时间控制枚举)
 * @date 2020年9月6日
 */
public class TimeControlConstants {
    /**
     * 状态
     *
     * @author lz
     */
    public enum Status {
        CLOSE(0, "关闭 "), OPEN(1, "开启 ");

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

    @Getter
    @AllArgsConstructor
    public enum NodeTemplate {
        SUBJECT(1, "课题申报", "subject", "指导教师申报毕业论文（设计）课题，专业负责人审核教师提交的课题"),
        STUDENT_SUBJECT(2, "学生选题", "student-subject", "学生选题，指导教师接收或拒绝学生"),
        MISSION(3, "下发任务书", "mission", "学生选题，指导教师接收或拒绝学生"),
        DOC_SUBMIT(4, "文档提交", "doc-submit", "学生提交文献综述、外文翻译和开题报告，指导教师分别进行审核和打分"),
        OPENING_DEFENSE(5, "开题答辩", "opening-defense", "学生进行开题答辩"),
        INSPECTION(6, "中期检查", "inspection", "指导教师填写中期检查表"),
        DISSERTATION(7, "毕业论文", "dissertation", "学生完成毕业论文，指导教师审核和打分"),
        GRADUATION_DEFENSE(8, "毕业答辩", "graduation-defense", "学生进行毕业答辩");

        private int sort;
        private String title;
        private String code;
        private String content;

    }

}

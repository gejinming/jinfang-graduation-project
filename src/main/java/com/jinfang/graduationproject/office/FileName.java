package com.jinfang.graduationproject.office;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class FileName {

    public static final String FILE_NAME_CONNECT_CHAR = "-";

    @AllArgsConstructor
    @Getter
    public enum Mapping {
        UNKNOWN("0", "", "NA", ".docx"),
        PROCESS_DOC_BANNER("1", "process_doc_banner", "过程管理材料封面", ".docx"),
        GRADUATION_DESIGN_BANNER("2", "graduation_design_banner", "毕业设计封面", ".docx"),
        GRADUATION_PROJECT_BANNER("2", "graduation_project_banner", "毕业论文封面", ".docx"),
        MISSION("3", "mission", "任务书", ".docx"),
        STUDENT_SUBJECT("4", "student_subject", "选题申报表", ".docx"),
        REVIEW("5", "review", "文献综述", ".docx"),
        OPENING_REPORT("6", "opening_report", "开题报告", ".docx"),
        OPENING_REPORT_DEFENSE_RECORD("6", "opening_report_defense_record", "开题报告答辩记录表", ".docx"),
        WORD_RECORD("7", "work_record", "工作记录卡", ".docx"),
        WORK_INSTRUCTION_PROCESS("8", "work_instruction_process", "工作指导卡", ".docx"),
        GUIDE_TEACHER_ASSESSMENT("9", "guide_teacher_assessment", "指导教师评语表", ".docx"),
        CHECK_TEACHER_ASSESSMENT("10", "check_teacher_assessment", "评阅教师评语表", ".docx"),
        GRADUATION_DEFENSE_RECORD("11", "graduation_defense_record", "答辩记录表", ".docx"),
        DEFENSE_ASSESSMENT("12", "defense_assessment", "答辩评语表和评分标准", ".docx"),
        SUBJECT_SUMMARY("13", "subject_summary", "课题汇总表", ".xlsx"),
        MID_TERM_INSPECTION_BY_GUIDE("14", "mid_term_inspection_by_guide", "中期教学检查（指导教师填写）", ".docx"),
        MID_TERM_INSPECTION_BY_INSTITUTE("15", "mid_term_inspection_by_ institute", "中期教学检查表(二级学院填写)", ".docx"),
        MID_TERM_INSPECTION_BY_SCHOOL("16", "mid_term_inspection_by_school", "中期教学检查表(学校检查级填写)", ".docx"),
        DISSERTATION_SIMILARITY_FILE("17", "dissertation_similarity_file", "论文相似度检测报告", ".pdf"),
        TRANSLATION("18", "translation", "外文资料翻译", ".docx"),
        COPYRIGHT_AUTHORIZATION("19", "copyright_authorization", "版权授权书", ".docx");

        private String sort;
        private String enName;
        private String cnName;
        private String fileType;

        public static Mapping of(String name) {
            for(Mapping mapping : Mapping.values()) {
                if(mapping.name().equalsIgnoreCase(name)) {
                    return mapping;
                }
            }

            return UNKNOWN;
        }

        public String getSourceFileName() {
            return this.getSort() + FILE_NAME_CONNECT_CHAR + this.getEnName() + this.getFileType();
        }

        public String tranlateCnName() {
            return this.getSort() + FILE_NAME_CONNECT_CHAR + this.getCnName() + this.getFileType();
        }

        public boolean isExcel() {
            return this == SUBJECT_SUMMARY;
        }

    }
}

package com.jinfang.graduactionproject.test.office;

import com.deepoove.poi.config.Configure;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.jinfang.graduationproject.office.vo.StudentSubjectRowVo;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public class StudentSubjectTest extends BasicOfficeTest {

    @Before
    public void init() {
        params.put("teacherName", "张守义");
        params.put("instituteName", "浙江科技学院");
        params.put("studentAmount", 3);

        List<StudentSubjectRowVo> studentSubjects = new ArrayList<>();

        studentSubjects.add(StudentSubjectRowVo.builder().subjectName("大数据应用").studentName("张三")
                .d("√").nature("学术论文").source("生产实际").build());

        studentSubjects.add(StudentSubjectRowVo.builder().subjectName("阿拉伯斯汀").studentName("李四")
                .p("√").nature("学术论文").source("生产实际").build());

        studentSubjects.add(StudentSubjectRowVo.builder().subjectName("测试案例22").studentName("王文武")
                .d("√").nature("学术论文").source("生产实际").build());


        params.put("studentSubjects", studentSubjects);

        sourceFileName = "4-student_subject.docx";
        targetFileName = "4-student_subject-" + System.currentTimeMillis() + ".docx";


        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();

        configure = Configure.newBuilder().bind("studentSubjects", policy).build();


    }

}

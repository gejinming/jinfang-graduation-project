package com.jinfang.graduactionproject.test.office;

import com.deepoove.poi.config.Configure;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import org.junit.Before;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkInstructionProcessTest extends BasicOfficeTest {

    @Before
    public void init() {
        params.put("studentName", "赵国安");
        params.put("teacherName", "张守义");
        params.put("instituteName", "浙江科技学院");
        params.put("majorName", "电气自动化");
        params.put("className", "电气化114班");


        List<Map<String, Object>> processes = new ArrayList<>();

        Map<String, Object> process = new HashMap<>();
        process.put("processTime", "2020年5月1日");
        process.put("content", "测试内如果您22332");
        process.put("result", "70%");
        process.put("teacherName", "张国庆");

        processes.add(process);

        process = new HashMap<>();
        process.put("processTime", "2020年10月1日");
        process.put("content", "艾莉婕dd33");
        process.put("result", "进度1");
        process.put("teacherName", "张国庆");

        processes.add(process);
        params.put("processes", processes);

        List<Map<String, Object>> answers = new ArrayList<>();
        Map<String, Object> answer = new HashMap<>();
        answer.put("recordTime", "2020年4月22日");
        answer.put("content", "问题安安地");
        answer.put("teacherName", "张国庆");

        answers.add(answer);

        answer = new HashMap<>();
        answer.put("recordTime", "2020年5月24日");
        answer.put("content", "回答33");
        answer.put("teacherName", "张国庆");

        answers.add(answer);
        params.put("answers", answers);


        List<Map<String, Object>> leaves = new ArrayList<>();
        Map<String, Object> leave = new HashMap<>();
        leave.put("recordTime", "2020年6月22日");
        leave.put("content", "家中有事");
        leave.put("teacherName", "张国庆");

        leaves.add(leave);

        leave = new HashMap<>();
        leave.put("recordTime", "2020年8月5日");
        leave.put("content", "生病");
        leave.put("teacherName", "张国庆");

        leaves.add(leave);

        params.put("leaves", leaves);

        sourceFileName = "8-work_instruction_process.docx";
        targetFileName = "8-work_instruction_process-" + System.currentTimeMillis() + ".docx";


        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();

        configure = Configure.newBuilder().bind("processes", policy)
                .bind("answers", policy).bind("leaves", policy).build();


    }

}

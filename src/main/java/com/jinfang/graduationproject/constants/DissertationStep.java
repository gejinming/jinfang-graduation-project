package com.jinfang.graduationproject.constants;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public enum DissertationStep {

    SUBJECT(1, "课题"), OPENING_REPORT(2, "开题报告"), COLLECT_DATA(3, "收集资料"),
    FIRST_DRAFT(4, "初稿"), SECOND_DRAFT(5, "二稿"),
    FINAL_DRAFT(6, "定稿"), DEFENSE(7, "答辩");

    private int code;
    private String title;


    public static List<JSONObject> allSteps() {
        List<JSONObject> list = new ArrayList<>();
        for (DissertationStep ds : DissertationStep.values()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", ds.getCode());
            jsonObject.put("title", ds.getTitle());
            list.add(jsonObject);
        }

        return list;
    }
}

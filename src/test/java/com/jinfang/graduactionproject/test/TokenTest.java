	package com.jinfang.graduactionproject.test;

import com.jinfang.graduationproject.util.DecodeUtil;
import com.jinfang.graduationproject.util.JwtTokenUtils;
import org.junit.Before;
import org.junit.Test;

public class TokenTest {

    private String role;
    private String id;
    private String token;

    @Before
    public void init() {
//        id = "746108";
//        token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI3NDYxMDgiLCJyb2xlIjoidGVhY2hlciIsImlzcyI6IkppbmZhbmciLCJleHAiOjE1OTk1NTEzMzQsImlhdCI6MTU5OTUyOTczNH0.auHbky2VptEkislndA0INNPUHnOR2jvFiPzXDxPAVj2kzJcirTTf_zqURs6sQ-Twp2l9Wj8OFcUVVS7lrwUxwA";
//        role = "teacher";

        id="704062"; // 专业负责人
//        id="704067"; // 指导教师
//        id="704068"; // 指导教师
//        id= "921019"; // 答辩组组长
        //id="921070"; // 答辩组成员
//        id = "704067"; // 评阅教师
        //id = "919731"; // 学生
//        id = "919550"; // 学生
        //id="919551";
        token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI3MDQwNTkiLCJyb2xlIjoidGVhY2hlciIsImlzcyI6IkppbmZhbmciLCJleHAiOjE2MDAzNjg4MTksImlhdCI6MTYwMDM0NzIxOX0.KRCVSWM5LqmGJHvpofnQocUFCXJE43EvVR1sMds9BIDrVjY1OyTrmR6OPiN6bSPttB4HLOrHqLiduVLO2UXf8w";
        role = "teacher";
//        role = "student";
        
    }

    @Test
    public void build() {
        token = JwtTokenUtils.createToken(id, role);
        System.out.println(token);
    }

    @Test
    public void out() {
        System.out.println(JwtTokenUtils.getClaimsFromToken(token));
    }

    @Test
    public void encode() {
        System.out.println(DecodeUtil.encode("http://www.baidu.com/?key=测试232"));

    }

}

package com.jinfang.graduactionproject.test;

import com.jinfang.graduationproject.util.CalculateUtil;
import org.junit.Test;

import java.math.BigDecimal;

public class PercentTest {

    @Test
    public void test() {
        System.out.println(CalculateUtil.percert(new BigDecimal(1), new BigDecimal(3)));
    }

}

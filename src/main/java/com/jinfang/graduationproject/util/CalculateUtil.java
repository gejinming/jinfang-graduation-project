package com.jinfang.graduationproject.util;

import java.math.BigDecimal;

public class CalculateUtil {

    /**
     * @param a 单数  32
     * @param b 总数  145
     *          a / b    计算百分比32/145
     * @return 22.07%
     */
    public static String percert(BigDecimal a, BigDecimal b) {
        return
                b == null ? "-" :
                        b.compareTo(new BigDecimal(0)) == 0 ? "-" :
                                a == null ? "0.00%" :
                                        a.multiply(new BigDecimal(100)).divide(b, 2, BigDecimal.ROUND_HALF_UP) + "%";

    }

}

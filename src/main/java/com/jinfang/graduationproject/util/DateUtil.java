package com.jinfang.graduationproject.util;

import java.util.Calendar;

public class DateUtil {

    public static int getCurrentYear() {
        Calendar date = Calendar.getInstance();

        return date.get(Calendar.YEAR);


    }
}

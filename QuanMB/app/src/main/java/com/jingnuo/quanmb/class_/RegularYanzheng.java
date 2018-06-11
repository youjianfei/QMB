package com.jingnuo.quanmb.class_;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularYanzheng {
    public static boolean ispassword(String mobiles) {
        Pattern p = Pattern
                .compile("^(?![0-9]*$)[a-zA-Z0-9]{6,18}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}

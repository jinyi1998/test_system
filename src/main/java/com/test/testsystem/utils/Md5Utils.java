package com.test.testsystem.utils;

import java.security.MessageDigest;

public class Md5Utils {
    private static final String slat = "testSystem";
    //MD5加密
    public static String getMd5ByStr(String str){
        //盐，用于混交md5
        try {
            str = str + slat;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(str.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}

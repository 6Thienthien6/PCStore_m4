package com.ntp.utils;

import java.util.regex.Pattern;

public class ValidDateUtils {

    public static final String NUMBER_REGEX = "^\\d+";
    public static final String PHONE_REGEX = "(^$|[0][0-9]{9,10}$)";
    public static boolean isNumberValid(String password) {
        return Pattern.compile(NUMBER_REGEX).matcher(password).matches();
    }
    public static boolean isPhoneValid(String phone) {
        return Pattern.compile(PHONE_REGEX).matcher(phone).matches();
    }
}

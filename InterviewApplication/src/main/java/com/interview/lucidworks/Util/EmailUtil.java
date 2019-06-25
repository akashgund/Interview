package com.interview.lucidworks.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailUtil {

    public static boolean checkEmail(String email) {

        String pattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(pattern);
        Matcher matchEmail = p.matcher(email);
        return matchEmail.matches();

    }
}
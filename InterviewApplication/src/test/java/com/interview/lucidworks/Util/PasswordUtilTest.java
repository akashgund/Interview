package com.interview.lucidworks.Util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordUtilTest {

    @Test
    public void validPass() {

        String password1 = "@Bcdefghij123";
        String password2 = "@bcdefghij123";
      
        String password5 = "@abcdefghijkl123";

        assertEquals(true,com.interview.lucidworks.Util.PasswordUtil.validPass(password1));
        assertEquals(true,com.interview.lucidworks.Util.PasswordUtil.validPass(password2));
    
        assertEquals(true,com.interview.lucidworks.Util.PasswordUtil.validPass(password5));
    }
}
package com.interview.lucidworks.Util;

import org.junit.Test;

import static org.junit.Assert.*;
import com.interview.lucidworks.Util.*;

public class EmailUtilTest {

    @Test
    public void checkEmail() {

        String email1= "abc@gmai.com";
        String email2="pqr@123.com";
        String email3="xyx.com";
        assertTrue(com.interview.lucidworks.Util.EmailUtil.checkEmail(email1));
        assertTrue(com.interview.lucidworks.Util.EmailUtil.checkEmail(email2));
        assertFalse(com.interview.lucidworks.Util.EmailUtil.checkEmail(email3));
    }
}
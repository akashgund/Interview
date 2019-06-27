package com.interview.lucidworks.Util;

import org.junit.Test;

import static org.junit.Assert.*;

public class UUIDGeneratorTest {

    @Test
    public void getUUID() {
        String a= com.interview.lucidworks.Util.UUIDGenerator.getUUID();
        String b=com.interview.lucidworks.Util.UUIDGenerator.getUUID();

        assertFalse(a.equalsIgnoreCase(b));
    }
}
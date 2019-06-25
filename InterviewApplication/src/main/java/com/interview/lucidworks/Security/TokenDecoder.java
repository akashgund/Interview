package com.interview.lucidworks.Security;

import org.apache.tomcat.util.codec.binary.Base64;

public class TokenDecoder {
    public static String[] decodeToken(String encodedToken) {
        final byte[] decodedByteValues = Base64.decodeBase64(encodedToken.getBytes());
        final String credentials = new String(decodedByteValues);
        final String[] credentialsArray = credentials.split(":", 2);
        return credentialsArray;
    }
}

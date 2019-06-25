package com.interview.lucidworks.Util;


import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordUtil {

    private static String salt="salt";

    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;


    public static String hash(String input) {
        try {

            // Static getInstance method is called with hashing SHA
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // digest() method called
            // to calculate message digest of an input
            // and return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown"
                    + " for incorrect algorithm: " + e);

            return null;
        }
    }
    public static String generateSecurePassword(String password) {
        String returnValue = null;
        byte[] b = salt.getBytes();
        String securePassword = hash(password);

        returnValue = securePassword;

        return returnValue;
    }



    public static boolean validPass(String password) {
        boolean containsUpperCase = false;
        boolean containsLowerCase = false;
        boolean containsChar = false;
        boolean containsDigit = false;

        /* consist of */

        if (password.length() > 10) {
            for (int i = 0; i < password.length(); i++) {
                char ch = password.charAt(i);
                if (Character.isDigit(ch)) ;
                {
                    containsDigit = true;
                }
                if (Character.isAlphabetic(ch)) {
                    if (Character.isUpperCase(ch)) {
                        containsUpperCase = true;
                    }
                    if (Character.isLowerCase(ch)) {
                        containsLowerCase = true;
                    }
                }

            }
            Pattern p = Pattern.compile("[^A-Za-z0-9]");
            Matcher m = p.matcher(password);
            boolean b = m.find();
            if (b == true) {
                containsChar = true;
            }
            return containsChar == true && containsDigit == true && containsLowerCase == true || containsUpperCase == true;

        }
        return false;
    }

    public static String encodePass(String password) {


        String encodedPassword = generateSecurePassword(password);

        return encodedPassword;
    }



    public static boolean checkPass(String passwordPlainText, String encodedPassword) {

        boolean check = false;


        check=verifyUserPassword(passwordPlainText,encodedPassword);
        return check;


    }


    public static boolean verifyUserPassword(String plainTextPassword,String securedPassword)
    {
        boolean returnValue = false;

        // Generate New secure password with the same salt
        String newSecurePassword = encodePass(plainTextPassword);

        // Check if two passwords are equal
        System.out.println(plainTextPassword+"             "+ newSecurePassword+"               "+ securedPassword);
        returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);
        System.out.println(returnValue);

        return returnValue;
    }


}
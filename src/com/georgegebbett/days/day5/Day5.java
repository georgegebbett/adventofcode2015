package com.georgegebbett.days.day5;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day5 {

    public static void main() throws NoSuchAlgorithmException {
        Part1Cracker part1 = new Part1Cracker();
        Part2Cracker part2 = new Part2Cracker();

        part1.doCrack("uqwqemis");
        part2.doCrack("uqwqemis");

    }




}

class Part1Cracker {

    public void doCrack(String doorId) throws NoSuchAlgorithmException {
        int currentInt = 0;
        String password = "";

        while (password.length() < 8) {

            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(doorId.concat(Integer.toString(currentInt)).getBytes());

            byte[] digest = m.digest();

            BigInteger bigInt = new BigInteger(1, digest);

            String hashText = bigInt.toString(16);

            while(hashText.length() < 32 ){
                hashText = "0" + hashText;
            }

//            System.out.println(hashText);

            if (hashText.startsWith("00000")) {
                password += hashText.substring(5, 6);
                System.out.println(hashText.substring(5, 6));
            }

            currentInt++;
        }

        System.out.println(password);
    }
}

class Part2Cracker {

    public void doCrack(String doorId) throws NoSuchAlgorithmException {
        int currentInt = 0;
        char[] password = new char[]{'*', '*', '*', '*', '*', '*', '*', '*'};
        int foundChars = 0;

        System.out.println(password);

        while (foundChars < 8) {

            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(doorId.concat(Integer.toString(currentInt)).getBytes());

            byte[] digest = m.digest();

            BigInteger bigInt = new BigInteger(1, digest);

            String hashText = bigInt.toString(16);

            while(hashText.length() < 32 ){
                hashText = "0" + hashText;
            }

//            System.out.println(hashText);

            if (hashText.startsWith("00000")) {
                if (Character.isDigit(hashText.substring(5,6).charAt(0)) && Integer.parseInt(hashText.substring(5, 6)) < 8) {
                    if (password[Integer.parseInt(hashText.substring(5, 6))] == '*') {
                        password[Integer.parseInt(hashText.substring(5, 6))] = hashText.substring(6, 7).charAt(0);
                        System.out.println(password);
                        foundChars++;
                    }
                }
            }

            currentInt++;
        }


    }
}


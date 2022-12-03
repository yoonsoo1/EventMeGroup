package com.example.eventmegroup;

import org.junit.Test;
import static org.junit.Assert.*;

public class SignInUnitTest {

    @Test
    public void testGoodValidation() {
        SignInVal tester = new SignInVal("", "");

        tester.setEmail("y1@gmail.com");
        tester.setPass("faksiue123");
        assertTrue(1 == tester.check());

        tester.setEmail("y3@gmail.com");
        tester.setPass("12iuy1953");
        assertTrue(1 == tester.check());

        tester.setEmail("k23@yahoo.com");
        tester.setPass("1239gkjasdf");
        assertTrue(1 == tester.check());

        tester.setEmail("jkasdf2@naver.com");
        tester.setPass("kjlasdf123");
        assertTrue(1 == tester.check());

        tester.setEmail("alkskdfj@usc.edu");
        tester.setPass("aslkjdhflkjh12");
        assertTrue(1 == tester.check());
    }

    @Test
    public void testBadEmail() {
        SignInVal tester = new SignInVal("", "");

        tester.setPass("asdfkljhasf123");
        assertTrue(2 == tester.check());

        tester.setPass("alkjshdf1231@");
        assertTrue(2 == tester.check());

        tester.setEmail("lkhj.ajsuek#gmail.com");
        tester.setPass("lkajhsdflkj123");
        assertTrue(5 == tester.check());

        tester.setEmail("lkjsdfkjl?.fja@qqq");
        tester.setPass("passwklerjhafsd");
        assertTrue(5 == tester.check());

        tester.setEmail("???.@???.?");
        tester.setPass("alkjshdfaslkjdfhlkj13@@2");
        assertTrue(5 == tester.check());
    }

    @Test
    public void testBadPass() {
        SignInVal tester = new SignInVal("yasdfa1@gmail.com", "");
        try{
            assertTrue(3 == tester.check());
        }
        catch (AssertionError e) {
            System.out.println("tester.check() returned: " + tester.check());
            throw e;
        }


        tester.setEmail("yasdf@yahoo.com");
        assertTrue(3 == tester.check());

        tester.setPass("a");
        assertTrue(4 == tester.check());

        tester.setPass("112");
        assertTrue(4 == tester.check());

    }
}

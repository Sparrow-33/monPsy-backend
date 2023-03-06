package com.example.demo.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    @Test
    public void testMail() {
        EmailValidator email = new EmailValidator();
        assertTrue(email.test("test##={}@test.com"));
    }

}
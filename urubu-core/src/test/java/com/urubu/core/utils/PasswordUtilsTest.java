package com.urubu.core.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordUtilsTest {

    @Test
    public void shouldBeValidPassword() {
        String password = "123456&!#Claudio";
        boolean valid = PasswordUtils.validatePassword(password);
        assertTrue(valid);
    }

    @Test
    public void shouldBeInvalidPasswordOnlyNumbers() {
        String password = "123456789";
        boolean valid = PasswordUtils.validatePassword(password);
        assertFalse(valid);
    }

    @Test
    public void shouldBeInvalidPasswordOnlyLetters() {
        String password = "Claudio";
        boolean valid = PasswordUtils.validatePassword(password);
        assertFalse(valid);
    }
}

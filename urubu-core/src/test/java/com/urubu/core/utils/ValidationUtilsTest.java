package com.urubu.core.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationUtilsTest {

    @Test
    public void shouldBeValidCPF() {

        String cpf = "12605716961";

        Boolean valid = ValidationUtils.isNationalRegistryValid(cpf);

        assertTrue(valid);
    }

    @Test
    public void shouldBeValidDirtyCPF() {

        String cpf = "126.057.169-61";

        Boolean valid = ValidationUtils.isNationalRegistryValid(cpf);

        assertTrue(valid);
    }

    @Test
    public void shouldBeInvalidCPF() {

        String cpf = "12605716900";

        Boolean valid = ValidationUtils.isNationalRegistryValid(cpf);

        assertFalse(valid);
    }

    @Test
    public void shouldBeInvalidCPFRepetitive() {

        String cpf = "11111111111";

        Boolean valid = ValidationUtils.isNationalRegistryValid(cpf);

        assertFalse(valid);
    }

    @Test
    public void shouldBeValidCNPJ() {

        String cnpj = "11.222.333/0001-81";

        Boolean valid = ValidationUtils.isNationalRegistryValid(cnpj);

        assertTrue(valid);
    }

    @Test
    public void shouldBeInvalidCNPJ() {

        String cnpj = "32.801.396/0001-81";

        Boolean valid = ValidationUtils.isNationalRegistryValid(cnpj);

        assertFalse(valid);
    }

    @Test
    public void shouldBeInvalidCNPJRepetitive() {

        String cnpj = "11.111.111/0001-11";

        Boolean valid = ValidationUtils.isNationalRegistryValid(cnpj);

        assertFalse(valid);
    }

}

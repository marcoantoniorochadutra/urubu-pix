package com.urubu.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class ValidationUtils {

	private static final String EMAIL_REGEX = "^\\S+@\\S+\\.\\S+$";

	public static Boolean isValidEmail(String email) {
		return email.matches(EMAIL_REGEX);
	}

	public static Boolean isNationalRegistryValid(String nationalRegistry) {

		String sanitizedNationalRegistry = CharUtils.removeChars(nationalRegistry);
		System.err.println(CharUtils.isOnlyRepetitiveChars(sanitizedNationalRegistry));
		if(CharUtils.isOnlyRepetitiveChars(sanitizedNationalRegistry)){
			return false;
		}

		if (checkCnpjLength(sanitizedNationalRegistry)) {
			return validCnpj(sanitizedNationalRegistry);
		}

		if (checkCpfLength(sanitizedNationalRegistry)) {
			return validCpf(sanitizedNationalRegistry);
		}


		return false;
	}

	private static Boolean validCnpj(String sanitizedNationalRegistry) {
		String toCalc = sanitizedNationalRegistry.substring(0, 12);
		String firstVerifierDigit = calcCnpjVerifierDigit(toCalc, List.of(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2));
		toCalc = toCalc.concat(firstVerifierDigit);
		String secondVerifierDigit = calcCnpjVerifierDigit(toCalc, List.of(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2));
		System.err.println(secondVerifierDigit);
		toCalc = toCalc.concat(secondVerifierDigit);
		return toCalc.equals(sanitizedNationalRegistry);
	}

	private static String calcCnpjVerifierDigit(String toCalc, List<Integer> integers) {

		AtomicInteger toMultiply = new AtomicInteger(0);
		int sum = toCalc.chars()
				.map(Character::getNumericValue)
				.map(i -> i * integers.get(toMultiply.getAndIncrement()))
				.reduce(0, Integer::sum);

		return validateRegistryCalc(sum);
	}


	private static Boolean validCpf(String sanitizedNationalRegistry) {
		String toCalc = sanitizedNationalRegistry.substring(0, 9);
		String firstVerifierDigit = calcCpfVerifierDigit(toCalc);
		toCalc = toCalc.concat(firstVerifierDigit);
		String secondtVerifierDigit = calcCpfVerifierDigit(toCalc);
		toCalc = toCalc.concat(secondtVerifierDigit);

		return toCalc.equals(sanitizedNationalRegistry);
	}

	private static String calcCpfVerifierDigit(String toCalc) {
		AtomicInteger toMultiply = new AtomicInteger( toCalc.length() + 1);

		int sum = toCalc.chars()
				.map(Character::getNumericValue)
				.map(i -> i * toMultiply.getAndDecrement())
				.reduce(0, Integer::sum);

		return validateRegistryCalc(sum);
	}

	private static String validateRegistryCalc(int sum) {
		int verifierDigit = sum % 11;
		if (verifierDigit < 2) {
			return "0";
		}

		return String.valueOf(11 - verifierDigit);
	}

	private static boolean checkCpfLength(String cpf) {
		return cpf.length() == 11;
	}

	private static boolean checkCnpjLength(String cnpj) {
		return cnpj.length() == 14;
	}

}

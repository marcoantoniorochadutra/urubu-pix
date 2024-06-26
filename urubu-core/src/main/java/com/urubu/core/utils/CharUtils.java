package com.urubu.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class CharUtils {

    private static final String CHARS = "/\\-.,:$()' ";

    public static String removeChars(String word, String toRemove){

        if (Objects.nonNull(word) && Objects.nonNull(toRemove)){
            for (int i = 0; i < toRemove.length(); i++){
                word = word.replace(String.valueOf(toRemove.charAt(i)), "");
            }
        }

        return StringUtils.isEmpty(word) ? null : word;
    }

    public static String removeChars(String word){
        return removeChars(word, CHARS);
    }
    
	public static Boolean isOnlyRepetitiveChars(String value) {
        return value.chars().allMatch(c -> c == value.charAt(0));
	}
}

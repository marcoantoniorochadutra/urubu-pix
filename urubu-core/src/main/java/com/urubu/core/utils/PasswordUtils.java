package com.urubu.core.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.urubu.core.constants.CoreReturnMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordUtils {

    private static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);

    private static final short MIN_LENGTH = 6;
    private static final short MAX_LENGTH = 20;
    public static final String PASSWORD_PATTERN = String.format("(^(?=.*[a-zA-Z])(?=.*\\d)[\\S]{%d,%d}$)", MIN_LENGTH, MAX_LENGTH);
    public static final String PASSWORD_MESSAGE_MEDIUM = String.format(CoreReturnMessage.WEAK_PASSWORD, MIN_LENGTH, MAX_LENGTH);

	public static String encodeMd5(String s) {
		if (s != null) {
			try {
				String senha = "";

				MessageDigest md = MessageDigest.getInstance("MD5");

				BigInteger hash = new BigInteger(1, md.digest(s.getBytes()));
				senha = hash.toString(16);
				return senha;
			} catch (NoSuchAlgorithmException e) {
				log.error("Error in Password Encoder", e);
			}
		}
		return null;

	}

    public static boolean validatePassword(String password){
        if(StringUtils.isEmpty(password)){
            return false;
        }
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
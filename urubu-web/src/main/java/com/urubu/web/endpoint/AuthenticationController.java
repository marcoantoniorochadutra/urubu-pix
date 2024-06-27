package com.urubu.web.endpoint;

import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

public class AuthenticationController {

//    private AccessDto getAuthorizationHeader(String authorization) {
//        if (StringUtils.isEmpty(authorization)) {
//            throw new LoginException(CoreReturnMessage.LOGIN_UNAUTHORIZED_ERROR, LoginError.TOKEN);
//        }
//        byte[] decoded = Base64.getDecoder().decode(authorization);
//        String[] splited = new String(decoded).split(SystemConstants.DEFAULT_DIVISOR);
//
//        if (splited.length < 1) {
//            throw new LoginException(CoreReturnMessage.LOGIN_UNAUTHORIZED_ERROR, LoginError.TOKEN);
//
//        }
//        return new AccessDto(splited[0], splited[1]);
//    }
}

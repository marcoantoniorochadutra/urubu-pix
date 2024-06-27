package com.urubu.core.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class LoginWeb {

    private String accessToken;
    private String refreshToken;
    private LoginDto login;

}

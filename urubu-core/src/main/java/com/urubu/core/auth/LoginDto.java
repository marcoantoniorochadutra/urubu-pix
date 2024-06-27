package com.urubu.core.auth;

import java.util.Locale;


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
public class LoginDto {

    private String name;
    private String email;
    private Locale locale;

}
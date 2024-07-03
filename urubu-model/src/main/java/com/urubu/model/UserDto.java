package com.urubu.model;

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
public class UserDto {

    private Long id;
    private String nome;
    private String email;
    private Boolean active;

    public UserDto(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.active = true;
    }
}

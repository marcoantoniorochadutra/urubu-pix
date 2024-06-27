package com.urubu.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "integer unsigned")
    private Long id;

    @NotNull
    @Column(columnDefinition = "bit")
    private Boolean active;

    @NotNull
    private Locale locale;

    private String refreshToken;

    public UserDetails(Boolean active, Locale locale) {
        this.active = active;
        this.locale = locale;
    }
}

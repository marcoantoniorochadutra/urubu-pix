package com.urubu.domain.entity;

import com.urubu.domain.ref.AvailableBank;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
@Entity
public class Account {

    @Id
    private Long id;
    private String accountIdentifier;
    private Double balance;
    private AvailableBank bank;

    @OneToOne
    private User user;

}

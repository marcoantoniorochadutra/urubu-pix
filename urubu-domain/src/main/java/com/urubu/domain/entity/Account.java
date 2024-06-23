package com.urubu.domain.entity;

import com.urubu.domain.ref.AvailableBank;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "accountIdentifier", name = "uk_account_identifier"),
})
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String accountIdentifier;

    @NotNull
    private Double balance;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "tinyint unsigned")
    private AvailableBank bank;

    @OneToOne
    @NotNull
    private User user;

}

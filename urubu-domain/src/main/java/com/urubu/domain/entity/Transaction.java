package com.urubu.domain.entity;

import com.urubu.domain.ref.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String transactionIdentifier;

    @NotNull
    @ManyToOne
    @JoinColumn(name="user_id", foreignKey = @ForeignKey(name = "fk_user_transactions"))
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name="account_id", foreignKey = @ForeignKey(name = "fk_account_transactions"))
    private Account account;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "tinyint unsigned")
    private TransactionType tipoTransacao;

    @NotNull
    private Double value;

}

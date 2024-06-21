package com.urubu.domain.entity;

import com.urubu.domain.ref.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Transaction {

    @Id
    private Long id;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private TransactionType tipoTransacao;
    private Double value;

}

package com.urubu.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.urubu.model.base.SelectableDto;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class TransactionDto {

    private Long id;
    private String transactionIdentifier;

    private UserDto user;
    private SelectableDto account;

    private SelectableDto transactionType;
    private SelectableDto paymentMethod;
    private SelectableDto paymentStatus;
    private Double amount;

    private String paymentLink;
    private String pixQrCode;
    private String bankSlipBarCode;
    private String bankSlipNumericLine;

    private CreditCardDto creditCard;
    private CreditCardHolderInfo creditCardHolderInfo;

}

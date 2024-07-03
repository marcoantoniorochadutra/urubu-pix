package com.urubu.service.converter.model;

import com.urubu.domain.entity.Transaction;
import com.urubu.domain.ref.PaymentMethod;
import com.urubu.domain.ref.TransactionType;
import com.urubu.model.TransactionDto;
import com.urubu.service.converter.AbstractConverter;
import org.modelmapper.spi.MappingContext;

import java.util.Objects;

public class TransactionToDomain extends AbstractConverter<TransactionDto, Transaction> {


    @Override
    public Transaction convert(MappingContext<TransactionDto, Transaction> context) {
        TransactionDto dto = context.getSource();
        Transaction dest = validTransaction(context.getDestination(), dto);
		if (Objects.nonNull(dest)) {
            dest.setAmount(dto.getAmount());
            PaymentMethod pay = PaymentMethod.fromStr(dto.getPaymentMethod().getKey());
            dest.setPaymentMethod(pay);
            return dest;
		}

        return null;
    }

    private Transaction validTransaction(Transaction destination, TransactionDto source) {
        Transaction value = null;
        if(Objects.isNull(destination)) {
            value = new Transaction();
            value.setId(getValidLong(source.getId(), null));
        } else {
            value = destination;
        }
        return value;
    }
}

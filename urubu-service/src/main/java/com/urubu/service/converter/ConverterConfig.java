package com.urubu.service.converter;


import com.urubu.domain.entity.Account;
import com.urubu.domain.entity.Transaction;
import com.urubu.domain.ref.PaymentMethod;
import com.urubu.model.TransactionDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfig {

    private final SelectableConverterDomain selectableConverterDomain;

    @Autowired
    public ConverterConfig(SelectableConverterDomain selectableConverterDomain) {
        this.selectableConverterDomain = selectableConverterDomain;
    }


    @Bean
    public ModelMapper mapper() {
        ModelMapper mapper = new ModelMapper();
        setModelToDomain(mapper);
        setDomainToModel(mapper);
        setClientToSomething(mapper);
        return mapper;
    }

	private void setClientToSomething(ModelMapper mapper) {

	}

	private void setDomainToModel(ModelMapper mapper) {
	}

	private void setModelToDomain(ModelMapper mapper) {
        mapper.addMappings(transactionDtoToDomainConverter());

	}

    private PropertyMap<TransactionDto, Transaction> transactionDtoToDomainConverter() {
        return new PropertyMap<TransactionDto, Transaction>() {
            protected void configure() {
                using(new SelectableConverter<>(PaymentMethod.class, selectableConverterDomain)).map(source.getPaymentMethod(), destination.getPaymentMethod());
                using(new SelectableConverter<>(Account.class, selectableConverterDomain)).map(source.getAccount(), destination.getAccount());

            }
        };
    }


}

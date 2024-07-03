package com.urubu.service.converter;

import com.urubu.core.domain.base.Selectable;
import com.urubu.domain.entity.Account;
import com.urubu.domain.repository.AccountRepository;
import com.urubu.model.base.SelectableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SelectableConverterDomain {

    private final AccountRepository accountRepository;

    @Autowired
    public SelectableConverterDomain(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public <E extends Selectable> E convert(Class<E> destination, SelectableDto source) {
        if(destination.isAssignableFrom(Account.class)) {
            return destination.cast(accountRepository.findByAccountIdentifier(source.getKey()));
        }
        return null;
    }
}

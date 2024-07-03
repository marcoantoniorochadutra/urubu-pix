package com.urubu.service.converter;

import com.urubu.core.domain.base.Selectable;
import com.urubu.domain.entity.Account;
import com.urubu.model.base.SelectableDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

public class SelectableConverter <E extends Selectable> implements Converter<SelectableDto, E> {

    private final Class<E> destination;
    private final SelectableConverterDomain selectableConverterDomain;

    public SelectableConverter(Class<E> destination,
                               SelectableConverterDomain selectableConverterDomain) {
        this.destination = destination;
        this.selectableConverterDomain = selectableConverterDomain;
    }

    @Override
	public E convert(MappingContext<SelectableDto, E> context) {
		SelectableDto source = context.getSource();
        if(Objects.nonNull(source)) {
			if (destination.isEnum()) {
                for (E enumConstant : destination.getEnumConstants()) {
                    if (enumConstant.getKey().equals(source.getKey())) {
                        return enumConstant;
                    }
                }
			}
            return destination.cast(selectableConverterDomain.convert(destination, source));

        }
        return null;
    }
}

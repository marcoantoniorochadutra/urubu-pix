package com.urubu.service.converter;


import com.urubu.domain.entity.Transaction;
import com.urubu.model.TransactionDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public abstract class AbstractConverter<S, D> implements Converter<S, D> {

    protected <R> R getConverter(MappingContext<?, ?> context, Class<R> resultClazz, Object value) {
        R result = context.getMappingEngine().map(context.create(value, resultClazz));
        return resultClazz.cast(result);
    }

    public static <T, U> ArrayList<U> map(final ModelMapper mapper, final Collection<T> source, final Class<U> destType) {

        final ArrayList<U> dest = new ArrayList<U>();

        source.forEach(element -> {
            if(Objects.nonNull(element)) {
                dest.add(mapper.map(element, destType));
            }
        });

        return dest;
    }

    protected Long getValidLong(Long object, Long destination) {
        Long destinationValue = Objects.isNull(destination) ? null : destination;
        return Objects.nonNull(object) ? object : destinationValue;
    }

}

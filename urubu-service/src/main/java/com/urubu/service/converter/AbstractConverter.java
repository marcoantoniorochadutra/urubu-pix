package com.urubu.service.converter;


import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.Objects;

public abstract class AbstractConverter<S, D> implements Converter<S, D> {

    protected <R> R getConverter(MappingContext<?, ?> context, Class<R> resultClazz, Object value) {
        R result = context.getMappingEngine().map(context.create(value, resultClazz));
        return resultClazz.cast(result);
    }


}

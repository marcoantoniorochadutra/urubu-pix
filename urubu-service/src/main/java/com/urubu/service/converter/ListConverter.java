package com.urubu.service.converter;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ListConverter {

    public static <T, U> ArrayList<U> map(final ModelMapper mapper, final Collection<T> source, final Class<U> destType) {

        final ArrayList<U> dest = new ArrayList<U>();

        source.forEach(element -> {
            if(Objects.nonNull(element)) {
                dest.add(mapper.map(element, destType));
            }
        });
   
        return dest;
    }
}

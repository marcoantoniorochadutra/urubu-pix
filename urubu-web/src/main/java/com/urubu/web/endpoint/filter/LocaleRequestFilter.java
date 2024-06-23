package com.urubu.web.endpoint.filter;

import com.urubu.core.config.LocaleContext;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Locale;

// TODO FILTRO DEPOIS DE ADICIONAR NO CONTEXTO AS INFOS DE LOGIN E IF LOGIN É TRUE POPULAR COM LOCALE DO LOGIN ELSE PEGAR DO HEADER
@Provider
public class LocaleRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String localeStr = requestContext.getHeaders().getFirst("Content-Language");
        if (StringUtils.isNotEmpty(localeStr)) {
            Locale locale = new Locale(localeStr);
            LocaleContext.setCurrentLocale(locale);

        }
    }

}

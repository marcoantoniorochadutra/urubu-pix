package com.urubu.web.config;



import com.urubu.model.auth.LoginDto;
import com.urubu.service.config.ServiceConfig;
import com.urubu.web.authentication.LoginFactory;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.ext.Provider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;
import org.reflections.Reflections;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Set;

@Configuration
@EnableAutoConfiguration(exclude = {QuartzAutoConfiguration.class})
@Import(value = {ServiceConfig.class})
public class AppConfig extends ResourceConfig {

    @PostConstruct
    public void init() {
        jaxrsEndpointConfig();
        jaxrsBindersConfig();
        swaggerConfig();
    }


    private void swaggerConfig() {
        OpenApiResource openApiResource = new OpenApiResource();
        openApiResource.setResourcePackages(Set.of("com.urubu.web.endpoint", "com.urubu.web.endpoint.config"));
        register(openApiResource);
    }


    private void jaxrsBindersConfig() {
        register(new AbstractBinder(){
            @Override
            protected void configure() {
                bindFactory(LoginFactory.class)
                        .to(LoginDto.class)
                        .in(RequestScoped.class);
            }
        });
    }

    private void jaxrsEndpointConfig() {
        registerControllerResource(
                "com.urubu.web.endpoint",
                "com.urubu.web.endpoint.filter",
                "com.urubu.security.filters",
                "com.urubu.web.endpoint.handler",
                "com.urubu.security.authentication"
        );
    }

    private void registerControllerResource(String... packages) {
        for (String pkg : packages) {
            Reflections reflections = new Reflections(pkg);
            Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Path.class);
            annotated.addAll(reflections.getTypesAnnotatedWith(Provider.class));
            annotated.forEach(this::register);
        }

    }

}

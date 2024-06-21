package com.urubu.web.endpoint.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(
                title = "Documentação Cavalo Crioulo",
                version = "0.0",
                description = "A api dos guri."
        ),
        servers = {@Server(url = "/cc/api", description = "base")},
        tags = {
                @Tag(name = "Cavalos", description = "Cavalos API")
        }
)
public class SwaggerConfig {
}

package com.financemanager.financemanager.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI financeManagerOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Finance Manager API")
                        .description(
                                "Personal Finance Management System APIs"
                        )
                        .version("1.0")
                )
                .externalDocs(
                        new ExternalDocumentation()
                                .description("Project Documentation")
                );
    }
}
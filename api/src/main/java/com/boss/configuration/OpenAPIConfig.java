package com.boss.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Store API")
                .description("API for working with store")
                .version("2.0")
                .contact(apiContact())
                .license(apiLicence());
    }

    private License apiLicence() {
        return new License()
                .name("Apache License 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0.html");
    }

    private Contact apiContact() {
        return new Contact()
                .name("Miskevich Evgeniy")
                .email("evgeniymiskevich@gmail.com");
    }
}
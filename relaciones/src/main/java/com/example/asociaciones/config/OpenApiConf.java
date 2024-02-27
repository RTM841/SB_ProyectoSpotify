package com.example.asociaciones.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
http://localhost:8080/v3/api-docs
http://localhost:8080/swagger-ui/index.html
*/
@Configuration
public class OpenApiConf {

    @Bean
    public  OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Example API")
                        .description("API REST para una app de música"));

    }
}

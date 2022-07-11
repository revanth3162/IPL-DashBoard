package com.springmaven.ipldashbaord.Configuration;


import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .paths(PathSelectors.any())
        .apis(RequestHandlerSelectors.basePackage("com.springmaven"))
        .build()
        .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
            "IPL-Dashboard-2022-API",
            "API List",
            "1.0",
            "Free to use",
            new springfox.documentation.service.Contact("Revanth Baloju", null, "revanthbaloju123@gmail.com"),
            "API License",
            null,
            Collections.emptyList()
        );
    }
}

package com.dellarosamarco.dormantbitcoinseeker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicate.not(PathSelectors.regex("/error")))
                .build()
                .apiInfo(this.apiInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("REST API");
        apiInfoBuilder.description("REST API Generation");
        apiInfoBuilder.version("1.0.0");
        apiInfoBuilder.license("GNU GENERAL PUBLIC LICENSE, Version 3");
        apiInfoBuilder.licenseUrl("https://www.gnu.org/licenses/gpl-3.0.en.html");
        return apiInfoBuilder.build();
    }

    private Predicate<String> paths() {
        return PathSelectors.regex("/basic-error-controller.*");
    }
}

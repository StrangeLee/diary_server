package com.strange.diary_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swaggerInfo());
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder()
                .title("Spring API Documentation for Android Diary Application")
                .description("Your Diary 앱의 서버 API 에 대한 문서입니다.")
                .license("Strange Lee")
                .version("1.0")
                .build();
    }
}

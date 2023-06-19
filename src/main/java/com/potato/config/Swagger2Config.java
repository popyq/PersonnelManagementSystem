package com.potato.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author potato
 * @PackageName:com.potato.config
 * @Description: TODO  Swagger 接口文档
 */
@Configuration
@EnableSwagger2// 开启Swagger功能
public class Swagger2Config {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("人事管理接口文档")
                .version("v1.0")
                .description("API接口测试")
                .termsOfServiceUrl("http://www.baidu.com")
                .licenseUrl("").license("")
                .build();
    }
}

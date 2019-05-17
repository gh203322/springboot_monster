package com.monster.config;

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
 * @author Administrator
 *    swagger配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	    @Bean
	    public Docket createRestApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .select()
	                //swagger要扫描的包路径
	                .apis(RequestHandlerSelectors.basePackage("com.monster.controller"))
	                .paths(PathSelectors.any())
	                .build();
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("springboot monster swagger")
	                .description("多模板聚合项目接口文档")
	                .termsOfServiceUrl("localhost:8080")
	                .version("1.0")
	                .build();
	    }
}

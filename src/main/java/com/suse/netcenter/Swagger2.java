package com.suse.netcenter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Tangerg
 * @create 2019-03-25 21:18
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.suse.netcenter"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("@tangerg","https://github.com/Tangerg","tangerg@qq.com");
        return new ApiInfoBuilder()
                .title("WEBSITE_MANAGE_SYSTEM RESTful APIs")
                .description("网站管理系统接口文档")
                .contact(contact)
                .version("1.0")
                .build();
    }

}
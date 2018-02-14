package com.smn.restlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Component
@PropertySource("classpath:swagger.properties")
public class SwaggerConfig {

    @Autowired
    private ApiConfig apiConfig;

    public Docket apiConfigDocs(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(apiConfig.getBase_package()))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .pathMapping("/api/v1/")
                .apiInfo(this.infoDocs());
    }

    private ApiInfo infoDocs(){
        return new ApiInfo(apiConfig.getTitle(), apiConfig.getDescription(), apiConfig.getVersion(), apiConfig.getTerms(), new Contact(apiConfig.getDevname(), apiConfig.getDevsite(), apiConfig.getDevemail()), apiConfig.getLicense(), apiConfig.getLicenseurl(), new ArrayList<>());
    }

}

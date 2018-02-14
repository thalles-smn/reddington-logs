package com.smn.restlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class RestlogApplication {

	@Autowired
	private SwaggerConfig swaggerConfig;

	public static void main(String[] args) {
		SpringApplication.run(RestlogApplication.class, args);
	}

	@Bean
	public Docket api() {
		return swaggerConfig.apiConfigDocs();
	}

}

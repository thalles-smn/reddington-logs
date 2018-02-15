package com.smn.restlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class RestlogApplicationWar extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RestlogApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RestlogApplication.class);
    }
}

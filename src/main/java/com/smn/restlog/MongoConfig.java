package com.smn.restlog;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig  {

    @Value("${application.restlog.mongo.host}")
    private String host;

    @Value("${application.restlog.mongo.port}")
    private Integer port;

    @Value("${application.restlog.mongo.database}")
    private String database;

    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(host, port);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), database);
    }

}

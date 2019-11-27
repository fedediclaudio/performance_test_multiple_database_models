package com.bd2final.demo.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = "com.bd2final.demo.repositories.mongo")
public class MongoConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "db2Final";
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(Collections.singletonList(new ServerAddress("localhost", 27017)), Collections.singletonList(MongoCredential.createCredential("user", "db2Final", "123456".toCharArray())));
    }


}

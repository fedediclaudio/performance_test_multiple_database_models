package com.bd2Final.elasticproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories("com.bd2Final.elasticproject.repositories")
public class ElasticprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticprojectApplication.class, args);
	}

}

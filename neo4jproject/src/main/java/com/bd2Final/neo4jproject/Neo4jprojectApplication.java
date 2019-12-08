package com.bd2Final.neo4jproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories("com.bd2Final.neo4jproject.repositories")
public class Neo4jprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Neo4jprojectApplication.class, args);
	}

}

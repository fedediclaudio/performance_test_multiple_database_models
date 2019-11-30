package com.bd2final.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EntityScan("com.bd2final.demo.model")
@EnableNeo4jRepositories("com.bd2final.demo.repositories.neo4j")
@EnableJpaRepositories("com.bd2final.demo.repositories.mysql")
@EnableCassandraRepositories("com.bd2final.demo.repositories.cassandra")
public class BitHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitHubApplication.class, args);
	}

}

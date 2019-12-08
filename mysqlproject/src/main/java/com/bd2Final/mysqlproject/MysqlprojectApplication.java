package com.bd2Final.mysqlproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.bd2Final.mysqlproject.model")
@EnableJpaRepositories("com.bd2Final.mysqlproject.repositories")
public class MysqlprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqlprojectApplication.class, args);
	}

}

package com.bd2Final.elasticproject.Config;

import com.bd2Final.elasticproject.model.Commit;
import com.bd2Final.elasticproject.model.User;
import com.bd2Final.elasticproject.repositories.UserRepository;
import com.github.javafaker.Faker;

import java.util.Random;

public class Generator {

    public void generateRegistros(int cantidad, UserRepository userRepository) {
        Faker faker = new Faker();
        Random random = new Random();
        for(int i = 1; i<= cantidad; i++) {
            User user = new User(faker.name().fullName(), faker.internet().emailAddress());
            int cantCommits = random.nextInt(3);
            for(int j = 0; j < cantCommits; j++) {
                Commit commit = new Commit(faker.pokemon().name() ,faker.code().isbn10());
            }
            userRepository.save(user);
        }
    }
}

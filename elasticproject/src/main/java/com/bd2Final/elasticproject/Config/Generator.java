package com.bd2Final.elasticproject.Config;

import com.bd2Final.elasticproject.model.Commit;
import com.bd2Final.elasticproject.model.User;
import com.bd2Final.elasticproject.repositories.CommitRepository;
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
                Commit commit = new Commit(faker.pokemon().name() ,faker.code().isbn10(), user);
            }
            userRepository.save(user);
        }
        // El siguiente es el sin commits:
        userRepository.save(new User("Fede el gordo de la bd", "fedemozzon@gmail.com"));
        // El siguiente tiene commits
        User user = new User("Fede Di Claudio", "fede@gmail.com");
        Commit commit1 = new Commit("Mensaje 1", "1234567890", user);
        Commit commit2 = new Commit("Mensaje2", "0123456789", user);
        userRepository.save(user);
    }
}

package com.bd2final.demo;

import com.bd2final.demo.model.PersistentObject;
import com.bd2final.demo.model.UserJPA;
import com.bd2final.demo.services.BitHubService;
import com.bd2final.demo.services.BitHubServiceMySQL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BitHubApplicationMySQLTests extends BitHubApplicationTest<Long> {

    @Autowired
    private BitHubServiceMySQL service;

    @Override
    protected BitHubService getService() {
        return service;
    }

    @Override
    protected Long getId(PersistentObject object) {
        return (Long) object.getId();
    }
}

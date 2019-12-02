package com.bd2final.demo;

import com.bd2final.demo.model.PersistentObject;
import com.bd2final.demo.model.UserJPA;
import com.bd2final.demo.services.BitHubService;
import com.bd2final.demo.services.BitHubServiceCassandra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class BitHubApplicationCassandraTests extends BitHubApplicationTest<UUID> {

    @Autowired
    private BitHubServiceCassandra service;

    @Override
    protected BitHubService getService() {
        return service;
    }

    @Override
    protected UUID getId(PersistentObject object) {
        return (UUID) object.getId();
    }

}

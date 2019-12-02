package com.bd2final.demo;

import com.bd2final.demo.model.PersistentObject;
import com.bd2final.demo.model.UserElastic;
import com.bd2final.demo.services.BitHubService;
import com.bd2final.demo.services.BitHubServiceElasticsearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class BitHubApplicationElasticsearchTests extends BitHubApplicationTest<String>{

    @Autowired
    private BitHubServiceElasticsearch service;


    @Override
    protected BitHubService getService() {
        return service;
    }

    @Override
    protected String getId(PersistentObject object) {
        return (String) object.getId();
    }
}

package com.bd2final.demo;

import com.bd2final.demo.model.PersistentObject;
import com.bd2final.demo.model.User;
import com.bd2final.demo.services.BitHubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(true)
public abstract class BitHubApplicationTest<T> {

    BitHubService<T> service;

    protected abstract BitHubService getService();

    protected abstract T getId(PersistentObject object);

    @BeforeEach
    public void setUp(){
        User u = this.getService().createUser("a","a");
        this.getService().createCommit("Un commit", "abc", u);
    }

    @Test
    public void listCommit(){
        Iterable<User> res = this.getService().allCommits();
    }

}

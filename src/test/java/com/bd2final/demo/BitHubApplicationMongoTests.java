package com.bd2final.demo;

import com.bd2final.demo.model.PersistentObject;
import com.bd2final.demo.model.UserJPA;
import com.bd2final.demo.services.BitHubService;
import com.bd2final.demo.services.BitHubServiceMongo;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BitHubApplicationMongoTests extends BitHubApplicationTest<ObjectId> {

	@Autowired
	private BitHubServiceMongo service;


	@Override
	protected BitHubService getService() {
		return service;
	}

	@Override
	protected ObjectId getId(PersistentObject object) {
		return (ObjectId) object.getId();
	}
}

package com.example.testmongodb.repository;

import com.example.testmongodb.entity.UserContactEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContactRepository extends MongoRepository<UserContactEntity, String> {
    UserContactEntity getByUserAddr(String userAddr);
}

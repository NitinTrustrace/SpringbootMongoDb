package com.example.userMongo.repository;

import com.example.userMongo.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel,Integer> {
}

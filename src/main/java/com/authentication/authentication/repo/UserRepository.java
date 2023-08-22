package com.authentication.authentication.repo;

import com.authentication.authentication.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

    public User findUserByUsernameAndPassword(String username, String password);
}

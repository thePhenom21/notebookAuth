package com.authentication.authentication.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

@Data
@Document(collection = "user")
public class User {
    private String id;
    private String username;
    private String password;

    public User(){};

    public User(String id, String username, String passcode) {
        this.id = id;
        this.username = username;
        this.password = passcode;
    }

}

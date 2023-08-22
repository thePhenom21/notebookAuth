package com.authentication.authentication.service;

import com.authentication.authentication.model.User;
import com.authentication.authentication.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/login/{username}/{passcode}")
    public ResponseEntity<String> getUserCredentials(@PathVariable String username, @PathVariable String passcode){
        passcode = passDecoder(passcode);
        try{
            User user = userRepository.findUserByUsernameAndPassword(username,passcode);
            if(!Objects.equals(user.getUsername(), "")){
                System.out.println("logged in");
                return ResponseEntity.ok(user.getUsername());
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    @PostMapping("/register/{username}/{passcode}")
    public ResponseEntity<String> registerUser(@PathVariable String username,@PathVariable String passcode){
        passcode = passDecoder(passcode);
        try{
            User user = userRepository.insert(new User(username+passcode+hashCode(),username,passcode));
            return ResponseEntity.ok(user.getUsername());
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    private String passDecoder(String passcode){
        String[] parts = passcode.split("k");
        String a = "";
        for(String d : parts){
            int charac = Integer.valueOf(d,10);
            charac = ((charac >> 12) | (charac << (32 - 12)));
            a = a.concat(String.valueOf((char) charac));
        }
        return a;
    }

}

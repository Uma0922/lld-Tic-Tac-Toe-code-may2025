package com.scaler.bms2025.bmsmay2025.service;


import java.util.Optional;

import org.aspectj.weaver.bcel.BcelAccessForInlineMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.scaler.bms2025.bmsmay2025.model.User;
import com.scaler.bms2025.bmsmay2025.repo.UserRepo;

@Service
public class UserServiceImpl {
    private UserRepo userRepo;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(); // Create a bean for this.
    

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public void createUser(String password, String username) {
       Optional<User> existingUser = userRepo.findByUserName(username);
       if(existingUser.isPresent()){
        // not allow the user creation.
       }
       
       // This is how you actually encrypt 
       User user = new User(username, bCryptPasswordEncoder.encode(password));
       User savedUser =  userRepo.save(user); // yhas1234

    }

    public boolean login(String password, String username){
              Optional<User> existingUser = userRepo.findByUserName(username);
       if(!existingUser.isPresent()){
         // Throw exception from here
       }
       User user = existingUser.get();
        if(bCryptPasswordEncoder.matches(password, user.getPassword())){
            return true;
        }
        return false;
    }
    
}

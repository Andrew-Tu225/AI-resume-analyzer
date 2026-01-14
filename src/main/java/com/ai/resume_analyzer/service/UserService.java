package com.ai.resume_analyzer.service;

import com.ai.resume_analyzer.model.User;
import com.ai.resume_analyzer.repository.UserRepository;
import com.ai.resume_analyzer.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public UserResponse getByUserId(long id){
        Optional<User> user = userRepo.findById(id);

        return user.map(value -> UserResponse.builder()
                .id(id)
                .username(value.getUsername())
                .email(value.getEmail())
                .build()).orElse(null);
    }
}

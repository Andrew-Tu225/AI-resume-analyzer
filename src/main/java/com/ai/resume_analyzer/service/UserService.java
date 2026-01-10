package com.ai.resume_analyzer.service;

import com.ai.resume_analyzer.model.User;
import com.ai.resume_analyzer.repository.UserRepository;
import com.ai.resume_analyzer.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public UserResponse createUser(User createUserRequest) throws Exception {
        try{
            User user = User.builder()
                    .username(createUserRequest.getUsername())
                    .email(createUserRequest.getEmail())
                    .password(createUserRequest.getPassword())
                    .build();

            userRepo.save(user);

            return UserResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .build();
        }
        catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                throw new Exception("username or password already exists");
            }
            else if (e.getMessage().contains("cannot be null")) {
                throw new Exception("fields cannot be null");
            }
            else{
                throw e;
            }
        }
    }
}

package com.ai.resume_analyzer.controller;

import com.ai.resume_analyzer.response.UserResponse;
import com.ai.resume_analyzer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") long id){
        UserResponse user = userService.getByUserId(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(user);
        }
    }
}

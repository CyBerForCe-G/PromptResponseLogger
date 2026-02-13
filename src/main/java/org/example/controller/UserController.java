package org.example.controller;

import org.example.interfaces.UserInfoProvider;
import org.example.model.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserInfoProvider userInfoProvider;

    public UserController(UserInfoProvider userInfoProvider){
        this.userInfoProvider = userInfoProvider;
    }

    @GetMapping("/user")
    public Mono<UserInfo> getUser(){
        return userInfoProvider.getUserInfo();
    }
}

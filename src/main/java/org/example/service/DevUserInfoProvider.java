package org.example.service;

import org.example.interfaces.UserInfoProvider;
import org.example.model.UserInfo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Profile("dev")
public class DevUserInfoProvider implements UserInfoProvider {
    @Override
    public Mono<UserInfo> getUserInfo() {
        return Mono.just(new UserInfo("Dev User", "dev@example.com", null));
    }
}

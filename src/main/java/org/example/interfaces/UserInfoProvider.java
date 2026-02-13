package org.example.interfaces;

import org.example.model.UserInfo;
import reactor.core.publisher.Mono;

public interface UserInfoProvider {
    Mono<UserInfo> getUserInfo();
}

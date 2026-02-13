package org.example.service;

import org.example.interfaces.UserInfoProvider;
import org.example.model.UserInfo;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Profile("!dev")
public class OAuth2UserInfoProvider implements UserInfoProvider {

    @Override
    public Mono<UserInfo> getUserInfo() {
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> ctx.getAuthentication().getPrincipal())
                .filter(principal -> principal instanceof OAuth2User)
                .cast(OAuth2User.class)
                .map(this::extractUserInfo)
                .switchIfEmpty(Mono.empty());
    }

    private UserInfo extractUserInfo(OAuth2User principal){
        String name = principal.getAttribute("name");
        String email = principal.getAttribute("email");
        String picture = principal.getAttribute("picture");

        if(name == null){
            name = principal.getAttribute("given_name");
        }

        if(name == null){
            name = email;
        }
        return new UserInfo(name, email, picture);
    }
}

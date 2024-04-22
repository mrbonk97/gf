package com.mrbonk97.springnextjsoauth2jwtserver.oauth2;

import com.mrbonk97.springnextjsoauth2jwtserver.model.User;
import com.mrbonk97.springnextjsoauth2jwtserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(provider,attributes);
        assert oAuth2UserInfo != null;

        Optional<User> existingUser = userRepository.findByEmail(oAuth2UserInfo.getEmail());

        return existingUser.isEmpty() ?
                createOAuth2User(oAuth2UserInfo) : updateOAuth2User(oAuth2UserInfo, existingUser.get());

    }

    public User createOAuth2User(OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();
        user.setName(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());
        user.setProvider(oAuth2UserInfo.getProvider());
        log.info("신규 유저 회원가입: " + user.getEmail());
        return userRepository.save(user);
    }

    public User updateOAuth2User(OAuth2UserInfo oAuth2UserInfo, User user) {
        if(!user.getProvider().equals(oAuth2UserInfo.getProvider())) throw new RuntimeException("로컬로 회원가입하고 OAuth2로 로그인 시도함. 거절");
        user.setName(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());
        log.info("기존 유저 로그인: " + user.getEmail());
        return userRepository.save(user);
    }
}

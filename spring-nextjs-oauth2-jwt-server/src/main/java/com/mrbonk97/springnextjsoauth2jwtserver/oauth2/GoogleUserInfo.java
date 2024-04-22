package com.mrbonk97.springnextjsoauth2jwtserver.oauth2;

import com.mrbonk97.springnextjsoauth2jwtserver.model.Provider;

import java.util.Map;

public class GoogleUserInfo extends OAuth2UserInfo{
    public GoogleUserInfo(Map<String, Object> attributes) {
        super(attributes, Provider.google);
    }
    @Override
    String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    String getImageUrl() {
        return (String) attributes.get("picture");
    }

    @Override
    Provider getProvider() {
        return provider;
    }
}

package com.mrbonk97.springnextjsoauth2jwtserver.oauth2;

import com.mrbonk97.springnextjsoauth2jwtserver.model.Provider;

import java.util.LinkedHashMap;
import java.util.Map;

public class NaverUserInfo extends OAuth2UserInfo{

    public NaverUserInfo(Map<String, Object> attributes) { super(attributes, Provider.naver); }

    @Override
    String getId() {
        LinkedHashMap<String, String> temp = (LinkedHashMap<String, String>) attributes.get("response");
        return (String) temp.get("sub");
    }

    @Override
    String getName() {
        LinkedHashMap<String, String> temp = (LinkedHashMap<String, String>) attributes.get("response");
        return (String) temp.get("name");
    }

    @Override
    String getEmail() {
        LinkedHashMap<String, String> temp = (LinkedHashMap<String, String>) attributes.get("response");
        return (String) temp.get("email");
    }

    @Override
    String getImageUrl() {
        LinkedHashMap<String, String> temp = (LinkedHashMap<String, String>) attributes.get("response");
        return (String) temp.get("profile_image");
    }

    @Override
    Provider getProvider() {
    return Provider.naver;
    }
}

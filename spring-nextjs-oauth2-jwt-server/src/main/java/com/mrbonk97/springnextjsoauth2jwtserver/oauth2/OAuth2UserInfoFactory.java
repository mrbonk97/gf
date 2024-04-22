package com.mrbonk97.springnextjsoauth2jwtserver.oauth2;

import com.mrbonk97.springnextjsoauth2jwtserver.model.Provider;
import com.mrbonk97.springnextjsoauth2jwtserver.oauth2.GoogleUserInfo;
import com.mrbonk97.springnextjsoauth2jwtserver.oauth2.OAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String provider, Map<String, Object> attributes) {
        if(provider.equalsIgnoreCase(Provider.google.toString())) {
            return new GoogleUserInfo(attributes);
        }

        if(provider.equalsIgnoreCase(Provider.naver.toString())) {
            return new NaverUserInfo(attributes);
        }

        throw new RuntimeException("current Oauth2 Provider Not Supported");
    }


}

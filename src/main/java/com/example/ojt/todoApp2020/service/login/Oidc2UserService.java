package com.example.ojt.todoApp2020.service.login;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Oidc2UserService extends OidcUserService {

    @Override
    @Transactional(readOnly = false)
    public OidcUser loadUser(OidcUserRequest userRequest)
        throws OAuth2AuthenticationException {
        String idPType = userRequest.getClientRegistration().getRegistrationId();
        switch(idPType) {
            case "google":
                saveGoogleUser(userRequest);
        }
        return null;
//         ⑤-1と同様
//        return saveAndGenerateUserDetails.process(userRequest,
//            super.loadUser(userRequest));
    }

    private void saveGoogleUser(OidcUserRequest userRequest) {
        var user = super.loadUser(userRequest);
    }
}

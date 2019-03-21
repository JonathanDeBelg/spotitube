package nl.han.dea.service;

import nl.han.dea.dto.TokenDTO;

import javax.enterprise.inject.Alternative;

@Alternative
public class LocalAuthenticationService implements AuthenticationService {
    @Override
    public TokenDTO login(String username, String password) {
        return new TokenDTO("1234", username);
    }
}


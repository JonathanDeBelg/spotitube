package nl.han.dea.service;

import nl.han.dea.dto.TokenDTO;

public interface AuthenticationService {
    TokenDTO login(String username, String password);
}

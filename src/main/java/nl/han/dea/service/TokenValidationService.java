package nl.han.dea.service;

import nl.han.dea.dto.UserDTO;

public interface TokenValidationService {
    UserDTO validateToken(String token);
}

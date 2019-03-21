package nl.han.dea.service;

import nl.han.dea.dto.TokenDTO;
import nl.han.dea.dto.UserDTO;
import nl.han.dea.persistence.dao.TokenDAO;

import javax.enterprise.inject.Default;

@Default
public class TokenValidationServiceImpl implements TokenValidationService {
    TokenDAO tokenDAO = new TokenDAO();

    @Override
    public UserDTO validateToken(String token) {
        UserDTO userDTO = tokenDAO.validateToken(token);

        if (userDTO != null) {
            return userDTO;
        } else {
            throw new SpotitubeTokenValidationException("Token doesn't exist for user " + userDTO.getUser());
        }
    }
}

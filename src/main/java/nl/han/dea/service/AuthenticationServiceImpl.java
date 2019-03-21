package nl.han.dea.service;

import nl.han.dea.dto.TokenDTO;
import nl.han.dea.dto.UserDTO;
import nl.han.dea.persistence.dao.TokenDAO;
import nl.han.dea.persistence.dao.UserDAO;
import nl.han.dea.util.TokenGenerator;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class AuthenticationServiceImpl implements AuthenticationService {
    private TokenGenerator tokenGenerator = new TokenGenerator();
    private TokenDAO tokenDAO;
    private UserDAO userDAO;

    public AuthenticationServiceImpl() {
    }

    @Inject
    public AuthenticationServiceImpl(UserDAO userDAO, TokenDAO tokenDAO) {
        this.userDAO = userDAO;
        this.tokenDAO = tokenDAO;
    }

    @Override
    public TokenDTO login(String username, String password) {
        UserDTO user = userDAO.getUser(username, password);
        if (user != null) {
            TokenDTO tokenDTO = new TokenDTO(tokenGenerator.generateToken(), username);
            tokenDAO.setTokenInDatabase(tokenDTO);

            return tokenDTO;
        } else if (username.isEmpty() || password.isEmpty()) {
            throw new SpotitubeLoginException("Missing input for a field", ExceptionCause.MISSING_INPUT);
        } else {
            throw new SpotitubeLoginException("Login failed for user " + username, ExceptionCause.WRONG_USERNAME_PASSWORD);
        }
    }
}

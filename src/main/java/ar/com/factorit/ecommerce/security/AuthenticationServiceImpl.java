package ar.com.factorit.ecommerce.security;

import ar.com.factorit.ecommerce.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void saveToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    @Override
    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }
}

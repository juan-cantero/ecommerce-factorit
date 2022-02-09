package ar.com.factorit.ecommerce.security;

import ar.com.factorit.ecommerce.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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

    @Override
    public Optional<User> getUser(String token) {
        Optional<AuthenticationToken> authenticationTokenOptional = tokenRepository.findByToken(token);
        if (authenticationTokenOptional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(authenticationTokenOptional.get().getUser());

    }

    @Override
    public User authenticate(String token) throws AuthenticationFailException {
        if (Objects.isNull(token)) {
            throw new AuthenticationFailException();
        }
        if (getUser(token).isEmpty()) {
            throw new AuthenticationFailException();
        }
        return getUser(token).get();
    }
}

package ar.com.factorit.ecommerce.security;

import ar.com.factorit.ecommerce.user.User;

import java.util.Optional;

public interface AuthenticationService {
    void saveToken(AuthenticationToken authenticationToken);

    AuthenticationToken getToken(User user);

    Optional<User> getUser(String token);

    User authenticate(String token) throws AuthenticationFailException;
}

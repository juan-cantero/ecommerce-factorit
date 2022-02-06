package ar.com.factorit.ecommerce.security;

import ar.com.factorit.ecommerce.user.User;

public interface AuthenticationService {
    void saveToken(AuthenticationToken authenticationToken);

    AuthenticationToken getToken(User user);
}

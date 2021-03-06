package ar.com.factorit.ecommerce.security;

import ar.com.factorit.ecommerce.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findByUser(User user);
    Optional<AuthenticationToken> findByToken(String token);
}

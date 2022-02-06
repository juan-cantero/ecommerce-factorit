package ar.com.factorit.ecommerce.user;

import ar.com.factorit.ecommerce.common.ApiResponse;
import ar.com.factorit.ecommerce.security.SignInDto;
import ar.com.factorit.ecommerce.security.SignInResponse;
import ar.com.factorit.ecommerce.security.SignUpDto;
import ar.com.factorit.ecommerce.security.SignupResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    SignupResponse signUp(SignUpDto signUpDto) throws Exception;

    SignInResponse signIn(SignInDto signInDto);
}

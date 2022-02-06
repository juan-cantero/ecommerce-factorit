package ar.com.factorit.ecommerce.user;

import ar.com.factorit.ecommerce.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignUpDto signUpDto) {

        try {

            SignupResponse signupResponse = userService.signUp(signUpDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(signupResponse);
        } catch (UserAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user already exist", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error", e);
        }

    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signin(@RequestBody SignInDto signInDto) {
        try {
            SignInResponse signInResponse = userService.signIn(signInDto);
            return ResponseEntity.status(HttpStatus.OK).body(signInResponse);
        } catch (AuthenticationFailException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "authentication error", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error", e);
        }
    }

}

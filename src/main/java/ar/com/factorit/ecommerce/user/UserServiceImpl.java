package ar.com.factorit.ecommerce.user;

import ar.com.factorit.ecommerce.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Transactional
    @Override
    public SignupResponse signUp(SignUpDto signUpDto) throws Exception {

        if(checkUserExistByEmail(signUpDto.getEmail()))
        {
            throw new UserAlreadyExistsException();
        }

        String encryptedPassword = signUpDto.getPassword();

        try {
            encryptedPassword = hashPassword(signUpDto.getPassword());
        }
        catch (NoSuchAlgorithmException e) {
            throw new Exception("internal server error");
        }

        User user = User.builder()
                .userId(signUpDto.getDni())
                .firstName(signUpDto.getFirstName())
                .lastName(signUpDto.getFirstName())
                .email(signUpDto.getEmail())
                .password(encryptedPassword)
                .build();

        User userDB = userRepository.save(user);

        AuthenticationToken authenticationToken = new AuthenticationToken(user);

        authenticationService.saveToken(authenticationToken);


        return SignupResponse.builder().messsage("user created").success(true).build();
    }

    @Override
    public SignInResponse signIn(SignInDto signInDto) {
        User user = userRepository.findByEmail(signInDto.getEmail());

        if(Objects.isNull(user)) {
            throw new AuthenticationFailException();
        }

        try {
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
                throw new AuthenticationFailException();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        AuthenticationToken token = authenticationService.getToken(user);

        if (Objects.isNull(token)) {
            throw new AuthenticationFailException();
        }

        return SignInResponse.builder()
                .status("success")
                .token(token.getToken())
                .build();

    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase(Locale.ROOT);
        return hash;

    }

    private boolean checkUserExistByEmail(String email) {
        return Objects.nonNull(userRepository.findByEmail(email));
    }


}

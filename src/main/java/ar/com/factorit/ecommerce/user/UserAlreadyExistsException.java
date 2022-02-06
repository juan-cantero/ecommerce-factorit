package ar.com.factorit.ecommerce.user;

public class UserAlreadyExistsException extends IllegalArgumentException {
    public UserAlreadyExistsException() {
        super("User already exist");
    }
}

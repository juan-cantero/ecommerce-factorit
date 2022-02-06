package ar.com.factorit.ecommerce.security;

public class AuthenticationFailException extends IllegalArgumentException{
    public AuthenticationFailException() {
        super("Bad password or user");
    }
}

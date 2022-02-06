package ar.com.factorit.ecommerce.security;

import lombok.Data;

@Data
public class SignInDto {
    private String email;
    private String password;
}

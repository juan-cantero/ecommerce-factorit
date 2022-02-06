package ar.com.factorit.ecommerce.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInResponse {
    private String status;
    private String token;
}

package ar.com.factorit.ecommerce.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupResponse {
    private final boolean success;
    private final String messsage;
}

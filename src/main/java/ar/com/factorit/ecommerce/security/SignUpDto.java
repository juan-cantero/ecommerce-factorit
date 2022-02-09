package ar.com.factorit.ecommerce.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpDto {
    private Integer dni;
    private String firstName;
    private String lasName;
    private String email;
    private String password;
}

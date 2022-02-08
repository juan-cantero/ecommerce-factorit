package ar.com.factorit.ecommerce.common;

import lombok.Builder;
import lombok.Data;

@Data
public class ApiResponse {
    private final boolean success;
    private final String message;

}

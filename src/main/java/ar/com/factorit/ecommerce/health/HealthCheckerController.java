package ar.com.factorit.ecommerce.health;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthCheckerController {

    @Operation(description = "Check if the app is up")
    @GetMapping("/isalive")
    public String isAlive() {
        return "Is Alive!!!";
    }
}

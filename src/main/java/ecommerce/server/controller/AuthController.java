package ecommerce.server.controller;

import ecommerce.server.dto.UserDataDto;
import ecommerce.server.model.request.LoginRequest;
import ecommerce.server.model.request.RegisterRequest;
import ecommerce.server.model.response.AuthenticationResponse;
import ecommerce.server.model.response.MessageResponse;
import ecommerce.server.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                MessageResponse.builder().message("Register successful!").build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequest request) {
        UserDataDto userData = authService.login(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                AuthenticationResponse.builder()
                        .message("login successful!")
                        .username(userData.getUsername())
                        .email(userData.getEmail())
                        .token(userData.getToken())
                        .build());
    }
}

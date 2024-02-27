package ecommerce.server.service.Impl;

import ecommerce.server.dto.CustomException;
import ecommerce.server.entity.User;
import ecommerce.server.model.request.LoginRequest;
import ecommerce.server.model.request.RegisterRequest;
import ecommerce.server.model.response.AuthenticationResponse;
import ecommerce.server.repository.UserRepository;
import ecommerce.server.security.JwtTokenUtil;
import ecommerce.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void register(RegisterRequest request) {
        String email = request.getEmail();

        // if user already registered, it is an error
        if (userRepository.findByEmail(email).isPresent()) {
            throw CustomException.builder().message("User already registered").status(400).build();
        }

        // TODO: enum roles
        userRepository.register(request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()), "USER");
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        // auth - check DB for user and password info
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        Optional<User> user = userRepository.findByEmail(request.getEmail());
        String jwtToken = jwtTokenUtil.generateToken(user.get());
        return AuthenticationResponse.builder()
                .username(user.get().getName())
                .email(user.get().getEmail())
                .token(jwtToken)
                .build();
    }
}

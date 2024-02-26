package ecommerce.server.service.Impl;

import ecommerce.server.model.request.RegisterRequest;
import ecommerce.server.repository.UserRepository;
import ecommerce.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    @Override
    public void register(RegisterRequest request) {
        String email = request.getEmail();

        // if user already registered, it is an error
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("User already registered");
        }

        // TODO: enum roles
        userRepository.register(request.getName(), request.getEmail(), request.getPassword(), "USER");
    }
}

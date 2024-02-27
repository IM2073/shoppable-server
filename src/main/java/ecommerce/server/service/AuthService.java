package ecommerce.server.service;

import ecommerce.server.model.request.LoginRequest;
import ecommerce.server.model.request.RegisterRequest;
import ecommerce.server.model.response.AuthenticationResponse;

public interface AuthService  {
    void register(RegisterRequest request);
    AuthenticationResponse login(LoginRequest request);
}

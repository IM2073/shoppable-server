package ecommerce.server.service;

import ecommerce.server.dto.UserDataDto;
import ecommerce.server.model.request.LoginRequest;
import ecommerce.server.model.request.RegisterRequest;
import ecommerce.server.model.response.AuthenticationResponse;

public interface AuthService  {
    void register(RegisterRequest request);
    UserDataDto login(LoginRequest request);
}

package ecommerce.server.service;

import ecommerce.server.model.request.RegisterRequest;

public interface AuthService  {
    void register(RegisterRequest request);
}

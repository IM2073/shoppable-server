package ecommerce.server.service.Impl;

import ecommerce.server.entity.Cart;
import ecommerce.server.entity.User;
import ecommerce.server.repository.CartRepository;
import ecommerce.server.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartImpl implements CartService {
    private final CartRepository cartRepository;
    @Override
    public List<Cart> getUserCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = ((User)  authentication.getPrincipal()).getId();
        return cartRepository.getUserCart(userId);
    }

    @Override
    public void deleteUserCart() {

    }

    @Override
    public void addCartItem(Integer productId) {

    }

    @Override
    public void deleteCartItem(Integer productId) {

    }

    @Override
    public void updateQuantityCartItem(Integer productId) {

    }
}

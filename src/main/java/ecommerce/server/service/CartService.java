package ecommerce.server.service;

import ecommerce.server.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getUserCart();
    void deleteUserCart();
    void addCartItem(Integer productId);
    void deleteCartItem(Integer productId);
    void updateQuantityCartItem(Integer productId);
}

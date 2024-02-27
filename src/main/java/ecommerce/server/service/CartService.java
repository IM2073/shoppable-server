package ecommerce.server.service;

import ecommerce.server.entity.Cart;
import ecommerce.server.model.request.CartItemRequest;

import java.util.List;

public interface CartService {
    List<Cart> getUserCart();
    void deleteUserCart();
    void addCartItem(Integer productId, CartItemRequest request);
    void deleteCartItem(Integer productId);
    void updateQuantityCartItem(Integer productId, String updateStatus);
}

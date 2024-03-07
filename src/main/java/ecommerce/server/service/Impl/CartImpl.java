package ecommerce.server.service.Impl;

import ecommerce.server.dto.CustomException;
import ecommerce.server.entity.Cart;
import ecommerce.server.entity.Product;
import ecommerce.server.entity.User;
import ecommerce.server.model.request.CartItemRequest;
import ecommerce.server.repository.CartRepository;
import ecommerce.server.repository.ProductRepository;
import ecommerce.server.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    @Override
    public List<Cart> getUserCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = ((User)  authentication.getPrincipal()).getId();
        return cartRepository.findByUserId(userId);
    }

    @Override
    public void deleteUserCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = ((User)  authentication.getPrincipal()).getId();
        cartRepository.deleteUserCart(userId);
    }

    @Override
    public void addCartItem(Integer productId, CartItemRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = ((User)  authentication.getPrincipal()).getId();

        // If request is null or quantity is not provided, set default quantity to 1
        if (request == null || request.getQuantity() == null) {
            request = new CartItemRequest();
            request.setQuantity(1);
        }

        Optional<Product> productOptional = productRepository.getProductDetail(productId);
        if (productOptional.isEmpty()) {
            throw new CustomException("Product not found", 404);
        }

        Product product = productOptional.get();
        int availableStock = product.getStock();

        // Check if requested quantity exceeds available stock
        int requestedQuantity = request.getQuantity();
        if (requestedQuantity > availableStock) {
            throw new CustomException("Requested quantity exceeds available stock for product: " + product.getName(), 400);
        }

        // check if product already in cart or not
        Optional<Cart> cartItem = cartRepository.getCartByUserAndProduct(productId, userId);
        if (cartItem.isEmpty()) {
            cartRepository.addCartItem(productId, userId, requestedQuantity);
        } else {
            cartRepository.updateQuantity(cartItem.get().getId(), requestedQuantity);
        }
    }

    @Override
    public void deleteCartItem(Integer productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = ((User)  authentication.getPrincipal()).getId();
        cartRepository.deleteCartItem(productId, userId);
    }

    @Override
    public void updateQuantityCartItem(Integer productId, String updateStatus) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = ((User) authentication.getPrincipal()).getId();

        // validate status
        if (!"increment".equalsIgnoreCase(updateStatus) && !"decrement".equalsIgnoreCase(updateStatus)) {
            throw new CustomException("Invalid update status. Only 'increment' or 'decrement' are allowed.", 400);
        }

        Cart cartItem = cartRepository.getCartByUserAndProduct(productId, userId).orElseThrow(() -> new CustomException("Product in cart not found", 404));
        Product product = productRepository.getProductDetail(productId).orElseThrow(() -> new CustomException("Product not found", 404));
        int updatedQuantity = (updateStatus.equals("increment")) ? cartItem.getQuantity() + 1 : cartItem.getQuantity() - 1;

        // update the cart quantity
        if (0 < updatedQuantity && updatedQuantity <= product.getStock()) {
            cartRepository.updateQuantity(cartItem.getId(), updatedQuantity);
        } else if (updatedQuantity == 0) {
            cartRepository.deleteCartItem(productId, userId);
        } else {
            throw new CustomException("The updated quantity exceeds the available stock", 400);
        }
    }
}

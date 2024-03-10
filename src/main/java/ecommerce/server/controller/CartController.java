package ecommerce.server.controller;

import ecommerce.server.dto.CartDto;
import ecommerce.server.entity.Cart;
import ecommerce.server.model.request.CartItemRequest;
import ecommerce.server.service.CartService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@SecurityRequirement(name="Bearer Authentication")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartDto>> getUserCart() {
        List<CartDto> carts = cartService.getUserCart();
        return ResponseEntity.status(HttpStatus.OK).body(carts);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteCart() {
        cartService.deleteUserCart();
        return ResponseEntity.status(HttpStatus.OK).body("cart deleted successfully");
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Object> addCartItem(@PathVariable Integer productId, @RequestBody CartItemRequest cartItemRequest) {
        cartService.addCartItem(productId, cartItemRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Item added to cart successfully");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Object> deleteCartItem(@PathVariable Integer productId) {
        cartService.deleteCartItem(productId);
        return ResponseEntity.status(HttpStatus.OK).body("Item deleted from cart successfully");
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Object> updateQuantityItem(@PathVariable Integer productId, @RequestParam(required = true) String updateStatus) {
        cartService.updateQuantityCartItem(productId, updateStatus);
        return ResponseEntity.status(HttpStatus.OK).body("Quantity cart item updated successfully");
    }
}

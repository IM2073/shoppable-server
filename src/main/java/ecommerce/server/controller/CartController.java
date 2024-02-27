package ecommerce.server.controller;

import ecommerce.server.entity.Cart;
import ecommerce.server.service.CartService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@SecurityRequirement(name="Bearer Authentication")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<Cart>> getUserCart() {
        List<Cart> carts = cartService.getUserCart();
        return ResponseEntity.status(HttpStatus.OK).body(carts);
    }

    @PostMapping("/{productId}")
    public ResponseEntity addCartItem(@PathVariable Integer productId) {
        cartService.addCartItem(productId);
        return ResponseEntity.status(HttpStatus.OK).body("Item added to cart successfully");
    }
}

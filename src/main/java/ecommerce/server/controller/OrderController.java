package ecommerce.server.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import ecommerce.server.dto.CheckoutItemDto;
import ecommerce.server.dto.OrderDetailDto;
import ecommerce.server.dto.OrderDto;
import ecommerce.server.entity.Order;
import ecommerce.server.entity.OrderDetail;
import ecommerce.server.model.response.StripeResponse;
import ecommerce.server.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@SecurityRequirement(name="Bearer Authentication")
@Slf4j
public class OrderController {
    private final OrderService orderService;
    // get the user order
    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<OrderDto> orderList = orderService.getOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }

    // get the order details by orderId and userId
    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderDetailDto>> getOrderDetail(@PathVariable Integer orderId) {
        List<OrderDetailDto> orderDetailList = orderService.getOrderDetail(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDetailList);
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDto) throws StripeException {
        Session session = orderService.createSession(checkoutItemDto);
        StripeResponse stripeResponse = new StripeResponse(session.getId(), session.getUrl());
        return new ResponseEntity<StripeResponse>(stripeResponse,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addOrder(@RequestBody String sessionId) {
        orderService.addOrder(sessionId);
        return ResponseEntity.status(HttpStatus.OK).body("order created successful");
    }
}

package ecommerce.server.controller;

import ecommerce.server.entity.Order;
import ecommerce.server.entity.OrderDetail;
import ecommerce.server.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@SecurityRequirement(name="Bearer Authentication")
public class OrderController {
    private final OrderService orderService;
    // get the user order
    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orderList = orderService.getOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }

    // get the order details by orderId and userId
    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderDetail>> getOrderDetail(@PathVariable Integer orderId) {
        List<OrderDetail> orderDetailList = orderService.getOrderDetail(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDetailList);
    }

    // put order - change the status of the order to paid after payment is done
    @PutMapping("/{orderId}")
    public ResponseEntity<Object> updateOrderStatus(@PathVariable Integer orderId) {
        orderService.updateOrderStatus(orderId);
        return ResponseEntity.status(HttpStatus.OK).body("Order status updated");
    }
}

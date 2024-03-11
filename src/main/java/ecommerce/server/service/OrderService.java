package ecommerce.server.service;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import ecommerce.server.dto.CheckoutItemDto;
import ecommerce.server.dto.OrderDetailDto;
import ecommerce.server.dto.OrderDto;
import ecommerce.server.entity.Order;
import ecommerce.server.entity.OrderDetail;

import java.util.List;

public interface OrderService {
     List<OrderDto> getOrders();
     List<OrderDetailDto> getOrderDetail(Integer orderId);
     void addOrder(String sessionId);
     Session createSession(List<CheckoutItemDto> checkoutItemDto) throws StripeException;
}

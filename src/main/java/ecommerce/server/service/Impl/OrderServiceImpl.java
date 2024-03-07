package ecommerce.server.service.Impl;

import ecommerce.server.dto.CustomException;
import ecommerce.server.entity.Order;
import ecommerce.server.entity.OrderDetail;
import ecommerce.server.entity.User;
import ecommerce.server.repository.OrderDetailRepository;
import ecommerce.server.repository.OrderRepository;
import ecommerce.server.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    @Override
    public List<Order> getOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = ((User)  authentication.getPrincipal()).getId();
        return orderRepository.getUserOrders(userId);
    }

    @Override
    public List<OrderDetail> getOrderDetail(Integer orderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = ((User)  authentication.getPrincipal()).getId();

        Optional<List<OrderDetail>> orderDetail = orderDetailRepository.getOrderDetail(orderId);
        Optional<Order> order = orderRepository.getOrderById(orderId);

        if (order.isEmpty() || orderDetail.isEmpty() || !Objects.equals(order.get().getUserId(), userId)) {
            throw new CustomException("No order found", 404);
        }

        return orderDetail.get();
    }

    @Override
    public void updateOrderStatus(Integer orderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = ((User)  authentication.getPrincipal()).getId();
        orderRepository.updateOrderStatus(orderId, userId);
    }
}

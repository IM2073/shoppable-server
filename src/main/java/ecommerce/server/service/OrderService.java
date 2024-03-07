package ecommerce.server.service;

import ecommerce.server.entity.Order;
import ecommerce.server.entity.OrderDetail;

import java.util.List;

public interface OrderService {
     List<Order> getOrders();
     List<OrderDetail> getOrderDetail(Integer orderId);
     void updateOrderStatus(Integer orderId);
}

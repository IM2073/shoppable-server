package ecommerce.server.service.Impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import ecommerce.server.dto.CheckoutItemDto;
import ecommerce.server.dto.CustomException;
import ecommerce.server.dto.OrderDetailDto;
import ecommerce.server.dto.OrderDto;
import ecommerce.server.entity.Cart;
import ecommerce.server.entity.Order;
import ecommerce.server.entity.OrderDetail;
import ecommerce.server.entity.User;
import ecommerce.server.model.request.CheckoutItemRequest;
import ecommerce.server.repository.CartRepository;
import ecommerce.server.repository.OrderDetailRepository;
import ecommerce.server.repository.OrderRepository;
import ecommerce.server.repository.ProductRepository;
import ecommerce.server.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Value("${baseURL}")
    private String baseURL;
    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    @Override
    public List<OrderDto> getOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = ((User)  authentication.getPrincipal()).getId();
        List<Order> orderList = orderRepository.getUserOrders(userId);
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (Order order: orderList) {
            Optional<List<OrderDetail>> orderDetail = orderDetailRepository.findAllByOrderId(order.getId());
            if (orderDetail.isEmpty()) continue;
            orderDtoList.add(OrderDto.builder()
                    .id(order.getId())
                    .dateIn(orderDetail.get().get(0).getDateIn())
                    .imageUrl(orderDetail.get().get(0).getProductOrder().getImageUrl())
                    .status(order.getStatus())
                    .totalPrice(order.getTotalPrice())
                    .build());
        }

        return orderDtoList;
    }

    @Override
    public List<OrderDetailDto> getOrderDetail(Integer orderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = ((User)  authentication.getPrincipal()).getId();

        // Retrieve the order details for the given orderId
        Optional<List<OrderDetail>> orderDetails = orderDetailRepository.findAllByOrderId(orderId);

        // Retrieve the order of this particular id
        Optional<Order> order = orderRepository.getOrderById(orderId);

        // check the userId is the assosciated one or not
        if (order.isEmpty() || !Objects.equals(order.get().getUserId(), userId) || orderDetails.isEmpty()) {
            throw new CustomException("No orders found", 404);
        }

        // Convert OrderDetail entities to DTOs
        List<OrderDetailDto> orderDetailDTOs = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails.get()) {
            orderDetailDTOs.add(
                    OrderDetailDto.builder()
                            .id(orderDetail.getId())
                            .productOrder(orderDetail.getProductOrder())
                            .quantity(orderDetail.getQuantity())
                            .subtotal(orderDetail.getSubtotal())
                            .dateIn(orderDetail.getDateIn())
                            .order(orderDetail.getOrder())
                            .build()
            );
        }

        return orderDetailDTOs;
    }

    @Override
    public void addOrder(String sessionId) {
        if (sessionId == null) throw new CustomException("SessionId not found", 400);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = ((User)  authentication.getPrincipal()).getId();

        // get all the cart item
        List<Cart> cartList = cartRepository.findByUserId(userId);

        int totalPrice = 0;
        for (Cart cart: cartList) {
            totalPrice += cart.getQuantity() * cart.getProduct().getPrice();
        }

        // create order
        orderRepository.addOrder(userId, totalPrice, "paid");
        Integer orderId = orderRepository.getLastInsertedOrderIdForUser(userId);

        // insert to orderDetail
        for (Cart cart : cartList) {
            orderDetailRepository.addOrderDetail(cart.getProduct().getId(), orderId, cart.getQuantity(), cart.getQuantity() * cart.getProduct().getPrice());
            productRepository.consumeProductStock(cart.getProduct().getId(), cart.getQuantity());
        }

        // delete cart
        cartRepository.deleteUserCart(userId);
    }

    // setting currency and product name
    SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount( ((long) checkoutItemDto.getPrice()) * 100)
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkoutItemDto.getProductName())
                                .build())
                .build();
    }

    SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.builder()
                .setPriceData(createPriceData(checkoutItemDto))
                .setQuantity(Long.parseLong(String.valueOf(checkoutItemDto.getQuantity())))
                .build();
    }

    @Override
    public Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        String successURL = baseURL + "payment/success";
        String failedURL = baseURL + "cart";

        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> sessionItemsList = new ArrayList<SessionCreateParams.LineItem>();
        for (CheckoutItemDto checkoutItemDto : checkoutItemDtoList) {
            sessionItemsList.add(createSessionLineItem(checkoutItemDto));
        }

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failedURL)
                .addAllLineItem(sessionItemsList)
                .setSuccessUrl(successURL)
                .build();
        return Session.create(params);
    }
}

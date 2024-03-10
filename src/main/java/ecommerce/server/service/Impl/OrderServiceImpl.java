package ecommerce.server.service.Impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import ecommerce.server.dto.CheckoutItemDto;
import ecommerce.server.dto.CustomException;
import ecommerce.server.entity.Cart;
import ecommerce.server.entity.Order;
import ecommerce.server.entity.OrderDetail;
import ecommerce.server.entity.User;
import ecommerce.server.model.request.CheckoutItemRequest;
import ecommerce.server.repository.CartRepository;
import ecommerce.server.repository.OrderDetailRepository;
import ecommerce.server.repository.OrderRepository;
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
    public void addOrder() {
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

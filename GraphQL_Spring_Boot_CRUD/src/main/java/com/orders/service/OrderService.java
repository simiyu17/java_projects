package com.orders.service;

import com.orders.domain.Order;
import com.orders.dto.OrderItemDto;

import java.security.SecureRandom;
import java.util.List;
import java.util.Set;

public interface OrderService {

    Order saveOrder(String vendorName);

    Order saveOrderWithItems(final String vendorName, final Set<OrderItemDto> orderItems);

    Order addOrderItem(OrderItemDto orderItemDto);
    Order findOrderById(Long id);

    Order findOrderByOrderNumber(String orderNumber);

    List<Order> findAvailableOrders(String status);

    default String generateOrderNumber(){
        var random = new SecureRandom();
        var min = 100001;
        var max = 9999999;
        return String.format("%d", random.nextInt(random.nextInt(max-min+1)+min));
    }
}

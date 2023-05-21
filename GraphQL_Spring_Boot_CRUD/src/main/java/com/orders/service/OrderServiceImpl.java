package com.orders.service;

import com.orders.domain.Order;
import com.orders.domain.OrderItem;
import com.orders.domain.OrderRepositoryWrapper;
import com.orders.dto.OrderItemDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepositoryWrapper orderRepositoryWrapper;

    public OrderServiceImpl(OrderRepositoryWrapper orderRepositoryWrapper) {
        this.orderRepositoryWrapper = orderRepositoryWrapper;
    }


    @Transactional
    @Override
    public Order saveOrder(String vendorName) {
        var order = Order.createOrder(vendorName, generateOrderNumber(), LocalDate.now());
        return this.orderRepositoryWrapper.saveOrder(order);
    }

    @Override
    public Order addOrderItem(OrderItemDto orderItemDto) {
        var order = this.orderRepositoryWrapper.findOrderById(orderItemDto.getOrderId());
        order.addOrderItem(OrderItem.createOrderItem(orderItemDto));
        return this.orderRepositoryWrapper.saveOrder(order);
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepositoryWrapper.findOrderById(id);
    }

    @Override
    public Order findOrderByOrderNumber(String orderNumber) {
        return this.orderRepositoryWrapper.findOrderByOrderNumber(orderNumber);
    }

    @Override
    public List<Order> findAvailableOrders(String status) {
        return this.orderRepositoryWrapper.findAvailableOrders(status);
    }
}

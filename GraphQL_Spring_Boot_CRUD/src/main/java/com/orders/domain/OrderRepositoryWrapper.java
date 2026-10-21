package com.orders.domain;

import com.orders.exception.OrderNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class OrderRepositoryWrapper {

    private final OrderRepository orderRepository;

    public OrderRepositoryWrapper(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order saveOrder(Order order){
        return this.orderRepository.save(order);
    }

    public Order findOrderById(Long orderId){
        return this.orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public Order findOrderByOrderNumber(String orderNumber){
        return this.orderRepository.findByOrderNumber(orderNumber).orElseThrow(OrderNotFoundException::new);
    }

    public List<Order> findAvailableOrders(String status){
        if (Objects.isNull(status)){
            return this.orderRepository.findAll();
        }
        return this.orderRepository.findByStatus(status);
    }
}

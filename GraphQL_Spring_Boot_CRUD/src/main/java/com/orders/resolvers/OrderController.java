package com.orders.resolvers;

import com.orders.domain.Order;
import com.orders.dto.OrderItemDto;
import com.orders.service.OrderService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @MutationMapping
    public Order createOrder(@Argument String vendorName){
        return this.orderService.saveOrder(vendorName);
    }

    @MutationMapping
    public Order addItemsToAnOrder(@Argument String itemName, @Argument String itemDescription, @Argument int quantity, @Argument BigDecimal unitPrice, @Argument Long orderId){
        var orderItem = new OrderItemDto(itemName, itemDescription, quantity, unitPrice, orderId);
        return this.orderService.addOrderItem(orderItem);
    }

    @MutationMapping
    public Order createOrderWithItems(@Argument String vendorName, @Argument Set<OrderItemDto> orderItems){
        return this.orderService.saveOrderWithItems(vendorName, orderItems);
    }

    @QueryMapping
    public Order findOrderById(@Argument Long orderId){
        return this.orderService.findOrderById(orderId);
    }

    @QueryMapping
    public Order findOrderByOrderNumber(@Argument String orderNumber){
        return this.orderService.findOrderByOrderNumber(orderNumber);
    }

    @QueryMapping
    public List<Order> allAvailableOrders(@Argument String status){
        return this.orderService.findAvailableOrders(status);
    }
}

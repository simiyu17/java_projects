package com.orders.domain;

import com.orders.dto.OrderItemDto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String description;

    private int quantity;

    private BigDecimal unitPrice;

    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    protected OrderItem() {
    }

    private OrderItem(String name, String description, int quantity, BigDecimal unitPrice) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.amount = this.unitPrice.multiply(new BigDecimal(this.quantity)).setScale(2, RoundingMode.HALF_UP);
    }

    public static OrderItem createOrderItem(OrderItemDto orderItemDto){
        return new OrderItem(orderItemDto.getName(), orderItemDto.getItemDescription(), orderItemDto.getQuantity(), orderItemDto.getPrice());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}

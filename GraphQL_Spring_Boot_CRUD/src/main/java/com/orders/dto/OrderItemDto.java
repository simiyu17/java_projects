package com.orders.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderItemDto implements Serializable {

    private Long itemId;

    private String name;

    private String itemDescription;

    private int quantity;

    private BigDecimal price;

    private BigDecimal totalAmount;

    private Long orderId;

    protected OrderItemDto(){}

    public OrderItemDto(String name, String itemDescription, int quantity, BigDecimal price) {
        this.name = name;
        this.itemDescription = itemDescription;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = this.price.multiply(new BigDecimal(this.quantity)).setScale(2, RoundingMode.HALF_UP);
    }

    public OrderItemDto(String name, String itemDescription, int quantity, BigDecimal price, Long orderId) {
        this.name = name;
        this.itemDescription = itemDescription;
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
        this.totalAmount = this.price.multiply(new BigDecimal(this.quantity)).setScale(2, RoundingMode.HALF_UP);
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getOrderId() {
        return orderId;
    }
}

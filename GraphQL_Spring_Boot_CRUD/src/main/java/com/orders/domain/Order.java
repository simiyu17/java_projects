package com.orders.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    public static final String NEW_ORDER = "NEW";
    public static final String SEND_ORDER = "SEND";
    public static final String FULFILLED_ORDER = "FULFILLED";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String orderNumber; // auto-generated

    private LocalDate orderDate; // default to today

    @Column(name = "vendor_name", unique = true)
    private String vendorName;

    private String status;

    @OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>();

    protected Order() {
    }

    private Order(String vendorName, String orderNumber, LocalDate orderDate) {
        this.vendorName = vendorName;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.status = NEW_ORDER;
    }

    public static Order createOrder(String name, String orderNumber, LocalDate orderDate){
        return new Order(name, orderNumber, orderDate);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItem.setOrder(this);
        this.orderItems.add(orderItem);
    }

    public void addOrderItems(List<OrderItem> orderItems){
        orderItems.forEach(this::addOrderItem);
    }

    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getVendorName() {
        return vendorName;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public String getStatus() {
        return status;
    }
}

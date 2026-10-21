package com.orders.resolvers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@GraphQlTest(OrderController.class)
class OrderApiResolverTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void findOrderById() {
    }

    @Test
    void findOrderByOrderNumber() {
    }

    @Test
    void allAvailableOrders() {
    }
}
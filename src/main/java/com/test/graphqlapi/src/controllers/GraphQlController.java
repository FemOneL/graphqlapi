package com.test.graphqlapi.src.controllers;

import com.test.graphqlapi.src.dto.Customer;
import com.test.graphqlapi.src.dto.Order;
import com.test.graphqlapi.src.mappers.OrderMapper;
import com.test.graphqlapi.src.sercices.CustomerService;
import com.test.graphqlapi.src.sercices.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class GraphQlController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final CustomerService customerService;

    @MutationMapping
    public Order createOrder(@Argument String code, @Argument Long customerId, @Argument List<Long> lineItemsIdList) {
        return orderService.createOrder(code, customerId, lineItemsIdList);
    }

    @QueryMapping
    public List<Order> orders() {
        return orderMapper.toOrderList(orderService.findAll());
    }

    @QueryMapping
    public Order orderById(@Argument long id) {
        return orderMapper.toOrder(orderService.findById(id));
    }

    @SchemaMapping
    public Customer customer(Order order) throws Exception {
//        customerService.fetchById(order.customer().id()) - uncomment to retrieve user from db
        return customerService.fetchById(order.customer().id());
    }
}

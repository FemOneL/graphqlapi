package com.test.graphqlapi.src.controllers;

import com.test.graphqlapi.src.dto.Customer;
import com.test.graphqlapi.src.dto.Order;
import com.test.graphqlapi.src.entities.CustomerEntity;
import com.test.graphqlapi.src.mappers.OrderMapper;
import com.test.graphqlapi.src.sercices.OrderService;
import lombok.AllArgsConstructor;
import org.dataloader.DataLoader;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
@AllArgsConstructor
public class GraphQlController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final DataLoader<Long, CustomerEntity> customerDataLoader;

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
    public CompletableFuture<Customer> customer(Order order) {
        long customerId = order.customer().id();

        CompletableFuture<Customer> customer = customerDataLoader.load(customerId).thenApply(orderMapper::toCustomer);

        customerDataLoader.dispatch();

        return customer;
    }
}

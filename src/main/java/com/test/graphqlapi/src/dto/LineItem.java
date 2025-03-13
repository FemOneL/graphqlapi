package com.test.graphqlapi.src.dto;

public record LineItem(
        Long id,
        String name,
        Double totalPrice,
        Long orderId
) {}
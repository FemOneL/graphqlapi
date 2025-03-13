package com.test.graphqlapi.src.dto;

import java.util.List;

public record Order(
        Long id,
        String code,
        Customer customer,
        List<LineItem> lineItems
) {}
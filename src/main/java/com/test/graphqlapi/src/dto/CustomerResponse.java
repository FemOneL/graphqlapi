package com.test.graphqlapi.src.dto;

public record CustomerResponse(Data data) {
    public record Data(Customer customer) {}
}

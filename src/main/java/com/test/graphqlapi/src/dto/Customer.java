package com.test.graphqlapi.src.dto;

import java.time.LocalDate;

public record Customer(
        Long id,
        String name,
        String email,
        LocalDate birthDate
) {}
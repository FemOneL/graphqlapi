package com.test.graphqlapi.src.testdatainitialization;

import com.test.graphqlapi.src.entities.CustomerEntity;
import com.test.graphqlapi.src.entities.LineItemEntity;
import com.test.graphqlapi.src.entities.OrderEntity;
import com.test.graphqlapi.src.sercices.CustomerService;
import com.test.graphqlapi.src.sercices.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CustomerService customerService;
    private final OrderService orderService;

    @Override
    public void run(String... args) throws Exception {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("John Doe");
        customerEntity.setEmail("john.doe@example.com");
        customerEntity.setBirthDate(LocalDate.of(1990, 1, 1));

        LineItemEntity item1 = new LineItemEntity();
        item1.setName("Product A");
        item1.setTotalPrice(100.0);

        LineItemEntity item2 = new LineItemEntity();
        item2.setName("Product B");
        item2.setTotalPrice(200.0);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCode("ORDER123");
        orderEntity.setCustomer(customerEntity);
        orderEntity.setLineItems(Arrays.asList(item1, item2));

        item1.setOrder(orderEntity);
        item2.setOrder(orderEntity);

        customerService.save(customerEntity);
        orderService.save(orderEntity);
    }
}
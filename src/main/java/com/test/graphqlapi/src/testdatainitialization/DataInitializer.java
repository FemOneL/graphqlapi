package com.test.graphqlapi.src.testdatainitialization;

import com.test.graphqlapi.src.entities.Customer;
import com.test.graphqlapi.src.entities.LineItem;
import com.test.graphqlapi.src.entities.Order;
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
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setBirthDate(LocalDate.of(1990, 1, 1));

        LineItem item1 = new LineItem();
        item1.setName("Product A");
        item1.setTotalPrice(100.0);

        LineItem item2 = new LineItem();
        item2.setName("Product B");
        item2.setTotalPrice(200.0);

        Order order = new Order();
        order.setCode("ORDER123");
        order.setCustomer(customer);
        order.setLineItems(Arrays.asList(item1, item2));

        item1.setOrder(order);
        item2.setOrder(order);

        customerService.save(customer);
        orderService.save(order);
    }
}
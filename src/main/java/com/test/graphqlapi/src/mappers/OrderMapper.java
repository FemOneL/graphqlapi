package com.test.graphqlapi.src.mappers;

import com.test.graphqlapi.src.dto.Customer;
import com.test.graphqlapi.src.dto.LineItem;
import com.test.graphqlapi.src.dto.Order;
import com.test.graphqlapi.src.entities.CustomerEntity;
import com.test.graphqlapi.src.entities.LineItemEntity;
import com.test.graphqlapi.src.entities.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderMapper {

    public Order toOrder(OrderEntity orderEntity) {
        return new Order(orderEntity.getId(), orderEntity.getCode(), toCustomer(orderEntity.getCustomer()), toLineItemList(orderEntity.getLineItems()));
    }

    public Customer toCustomer(CustomerEntity customerEntity) {
        return new Customer(customerEntity.getId(), customerEntity.getName(), customerEntity.getEmail(), customerEntity.getBirthDate());
    }

    public LineItem toLineItem(LineItemEntity lineItemEntity) {
        return new LineItem(lineItemEntity.getId(), lineItemEntity.getName(), lineItemEntity.getTotalPrice(), lineItemEntity.getOrder().getId());
    }

    public List<LineItem> toLineItemList(List<LineItemEntity> lineItemEntityList) {
        return lineItemEntityList
                .stream()
                .map(this::toLineItem)
                .toList();
    }

    public List<Order> toOrderList(List<OrderEntity> orderEntityList) {
        return orderEntityList
                .stream()
                .map(this::toOrder)
                .toList();
    }

}

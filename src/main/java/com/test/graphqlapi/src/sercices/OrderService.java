package com.test.graphqlapi.src.sercices;

import com.test.graphqlapi.src.dto.Order;
import com.test.graphqlapi.src.entities.OrderEntity;
import com.test.graphqlapi.src.mappers.OrderMapper;
import com.test.graphqlapi.src.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final LineItemService lineItemService;
    private final OrderMapper orderMapper;

    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }

    public OrderEntity findById(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public OrderEntity save(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    public void deleteById(long id) {
        orderRepository.deleteById(id);
    }

    public Order createOrder(String code, long customerId, List<Long> itemsIdList) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomer(customerService.findById(customerId));
        orderEntity.setCode(code);
        orderEntity.setLineItems(itemsIdList.stream().map(lineItemService::findById).toList());
        return orderMapper.toOrder(save(orderEntity));
    }
}

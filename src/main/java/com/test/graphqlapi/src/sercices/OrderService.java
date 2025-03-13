package com.test.graphqlapi.src.sercices;

import com.test.graphqlapi.src.entities.OrderEntity;
import com.test.graphqlapi.src.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }

    public OrderEntity findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public OrderEntity save(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}

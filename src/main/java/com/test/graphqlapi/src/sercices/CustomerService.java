package com.test.graphqlapi.src.sercices;

import com.test.graphqlapi.src.entities.CustomerEntity;
import com.test.graphqlapi.src.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    public CustomerEntity findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public CustomerEntity save(CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
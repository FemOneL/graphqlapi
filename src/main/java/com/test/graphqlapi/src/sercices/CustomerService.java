package com.test.graphqlapi.src.sercices;

import com.test.graphqlapi.src.dto.Customer;
import com.test.graphqlapi.src.entities.CustomerEntity;
import com.test.graphqlapi.src.fetcher.CustomerDataFetcher;
import com.test.graphqlapi.src.repositories.CustomerRepository;
import graphql.schema.DataFetchingEnvironmentImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private static final String ID = "id";

    private final CustomerRepository customerRepository;
    private final CustomerDataFetcher customerDataFetcher;

    public List<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    public CustomerEntity findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<CustomerEntity> findAllById(Iterable<Long> ids) {
        return customerRepository.findAllById(ids);
    }

    public Customer fetchById(Long id) throws Exception {
        return customerDataFetcher.get(DataFetchingEnvironmentImpl.newDataFetchingEnvironment().arguments(Map.of(ID, id)).build());
    }

    public CustomerEntity save(CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
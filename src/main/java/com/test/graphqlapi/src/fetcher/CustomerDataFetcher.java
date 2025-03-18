package com.test.graphqlapi.src.fetcher;

import com.test.graphqlapi.src.dto.Customer;
import com.test.graphqlapi.src.dto.CustomerResponse;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class CustomerDataFetcher implements DataFetcher<Customer> {

    private final static String REQUEST = """
    {
      "query": "query GetCustomer($id: ID!) { customer(id: $id) { id name email birthDate } }",
      "variables": {
        "id": "%s"
      }
    }
    """;
    private static final String ID = "id";
    private static final String URL = "http://localhost:8081/graphql";

    private final RestTemplate restTemplate;

    @Override
    public Customer get(DataFetchingEnvironment environment) throws Exception {
        long id = environment.getArgument(ID);
        String url = URL;

        String query = REQUEST.formatted(id);

        CustomerResponse response = restTemplate.postForObject(url, query, CustomerResponse.class);

        return response.data().customer();
    }

}

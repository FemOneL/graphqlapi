package com.test.graphqlapi.src.configuration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.test.graphqlapi.src.entities.CustomerEntity;
import com.test.graphqlapi.src.sercices.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Configuration
@Slf4j
public class GraphqlapiConfiguration {
    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer wireMockServer() {
        return new WireMockServer(WireMockConfiguration.options().port(8081));
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public DataLoader<Long, CustomerEntity> customerDataLoader(CustomerService customerService) {
        BatchLoader<Long, CustomerEntity> userBatchLoader = userIds -> CompletableFuture.supplyAsync(() -> {
            log.info("retrieve customers with id's - {}", userIds);
            return customerService.findAllById(userIds);
        });

        return DataLoaderFactory.newDataLoader(userBatchLoader);
    }
}

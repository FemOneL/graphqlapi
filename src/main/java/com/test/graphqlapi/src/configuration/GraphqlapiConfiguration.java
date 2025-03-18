package com.test.graphqlapi.src.configuration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GraphqlapiConfiguration {
    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer wireMockServer() {
        return new WireMockServer(WireMockConfiguration.options().port(8081));
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

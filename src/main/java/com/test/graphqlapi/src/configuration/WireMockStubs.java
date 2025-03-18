package com.test.graphqlapi.src.configuration;


import com.github.tomakehurst.wiremock.WireMockServer;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

@Configuration
@AllArgsConstructor
public class WireMockStubs {

    private final static String MOCKED_REQUEST = """
    {
      "query": "query GetCustomer($id: ID!) { customer(id: $id) { id name email birthDate } }",
      "variables": {
        "id": "1"
      }
    }
    """;
    private static final String MOCKED_RESPONSE = "{\"data\":{\"customer\":{\"id\": 1,\"name\":\"John Fetched\",\"email\":\"john.fetched@example.com\",\"birthDate\":\"1990-01-01\"}}}";
    private static final String GRAPHQL = "/graphql";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";

    private final WireMockServer wireMockServer;

    @PostConstruct
    public void setupStubs() {
        wireMockServer.stubFor(post(urlEqualTo(GRAPHQL))
                .withRequestBody(equalToJson(MOCKED_REQUEST))
                .willReturn(aResponse()
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .withBody(MOCKED_RESPONSE)));
    }

}

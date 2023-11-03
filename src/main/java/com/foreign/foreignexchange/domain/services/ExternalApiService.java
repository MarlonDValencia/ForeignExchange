package com.foreign.foreignexchange.domain.services;

import com.foreign.foreignexchange.domain.generic.Currency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {

    @Value("${external.api.url}")
    private String baseUrl;

    public Currency getCurrency() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(baseUrl, Currency.class);
    }
}

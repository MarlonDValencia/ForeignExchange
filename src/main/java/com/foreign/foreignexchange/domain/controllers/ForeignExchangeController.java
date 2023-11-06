package com.foreign.foreignexchange.domain.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.foreign.foreignexchange.domain.generic.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foreign.foreignexchange.domain.services.ExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/foreign-exchange")
public class ForeignExchangeController {
    @Autowired
    private ExternalApiService externalApiService;

    @GetMapping("findAll")
    public Currency getData(){
        return externalApiService.getCurrency();
    }

    @GetMapping("getSpecificCurrency")
    public Object getSpecificData(@RequestParam String exchange){
        Object currency = getData().data;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.valueToTree(currency);
            JsonNode exchangeObject = jsonNode.get(exchange);
            if (exchangeObject != null) {
                String code = exchangeObject.get("code").asText();
                double value = exchangeObject.get("value").asDouble();
                System.out.println("Código: " + code);
                System.out.println("Valor: " + value);
            } else {
                System.out.println("El objeto no existe en el JSON.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currency;
    }

    @GetMapping("convert")
    public Object convertCurrency(@RequestParam double amount, @RequestParam String firstExchange, @RequestParam String secondExchange) {
        Object currency = getData().data;

        double conversion = 0;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.valueToTree(currency);
            JsonNode exchange1 = jsonNode.get(firstExchange);
            JsonNode exchange2 = jsonNode.get(secondExchange);
            if (exchange1 != null && exchange2 != null) {
                conversion = amount * (exchange2.get("value").asDouble() / exchange1.get("value").asDouble());
                System.out.println("total: " + conversion);
            } else {
                System.out.println("El objeto no existe en el JSON.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conversion;
    }

}

package com.foreign.foreignexchange.domain.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.foreign.foreignexchange.domain.generic.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foreign.foreignexchange.domain.services.ExternalApiService;
import com.foreign.foreignexchange.domain.services.ForeignExchangeKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/foreign-exchange")
@CrossOrigin(origins = "https://foreign-exch-git-aef50d-marlon-david-valencia-pandales-projects.vercel.app/")
public class ForeignExchangeController {
    private String leerContenidoArchivo() {
        StringBuilder contenido = new StringBuilder();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("abbreviations.txt");
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(streamReader)) {

            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Contenido del archivo:");
        System.out.println(contenido.toString());

        return contenido.toString();
    }

    @Autowired
    private ExternalApiService externalApiService;
    private final ForeignExchangeKeyRepository foreignExchangeKeyRepository;

    public ForeignExchangeController(ForeignExchangeKeyRepository foreignExchangeKeyRepository) {
        this.foreignExchangeKeyRepository = foreignExchangeKeyRepository;
    }

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

    @GetMapping("abbreviation")
    public String abbreviations(){
        return leerContenidoArchivo();
    }
}

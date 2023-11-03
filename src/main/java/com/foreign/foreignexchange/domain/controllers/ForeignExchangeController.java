package com.foreign.foreignexchange.domain.controllers;

import com.foreign.foreignexchange.domain.generic.Currency;
import com.foreign.foreignexchange.domain.services.ExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/foreign-exchange")
public class ForeignExchangeController {
    @Autowired
    private ExternalApiService externalApiService;

    @GetMapping("")
    public Currency obtenerData(){
        return externalApiService.getCurrency();
    }
}

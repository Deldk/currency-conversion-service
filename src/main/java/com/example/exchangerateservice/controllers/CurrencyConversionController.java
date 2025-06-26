package com.example.exchangerateservice.controllers;

import com.example.exchangerateservice.dto.CurrencyConversionRequest;
import com.example.exchangerateservice.dto.CurrencyConversionResponse;
import com.example.exchangerateservice.services.CurrencyConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
//@RequiredArgsConstructor
public class CurrencyConversionController {

    private final CurrencyConversionService currencyConversionService;

    public CurrencyConversionController(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
    }

    /**
     *
     * @param from
     * @param to
     * @param amount
     * @return
     */
    @GetMapping("/convert")
    public CurrencyConversionResponse convertCurrency(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam Double amount
    ){
        CurrencyConversionRequest request = new CurrencyConversionRequest(from, to, amount);
        return currencyConversionService.convertCurrency(request);
    }
}

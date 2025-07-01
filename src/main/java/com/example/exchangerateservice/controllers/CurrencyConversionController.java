package com.example.exchangerateservice.controllers;

import com.example.exchangerateservice.dto.CurrencyConversionHistoryResponse;
import com.example.exchangerateservice.dto.CurrencyConversionRequest;
import com.example.exchangerateservice.dto.CurrencyConversionResponse;
import com.example.exchangerateservice.entities.CurrencyConversion;
import com.example.exchangerateservice.services.CurrencyConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/history")
    public Page<CurrencyConversionHistoryResponse> getConversionHistory(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ){

        System.out.println("from " + from + " to " + to + " page " + page + " size " + size + " date " + date);
        Pageable pageable = PageRequest.of(page, size);
        return currencyConversionService.getFilteredHistory(from, to, date, pageable);
    }
}

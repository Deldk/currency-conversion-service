package com.example.exchangerateservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class CurrencyConversionRequest {
    private String from;
    private String to;
    private Double amount;

    public CurrencyConversionRequest(String from, String to, Double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public Double getAmount() {
        return amount;
    }
}



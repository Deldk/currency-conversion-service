package com.example.exchangerateservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class CurrencyConversionResponse {
    private String to;
    private String from;
    private Double amount;
    private Double rate;
    private Double conversion;
    private LocalDateTime timestamp;

    public CurrencyConversionResponse(String from, String to, Double amount, Double rate, Double conversion, LocalDateTime timestamp) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.rate = rate;
        this.conversion = conversion;
        this.timestamp = timestamp;
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

    public Double getRate() {
        return rate;
    }

    public Double getConversion() {
        return conversion;
    }
//
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

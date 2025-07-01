package com.example.exchangerateservice.dto;

import java.time.LocalDateTime;

public class CurrencyConversionHistoryResponse {
    private String from;
    private String to;
    private Double amount;
    private Double rate;
    private Double conversion;
    private LocalDateTime timestamp;

    public CurrencyConversionHistoryResponse(String from, String to, Double amount,
                                             Double rate, Double conversion, LocalDateTime timestamp) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.rate = rate;
        this.conversion = conversion;
        this.timestamp = timestamp;
    }

    public String getFrom() {
        return from;
    }

    public String getTo(){
        return to;
    }

    public Double getAmount(){
        return amount;
    }

    public Double getRate(){
        return rate;
    }

    public Double getConversion(){
        return conversion;
    }

    public LocalDateTime getTimestamp(){
        return timestamp;
    }
}

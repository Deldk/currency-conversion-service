package com.example.exchangerateservice.dto;

import lombok.Data;

@Data
public class Info {
    private long timestamp;
    private double quote;
    public double getQuote() {
        return quote;
    }
}

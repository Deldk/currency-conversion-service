package com.example.exchangerateservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExchangeRateApiResponse {
    private boolean success;
    private Query query;
    private Info info;
    private double result;

    public boolean isSuccess() {
        return success;
    }

    public Query getQuery() {
        return query;
    }

    public Info getInfo() {
        return info;
    }

    public double getResult() {
        return result;
    }
}

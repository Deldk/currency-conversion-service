package com.example.exchangerateservice.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="currency_conversions")
public class CurrencyConversion {
    @Id
    @GeneratedValue(generator = "uuid2") // use uuid2 generator
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    private String fromCurrency;
    private String toCurrency;
    private Double amount;
    private Double rate;
    private Double conversion;
    private LocalDateTime timestamp;

    public CurrencyConversion(){

    }

    public CurrencyConversion(String fromCurrency, String toCurrency, Double amount, Double rate, Double conversion, LocalDateTime timestamp){
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
        this.rate = rate;
        this.conversion = conversion;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

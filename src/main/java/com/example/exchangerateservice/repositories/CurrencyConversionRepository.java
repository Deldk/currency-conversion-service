package com.example.exchangerateservice.repositories;

import com.example.exchangerateservice.entities.CurrencyConversion;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface CurrencyConversionRepository extends JpaRepository<CurrencyConversion, UUID> {

    Page<CurrencyConversion> findByFromCurrencyAndToCurrencyAndTimestampBetween(String from, String to, LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<CurrencyConversion> findByFromCurrencyAndToCurrency(String from, String to, Pageable pageable);
}

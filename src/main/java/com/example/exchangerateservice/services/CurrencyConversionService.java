package com.example.exchangerateservice.services;

import com.example.exchangerateservice.dto.CurrencyConversionHistoryResponse;
import com.example.exchangerateservice.dto.CurrencyConversionRequest;
import com.example.exchangerateservice.dto.CurrencyConversionResponse;
import com.example.exchangerateservice.dto.ExchangeRateApiResponse;
import com.example.exchangerateservice.entities.CurrencyConversion;
import com.example.exchangerateservice.repositories.CurrencyConversionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class CurrencyConversionService {

    private final RestClient restClient;
    private final CurrencyConversionRepository conversionRepository;

    @Value("${exchange.api.key:}")
    private String apiKey;

    public CurrencyConversionService(RestClient restClient, CurrencyConversionRepository conversionRepository) {
        this.restClient = restClient;
        this.conversionRepository = conversionRepository;
    }

    /**
     * This method handles the conversion of the amount provided from one currency to the other
     * @param request
     * @return CureencyConversionResponse
     */
    public CurrencyConversionResponse convertCurrency(CurrencyConversionRequest request){
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/convert")
                .queryParam("from", request.getFrom())
                .queryParam("to", request.getTo())
                .queryParam("amount", request.getAmount());

        // Will be useful if a free exchange rate api is found that doesn't require a key (not likely)
        if (!apiKey.isBlank()){
            builder.queryParam("access_key", apiKey);
        }

        String url = builder.toUriString();

        // Making request to the external exchange rate api using base url + built url in rentclient
        ExchangeRateApiResponse apiResponse = restClient.get()
                .uri(url)
                .retrieve()
                .body(ExchangeRateApiResponse.class);

        // Sanity checks ;)
        System.out.println("Raw API:: " + apiResponse);
        System.out.println("Quote:: " + apiResponse.getResult());
        System.out.println("Result:: " + apiResponse.getInfo().getQuote());

        if (apiResponse == null || !apiResponse.isSuccess()){
            throw new RuntimeException("Failed to retrieve exchange rate data");
        }

        CurrencyConversion conversion = new CurrencyConversion(
                request.getFrom(),
                request.getTo(),
                request.getAmount(),
                apiResponse.getResult(),
                apiResponse.getInfo().getQuote(),
                LocalDateTime.now()
        );
        conversionRepository.save(conversion);
        
        return new CurrencyConversionResponse(
                request.getFrom(),
                request.getTo(),
                request.getAmount(),
                apiResponse.getResult(),
                apiResponse.getInfo().getQuote(),
                LocalDateTime.now()
        );
    }

    public List<CurrencyConversion> getAllConversions(){
        return conversionRepository.findAll();
    }

    public Page<CurrencyConversionHistoryResponse> getFilteredHistory(String from, String to, LocalDateTime date, Pageable pageable){
        Page<CurrencyConversion> results;

        if (from != null && to != null && date != null){
            results = conversionRepository.findByFromCurrencyAndToCurrencyAndTimestampBetween(from, to, date.toLocalDate().atStartOfDay(), date.toLocalDate().atTime(23,59,59), pageable);
        } else if(from != null && to != null){
            results = conversionRepository.findByFromCurrencyAndToCurrency(from, to, pageable);
        } else {
            results = conversionRepository.findAll(pageable);
        }

        return results.map(c -> new CurrencyConversionHistoryResponse(
                c.getFromCurrency(),
                c.getToCurrency(),
                c.getAmount(),
                c.getRate(),
                c.getConversion(),
                c.getTimestamp()
        ));
    }
}

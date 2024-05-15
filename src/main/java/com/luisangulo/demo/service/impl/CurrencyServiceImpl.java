package com.luisangulo.demo.service.impl;


import com.luisangulo.demo.entity.Currency;
import com.luisangulo.demo.repository.CurrencyRepository;
import com.luisangulo.demo.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public Flux<Currency> listAllCurrency() {
        return Flux.fromIterable(currencyRepository.findAll());
    }
}

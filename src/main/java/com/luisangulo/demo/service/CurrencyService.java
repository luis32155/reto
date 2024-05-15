package com.luisangulo.demo.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.luisangulo.demo.entity.Currency;

import java.util.List;

public interface CurrencyService {
    Flux<Currency> listAllCurrency();
}

package com.luisangulo.demo.service;


import com.luisangulo.demo.dto.ReqExchange;
import com.luisangulo.demo.dto.ReqExchangeUpdate;
import com.luisangulo.demo.dto.ResExchange;
import com.luisangulo.demo.entity.Exchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExchangeService {
    Flux<Exchange> listAllExchange();

    Flux<Exchange> listExchangeById(Long exchangeId);

    Mono<ResExchange> convertAmountExchange(ReqExchange exchange);

    Exchange updateRateExchange(Long exchangeId, ReqExchangeUpdate data);
}

package com.luisangulo.demo.controller;


import com.luisangulo.demo.dto.ReqExchange;
import com.luisangulo.demo.dto.ReqExchangeUpdate;
import com.luisangulo.demo.dto.ResExchange;
import com.luisangulo.demo.entity.Exchange;
import com.luisangulo.demo.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
@RequestMapping(value = "/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Flux<Exchange>> listExchangeById(@RequestParam(name = "exchangeFrom", required = false) Long exchangeFrom){
        if(exchangeFrom!=null){
            Flux<Exchange> exchange = exchangeService.listExchangeById(exchangeFrom);

            return ResponseEntity.ok(exchange);
        }else{
            Flux<Exchange> exchange = exchangeService.listAllExchange();

            return ResponseEntity.ok(exchange);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<ResponseEntity<ResExchange>> convertExchange(@RequestBody ReqExchange data) {
        return exchangeService.convertAmountExchange(data)
                .map(resExchange -> ResponseEntity.ok(resExchange));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<ResponseEntity<?>> updateRateExchange(@PathVariable Long id, @RequestBody ReqExchangeUpdate data) {
        return Mono.fromSupplier(() -> exchangeService.updateRateExchange(id, data))
                .map(updatedExchange -> {
                    if (updatedExchange == null) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(updatedExchange);
                });
    }
}

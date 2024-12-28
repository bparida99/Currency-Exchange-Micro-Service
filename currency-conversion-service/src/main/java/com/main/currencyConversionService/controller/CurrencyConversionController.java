package com.main.currencyConversionService.controller;

import com.main.currencyConversionService.entity.CurrencyConversion;
import com.main.currencyConversionService.proxy.CurrencyExchangeProxy;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@Slf4j
public class CurrencyConversionController {
    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;
    /*
      Retry will hit the service and return the fallback if exception occur
      But the circuit-breaker will stop the service call for certain time period
      and then after that will allow few requests to hit the service and if that fails
      it will again stop the service call else it will allow the service call
     */
    @GetMapping("retrieveTotalExchangeValue/{from}/{to}/{quantity}")
    //@Retry(name="currency-exchange",fallbackMethod = "calculateCurrencyConversionFallBack")
    @CircuitBreaker(name="currency-exchange",fallbackMethod = "calculateCurrencyConversionFallBack")
    @Bulkhead(name="currency-exchange",fallbackMethod = "calculateCurrencyConversionBulkHeadFallBack")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
                                                         @PathVariable String to,
                                                         @PathVariable BigDecimal quantity ) throws InterruptedException {
        log.info("Entering calculateCurrencyConversion(){}");
      CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from,to);
      return new CurrencyConversion
              (currencyConversion.getId(),currencyConversion.getFrom(),
                      currencyConversion.getTo(), currencyConversion.getConversionMultiple(),
                      quantity,quantity.multiply(currencyConversion.getConversionMultiple()));
    }

    public CurrencyConversion calculateCurrencyConversionFallBack(Exception ex){
        return new CurrencyConversion(1l,"","",
                BigDecimal.valueOf(0),BigDecimal.valueOf(0),BigDecimal.valueOf(0));
    }

    public CurrencyConversion calculateCurrencyConversionBulkHeadFallBack(Exception ex){
        return new CurrencyConversion();
    }
}

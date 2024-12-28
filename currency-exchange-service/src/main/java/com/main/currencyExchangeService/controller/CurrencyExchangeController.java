package com.main.currencyExchangeService.controller;

import com.main.currencyExchangeService.dao.CurrencyExchangeDao;
import com.main.currencyExchangeService.entity.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeDao currencyExchangeDao;

    @GetMapping("retrieveExchangeValue/{from}/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from,
                                                  @PathVariable String to){
      return currencyExchangeDao.findByFromAndTo(from.toUpperCase(),to.toUpperCase());
    }
}

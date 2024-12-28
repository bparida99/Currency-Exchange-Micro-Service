package com.main.limitservice.controller;

import com.main.limitservice.config.LimitConfig.LimitConfiguration;
import com.main.limitservice.entity.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {
    @Autowired
    private LimitConfiguration limitConfiguration;

    @GetMapping("/retrieveLimits")
    public Limit retrieveLimits(){
       return new Limit(limitConfiguration.getMinimum(),limitConfiguration.getMaximum());
    }
}

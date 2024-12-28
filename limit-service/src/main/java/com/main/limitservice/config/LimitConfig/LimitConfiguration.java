package com.main.limitservice.config.LimitConfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("limit-service")
@Configuration
@Getter
@Setter
public class LimitConfiguration {
    private int minimum;
    private int maximum;
}

package com.main.currencyExchangeService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrencyExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "Currency_From")
    private String from;
    @Column(name = "Currency_To")
    private String to;
    private BigDecimal conversionMultiple;

}

package com.project.stockMonitor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quote {
    private String name;
    private BigDecimal ask;
    private BigDecimal bid;
    private BigDecimal price;
    private BigDecimal previousClose;
}

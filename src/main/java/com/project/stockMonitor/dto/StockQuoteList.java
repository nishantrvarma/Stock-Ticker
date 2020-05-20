package com.project.stockMonitor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockQuoteList {
    private Instant currentTime;
    private ArrayList<Quote> stockQuoteList;
}

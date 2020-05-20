package com.project.stockMonitor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockWatchList {
    private HashSet<String> stocks;
}

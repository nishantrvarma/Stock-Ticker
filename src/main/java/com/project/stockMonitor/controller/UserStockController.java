package com.project.stockMonitor.controller;

import com.project.stockMonitor.dto.StockQuoteList;
import com.project.stockMonitor.dto.StockWatchList;
import com.project.stockMonitor.service.UserStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/stock")
public class UserStockController {

    @Autowired
    UserStockService userStockService;

    @PostMapping(value = "/add", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addToWatch(@RequestBody StockWatchList stockWatchList) throws IOException {
        userStockService.addToWatch(stockWatchList.getStocks());
    }

    @GetMapping(value = "/list", produces = "application/json")
    public StockWatchList getAllWatched() throws IOException {
        return userStockService.getAllWatched();
    }

    @PostMapping(value = "/remove", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void removeFromWatched(@RequestBody StockWatchList stockWatchList) throws IOException {
        userStockService.removeFromWatched(stockWatchList.getStocks());
    }

    @GetMapping(value = "/quote", produces = "application/json")
    public StockQuoteList getQuotes() throws IOException {
        return userStockService.getQuotes();
    }

}

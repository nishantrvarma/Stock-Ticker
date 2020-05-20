package com.project.stockMonitor.service;

import com.project.stockMonitor.dto.Quote;
import com.project.stockMonitor.dto.StockQuoteList;
import com.project.stockMonitor.dto.StockWatchList;
import com.project.stockMonitor.entity.UserStockData;
import com.project.stockMonitor.repository.UserStockDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

@Service
public class UserStockService {

    @Autowired
    UserStockDataRepository userStockDataRepository;

    @Autowired
    private SimpMessagingTemplate template;


    /*
    //Add set of stocks to be watched
    //Param : Set<String> stockList
    //Returns: void
    //
    */
    public void addToWatch(Set<String> stockList) throws IOException {
        String[] stockArray = new String[stockList.size()];
        Map<String, Stock> validStockMap = YahooFinance.get(stockList.toArray(stockArray));
        if(validStockMap.isEmpty()){
            return;
        }
        if(userStockDataRepository.existsByName("Default User")){
            UserStockData watchedStocks = userStockDataRepository.findByName("Default User");
            StockWatchList updatedStockWatchList = convertToStockWatchList(watchedStocks);
            updatedStockWatchList.getStocks().addAll(validStockMap.keySet());
            watchedStocks.setStockList(updatedStockWatchList.getStocks());
            userStockDataRepository.save(watchedStocks);
        } else {
            userStockDataRepository.save(new UserStockData("Default User", new HashSet<>(validStockMap.keySet())));
        }
    }

    /*
    //get set of stocks being watched
    //Param : null
    //Returns: StockWatchList
    //
    */
    public StockWatchList getAllWatched() throws IOException {
        UserStockData watchedStocks = userStockDataRepository.findByName("Default User");
        return convertToStockWatchList(watchedStocks);
    }

    /*
    //Remove from set of stocks to be watched
    //Param : Set<String> stockList
    //Returns: void
    //
    */
    public void removeFromWatched(Set<String> stockList) throws IOException {
        UserStockData watchedStocks = userStockDataRepository.findByName("Default User");
        StockWatchList updatedStockWatchList = convertToStockWatchList(watchedStocks);
        updatedStockWatchList.getStocks().removeAll(stockList);
        watchedStocks.setStockList(updatedStockWatchList.getStocks());
        userStockDataRepository.save(watchedStocks);
    }

    /*
    //get quotes for stocks being watched
    //Param : null
    //Returns: StockQuoteList
    //
    */
    public StockQuoteList getQuotes() throws IOException {
        UserStockData watchedStocks = userStockDataRepository.findByName("Default User");
        String[] stockArray = new String[watchedStocks.getStockList().size()];
        Map<String, Stock> validStockMap = YahooFinance.get(watchedStocks.getStockList().toArray(stockArray));
        return convertToStockQuoteList(validStockMap);
    }

    /*
    //Pushes updated quotes for stocks being watched to websocket
    //Param : null
    //Returns: void
    //
    */
    @Scheduled(fixedRate = 5000)
    public void getScheduledQuotes() throws IOException {
        if(!userStockDataRepository.existsByName("Default User")){
            return;
        }
        UserStockData watchedStocks = userStockDataRepository.findByName("Default User");
        String[] stockArray = new String[watchedStocks.getStockList().size()];
        Map<String, Stock> validStockMap = YahooFinance.get(watchedStocks.getStockList().toArray(stockArray));
        StockQuoteList stockQuoteList = convertToStockQuoteList(validStockMap);
        this.template.convertAndSend("/channel/quotes", stockQuoteList);
    }

    private StockQuoteList convertToStockQuoteList(Map<String, Stock> validStockMap){
        StockQuoteList stockQuoteList = new StockQuoteList(Instant.now(), new ArrayList<>());
        for(String symbol : validStockMap.keySet()){
            StockQuote currentQuote = validStockMap.get(symbol).getQuote();
            Quote quote = convertToQuote(currentQuote);
            stockQuoteList.getQuoteList().add(quote);
        }
        return stockQuoteList;
    }

    private Quote convertToQuote(StockQuote stockQuote){
        Quote quote = new Quote(stockQuote.getSymbol(),stockQuote.getAsk(), stockQuote.getBid(), stockQuote.getPrice(), stockQuote.getPreviousClose());
        return quote;
    }

    private StockWatchList convertToStockWatchList(UserStockData userStockData){
        StockWatchList stockWatchList = new StockWatchList(new HashSet<>());
        stockWatchList.getStocks().addAll(userStockData.getStockList());
        return stockWatchList;
    }

}

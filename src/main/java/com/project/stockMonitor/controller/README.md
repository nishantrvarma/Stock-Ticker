# API-DOCS

## Add stocks to watch-list
```
Name: addToWatch
REST type : POST
Function: Adds set of stocks passed as payload to watch-list.
Path : localhost:8080/stock/add
Request Body : JSON with set of stocks. 
        Example :- {
                    "stocks" : ["GOOG"]
                   }
Expected Response : 200 OK
```

## Get stocks from watch-list
```
Name: getAllWatched
REST type : GET
Function: Gets list of stocks in watch-list.
Path : localhost:8080/stock/list
Request Body : None
Expected Response : Json with set of Stocks. Example :- {"stocks" : ["GOOG"]}
```

## Remove stocks from watch-list
```
Name: removeFromWatched
REST type : POST
Function: Removes set of stocks passed as payload from watch-list.
Path : localhost:8080/stock/remove
Request Body : JSON with set of stocks. 
        Example :- {
                    "stocks" : ["GOOG"]
                   }
Expected Response : 200 OK
```

## Get quotes for stocks in watch-list
```
Name: getQuotes
REST type : GET
Function: Gets Quotes for set of stocks in watch-list.
Path : localhost:8080/stock/quote
Request Body : None
Expected Response : Json with Quote of Stocks in watchlist.
         Example :- {
                    "currentTime": "2020-05-20T23:52:25.761830700Z",
                    "quoteList": [
                        {
                            "name": "GOOG",
                            "ask": 1410.49,
                            "bid": 1406.82,
                            "price": 1406.72,
                            "previousClose": 1373.485
                        }
                    ]
                    }
```


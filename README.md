# Stock-Ticker
Web application to display stock quotes in real time
This is the backend implementation of a web application built to serve real time stock quote requests. The Yahoo Finance API is used to retrieve stock quotes. The user can add quotes to watch and remove quotes from the watch list. Quotes are only retrieved for stocks currently being watched. 

## APIs provided
Endpoints are provided to add stocks to be watched, remove stocks from being watched, retrieve all stocks currently watched, and retrieve quotes for all stocks currently being watched. 

## Websocket layer
A websocket is implemented that pushes real time quote updates for all stocks currently being watched. To begin pushing quotes to the websocket, user needs to add at least 1 stock to the watchlist. Clients can subscribe to the websocket to recieve updated quotes. Quotes are pinged from the Yahoo Finance API every few seconds which is customizable. 

## Steps to run :
Pull the repository into your local branch and execute the following
**mvn spring-boot:run**
which initializes and runs the spring web server
Use postman to test the API endpoints. 

## Extentions and further comments:
1. The Yahoo Finance API seems to be a deprecated resource and has limited capabilities. Ideally being provided a complete list of stock tickers that are maintained in the central repository can help handle exception scenarios and edge cases more organically. 
2. Compared to pinging the API every few static seconds, being provided a publisher to subscribe to would be the preferred alternative as the stock server can push updates to the web application whenever there is a change in the ticker quote which can optimize the whole process.
3. Quote data has not been persisted locally since functionality is limited and data is readily available in the Yahoo Finance servers. However persisting data locally provides a number of advantages. Local data can be referenced instead of pinging the server which can reduce latency. Also data can be aggregated based on user requirements and customizations. 
4. Unit tests are very important and can be written in JUnit. 
5. Further functionality can be implemented based on requirements. User authentication is an important feature which requires persistence of user credentials that has been passed over here for brevity. 
6. API authentication is a very critical business requirement that is usually focused on with scale of use. 


# Stock-Ticker
Web application to display stock quotes in real time
This is the backend implementation of a web application built to serve real time stock quote requests. The Yahoo Finance API is used to retrieve stock quotes. The user can add quotes to watch and remove quotes from the watch list. Quotes are only retrieved for stocks currently being watched. Data persistence is achieved be using an H2 database and Hibernate to access records. The stomp protocol is used along with Websockets to faciliate message passing. 

## APIs provided
Endpoints are provided to add stocks to be watched, remove stocks from being watched, retrieve all stocks currently watched, and retrieve quotes for all stocks currently being watched. 

## Websocket layer
A websocket is implemented that pushes real time quote updates for all stocks currently being watched. To begin pushing quotes to the websocket, user needs to add at least 1 stock to the watchlist. Clients can subscribe to the websocket to recieve updated quotes. Quotes are pinged from the Yahoo Finance API every few seconds which is customizable. 

## Steps to run :
Pull the repository into your local branch and execute the following
**mvn spring-boot:run**
which initializes and runs the spring web server
Use postman to test the API endpoints. 

##Comments:
1. The Yahoo Finance API seems to be a deprecated resource and has limited capabilities. Ideally being provided a complete list of stock tickers that are maintained in the central repository can help handle exception scenarios and edge cases more organically. 
2. Compared to polling the API, being provided a publisher to subscribe to would be the preferred alternative as the stock server can push updates to the web application whenever there is a change in the ticker quote which can optimize the whole process.
3. Quote data has not been persisted locally.A production version of this would definitely involve a persistence layer on the server as this would scale better and reduce overhead and latency.
##Further steps:
1. Unit tests for production deployment can be written in JUnit. 
2. User authentication is an important feature which requires persistence of user credentials. 
3. API authentication is a very critical business requirement. 

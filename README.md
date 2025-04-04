# Project-OOP
A Java-based Stock Market Trading Simulation where users can buy, sell, and track stocks in a dynamic market. 
Class Structure Outline
Stock
  Represents an individual stock in the market.
  Store details like the stock symbol, company name, current price, and price history.
  Provides methods to update the price and retrieve historical data.
Trader
  Represents a player in the simulation who can buy and sell stocks.
  Manages a portfolio of owned stocks and tracks available balance.
  Includes functions to execute trades and view the portfolio.
Market
  Manages all available stocks and simulates price fluctuations.
  Ensures a limited number of stocks and updates their values periodically.
  Allows traders to access stock information and execute trades.
Transaction
  Represents a trade action (buy/sell) between a trader and the market.
  Stores details such as trader identity, stock, quantity, price, and timestamp.
  Ensures transactions are recorded for history tracking.
User
  Provides a common structure for different types of users (e.g., traders, brokers).
  Can be extended for additional user roles in the simulation.
StockMarketSimulation 
  Defines key behaviors such as updating stock prices and processing trades.
  Implemented by the Market class to ensure consistent operations.
Exception Handling
  Custom exceptions like InsufficientFundsException and StockNotAvailableException
  Helps prevent invalid transactions and enforce simulation rules.  

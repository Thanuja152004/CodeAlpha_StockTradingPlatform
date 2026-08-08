package com.codealpha.stocktrading;
import java.util.Random;
import java.util.ArrayList;

public class Market {

    private ArrayList<Stock> stocks;
    private Random random = new Random();
    // Constructor
    public Market() {

        stocks = new ArrayList<>();

        // Default Stocks
        stocks.add(new Stock("AAPL", "Apple Inc.", 180.50));
        stocks.add(new Stock("GOOGL", "Google", 2850.75));
        stocks.add(new Stock("MSFT", "Microsoft", 410.20));
        stocks.add(new Stock("TSLA", "Tesla", 250.80));
        stocks.add(new Stock("AMZN", "Amazon", 145.30));

    }

    // Display Market
    public void displayMarket() {

        System.out.println("\n========== STOCK MARKET ==========");

        System.out.printf("%-10s %-20s %-10s%n",
                "SYMBOL",
                "COMPANY",
                "PRICE");

        System.out.println("----------------------------------------------");

        for (Stock stock : stocks) {

            stock.displayStock();

        }

        System.out.println("==============================================");
    }
    
    public void updateStockPrices() {

        for (Stock stock : stocks) {

            double change = (random.nextDouble() * 20) - 10;

            double newPrice = stock.getPrice() + change;

            if (newPrice < 50) {
                newPrice = 50;
            }

            stock.setPrice(newPrice);

        }

    }

    // Search Stock
    public Stock searchStock(String symbol) {

        for (Stock stock : stocks) {

            if (stock.getSymbol().equalsIgnoreCase(symbol)) {

                return stock;

            }

        }

        return null;

    }

    // Get All Stocks
    public ArrayList<Stock> getStocks() {

        return stocks;

    }

}
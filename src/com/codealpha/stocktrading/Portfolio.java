package com.codealpha.stocktrading;

import java.util.HashMap;

public class Portfolio {

    private double balance;
    private final double initialBalance;
    private HashMap<String, Integer> ownedStocks;

    // Constructor
    public Portfolio(double balance) {

        this.balance = balance;
        this.initialBalance = balance;
        this.ownedStocks = new HashMap<>();

    }

    // Get Balance
    public double getBalance() {
        return balance;
    }

    // Update Balance
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public double getInitialBalance() {
        return initialBalance;
    }

    // Get Owned Stocks
    public HashMap<String, Integer> getOwnedStocks() {
        return ownedStocks;
    }

    // Buy Stock
    public void buyStock(String symbol, int quantity) {

        if (ownedStocks.containsKey(symbol)) {
            ownedStocks.put(symbol, ownedStocks.get(symbol) + quantity);
        } else {
            ownedStocks.put(symbol, quantity);
        }

    }

    // Sell Stock
    public boolean sellStock(String symbol, int quantity) {

        if (!ownedStocks.containsKey(symbol)) {
            return false;
        }

        int currentQuantity = ownedStocks.get(symbol);

        if (quantity > currentQuantity) {
            return false;
        }

        currentQuantity -= quantity;

        if (currentQuantity == 0) {
            ownedStocks.remove(symbol);
        } else {
            ownedStocks.put(symbol, currentQuantity);
        }

        return true;
    }

    // Display Portfolio
    public void displayPortfolio() {

        System.out.println("\n========== YOUR PORTFOLIO ==========");

        System.out.printf("Available Balance : ₹%.2f%n", balance);

        System.out.println("\nOwned Stocks:");

        if (ownedStocks.isEmpty()) {

            System.out.println("No stocks purchased.");

        } else {

            for (String symbol : ownedStocks.keySet()) {

                System.out.println(symbol + " : " + ownedStocks.get(symbol) + " shares");

            }

        }

        System.out.println("====================================");
    }
    
 // Calculate Portfolio Value
    public double calculatePortfolioValue(Market market) {

        double total = 0;

        for (String symbol : ownedStocks.keySet()) {

            Stock stock = market.searchStock(symbol);

            if (stock != null) {

                total += stock.getPrice() * ownedStocks.get(symbol);

            }

        }

        return total;
    }

}
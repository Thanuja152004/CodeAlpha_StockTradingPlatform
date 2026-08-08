package com.codealpha.stocktrading;

public class Stock {

    private String symbol;
    private String companyName;
    private double price;

    // Constructor
    public Stock(String symbol, String companyName, double price) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.price = price;
    }

    // Getters
    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getPrice() {
        return price;
    }

    // Setter
    public void setPrice(double price) {
        this.price = price;
    }

    // Display Stock Details
    public void displayStock() {
        System.out.printf("%-10s %-20s ₹%.2f%n",
                symbol,
                companyName,
                price);
    }
}
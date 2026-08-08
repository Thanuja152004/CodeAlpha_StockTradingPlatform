package com.codealpha.stocktrading;

public class Transaction {

    private String type;
    private String symbol;
    private int quantity;
    private double totalAmount;

    // Constructor
    public Transaction(String type, String symbol, int quantity, double totalAmount) {

        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.totalAmount = totalAmount;

    }

    // Getters
    public String getType() {
        return type;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    // Display Transaction
    public void displayTransaction() {

        System.out.printf("%-8s %-10s %-10d ₹%.2f%n",
                type,
                symbol,
                quantity,
                totalAmount);

    }

}
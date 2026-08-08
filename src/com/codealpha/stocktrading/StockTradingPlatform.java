package com.codealpha.stocktrading;

import java.util.ArrayList;
import java.util.Scanner;

public class StockTradingPlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Market market = new Market();
        FileManager fileManager = new FileManager();

        Portfolio portfolio = fileManager.loadPortfolio();

        ArrayList<Transaction> transactions = new ArrayList<>();

        int choice;

        do {

            System.out.println("\n===========================================");
            System.out.println("      STOCK TRADING PLATFORM");
            System.out.println("===========================================");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transaction History");
            System.out.println("6. Save Portfolio");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input!");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

            case 1:

                market.updateStockPrices();

                market.displayMarket();

                break;

                case 2:

                    market.displayMarket();

                    System.out.print("\nEnter Stock Symbol: ");
                    String buySymbol = sc.nextLine();

                    Stock buyStock = market.searchStock(buySymbol);

                    if (buyStock == null) {

                        System.out.println("Stock not found.");
                        break;

                    }

                    System.out.print("Enter Quantity: ");
                    int buyQuantity = sc.nextInt();

                    double totalCost = buyQuantity * buyStock.getPrice();

                    if (portfolio.getBalance() < totalCost) {

                        System.out.println("Insufficient Balance.");

                    } else {

                        portfolio.buyStock(buyStock.getSymbol(), buyQuantity);

                        portfolio.setBalance(
                                portfolio.getBalance() - totalCost);

                        transactions.add(
                                new Transaction(
                                        "BUY",
                                        buyStock.getSymbol(),
                                        buyQuantity,
                                        totalCost));

                        System.out.println("Stock purchased successfully.");

                    }

                    break;
                case 3:

                    market.displayMarket();

                    System.out.print("\nEnter Stock Symbol: ");
                    String sellSymbol = sc.nextLine();

                    Stock sellStock = market.searchStock(sellSymbol);

                    if (sellStock == null) {

                        System.out.println("Stock not found.");
                        break;

                    }

                    System.out.print("Enter Quantity: ");
                    int sellQuantity = sc.nextInt();

                    double totalAmount = sellQuantity * sellStock.getPrice();

                    if (portfolio.sellStock(sellStock.getSymbol(), sellQuantity)) {

                        portfolio.setBalance(
                                portfolio.getBalance() + totalAmount);

                        transactions.add(
                                new Transaction(
                                        "SELL",
                                        sellStock.getSymbol(),
                                        sellQuantity,
                                        totalAmount));

                        System.out.println("Stock sold successfully.");

                    } else {

                        System.out.println("Not enough shares to sell.");

                    }

                    break;

                case 4:

                    portfolio.displayPortfolio();

                    // Calculate Portfolio Value
                    double portfolioValue = portfolio.calculatePortfolioValue(market);

                    // Calculate Total Assets
                    double totalAssets = portfolio.getBalance() + portfolioValue;

                    // Calculate Profit or Loss
                    double profitLoss = totalAssets - portfolio.getInitialBalance();

                    System.out.printf("Portfolio Value : ₹%.2f%n", portfolioValue);

                    System.out.printf("Total Assets : ₹%.2f%n", totalAssets);

                    if (profitLoss >= 0) {

                        System.out.printf("Profit : ₹%.2f%n", profitLoss);

                    } else {

                        System.out.printf("Loss : ₹%.2f%n", Math.abs(profitLoss));

                    }

                    break;
                case 5:

                    System.out.println("\n========== TRANSACTION HISTORY ==========");

                    if (transactions.isEmpty()) {

                        System.out.println("No transactions found.");

                    } else {

                        System.out.printf("%-8s %-10s %-10s %s%n",
                                "TYPE",
                                "SYMBOL",
                                "QUANTITY",
                                "AMOUNT");

                        System.out.println("-----------------------------------------------");

                        for (Transaction transaction : transactions) {

                            transaction.displayTransaction();

                        }

                    }

                    break;

                case 6:

                    fileManager.savePortfolio(portfolio);

                    break;

                case 7:

                    fileManager.savePortfolio(portfolio);

                    System.out.println("\nThank you for using Stock Trading Platform!");

                    break;

                default:

                    System.out.println("Invalid Choice!");

            }

        } while (choice != 7);

        sc.close();

    }

}
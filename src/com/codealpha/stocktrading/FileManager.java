package com.codealpha.stocktrading;

import java.io.*;
import java.util.HashMap;

public class FileManager {

    private static final String FILE_NAME = "portfolio.txt";

    // Save Portfolio
    public void savePortfolio(Portfolio portfolio) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            writer.write(String.valueOf(portfolio.getBalance()));
            writer.newLine();

            HashMap<String, Integer> stocks = portfolio.getOwnedStocks();

            for (String symbol : stocks.keySet()) {

                writer.write(symbol + "," + stocks.get(symbol));
                writer.newLine();

            }

            System.out.println("Portfolio saved successfully.");

        } catch (IOException e) {

            System.out.println("Error saving portfolio.");

        }

    }

    // Load Portfolio
    public Portfolio loadPortfolio() {

        Portfolio portfolio = new Portfolio(100000);

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return portfolio;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            line = reader.readLine();

            if (line != null) {
                portfolio.setBalance(Double.parseDouble(line));
            }

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                portfolio.buyStock(data[0], Integer.parseInt(data[1]));

            }

        } catch (IOException e) {

            System.out.println("Error loading portfolio.");

        }

        return portfolio;

    }

}
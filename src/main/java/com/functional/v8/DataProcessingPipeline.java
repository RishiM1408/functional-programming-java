package com.functional.v8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Demonstrates the core Functional Programming features introduced in Java 8:
 * Streams, Lambdas, Optional, and standard Functional Interfaces.
 *
 * This class simulates a data processing pipeline for a list of transactions.
 */
public class DataProcessingPipeline {

    /**
     * A simple transaction record (immutable data carrier).
     * In Java 14+, this would be a 'record', but for Java 8 simulation, we use a
     * class.
     */
    public static class Transaction {
        private final String id;
        private final double amount;
        private final String currency;

        public Transaction(String id, double amount, String currency) {
            this.id = id;
            this.amount = amount;
            this.currency = currency;
        }

        public String getId() {
            return id;
        }

        public double getAmount() {
            return amount;
        }

        public String getCurrency() {
            return currency;
        }

        @Override
        public String toString() {
            return "Transaction{id='" + id + "', amount=" + amount + ", currency='" + currency + "'}";
        }
    }

    /**
     * Processes a list of transactions to calculate the total amount for a specific
     * currency.
     * Demonstrates: filter, map, reduce.
     *
     * @param transactions   List of transactions
     * @param targetCurrency The currency to filter by
     * @return Total amount in that currency
     */
    public double calculateTotalInCurrency(List<Transaction> transactions, String targetCurrency) {
        return transactions.stream()
                .filter(t -> t.getCurrency().equals(targetCurrency))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    /**
     * Groups transactions by currency.
     * Demonstrates: Collectors.groupingBy.
     *
     * @param transactions List of transactions
     * @return Map of currency to list of transactions
     */
    public Map<String, List<Transaction>> groupTransactionsByCurrency(List<Transaction> transactions) {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency));
    }

    /**
     * Finds a high-value transaction safely using Optional.
     * Demonstrates: Optional, findFirst.
     *
     * @param transactions List of transactions
     * @param threshold    Minimum amount to consider "high-value"
     * @return Optional containing the transaction if found, empty otherwise
     */
    public Optional<Transaction> findHighValueTransaction(List<Transaction> transactions, double threshold) {
        return transactions.stream()
                .filter(t -> t.getAmount() > threshold)
                .findFirst();
    }
}

package com.functional.v21;

/**
 * Demonstrates features from Java 21:
 * Record Patterns, Nested Patterns, and Pattern Matching for switch with guards
 * (when).
 *
 * This example processes complex nested transaction structures.
 */
public class TransactionMatcher {

    public record User(String name, int age) {
    }

    public record Location(String city, String country) {
    }

    public record Metadata(User user, Location location) {
    }

    // A Transaction contains metadata and an amount
    public record Transaction(Metadata metadata, double amount) {
    }

    /**
     * Analyzes a transaction using Java 21 Record Patterns.
     * It deconstructs the nested records directly in the switch cases.
     *
     * @param transaction The transaction to analyze
     * @return A descriptive analysis string
     */
    public String analyzeTransaction(Object transaction) {
        return switch (transaction) {
            // Nested Record Pattern: Deconstructing Transaction -> Metadata -> Location
            case Transaction(Metadata(User u, Location(var city, var country)), var amount) when "US".equals(country)
                    && amount > 1000 ->
                "High-value US transaction from " + city + " by " + u.name();

            case Transaction(Metadata(User u, Location(var city, var country)), var amount) when "US".equals(country) ->
                "Standard US transaction from " + city + " by " + u.name();

            case Transaction(Metadata(User u, Location loc), var amount) when u.age() < 18 ->
                "Blocked: Minor " + u.name() + " attempted transaction from " + loc.country();

            case Transaction(Metadata(User u, Location loc), var amount) ->
                "International transaction from " + loc.country() + " by " + u.name();

            case null -> "Invalid transaction: null";

            default -> "Unknown transaction type";
        };
    }
}

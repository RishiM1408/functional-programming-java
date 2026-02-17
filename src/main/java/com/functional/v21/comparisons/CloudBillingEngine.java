package com.functional.v21.comparisons;

/**
 * JEP 440: Record Patterns & JEP 441: Pattern Matching for switch (Java 21)
 *
 * Comparisons:
 * 1. Legacy: Deep getter chains ("Getter Hell"), defensive null checks.
 * 2. Modern: Data-Oriented Programming (DOP) with Record Patterns and Guards.
 */
public class CloudBillingEngine {

    // --- DATA MODEL (Records) ---
    public record Identity(String region, String tier) {
    }

    public record Resource(String type, Identity identity) {
    }

    public record Usage(Resource resource, double quantity) {
    }

    // --- LEGACY APPROACH: Getter Hell ---
    /**
     * Calculates cost using imperative logic.
     * High cognitive load due to null checks and deep accessors.
     */
    public double calculateCostLegacy(Usage usage) {
        if (usage == null)
            return 0.0;

        Resource res = usage.resource();
        if (res == null)
            return 0.0;

        Identity id = res.identity();
        if (id == null)
            return 0.0;

        double baseRate = 0.10; // Default per unit

        // Complex conditional logic
        if ("PREMIUM".equals(id.tier())) {
            baseRate = 0.20;
        }

        if ("EU".equals(id.region())) {
            baseRate += 0.05; // VAT-like tax
        }

        return usage.quantity() * baseRate;
    }

    // --- MODERN APPROACH: Data-Oriented Programming ---
    /**
     * Calculates cost using declarative pattern matching.
     * The shape of the data drives the logic.
     */
    public double calculateCostModern(Usage usage) {
        return switch (usage) {
            // Case 1: Premium user in EU -> 0.20 + 0.05 = 0.25
            case Usage(Resource(var type, Identity(var region, var tier)), var qty) when "EU".equals(region)
                    && "PREMIUM".equals(tier) ->
                qty * 0.25;

            // Case 2: Premium user elsewhere -> 0.20
            case Usage(Resource(var type, Identity(var region, var tier)), var qty) when "PREMIUM".equals(tier) ->
                qty * 0.20;

            // Case 3: Standard user in EU -> 0.10 + 0.05 = 0.15
            case Usage(Resource(var type, Identity(var region, var tier)), var qty) when "EU".equals(region) ->
                qty * 0.15;

            // Case 4: Default -> 0.10
            case Usage(Resource(var type, Identity(var region, var tier)), var qty) ->
                qty * 0.10;

            case null -> 0.0;
        };
    }
}

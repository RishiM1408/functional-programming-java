package com.functional.v17;

/**
 * Demonstrates features from Java 17 (and predecessors like 14, 15, 16):
 * Sealed Classes, Records, and Pattern Matching for switch.
 *
 * This example models a Payment System where the payment method hierarchy is
 * restricted.
 */
public class PaymentSystem {

    /**
     * A sealed interface defines a closed set of permitted implementations.
     * This allows the compiler to enforce exhaustiveness in switch expressions.
     */
    public sealed interface PaymentMethod permits CreditCard, PayPal, Crypto {
    }

    /**
     * Records (Java 14+) are immutable data carriers with built-in equals, limit,
     * toString.
     */
    public record CreditCard(String cardNumber, String expiry) implements PaymentMethod {
    }

    public record PayPal(String email) implements PaymentMethod {
    }

    public record Crypto(String walletAddress, String coinType) implements PaymentMethod {
    }

    /**
     * Processes a payment using Pattern Matching for switch (Preview in 17,
     * Standard in 21, but
     * type patterns were introduced earlier).
     * 
     * Note: In strict Java 17, pattern matching for switch was a Preview feature.
     * Since we are running with --source 21, we can use the full standard syntax.
     * 
     * @param method The payment method
     * @param amount The amount to charge
     * @return A status string
     */
    public String processPayment(PaymentMethod method, double amount) {
        return switch (method) {
            case CreditCard c ->
                "Charging " + amount + " to Credit Card ending in " + c.cardNumber.substring(c.cardNumber.length() - 4);
            case PayPal p -> "Processing PayPal payment for " + amount + " to " + p.email;
            case Crypto c -> "Transferring " + amount + " " + c.coinType + " to " + c.walletAddress;
            // No default needed because the interface is sealed and all permits are
            // covered!
        };
    }
}

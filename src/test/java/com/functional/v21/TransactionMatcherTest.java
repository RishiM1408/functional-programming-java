package com.functional.v21;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class TransactionMatcherTest {

    private final TransactionMatcher matcher = new TransactionMatcher();

    @Test
    void shouldMatchHighValueUSTransaction() {
        TransactionMatcher.User user = new TransactionMatcher.User("Alice", 30);
        TransactionMatcher.Location loc = new TransactionMatcher.Location("New York", "US");
        TransactionMatcher.Metadata meta = new TransactionMatcher.Metadata(user, loc);
        TransactionMatcher.Transaction tx = new TransactionMatcher.Transaction(meta, 1500.0);

        String result = matcher.analyzeTransaction(tx);
        assertThat(result).isEqualTo("High-value US transaction from New York by Alice");
    }

    @Test
    void shouldMatchStandardUSTransaction() {
        TransactionMatcher.User user = new TransactionMatcher.User("Bob", 25);
        TransactionMatcher.Location loc = new TransactionMatcher.Location("San Francisco", "US");
        TransactionMatcher.Metadata meta = new TransactionMatcher.Metadata(user, loc);
        TransactionMatcher.Transaction tx = new TransactionMatcher.Transaction(meta, 50.0);

        String result = matcher.analyzeTransaction(tx);
        assertThat(result).isEqualTo("Standard US transaction from San Francisco by Bob");
    }

    @Test
    void shouldBlockMinorTransaction() {
        TransactionMatcher.User user = new TransactionMatcher.User("Charlie", 16);
        TransactionMatcher.Location loc = new TransactionMatcher.Location("London", "UK");
        TransactionMatcher.Metadata meta = new TransactionMatcher.Metadata(user, loc);
        TransactionMatcher.Transaction tx = new TransactionMatcher.Transaction(meta, 100.0);

        String result = matcher.analyzeTransaction(tx);
        assertThat(result).contains("Blocked: Minor Charlie");
    }

    @Test
    void shouldMatchInternationalTransaction() {
        TransactionMatcher.User user = new TransactionMatcher.User("Diana", 28);
        TransactionMatcher.Location loc = new TransactionMatcher.Location("Berlin", "Germany");
        TransactionMatcher.Metadata meta = new TransactionMatcher.Metadata(user, loc);
        TransactionMatcher.Transaction tx = new TransactionMatcher.Transaction(meta, 200.0);

        String result = matcher.analyzeTransaction(tx);
        assertThat(result).contains("International transaction from Germany by Diana");
    }
}

package com.functional.v8;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class DataProcessingPipelineTest {

    private final DataProcessingPipeline pipeline = new DataProcessingPipeline();
    private final List<DataProcessingPipeline.Transaction> transactions = Arrays.asList(
            new DataProcessingPipeline.Transaction("1", 100.0, "USD"),
            new DataProcessingPipeline.Transaction("2", 150.0, "EUR"),
            new DataProcessingPipeline.Transaction("3", 200.0, "USD"),
            new DataProcessingPipeline.Transaction("4", 50.0, "GBP"),
            new DataProcessingPipeline.Transaction("5", 300.0, "USD"));

    @Test
    void shouldCalculateTotalInCurrency() {
        double totalUSD = pipeline.calculateTotalInCurrency(transactions, "USD");
        assertThat(totalUSD).isEqualTo(600.0);
    }

    @Test
    void shouldGroupTransactionsByCurrency() {
        Map<String, List<DataProcessingPipeline.Transaction>> grouped = pipeline
                .groupTransactionsByCurrency(transactions);

        assertThat(grouped).containsKeys("USD", "EUR", "GBP");
        assertThat(grouped.get("USD")).hasSize(3);
        assertThat(grouped.get("EUR")).hasSize(1);
    }

    @Test
    void shouldFindHighValueTransaction() {
        Optional<DataProcessingPipeline.Transaction> highValue = pipeline.findHighValueTransaction(transactions, 250.0);

        assertThat(highValue).isPresent();
        assertThat(highValue.get().getAmount()).isEqualTo(300.0);
    }

    @Test
    void shouldReturnEmptyForMissingHighValueTransaction() {
        Optional<DataProcessingPipeline.Transaction> highValue = pipeline.findHighValueTransaction(transactions,
                1000.0);

        assertThat(highValue).isEmpty();
    }
}

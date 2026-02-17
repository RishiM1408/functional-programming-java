package com.functional.v21.comparisons;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CloudBillingEngineTest {

    private final CloudBillingEngine engine = new CloudBillingEngine();

    @Test
    void shouldCalculateCostsIdentically() {
        // Scenarios
        var premiumEU = new CloudBillingEngine.Usage(
                new CloudBillingEngine.Resource("VM", new CloudBillingEngine.Identity("EU", "PREMIUM")), 100);

        var premiumUS = new CloudBillingEngine.Usage(
                new CloudBillingEngine.Resource("DB", new CloudBillingEngine.Identity("US", "PREMIUM")), 100);

        var standardEU = new CloudBillingEngine.Usage(
                new CloudBillingEngine.Resource("S3", new CloudBillingEngine.Identity("EU", "STANDARD")), 100);

        var standardUS = new CloudBillingEngine.Usage(
                new CloudBillingEngine.Resource("FaaS", new CloudBillingEngine.Identity("US", "STANDARD")), 100);

        var nullUsage = (CloudBillingEngine.Usage) null;

        // Verify matches using Assertion helpers
        assertThat(engine.calculateCostLegacy(premiumEU)).isEqualTo(25.0); // 0.25 * 100
        assertThat(engine.calculateCostModern(premiumEU)).isEqualTo(25.0);

        assertThat(engine.calculateCostLegacy(premiumUS)).isEqualTo(20.0); // 0.20 * 100
        assertThat(engine.calculateCostModern(premiumUS)).isEqualTo(20.0);

        assertThat(engine.calculateCostLegacy(standardEU)).isEqualTo(15.0); // 0.15 * 100
        assertThat(engine.calculateCostModern(standardEU)).isEqualTo(15.0);

        assertThat(engine.calculateCostLegacy(standardUS)).isEqualTo(10.0); // 0.10 * 100
        assertThat(engine.calculateCostModern(standardUS)).isEqualTo(10.0);

        assertThat(engine.calculateCostLegacy(nullUsage)).isEqualTo(0.0);
        assertThat(engine.calculateCostModern(nullUsage)).isEqualTo(0.0);
    }
}

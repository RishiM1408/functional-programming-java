package com.functional.v17;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentSystemTest {

    private final PaymentSystem system = new PaymentSystem();

    @Test
    void shouldProcessCreditCard() {
        PaymentSystem.CreditCard cc = new PaymentSystem.CreditCard("1234567890123456", "12/25");
        String result = system.processPayment(cc, 100.0);
        assertThat(result).contains("Credit Card ending in 3456");
    }

    @Test
    void shouldProcessPayPal() {
        PaymentSystem.PayPal pp = new PaymentSystem.PayPal("user@example.com");
        String result = system.processPayment(pp, 50.0);
        assertThat(result).contains("PayPal payment for 50.0 to user@example.com");
    }

    @Test
    void shouldProcessCrypto() {
        PaymentSystem.Crypto crypto = new PaymentSystem.Crypto("0x123abc", "ETH");
        String result = system.processPayment(crypto, 2.5);
        assertThat(result).contains("Transferring 2.5 ETH to 0x123abc");
    }
}

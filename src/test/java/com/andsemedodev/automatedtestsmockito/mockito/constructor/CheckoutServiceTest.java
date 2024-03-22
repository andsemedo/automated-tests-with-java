package com.andsemedodev.automatedtestsmockito.mockito.constructor;

import com.andsemedodev.automatedtestsmockito.service.CheckoutService;
import com.andsemedodev.automatedtestsmockito.service.PaymentProcessor;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CheckoutServiceTest {

    @Test
    void testMockObjectConstruction() {
        try(
                MockedConstruction<PaymentProcessor> mocked = mockConstruction(PaymentProcessor.class,
                (mock, context) -> {
                    when(mock.chargeCustomer(anyString(), any(BigDecimal.class)))
                            .thenReturn(BigDecimal.TEN);
                }
                )) {
            CheckoutService service = new CheckoutService();

            // When
            BigDecimal result = service.purchaseProduct("Mackbook Pro", "32");

            // Then
            assertEquals(BigDecimal.TEN, result);
            assertEquals(1, mocked.constructed().size());

        }
    }

}

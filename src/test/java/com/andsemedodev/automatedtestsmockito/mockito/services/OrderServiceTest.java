package com.andsemedodev.automatedtestsmockito.mockito.services;

import com.andsemedodev.automatedtestsmockito.model.Order;
import com.andsemedodev.automatedtestsmockito.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private final OrderService service = new OrderService();
    private final Object defaultUUID = UUID.fromString("8d8b30e3-de52-4f1c-a71c-9905a8043dac");
    private final LocalDateTime defaultLocalDateTime = LocalDateTime.of(2024,3,20,9,54);

    @DisplayName("Should Include Random Order Id When No Order Id Exists")
    @Test
    void testShouldIncludeRandomOrderId_When_NoOrderIdExists() {
        // Given / Arrange
        try(MockedStatic<UUID> mockedUUID = mockStatic(UUID.class)) {
            mockedUUID.when(UUID::randomUUID).thenReturn(defaultUUID);

            // When / Act
            Order order = service.createOrder("Mackbook Pro", 150000L, null);

            // Then / Assert
            assertEquals(defaultUUID.toString(), order.getId());

        }

    }

    @DisplayName("Should Include Current Time When Create A New Order")
    @Test
    void testShouldIncludeCurrentTime_When_CreateANewOrder() {
        // Given / Arrange
        try(MockedStatic<LocalDateTime> mockedUUID = mockStatic(LocalDateTime.class)) {
            mockedUUID.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);

            // When / Act
            Order order = service.createOrder("Mackbook Pro", 150000L, null);

            // Then / Assert
            assertEquals(defaultLocalDateTime, order.getCreationDate());

        }

    }
}

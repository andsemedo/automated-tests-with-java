package com.andsemedodev.automatedtestsmockito.bussiness;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ListMockWithBDDTest {

    @Test
    void testMockingWithBDDList_When_SizeIsCalled_ShouldReturn10() {
        // Given / Arrange
        List<?> list = mock(List.class);
        given(list.size()).willReturn(10);

        // When / Act && Then / Assert
        assertThat(list.size(), is(10));
        assertThat(list.size(), is(10));
        assertThat(list.size(), is(10));

    }

    @Test
    void testMockingWithBDDList_When_SizeIsCalled_ShouldReturnMultipleValues() {
        // Given / Arrange
        List<?> list = mock(List.class);
        given(list.size()).willReturn(10).willReturn(20);

        // When / Act && Then / Assert
        assertThat(list.size(), is(10));
        assertThat(list.size(), is(20));
        assertThat(list.size(), is(20));

    }

    @Test
    void testMockingWithBDDList_When_GetIsCalled_ShouldReturnErudio() {
        // Given / Arrange
        var list = mock(List.class);
        given(list.get(0)).willReturn("Erudio");

        // When / Act && Then / Assert
        assertThat(list.get(0), is("Erudio"));
        assertNull(list.get(1));

    }

    @Test
    void testMockingList_When_GetIsCalledWithArgumentMatcher_ShouldReturnErudio() {
        // Given / Arrange
        var list = mock(List.class);
        given(list.get(anyInt())).willReturn("Erudio");

        // When / Act && Then / Assert
        assertThat(list.get(anyInt()), is("Erudio"));
        assertThat(list.get(0), is("Erudio"));
    }

    @Test
    void testMockingList_When_ThrowsAnException() {
        // Given / Arrange
        var list = mock(List.class);
        given(list.get(anyInt())).willThrow(new  RuntimeException("Foo Bar"));

        // When / Act && Then / Assert
        assertThrows(RuntimeException.class, () -> {
            // When / Act
            list.get(anyInt());
        }, () -> "Should have thown an runtime exception");

    }
}

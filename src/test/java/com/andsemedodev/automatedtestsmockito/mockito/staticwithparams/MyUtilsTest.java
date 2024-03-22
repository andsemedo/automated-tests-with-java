package com.andsemedodev.automatedtestsmockito.mockito.staticwithparams;

import com.andsemedodev.automatedtestsmockito.staticwithparams.MyUtils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MyUtilsTest {

    @Test
    void testShouldMockStaticMethodWithParams() {
        try(MockedStatic<MyUtils> mockedStatic = mockStatic(MyUtils.class)) {
            mockedStatic.when(
                    () -> MyUtils.getWelcomeMessage(
                            eq("Erudio"),
                            anyBoolean()
                    )
            ).thenReturn("Howdy Erudio");

            String result = MyUtils.getWelcomeMessage("Erudio", false);

            assertEquals("Howdy Erudio", result);

        }
    }

}

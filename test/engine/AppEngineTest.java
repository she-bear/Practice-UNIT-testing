package ru.gb.test.hw.avgcompare.engine;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.mockito.Mockito.*;

class AppEngineTest {

  @SuppressWarnings("unchecked")
  void testRunCall(double firstAvg, double secondAvg, String expectedResult) {
    final var firstValues = List.of(firstAvg);
    final var secondValues = List.of(secondAvg);

    var mockValuesProvider = mock(Supplier.class);
    when(mockValuesProvider.get())
            .thenReturn(firstValues)
            .thenReturn(secondValues);

    var mockAvgCalculator = mock(Function.class);
    when(mockAvgCalculator.apply(firstValues)).thenReturn(firstAvg);
    when(mockAvgCalculator.apply(secondValues)).thenReturn(secondAvg);

    var mockResultPrinter = mock(Consumer.class);

    var objectUnderTest = new AppEngine(mockValuesProvider, mockAvgCalculator, mockResultPrinter);
    objectUnderTest.run();

    verify(mockValuesProvider, times(2)).get();
    if (firstAvg == secondAvg) {
      verify(mockAvgCalculator, times(2)).apply(firstValues);
    } else {
      verify(mockAvgCalculator, times(1)).apply(firstValues);
      verify(mockAvgCalculator, times(1)).apply(secondValues);
    }
    verify(mockResultPrinter, times(1)).accept(expectedResult);
  }

  @Test
  void runWhenFirstAvgIsLessThanSecondAvg() {
    testRunCall(0, 1, "The second list has a higher average valuе");
  }

  @Test
  void runWhenFirstAvgIsGreaterThanSecondAvg() {
    testRunCall(1, 0, "The first list has a higher average value");
  }

  @Test
  void runWhenFirstAvgIsEqualToSecondAvg() {
    testRunCall(1, 1, "The average values are the same");
  }
}

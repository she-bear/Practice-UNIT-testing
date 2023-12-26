package ru.gb.test.hw.avgcompare.engine.impl;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ValuesInputTest {

  @Test
  void stringToValuesReturnsEmptyListForNull() {
    assertTrue(ValuesInput.stringToValues(null).isEmpty());
  }

  @Test
  void stringToValuesReturnsEmptyListForBlankString() {
    assertTrue(ValuesInput.stringToValues("").isEmpty());
    assertTrue(ValuesInput.stringToValues(" \t \n \r").isEmpty());
  }

  @Test
  void stringToValuesReadsSingleItemList() {
    assertIterableEquals(
            List.of(12345.6789),
            ValuesInput.stringToValues("12345.6789")
    );
  }

  @Test
  void stringToValuesReadsMultiItemList() {
    assertIterableEquals(
            List.of(1., 2., 3.4, .56, 789.),
            ValuesInput.stringToValues("    1, 2.,3.4,.56,\t  789")
    );
  }

  @Test
  @SuppressWarnings("unchecked")
  void get() {
    final String rawString = "1.,2.3,.45";
    final List<Double> expected = List.of(1., 2.3, .45);

    var promptPrinter = mock(Consumer.class);
    var stringReader = mock(Supplier.class);
    when(stringReader.get()).thenReturn(rawString);

    ValuesInput objectUnderTest = new ValuesInput(promptPrinter, stringReader);
    List<Double> actual = objectUnderTest.get();

    assertIterableEquals(expected, actual);
    verify(promptPrinter, times(1)).accept("Enter a list of numbers separated by commas (Enter - end of input):");
    verify(stringReader, times(1)).get();
  }
}

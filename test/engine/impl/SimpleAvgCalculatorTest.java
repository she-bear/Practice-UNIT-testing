package ru.gb.test.hw.avgcompare.engine.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SimpleAvgCalculatorTest {
  private SimpleAvgCalculator objectUnderTest;

  @BeforeEach
  void beforeEach() {
    objectUnderTest = new SimpleAvgCalculator();
  }

  @Test
  void applyThrowsExceptionForEmptyList() {
    var exception = assertThrows(IllegalArgumentException.class, () -> objectUnderTest.apply(List.of()));
    assertEquals("The list is empty", exception.getMessage());
  }

  @Test
  void applyForSingleItemList() {
    final double expected = 12345.6789;
    assertEquals(expected, objectUnderTest.apply(List.of(expected)));
  }

  @Test
  void applyForMultiItemsList() {
    assertEquals(2222.2, objectUnderTest.apply(List.of(1., 10., 100., 1000., 10000.)));
  }
}

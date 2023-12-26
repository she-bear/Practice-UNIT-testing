package ru.gb.test.hw.avgcompare;

import org.junit.jupiter.api.Test;
import ru.gb.test.hw.avgcompare.engine.AppEngine;
import ru.gb.test.hw.avgcompare.engine.impl.SimpleAvgCalculator;
import ru.gb.test.hw.avgcompare.engine.impl.ValuesInput;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

class AppTest {

  @Test
  void mainConstructsSingleAppEngineAndCallsRunOnce() {
    try (var mockAppEngineConstruction = mockConstruction(
            AppEngine.class,
            (mock, context) -> {
              var valuesProvider = context.arguments().get(0);
              var avgCalculator = context.arguments().get(1);

              assertInstanceOf(ValuesInput.class, valuesProvider);
              assertInstanceOf(SimpleAvgCalculator.class, avgCalculator);
            })) {
      App.main(null);

      assertEquals(1, mockAppEngineConstruction.constructed().size());
      var mockAppEngine = mockAppEngineConstruction.constructed().get(0);

      verify(mockAppEngine, times(1)).run();
    }
  }
}
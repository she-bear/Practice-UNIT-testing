package ru.gb.test.hw.avgcompare.engine;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class AppEngine {

  private final Supplier<List<Double>> valuesProvider;

  private final Function<List<Double>, Double> avgCalculator;

  private final Consumer<String> resultPrinter;

  public AppEngine(
          Supplier<List<Double>> valuesProvider,
          Function<List<Double>, Double> avgCalculator,
          Consumer<String> resultPrinter
  ) {
    this.valuesProvider = valuesProvider;
    this.avgCalculator = avgCalculator;
    this.resultPrinter = resultPrinter;
  }

  public void run() {
    double firstCalculated = avgCalculator.apply(valuesProvider.get());
    double secondCalculated = avgCalculator.apply(valuesProvider.get());

    if (firstCalculated < secondCalculated) {
      resultPrinter.accept("The second list has a higher average value");
      return;
    }

    if (firstCalculated > secondCalculated) {
      resultPrinter.accept("The first list has a higher average value");
      return;
    }

    resultPrinter.accept("The average values are the same");
  }
}

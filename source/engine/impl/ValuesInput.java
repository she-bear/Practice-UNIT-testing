package ru.gb.test.hw.avgcompare.engine.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ValuesInput implements Supplier<List<Double>> {
  private final Consumer<String> promptPrinter;

  private final Supplier<String> stringReader;

  public ValuesInput(Consumer<String> promptPrinter, Supplier<String> stringReader) {
    this.promptPrinter = promptPrinter;
    this.stringReader = stringReader;
  }

  public static List<Double> stringToValues(String from) {
    if (from == null || from.isBlank()) {
      return List.of();
    }

    String[] items = from.split(",");

    ArrayList<Double> result = new ArrayList<>();
    for (String item : items) {
      result.add(Double.parseDouble(item));
    }
    return result;
  }

  @Override
  public List<Double> get() {
    promptPrinter.accept("Enter a list of numbers separated by commas (Enter - end of input):");

    return stringToValues(stringReader.get());
  }
}

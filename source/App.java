package ru.gb.test.hw.avgcompare;

import java.util.Scanner;
import ru.gb.test.hw.avgcompare.engine.AppEngine;
import ru.gb.test.hw.avgcompare.engine.impl.SimpleAvgCalculator;
import ru.gb.test.hw.avgcompare.engine.impl.ValuesInput;

public class App {

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      new AppEngine(
              new ValuesInput(System.out::println, scanner::nextLine),
              new SimpleAvgCalculator(),
              System.out::println
      ).run();
    }
  }
}

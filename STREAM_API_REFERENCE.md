# Stream API Reference Guide

This document lists the Stream API methods demonstrated in this repository, categorized by operation type.

## 1. Intermediate Operations

These operations transform a stream into another stream. They are lazy and only execute when a terminal operation is invoked.

| Method                              | Description                              | Example Usage                                                                                                                                 |
| :---------------------------------- | :--------------------------------------- | :-------------------------------------------------------------------------------------------------------------------------------------------- |
| **`filter(Predicate)`**             | Selects elements that match a condition. | [StudentGradingSystem.java](file:///e:/functional-programming-java/src/main/java/com/functional/v8/comparisons/StudentGradingSystem.java#L87) |
| **`map(Function)`**                 | Transforms each element.                 | (Implicit in pipelines)                                                                                                                       |
| **`mapToDouble(ToDoubleFunction)`** | Transforms elements to a `DoubleStream`. | [DataProcessingPipeline.java](file:///e:/functional-programming-java/src/main/java/com/functional/v8/DataProcessingPipeline.java#L63)         |
| **`mapToLong(ToLongFunction)`**     | Transforms elements to a `LongStream`.   | [FileSystem.java](file:///e:/functional-programming-java/src/main/java/com/functional/v17/comparisons/FileSystem.java#L100)                   |
| **`sorted(Comparator)`**            | Sorts the stream.                        | [StudentGradingSystem.java](file:///e:/functional-programming-java/src/main/java/com/functional/v8/comparisons/StudentGradingSystem.java#L90) |
| **`limit(long)`**                   | Truncates the stream to a specific size. | [StudentGradingSystem.java](file:///e:/functional-programming-java/src/main/java/com/functional/v8/comparisons/StudentGradingSystem.java#L91) |

## 2. Terminal Operations

These operations produce a result or side-effect and close the stream.

| Method                   | Description                                         | Example Usage                                                                                                                         |
| :----------------------- | :-------------------------------------------------- | :------------------------------------------------------------------------------------------------------------------------------------ |
| **`collect(Collector)`** | Accumulates elements into a collection.             | [DataProcessingPipeline.java](file:///e:/functional-programming-java/src/main/java/com/functional/v8/DataProcessingPipeline.java#L76) |
| **`sum()`**              | Calculates the sum of a numeric stream.             | [DataProcessingPipeline.java](file:///e:/functional-programming-java/src/main/java/com/functional/v8/DataProcessingPipeline.java#L64) |
| **`findFirst()`**        | Returns an `Optional` describing the first element. | [DataProcessingPipeline.java](file:///e:/functional-programming-java/src/main/java/com/functional/v8/DataProcessingPipeline.java#L90) |

## 3. Collectors

Used with `collect()` to accumulate results.

| Collector                  | Description                                   | Example Usage                                                                                                                                 |
| :------------------------- | :-------------------------------------------- | :-------------------------------------------------------------------------------------------------------------------------------------------- |
| **`groupingBy(Function)`** | Groups elements by a classification function. | [DataProcessingPipeline.java](file:///e:/functional-programming-java/src/main/java/com/functional/v8/DataProcessingPipeline.java#L76)         |
| **`toList()`**             | Accumulates elements into a `List`.           | [StudentGradingSystem.java](file:///e:/functional-programming-java/src/main/java/com/functional/v8/comparisons/StudentGradingSystem.java#L92) |

## 4. Sources

How streams are created in this project.

| Source                    | Description                                     | Example Usage                                                                                                                         |
| :------------------------ | :---------------------------------------------- | :------------------------------------------------------------------------------------------------------------------------------------ |
| **`Collection.stream()`** | Creates a stream from a Collection (List, Set). | [DataProcessingPipeline.java](file:///e:/functional-programming-java/src/main/java/com/functional/v8/DataProcessingPipeline.java#L61) |

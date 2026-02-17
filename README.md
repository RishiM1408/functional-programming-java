# Modern Java Functional Programming

A comprehensive, deep-dive educational resource for mastering Functional Programming in Java. This repository tracks the transition from the introduction of Lambdas in Java 8 to the advanced Data-Oriented Programming (DOP) paradigms in Java 21.

## Project Overview

This project is designed to demonstrate **how** and **why** Java has evolved to support functional programming patterns. Each package represents a major milestone in Java's history, focusing on specific features that enable cleaner, more robust, and more expressive code.

### 📚 Structure

The codebase is organized by Java version:

- **`com.functional.v8`**: The Foundation (Streams, Lambdas, Optional)
- **`com.functional.v17`**: distinct Data Modeling (Sealed Classes, Records)
- **`com.functional.v21`**: Advanced Pattern Matching (Record Patterns, Switch Expressions)

---

## 🆚 Side-by-Side Comparisons

We have implemented direct comparisons between "Legacy" (Imperative) and "Modern" (Functional) styles for key features.

### 1. Java 8: Stream API vs. Loops

**Location**: `src/main/java/com/functional/v8/comparisons/StudentGradingSystem.java`

- **Scenario**: Find top 3 students in a department with GPA > 3.5.
- **Legacy**: Uses `for` loops, mutable `ArrayList`, and explicit `Collections.sort`.
- **Modern**: Uses `stream()`, `filter()`, `sorted()`, and `limit()`.
- **Advanced**: Demonstrates handling checked exceptions inside Lambdas using a wrapper.

```java
// Legacy
for (Student s : students) {
    if (s.getGpa() > 3.5) filtered.add(s);
}
// Modern
students.stream().filter(s -> s.getGpa() > 3.5)...
```

### 2. Java 17: Sealed ADTs vs. Abstract Classes

**Location**: `src/main/java/com/functional/v17/comparisons/FileSystem.java`

- **Scenario**: Calculate total size of a file system tree.
- **Legacy**: Abstract `FileNode` with manual `instanceof` checks and casting.
- **Modern**: Sealed `Node` interface with `record` implementations. Uses pattern matching for switch (Preview in 17) to eliminate casting and ensure type safety.

```java
// Modern
return switch (node) {
    case File f -> f.size();
    case Directory d -> ...
};
```

### 3. Java 21: Data-Oriented Programming vs. Getter Chains

**Location**: `src/main/java/com/functional/v21/comparisons/CloudBillingEngine.java`

- **Scenario**: Calculate billing cost based on complex nested rules.
- **Legacy**: "Getter Hell" - chains of `getUsages().getResource().getIdentity()...` with defensive null checks.
- **Modern**: **Data-Oriented Programming**. We deconstruct the entire object graph in the match expression and use "Guarded Patterns" (`when`) to apply business logic.

```java
// Modern
case Usage(Resource(var type, Identity(var region, var tier)), var qty)
    when "PREMIUM".equals(tier) -> qty * 0.20;
```

---

## 🛠️ Requirements & Running

- **Java 21** or higher is required.
- **Maven** 3.8+ (optional, for building).

### Build & Test

```bash
mvn test
```

### Manual Compile

```bash
javac -d out --source 21 src/main/java/com/functional/v8/comparisons/StudentGradingSystem.java src/main/java/com/functional/v17/comparisons/FileSystem.java src/main/java/com/functional/v21/comparisons/CloudBillingEngine.java
```

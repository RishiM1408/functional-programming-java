package com.functional.v8.comparisons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * JEP 126: Lambda Expressions & Streams (Java 8)
 *
 * Comparisons:
 * 1. Imperative: Loops, mutable state (ArrayList), explicit sort with anonymous
 * class.
 * 2. Functional: Stream API, method references, immutable transformation.
 *
 * Advanced:
 * - Handling checked exceptions in Lambdas using a wrapper.
 */
public class StudentGradingSystem {

    public static class Student {
        private final String name;
        private final String department;
        private final double gpa;

        public Student(String name, String department, double gpa) {
            this.name = name;
            this.department = department;
            this.gpa = gpa;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public double getGpa() {
            return gpa;
        }

        @Override
        public String toString() {
            return name + " (" + gpa + ")";
        }
    }

    /**
     * LEGACY APPROACH: Imperative Style
     * Use for-loops, explicit if-checks, and mutable lists.
     */
    public List<Student> findTopStudentsImperative(List<Student> students, String department, int limit) {
        List<Student> filtered = new ArrayList<>();
        // 1. Filter
        for (Student s : students) {
            if (s != null && s.getDepartment().equals(department) && s.getGpa() > 3.5) {
                filtered.add(s);
            }
        }

        // 2. Sort (Descending GPA)
        Collections.sort(filtered, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.getGpa(), s1.getGpa());
            }
        });

        // 3. Limit
        if (filtered.size() > limit) {
            return filtered.subList(0, limit);
        }
        return filtered;
    }

    /**
     * MODERN APPROACH: Functional Style
     * Declarative pipeline. Use CheckedExceptionWrapper for advanced error handling
     * demonstration.
     */
    public List<Student> findTopStudentsFunctional(List<Student> students, String department, int limit) {
        return students.stream()
                .filter(s -> s != null) // Basic check
                .filter(s -> s.getDepartment().equals(department))
                .filter(s -> safeCheckGpa(s, 3.5)) // Advanced: Wrapped validation
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    // --- Advanced: Handling Checked Exceptions in Lambdas ---

    /**
     * Simulates a validation method that throws a Checked Exception.
     */
    private boolean validateGpa(Student s, double threshold) throws Exception {
        if (s.getGpa() < 0 || s.getGpa() > 4.0) {
            throw new Exception("Invalid GPA: " + s.getGpa());
        }
        return s.getGpa() > threshold;
    }

    /**
     * Wraps the checked exception call into a runtime exception so it works in
     * Stream Filters.
     */
    private boolean safeCheckGpa(Student s, double threshold) {
        try {
            return validateGpa(s, threshold);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Functional Interface that allows throwing exceptions.
     */
    @FunctionalInterface
    public interface ThrowingFunction<T, R, E extends Exception> {
        R apply(T t) throws E;
    }

    /**
     * Generic wrapper to convert ThrowingFunction to standard Function.
     */
    public static <T, R> Function<T, R> wrap(ThrowingFunction<T, R, Exception> throwingFunction) {
        return i -> {
            try {
                return throwingFunction.apply(i);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}

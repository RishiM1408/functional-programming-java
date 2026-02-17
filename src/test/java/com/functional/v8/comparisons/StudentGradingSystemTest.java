package com.functional.v8.comparisons;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class StudentGradingSystemTest {

    private final StudentGradingSystem system = new StudentGradingSystem();
    private final List<StudentGradingSystem.Student> students = Arrays.asList(
            new StudentGradingSystem.Student("Alice", "CS", 3.8),
            new StudentGradingSystem.Student("Bob", "CS", 3.4),
            new StudentGradingSystem.Student("Charlie", "EE", 3.9),
            new StudentGradingSystem.Student("Dave", "CS", 3.9),
            new StudentGradingSystem.Student("Eve", "CS", 3.6),
            new StudentGradingSystem.Student("Frank", "CS", 4.0),
            null);

    @Test
    void shouldReturnIdenticalResultsComparison() {
        // Imperative
        List<StudentGradingSystem.Student> imperativeResult = system.findTopStudentsImperative(students, "CS", 3);

        // Functional
        List<StudentGradingSystem.Student> functionalResult = system.findTopStudentsFunctional(students, "CS", 3);

        // Verify: Both should return [Frank(4.0), Dave(3.9), Alice(3.8)]
        assertThat(imperativeResult).hasSize(3);
        assertThat(functionalResult).hasSize(3);

        assertThat(imperativeResult).extracting("name")
                .containsExactly("Frank", "Dave", "Alice");

        assertThat(functionalResult).extracting("name")
                .containsExactly("Frank", "Dave", "Alice");

        // Direct equality
        assertThat(imperativeResult).usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(functionalResult);
    }
}

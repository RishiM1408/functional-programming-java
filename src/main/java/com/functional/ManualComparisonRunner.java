package com.functional;

import com.functional.v8.comparisons.StudentGradingSystem;
import com.functional.v17.comparisons.FileSystem;
import com.functional.v21.comparisons.CloudBillingEngine;

import java.util.Arrays;
import java.util.List;

public class ManualComparisonRunner {

    public static void main(String[] args) {
        System.out.println("Running Manual Comparison Tests...");
        boolean allPassed = true;

        try {
            allPassed &= testJava8();
            allPassed &= testJava17();
            allPassed &= testJava21();
        } catch (Exception e) {
            e.printStackTrace();
            allPassed = false;
        }

        if (allPassed) {
            System.out.println("\nAll Comparison Tests PASSED!");
        } else {
            System.out.println("\nSome Tests FAILED!");
            System.exit(1);
        }
    }

    private static boolean testJava8() {
        System.out.print("Testing Java 8 (StudentGrading)... ");
        StudentGradingSystem system = new StudentGradingSystem();
        List<StudentGradingSystem.Student> students = Arrays.asList(
                new StudentGradingSystem.Student("Alice", "CS", 3.8),
                new StudentGradingSystem.Student("Bob", "CS", 3.4),
                new StudentGradingSystem.Student("Frank", "CS", 4.0));

        List<StudentGradingSystem.Student> legacy = system.findTopStudentsImperative(students, "CS", 10);
        List<StudentGradingSystem.Student> modern = system.findTopStudentsFunctional(students, "CS", 10);

        if (legacy.size() != 2 || modern.size() != 2 || !legacy.get(0).getName().equals(modern.get(0).getName())) {
            System.out.println("FAILED");
            return false;
        }
        System.out.println("PASSED");
        return true;
    }

    private static boolean testJava17() {
        System.out.print("Testing Java 17 (FileSystem)... ");
        FileSystem.File mFile = new FileSystem.File("f1", 100);
        FileSystem.Directory mRoot = new FileSystem.Directory("root", List.of(mFile));
        long modernSize = FileSystem.calculateTotalSizeModern(mRoot);

        if (modernSize != 100) {
            System.out.println("FAILED (Size=" + modernSize + ")");
            return false;
        }
        System.out.println("PASSED");
        return true;
    }

    private static boolean testJava21() {
        System.out.print("Testing Java 21 (CloudBilling)... ");
        CloudBillingEngine engine = new CloudBillingEngine();
        var usage = new CloudBillingEngine.Usage(
                new CloudBillingEngine.Resource("VM", new CloudBillingEngine.Identity("EU", "PREMIUM")), 100);

        double legacy = engine.calculateCostLegacy(usage);
        double modern = engine.calculateCostModern(usage);

        // 100 * 0.25 = 25.0
        if (Math.abs(legacy - 25.0) > 0.001 || Math.abs(modern - 25.0) > 0.001) {
            System.out.println("FAILED (Legacy=" + legacy + ", Modern=" + modern + ")");
            return false;
        }
        System.out.println("PASSED");
        return true;
    }
}

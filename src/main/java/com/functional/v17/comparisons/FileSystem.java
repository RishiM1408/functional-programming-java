package com.functional.v17.comparisons;

import java.util.List;

/**
 * JEP 409: Sealed Classes (Java 17)
 *
 * Comparisons:
 * 1. Legacy: Abstract class, instanceOf checks, unsafe casting.
 * 2. Modern: Sealed Interface, Records, Pattern Matching for switch (Preview in
 * 17, Standard in 21).
 */
public class FileSystem {

    // --- LEGACY APPROACH: Abstract Class Hierarchy ---

    public static abstract class FileNode {
        public abstract String getName();
    }

    public static class LegacyFile extends FileNode {
        private final String name;
        private final long size;

        public LegacyFile(String name, long size) {
            this.name = name;
            this.size = size;
        }

        @Override
        public String getName() {
            return name;
        }

        public long getSize() {
            return size;
        }
    }

    public static class LegacyDirectory extends FileNode {
        private final String name;
        private final List<FileNode> children;

        public LegacyDirectory(String name, List<FileNode> children) {
            this.name = name;
            this.children = children;
        }

        @Override
        public String getName() {
            return name;
        }

        public List<FileNode> getChildren() {
            return children;
        }
    }

    /**
     * Legacy total size calculation.
     * Requires manual instanceof checks and casting.
     * Error-prone if a new subclass is added (compiler won't warn about missing
     * else-if).
     */
    public static long calculateTotalSizeLegacy(FileNode node) {
        if (node instanceof LegacyFile) {
            return ((LegacyFile) node).getSize();
        } else if (node instanceof LegacyDirectory) {
            LegacyDirectory dir = (LegacyDirectory) node;
            long sum = 0;
            for (FileNode child : dir.getChildren()) {
                sum += calculateTotalSizeLegacy(child);
            }
            return sum;
        } else {
            throw new IllegalArgumentException("Unknown node type");
        }
    }

    // --- MODERN APPROACH: Sealed Interface & Records ---

    public sealed interface Node permits File, Directory {
    }

    public record File(String name, long size) implements Node {
    }

    public record Directory(String name, List<Node> children) implements Node {
    }

    /**
     * Modern total size calculation.
     * Uses Pattern Matching for switch (Preview in 17, Standard in 21).
     * Compiler enforces exhaustiveness because Node is sealed.
     */
    public static long calculateTotalSizeModern(Node node) {
        return switch (node) {
            case File f -> f.size();
            case Directory d -> d.children().stream()
                    .mapToLong(FileSystem::calculateTotalSizeModern)
                    .sum();
        };
    }
}

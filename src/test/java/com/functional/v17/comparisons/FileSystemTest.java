package com.functional.v17.comparisons;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class FileSystemTest {

    @Test
    void shouldCalculateSizeIdentically() {
        // Build Legacy Tree: root -> [ file1(100), subDir -> [ file2(200) ] ]
        FileSystem.LegacyFile lFile1 = new FileSystem.LegacyFile("f1", 100);
        FileSystem.LegacyFile lFile2 = new FileSystem.LegacyFile("f2", 200);
        FileSystem.LegacyDirectory lSubDir = new FileSystem.LegacyDirectory("sub", List.of(lFile2));
        FileSystem.LegacyDirectory lRoot = new FileSystem.LegacyDirectory("root", List.of(lFile1, lSubDir));

        // Build Modern Tree: root -> [ file1(100), subDir -> [ file2(200) ] ]
        FileSystem.File mFile1 = new FileSystem.File("f1", 100);
        FileSystem.File mFile2 = new FileSystem.File("f2", 200);
        FileSystem.Directory mSubDir = new FileSystem.Directory("sub", List.of(mFile2));
        FileSystem.Directory mRoot = new FileSystem.Directory("root", List.of(mFile1, mSubDir));

        // Calculate Sizes
        long legacySize = FileSystem.calculateTotalSizeLegacy(lRoot);
        long modernSize = FileSystem.calculateTotalSizeModern(mRoot);

        // Verify
        assertThat(legacySize).isEqualTo(300);
        assertThat(modernSize).isEqualTo(300);
    }
}

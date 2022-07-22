package sorting;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class SortingDemoTest {
    private final SortingDemo demo = new SortingDemo();

    private void checkAlphabetical(List<String> strings) {
        strings.stream()
                .reduce("", (prev, curr) -> {
                            assertTrue(prev.compareTo(curr) <= 0);
                            return curr;
                        }
                );
    }

    private void checkLength(List<String> strings) {
        strings.stream()
                .reduce((prev, curr) -> {
                            assertTrue(prev.length() <= curr.length());
                            return curr;
                        }
                );
    }

    @Test
    public void testAlphaSort() {
        checkAlphabetical(demo.alphaSort());
    }

    @Test
    public void testAlphaSortUsingStreams() {
        checkAlphabetical(demo.alphaSortUsingStreams());
    }

    @SuppressWarnings("ComparatorMethodParameterNotUsed")
    @Test
    public void testLengthReverseSortWithComparator() {
        List<String> strings = demo.lengthReverseSortWithComparator().stream()
                .sorted((s1, s2) -> -1)
                .collect(Collectors.toList());
        checkLength(strings);
    }

    @Test
    public void testLengthSortWithLambda() {
        checkLength(demo.lengthSortWithLambda());
    }

    @Test
    public void testLengthSortUsingSorted() {
        checkLength(demo.lengthSortUsingSorted());
    }

    @Test
    public void testLengthSortUsingComparator() {
        checkLength(demo.lengthSortUsingComparator());
    }

    @Test
    public void testLengthSortThenAlphaSort() {
        demo.lengthSortThenAlphaSort().stream()
                .reduce((prev, curr) -> {
                    if (prev.length() != curr.length()) {
                        assertTrue(prev.length() <= curr.length());
                    } else {
                        assertTrue(prev.compareTo(curr) <= 0);
                    }
                    return curr;
                });
    }

    @Test
    public void testLengthSortThenReverseAlphaSort() {
        demo.lengthSortThenReverseAlphaSort().stream()
                .reduce((prev, curr) -> {
                    if (prev.length() != curr.length()) {
                        assertTrue(prev.length() <= curr.length());
                    } else {
                        assertTrue(prev.compareTo(curr) >= 0);
                    }
                    return curr;
                });
    }
}
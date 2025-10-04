package algorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for Kadane's Algorithm
 */
class KadaneAlgorithmTest {
    
    @Test
    @DisplayName("Basic test case from literature")
    void testBasicCase() {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        KadaneAlgorithm.Result result = KadaneAlgorithm.findMaxSubarray(arr);
        
        assertEquals(6, result.maxSum);
        assertEquals(3, result.startIndex);
        assertEquals(6, result.endIndex);
    }
    
    @Test
    @DisplayName("Single element array")
    void testSingleElement() {
        int[] arr = {5};
        KadaneAlgorithm.Result result = KadaneAlgorithm.findMaxSubarray(arr);
        
        assertEquals(5, result.maxSum);
        assertEquals(0, result.startIndex);
        assertEquals(0, result.endIndex);
    }
    
    @Test
    @DisplayName("All positive numbers")
    void testAllPositive() {
        int[] arr = {1, 2, 3, 4, 5};
        KadaneAlgorithm.Result result = KadaneAlgorithm.findMaxSubarray(arr);
        
        assertEquals(15, result.maxSum);
        assertEquals(0, result.startIndex);
        assertEquals(4, result.endIndex);
    }
    
    @Test
    @DisplayName("All negative numbers")
    void testAllNegative() {
        int[] arr = {-5, -2, -8, -1, -4};
        KadaneAlgorithm.Result result = KadaneAlgorithm.findMaxSubarray(arr);
        
        assertEquals(-1, result.maxSum);
        assertEquals(3, result.startIndex);
        assertEquals(3, result.endIndex);
    }
    
    @Test
    @DisplayName("Array with zeros")
    void testWithZeros() {
        int[] arr = {-2, 0, -1, 0, -3};
        KadaneAlgorithm.Result result = KadaneAlgorithm.findMaxSubarray(arr);
        
        assertEquals(0, result.maxSum);
    }
    
    @Test
    @DisplayName("Maximum at beginning")
    void testMaxAtBeginning() {
        int[] arr = {10, -5, -2, -1};
        KadaneAlgorithm.Result result = KadaneAlgorithm.findMaxSubarray(arr);
        
        assertEquals(10, result.maxSum);
        assertEquals(0, result.startIndex);
        assertEquals(0, result.endIndex);
    }
    
    @Test
    @DisplayName("Maximum at end")
    void testMaxAtEnd() {
        int[] arr = {-5, -2, -1, 10};
        KadaneAlgorithm.Result result = KadaneAlgorithm.findMaxSubarray(arr);
        
        assertEquals(10, result.maxSum);
        assertEquals(3, result.startIndex);
        assertEquals(3, result.endIndex);
    }
    
    @Test
    @DisplayName("Null array throws exception")
    void testNullArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            KadaneAlgorithm.findMaxSubarray(null);
        });
    }
    
    @Test
    @DisplayName("Empty array throws exception")
    void testEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            KadaneAlgorithm.findMaxSubarray(new int[]{});
        });
    }
    
    @Test
    @DisplayName("Large numbers")
    void testLargeNumbers() {
        int[] arr = {Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2};
        KadaneAlgorithm.Result result = KadaneAlgorithm.findMaxSubarray(arr);
        
        assertTrue(result.maxSum > 0);
        assertEquals(0, result.startIndex);
        assertEquals(1, result.endIndex);
    }
    
    @Test
    @DisplayName("Alternating positive and negative")
    void testAlternating() {
        int[] arr = {5, -3, 5, -3, 5};
        KadaneAlgorithm.Result result = KadaneAlgorithm.findMaxSubarray(arr);
        
        assertEquals(9, result.maxSum);
    }
    
    @Test
    @DisplayName("Metrics are tracked correctly")
    void testMetricsTracking() {
        int[] arr = {1, 2, 3, 4, 5};
        KadaneAlgorithm.Result result = KadaneAlgorithm.findMaxSubarray(arr);
        
        assertNotNull(result.metrics);
        assertTrue(result.metrics.getComparisons() > 0);
        assertTrue(result.metrics.getArrayAccesses() > 0);
        assertTrue(result.metrics.getExecutionTimeNanos() >= 0);
    }
    
    @ParameterizedTest
    @MethodSource("provideTestArrays")
    @DisplayName("Property-based testing: result sum matches expected")
    void testPropertyBasedValidation(TestCase testCase) {
        KadaneAlgorithm.Result result = KadaneAlgorithm.findMaxSubarray(testCase.array);
        
        // Calculate actual sum from indices
        int actualSum = 0;
        for (int i = result.startIndex; i <= result.endIndex; i++) {
            actualSum += testCase.array[i];
        }
        
        assertEquals(result.maxSum, actualSum, 
            "Reported max sum should match actual sum of subarray");
    }
    
    static class TestCase {
        int[] array;
        
        TestCase(int[] array) {
            this.array = array;
        }
    }
    
    static Stream<TestCase> provideTestArrays() {
        return Stream.of(
            new TestCase(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}),
            new TestCase(new int[]{1, 2, 3, 4, 5}),
            new TestCase(new int[]{-5, -2, -8, -1, -4}),
            new TestCase(new int[]{10, -5, 3, -2, 8}),
            new TestCase(new int[]{5})
        );
    }
}

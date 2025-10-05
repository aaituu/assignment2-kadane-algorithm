package algorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for optimized Kadane's Algorithm
 */
class KadaneAlgorithmOptimizedTest {
    
    @Test
    @DisplayName("Basic test case - should produce same results as original")
    void testBasicCase() {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        KadaneAlgorithmOptimized.Result result = KadaneAlgorithmOptimized.findMaxSubarray(arr);
        
        assertEquals(6, result.maxSum);
        assertEquals(3, result.startIndex);
        assertEquals(6, result.endIndex);
    }
    
    @Test
    @DisplayName("All positive numbers")
    void testAllPositive() {
        int[] arr = {1, 2, 3, 4, 5};
        KadaneAlgorithmOptimized.Result result = KadaneAlgorithmOptimized.findMaxSubarray(arr);
        
        assertEquals(15, result.maxSum);
        assertEquals(0, result.startIndex);
        assertEquals(4, result.endIndex);
    }
    
    @Test
    @DisplayName("All negative numbers")
    void testAllNegative() {
        int[] arr = {-5, -2, -8, -1, -4};
        KadaneAlgorithmOptimized.Result result = KadaneAlgorithmOptimized.findMaxSubarray(arr);
        
        assertEquals(-1, result.maxSum);
        assertEquals(3, result.startIndex);
        assertEquals(3, result.endIndex);
    }
    
    @Test
    @DisplayName("Large numbers - test overflow protection")
    void testOverflowProtection() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.MAX_VALUE / 10;
        }
        
        KadaneAlgorithmOptimized.Result result = KadaneAlgorithmOptimized.findMaxSubarray(arr);
        
        // Should not overflow with long
        assertTrue(result.maxSum > 0);
        assertEquals(0, result.startIndex);
        assertEquals(99, result.endIndex);
    }
    
    @Test
    @DisplayName("Early termination for all-negative")
    void testEarlyTermination() {
        int[] arr = {-10, -5, -20, -1, -15};
        KadaneAlgorithmOptimized.Result result = 
            KadaneAlgorithmOptimized.findMaxSubarrayWithEarlyTermination(arr);
        
        assertEquals(-1, result.maxSum);
        assertEquals(3, result.startIndex);
        assertEquals(3, result.endIndex);
    }
    
    @Test
    @DisplayName("Reduced array accesses")
    void testArrayAccessReduction() {
        int[] arr = {1, 2, 3, 4, 5};
        KadaneAlgorithmOptimized.Result result = KadaneAlgorithmOptimized.findMaxSubarray(arr);
        
        // Should have fewer array accesses than original
        assertTrue(result.metrics.getArrayAccesses() <= arr.length + 1);
    }
}

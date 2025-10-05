package algorithms;

import metrics.PerformanceTracker;

/**
 * Kadane's Algorithm - Optimized Version
 * 
 * Optimizations based on peer review by Student A:
 * 1. Cache array elements to reduce redundant access
 * 2. Simplified comparison logic (currentSum < 0)
 * 3. Use long for sum calculations to prevent overflow
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class KadaneAlgorithmOptimized {
    
    public static class Result {
        public final long maxSum;  // Changed from int to long
        public final int startIndex;
        public final int endIndex;
        public final PerformanceTracker metrics;
        
        public Result(long maxSum, int startIndex, int endIndex, PerformanceTracker metrics) {
            this.maxSum = maxSum;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.metrics = metrics;
        }
        
        @Override
        public String toString() {
            return String.format("MaxSum: %d, Range: [%d, %d]", maxSum, startIndex, endIndex);
        }
    }
    
    /**
     * Optimized version with reduced array access and overflow protection
     * 
     * Optimization #1: Cache array element (reduce 3n to n accesses)
     * Optimization #2: Simplified comparison logic (currentSum < 0)
     * Optimization #3: Use long to prevent integer overflow
     * 
     * @param arr Input array
     * @return Result containing max sum, positions, and performance metrics
     * @throws IllegalArgumentException if array is null or empty
     */
    public static Result findMaxSubarray(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        
        PerformanceTracker tracker = new PerformanceTracker();
        tracker.startTimer();
        
        long maxSum = arr[0];
        long currentSum = arr[0];
        int start = 0;
        int end = 0;
        int tempStart = 0;
        
        tracker.incrementComparisons();
        tracker.incrementArrayAccess(); // arr[0]
        
        for (int i = 1; i < arr.length; i++) {
            // OPTIMIZATION #1: Cache array element (single read)
            int current = arr[i];
            tracker.incrementArrayAccess();
            
            // OPTIMIZATION #2: Simplified logic - check if currentSum < 0
            tracker.incrementComparisons();
            if (currentSum < 0) {
                currentSum = current;
                tempStart = i;
            } else {
                currentSum = currentSum + current;
            }
            
            // Update maximum if needed
            tracker.incrementComparisons();
            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }
        
        tracker.stopTimer();
        return new Result(maxSum, start, end, tracker);
    }
    
    /**
     * Alternative optimized version with early termination
     */
    public static Result findMaxSubarrayWithEarlyTermination(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        
        PerformanceTracker tracker = new PerformanceTracker();
        tracker.startTimer();
        
        // Check for all-negative array (early termination)
        boolean allNegative = true;
        int maxElement = arr[0];
        int maxIndex = 0;
        
        for (int i = 0; i < arr.length; i++) {
            tracker.incrementArrayAccess();
            tracker.incrementComparisons();
            
            if (arr[i] >= 0) {
                allNegative = false;
                break;
            }
            if (arr[i] > maxElement) {
                maxElement = arr[i];
                maxIndex = i;
            }
        }
        
        if (allNegative) {
            tracker.stopTimer();
            return new Result(maxElement, maxIndex, maxIndex, tracker);
        }
        
        // Standard optimized Kadane's algorithm
        long maxSum = arr[0];
        long currentSum = arr[0];
        int start = 0;
        int end = 0;
        int tempStart = 0;
        
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            tracker.incrementArrayAccess();
            tracker.incrementComparisons();
            
            if (currentSum < 0) {
                currentSum = current;
                tempStart = i;
            } else {
                currentSum = currentSum + current;
            }
            
            tracker.incrementComparisons();
            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }
        
        tracker.stopTimer();
        return new Result(maxSum, start, end, tracker);
    }
}

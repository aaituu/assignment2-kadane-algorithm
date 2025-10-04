package algorithms;

import metrics.PerformanceTracker;

/**
 * Kadane's Algorithm - Maximum Subarray Sum
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 * 
 * Finds the contiguous subarray with the maximum sum and tracks its position.
 */
public class KadaneAlgorithm {
    
    public static class Result {
        public final int maxSum;
        public final int startIndex;
        public final int endIndex;
        public final PerformanceTracker metrics;
        
        public Result(int maxSum, int startIndex, int endIndex, PerformanceTracker metrics) {
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
     * Finds maximum subarray sum with position tracking
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
        
        int maxSum = arr[0];
        int currentSum = arr[0];
        int start = 0;
        int end = 0;
        int tempStart = 0;
        
        tracker.incrementComparisons(); // arr[0] assignment
        tracker.incrementArrayAccess(2); // Read arr[0] twice
        
        for (int i = 1; i < arr.length; i++) {
            tracker.incrementArrayAccess(); // Read arr[i]
            tracker.incrementComparisons(); // Compare currentSum + arr[i] vs arr[i]
            
            // If starting fresh is better than continuing
            if (arr[i] > currentSum + arr[i]) {
                currentSum = arr[i];
                tempStart = i;
            } else {
                currentSum = currentSum + arr[i];
            }
            
            tracker.incrementComparisons(); // Compare currentSum vs maxSum
            
            // Update maximum if current is better
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
     * Optimized version with early termination for all-negative arrays
     */
    public static Result findMaxSubarrayOptimized(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        
        PerformanceTracker tracker = new PerformanceTracker();
        tracker.startTimer();
        
        // Check if all elements are negative - find max element
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
        
        // Standard Kadane's algorithm
        int maxSum = arr[0];
        int currentSum = arr[0];
        int start = 0;
        int end = 0;
        int tempStart = 0;
        
        for (int i = 1; i < arr.length; i++) {
            tracker.incrementArrayAccess();
            tracker.incrementComparisons();
            
            if (arr[i] > currentSum + arr[i]) {
                currentSum = arr[i];
                tempStart = i;
            } else {
                currentSum = currentSum + arr[i];
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

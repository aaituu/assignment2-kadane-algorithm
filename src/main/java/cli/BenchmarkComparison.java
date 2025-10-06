package cli;

import algorithms.KadaneAlgorithm;
import algorithms.KadaneAlgorithmOptimized;

import java.util.Random;

/**
 * Compare original vs optimized implementation
 */
public class BenchmarkComparison {
    
    public static void main(String[] args) {
        System.out.println("=== Kadane's Algorithm: Original vs Optimized ===\n");
        
        int[] sizes = {1000, 5000, 10000, 50000, 100000};
        
        System.out.println("Performance Comparison:");
        System.out.println("-".repeat(80));
        System.out.printf("%-10s | %-15s | %-15s | %-15s%n", 
            "Size", "Original (ms)", "Optimized (ms)", "Improvement");
        System.out.println("-".repeat(80));
        
        for (int size : sizes) {
            int[] arr = generateRandomArray(size, -1000, 1000);
            
            // Test original
            long startOrig = System.nanoTime();
            KadaneAlgorithm.Result origResult = KadaneAlgorithm.findMaxSubarray(arr);
            long endOrig = System.nanoTime();
            double timeOrig = (endOrig - startOrig) / 1_000_000.0;
            
            // Test optimized
            long startOpt = System.nanoTime();
            KadaneAlgorithmOptimized.Result optResult = KadaneAlgorithmOptimized.findMaxSubarray(arr);
            long endOpt = System.nanoTime();
            double timeOpt = (endOpt - startOpt) / 1_000_000.0;
            
            // Verify correctness
            if (origResult.maxSum != optResult.maxSum) {
                System.err.println("ERROR: Results don't match!");
                System.exit(1);
            }
            
            double improvement = ((timeOrig - timeOpt) / timeOrig) * 100;
            
            System.out.printf("%-10d | %-15.4f | %-15.4f | %+.2f%%%n",
                size, timeOrig, timeOpt, improvement);
        }
        
        System.out.println("-".repeat(80));
        System.out.println("\nArray Access Comparison:");
        System.out.println("-".repeat(60));
        
        int testSize = 10000;
        int[] testArr = generateRandomArray(testSize, -1000, 1000);
        
        KadaneAlgorithm.Result origResult = KadaneAlgorithm.findMaxSubarray(testArr);
        KadaneAlgorithmOptimized.Result optResult = KadaneAlgorithmOptimized.findMaxSubarray(testArr);
        
        System.out.printf("Original - Array Accesses: %d (%.2f per element)%n",
            origResult.metrics.getArrayAccesses(),
            (double) origResult.metrics.getArrayAccesses() / testSize);
        
        System.out.printf("Optimized - Array Accesses: %d (%.2f per element)%n",
            optResult.metrics.getArrayAccesses(),
            (double) optResult.metrics.getArrayAccesses() / testSize);
        
        long reduction = origResult.metrics.getArrayAccesses() - optResult.metrics.getArrayAccesses();
        double reductionPercent = ((double) reduction / origResult.metrics.getArrayAccesses()) * 100;
        
        System.out.printf("Reduction: %d accesses (%.2f%%)%n", reduction, reductionPercent);
    }
    
    private static int[] generateRandomArray(int size, int min, int max) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(max - min + 1) + min;
        }
        return arr;
    }
}

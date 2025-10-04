package cli;

import algorithms.KadaneAlgorithm;
import metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Command-line interface for benchmarking Kadane's Algorithm
 */
public class BenchmarkRunner {
    
    public static void main(String[] args) {
        System.out.println("=== Kadane's Algorithm Benchmark ===\n");
        
        if (args.length > 0 && args[0].equals("--quick")) {
            runQuickTest();
        } else if (args.length > 0 && args[0].equals("--full")) {
            runFullBenchmark();
        } else {
            System.out.println("Usage:");
            System.out.println("  --quick : Run quick test");
            System.out.println("  --full  : Run full benchmark suite");
            System.out.println("\nRunning demo...\n");
            runDemo();
        }
    }
    
    private static void runDemo() {
        int[] testArray = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        
        System.out.println("Test Array: ");
        printArray(testArray);
        
        KadaneAlgorithm.Result result = KadaneAlgorithm.findMaxSubarray(testArray);
        
        System.out.println("\nResult: " + result);
        System.out.println("Subarray: ");
        for (int i = result.startIndex; i <= result.endIndex; i++) {
            System.out.print(testArray[i] + " ");
        }
        System.out.println("\n\nMetrics: " + result.metrics);
    }
    
    private static void runQuickTest() {
        System.out.println("Running quick test on small arrays...\n");
        
        int[] sizes = {10, 50, 100, 500, 1000};
        
        for (int size : sizes) {
            int[] arr = generateRandomArray(size, -100, 100);
            KadaneAlgorithm.Result result = KadaneAlgorithm.findMaxSubarray(arr);
            
            System.out.printf("n=%5d | %s\n", size, result.metrics);
        }
    }
    
    private static void runFullBenchmark() {
        System.out.println("Running full benchmark suite...\n");
        System.out.println("This may take a few minutes...\n");
        
        int[] sizes = {100, 500, 1000, 5000, 10000, 50000, 100000};
        int runsPerSize = 10;
        
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/benchmark_results.csv"))) {
            writer.println("InputSize,Run,Comparisons,ArrayAccesses,TimeMs,MemoryBytes");
            
            for (int size : sizes) {
                System.out.printf("Testing n=%d...\n", size);
                
                for (int run = 0; run < runsPerSize; run++) {
                    int[] arr = generateRandomArray(size, -1000, 1000);
                    KadaneAlgorithm.Result result = KadaneAlgorithm.findMaxSubarray(arr);
                    
                    writer.printf("%d,%d,%d,%d,%.6f,%d\n",
                        size, run + 1,
                        result.metrics.getComparisons(),
                        result.metrics.getArrayAccesses(),
                        result.metrics.getExecutionTimeMillis(),
                        result.metrics.getMemoryUsed()
                    );
                }
            }
            
            System.out.println("\n✅ Results saved to data/benchmark_results.csv");
            
        } catch (IOException e) {
            System.err.println("Error writing results: " + e.getMessage());
        }
        
        // Test different input distributions
        runDistributionTests();
    }
    
    private static void runDistributionTests() {
        System.out.println("\nTesting different input distributions...\n");
        
        int size = 10000;
        int runs = 5;
        
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/distribution_results.csv"))) {
            writer.println("Distribution,Run,Comparisons,ArrayAccesses,TimeMs");
            
            // Random distribution
            System.out.println("Testing random distribution...");
            for (int i = 0; i < runs; i++) {
                int[] arr = generateRandomArray(size, -1000, 1000);
                KadaneAlgorithm.Result result = KadaneAlgorithm.findMaxSubarray(arr);
                writer.printf("Random,%d,%d,%d,%.6f\n", i + 1,
                    result.metrics.getComparisons(),
                    result.metrics.getArrayAccesses(),
                    result.metrics.getExecutionTimeMillis());
            }
            
            // All positive
            System.out.println("Testing all positive...");
            for (int i = 0; i < runs; i++) {
                int[] arr = generateRandomArray(size, 1, 1000);
                KadaneAlgorithm.Result result = KadaneAlgorithm.findMaxSubarray(arr);
                writer.printf("AllPositive,%d,%d,%d,%.6f\n", i + 1,
                    result.metrics.getComparisons(),
                    result.metrics.getArrayAccesses(),
                    result.metrics.getExecutionTimeMillis());
            }
            
            // All negative
            System.out.println("Testing all negative...");
            for (int i = 0; i < runs; i++) {
                int[] arr = generateRandomArray(size, -1000, -1);
                KadaneAlgorithm.Result result = KadaneAlgorithm.findMaxSubarray(arr);
                writer.printf("AllNegative,%d,%d,%d,%.6f\n", i + 1,
                    result.metrics.getComparisons(),
                    result.metrics.getArrayAccesses(),
                    result.metrics.getExecutionTimeMillis());
            }
            
            System.out.println("\n✅ Distribution results saved to data/distribution_results.csv");
            
        } catch (IOException e) {
            System.err.println("Error writing distribution results: " + e.getMessage());
        }
    }
    
    private static int[] generateRandomArray(int size, int min, int max) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(max - min + 1) + min;
        }
        return arr;
    }
    
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}

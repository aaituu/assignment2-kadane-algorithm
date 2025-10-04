# Assignment 2: Kadane's Algorithm

Maximum Subarray Sum Algorithm with Performance Analysis

## Algorithm Overview

Kadane's Algorithm finds the contiguous subarray within a one-dimensional array with the largest sum in O(n) time and O(1) space.

**Student:** Student B  
**Pair:** 3 - Linear Array Algorithms  
**Algorithm:** Kadane's Algorithm (Maximum Subarray Sum with Position Tracking)

## Complexity Analysis

### Time Complexity
- **Best Case:** Θ(n) - Single pass through array
- **Average Case:** Θ(n) - Single pass through array
- **Worst Case:** Θ(n) - Single pass through array

### Space Complexity
- **Auxiliary Space:** Θ(1) - Only constant extra variables

## Features

- ✅ Position tracking (start and end indices)
- ✅ Comprehensive performance metrics
- ✅ Edge case handling (empty, single element, all negative)
- ✅ Optimized version with early termination
- ✅ Full test coverage
- ✅ CLI benchmark tool

## Building and Running

### Prerequisites
- Java 17+
- Maven 3.6+

### Build
```bash
mvn clean compile
```

### Run Tests
```bash
mvn test
```

### Run Demo
```bash
mvn exec:java -Dexec.mainClass="cli.BenchmarkRunner"
```

### Run Quick Benchmark
```bash
mvn exec:java -Dexec.mainClass="cli.BenchmarkRunner" -Dexec.args="--quick"
```

### Run Full Benchmark
```bash
mvn exec:java -Dexec.mainClass="cli.BenchmarkRunner" -Dexec.args="--full"
```

## Project Structure

```
assignment2-kadane-algorithm/
├── src/
│   ├── main/java/
│   │   ├── algorithms/KadaneAlgorithm.java
│   │   ├── metrics/PerformanceTracker.java
│   │   └── cli/BenchmarkRunner.java
│   └── test/java/
│       └── algorithms/KadaneAlgorithmTest.java
├── docs/
│   ├── analysis-report.pdf
│   └── performance-plots/
├── data/
│   ├── benchmark_results.csv
│   └── distribution_results.csv
├── pom.xml
└── README.md
```

## Usage Example

```java
int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
KadaneAlgorithm.Result result = KadaneAlgorithm.findMaxSubarray(arr);

System.out.println("Max Sum: " + result.maxSum);  // 6
System.out.println("Range: [" + result.startIndex + ", " + result.endIndex + "]");  // [3, 6]
System.out.println(result.metrics);
```

## Test Coverage

- ✅ Basic functionality
- ✅ Edge cases (empty, single element)
- ✅ All positive numbers
- ✅ All negative numbers
- ✅ Arrays with zeros
- ✅ Large numbers
- ✅ Alternating patterns
- ✅ Property-based validation

## Performance Metrics

The implementation tracks:
- Number of comparisons
- Array access operations
- Execution time (nanoseconds)
- Memory usage

## Git Workflow

Branches:
- `main` - Stable releases only
- `feature/algorithm` - Core implementation
- `feature/metrics` - Performance tracking
- `feature/testing` - Unit tests
- `feature/cli` - Command-line interface
- `feature/optimization` - Performance improvements

## License

Academic project for Algorithm Analysis course.

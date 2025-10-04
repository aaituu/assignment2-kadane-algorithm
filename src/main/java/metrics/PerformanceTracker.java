package metrics;

/**
 * Tracks performance metrics for algorithm analysis
 */
public class PerformanceTracker {
    private long comparisons;
    private long arrayAccesses;
    private long assignments;
    private long startTime;
    private long endTime;
    private long memoryUsed;
    
    public PerformanceTracker() {
        this.comparisons = 0;
        this.arrayAccesses = 0;
        this.assignments = 0;
        this.startTime = 0;
        this.endTime = 0;
        this.memoryUsed = 0;
    }
    
    public void startTimer() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Suggest garbage collection
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        this.startTime = System.nanoTime();
        this.memoryUsed = memoryBefore;
    }
    
    public void stopTimer() {
        this.endTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        this.memoryUsed = memoryAfter - this.memoryUsed;
    }
    
    public void incrementComparisons() {
        this.comparisons++;
    }
    
    public void incrementComparisons(long count) {
        this.comparisons += count;
    }
    
    public void incrementArrayAccess() {
        this.arrayAccesses++;
    }
    
    public void incrementArrayAccess(long count) {
        this.arrayAccesses += count;
    }
    
    public void incrementAssignments() {
        this.assignments++;
    }
    
    public long getComparisons() {
        return comparisons;
    }
    
    public long getArrayAccesses() {
        return arrayAccesses;
    }
    
    public long getAssignments() {
        return assignments;
    }
    
    public long getExecutionTimeNanos() {
        return endTime - startTime;
    }
    
    public double getExecutionTimeMillis() {
        return (endTime - startTime) / 1_000_000.0;
    }
    
    public long getMemoryUsed() {
        return memoryUsed;
    }
    
    @Override
    public String toString() {
        return String.format(
            "Comparisons: %d, Array Accesses: %d, Time: %.3f ms, Memory: %d bytes",
            comparisons, arrayAccesses, getExecutionTimeMillis(), memoryUsed
        );
    }
    
    public String toCSVHeader() {
        return "InputSize,Comparisons,ArrayAccesses,TimeMs,MemoryBytes";
    }
    
    public String toCSV(int inputSize) {
        return String.format("%d,%d,%d,%.6f,%d",
            inputSize, comparisons, arrayAccesses, getExecutionTimeMillis(), memoryUsed
        );
    }
}

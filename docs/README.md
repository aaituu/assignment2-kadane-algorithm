# Documentation

## Analysis Report

The analysis report (`analysis-report.pdf`) contains:

1. **Algorithm Overview** - Theoretical background of Kadane's Algorithm
2. **Complexity Analysis** - Detailed time/space complexity derivations
3. **Code Review** - Analysis of implementation efficiency
4. **Empirical Results** - Benchmark data and performance plots
5. **Conclusion** - Optimization recommendations

## Performance Plots

Located in `performance-plots/`:
- `time_vs_size.png` - Execution time vs input size
- `comparisons_vs_size.png` - Number of comparisons vs input size
- `distribution_comparison.png` - Performance across different input distributions

## Generating Plots

Use Python with matplotlib:

```python
import pandas as pd
import matplotlib.pyplot as plt

# Read benchmark data
df = pd.read_csv('../data/benchmark_results.csv')

# Plot time vs size
plt.figure(figsize=(10, 6))
plt.plot(df['InputSize'], df['TimeMs'], 'o-')
plt.xlabel('Input Size (n)')
plt.ylabel('Time (ms)')
plt.title('Kadane Algorithm: Time Complexity')
plt.grid(True)
plt.savefig('time_vs_size.png')
```

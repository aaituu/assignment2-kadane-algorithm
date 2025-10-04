#!/usr/bin/env python3
"""
Generate performance plots from benchmark data
"""

import pandas as pd
import matplotlib.pyplot as plt
import os

# Create output directory
os.makedirs('performance-plots', exist_ok=True)

# Read benchmark data
try:
    df = pd.read_csv('../data/benchmark_results.csv')
    
    # Group by input size and calculate mean
    grouped = df.groupby('InputSize').mean().reset_index()
    
    # Plot 1: Time vs Input Size
    plt.figure(figsize=(10, 6))
    plt.plot(grouped['InputSize'], grouped['TimeMs'], 'o-', linewidth=2, markersize=8)
    plt.xlabel('Input Size (n)', fontsize=12)
    plt.ylabel('Average Time (ms)', fontsize=12)
    plt.title('Kadane Algorithm: Time Complexity Analysis', fontsize=14, fontweight='bold')
    plt.grid(True, alpha=0.3)
    plt.tight_layout()
    plt.savefig('performance-plots/time_vs_size.png', dpi=300)
    print("✅ Generated: time_vs_size.png")
    
    # Plot 2: Comparisons vs Input Size
    plt.figure(figsize=(10, 6))
    plt.plot(grouped['InputSize'], grouped['Comparisons'], 's-', linewidth=2, markersize=8, color='orange')
    plt.xlabel('Input Size (n)', fontsize=12)
    plt.ylabel('Number of Comparisons', fontsize=12)
    plt.title('Kadane Algorithm: Comparison Operations', fontsize=14, fontweight='bold')
    plt.grid(True, alpha=0.3)
    plt.tight_layout()
    plt.savefig('performance-plots/comparisons_vs_size.png', dpi=300)
    print("✅ Generated: comparisons_vs_size.png")
    
    # Plot 3: Array Accesses vs Input Size
    plt.figure(figsize=(10, 6))
    plt.plot(grouped['InputSize'], grouped['ArrayAccesses'], '^-', linewidth=2, markersize=8, color='green')
    plt.xlabel('Input Size (n)', fontsize=12)
    plt.ylabel('Array Access Operations', fontsize=12)
    plt.title('Kadane Algorithm: Memory Access Patterns', fontsize=14, fontweight='bold')
    plt.grid(True, alpha=0.3)
    plt.tight_layout()
    plt.savefig('performance-plots/array_accesses_vs_size.png', dpi=300)
    print("✅ Generated: array_accesses_vs_size.png")
    
    # Plot 4: Linear regression verification
    plt.figure(figsize=(10, 6))
    plt.scatter(grouped['InputSize'], grouped['TimeMs'], s=100, alpha=0.6)
    
    # Fit linear model
    from numpy.polynomial import Polynomial
    p = Polynomial.fit(grouped['InputSize'], grouped['TimeMs'], 1)
    x_fit = grouped['InputSize']
    y_fit = p(x_fit)
    plt.plot(x_fit, y_fit, 'r--', linewidth=2, label=f'Linear fit: O(n)')
    
    plt.xlabel('Input Size (n)', fontsize=12)
    plt.ylabel('Time (ms)', fontsize=12)
    plt.title('Linear Time Complexity Verification', fontsize=14, fontweight='bold')
    plt.legend(fontsize=11)
    plt.grid(True, alpha=0.3)
    plt.tight_layout()
    plt.savefig('performance-plots/linear_verification.png', dpi=300)
    print("✅ Generated: linear_verification.png")
    
except FileNotFoundError:
    print("❌ Error: benchmark_results.csv not found. Run benchmarks first.")
    exit(1)

# Read distribution data if available
try:
    dist_df = pd.read_csv('../data/distribution_results.csv')
    
    # Group by distribution type
    dist_grouped = dist_df.groupby('Distribution').mean().reset_index()
    
    # Plot 5: Distribution comparison
    plt.figure(figsize=(12, 6))
    distributions = dist_grouped['Distribution']
    x = range(len(distributions))
    
    plt.bar(x, dist_grouped['TimeMs'], color=['#3498db', '#2ecc71', '#e74c3c'])
    plt.xlabel('Input Distribution', fontsize=12)
    plt.ylabel('Average Time (ms)', fontsize=12)
    plt.title('Performance Across Different Input Distributions (n=10,000)', fontsize=14, fontweight='bold')
    plt.xticks(x, distributions)
    plt.grid(True, alpha=0.3, axis='y')
    plt.tight_layout()
    plt.savefig('performance-plots/distribution_comparison.png', dpi=300)
    print("✅ Generated: distribution_comparison.png")
    
except FileNotFoundError:
    print("⚠️  Warning: distribution_results.csv not found. Skipping distribution plots.")

print("\n🎉 All plots generated successfully in docs/performance-plots/")

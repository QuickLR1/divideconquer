# Divide & Conquer Algorithms – Report

## Architecture Notes

The project is organized around a clear separation of **algorithms** and **utilities**:

* `dnc/` contains algorithm implementations (QuickSort, MergeSort, Deterministic Select, Closest Pair, etc.).
* `util/` contains supporting functions (partition, shuffle, swap, guards).
* `cli/` provides a command-line interface for experiments and CSV output.

**Depth control**: recursive algorithms include depth counters to measure recursion height, useful for comparing theoretical and practical recursion costs.
**Allocations**: MergeSort and Closest Pair rely on auxiliary arrays or temporary lists, while QuickSort and Deterministic Select work in-place, reducing allocation overhead.

---

## Recurrence Analysis

### QuickSort

Recurrence (average case):
T(n) = 2T(n/2) + Θ(n) → Θ(n log n)
Analyzed with **Master Theorem**, where a = 2, b = 2, f(n) = Θ(n). Case 2 applies.

Worst case: T(n) = T(n–1) + Θ(n) → Θ(n²), but random pivots make this rare.

---

### MergeSort

Recurrence:
T(n) = 2T(n/2) + Θ(n) → Θ(n log n)
Straightforward divide-and-conquer, Master Theorem Case 2 again.
Requires Θ(n) auxiliary memory.

---

### Deterministic Select (Median of Medians, groups of 5)

Recurrence:
T(n) ≤ T(n/5) + T(7n/10) + Θ(n)
Solvable by **Akra–Bazzi** method: T(n) = Θ(n).
This ensures linear time worst-case selection.

---

### Closest Pair of Points

Divide-and-conquer recurrence:
T(n) = 2T(n/2) + Θ(n log n)
Solves to Θ(n log² n).
With careful merge step (strip processing in Θ(n)), it can be improved to Θ(n log n).

---

## Experimental Plots (Planned)

* **Time vs n** to compare asymptotic growth.
* **Depth vs n** to confirm logarithmic recursion depth.
* Discussion of constant factors: cache locality improves MergeSort, while QuickSort benefits from in-place swaps. JVM garbage collection may affect timings when temporary arrays are large.

---

## Summary

* **Theory vs Practice**:

    * QuickSort outperforms MergeSort in practice due to smaller constant factors, despite same Θ(n log n) complexity.
    * Deterministic Select guarantees Θ(n) but is slower than randomized select on small datasets due to overhead.
    * Closest Pair matches theory only for large n; for small n, constants dominate.

* **Alignment**: The theoretical asymptotics mostly match experimental results.

* **Mismatch**: Practical performance is influenced by cache, memory allocations, and JVM optimizations.

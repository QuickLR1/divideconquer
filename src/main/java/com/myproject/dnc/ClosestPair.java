package com.myproject.dnc;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static class Point {
        public final double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double closest(Point[] points) {
        if (points == null || points.length < 2) {
            throw new IllegalArgumentException("Need at least two points");
        }
        Point[] sortedByX = Arrays.copyOf(points, points.length);
        Arrays.sort(sortedByX, Comparator.comparingDouble(p -> p.x));
        Point[] aux = new Point[points.length];
        return closestRecursive(sortedByX, aux, 0, points.length - 1);
    }

    private static double closestRecursive(Point[] pts, Point[] aux, int lo, int hi) {
        if (hi - lo <= 3) {
            return bruteForce(pts, lo, hi);
        }

        int mid = (lo + hi) / 2;
        double midX = pts[mid].x;

        double dLeft = closestRecursive(pts, aux, lo, mid);
        double dRight = closestRecursive(pts, aux, mid + 1, hi);
        double d = Math.min(dLeft, dRight);

        mergeByY(pts, aux, lo, mid, hi);

        int m = 0;
        for (int i = lo; i <= hi; i++) {
            if (Math.abs(pts[i].x - midX) < d) {
                aux[m++] = pts[i];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m && (aux[j].y - aux[i].y) < d; j++) {
                d = Math.min(d, dist(aux[i], aux[j]));
            }
        }
        return d;
    }

    private static double bruteForce(Point[] pts, int lo, int hi) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = lo; i <= hi; i++) {
            for (int j = i + 1; j <= hi; j++) {
                min = Math.min(min, dist(pts[i], pts[j]));
            }
        }
        Arrays.sort(pts, lo, hi + 1, Comparator.comparingDouble(p -> p.y));
        return min;
    }

    private static void mergeByY(Point[] pts, Point[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1, k = 0;
        while (i <= mid && j <= hi) {
            if (pts[i].y <= pts[j].y) aux[k++] = pts[i++];
            else aux[k++] = pts[j++];
        }
        while (i <= mid) aux[k++] = pts[i++];
        while (j <= hi) aux[k++] = pts[j++];
        System.arraycopy(aux, 0, pts, lo, k);
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.hypot(dx, dy);
    }
}

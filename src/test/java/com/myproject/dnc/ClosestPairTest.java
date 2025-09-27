package com.myproject.dnc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClosestPairTest {

    @Test
    void testSmall() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4),
                new ClosestPair.Point(7, 1),
                new ClosestPair.Point(2, 2)
        };
        double d = ClosestPair.closest(pts);
        assertEquals(Math.sqrt(8), d, 1e-6);
    }

    @Test
    void testCollinear() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(5, 0),
                new ClosestPair.Point(2, 0),
                new ClosestPair.Point(9, 0)
        };
        double d = ClosestPair.closest(pts);
        assertEquals(2.0, d, 1e-6);
    }

    @Test
    void testRandom() {
        ClosestPair.Point[] pts = new ClosestPair.Point[1000];
        for (int i = 0; i < pts.length; i++) {
            pts[i] = new ClosestPair.Point(Math.random() * 1000, Math.random() * 1000);
        }
        double d = ClosestPair.closest(pts);
        assertTrue(d >= 0);
    }

    @Test
    void testInvalid() {
        assertThrows(IllegalArgumentException.class, () -> ClosestPair.closest(new ClosestPair.Point[0]));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class RationalTest {
    
    public RationalTest() {
    }

    @Test
    public void testToDouble() {
        assertEquals(0.25, Rational.of(1, 4).toDouble());
    }

    @Test
    public void testToString() {
        assertEquals("1/4", Rational.of(1, 4).toString());
    }

    @Test
    public void testMediant() {
        Rational r1 = Rational.of(2, 3);
        Rational r2 = Rational.of(4, 5);
        Rational r3 = Rational.of(3, 4);
        assertEquals(r3, Rational.mediant(r1, r2));
    }

    @Test
    public void testCompareTo() {
        Rational r1 = Rational.of(2,3);
        Rational r2 = Rational.of(4,5);
        assertEquals(-1, r1.compareTo(r2));
        assertEquals(1, r2.compareTo(r1));
        assertEquals(0, r1.compareTo(r1));
    }
    
    @Test
    public void testAdd() {
        Rational r1 = Rational.of(2, 45);
        Rational r2 = Rational.of(4, 75);
        Rational r3 = Rational.of(22, 225);
        assertEquals(r3, r1.add(r2));
    }
    
    @Test
    public void testMul() {
        Rational r1 = Rational.of(2, 3);
        Rational r2 = Rational.of(4, 5);
        Rational r3 = Rational.of(8, 15);
        assertEquals(r3, r1.mul(r2));
    }

    @Test
    public void testDiv() {
        Rational r1 = Rational.of(2, 3);
        Rational r2 = Rational.of(4, 5);
        Rational r3 = Rational.of(5, 6);
        assertEquals(r3, r1.div(r2));
    }
    
    @Test
    public void testRationalofDouble() {
        assertEquals(Rational.ONE, Rational.of(1.0));
        assertEquals(Rational.of(1,2), Rational.of(0.5));
        assertEquals(Rational.of(2), Rational.of(2.0));
        Rational big = Rational.of(0x7fffffffL, 1L);
        assertEquals(big, Rational.of(big.toDouble()), "big");
        Rational small = Rational.of(1L, 0x80000000L);
        double smallDouble = small.toDouble();
        Rational smallRational = Rational.of(smallDouble);
        assertEquals(small, Rational.of(small.toDouble()), "small");
    }
    
    @Test
    public void testEgyptianFraction() {
        assertArrayEquals(new Rational[]{Rational.of(1,2), Rational.of(1,3)}, Rational.of(5,6).egyptianFraction());
        var expected = new Rational[]{Rational.of(1,2), Rational.of(1,7), Rational.of(1,75), Rational.of(1,16800)};
        assertArrayEquals(expected, Rational.of(21,32).egyptianFraction());
    }

    
}

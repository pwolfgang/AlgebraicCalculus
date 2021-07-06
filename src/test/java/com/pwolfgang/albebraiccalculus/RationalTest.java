/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus;

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
        assertEquals(0.25, new Rational(1, 4).toDouble());
    }

    @Test
    public void testToString() {
        assertEquals("1/4", new Rational(1, 4).toString());
    }

    @Test
    public void testMediant() {
        Rational r1 = new Rational(2, 3);
        Rational r2 = new Rational(4, 5);
        Rational r3 = new Rational(3, 4);
        assertEquals(r3, Rational.mediant(r1, r2));
    }

    @Test
    public void testCompareTo() {
        Rational r1 = new Rational(2,3);
        Rational r2 = new Rational(4,5);
        assertEquals(-1, r1.compareTo(r2));
        assertEquals(1, r2.compareTo(r1));
        assertEquals(0, r1.compareTo(r1));
    }
    
    @Test
    public void testAdd() {
        Rational r1 = new Rational(2, 3);
        Rational r2 = new Rational(4, 5);
        Rational r3 = new Rational(22, 15);
        assertEquals(r3, r1.add(r2));
    }
    
    @Test
    public void testMul() {
        Rational r1 = new Rational(2, 3);
        Rational r2 = new Rational(4, 5);
        Rational r3 = new Rational(8, 15);
        assertEquals(r3, r1.mul(r2));
    }

    @Test
    public void testDiv() {
        Rational r1 = new Rational(2, 3);
        Rational r2 = new Rational(4, 5);
        Rational r3 = new Rational(5, 6);
        assertEquals(r3, r1.div(r2));
    }

    
}

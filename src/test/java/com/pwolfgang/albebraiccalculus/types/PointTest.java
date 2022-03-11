/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class PointTest {
    
    public PointTest() {
    }

    @Test
    public void testGetX() {
    }

    @Test
    public void testGetY() {
    }

    @Test
    public void testMul() {
        Rational sqrt2 = Rational.of(Math.sqrt(2));
        var a = (sqrt2.mul(Rational.of(8)).sub(Rational.of(5))).div(Rational.of(12));
        var b = (sqrt2.mul(Rational.of(4)).add(Rational.of(5))).div(Rational.of(12));
        System.out.printf("a: %s%n", a);
        System.out.printf("b: %s%n", b);
        var p0 = new Point(a, b);
        var p1 = new Point(b, a);
        var p0Half = p0.mul(Rational.HALF);
        var p1Half = p1.mul(Rational.HALF);
        System.out.printf("p0Half: %s%n", p0Half);
        System.out.printf("p1Half: %s%n", p1Half);
        var p0HalfPlusP1Half = p0Half.add(p1Half);
        var sqrt2Over2 = sqrt2.mul(Rational.HALF);
        assertEquals(new Point(sqrt2Over2,sqrt2Over2),p0HalfPlusP1Half);
    }

    @Test
    public void testAdd_Point() {
    }

    @Test
    public void testAdd_Vector() {
    }

    @Test
    public void testSub() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void testHashCode() {
    }
    
}

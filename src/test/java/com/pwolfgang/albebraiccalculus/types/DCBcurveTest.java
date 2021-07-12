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
public class DCBcurveTest {
    
    public DCBcurveTest() {
    }

    @Test
    public void testR2() {
        System.out.println("testR2");
        var p0 = new Point(-1, 2);
        var p1 = new Point(4, 3);
        var p2 = new Point(5, 1);
        var curve = new DCBcurve(p0, p1, p2);
        for (int i = 0; i <= 4; i++) {
            System.out.println(curve.r(new Rational(i, 4)));
        }
    }
    
    @Test
    public void testR3_3() {
        System.out.println("testR3_3");
        var p0 = new Point(1, 0, 1);
        var p1 = new Point(1000, 552, 1000);
        var p2 = new Point(1000, 1000, 552);
        var p3 = new Point(1, 1, 0);
        var curve = new DCBcurve(p0, p1, p2, p3);
        var lambda = new Rational(1,2);
        var r = curve.r3(lambda);
        System.out.printf("r= %.9f%n", r.getX().toDouble());
        assertEquals(new Point(1000, 707, 707), r);
    }

    @Test
    public void testR3_4() {
        System.out.println("testR3_4");
        var p0 = new Point(1, 0, 1);
        var p1 = new Point(100000000,  55228475, 100000000);
        var p2 = new Point(100000000, 100000000,  55228475);
        var p3 = new Point(1, 1, 0);
        var curve = new DCBcurve(p0, p1, p2, p3);
        var lambda = new Rational(1,2);
        var r = curve.r3(lambda);
        System.out.printf("r= %s%n", Double.toString(r.getX().toDouble()));
        System.out.println("sqrt(2)/2 = " + Math.sqrt(2.0)/2.0);
        assertEquals(new Point(100000000000L, 70710678125L, 70710678125L), r);
    }

    @Test
    public void testR3_5() {
        System.out.println("testR3_5");
        double sqrt2over2 = Math.sqrt(2.0)/2.0;
        double a = (Math.sqrt(2)*4 - 4.0)/3.0;
        Rational rationalA = new Rational(a);
        var p0 = new Point(1, 0, 1);
        var p1 = new Point(rationalA, Rational.ONE);
        var p2 = new Point(Rational.ONE, rationalA);
        var p3 = new Point(1, 1, 0);
        var curve = new DCBcurve(p0, p1, p2, p3);
        var lambda = new Rational(1,2);
        var r = curve.r3(lambda);
        System.out.printf("r= %s%n", Double.toString(r.getX().toDouble()));
        System.out.println("sqrt(2)/2 = " + Math.sqrt(2.0)/2.0);
        Rational ratSqrt2Over2 = new Rational(sqrt2over2);
        System.out.println("ratSqrt2Over2 = " + ratSqrt2Over2.toDouble());
        assertEquals(new Point(ratSqrt2Over2,ratSqrt2Over2), r);
    }

}
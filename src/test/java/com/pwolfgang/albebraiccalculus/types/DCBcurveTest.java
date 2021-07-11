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
        var p0 = new Point(1, 0, 1);
        var p1 = new Point(100000000,  55228475, 100000000);
        var p2 = new Point(100000000, 100000000,  55228475);
        var p3 = new Point(1, 1, 0);
        var curve = new DCBcurve(p0, p1, p2, p3);
        var lambda = new Rational(1,2);
        var r = curve.r3(lambda);
        System.out.printf("r= %.9f%n", r.getX().toDouble());
        assertEquals(new Point(100000000, 707106781, 707106781), r);
    }

}

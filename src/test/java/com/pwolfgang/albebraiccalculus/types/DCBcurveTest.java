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
    public void testR3() {
        var p0 = new Point(-1,0);
        var p1 = new Point(new Rational(-1, 1), new Rational(-1, 3));
        var p2 = new Point(new Rational(-2, 3), new Rational(-2, 3));
        var p3 = new Point(0, 0);
        var curve = new DCBcurve(p0, p1, p2, p3);
        for (int i = 0; i <= 4; i++) {
            System.out.println(curve.r(new Rational(i, 4)));
        }
    }
    
}

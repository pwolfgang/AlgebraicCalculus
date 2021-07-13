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
public class OSideTest {
    
    public OSideTest() {
    }

    @Test
    public void testSignedArea() {
        Point p1 = new Point(1,3);
        Point p2 = new Point(-5,4);
        assertEquals(new Rational(19,2), new OSide(p1,p2).area());
    }
    
    @Test
    public void testInside() {
        var a = new Point(-3, -1);
        var b = new Point(9, 3);
        var c = new Point(3, 1);
        var d = new Point(-6, -2);
        var e = new Point(12, 4);
        var f = new Point(2, 4);
        var oside = new OSide(a, b);
        assertTrue(oside.isInside(c), "c is inside ab");
        assertFalse(oside.isInside(d), "d is not inside ab");
        assertFalse(oside.isInside(e), "e is not inside ab");
        assertFalse(oside.isInside(f), "f is not inside ab");
    }
    
}

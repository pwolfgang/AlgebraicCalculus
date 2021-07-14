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
    
    @Test
    public void testIsRightOf() {
        var a = new Point(5, -3);
        var b = new Point(-5, 3);
        var e = new Point(2, 1);
        var d = new Point(-4, -5);
        var c = new Point(0, 0);
        var oside = new OSide(a, b);
        assertTrue(oside.isRightOf(e), "e is right of ab");
        assertTrue(oside.isStrictlyRightOf(e), "e is strictly right of ab");
        assertTrue(oside.isRightOf(c), "c is right of ab");
        assertFalse(oside.isStrictlyRightOf(c), "c is not strictly right of ab");
        assertFalse(oside.isRightOf(d), "d is not right of ab");
        assertFalse(oside.isStrictlyRightOf(d), "d is not strictly right of ab");
    }
    
    @Test
    public void testIsLeftOf() {
        var a = new Point(5, -3);
        var b = new Point(-5, 3);
        var e = new Point(2, 1);
        var d = new Point(-4, -5);
        var c = new Point(0, 0);
        var oside = new OSide(a, b);
        assertFalse(oside.isLeftOf(e), "e is not left of ab");
        assertFalse(oside.isStrictlyLeftOf(e), "e is not strictly left of ab");
        assertTrue(oside.isLeftOf(c), "c is left of ab");
        assertFalse(oside.isStrictlyLeftOf(c), "c is not strictly left of ab");
        assertTrue(oside.isLeftOf(d), "d is left of ab");
        assertTrue(oside.isStrictlyLeftOf(d), "d is strictly left of ab");
    }
    
    @Test
    public void testLeftRight() {
        var c = new Point(0,0);
        var d = new Point(6,6);
        var a = new Point(1,2);
        var b = new Point(-2, 7);
        var cd = new OSide(c,d);
        var ab = new OSide(a,b);
        assertTrue(cd.isLeftOf(a), "cd is left of a");
        assertFalse(cd.isRightOf(a), "cd is not right of a");
        assertTrue(cd.isLeftOf(b), "cd is left of b");
        assertFalse(cd.isRightOf(b), "cd is not right of b");
        assertTrue(ab.isLeftOf(c), "ab is left of c");
        assertFalse(ab.isRightOf(c), "ab is nor right of c");
        assertTrue(ab.isRightOf(d), "ab is right of d");
        assertFalse(ab.isLeftOf(d), "ab is left of d");

    }
    
    @Test
    public void testCrosses() {
        var a = new Point(4, -1);
        var b = new Point(-1, 5);
        var c = new Point(0, 0);
        var d = new Point(6, 6);
        var e = new Point(3, 3);
        var f = new Point(0, 7);
        var g = new Point(1, 2);
        var h = new Point(-2, 7);
        var ab = new OSide(a, b);
        var cd = new OSide(c, d);
        var ef = new OSide(e, f);
        var gh = new OSide(g, h);
        assertTrue(OSide.crosses(ab, cd), "ab crosses cd");
        assertTrue(OSide.crosses(cd, ef), "cd crosses ef");
        assertFalse(OSide.crosses(cd, gh), "gh does not cross cd");
    }
    
    @Test
    public void testCrossingNumberTest1() {
        var a = new Point(10, -2);
        var b = new Point(11, -3);
        var c = new Point(17, 0);
        var d = new Point(0, -5);
        var ab = new OSide(a, b);
        var cd = new OSide(c, d);
        assertEquals(-1, ab.crossingNumber(cd));
        assertEquals(1, cd.crossingNumber(ab));
    }

    @Test
    public void testCrossingNumberTest2() {
        var a = new Point(3, 1);
        var b = new Point(5, -1);
        var c = new Point(0, -1);
        var d = new Point(5, 3);
        var ab = new OSide(a, b);
        var cd = new OSide(c, d);
        assertEquals(0, ab.crossingNumber(cd));
        assertEquals(0, cd.crossingNumber(ab));
    }

    @Test
    public void testCrossingNumberTest3() {
        var a = new Point(-6, 0);
        var b = new Point(-1, 0);
        var c = new Point(0, 3);
        var d = new Point(-4, -1);
        var ab = new OSide(a, b);
        var cd = new OSide(c, d);
        assertEquals(-1, ab.crossingNumber(cd));
        assertEquals(1, cd.crossingNumber(ab));
    }
    
    @Test
    public void linesShouldNotBeAdjacent() {
        var a = new Point(0, 0);
        var b = new Point(2, 2);
        var c = new Point(3, 3);
        var d = new Point(-1, -1);
        assertThrows(IllegalArgumentException.class, () -> {
            var ab = new OSide(a, b);
            var cd = new OSide(c, d);
            int i = ab.crossingNumber(cd);
        });
    }
}

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
public class COPSTest {
    
    public COPSTest() {
    }

    @Test
    public void testArea() {
        System.out.println("Test 1");
        Point a = new Point(3, -1);
        Point b = new Point(0, 2);
        Point c = new Point(-1, 1);
        Point d = new Point(0, -1);
        COPS abcd = new COPS(Arrays.asList(a, b, c, d));
        COPS abcbbc = new COPS(Arrays.asList(a, b, c, b, b,c));
        verifyArea(abcd, new Rational(6));
        verifyArea(abcbbc, new Rational(3));
        System.out.println();
    }
    
    @Test
    public void testArea2() {
        System.out.println("Test 2");
        var c = new Point(0,0);
        var d = new Point(6,6);
        var a = new Point(1,2);
        var b = new Point(-2, 7);
        var cda = new COPS(c, d, a);
        var cdb = new COPS(c, d, b);
        var abc = new COPS(a, b, c);
        var abd = new COPS(a, b, d);
        verifyArea(cda, new Rational(3));
        verifyArea(cdb, new Rational(27));
        verifyArea(abc, new Rational(11,2));
        verifyArea(abd, new Rational(-37, 2));
        System.out.println();
    }
    
    private void verifyArea(COPS ops, Rational a) {
        var area = ops.area();
        assertEquals(a, area);
    }
    
}

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
public class LineTest {
    
    public LineTest() {
    }
    
    @Test
    public void testLine() {
        Line l1 = new Line(12, -4, 8);
        Line l2 = new Line(3, -1, 2);
        assertEquals(l2, l1);
    }
    
    @Test
    public void testLine2() {
        Line l1 = new Line(Rational.of(1,2), Rational.of(1,3), Rational.of(1));
        Line l2 = new Line(Rational.of(1), Rational.of(2,3), Rational.of(2));
        assertEquals(l2, l1);
    }
    
    @Test
    public void testJoin() {
        Point p1 = new Point(2, 1);
        Point p2 = new Point(6, 3);
        Line l1 = new Line(0, -2, 4);
        assertEquals(l1, Line.join(p1, p2));
        Point p3 = new Point(5, 1);
        Line l3 = new Line(-1, 0, 1);
        assertEquals(l3, Line.join(p1, p3));
        Point p4 = new Point(6, 6);
        Line l4 = new Line(-6, 1, 0);
        assertEquals(l4, Line.join(p2, p4));
    }
    
    @Test
    public void testJoin2() {
        var p1 = new Point(1, -1);
        var p2 = new Point(2, 3);
        var l1 = new Line(-5, 4, -1);
        assertEquals(l1, Line.join(p1, p2));
    }
    
    @Test
    public void testPassesThrough() {
        Line l1 = new Line(0, -2, 4);
        Point p1 = new Point(4, 2);
        assertTrue(l1.passesThrough(p1),l1 + " passes through " + p1);
        Point p2 = new Point(3, 2);
        assertFalse(l1.passesThrough(p2), l1 + "does not pass through " + p2);
    }
    
    @Test 
    public void pointSlopeTest() {
        System.out.println("point slope test");
        var p = new Point(-1, 0);
        var m = Rational.of(3);
        var line = Line.pointSlope(p, m);
        assertEquals(new Line(3, 3, -1), line);
    }
    
    @Test
    public void slopeTest() {
        var p = new Point(-1, 0);
        var a = new Point(-2, -2);
        var b = new Point(0, 4);
        var c = new Point(3, -2);
        var d = new Point(-4, 0);
        var e = new Point(3, 2);
        assertEquals(Rational.of(2), new Line(a, p).slope());
        assertEquals(Rational.of(4), new Line(b, p).slope());
        assertEquals(Rational.of(-1,2), new Line(c, p).slope());
        assertEquals(Rational.of(0), new Line(d, p).slope());
        assertEquals(Rational.of(1,2), new Line(e, p).slope());
        assertEquals(Rational.of(2), new Line(p, a).slope());
    }
    
    @Test
    public void findLine() {
        var p = new Point(5, 3, 4);
        var q = new Point(5, -1, 7);
        var l = Line.join(p, q);
        System.out.println(l);
                
    }
    
    @Test
    public void paramCircle() {
        var p0 = new Point(-1, 0);
        var p1 = new Point(Rational.ZERO, Rational.of(2,5));
        var line = new Line(p0, p1);
        System.out.println(line);
    }
    
}

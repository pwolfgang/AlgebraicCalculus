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
        Line l1 = new Line(new Rational(1,2), new Rational(1,3), new Rational(1));
        Line l2 = new Line(new Rational(1), new Rational(2,3), new Rational(2));
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
        System.out.println(Line.join(p1, p2));
    }
    
    @Test
    public void testPassesThrough() {
        Line l1 = new Line(0, -2, 4);
        Point p1 = new Point(4, 2);
        assertTrue(l1.passesThrough(p1),l1 + " passes through " + p1);
        Point p2 = new Point(3, 2);
        assertFalse(l1.passesThrough(p2), l1 + "does not pass through " + p2);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus;

import org.junit.Test;
import static org.junit.Assert.*;

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
    public void lineTest2() {
        Line l1 = new Line(new Rational(1,2), new Rational(1,3), new Rational(1));
        Line l2 = new Line(new Rational(1), new Rational(2,3), new Rational(2));
        assertEquals(l2, l1);
    }
    
    @Test
    public void testJoin() {
        Point p1 = new Point(2, 1);
        Point p2 = new Point(6, 3);
        Line l1 = new Line(-2, 4, 0);
        assertEquals(l1, Line.join(p1, p2));
    }
    
    @Test
    public void testPassesThrough() {
        Line l1 = new Line(-2, 4, 0);
        Point p1 = new Point(4, 2);
        assertTrue(l1.passesThrough(p1));
        Point p2 = new Point(3, 2);
        assertFalse(l1.passesThrough(p2));
    }
    
    

    
}

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
public class VectorTest {
    
    public VectorTest() {
    }

    @Test
    public void vector1_2() {
        var p1 = new Point(1,1);
        var p2 = new Point(3,5);
        assertEquals(new Vector(2, 4), new Vector(p1, p2));
    }
    
    @Test
    public void vectorTest2() {
        var p1 = new Point(Rational.of(2,3), Rational.ONE);
        var p2 = new Point(5, 3);
        assertEquals(new Vector(Rational.of(13, 3), Rational.of(2)), new Vector(p1, p2));
    }
    
    @Test
    public void testDot() {
        var p1 = new Point(1, 8);
        var p2 = new Point(9, 0);
        var p3 = new Point(6, 13);
        var v1 = new Vector(p1, p2);
        var v2 = new Vector(p1, p3);
        assertEquals(Rational.ZERO, v1.dot(v2));
    }
    
    @Test
    public void testCross() {
        var p1 = new Point(1, 8);
        var p2 = new Point(9, 0);
        var p3 = new Point(9, 12);
        var p4 = new Point(16, 5);
        var v1 = new Vector(p1, p2);
        var v2 = new Vector(p3, p4);
        assertEquals(Rational.ZERO, v1.cross(v2));        
    }
    
}

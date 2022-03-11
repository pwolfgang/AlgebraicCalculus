/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import com.pwolfgang.albebraiccalculus.datastructures.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class OPSTest {
    
    public OPSTest() {
    }

    @Test
    public void testArea() {
        Point a = new Point(3, -1);
        Point b = new Point(0, 2);
        Point c = new Point(-1, -1);
        Point d = new Point(0, -1);
        OPS ab = new OPS(a, b);
        OPS cd = new OPS(c, d);
        OPS abcd = new OPS(a, b, c, d);
        OPS abcbbc = new OPS(a, b, c, b, b,c);
        assertEquals(Rational.of(3), ab.area());
        assertEquals(Rational.of(1,2), cd.area());
        assertEquals(Rational.of(9,2), abcd.area(), abcd.toString());
        assertEquals(Rational.of(4), abcbbc.area(), abcbbc.toString());
    }
    
    @Test
    public void testQuadraticOPS() {
        List<Point> p1to5 = new List<>();
        for (int i = 1; i <= 5; i++) {
            p1to5.add(new Point(i-1, i*i));
        }
        OPS ops1to5 = new OPS(p1to5);
        assertEquals(Rational.of(8), ops1to5.area());
        
        List<Point> p10to13 = new List<>();
        for (int i = 10; i <= 13; i++) {
            p10to13.add(new Point(i-1, i*i));
        }
        OPS ops10to13 = new OPS(p10to13);
        assertEquals(Rational.of(329,2), ops10to13.area());
    }
    
}

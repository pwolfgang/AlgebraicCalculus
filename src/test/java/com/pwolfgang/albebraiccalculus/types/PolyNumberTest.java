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
public class PolyNumberTest {
    
    public PolyNumberTest() {
    }

    @Test
    public void testSub() {
        var p1 = new PolyNumber(3, 2, 1);
        var p2 = new PolyNumber(4, 5);
        var p3 = new PolyNumber(-1, -3, 1);
        assertEquals(p3, p1.sub(p2));
    }

    @Test
    public void testMul_PolyNumber() {
        PolyNumber p = new PolyNumber(2, 3);
        PolyNumber q = new PolyNumber(4, 5, 6);
        PolyNumber pq = new PolyNumber(8, 22, 27, 18);
        assertEquals(pq, p.mul(q));
    }
    
    @Test
    public void testEval_Rational() {
        PolyNumber p = new PolyNumber(3, 2, 1);
        Rational r1 = Rational.ONE;
        Rational r2 = new Rational(2);
        Rational r5 = new Rational(5);
        Rational r6 = new Rational(6);
        Rational r11 = new Rational(11);
        Rational r38 = new Rational(38);
        assertEquals(r6, p.eval(r1));
        assertEquals(r11, p.eval(r2));
        assertEquals(r38, p.eval(r5));
    }
    
    @Test
    public void testEval_PolyNumber() {
        var p1 = new PolyNumber(3, 2, 1);
        var p2 = new PolyNumber(4, 5);
        var p3 = new PolyNumber(27, 50, 25);
        assertEquals(p3, p1.eval(p2));
    }
    
    @Test
    public void testD() {
        var p1 = new PolyNumber(6, 5, 4);
        var p2 = new PolyNumber(5, 8);
        assertEquals(p2, p1.D());
        assertEquals(new PolyNumber(0), new PolyNumber(5).D());
    }

    @Test
    public void testS() {
        var p1 = new PolyNumber(0, 5, 4);
        var p2 = new PolyNumber(5, 8);
        assertEquals(p1, p2.S());
    }
    
    @Test
    public void testToString() {
        assertEquals("α + 2α^2", new PolyNumber(0, 1, 2).toString());
        assertEquals("3 + 2α + α^2", new PolyNumber(3, 2, 1).toString());
    }
    
    @Test
    public void p6p3p3() {
        var p = new PolyNumber(2, -3, 0, 0, 12);
        var q = new PolyNumber(-1, 5, -8, 1);
        System.out.println(p.mul(q));
        
    }
    
    @Test
    public void p6p2p8() {
        var p = new PolyNumber(4, -1);
        var q = new PolyNumber(0, 0, 3);
        System.out.println(p.eval(q));
        System.out.println(q.eval(p));
    }
    
    @Test
    public void p6p2p9() {
        var p = new PolyNumber(-2, -1, 2, 3, 1);
        System.out.println(p.D());
        System.out.println(p.S());
    }
   
}

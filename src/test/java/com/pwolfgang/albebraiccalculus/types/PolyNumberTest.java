/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class PolyNumberTest {
    
    public PolyNumberTest() {
    }
    
    @BeforeEach
    public void init() {
        try {
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new InternalError("VM does not support UTF-8");
        }
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
        Rational r2 = Rational.of(2);
        Rational r5 = Rational.of(5);
        Rational r6 = Rational.of(6);
        Rational r11 = Rational.of(11);
        Rational r38 = Rational.of(38);
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
    
    @Test
    public void testDiv() {
        var a = new PolyNumber(2, 7, 2, -3);
        var b = new PolyNumber(1, 3);
        var c = new PolyNumber(2, 1, -1);
        assertEquals(c, a.divWithRemainder(b)[0]);
        assertEquals(b, a.divWithRemainder(c)[0]);
    }
    
    @Test void testDiv2() {
        var a = new PolyNumber(12, 8, -7, -2, 1);
        var b = new PolyNumber(4, 0, -1);
        var c = new PolyNumber(3, 2, -1);
        assertEquals(c, a.divWithRemainder(b)[0]);
        assertEquals(b, a.divWithRemainder(c)[0]);
    }
    
    @Test
    public void testDiv3(){
        var a = new PolyNumber(1, 0, 0, 0, 0, 0, -1);
        var b = new PolyNumber(1, -1, 1);
        var c = new PolyNumber(1, 1, 0, -1, -1);
        assertEquals(c, a.divWithRemainder(b)[0]);
        assertEquals(b, a.divWithRemainder(c)[0]);
    }
    
    @Test
    public void testDiv4() {
        System.out.println("********testDiv4********");
        var a = new PolyNumber(1, 0, 0, 0, 0, -1);
        var b = new PolyNumber(1, -1, 1);
        System.out.println(a + " div " + b);
        var qr = a.divWithRemainder(b);
        var q = qr[0];
        var r = qr[1];
        System.out.printf("q: %s%n", q.toString());
        System.out.printf("r: %s%n", r.toString());
        var qMulB = q.mul(b);
        var qMulBPlusR = qMulB.add(r);
        System.out.printf("q × b: %s%n",qMulB);
        System.out.printf("q × b + r: %s%n",qMulBPlusR);
        System.out.println();
        assertEquals(a, qMulBPlusR);
        
    }
    
    @Test
    public void testDiv5() {
        var f = new PolyNumber(3, 1, 2, 4);
        var g = new PolyNumber(1, 2);
        PolyNumber[] qr = f.divWithRemainder(g);
        var q = qr[0];
        var r = qr[1];
        System.out.printf("q: %s%n", q.toString());
        System.out.printf("r: %s%n", r.toString());
        var qMulB = q.mul(g);
        var qMulBPlusR = qMulB.add(r);
        System.out.printf("q × b: %s%n",qMulB);
        System.out.printf("q × b + r: %s%n",qMulBPlusR);
        System.out.println();
        assertEquals(f, qMulBPlusR);
    }
    
    @Test
    public void testGCD() {
        var a = new PolyNumber(1, 1, 1, 1, 1, 1, 1);
        var b = new PolyNumber(1, 1, 1, 0, 1);
        System.out.printf("a: %s%n", a.toString());
        System.out.printf("b: %s%n", b.toString());
        var g = PolyNumber.gcd(a,b);
        System.out.printf("gcd: %s%n", g.toString());
        assertEquals(PolyNumber.ONE, g);
    }
    
    @Test
    public void testGCD2() {
        var f = new PolyNumber(-2, -3, -2, 0, 1);
        var g = new PolyNumber(1, 4, 4, 1);
        var gcd = PolyNumber.gcd(f, g);
        assertEquals(new PolyNumber(Rational.of(11,25), Rational.of(11,25)), gcd);
    }
    
    @Test
    public void testDiv6() {
        System.out.println("testDiv6");
        var a = new PolyNumber(1, 0, 0, 0, 0, 0, -1);
        var b = new PolyNumber(1, -1, 1);
        PolyNumber[] qr = a.divWithRemainder(b);
        var q1 = qr[0];
        System.out.printf("a: %s%n", a);
        System.out.printf("a.div(b): %s%n", q1);
        System.out.printf("q1.mul(b): %s%n", q1.mul(b));
    }
    
    
    @Test
    public void testGCD22() {
        var a = new PolyNumber(1, 1, 1, 1, 1, 1, 1);
        var b = new PolyNumber(1, 1, 1, 0, 1);
        System.out.printf("a: %s%n", a.toString());
        System.out.printf("b: %s%n", b.toString());
        var g = PolyNumber.gcd(a,b);
        System.out.printf("gcd: %s%n", g.toString());
        assertEquals(PolyNumber.ONE, g);
    }
    
    @Test
    public void testGCD222() {
        var f = new PolyNumber(-2, -3, -2, 0, 1);
        var g = new PolyNumber(1, 4, 4, 1);
        var gcd = PolyNumber.gcd(f, g);
        assertEquals(new PolyNumber(Rational.of(11,25), Rational.of(11,25)), gcd);
    }

    @Test
    public void testDelta() {
        var p = new PolyNumber(0,0,0,1);
        var q = new PolyNumber(1,3,3);
        assertEquals(q, p.delta());
    }
    
    @Test
    public void testDel() {
        var p = new PolyNumber(0,0,0,1);
        var q = new PolyNumber(1, -3, 3);
        assertEquals(q, p.del());
    }
    
    @Test
    public void testDeltaFallingPowers() {
        var alpha = new PolyNumber(0,1);
        var alphaMinusOne = new PolyNumber(-1, 1);
        var alphaMinusTwo = new PolyNumber(-2, 1);
        var p = alpha.mul(alphaMinusOne).mul(alphaMinusTwo);
        var q = alpha.mul(alphaMinusOne).mul(3);
        assertEquals(q, p.delta());
        var r = alphaMinusOne.mul(alphaMinusTwo).mul(3);
        assertEquals(r, p.del());
    }
    
    @Test
    public void testDeltaRisingPowers() {
        var alpha = new PolyNumber(0,1);
        var alphaPlusOne = alpha.add(PolyNumber.ONE);
        var alphaPlusTwo = alpha.add(new PolyNumber(2));
        var p = alpha.mul(alphaPlusOne).mul(alphaPlusTwo);
        var q = alphaPlusOne.mul(alphaPlusTwo).mul(3);
        var r = alpha.mul(alphaPlusOne).mul(3);
        assertEquals(q, p.delta());
        assertEquals(r, p.del());       
    }
    
    @Test
    public void testFromPoints2() {
        var p1 = new Point(-1,2);
        var p2 = new Point(5,4);
        var q = new PolyNumber(Rational.of(7,3), Rational.of(1,3));
        assertEquals(q, PolyNumber.fromPoints(p1, p2));
    }
    
    @Test
    public void testFromPoints3() {
        System.out.println("Parabola");
        var p1 = new Point(-3, 4);
        var p2 = new Point(0, -2);
        var p3 = new Point(1, 5);
        var poly = PolyNumber.fromPoints(p1, p2, p3);
        System.out.println(poly);
        assertEquals(Rational.of(4), poly.eval(-3));
        assertEquals(Rational.of(-2), poly.eval(0));
        assertEquals(Rational.of(5), poly.eval(1));
    }
    
    @Test
    public void testFromPoints4() {
        System.out.println("cubic");
        var p1 = new Point(-1,2);
        var p2 = new Point(0,0);
        var p3 = new Point(1,4);
        var p4 = new Point(2, 7);
        var poly = PolyNumber.fromPoints(p1, p2, p3, p4);
        System.out.println(poly);
        assertEquals(Rational.of(2), poly.eval(-1));
        assertEquals(Rational.ZERO, poly.eval(0));
        assertEquals(Rational.of(4), poly.eval(1));
        assertEquals(Rational.of(7), poly.eval(2));
    }
    
    @Test
    public void testFromPointsCircle() {
        System.out.println("circle?");
        var p1 = new Point(0, 1);
        var p2 = new Point(Rational.of(3,5), Rational.of(4,5));
        var p3 = new Point(Rational.of(4,5), Rational.of(3,5));
        var p4 = new Point(1, 0);
        var poly = PolyNumber.fromPoints(p1,p2,p3,p4);
        System.out.println(poly);
    }
    
    @Test
    public void testEGCD2() {
        System.out.println("Extended PolyNumber GCD");
        PolyNumber a = new PolyNumber(-2, -3, -2, 0, 1);
        PolyNumber b = new PolyNumber(1, 4, 4, 1);
        PolyNumber[] egcd = PolyNumber.extendedGCD(a, b);
        PolyNumber gcd = egcd[0];
        PolyNumber s = egcd[1];
        PolyNumber t = egcd[2];
        System.out.printf("%s = (%s)×(%s) + (%s)×(%s)%n",
                gcd.toString(), s.toString(), a.toString(), t.toString(), b.toString());
        assertEquals(new PolyNumber(Rational.of(11,25), Rational.of(11,25)), gcd);
        PolyNumber sExpected = new PolyNumber(Rational.of(-7, 25), Rational.of(-1, 10));
        assertEquals(sExpected, s);
        PolyNumber tExpected = new PolyNumber(Rational.of(-3, 25), Rational.of(-3,25), Rational.of(1, 10));
        System.out.printf("tExpected: %s%n", tExpected.toString());
        assertEquals(tExpected, t);
    }
    
    @Test
    public void testSqrt() {
        System.out.println("Test sqrt");
        PolyNumber q = new PolyNumber(1,1);
        var itr = q.sqrt().iterator();
        int index = 0;
        PolyNumber alphaN = PolyNumber.ALPHA;
        PolyNumber p = PolyNumber.ONE;
        while (index++ < 5 && itr.hasNext()) {
            var c = itr.next();
            p = p.add(c);
        }
        var expected = new PolyNumber(Rational.ONE, Rational.of(1,2), 
                Rational.of(-1, 8), Rational.of(1,16), Rational.of(-5,128), 
                Rational.of(7,256));
        System.out.printf("Sqrt(%s) is %s + ...%n", q, p);
        assertEquals(expected, p);
    }

    @Test
    public void testSqrt2() {
        System.out.println("Test sqrt2");
        PolyNumber q = new PolyNumber(1,1,-1);
        var itr = q.sqrt().iterator();
        int index = 0;
        PolyNumber alphaN = PolyNumber.ALPHA;
        PolyNumber p = PolyNumber.ONE;
        while (index++ < 5 && itr.hasNext()) {
            var c = itr.next();
            p = p.add(c);
        }
        var expected = new PolyNumber(Rational.ONE, Rational.of(1,2), 
                Rational.of(-5, 8), Rational.of(5,16), Rational.of(-45,128), 
                Rational.of(95,256));
        System.out.printf("Sqrt(%s) is %s + ...%n", q, p);
        assertEquals(expected, p);
    }

    @Test
    public void testSqrt3() {
        System.out.println("Test sqrt3");
        PolyNumber q = new PolyNumber(1,2,1);
        var itr = q.sqrt().iterator();
        int index = 0;
        PolyNumber alphaN = PolyNumber.ALPHA;
        PolyNumber p = PolyNumber.ONE;
        while (index++ < 5 && itr.hasNext()) {
            var c = itr.next();
            p = p.add(c);
        }
        var expected = new PolyNumber(1,1); 
        System.out.printf("Sqrt(%s) is %s%n", q, p);
        assertEquals(expected, p);
    }
    
    @Test
    public void testDivPolyseries() {
        System.out.println("Test Div Polyseries");
        PolyNumber x = new PolyNumber(1, 0, -1);
        PolyNumber y = new PolyNumber(1, 0, 1);
        System.out.printf("x: %s%n", x);
        System.out.printf("y: %s%n", y);
        var itr = x.div(y).iterator();
        for (int i = 0; i < 5 && itr.hasNext(); i++) {
            System.out.println(itr.next());
        }
    }
    
    @Test
    public void testTruncateShortens() {
        var p = new PolyNumber(1, 2, 3, 4, 5);
        var tP = p.truncate(2);
        var q = new KPoly(2, new Rational[]{Rational.of(1), Rational.of(2), Rational.of(3)});
        assertEquals(q,tP);
    }
        @Test
    public void testTruncateExpands() {
        var p = new PolyNumber(1, 2);
        var tP = p.truncate(2);
        var q = new KPoly(2, new Rational[]{Rational.of(1), Rational.of(2), Rational.ZERO});
        assertEquals(q,tP);
    }

}

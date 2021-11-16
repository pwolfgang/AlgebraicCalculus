/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import com.pwolfgang.albebraiccalculus.SqMatrix;
import com.pwolfgang.albebraiccalculus.Pascal;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class DCBcurveTest {
    
    public DCBcurveTest() {
    }
    
    @BeforeEach
    public void init() {
        try {
            PrintStream p = new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8");
            System.setOut(p);          
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        
    }


    @Test
    public void testR2() {
        System.out.println("testR2");
        var p0 = new Point(-1, 2);
        var p1 = new Point(4, 3);
        var p2 = new Point(5, 1);
        var curve = new DCBcurve(p0, p1, p2);
        Point[] expected = new Point[] {
            new Point(-1, 2),
            new Point(new Rational(5,4), new Rational(37, 16)),
            new Point(new Rational(3), new Rational(9,4)),
            new Point(new Rational(17,4), new Rational(29,16)),
            new Point(5,1)
        };
        for (int i = 0; i <= 4; i++) {
            assertEquals(expected[i], curve.r(new Rational(i, 4)));
        }
    }
    
    @Test
    public void testR3_3() {
        System.out.println("testR3_3");
        var p0 = new Point(1, 0, 1);
        var p1 = new Point(1000, 552, 1000);
        var p2 = new Point(1000, 1000, 552);
        var p3 = new Point(1, 1, 0);
        var curve = new DCBcurve(p0, p1, p2, p3);
        var lambda = new Rational(1,2);
        var r = curve.r(lambda);
        assertEquals(new Point(1000, 707, 707), r);
    }

    @Test
    public void testR3_4() {
        System.out.println("testR3_4");
        var p0 = new Point(1, 0, 1);
        var p1 = new Point(100000000,  55228475, 100000000);
        var p2 = new Point(100000000, 100000000,  55228475);
        var p3 = new Point(1, 1, 0);
        var curve = new DCBcurve(p0, p1, p2, p3);
        var lambda = new Rational(1,2);
        var r = curve.r(lambda);
        assertEquals(new Point(100000000000L, 70710678125L, 70710678125L), r);
    }

    @Test
    public void testR3_5() {
        System.out.println("testR3_5");
        double sqrt2over2 = Math.sqrt(2.0)/2.0;
        Rational rationalA = (new Rational(Math.sqrt(2)).mul(new Rational(4))).sub(new Rational(4)).div(new Rational(3));
        var p0 = new Point(1, 0, 1);
        var p1 = new Point(rationalA, Rational.ONE);
        var p2 = new Point(Rational.ONE, rationalA);
        var p3 = new Point(1, 1, 0);
        var curve = new DCBcurve(p0, p1, p2, p3);
        var lambda = new Rational(1,2);
        var r = curve.r(lambda);
        Rational ratSqrt2Over2 = new Rational(sqrt2over2);
        assertEquals(new Point(ratSqrt2Over2,ratSqrt2Over2), r);
    }

    @Test
    public void testR3_6() {
        System.out.println("testR3_6");
        double sqrt2over2 = Math.sqrt(2.0)/2.0;
        double a = (Math.sqrt(2)*4-4)/3.0;
        Rational rationalA = new Rational(a);
        Rational A = (new Rational(Math.sqrt(2)).mul(new Rational(4)).sub(new Rational(4))).div(new Rational(3));
        var p0 = new Point(1, 0, 1);
        var p1 = new Point(rationalA, Rational.ONE);
        var p2 = new Point(Rational.ONE, rationalA);
        var p3 = new Point(1, 1, 0);
        var curve = new DCBcurve(p0, p1, p2, p3);
        var lambda = new Rational(2,5);
        var r = curve.r(lambda);
        System.out.println("r = " + r);
        System.out.println("x = " + r.getX().toDouble() + " y = " + r.getY().toDouble());
        PolyNumber[] pn = curve.toPolyNumber();
        System.out.println("pnX = " + pn[0]);
        System.out.println("pnY = " + pn[1]);
    }
    
    @Test
    public void testToPolyNumber2() {
        var p0 = new Point(-1,2);
        var p1 = new Point(4,3);
        var p2 = new Point(5,1);
        var curve = new DCBcurve(p0, p1, p2);
        PolyNumber[] pn = curve.toPolyNumber();
        var pnX = new PolyNumber(-1, 10, -4);
        var pnY = new PolyNumber(2, 2, -3);
        assertEquals(pnX, pn[0]);
        assertEquals(pnY, pn[1]);   
    }
    
    @Test
    public void testToPolyNumber3() {
        var p0 = new Point(-1, 0);
        var p1 = new Point(Rational.ONE.neg(), new Rational(-1, 3));
        var p2 = new Point(new Rational(-2,3), new Rational(-2,3));
        var p3 = new Point(0,0);
        var curve = new DCBcurve(p0, p1, p2, p3);
        var pnX = new PolyNumber(-1, 0, 1);
        var pnY = new PolyNumber(0, -1, 0, 1);
        PolyNumber[] pn = curve.toPolyNumber();
        assertEquals(pnX, pn[0]);
        assertEquals(pnY, pn[1]);
    }
    
    @Test
    public void testFromPolyNumber2() {
        var aX = new PolyNumber(new long[]{1, 1});
        var aY = new PolyNumber(new long[]{1, 0, -1});
        var p0 = new Point(1, 1);
        var p2 = new Point(2, 0);
        var p1 = new Point(new Rational(3, 2), Rational.ONE);
        var c = new DCBcurve(p0, p1, p2);
        assertEquals(c, new DCBcurve(new PolyNumber[]{aX, aY}));
    }

    @Test
    public void testFromPolyNumber3() {
        var aX = new PolyNumber(new long[]{-1, 0, 1});
        var aY = new PolyNumber(new long[]{0, -1, 0, 1});
        var p0 = new Point(-1, 0);
        var p3 = new Point(0, 0);
        var p1 = new Point(new Rational(-1), new Rational(-1, 3));
        var p2 = new Point(new Rational(-2,3), new Rational(-2,3));
        var c = new DCBcurve(p0, p1, p2, p3);
        assertEquals(c, new DCBcurve(new PolyNumber[]{aX, aY}));
    }

    @Test
    public void testFromPolyNumber3B() {
        var aX = new PolyNumber(new long[]{2, 1, -3});
        var aY = new PolyNumber(new long[]{1, 0, 1, -1});
        var p0 = new Point(2,1);
        var p3 = new Point(0,1);
        var p1 = new Point(new Rational(7,3), Rational.ONE);
        var p2 = new Point(new Rational(5,3), new Rational(4,3));
        var c = new DCBcurve(p0, p1, p2, p3);
        assertEquals(c, new DCBcurve(new PolyNumber[]{aX, aY}));
    }
    
    @Test
    public void testFromPolyNumberX() {
        System.out.println("testPolyNumberX");
        var aX = new PolyNumber(new Rational[]{Rational.ZERO, new Rational(1.65), new Rational(-0.314), new Rational(-0.343)});
        var aY = new PolyNumber(new Rational[]{Rational.ONE, Rational.ZERO, new Rational(-1.343), new Rational(0.343)});
        var c = new DCBcurve(new PolyNumber[]{aX, aY});
        System.out.println(c);
    }
    
    @Test
    public void testToPolyNumber_5p4p13() {
        System.out.println("Q5.4.13");
        var p0 = new Point(0,4);
        var p1 = new Point(new Rational(8,9), new Rational(9));
        var p2 = new Point(6,2);
        var p3 = new Point(4,0);
        var c = new DCBcurve(p0, p1, p2, p3);
        PolyNumber[] p = c.toPolyNumber();
        System.out.printf("x(t) = %s%n", p[0].toString());
        System.out.printf("y(t) = %s%n", p[1].toString());
        
    }
    
    @Test
    public void testFolium() {
        System.out.println("Q5.4.17");
        var p0 = new Point(2,4);
        var p1 = new Point(new Rational(11,3), new Rational(16,3));
        var p2 = new Point(new Rational(16,3), new Rational(11,3));
        var p3 = new Point(4,2);
        var folium = new DCBcurve(p0, p1, p2, p3);
        PolyNumber[] p = folium.toPolyNumber();
        System.out.printf("x(t) = %s%n", p[0].toString());
        System.out.printf("y(t) = %s%n", p[1].toString());
    }
    
    @Test
    public void testQuintic() {
        System.out.println("Quintic");
        var p0 = new Point(0,0);
        var p1 = new Point(1,2);
        var p2 = new Point(3,3);
        var p3 = new Point(4, -1);
        var p4 = new Point(6,1);
        var c = new DCBcurve(p0, p1, p2, p3, p4);
        PolyNumber[] p = c.toPolyNumber();
        PolyNumber[] expected = new PolyNumber[]{new PolyNumber(0, 4, 6, -8, 4), new PolyNumber(0, 8, -6, -16, 15)};
        assertArrayEquals(expected, p);
        System.out.printf("p[0] = %s%n", p[0].toString());
        System.out.printf("p[1] = %s%n", p[1].toString());
        
    }
    
    @Test
    public void testSplitAt() {
        var p0 = new Point(6,-12);
        var p1 = new Point(12,2);
        var p2 = new Point(34,-2);
        var p3 = new Point(8,-14);
        var p4 = new Point(38,-12);
        var p10 = p0;
        var p11 = new Point(new Rational(78,10),new Rational(-78,10));
        var p12 = new Point(new Rational(1104,100), new Rational(-522,100));
        var p13 = new Point(new Rational(1749,125), new Rational(-399,100));
        var p14 = new Point(new Rational(20361,1250), new Rational(-9357,2500));
        var p20 = p14;
        var p21 = new Point(new Rational(2706,125),new Rational(-1583,500));
        var p22 = new Point(new Rational(2344,100),new Rational(-794,100));
        var p23 = new Point(new Rational(17), new Rational(-134,10));
        var p24 = p4;
        var curve = new DCBcurve(p0, p1, p2, p3, p4);
        DCBcurve[] split = curve.splitAt(new Rational(3,10));
        var left = split[0];
        var right = split[1];
        var expectedLeft = new DCBcurve(p10, p11, p12, p13, p14);
        var expectedRight = new DCBcurve(p20, p21, p22, p23, p24);
        assertEquals(expectedLeft, left);
        assertEquals(expectedRight, right); 
    }
    
    @Test
    public void lecture4Example() {
        System.out.println("lecture4 example");
        var p0 = new Point(Rational.ZERO,Rational.HALF);
        var p1 = new Point(-2,1);
        var p2 = new Point(Rational.ZERO, Rational.HALF.neg());
        var curve = new DCBcurve(p0, p1, p2);
        PolyNumber[] p = curve.toPolyNumber();
        System.out.printf("p[0] = %s, P[1] = %s%n", p[0].toString(), p[1].toString());
    }
        
}
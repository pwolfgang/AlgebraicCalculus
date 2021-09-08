/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class CubicSplineTest {
    
    public CubicSplineTest() {
    }
    
    @BeforeEach
    public void init() {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (Exception ex) {
            throw new Error("UTF-8 not supported", ex);
        }
    }

    @Test
    public void test1() {
        var p0 = new Point(0,1);
        var p1 = new Point(1,3);
        var p2 = new Point(2,2);
        var p3 = new Point(3,2);
        var p4 = new Point(4,1);
        var p = new PolyNumber(1, 2, 1, -1);
        var s = new CubicSpline(p, p0, p1, p2, p3, p4);
        for (int i = 0; i < s.polies.length; i++) {
            System.out.printf("p[%d] = %s%n", i, s.polies[i].toString());
        }
        for (int i = 0; i < s.polies.length; i++) {
            System.out.printf("p[%d] = %s%n", i, s.pFinal[i].toString());
        }
        for (int i = 0; i < s.polies.length; i++) {
            PolyNumber xOft = new PolyNumber(i, 1);
            PolyNumber yOft = s.pFinal[i].eval(xOft);
            PolyNumber[] pp = new PolyNumber[]{xOft, yOft};
            var c = new DCBcurve(pp);
            System.out.printf("c[%d] = %s%n", i, c);
        } 
    }

   @Test
    public void test2() {
        var p0 = new Point(0,1);
        var p1 = new Point(1,3);
        var p2 = new Point(2,2);
        var p3 = new Point(3,2);
        var p4 = new Point(4,1);
        var p = new PolyNumber(1, 2, 2, -2);
        var s = new CubicSpline(p, p0, p1, p2, p3, p4);
        for (int i = 0; i < s.polies.length; i++) {
            System.out.printf("p[%d] = %s%n", i, s.polies[i].toString());
        }
        for (int i = 0; i < s.polies.length; i++) {
            System.out.printf("p[%d] = %s%n", i, s.pFinal[i].toString());
        }
        for (int i = 0; i < s.polies.length; i++) {
            PolyNumber xOft = new PolyNumber(i, 1);
            PolyNumber yOft = s.pFinal[i].eval(xOft);
            PolyNumber[] pp = new PolyNumber[]{xOft, yOft};
            var c = new DCBcurve(pp);
            System.out.printf("c[%d] = %s%n", i, c);
        }
        
        System.out.printf("quartic = %s%n", PolyNumber.fromPoints(p0, p1, p2, p3, p4).toString());
    }
    
   @Test
    public void test3() {
        System.out.println("test3 NEW");
        var p0 = new Point(0,1);
        var p1 = new Point(1,3);
        var p2 = new Point(2,2);
        var p3 = new Point(3,2);
        var p4 = new Point(4,1);
        var p = new PolyNumber(Rational.ONE, new Rational(19,3), new Rational(-6), new Rational(5,3));
        var s = new CubicSpline(p, p0, p1, p2, p3, p4);
        for (int i = 0; i < s.polies.length; i++) {
            System.out.printf("p[%d] = %s%n", i, s.polies[i].toString());
        }
        for (int i = 0; i < s.polies.length; i++) {
            System.out.printf("p[%d] = %s%n", i, s.pFinal[i].toString());
        }
        for (int i = 0; i < s.polies.length; i++) {
            PolyNumber xOft = new PolyNumber(i, 1);
            PolyNumber yOft = s.pFinal[i].eval(xOft);
            PolyNumber[] pp = new PolyNumber[]{xOft, yOft};
            var c = new DCBcurve(pp);
            System.out.printf("c[%d] = %s%n", i, c);
        }
        var polyFromPoints = PolyNumber.fromPoints(p0, p1, p2, p3, p4);
        var polyFromPointsPrime = polyFromPoints.D();
        var polyFromPointsPrimeOfOne = polyFromPointsPrime.eval(1);
        System.out.printf("quartic = %s%n", polyFromPoints.toString());
        System.out.printf("quartic') = %s%n", polyFromPointsPrime.toString());
        System.out.printf("quateic'(1) = %s%n", polyFromPointsPrimeOfOne.toString());
     }

    @Test
    public void test3_old() {
        System.out.println("test3 OLD");
        var p0 = new Point(0,1);
        var p1 = new Point(1,3);
        var p2 = new Point(2,2);
        var p3 = new Point(3,2);
        var p4 = new Point(4,1);
        var p = new PolyNumber(Rational.ONE, new Rational(19,3), new Rational(-6), new Rational(5,3));
        var s = new CubicSplineOld(p, p0, p1, p2, p3, p4);
        for (int i = 0; i < s.polies.length; i++) {
            System.out.printf("p[%d] = %s%n", i, s.polies[i].toString());
        }
        for (int i = 0; i < s.polies.length; i++) {
            System.out.printf("p[%d] = %s%n", i, s.pFinal[i].toString());
        }
        for (int i = 0; i < s.polies.length; i++) {
            PolyNumber xOft = new PolyNumber(i, 1);
            PolyNumber yOft = s.pFinal[i].eval(xOft);
            PolyNumber[] pp = new PolyNumber[]{xOft, yOft};
            var c = new DCBcurve(pp);
            System.out.printf("c[%d] = %s%n", i, c);
        }
        var polyFromPoints = PolyNumber.fromPoints(p0, p1, p2, p3, p4);
        var polyFromPointsPrime = polyFromPoints.D();
        var polyFromPointsPrimeOfOne = polyFromPointsPrime.eval(1);
        System.out.printf("quartic = %s%n", polyFromPoints.toString());
        System.out.printf("quartic') = %s%n", polyFromPointsPrime.toString());
        System.out.printf("quateic'(1) = %s%n", polyFromPointsPrimeOfOne.toString());
     }
    
   @Test
    public void test4() {
        System.out.println("exercise 3");
        var p0 = new Point(0,0);
        var p1 = new Point(1,2);
        var p2 = new Point(3,1);
        var p3 = new Point(7,4);
         var p = new PolyNumber(0, 0, 0, 2);
        var s = new CubicSpline(p, p0, p1, p2, p3);
        for (int i = 0; i < s.polies.length; i++) {
            System.out.printf("p[%d] = %s%n", i, s.polies[i].toString());
        }
        for (int i = 0; i < s.polies.length; i++) {
            System.out.printf("p[%d] = %s%n", i, s.pFinal[i].toString());
        }
        for (int i = 0; i < s.polies.length; i++) {
            PolyNumber xOft = new PolyNumber(i, 1);
            PolyNumber yOft = s.pFinal[i].eval(xOft);
            PolyNumber[] pp = new PolyNumber[]{xOft, yOft};
            var c = new DCBcurve(pp);
            System.out.printf("c[%d] = %s%n", i, c);
        }
        
        System.out.printf("cubic = %s%n", PolyNumber.fromPoints(p0, p1, p2, p3).toString());
    }
}

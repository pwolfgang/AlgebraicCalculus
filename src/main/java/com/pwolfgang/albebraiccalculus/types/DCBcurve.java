/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import com.pwolfgang.albebraiccalculus.Matrix;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class DCBcurve {
    
        
    private static final Matrix M2 = new Matrix(new long[]{1, 0, 0, -2, 2, 0, 1, -2, 1},3);
    private static final Matrix M2_INV = M2.inv();
    private static final Matrix M3 = new Matrix(new long[]{1, 0, 0, 0, -3, 3, 0, 0, 3, -6, 3, 0, -1, 3, -3, 1}, 4);
    private static final Matrix M3_INV = M3.inv();
 
    public final Point p0;
    public final Point p1;
    public final Point p2;
    public final Point p3;
    
    public DCBcurve(Point p0, Point p1, Point p2) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = null;
    }

    public DCBcurve(Point p0, Point p1, Point p2, Point p3) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    
    public Point r(Rational lambda) {
        if (p3 == null) return r2(lambda);
        else return r3(lambda);
    }
    
    Point r2(Rational lambda) {
        var q0 = scale(lambda, p0, p1);
        var q1 = scale(lambda, p1, p2);
        var r  = scale(lambda, q0, q1);
        return r;
    }
    
    Point r3(Rational lambda) {
        var q0 = scale(lambda, p0, p1);
        var q1 = scale(lambda, p1, p2);
        var q2 = scale(lambda, p2, p3);
        var r0 = scale(lambda, q0, q1);
        var r1 = scale(lambda, q1, q2);
        var s = scale(lambda, r0, r1);
        return s;
    }
    
    private static Point scale(Rational lambda, Point p0, Point p1) {
        Rational oneMinusLambda = Rational.ONE.sub(lambda);
        Point p0Scaled = p0.mul(oneMinusLambda);
        Point p1Scaled = p1.mul(lambda);
        Point result = p0Scaled.add(p1Scaled);
        return result;
    }
    
    public PolyNumber[] toPolyNumber() {
        if (p3 == null) {
            return toPolyNumber2();
        } else {
            return toPolyNumber3();
        }
    }
    
    PolyNumber[] toPolyNumber2() {
        PolyNumber[] r = new PolyNumber[2];
        r[0] = new PolyNumber(M2.mul(p0.getX(), p1.getX(), p2.getX()));
        r[1] = new PolyNumber(M2.mul(p0.getY(), p1.getY(), p2.getY()));
        return r;
    }
    
    
    PolyNumber[] toPolyNumber3() {
        PolyNumber[] r = new PolyNumber[2];
        r[0] = new PolyNumber(M3.mul(p0.getX(), p1.getX(), p2.getX(), p3.getX()));
        r[1] = new PolyNumber(M3.mul(p0.getY(), p1.getY(), p2.getY(), p3.getY()));
        return r;
    }
    
    static Rational[] createVector(Rational[] aS, int size) {
        Rational[] v = new Rational[size];
        for (int i = 0; i < aS.length; i++) {
            v[i] = aS[i];
        }
        for (int j = aS.length; j < size; j++) {
            v[j] = Rational.ZERO;
        }
        return v;
    }
    
    public static DCBcurve fromPolyNumber2(PolyNumber[] p) {
        Rational[] pX = createVector(p[0].aS,3);
        Rational[] pY = createVector(p[1].aS,3);
        Rational[] xS = M2_INV.mul(pX);
        Rational[] yS = M2_INV.mul(pY);
        return new DCBcurve(new Point(xS[0], yS[0]), new Point(xS[1], yS[1]), new Point(xS[2], yS[2]));
    }

    public static DCBcurve fromPolyNumber3(PolyNumber[] p) {
        Rational[] pX = createVector(p[0].aS,4);
        Rational[] pY = createVector(p[1].aS,4);
        Rational[] xS = M3_INV.mul(pX);
        Rational[] yS = M3_INV.mul(pY);
        return new DCBcurve(new Point(xS[0], yS[0]), new Point(xS[1], yS[1]), new Point(xS[2], yS[2]), new Point(xS[3], yS[3]));
    }
   
    public String toString() {
        var sB = new StringBuilder();
        sB.append(String.format("p0 = %s", p0.toString()));
        sB.append(String.format(" p1 = %s", p1.toString()));
        sB.append(String.format(" p2 = %s", p2.toString()));
        if (p3 != null) {
            sB.append(String.format(" p3 = %s%n", p3.toString()));
        }
        return sB.toString();
    }
    
    public boolean equals(Object other) {
        if (other == null) return false;
        if (this == other) return true;
        if (this.getClass() == other.getClass()) {
            DCBcurve c = (DCBcurve)other;
            if (!p0.equals(c.p0)) return false;
            if (!p1.equals(c.p1)) return false;
            if (!p2.equals(c.p2)) return false;
            if (p3 != null && !p3.equals(c.p3)) return false;
            if (p3 == null && c.p3 != null) return false;
            return true;
        } else {
            return false;
        }
    }
}

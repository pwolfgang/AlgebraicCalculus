/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import com.pwolfgang.albebraiccalculus.Matrix;
import com.pwolfgang.albebraiccalculus.Pascal;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class DCBcurve {
    
    private final Point[][] points;
    private final int n;
    private final Matrix m;
    
    public DCBcurve(Point... points) {
        n = points.length;
        this.points = new Point[n][];
        this.points[0] = points;
        for (int i = 1; i < n; i++) {
            this.points[i] = new Point[n-i];
        }
        m = Pascal.getDiagonal(n).mul(Pascal.getQ(n));
    }
    
    public DCBcurve(PolyNumber[] p) {
        int maxN = p[0].aS.length;
        if (maxN < p[1].aS.length) {
            maxN = p[1].aS.length;
        }
        n = maxN;
        m = Pascal.getDiagonal(n).mul(Pascal.getQ(n));
        Rational[] pX = createVector(p[0].aS,n);
        Rational[] pY = createVector(p[1].aS,n);
        Rational[] xS = m.inv().mul(pX);
        Rational[] yS = m.inv().mul(pY);
        points = new Point[n][];
        points[0] = createPoints(xS, yS);
        for (int i = 1; i < n; i++) {
            this.points[i] = new Point[n-i];
        }
    }

    public Point r(Rational t) {
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n-i; j++) {
                points[i][j] = scale(t, points[i-1][j],points[i-1][j+1]);
            }
        }
        return points[n-1][0];
    }
    
    public DCBcurve[] splitAt(Rational t) {
        r(t);
        Point[] left = new Point[n];
        Point[] right = new Point[n];
        for (int i=0; i < points.length; i++) {
            left[i] = points[i][0];
        }
        for (int i = n-1; i >=0; i--) {
            right[n-1-i] = points[i][n-1-i];
        }
        return new DCBcurve[]{new DCBcurve(left), new DCBcurve(right)};
    }
 
    private static Point scale(Rational lambda, Point p0, Point p1) {
        Rational oneMinusLambda = Rational.ONE.sub(lambda);
        Point p0Scaled = p0.mul(oneMinusLambda);
        Point p1Scaled = p1.mul(lambda);
        Point result = p0Scaled.add(p1Scaled);
        return result;
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
    
    static Point[] createPoints(Rational[] xS, Rational[] yS) {
        int numPoints = xS.length;
        Point[] points = new Point[numPoints];
        for (int i = 0; i < numPoints; i++) {
            points[i] = new Point(xS[i], yS[i]);
        }
        return points;
    }
    
    private Rational[][] getCoordinates() {
        int numPoints = points[0].length;
        Rational[][] c = new Rational[2][numPoints];
        for (int i = 0; i < numPoints; i++) {
            c[0][i] = points[0][i].getX();
            c[1][i] = points[0][i].getY();
        }
        return c;
    }
    
    public PolyNumber[] toPolyNumber(){
        Rational[][] pS = getCoordinates();
        Rational[] xS = m.mul(pS[0]);
        Rational[] yS = m.mul(pS[1]);
        PolyNumber[] p = new PolyNumber[]{new PolyNumber(xS), new PolyNumber(yS)};
        return p;
    }
       
    public String toString() {
        var sJ = new StringJoiner(", ");
        for (int i = 0; i < n; i++) {
            sJ.add(String.format("p%d = %s", i, points[0][i]));
        }
        return sJ.toString();
    }
    
    public boolean equals(Object other) {
        if (other == null) return false;
        if (this == other) return true;
        if (this.getClass() == other.getClass()) {
            DCBcurve c = (DCBcurve)other;
            return Arrays.equals(points[0], c.points[0]);
        } else {
            return false;
        }
    }
}

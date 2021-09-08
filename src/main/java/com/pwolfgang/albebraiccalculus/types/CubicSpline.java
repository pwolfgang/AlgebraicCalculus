/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import com.pwolfgang.albebraiccalculus.Matrix;
import com.pwolfgang.albebraiccalculus.Pascal;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class CubicSpline {
    
    Point[] points;
    PolyNumber[] polies;
    PolyNumber[] pFinal;
    int n;
    
    public CubicSpline(PolyNumber initialPoly, Point... points) {
        this.points = points;
        n = points.length-1;
        polies = new PolyNumber[n];
        genSplineCurves(initialPoly);
    }
    
    public CubicSpline(Point... points) {
        this.points = points;
        n = points.length-1;
        polies = new PolyNumber[n];
        var initialPolly = createInitialPolly();
        genSplineCurves(initialPolly);
    }

    private void genSplineCurves(PolyNumber initialPoly) {
        polies[0] = initialPoly;
        for (int i = 1; i < n; i++) {
            Rational c = points[i].getX().sub(points[i-1].getX());
            Matrix P = Pascal.getP(4,c).trans();
            Rational[] coefs = P.mul(polies[i-1].expandToDegree(4));
            coefs[3] = Rational.ZERO;
            var pp = new PolyNumber(coefs);
            Rational dx = points[i+1].getX().sub(points[i].getX());
            var s = pp.eval(dx);
            var dXcubed = dx.mul(dx).mul(dx);
            coefs[3] = (points[i+1].getY().sub(s)).div(dXcubed);
            polies[i] = new PolyNumber(coefs);
        }
        pFinal = new PolyNumber[n];
        for (int i = 0; i < n; i++) {
            pFinal[i] = polies[i].eval(new PolyNumber(points[i].getX().neg(), Rational.ONE));
        }
    }
    
    private PolyNumber createInitialPolly() {
        var x0 = points[0].getX();
        var y0 = points[0].getY();
        var x1 = points[1].getX();
        var y1 = points[1].getY();
        var curve = PolyNumber.fromPoints(points);
        var curvePrime = curve.D();
        var x0Sq = x0.mul(x0);
        var x0Cu = x0Sq.mul(x0);
        var x1Sq = x1.mul(x1);
        var x1Cu = x1Sq.mul(x1);
        var dOf0 = curvePrime.eval(x0);
        var dOf1 = curvePrime.eval(x1);
        Matrix m = new Matrix(new Rational[] 
        {Rational.ONE, x0, x0Sq, x0Cu,
         Rational.ZERO,Rational.ONE, x0.mul(Rational.TWO), x0Sq.mul(Rational.THREE),
         Rational.ZERO,Rational.ONE, x1.mul(Rational.TWO), x1Sq.mul(Rational.THREE),
         Rational.ONE, x1, x1Sq, x1Cu
        },4);
        Rational[] p = m.inv().mul(new Rational[]{y0, dOf0, dOf1, y1});
        return new PolyNumber(p);
    }
    
}

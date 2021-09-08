/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import com.pwolfgang.albebraiccalculus.Matrix;
import com.pwolfgang.albebraiccalculus.Pascal;
import java.util.Arrays;

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
        polies[0] = initialPoly;
        for (int i = 1; i < n; i++) {
            Rational c = points[i].getX().sub(points[i-1].getX());
            Matrix P = Pascal.getP(4,c).trans();
            System.out.printf("P_new: %s%n",P.toString());
            Rational[] coefs = P.mul(polies[i-1].expandToDegree(4));
            System.out.printf("coefs_new: %s%n", Arrays.asList(coefs).toString());
            coefs[3] = Rational.ZERO;
            var pp = new PolyNumber(coefs);
            var dx = points[i+1].getX().sub(points[i].getX());
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
    
}

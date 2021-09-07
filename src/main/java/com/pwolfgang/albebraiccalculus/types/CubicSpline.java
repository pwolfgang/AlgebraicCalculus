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
        Matrix P = Pascal.getP(4).trans();
        for (int i = 1; i < n; i++) {
            Rational[] coefs = P.mul(polies[i-1].expandToDegree(4));
            Rational sum = Rational.ZERO;
            for (int j = 0; j < 3; j++) {
                sum = sum.add(coefs[j]);
            }
            coefs[3] = points[i+1].getY().sub(sum);
            polies[i] = new PolyNumber(coefs);
        }
        pFinal = new PolyNumber[n];
        for (int i = 0; i < n; i++) {
            pFinal[i] = polies[i].eval(new PolyNumber(-i, 1));
        }
    }
    
}

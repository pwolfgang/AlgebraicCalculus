/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class Matrix {
    
    private final Rational[] m;
    
    private final int n;
    
    Rational determinant = null;
    
    Matrix inverse = null;
    
    Map<int[], Matrix> cofactors = new HashMap<>();
    
    public Matrix(Rational[] m, int n) {
        this.n = n;
        this.m = new Rational[n * n];
        System.arraycopy(m, 0, this.m, 0,  n*n);
    }
    
    public Rational det() {
        if (determinant != null) {
            return determinant;
        }
        if (n == 2) {
            determinant = m[0].mul(m[3].sub(m[1].mul(m[2])));
            return determinant;
        } else {
            determinant = null;
        }
        return determinant;
    }
    
    public Matrix inv() {
        if (determinant == null) {
            determinant = det();
            if (determinant.equals(Rational.ZERO)) {
                throw new IllegalArgumentException("Singular Matrix");
            }
        }
        if (inverse != null) {
            return inverse;
        }
        if (n == 2) {
            Rational[] invM = new Rational[4];
            invM[0] = m[3].div(determinant);
            invM[1] = m[1].div(determinant).neg();
            invM[2] = m[2].div(determinant).neg();
            invM[3] = m[0].div(determinant);
            inverse = new Matrix(invM, 2);  
        } else {
            inverse = null;
        }
        return inverse;
    }
    
}

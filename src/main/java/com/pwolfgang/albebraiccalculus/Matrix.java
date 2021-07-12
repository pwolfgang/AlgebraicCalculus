/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus;

import com.pwolfgang.albebraiccalculus.types.Rational;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

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
            determinant = m[0].mul(m[3]).sub(m[1].mul(m[2]));
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
    
    public Matrix add(Matrix other) {
        if (n != other.n) {
            throw new IllegalArgumentException("Matrices must be the same size");
        }
        Rational[] r = new Rational[n*n];
        for (int i = 0; i < n*n; i++) {
            r[i] = m[i].add(other.m[i]);
        }
        return new Matrix(r, n);
    }
    
    public Rational[] mul(Rational[] v) {
        Rational[] r = new Rational[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            Rational s = Rational.ZERO;
            for (int k = 0; k < n; k++) {
                s = s.add(m[j].mul(v[k]));
                j++;
            }
            r[i] = s;
        }
        return r;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (o.getClass() == this.getClass()) {
            Matrix other = (Matrix)o;
            if (n != other.n) return false;
            return Arrays.equals(m, other.m);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Arrays.deepHashCode(this.m);
        hash = 19 * hash + this.n;
        return hash;
    }
    
    @Override
    public String toString() {
        var sj1 = new StringJoiner("\n");
        int j = 0;
        for (int i = 0; i < n; i++) {
            var sj2 = new StringJoiner(" ");
            for (int k = 0; k < n; k++) {
                sj2.add(String.format("%10s", m[j].toString()));
                j++;
            }
            sj1.add(sj2.toString());
        }
        return sj1.toString();
    }
    
}

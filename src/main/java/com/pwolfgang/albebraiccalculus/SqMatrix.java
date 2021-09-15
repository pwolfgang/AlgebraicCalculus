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
public class SqMatrix extends Matrix {
    
    private final int n;
       
    Rational determinant = null;
    
    SqMatrix inverse = null;
    
    SqMatrix transpose = null;
    
    Map<Integer, Rational> cofactors = new HashMap<>();
    
    public SqMatrix(long[] d, int n) {
        super(d, n, n);
        this.n = n;
    }
    
    public SqMatrix(Rational[] newD, int n) {
        super(newD, n, n);
        this.n = n;
    }
    
    public SqMatrix subMatrix(int r, int c) {
        int newN = n-1;
        Rational[] newM = new Rational[newN * newN];
        int k = 0;
        int rXn = r*n;
        for (int i = 0; i < n*n; i+=n) {
            for (int j = 0; j < n; j++) {
                if (i != rXn && j != c) {
                    newM[k++] = d[i+j];
                }
            }
        }
        return new SqMatrix(newM, newN);
    }
    
    public Rational minor(int r, int c) {
        if (n < 3) {
            throw new IllegalArgumentException("Minor not defined for 2x2 matrix");
        }
        return subMatrix(r, c).det();
    }
    
    public Rational coFactor(int r, int c) {
        Integer key = r*n+c;
        if (!cofactors.containsKey(key)) {
            var mnor = minor(r, c);
            if (((r + c)&1) == 1) {
                cofactors.put(key, mnor.neg());
            } else {
                cofactors.put(key, mnor);
            } 
        }
        return cofactors.get(key);
    }
    
    public Rational det() {
        if (determinant != null) {
            return determinant;
        }
        if (n == 2) {
            determinant = d[0].mul(d[3]).sub(d[1].mul(d[2]));
            return determinant;
        } else {
            determinant = Rational.ZERO;
            for (int i = 0; i < n; i++) {
                determinant = determinant.add(d[i].mul(coFactor(0, i)));
            }
        }
        return determinant;
    }
    
    public SqMatrix trans() {
        if (transpose == null) {
            Rational[] x = new Rational[n*n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int index1 = i*n + j;
                    int index2 = j*n + i;
                    x[index2] = d[index1];
                }
            }
            transpose = new SqMatrix(x, n);
        }
        return transpose;
    } 
    
    public SqMatrix inv() {
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
            invM[0] = d[3].div(determinant);
            invM[1] = d[1].div(determinant).neg();
            invM[2] = d[2].div(determinant).neg();
            invM[3] = d[0].div(determinant);
            inverse = new SqMatrix(invM, 2);  
        } else {
            Rational[] cofactors = new Rational[n * n];
            int k = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    cofactors[k++] = coFactor(i, j);
                }
            }
            inverse = new SqMatrix(cofactors, n);
            inverse = inverse.trans().div(determinant);
        }
        return inverse;
    }
    
    public SqMatrix div(Rational r) {
        Rational[] x = new Rational[n*n];
        for (int k = 0; k < n*n; k++) {
            x[k] = d[k].div(r);
        }
        return new SqMatrix(x, n);
    }
    
    
    
    public SqMatrix add(SqMatrix other) {
        if (n != other.n) {
            throw new IllegalArgumentException("Matrices must be the same size");
        }
        Rational[] r = new Rational[n*n];
        for (int i = 0; i < n*n; i++) {
            r[i] = d[i].add(other.d[i]);
        }
        return new SqMatrix(r, n);
    }
    
    public Rational[] mul(Rational... v) {
        if (v.length != n) {
            throw new IllegalArgumentException("Vector and matrix not the same size");
        }
        Rational[] r = new Rational[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            Rational s = Rational.ZERO;
            for (int k = 0; k < n; k++) {
                s = s.add(d[j].mul(v[k]));
                j++;
            }
            r[i] = s;
        }
        return r;
    }
    
    public SqMatrix mul(SqMatrix o) {
        if (n != o.n) {
            throw new IllegalArgumentException("Matrices must be the same size");
        }
        Rational[] c = new Rational[n*n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cIndex = i*n + j;
                c[cIndex] = Rational.ZERO;
                for (int k = 0; k < n; k++) {
                    int mIndex = i*n + k;
                    int oIndex = k*n + j;
                    c[cIndex] =c[cIndex].add(d[mIndex].mul(o.d[oIndex]));
                }
            }
        }
        return new SqMatrix(c, n);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (o.getClass() == this.getClass()) {
            SqMatrix other = (SqMatrix)o;
            if (n != other.n) return false;
            return Arrays.equals(d, other.d);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Arrays.deepHashCode(this.d);
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
                sj2.add(String.format("%10s", d[j].toString()));
                j++;
            }
            sj1.add(sj2.toString());
        }
        return sj1.toString();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus;

import com.pwolfgang.albebraiccalculus.types.Rational;
import java.util.Arrays;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class Pascal {
    
    private static long[][] triangle;
    private static int maxN;
    
    static {
        maxN = 5;
        buildTriangle();
    }
       
    private static void buildTriangle() {
        triangle = new long[maxN+1][];
        triangle[0] = new long[]{1};
        triangle[1] = new long[]{1,1};
        fillRows(2, maxN+1);
    }

    private static void fillRows(int start, int end) {
        for (int i = start; i < end; i++) {
            triangle[i] = new long[i+1];
            triangle[i][0] = 1;
            for (int j = 1; j < i; j++) {
                triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j];
            }
            triangle[i][i] = 1;
        }
    }
    
    private static void expandTriangle(int n) {
        long[][] oldTriangle = triangle;
        triangle = Arrays.copyOf(oldTriangle, n+1);
        fillRows(maxN+1, n+1);
        maxN = n;
    }
    
    public static long nChooseK(int n, int k) {
        if (n > maxN) {
            expandTriangle(n);
        }
        return triangle[n][k];
    }
    
    public static SqMatrix getP(int n) {
        Rational[] m = new Rational[n*n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j <= i) {
                    m[k++] = Rational.of(Pascal.nChooseK(i, j));
                } else {
                    m[k++] = Rational.ZERO;
                }
            }
        }
        return new SqMatrix(m, n);
    }
    
    public static SqMatrix getP(int n, Rational c) {
        var P = getP(n);
        Rational factor = Rational.ONE;
        Rational[] m = P.getContents();
        for (int i = n; i < n*n; i+=n) {
            factor = factor.mul(c);
            for (int j = i; j < n*n; j+=(n+1)) {
                m[j] = m[j].mul(factor);
            }
        }
        return new SqMatrix(m,n);
    }
    
    public static SqMatrix getQ(int n) {
        Rational[] m = new Rational[n*n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j <= i) {
                    if (((i & 1) == 0) && ((j & 1) == 1)) {
                        m[k++] = Rational.of(-Pascal.nChooseK(i, j));
                    } else if (((i & 1) == 1) && ((j & 1) == 0)) {
                        m[k++] = Rational.of(-Pascal.nChooseK(i, j));
                    } else {
                        m[k++] = Rational.of(Pascal.nChooseK(i, j));
                    }
                } else {
                    m[k++] = Rational.ZERO;
                }
            }
        }
        return new SqMatrix(m, n);        
    }
    
    public static SqMatrix getDiagonal(int n) {
        long[] m = new long[n*n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    m[k++] = Pascal.nChooseK(n-1, i);
                } else {
                    m[k++] = 0;
                }
            }
        }
        return new SqMatrix(m, n);
    }
}

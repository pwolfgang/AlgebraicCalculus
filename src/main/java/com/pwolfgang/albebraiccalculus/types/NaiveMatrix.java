/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import java.util.Arrays;

/**
 * A NaiveMatrix is a two dimensional array of Rationals
 * 
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class NaiveMatrix {
    
    private Rational[][]m;
    private final int nRows;
    private final int nCols;
    
    /**
     * Create a new Matrix from an existing two dimensional array.
     * @param m The existing array.
     */
    public NaiveMatrix(Rational[][] m) {
        nRows = m.length;
        nCols = m[0].length;
        this.m = new Rational[nRows][];
        for (int i = 0; i < m.length; i++) {
            this.m[i] = Arrays.copyOf(m[i],nCols);
        }
    }
    
    /**
     * Create an empty matrix of a given size.
     * 
     * @param nRows The number of Rows;
     * @param nCols The number of Columns;
     */
    public NaiveMatrix(int nRows, int nCols) {
        this.nRows = nRows;
        this.nCols = nCols;
        m = new Rational[nRows][nCols];
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                m[i][j] = Rational.ZERO;
            }
        }
    }
    
    public NaiveMatrix swapRows(int i, int j) {
        Rational[] t = m[i];
        m[i] = m[j];
        m[j] = t;
        return this;
    }
    
    public NaiveMatrix scaleRow(Rational s, int row) {
        for (int j = 0; j < m[row].length; j++) {
            m[row][j] = m[row][j].mul(s);
        }
        return this;
    }
    
    public NaiveMatrix scaleAndAddRow(Rational s, int source, int dest) {
        for (int j = 0; j < m[source].length; j++) {
            m[dest][j] = m[dest][j].add(m[source][j].mul(s));
        }
        return this;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import java.util.Arrays;
import java.util.StringJoiner;

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
    
    public NaiveMatrix(long[][] m) {
        nRows = m.length;
        nCols = m[0].length;
        this.m = new Rational[nRows][nCols];
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < m[i].length && j < nCols; j++) {
                this.m[i][j] = Rational.of(m[i][j]);
            }
            for (int j = m[i].length; j < nCols; j++) {
                this.m[i][j] = Rational.ZERO;
            }
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
    
    /**
     * Swap two rows
     * @param i One row index
     * @param j The other row index
     * @return Modified Matrix with rows swapped.
     */
    public NaiveMatrix swapRows(int i, int j) {
        Rational[] t = m[i];
        m[i] = m[j];
        m[j] = t;
        return this;
    }
    
    /**
     * Scale the contents of a row
     * @param s The scale factor
     * @param row The row
     * @return Modified Matrix with the modified row.
     */
    public NaiveMatrix scaleRow(Rational s, int row) {
        for (int j = 0; j < m[row].length; j++) {
            m[row][j] = m[row][j].mul(s);
        }
        return this;
    }
    
    /**
     * Add a scaled copy of a row to another row.
     * @param s The scale factor
     * @param source The source row
     * @param dest The destination row
     * @return Modified Matrix.
     */
    public NaiveMatrix scaleAndAddRow(Rational s, int source, int dest) {
        for (int j = 0; j < m[source].length; j++) {
            m[dest][j] = m[dest][j].add(m[source][j].mul(s));
        }
        return this;
    }
    
    /**
     * Find leading row. A leading row has a non-zero entry. This method finds
     * the leading row with the smallest row index and col index of a non-zero
     * entry, with the search starting at the start row.
     * @param start The first row to start the search.
     * @return An int array. The [0] entry is the index of the row and the [1]
     * index is the column. Return null if no leading row is found.
     */
    public int[] findLeadingRow(int start) {
        int leadingRowIndex = nRows;
        int leadingColIndex = nCols;
        for (int i = start; i < nRows; i++) {
            int k = findFirstNonZero(m[i]);
            if (k < leadingColIndex) {
                leadingColIndex = k;
                leadingRowIndex = i;
            }
        }
        if (leadingRowIndex == nRows) {
            return null;
        }
        return new int[]{leadingRowIndex, leadingColIndex};
    }
    
    /**
     * Find first non-zero entry.
     * @param row A row in the matrix.
     * @return index of first non-zero entry or row.length if none.
     */
    private int findFirstNonZero(Rational[] row) {
        for (int i = 0; i < row.length; i++) {
            if (!Rational.ZERO.equals(row[i])) {
                return i;
            }
        }
        return row.length;
    }
    
    /**
     * Convert to row-echelon form. After performing this transformation
     * all rows consisting only of zeros are at the bottom and the leading
     * coefficient (first non-zero) of a non-row is strictly to the right
     * of the leading coefficient of the row above it.
     */
    NaiveMatrix convertToRowEchelonForm() {
        int startRow = 0;
        int[] nextRow;
        while ((nextRow = findLeadingRow(startRow)) != null) {
            int foundRow = nextRow[0];
            int foundCol = nextRow[1];
            if (foundRow != startRow) {
                swapRows(foundRow, startRow);
                System.out.println("After row swap");
                System.out.println(this.toString());
            }
            for (int i = startRow+1; i < nRows; i++) {
                var factor = m[i][foundCol].div(m[startRow][foundCol]).neg();
                scaleAndAddRow(factor, startRow, i);
            }
            startRow++;
        }
        return this;
    }
    
    public String toString() {
        var rowSJ = new StringJoiner("\n");
        for (int i = 0; i < nRows; i++) {
            var colSJ = new StringJoiner("  ");
            for (int j = 0; j < nCols; j++) {
                colSJ.add(m[i][j].toString());
            }
            rowSJ.add(colSJ.toString());
        }
        return rowSJ.toString();
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() == o.getClass()) {
            var other = (NaiveMatrix)o;
            return Arrays.deepEquals(m, other.m);
        }
        return false;
    }
    
}

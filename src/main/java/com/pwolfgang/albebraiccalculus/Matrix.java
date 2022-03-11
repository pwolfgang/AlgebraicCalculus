package com.pwolfgang.albebraiccalculus;

import com.pwolfgang.albebraiccalculus.types.Rational;
import java.util.Arrays;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public abstract class Matrix {

    final Rational[] d;
    final int nRows;
    final int nCols;
    
    Matrix(Rational[] d, int nRows, int nCols) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.d = new Rational[nRows*nCols];
        System.arraycopy(d, 0, this.d, 0,  nRows*nCols);
    }
    
    Matrix (long[] d, int nRows, int nCols) {
        this.d = new Rational[nRows*nCols];
        this.nRows = nRows;
        this.nCols = nCols;
        for (int i = 0; i < nRows*nCols; i++) {
            this.d[i] = Rational.of(d[i]);
        }
        
    }

    public Rational[] getContents() {
        return Arrays.copyOf(d, nRows*nCols);
    }
    

}

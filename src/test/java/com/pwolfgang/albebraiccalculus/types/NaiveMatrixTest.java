/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import com.pwolfgang.albebraiccalculus.SqMatrix;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class NaiveMatrixTest {
    
    public NaiveMatrixTest() {
    }

    @Test
    public void testSwapRows() {
    }

    @Test
    public void testScaleRow() {
    }

    @Test
    public void testScaleAndAddRow() {
    }

    @Test
    public void testFindLeadingRow() {
        var m = new NaiveMatrix(new long[][]
        {{0, 2, 1, 3, -1},
         {0, 0, 0, 1, 5},
         {0, 0, 0, 0, 0}});
        int[] exp1 = new int[]{0,1};
        int[] exp2 = new int[]{1,3};
        assertArrayEquals(exp1, m.findLeadingRow(0));
        assertArrayEquals(exp2, m.findLeadingRow(1));
    }
    
    @Test
    public void testConvertToRowEchelonForm() {
        var m = new NaiveMatrix(new long[][]
        {{1, 1, 1, 3},
         {1, 2, 4, -1},
         {1, 3, 9, 5}
        });
        m.convertToRowEchelonForm();
        var expected = new NaiveMatrix(new long[][]
        {{1, 1, 1, 3},
         {0, 1, 3, -4},
         {0, 0, 2, 10}
        });
        assertEquals(expected, m);
    }
    
    @Test
    public void testReduce() {
        var m = new NaiveMatrix(new long[][]
        {   {1, 2, 3, -1,  5},
            {0, 4,  0, 8, 12},
            {0, 0,  0, 3, -3}
        });
        var e = new NaiveMatrix(new long[][]
        {   {1, 0, 3, 0, -6},
            {0, 1, 0, 0,  5},
            {0, 0, 0, 1, -1}
        });
        assertEquals(e, m.reduce());
    }
    
    @Test
    public void testSolve() {
        var m = new NaiveMatrix(new long[][]
        {   {1, 4,  6,  48},
            {2, 3, 20, 113},
            {3, 5, 15, 111}
        });
        var e = new NaiveMatrix(new long[][]
        {   {1, 0, 0, 12},
            {0, 1, 0, 3},
            {0, 0, 1, 4}
        });
        assertEquals(e, m.convertToRowEchelonForm().reduce()); 
    }
    
    @Test
    public void testSolve2() {
        NaiveMatrix A = new NaiveMatrix(new long[][]
        {   {1, 1, 1},
            {1, 2, 4},
            {1, 3, 9}
        });
        Rational[] b = new Rational[]{Rational.of(3), Rational.of(-1), Rational.of(5)};
        Rational[] x = new Rational[]{Rational.of(17), Rational.of(-19), Rational.of(5)};
        assertArrayEquals(x, A.solve(b));
    }

    @Test
    public void testSolve3() {
        NaiveMatrix A = new NaiveMatrix(new long[][]
        {   {1, 4, 6},
            {2, 3, 20},
            {3, 5, 15}
        });
        Rational[] b = new Rational[]{Rational.of(48), Rational.of(113), Rational.of(111)};
        Rational[] x = new Rational[]{Rational.of(12), Rational.of(3), Rational.of(4)};
        assertArrayEquals(x, A.solve(b));
    }

    @Test
    public void testSolve4() {
        NaiveMatrix A = new NaiveMatrix(new long[][]
        {   {2, 7, 4},
            {1, 2, -1},
            {-1, 4, 13}
        });
        Rational[] b = new Rational[]{Rational.of(2), Rational.of(1), Rational.of(-1)};
        Rational[] x = new Rational[]{Rational.of(1), Rational.of(0), Rational.of(0)};
        assertArrayEquals(x, A.solve(b));
    }
    
    @Test
    public void testSolve5() {
        NaiveMatrix A = new NaiveMatrix(new long[][]
        {   {1, 2, 3, -1},
            {0, 4, 0, 8},
            {0, 0, 0, 3}
        });
        Rational[] b = new Rational[]{Rational.of(5), Rational.of(12), Rational.of(-3)};
        Rational[] x = new Rational[]{Rational.of(-6), Rational.of(5), Rational.of(-1)};
        assertArrayEquals(x, A.solve(b));
    }
    
    @Test
    public void testInvert() {
        var a = new SqMatrix(new long[]{2,0,0,1,2,1,0,1,2},3);
        var aI = NaiveMatrix.invert(a);
        var aInv = a.inv();
        assertEquals(aInv, aI);
    }
}

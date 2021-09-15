/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus;

import com.pwolfgang.albebraiccalculus.types.Rational;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class MatrixTest {
    
    SqMatrix m = new SqMatrix(new Rational[]{new Rational(2), new Rational(-1), new Rational(-3), new Rational(5)}, 2);
    
    public MatrixTest() {
    }

    @Test
    public void testDet() {
        assertEquals(new Rational(7), m.det());
    }

    @Test
    public void testInv() {
        SqMatrix inv = new SqMatrix(new Rational[]{new Rational(5,7), new Rational(1,7), new Rational(3,7), new Rational(2,7)},2);
        assertEquals(inv, m.inv());
    }

    @Test
    public void testAdd() {
    }

    @Test
    public void testMul() {
        Rational[] r = new Rational[]{new Rational(1), new Rational(2)};
        Rational[] c = new Rational[]{new Rational(0), new Rational(7)};
        assertArrayEquals(r, m.inv().mul(c));
    }

    @Test
    public void testToString() {
        String mString = "         2         -1\n" +
                         "        -3          5";
        String mInvString = "       5/7        1/7\n" +
                            "       3/7        2/7";
        assertEquals(mString, m.toString());
        assertEquals(mInvString, m.inv().toString());
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void testHashCode() {
    }
    
    @Test
    public void testSubMatrix() {
        SqMatrix m = new SqMatrix(new long[]{-6, 1, 1, 6, -8, 5, 8, 6, -1, 0, 8, 2, -7, 1, -1, 1}, 4);
        SqMatrix expected = new SqMatrix(new long[]{-6, 1, 6, -8, 8, 6, -7, -1, 1}, 3);
        assertEquals(expected, m.subMatrix(2, 1));
    }
    
    @Test
    public void testMinor() {
        SqMatrix m = new SqMatrix(new long[]{3, 5, 0, 2, -1, -7, 6, -1, 5}, 3);
        assertEquals(new Rational(25), m.minor(1, 0));
    }
    
    @Test
    public void testDet4x4() {
        SqMatrix m = new SqMatrix(new long[]{-2, -8, 3, 5, -3, 1, 7, 3, 1, 2, -9, 6, -6, 7, 7, -9}, 4);
        assertEquals(new Rational(-4071), m.det());
    }
    
    @Test
    public void testInv4x4() {
        SqMatrix m = new SqMatrix(new long[]{-5, 2, 6, -8, 1, -5, 1, 8, 7, 7, -6, -7, 1, -3, 7, 4}, 4);
        SqMatrix mInv = m.inv();
        SqMatrix I = new SqMatrix(new long[]{1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},4);
        System.out.println(mInv.toString());
        assertEquals(I, m.mul(mInv));
    }
    
}

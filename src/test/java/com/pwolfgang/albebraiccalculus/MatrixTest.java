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
    
    Matrix m = new Matrix(new Rational[]{new Rational(2), new Rational(-1), new Rational(-3), new Rational(5)}, 2);
    
    public MatrixTest() {
    }

    @Test
    public void testDet() {
        assertEquals(new Rational(7), m.det());
    }

    @Test
    public void testInv() {
        Matrix inv = new Matrix(new Rational[]{new Rational(5,7), new Rational(1,7), new Rational(3,7), new Rational(2,7)},2);
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
    
}

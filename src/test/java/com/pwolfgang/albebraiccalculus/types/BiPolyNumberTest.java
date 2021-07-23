/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class BiPolyNumberTest {
    
    public BiPolyNumberTest() {
    }

    @Test
    public void testTrimTrailingZeros() {
    }

    @Test
    public void testAdd() {
        var p = new BiPolyNumber(new long[][]
        {
            {-1, 0, 1, 2},
            {0, -2, 0, 3},
            {1, 0, 0, 1}
        });
        var q = new BiPolyNumber(new long[][]
        {
            {2, -2},
            {7, -3}
        });
        var r = new BiPolyNumber(new long[][]
        {
            {0, 1, -4},
            {-1, -3}
        });
        var result = new BiPolyNumber(new long[][]
        {
            {0, -5, 14, 4},
            {10, 2, 0, 6},
            {2, 0, 0, 2}
        });
        var px2 = p.mul(2);
        var rx3 = r.mul(3);
        var px2PlusQ = px2.add(q);
        var px2PlusQsubRx3 = px2PlusQ.sub(rx3);
        assertEquals(result, p.mul(2).add(q).sub(r.mul(3)));
    }

    @Test
    public void testAddRows() {
    }

    @Test
    public void testSub() {
    }

    @Test
    public void testSubRows() {
    }

    @Test
    public void testMul_Rational() {
    }

    @Test
    public void testShift() {
    }

    @Test
    public void testMul_BiPolyNumber() {
    }

    @Test
    public void testEval_Rational_Rational() {
    }

    @Test
    public void testEval_BiPolyNumber_BiPolyNumber() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void testFormatAlphaBeta() {
    }

    @Test
    public void testEquals() {
    }
    
}

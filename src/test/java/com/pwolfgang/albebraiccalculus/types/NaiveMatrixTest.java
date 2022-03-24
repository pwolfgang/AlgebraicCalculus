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
        System.out.println(m.convertToRowEchelonForm());
        var expected = new NaiveMatrix(new long[][]
        {{1, 1, 1, 3},
         {0, 1, 3, -4},
         {0, 0, 2, 10}
        });
        assertEquals(expected, m);
    }
    
}

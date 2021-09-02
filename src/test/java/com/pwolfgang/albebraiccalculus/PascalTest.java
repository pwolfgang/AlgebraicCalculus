/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class PascalTest {
    
    long[][] p;
    
    public PascalTest() {
    }
    
    @Test
    public void testNchooseK() {
        assertEquals(10, Pascal.nChooseK(5, 3));
        assertEquals(21, Pascal.nChooseK(7, 5));
    }
    
    @Test
    public void testGetP() {
        Matrix expected = new Matrix(
        new long[]{1,0,0,0,1,1,0,0,1,2,1,0,1,3,3,1}, 4);
        assertEquals(expected, Pascal.getP(4));
    }
 
    @Test
    public void testGetQ() {
        Matrix expected = new Matrix(
        new long[]{1,0,0,0,-1,1,0,0,1,-2,1,0,-1,3,-3,1}, 4);
        assertEquals(expected, Pascal.getQ(4));
    }
    
    @Test
    public void testGetDiagonal() {
        Matrix expected = new Matrix(
        new long[]{1, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 1}, 4);
        assertEquals(expected, Pascal.getDiagonal(4));
        
    }
    
}

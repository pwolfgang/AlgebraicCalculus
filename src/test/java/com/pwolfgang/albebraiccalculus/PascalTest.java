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
public class PascalTest {
    
    
    public PascalTest() {
    }
    
    @Test
    public void testNchooseK() {
        System.out.println("n choose k");
        assertEquals(10, Pascal.nChooseK(5, 3));
        assertEquals(21, Pascal.nChooseK(7, 5));
    }
    
    @Test
    public void testGetP() {
        System.out.println("get P");
        SqMatrix expected = new SqMatrix(
        new long[]{1,0,0,0,1,1,0,0,1,2,1,0,1,3,3,1}, 4);
        assertEquals(expected, Pascal.getP(4));
    }
 
    @Test
    public void testGetQ() {
        System.out.println("get q");
        SqMatrix expected = new SqMatrix(
        new long[]{1,0,0,0,-1,1,0,0,1,-2,1,0,-1,3,-3,1}, 4);
        assertEquals(expected, Pascal.getQ(4));
    }
    
    @Test
    public void testGetDiagonal() {
        System.out.println("get diagonal");
        SqMatrix expected = new SqMatrix(
        new long[]{1, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 1}, 4);
        assertEquals(expected, Pascal.getDiagonal(4));
        
    }
    
    @Test
    public void testGetPsubC() {
        System.out.println("get p sub c");
        SqMatrix expected = new SqMatrix(
        new long[]{
            1, 0, 0, 0,
            4, 1, 0, 0,
            16,8, 1, 0,
            64,48,12,1
        },4);
        var four=Rational.of(4);
        var result = Pascal.getP(4,four);
        assertEquals(expected, result);
    }

}

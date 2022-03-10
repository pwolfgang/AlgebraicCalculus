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
public class IntTest {
    
    public IntTest() {
    }

    @Test
    public void testDiv() {
        assertArrayEquals(new long[]{5, 3},Int.div(28, 5));
    }
    
    @Test
    public void testGcd() {
        assertEquals(1, Int.gcd(28,5));
    }

    @Test
    public void testEGCD() {
        assertArrayEquals(new long[]{1, 2, -11}, Int.eGCD(28, 5));
    }
    
}

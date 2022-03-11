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
public class CircleTest {
    
    public CircleTest() {
    }

    @Test
    public void testTriangle() {
        assertEquals(Rational.of(6,10), Circle.triangle(Rational.of(-1), 
                Rational.of(2), Rational.of(-3)).area());
    }
    
}

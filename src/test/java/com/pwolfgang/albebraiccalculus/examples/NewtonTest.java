/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.pwolfgang.albebraiccalculus.examples;

import com.pwolfgang.albebraiccalculus.types.BiPolyNumber;
import com.pwolfgang.albebraiccalculus.types.Point;
import com.pwolfgang.albebraiccalculus.types.Rational;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class NewtonTest {
    
    public NewtonTest() {
    }
    
    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testNewtonIteration() {
        var fermatCurve = new BiPolyNumber(new long[][] {
            {-1, 0, 0, 1},
            {0, 0, 0,0},
            {0, 0, 0, 0},
            {1, 0, 0, 0}
        });
        var bernoulli = new BiPolyNumber(new long[][] {
            {0, 0, 2, 0, 1},
            {0, 0, 0, 0, 0},
            {-2, 0, 2, 0, 0},
            {0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0}
        });
        var a = new Point(2,1);
        var aP = Newton.newtonIteration(fermatCurve, bernoulli, a);
        System.out.println(aP.toString());
        var expected = new Point(Rational.of(83,64), Rational.of(55, 48));
        assertEquals(expected, aP);
    }
    
}

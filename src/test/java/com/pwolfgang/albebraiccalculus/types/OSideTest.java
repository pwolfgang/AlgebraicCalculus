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
public class OSideTest {
    
    public OSideTest() {
    }

    @Test
    public void testSignedArea() {
        Point p1 = new Point(1,3);
        Point p2 = new Point(-5,4);
        assertEquals(new Rational(19,2), new OSide(p1,p2).signedArea());
    }
    
}

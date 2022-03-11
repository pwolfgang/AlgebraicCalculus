/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.datastructures;

import com.pwolfgang.albebraiccalculus.types.Rational;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class OSetTest {
    
    OSet<Rational> oSet;
    
    public OSetTest() {
        oSet = new OSet<>(Arrays.asList(Rational.of(1), Rational.of(4), Rational.of(2)));
    }

    @Test
    public void testSize() {
        assertEquals(3, oSet.size());
    }

   @Test
    public void testToString() {
        assertEquals("{1,4,2}", oSet.toString());
    }
    
}

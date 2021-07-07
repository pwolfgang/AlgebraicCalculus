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
public class ListTest {
    
    public ListTest() {
    }

    @Test
    public void testToString() {
        List<Rational> list = new List<>(Arrays.asList(new Rational(1), new Rational(1,2)));
        assertEquals("[1,1/2]", list.toString());
    }
    
}

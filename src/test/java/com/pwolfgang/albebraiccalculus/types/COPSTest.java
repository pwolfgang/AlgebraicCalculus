/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class COPSTest {
    
    public COPSTest() {
    }

    @Test
    public void testArea() {
        Point a = new Point(3, -1);
        Point b = new Point(0, 2);
        Point c = new Point(-1, 1);
        Point d = new Point(0, -1);
        COPS ops2 = new COPS(Arrays.asList(a, b, c, d));
        COPS ops3 = new COPS(Arrays.asList(a, b, c, b, b,c));
        printArea(ops2);
        printArea(ops3);
    }
    
    private void printArea(COPS ops) {
        System.out.printf("Area of %s is %s%n", ops.toString(), ops.area().toString());        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import com.pwolfgang.albebraiccalculus.datastructures.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class OPSTest {
    
    public OPSTest() {
    }

    @Test
    public void testArea() {
        Point a = new Point(3, -1);
        Point b = new Point(0, 2);
        Point c = new Point(-1, -1);
        Point d = new Point(0, -1);
        OPS ops1a = new OPS(Arrays.asList(a, b));
        OPS ops1b = new OPS(Arrays.asList(c, d));
        OPS ops2 = new OPS(Arrays.asList(a, b, c, d));
        OPS ops3 = new OPS(Arrays.asList(a, b, c, b, b,c));
        printArea(ops1a);
        printArea(ops1b);
        printArea(ops2);
        printArea(ops3);
    }
    
    @Test
    public void testQuadraticOPS() {
        List<Point> p1to5 = new List<>();
        for (int i = 1; i <= 5; i++) {
            p1to5.add(new Point(i-1, i*i));
        }
        OPS ops1to5 = new OPS(p1to5);
        printArea(ops1to5);
        
        List<Point> p10to13 = new List<>();
        for (int i = 10; i <= 13; i++) {
            p10to13.add(new Point(i-1, i*i));
        }
        OPS ops10to13 = new OPS(p10to13);
        printArea(ops10to13);
    }

    private void printArea(OPS ops) {
        System.out.printf("Area of %s is %s%n", ops.toString(), ops.area().toString());        
    } 
    
}

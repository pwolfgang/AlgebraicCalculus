/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class DivPolySeriesTest {
    
    public DivPolySeriesTest() {
    }

    @BeforeEach
    public void init() {
        try {
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new InternalError("VM does not support UTF-8");
        }
    }

    @Test
    public void testIterator() {
    }
    
    @Test
    public void testDiv() {
        System.out.println("testIterativeDiv");
        var a = new PolyNumber(1, 0, 0, 0, 0, 0, -1);
        var b = new PolyNumber(1, -1, 1);
        var itr = a.div(b).iterator();
        var q = PolyNumber.ZERO;
        while (itr.hasNext()) {
            var p = itr.next();
            q = q.add(p);
            System.out.println(p);
        }
        assertEquals(new PolyNumber(1,1,0,-1,-1), q);
    }

    @Test
    public void testDiv2() {
        System.out.println("testIterativeDiv2");
        var a = new PolyNumber(1, 0, 0, 0, 0, -1);
        var b = new PolyNumber(1, -1, 1);
        var itr = a.div(b).iterator();
        var q = PolyNumber.ZERO;
        int i = 0;
        while (itr.hasNext() && i++ < 6) {
            var p = itr.next();
            q = q.add(p);
            System.out.println(p);
        }
        assertEquals(new PolyNumber(1,1,0,-1,-1, -1), q);
    }
}

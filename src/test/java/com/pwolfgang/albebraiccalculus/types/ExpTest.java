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
public class ExpTest {
    
    public ExpTest() {
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
        
        Exp exp = new Exp();
        var itr = exp.iterator();
        for (int i = 0; i < 5; i++) {
            System.out.println(itr.next());
        }  
    }
    
    @Test
    public void testTruncate() {
        PolySeries p = new Exp();
        System.out.println(p.truncate(2));
    }
    
}

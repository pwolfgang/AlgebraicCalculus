/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types.plus;

import com.pwolfgang.albebraiccalculus.types.*;
import com.pwolfgang.albebraiccalculus.types.pluis.DCBcurvePlus;
import com.pwolfgang.albebraiccalculus.types.pluis.PointPlus;
import com.pwolfgang.albebraiccalculus.types.pluis.RationalPlus;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class DCBcurveTestPlus {
    
    @BeforeEach
    public void init() {
        try {
            PrintStream p = new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8");
            System.setOut(p);          
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
    
    public DCBcurveTestPlus() {
    }


    @Test
    public void testR3_5() {
        System.out.println("testR3_5");
        RationalPlus sqrt2 = new RationalPlus(0,1);
        RationalPlus a = sqrt2.mul(Rational.of(4,3)).sub(Rational.of(4,3));
        var p0 = new PointPlus(0,1);
        var p1 = new PointPlus(a, new RationalPlus(1));
        var p2 = new PointPlus(new RationalPlus(1),a);
        var p3 = new PointPlus(1,0);
        var curve = new DCBcurvePlus(p0, p1, p2, p3);
        var lambda = new RationalPlus(Rational.HALF,Rational.ZERO);
//        var lambda = new RationalPlus(Rational.ZERO, Rational.of(1,5));
        var r = curve.r3(lambda);
        System.out.println(r);
    }

}
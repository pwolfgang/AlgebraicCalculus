/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.examples;

import com.pwolfgang.albebraiccalculus.types.PolyNumber;
import com.pwolfgang.albebraiccalculus.types.Rational;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class DivideAlgorithms {
    
    public static void main(String... args) {
        try {
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new InternalError("VM does not support UTF-8");
        }
        var p1 = new PolyNumber(-1, 0, 0, 0, 0 , 1);
        var p3 = new PolyNumber(1, 0, 0, 0, 0 , -1);
        var d = new PolyNumber(1, -1, 1);
        var p2 = new PolyNumber(-1, 0, 0, 0, 0, 0, 1);
        var p4 = new PolyNumber(1, 0, 0, 0, 0, 0, -1);
        var alpha1 = new Rational(10);        
        var alpha2 = new Rational(1,10);        
        compute(p1, alpha1, d);
        compute(p2, alpha1, d);
        compute(p3, alpha2, d);
        compute(p4, alpha2, d);
    }

    private static void compute(PolyNumber p, Rational alpha, PolyNumber d) {
        PolyNumber[] qr1 = p.div(d);
        var q1 = qr1[0];
        var r1 = qr1[1];
        Rational pVal = p.eval(alpha);
        Rational dVal = d.eval(alpha);
        Rational q1Val = q1.eval(alpha);
        Rational r1Val = r1.eval(alpha);
       System.out.printf("%s = (%s × %s) + %s%n", p, d, q1, r1);
        System.out.printf("%s = (%s × %s) + %s%n", pVal, dVal, q1Val, r1Val);
    }
    
}

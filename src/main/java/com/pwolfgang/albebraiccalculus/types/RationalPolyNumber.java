/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class RationalPolyNumber extends PolyNumber {
    
    PolyNumber num;
    PolyNumber den;
    
    public RationalPolyNumber(PolyNumber num, PolyNumber den) {
        super();
        this.num = num;
        this.den = den;
    }
    
    public Rational eval(Rational x) {
        Rational n = num.eval(x);
        Rational d = den.eval(x);
        if (Rational.ZERO.equals(d)) {
            return Rational.of(Long.MAX_VALUE);
        }
        return n.div(d);
    }
    
}

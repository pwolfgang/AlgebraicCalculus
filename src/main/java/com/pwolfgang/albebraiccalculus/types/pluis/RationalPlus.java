/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types.pluis;

import com.pwolfgang.albebraiccalculus.types.Rational;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class RationalPlus {
    
    final Rational a;
    final Rational b;
    
    public static final RationalPlus ONE = new RationalPlus(1);
    public static final RationalPlus ZERO = new RationalPlus(0);
    
    public RationalPlus(Rational a, Rational b) {
        this.a = a;
        this.b = b;
    }
    
    public RationalPlus(long a, long b) {
        this.a = Rational.of(a);
        this.b = Rational.of(b);
    }
    
    public RationalPlus(long a) {
        this.a = Rational.of(a);
        this.b = Rational.ZERO;
    }
    
    public RationalPlus add(RationalPlus x) {
        return new RationalPlus(a.add(x.a), b.add(x.b));
    }
    
    public RationalPlus sub(RationalPlus x) {
        return new RationalPlus(a.sub(x.a), b.sub(x.b));
    }
    
    public RationalPlus mul(RationalPlus x) {
        return new RationalPlus(a.mul(x.a).add(b.mul(x.b).mul(Rational.TWO)), 
                b.mul(x.a).add(a.mul(x.b)));
    }
    
    public RationalPlus add(Rational x) {
        return new RationalPlus(a.add(x), b);
    }
    public RationalPlus sub(Rational x) {
        return new RationalPlus(a.sub(x), b);
    }
    public RationalPlus mul(Rational x) {
        return new RationalPlus(a.mul(x), b.mul(x));
    }
    
    @Override
    public String toString() {
        if (!Rational.ZERO.equals(b)) {
            return String.format("%s + %s√2", a.toString(), b.toString());
        } else {
            return a.toString();
        }
    }
    
}

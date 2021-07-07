/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import com.pwolfgang.albebraiccalculus.types.Rational;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class Point {
    
    public final Rational x;
    public final Rational y;
    
    public static final Point O = new Point(new Rational(0), new Rational(0));
    
    public Point(Rational x, Rational y) {
        this.x = x;
        this.y = y;
    }
    
    public Point(long x, long y) {
        this(new Rational(x), new Rational(y));
    }
    
    @Override
    public String toString() {
        return String.format("[%s, %s]", x.toString(), y.toString());
    }
    
}

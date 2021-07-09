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
public class Point {
    
    private final Rational x;
    private final Rational y;
    
    public static final Point O = new Point(new Rational(0), new Rational(0));
    
    public Point(Rational x, Rational y) {
        this.x = x;
        this.y = y;
    }
    
    public Point(long x, long y) {
        this(new Rational(x), new Rational(y));
    }
    
    public Rational getX() {return x;}
    public Rational getY() {return y;}
    
    public Point mul(Rational lambda) {
        return new Point(x.mul(lambda), y.mul(lambda));
    }
    
    public Point add(Point p) {
        return new Point(x.add(p.x), y.add(p.y));
    }
    
    @Override
    public String toString() {
        return String.format("[%s, %s]", x.toString(), y.toString());
    }
    
}

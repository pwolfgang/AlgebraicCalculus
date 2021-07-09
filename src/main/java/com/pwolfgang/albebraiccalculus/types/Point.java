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
    
   
    private final long a, b, c;
    
    public static final Point O = new Point(new Rational(0), new Rational(0));
    
    public Point(Rational x, Rational y) {
        Rational lcm = new Rational(Rational.lcm(x.den, y.den));
        this.a = lcm.num;
        this.b = x.mul(lcm).num;
        this.c = y.mul(lcm).num;
    }
    
    public Point(long x, long y) {
        this.a = 1;
        this.b = x;
        this.c = y;
    }
    
    public Rational getX() {return new Rational(b,a);}
    public Rational getY() {return new Rational(c,a);}
    public long getA() {return a;}
    public long getB() {return b;}
    public long getC() {return c;}
    
    public Point mul(Rational lambda) {
        return new Point(getX().mul(lambda), getY().mul(lambda));
    }
    
    public Point add(Point p) {
        return new Point(getX().add(p.getX()), getY().add(p.getY()));
    }
    
    @Override
    public String toString() {
        return String.format("[%s, %s]", getX().toString(), getY().toString());
    }
    
}

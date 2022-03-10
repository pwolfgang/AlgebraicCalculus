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
    
   
    public final long a, b, c;
    
    public static final Point O = new Point(new Rational(0), new Rational(0));
    
    public Point(Rational x, Rational y) {
        Rational lcm = new Rational(Int.lcm(x.den, y.den));
        this.a = lcm.num;
        this.b = x.mul(lcm).num;
        this.c = y.mul(lcm).num;
    }
    
    public Point(long x, long y) {
        this.a = 1;
        this.b = x;
        this.c = y;
    }
    
    public Point(long a, long b, long c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public Rational getX() {return new Rational(b,a);}
    public Rational getY() {return new Rational(c,a);}
    
    public Point mul(Rational lambda) {
        return new Point(getX().mul(lambda), getY().mul(lambda));
    }
    
    public Point add(Point p) {
        return new Point(getX().add(p.getX()), getY().add(p.getY()));
    }
    
    public Point add(Vector v) {
        return new Point(getX().add(v.x), getY().add(v.y));
    }
    
    public Vector sub(Point p) {
        return new Vector(this, p);
    }
    
    @Override
    public String toString() {
        return String.format("[%s, %s]", getX().toString(), getY().toString());
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (this.getClass() == o.getClass()) {
            var p = (Point) o;
            return getX().equals(p.getX()) && getY().equals(p.getY());  
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.a ^ (this.a >>> 32));
        hash = 59 * hash + (int) (this.b ^ (this.b >>> 32));
        hash = 59 * hash + (int) (this.c ^ (this.c >>> 32));
        return hash;
    }
    
}

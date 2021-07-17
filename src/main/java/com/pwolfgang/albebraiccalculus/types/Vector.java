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
public class Vector {
    public final Rational x;
    public final Rational y;
    
    public Vector(Rational x, Rational y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector(long x, long y){
        this(new Rational(x), new Rational(y));
    }
    
    public Vector(Point p1, Point p2) {
        long lcm = Rational.lcm(p1.a, p2.a);
        long p1Scale = lcm/p1.a;
        long p2Scale = lcm/p2.a;
        long scaledP1b = p1Scale * p1.b;
        long scaledP1c = p1Scale * p1.c;
        long scaledP2b = p2Scale * p2.b;
        long scaledP2c = p2Scale * p2.c;
        this.x = new Rational(scaledP2b - scaledP1b, lcm);
        this.y = new Rational(scaledP2c - scaledP1c, lcm);
    }
    
    public Vector add(Vector other) {
        return new Vector(x.add(other.x), y.add(other.y));
    }
    
    public Vector sub(Vector other) {
        return new Vector(x.sub(other.x), y.sub(other.y));
    }
    
    public Vector scale(Rational lambda) {
        return new Vector(x.mul(lambda), y.mul(lambda));
    }
    
    public Rational dot(Vector other) {
        return x.mul(other.x).add(y.mul(other.y));
    }
    
    public Rational cross(Vector other) {
        return (x.mul(other.y).sub(y.mul(other.x)));
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (this.getClass() == o.getClass()) {
            Vector other = (Vector)o;
            return x.equals(other.x) && y.equals(other.y);
        } else {
            return false;
        }
    }
    
    public String toString() {
        return String.format("(%s,%s)", x.toString(), y.toString());
    }
    
}

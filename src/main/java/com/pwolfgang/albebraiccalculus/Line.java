/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus;

import java.util.Objects;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class Line {
    public final Rational a;
    public final Rational b;
    public final Rational c;
    
    public Line(long a, long b, long c) {
        this(new Rational(a), new Rational(b), new Rational(c));
    }
    
    public Line (Rational a, Rational b, Rational c) {
        if (Rational.ZERO.equals(a) && Rational.ZERO.equals(b) && Rational.ZERO.equals(c)) {
            throw new IllegalArgumentException(String.format("%s and %s and %s are all zero", 
                    a.toString(), b.toString(), c.toString()));
        }
        long num1 = a.num;
        long den1 = a.den;
        long num2 = b.num;
        long den2 = b.den;
        long num3 = c.num;
        long den3 = c.den;
        long den = den1 * den2 * den3;
        num1 *= den / den1;
        num2 *= den / den2;
        num3 *= den / den3;
        long gcd = Rational.gcd(Rational.gcd(num1, num2), num3);
        this.a = new Rational(num1/gcd);
        this.b = new Rational(num2/gcd);
        this.c = new Rational(num3/gcd);
   }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (Line.class == o.getClass()) {
            Line el = (Line)o;
            return a.equals(el.a) && b.equals(el.b) && c.equals(el.c);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.a);
        hash = 53 * hash + Objects.hashCode(this.b);
        hash = 53 * hash + Objects.hashCode(this.c);
        return hash;
    }
    
    @Override
    public String toString() {
        return String.format("%s*x + %s*y = %s", a.toString(), b.toString(), c.toString());
    }
    
    public boolean isParallel(Line other) {
        return a.mul(b).equals(b.mul(other.a));
    }
    
    public static Line join(Point p1, Point p2) {
        Rational x1 = p1.x;
        Rational y1 = p1.y;
        Rational x2 = p2.x;
        Rational y2 = p2.y;
        Rational a = y1.sub(y2);
        Rational b = x2.sub(x1);
        Rational c = a.mul(x1).add(b.mul(y1));
        return new Line(a, b, c);
    }
    
    public boolean passesThrough(Point p) {
        return a.mul(p.x).add(b.mul(p.y)).equals(c);
    }
    
    public boolean areCollinear(Point p1, Point p2, Point p3) {
        Line l1 = join(p1, p3);
        return l1.passesThrough(p3);
    }
    
    public static Point meet(Line l1, Line l2) {
        return null;
    }
    
}

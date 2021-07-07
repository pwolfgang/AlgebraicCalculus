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
public class Rational implements Comparable<Rational> {
    
    public final long num;
    public final long den;
    public Rational(long num, long den) {
        if (den == 0) throw new IllegalArgumentException("Div by Zero");
        long g = gcd(num, den);
        long n = num / g;
        long d = den / g;
        if (d < 0) {
            n = -n;
            d = -d;
        }
        this.num = n;
        this.den = d;
    }
    
    public Rational(long num) {
        this(num, 1);
    }
    
    public double toDouble() {
        return (double)num/(double)den;
    }
    @Override
    public String toString() {
        if (den == 1) {
            return Long.toString(num);
        } else {
            return num + "/" + den;
        }
    }
    public static Rational mediant(Rational left, Rational right) {
        return new Rational(left.num + right.num, left.den + right.den);
    }
    public static final Rational ONE = new Rational(1,1);
    public static final Rational ZERO = new Rational(0,1);
    
    public static long gcd(long d1, long d2) {
        if (d2 == 0) return d1;
        return gcd(d2, d1 % d2);
    }
    
    public static long lcm(long d1, long d2) {
        return d1 * d2 / gcd(d1, d2);
    }
    
    public Rational add(Rational other) {
        long commonD = lcm(den, other.den);
        long n1 = num * commonD / den;
        long n2 = other.num * commonD / other.den;
        return new Rational(n1 + n2, commonD);
    }
    
    public Rational neg() {
        return new Rational(-num, den);
    }
    
    public Rational sub(Rational other) {
        return add(other.neg());
    }
    
    public Rational mul(Rational other) {
        return new Rational(num * other.num, den * other.den);
    }
    
    public Rational div(Rational other) {
        return new Rational(num * other.den, den * other.num);
    }
    
    @Override
    public int compareTo(Rational other) {
        Rational delta = sub(other);
        if (delta.num < 0) return -1;
        if (delta.num > 0) return +1;
        return 0;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (o.getClass() == Rational.class) {
            Rational r = (Rational)o;
            return num*r.den == den*r.num;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.num ^ (this.num >>> 32));
        hash = 59 * hash + (int) (this.den ^ (this.den >>> 32));
        return hash;
    }
}

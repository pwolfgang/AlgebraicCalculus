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
public class Line {

    private final long r, s, t;

    public Line(Point p1, Point p2) {
        this(p1.b * p2.c - p1.c * p2.b, p1.c * p2.a - p1.a * p2.c, p1.a * p2.b - p1.b * p2.a);
    }

    public Line(long r, long s, long t) {
        long gcd = Rational.gcd(r, s);
        gcd = Rational.gcd(gcd, t);
        if (r > 0) {
            gcd = -gcd;
        }
        if (gcd == 0) {
            this.r = 0;
            this.s = 0;
            this.t = 0;
        } else {
            this.r = r / gcd;
            this.s = s / gcd;
            this.t = t / gcd;
        }
    }

    public Line(Rational a, Rational b, Rational c) {
        long lcm = Rational.lcm(a.den, b.den);
        lcm = Rational.lcm(lcm, c.den);
        Rational lcmR = new Rational(lcm);
        r = -c.mul(lcmR).num;
        s = a.mul(lcmR).num;
        t = b.mul(lcmR).num;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (Line.class == o.getClass()) {
            Line el = (Line) o;
            return r == el.r && s == el.s && t == el.t;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (int) (this.r ^ (this.r >>> 32));
        hash = 19 * hash + (int) (this.s ^ (this.s >>> 32));
        hash = 19 * hash + (int) (this.t ^ (this.t >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return String.format("%d*x + %d*y = %d", s, t, -r);
    }

    public boolean isParallel(Line other) {
        return s * other.t == t * other.s;
    }

    public static Line join(Point p1, Point p2) {
        return new Line(p1, p2);
    }

    public boolean passesThrough(Point p) {
        return r * p.a + s * p.b + t * p.c == 0;
    }

    public boolean areCollinear(Point p1, Point p2, Point p3) {
        Line l1 = join(p1, p3);
        return l1.passesThrough(p3);
    }

    public static Point meet(Line l1, Line l2) {
        return new Point(l1.s * l2.t - l1.t * l2.s, l1.t * l2.r - l1.r * l2.t, l1.r * l2.s - l1.s * l2.r);
    }
    
    public Rational slope() {
        return new Rational(-s, t);
    }
    
    public static Line pointSlope(Point p, Rational m) {
        Point p2 = new Point(p.getX().add(Rational.ONE), p.getY().add(m));
        return join(p, p2);
    }

}

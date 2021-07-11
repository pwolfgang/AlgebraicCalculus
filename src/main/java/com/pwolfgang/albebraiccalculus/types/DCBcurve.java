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
public class DCBcurve {
    
    public final Point p0;
    public final Point p1;
    public final Point p2;
    public final Point p3;
    
    public DCBcurve(Point p0, Point p1, Point p2) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = null;
    }

    public DCBcurve(Point p0, Point p1, Point p2, Point p3) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    
    public Point r(Rational lambda) {
        if (p3 == null) return r2(lambda);
        else return r3(lambda);
    }
    
    Point r2(Rational lambda) {
        var q0 = scale(lambda, p0, p1);
        var q1 = scale(lambda, p1, p2);
        var r  = scale(lambda, q0, q1);
        return r;
    }
    
    Point r3(Rational lambda) {
        var q0 = scale(lambda, p0, p1);
        var q1 = scale(lambda, p1, p2);
        var q2 = scale(lambda, p2, p3);
        var r0 = scale(lambda, q0, q1);
        var r1 = scale(lambda, q1, q2);
        var s = scale(lambda, r0, r1);
        return s;
    }
    
    private static Point scale(Rational lambda, Point p0, Point p1) {
        return p0.mul(Rational.ONE.sub(lambda)).add(p1.mul(lambda));
    }
    
}

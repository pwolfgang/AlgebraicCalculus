/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import com.pwolfgang.albebraiccalculus.Matrix;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class OSide {
    
    private final Point p1;
    private final Point p2;
    private final Line line;
    
    public OSide(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.line = new Line(p1, p2);
    }
    
    public Rational area() {
        return Rational.HALF.mul(p1.getX().mul(p2.getY()).sub(p2.getX().mul(p1.getY())));
    }
    
    public boolean isInside(Point p) {
//        if (!line.passesThrough(p)) return false;
        var aXmBx = p1.getX().sub(p2.getX());
        var aXmCx = p1.getX().sub(p.getX());
        var lambda = aXmCx.div(aXmBx);
        if (Rational.ZERO.compareTo(lambda) > 0) return false;
        return(Rational.ONE.compareTo(lambda) > 0);
    }
    
}

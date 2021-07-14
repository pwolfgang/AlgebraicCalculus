/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import java.util.Arrays;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class OSide {
    
    private final Point p1;
    private final Point p2;
    
    public OSide(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    
    public Rational area() {
        if (p1.equals(p2)) return Rational.ZERO;
        return Rational.HALF.mul(p1.getX().mul(p2.getY()).sub(p2.getX().mul(p1.getY())));
    }
    
    public boolean isInside(Point p) {
        var aXmBx = p1.getX().sub(p2.getX());
        var aXmCx = p1.getX().sub(p.getX());
        var lambdax = aXmCx.div(aXmBx);
        var aYmBy = p1.getY().sub(p2.getY());
        var aYmCy = p1.getY().sub(p.getY());
        var lambday = aYmCy.div(aYmBy);
        if (!lambdax.equals(lambday)) return false;
        if (Rational.ZERO.compareTo(lambdax) > 0) return false;
        return(Rational.ONE.compareTo(lambdax) > 0);
    }
    
    public boolean isLeftOf(Point p) {
        var cops = new COPS(Arrays.asList(p1, p2, p));
        var copsArea = cops.area();
        return (copsArea.compareTo(Rational.ZERO) >= 0);
    }
    
    public boolean isRightOf(Point p) {
        var cops = new COPS(p1, p2, p);
        var copsArea = cops.area();
        return (copsArea.compareTo(Rational.ZERO) <= 0);
    }

    public boolean isStrictlyLeftOf(Point p) {
        var cops = new COPS(p1, p2, p);
        var copsArea = cops.area();
        return (copsArea.compareTo(Rational.ZERO) > 0);
    }
    
    public boolean isStrictlyRightOf(Point p) {
        var cops = new COPS(Arrays.asList(p1, p2, p));
        var copsArea = cops.area();
        return (copsArea.compareTo(Rational.ZERO) < 0);
    }
    
    public boolean oppositeSides(Point a, Point b) {
        boolean leftOfA = isLeftOf(a);
        boolean rightOfB = isRightOf(b);
        boolean rightOfA = isRightOf(a);
        boolean leftOfB = isLeftOf(b);
        if (leftOfA && rightOfB) return true;
        return rightOfA && leftOfB;
    }
    
    public static boolean crosses(OSide ab, OSide cd) {
        return ab.oppositeSides(cd.p1, cd.p2) && cd.oppositeSides(ab.p1, ab.p2);
    }
    
    public int crossingNumber(OSide cd) {
        Point c = cd.p1;
        Point d = cd.p2;
        if (!crosses(this, cd)) return 0;
        if (isRightOf(c) && isLeftOf(d))return 1;
        if (isLeftOf(c) && isRightOf(d)) return -1;
        throw new RuntimeException(String.format("%s and %s crossingNumber not defined%n", 
                this.toString(), cd.toString()));
    }
}

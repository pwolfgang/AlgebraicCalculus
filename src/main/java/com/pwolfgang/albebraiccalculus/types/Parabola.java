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
public class Parabola {
    
    public static Point point(Rational t) {
        return new Point(t, t.mul(t));
    }
    
    public static OSide oSide(Rational t1, Rational t2) {
        return new OSide(point(t1), point(t2));
    }
    
    public static Triangle triangle(Rational t1, Rational t2, Rational t3) {
        Point p1 = point(t1);
        Point p2 = point(t2);
        Point p3 = point(t3);
        return new Triangle(p1, p2, p3);
    }
    
}

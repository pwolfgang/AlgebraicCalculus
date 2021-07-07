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
public class Triangle {
    
    public final Point p1;
    public final Point p2;
    public final Point p3;
    
    public Triangle (Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    
    public Rational area() {
        OSide s1 = new OSide(p1, p2);
        OSide s2 = new OSide(p2, p3);
        OSide s3 = new OSide(p3, p1);
        return s1.area().add(s2.area()).add(s3.area());
    }
    
    
}

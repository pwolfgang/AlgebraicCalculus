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
public class OSide {
    
    public final Point p1;
    public final Point p2;
    
    public OSide(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    
    public Rational signedArea() {
        return Rational.HALF.mul(p1.x.mul(p2.y).sub(p2.x.mul(p1.y)));
    }
    
}

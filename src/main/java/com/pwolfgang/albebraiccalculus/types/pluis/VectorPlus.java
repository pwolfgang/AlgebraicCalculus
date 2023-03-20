/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types.pluis;

import com.pwolfgang.albebraiccalculus.types.*;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class VectorPlus {
    public final RationalPlus x;
    public final RationalPlus y;
    
    public VectorPlus(RationalPlus x, RationalPlus y) {
        this.x = x;
        this.y = y;
    }
    
    public VectorPlus(long x, long y){
        this(new RationalPlus(x), new RationalPlus(y));
    }
    
    public VectorPlus(PointPlus p1, PointPlus p2) {
        var p1x = p1.getX();
        var p2x = p2.getX();
        var p2y = p2.getY();
        this.x = p2x.sub(p1x);
        this.y = p2y.sub(p2y);
    }
    
    public VectorPlus add(VectorPlus other) {
        return new VectorPlus(x.add(other.x), y.add(other.y));
    }
    
    public VectorPlus sub(VectorPlus other) {
        return new VectorPlus(x.sub(other.x), y.sub(other.y));
    }
    
    public VectorPlus scale(Rational lambda) {
        return new VectorPlus(x.mul(lambda), y.mul(lambda));
    }
    
    public RationalPlus dot(VectorPlus other) {
        return x.mul(other.x).add(y.mul(other.y));
    }
    
    public RationalPlus cross(VectorPlus other) {
        return (x.mul(other.y).sub(y.mul(other.x)));
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (this.getClass() == o.getClass()) {
            VectorPlus other = (VectorPlus)o;
            return x.equals(other.x) && y.equals(other.y);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + x.hashCode();
        hash = 59 * hash + y.hashCode();
        return hash;
    }
    
    @Override
    public String toString() {
        return String.format("(%s,%s)", x.toString(), y.toString());
    }
    
}

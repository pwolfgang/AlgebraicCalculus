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
public class PointPlus {
    
   
    final RationalPlus x, y;
    
    public static final PointPlus O = new PointPlus(new RationalPlus(0), new RationalPlus(0));
    
    public PointPlus(RationalPlus x, RationalPlus y) {
        this.x = x;
        this.y = y;
    }
    
    public PointPlus(long x, long y) {
        this.x = new RationalPlus(x);
        this.y = new RationalPlus(y);
    }
    
    
    public RationalPlus getX() {return x;}
    public RationalPlus getY() {return y;}
    
    public PointPlus mul(Rational lambda) {
        return new PointPlus(getX().mul(lambda), getY().mul(lambda));
    }

    public PointPlus mul(RationalPlus lambda) {
        return new PointPlus(getX().mul(lambda), getY().mul(lambda));
    }
    
    public PointPlus add(PointPlus p) {
        return new PointPlus(getX().add(p.getX()), getY().add(p.getY()));
    }
    
    public PointPlus add(VectorPlus v) {
        return new PointPlus(getX().add(v.x), getY().add(v.y));
    }
    
    public VectorPlus sub(PointPlus p) {
        return new VectorPlus(this, p);
    }
    
    @Override
    public String toString() {
        return String.format("[%s, %s]", getX().toString(), getY().toString());
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (this.getClass() == o.getClass()) {
            var p = (PointPlus) o;
            return getX().equals(p.getX()) && getY().equals(p.getY());  
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
}

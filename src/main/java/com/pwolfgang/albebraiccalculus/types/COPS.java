/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import com.pwolfgang.albebraiccalculus.datastructures.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringJoiner;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class COPS {
    
    public final List<Point> points;
    
    public COPS(Point... points) {
        this.points = new List(Arrays.asList(points));
    }
    
    public COPS(Collection<Point> points) {
        this.points = new List(points);
    }
    
    Rational area() {
        Rational sum = Rational.ZERO;
        for (int i = 0; i < points.size(); i++) {
            var p1 = points.get(i);
            Point p2;
            if (i == points.size()-1) {
                p2 = points.get(0);
            } else {
                p2 = points.get(i+1);
            }
            Rational s = new OSide(p1, p2).area();
            sum = sum.add(s);
        }
        return sum;
    }
    
    @Override
    public String toString() {
        var sj = new StringJoiner(",", "<[","]>");
        points.forEach((var p) -> sj.add(p.toString()));
        return sj.toString();
    }
}

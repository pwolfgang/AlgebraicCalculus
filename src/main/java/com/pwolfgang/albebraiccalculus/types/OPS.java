/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import com.pwolfgang.albebraiccalculus.datastructures.List;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class OPS {
    public final List<Point> points;
    
    public OPS(Point... points) {
        this.points = new List<>(Arrays.asList(points));
    }
    
    public OPS(Collection<Point> points) {
        this.points = new List<>(points);
    }
    
    Rational area() {
        Rational sum = Rational.ZERO;
        for (int i = 0; i < points.size()-1; i++) {
            var p1 = points.get(i);
            var p2 = points.get(i+1);
            Rational s = new OSide(p1, p2).area();
//            System.out.printf("%s + %s == %s%n", p1.toString(), p2.toString(), s.toString());
            sum = sum.add(s);
//            System.out.printf("sum == %s%n", sum.toString());
        }
        return sum;
    }
    
    @Override
    public String toString() {
        return points.toString();
    }
    
}

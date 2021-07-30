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
public class DCBcurvePlus {
    
    public final PointPlus p0;
    public final PointPlus p1;
    public final PointPlus p2;
    public final PointPlus p3;
    
    public DCBcurvePlus(PointPlus p0, PointPlus p1, PointPlus p2) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = null;
    }

    public DCBcurvePlus(PointPlus p0, PointPlus p1, PointPlus p2, PointPlus p3) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    
  
    public PointPlus r3(RationalPlus lambda) {
        System.out.println("p0 = " + p0);
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
        System.out.println("p3 = " + p3);
        System.out.println("lambda = " + lambda);
        var q0 = scale(lambda, p0, p1);
        System.out.println("q0 = " + q0);
        var q1 = scale(lambda, p1, p2);
        System.out.println("q1 = " + q1);
        var q2 = scale(lambda, p2, p3);
        System.out.println("q2 = " + q2);
        var r0 = scale(lambda, q0, q1);
        System.out.println("r0 = " + r0);
        var r1 = scale(lambda, q1, q2);
        System.out.println("r1 = " + r1);
        var s = scale(lambda, r0, r1);
        return s;
    }
    
    private static PointPlus scale(RationalPlus lambda, PointPlus p0, PointPlus p1) {
        var oneMinusLambda = RationalPlus.ONE.sub(lambda);
        var p0Scaled = p0.mul(oneMinusLambda);
        var p1Scaled = p1.mul(lambda);
        var result = p0Scaled.add(p1Scaled);
        return result;
    }
    
}

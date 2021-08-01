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
public class DCBcurve {
    
    public final Point p0;
    public final Point p1;
    public final Point p2;
    public final Point p3;
    
    public DCBcurve(Point p0, Point p1, Point p2) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = null;
    }

    public DCBcurve(Point p0, Point p1, Point p2, Point p3) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    
    public Point r(Rational lambda) {
        if (p3 == null) return r2(lambda);
        else return r3(lambda);
    }
    
    Point r2(Rational lambda) {
        var q0 = scale(lambda, p0, p1);
        var q1 = scale(lambda, p1, p2);
        var r  = scale(lambda, q0, q1);
        return r;
    }
    
    Point r3(Rational lambda) {
        var q0 = scale(lambda, p0, p1);
        var q1 = scale(lambda, p1, p2);
        var q2 = scale(lambda, p2, p3);
        var r0 = scale(lambda, q0, q1);
        var r1 = scale(lambda, q1, q2);
        var s = scale(lambda, r0, r1);
        return s;
    }
    
    private static Point scale(Rational lambda, Point p0, Point p1) {
        Rational oneMinusLambda = Rational.ONE.sub(lambda);
        Point p0Scaled = p0.mul(oneMinusLambda);
        Point p1Scaled = p1.mul(lambda);
        Point result = p0Scaled.add(p1Scaled);
        return result;
    }
    
    public PolyNumber[] toPolyNumber() {
        if (p3 == null) {
            return toPolyNumber2();
        } else {
            return toPolyNumber3();
        }
    }
    
    PolyNumber[] toPolyNumber2() {
        PolyNumber[] r = new PolyNumber[2];
        r[0] = B02.mul(p0.getX()).add(B12.mul(p1.getX())).add(B22.mul(p2.getX()));
        r[1] = B02.mul(p0.getY()).add(B12.mul(p1.getY())).add(B22.mul(p2.getY()));
        return r;
    }
    
    
    PolyNumber[] toPolyNumber3() {
        PolyNumber[] r = new PolyNumber[2];
        r[0] = B03.mul(p0.getX()).add(B13.mul(p1.getX())).add(B23.mul(p2.getX())).add(B33.mul(p3.getX()));
        r[1] = B03.mul(p0.getY()).add(B13.mul(p1.getY())).add(B23.mul(p2.getY())).add(B33.mul(p3.getY()));
        return r;
    }
    
    private static final PolyNumber LAMBDA = new PolyNumber(0, 1);
    private static final PolyNumber LAMBDA_2 = new PolyNumber(0,0,1);
    private static final PolyNumber LAMBDA_3 = new PolyNumber(0,0,0,1);
    private static final PolyNumber ONE_MINUS_LAMBDA = new PolyNumber(1, -1);
    private static final PolyNumber ONE_MINUS_LAMBDA_2 = ONE_MINUS_LAMBDA.mul(ONE_MINUS_LAMBDA);
    private static final PolyNumber ONE_MINUS_LAMBDA_3 = ONE_MINUS_LAMBDA_2.mul(ONE_MINUS_LAMBDA);
    private static final PolyNumber B03 = ONE_MINUS_LAMBDA_3;
    private static final PolyNumber B13 = ONE_MINUS_LAMBDA_2.mul(LAMBDA).mul(new Rational(3));
    private static final PolyNumber B23 = ONE_MINUS_LAMBDA.mul(LAMBDA_2).mul(new Rational(3));
    private static final PolyNumber B33 = LAMBDA_3;
    private static final PolyNumber B02 = ONE_MINUS_LAMBDA_2;
    private static final PolyNumber B12 = ONE_MINUS_LAMBDA.mul(LAMBDA).mul(Rational.TWO);
    private static final PolyNumber B22 = LAMBDA_2;
    
    
    
}

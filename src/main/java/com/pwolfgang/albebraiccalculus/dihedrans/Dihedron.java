/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.dihedrans;

import com.pwolfgang.albebraiccalculus.SqMatrix;
import com.pwolfgang.albebraiccalculus.types.Rational;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class Dihedron {
    
    public static final SqMatrix ONE = new SqMatrix(new long[]{1, 0, 0, 1},2);
    public static final SqMatrix I = new SqMatrix(new long[]{0, 1, -1, 0}, 2);
    public static final SqMatrix J = new SqMatrix(new long[]{0, 1, 1, 0},2);
    public static final SqMatrix K = new SqMatrix(new long[]{1, 0, 0, -1},2);
    public static final SqMatrix E = new SqMatrix(new long[]{0, 0, 1, 0},2);
    
    public final Rational t, x, y, z;
    
    public Dihedron(Rational t, Rational x, Rational y, Rational z) {
        this.t = t;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Dihedron(long t, long x, long y, long z) {
        this.t = Rational.of(t);
        this.x = Rational.of(x);
        this.y = Rational.of(y);
        this.z = Rational.of(z);
    }
    
    public Dihedron add(Dihedron d) {
        return new Dihedron(t.add(d.t), x.add(d.x), y.add(d.y), z.add(d.z));
    }
    
    public Dihedron mul(Dihedron d) {
        Rational[] v1 = new Rational[]{x, y, z};
        Rational[] v2 = new Rational[]{d.x, d.y, d.z};
        var t = this.t.mul(d.t).add(dot(v1, v2));
        Rational[] v = cross(v1, v2);
        return new Dihedron(t, v[0], v[1], v[2]);
    }
    
    private Rational dot(Rational[] v1, Rational[] v2) {
        return v1[0].mul(v2[0]).sub(v1[1].mul(v2[1])).sub(v1[2].mul(v2[2]));
    }
    
    private Rational[] cross(Rational[] v1, Rational[] v2) {
        Rational[] v = new Rational[3];
        v[0] = v1[1].mul(v2[2]).neg().add(v2[2].mul(v1[1]));
        v[1] = v1[3].mul(v2[0]).sub(v1[0].mul(v2[3]));
        v[3] = v1[0].mul(v2[1]).sub(v1[1].mul(v2[0]));
        return v;
    }
    
}

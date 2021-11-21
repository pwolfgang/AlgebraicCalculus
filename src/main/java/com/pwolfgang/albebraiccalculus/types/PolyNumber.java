/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import com.pwolfgang.albebraiccalculus.SqMatrix;
import com.pwolfgang.albebraiccalculus.datastructures.List;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class PolyNumber {
    
    final Rational[] aS;
    
    public static final PolyNumber ZERO = new PolyNumber(0);
    public static final PolyNumber ONE = new PolyNumber(1);
    
    PolyNumber() {
        aS = null;
    }
    
    public PolyNumber(Rational... r) {
        List<Rational> tempList = new List<>(Arrays.asList(r));
        trimTrailingZeros(tempList);
        aS = tempList.toArray(new Rational[tempList.size()]);
    }
    
    public PolyNumber(long... n) {
        List<Rational> tempList = new List<>();
        for (int i = 0; i < n.length; i++) {
            tempList.add(new Rational(n[i]));
        }
        trimTrailingZeros(tempList);
        aS = tempList.toArray(new Rational[tempList.size()]);
    }
    
    public PolyNumber(List<Rational> list) {
        List<Rational> tempList = new List<>(list);
        trimTrailingZeros(tempList);
        aS = tempList.toArray(new Rational[tempList.size()]);
    }
    
    private void trimTrailingZeros(List<Rational> list) {
        int i = list.size()-1;
        while (i > 0 && list.get(i).equals(Rational.ZERO)) {
            list.remove(i);
            i--;
        }
    }
    
    public static PolyNumber fromPoints(Point... points) {
        if (points.length == 0) {
            throw new IllegalArgumentException("Number of points is zero");
        }
        if (points.length == 1) {
            return new PolyNumber(points[0].getY().div(points[0].getX()));
        }
        int numPoints = points.length;
        Rational[] xS = new Rational[numPoints*numPoints];
        Rational[] yS = new Rational[numPoints];
        for (int i = 0; i < numPoints; i++) {
            var point = points[i];
            var x = point.getX();
            var y = point.getY();
            yS[i] = y;
            int row = numPoints*i;
            xS[row] = Rational.ONE;
            for (int j = row+1; j < row+numPoints; j++) {
                xS[j] = xS[j-1].mul(x);
            }
        }
        var m = new SqMatrix(xS, numPoints);
        Rational[] aS = m.inv().mul(yS);
        return new PolyNumber(aS);
    }
    
    public PolyNumber add(PolyNumber p) {
        List<Rational> result = new List<>();
        int maxThis = aS.length;
        int maxOther = p.aS.length;
        int i = 0;
        int j = 0;
        while (i < maxThis && j < maxOther) {
            result.add(aS[i++].add(p.aS[j++]));
        }
        while (i < maxThis) {
            result.add(aS[i++]);
        }
        while (j < maxOther) {
            result.add(p.aS[j++]);
        }
        return new PolyNumber(result);
    }

    public PolyNumber sub(PolyNumber p) {
        List<Rational> result = new List<>();
        int maxThis = aS.length;
        int maxOther = p.aS.length;
        int i = 0;
        int j = 0;
        while (i < maxThis && j < maxOther) {
            result.add(aS[i++].sub(p.aS[j++]));
        }
        while (i < maxThis) {
            result.add(aS[i++]);
        }
        while (j < maxOther) {
            result.add(p.aS[j++].neg());
        }
        return new PolyNumber(result);
    }
    
    public PolyNumber mul(Rational lambda) {
        Rational[] result = new Rational[aS.length];
        for (int i = 0; i < aS.length; i++) {
            result[i] = lambda.mul(aS[i]);
        }
        return new PolyNumber(result);
    }
    
    public PolyNumber mul(long k) {
        return mul(new Rational(k));
    }
    
    public PolyNumber shift(int k) {
        Rational[] result = new Rational[aS.length + k];
        for (int i = 0; i < k; i++) {
            result[i] = Rational.ZERO;
        }
        for (int i = 0; i < aS.length; i++) {
            result[i+k] = aS[i];
        }
        return new PolyNumber(result);
    }
    
    public PolyNumber mul(PolyNumber p) {
        PolyNumber result = new PolyNumber(Rational.ZERO);
        for (int i = 0; i < aS.length; i++) {
            PolyNumber term = p.mul(aS[i]).shift(i);
            result = result.add(term);
        }
        return result;
    }
    
    public int deg() {
        return aS.length-1;
    }

    
    public PolyNumber[] div(PolyNumber g) {
        PolyNumber r = new PolyNumber(aS);
        PolyNumber q = new PolyNumber(0);
        while (r.deg() >= g.deg() && !PolyNumber.ZERO.equals(r)) {
            var t = r.aS[r.deg()].div(g.aS[g.deg()]);
            int d = r.deg() - g.deg();
            Rational[] a = new Rational[d+1];
            for (int i = 0; i < d; i++) {
                a[i] = Rational.ZERO;
            }
            a[d] = t;
            var p = new PolyNumber(a);
            r = r.sub(p.mul(g));
            q = q.add(p);
        }
        return new PolyNumber[]{q, r};
    }
    
    public static PolyNumber gcd(PolyNumber a, PolyNumber b) {
        System.out.printf("a: %s%n", a.toString());
        System.out.printf("b: %s%n", b.toString());
        if (b.equals(PolyNumber.ZERO)) {
            return a;
        }
        var r = a.div(b)[1];
        return gcd(b, r);
    }
    
    public static PolyNumber[] extendedGCD(PolyNumber a, PolyNumber b) {
        return eGCD(a, b, PolyNumber.ONE, PolyNumber.ZERO, PolyNumber.ZERO, PolyNumber.ONE);
    }
    
    public static PolyNumber[]eGCD(PolyNumber a, PolyNumber b, PolyNumber ax, PolyNumber ay, PolyNumber bx, PolyNumber by) {
        PolyNumber[] qr = a.div(b);
        PolyNumber q = qr[0];
        PolyNumber r = qr[1];
        if (r.equals(PolyNumber.ZERO)) {
            return new PolyNumber[]{b, bx, by};
        }
        PolyNumber x = ax.sub(q.mul(bx));
        PolyNumber y = ay.sub(q.mul(by));
        return eGCD(b, r, bx, by, x, y);        
    }
    
    public static int[] div(int a, int b) {
        int q = a/b;
        int r = a - q*b;
        return new int[]{q, r};
    }
    
    public static int[] eGCD(int a, int b) {
        return eGCD(a, b, 1, 0, 0, 1);
    }
    
    public static int[] eGCD(int a, int b, int ax, int ay, int bx, int by) {
        int[] qr = div(a, b);
        int q = qr[0];
        int r = qr[1];
        if (r == 0) {
            return new int[]{b, bx, by};
        }
        int x = ax - q*bx;
        int y = ay - q*by;
        return eGCD(b, r, bx, by, x, y);
    }
    
    public PolyNumber div(Rational x) {
        List<Rational> q = new List<>();
        int j = 0;
        while (j < aS.length && aS[j].equals(Rational.ZERO)) {
            q.add(Rational.ZERO);
            j++;
        }
        if (j < aS.length) {
            q.add(aS[j].div(x));
        }
        return new PolyNumber(q);
    }
    
    public Rational eval(long x) {
        return eval(new Rational(x));
    }
    
    public Rational eval(Rational x) {
        Rational result = Rational.ZERO;
        for (int i = aS.length-1; i >= 0; i--) {
            result = result.mul(x).add(aS[i]);
        }
        return result;
    }

    public PolyNumber eval(PolyNumber x) {
        PolyNumber result = new PolyNumber(0);
        for (int i = aS.length-1; i >= 0; i--) {
            result = result.mul(x).add(new PolyNumber(aS[i]));
        }
        return result;
    }
    
    public PolyNumber D() {
        if (aS.length == 1) {
            return new PolyNumber(new Rational[]{Rational.ZERO});
        }
        Rational[] result = new Rational[aS.length-1];
        for (int i = 1; i < aS.length; i++) {
            result[i-1] = aS[i].mul(new Rational(i));
        }
        return new PolyNumber(result);
    }
    
    public PolyNumber delta() {
        return eval(new PolyNumber(1,1)).sub(this);
    }
    
    public PolyNumber del() {
        return this.sub(eval(new PolyNumber(-1,1)));
    }
    
    public PolyNumber S() {
        Rational[] result = new Rational[aS.length+1];
        result[0] = Rational.ZERO;
        for (int i = 0; i < aS.length; i++) {
            result[i+1] = aS[i].mul(new Rational(1, i+1));
        }
        return new PolyNumber(result);
    }
    
    public Rational[] expandToDegree(int d) {
        Rational[] r = new Rational[d];
        int n = aS.length;
        for (int i = 0; i < n && i < d; i++) {
            r[i] = aS[i];
        }
        for (int i = n; i < d; i++) {
            r[i] = Rational.ZERO;
        }
        return r;
    }
    
    public String toString() {
        var sj = new StringJoiner(" + ");
        for (int i = 0; i < aS.length; i++) {
            Rational term = aS[i];
            if (!Rational.ZERO.equals(term)) {
                if (Rational.ONE.equals(term)) {
                    if (i == 0) {
                        sj.add("1");
                    } else if (i == 1) {
                        sj.add("α");
                    } else {
                        sj.add(String.format("α^%d",i));
                    }
                }
                else if (Rational.MINUS_ONE.equals(term)) {
                    if (i == 0) {
                        sj.add("-1");
                    } else if (i == 1) {
                        sj.add("-α");
                    } else {
                        sj.add(String.format("-α^%d",i));
                    }
                } else {
                    if (i == 0) {
                        sj.add(String.format("%s", term));
                    } else if (i == 1) {
                        sj.add(String.format("%sα", term));
                    } else {
                        sj.add(String.format("%sα^%d",term, i));
                    }
                }
            }
        }
        var s = sj.toString();
        if (s.isBlank()) {
            return "0";
        } else {
            return s;
        }
    }
    
    public String toStringDouble() {
         var sj = new StringJoiner(" + ");
        for (int i = 0; i < aS.length; i++) {
            Rational term = aS[i];
            if (!Rational.ZERO.equals(term)) {
                if (Rational.ONE.equals(term)) {
                    if (i == 0) {
                        sj.add("1");
                    } else if (i == 1) {
                        sj.add("α");
                    } else {
                        sj.add(String.format("α^%d",i));
                    }
                } else {
                    if (i == 0) {
                        sj.add(String.format("%f", term.toDouble()));
                    } else if (i == 1) {
                        sj.add(String.format("%fα", term.toDouble()));
                    } else {
                        sj.add(String.format("%fα^%d",term.toDouble(), i));
                    }
                }
            }
        }
        var s = sj.toString();
        if (s.isBlank()) {
            return "0";
        } else {
            return s;
        }       
    }
    
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (this.getClass() == o.getClass()) {
            return Arrays.equals(aS, ((PolyNumber)o).aS);
        } else {
            return false;
        }
    }
    
        
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

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
            result.add(p.aS[j++]);
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
    
    public PolyNumber div(PolyNumber p) {
        PolyNumber r = new PolyNumber(aS);
        PolyNumber q = new PolyNumber(0);
        int maxTerms = aS.length + p.aS.length;
        while (!r.equals(PolyNumber.ZERO) && q.aS.length < maxTerms) {
            var t = r.div(p.aS[0]);
            r = r.sub(p.mul(t));
            q = q.add(t);
        }
        return q;
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
    
    public PolyNumber S() {
        Rational[] result = new Rational[aS.length+1];
        result[0] = Rational.ZERO;
        for (int i = 0; i < aS.length; i++) {
            result[i+1] = aS[i].mul(new Rational(1, i+1));
        }
        return new PolyNumber(result);
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

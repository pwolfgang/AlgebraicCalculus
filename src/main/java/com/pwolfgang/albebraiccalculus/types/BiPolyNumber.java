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
public class BiPolyNumber {
    
    private final Rational[][] aS;
    
    public BiPolyNumber(Rational[][] r) {
        List<List<Rational>> tempList = new List<>();
        for (int i = 0; i < r.length; i++) {
            List<Rational> rowList = new List<>(Arrays.asList(r[i]));
            trimTrailingZeros(rowList);
            tempList.add(rowList);
        }
        trimTrailingZeros(tempList);
        aS = new Rational[tempList.size()][];
        for (int i = 0; i < tempList.size(); i++) {
            var rowList = tempList.get(i);
            aS[i] = rowList.toArray(new Rational[rowList.size()]);
        }
    }
    
    public BiPolyNumber(long[][]  n) {
        List<List<Rational>> tempList = new List<>();
        for (int i = 0; i < n.length; i++) {
            List<Rational> rowList = new List<>();
            for (int j = 0; j < n[i].length; j++) {
                rowList.add(new Rational(n[i][j]));
            }
            trimTrailingZeros(rowList);
            tempList.add(rowList);
        }
        aS = new Rational[tempList.size()][];
        for (int i = 0; i < tempList.size(); i++) {
            var rowList = tempList.get(i);
            aS[i] = rowList.toArray(new Rational[rowList.size()]);
        }
    }

    
    public BiPolyNumber(List<List<Rational>> list) {
        List<Rational> tempList = new List<>();
        list.forEach(row -> trimTrailingZeros(row));
        trimTrailingZeros(tempList);
        aS = new Rational[tempList.size()][];
        for (int j = 0; j < list.size(); j++) {
            var row = list.get(j);
            aS[j] = row.toArray(new Rational[row.size()]);
        }
    }
    
    void trimTrailingZeros(List<?> list) {
        int i = list.size()-1;
        while (i > 0) {
            Object o = list.get(i);
            if (o.getClass() == Rational.class) {
                if (Rational.ZERO.equals(o)) {
                    list.remove(i);
                    i--;
                }
            } else if (o.getClass() == List.class) {
                List<?> row = (List<?>)o;
                if (row.size() == 1 && Rational.ZERO.equals(row.get(0))) {
                    list.remove(i);
                    i--;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
    }
    
    public BiPolyNumber add(BiPolyNumber p) {
        List<List<Rational>> result = new List<>();
        int maxThis = aS.length;
        int maxOther = p.aS.length;
        int i = 0;
        int j = 0;
        while (i < maxThis && j < maxOther) {
            result.add(addRows(aS[i++],p.aS[j++]));
        }
        while (i < maxThis) {
            result.add(new List<>(Arrays.asList(aS[i++])));
        }
        while (j < maxOther) {
            result.add(new List<>(Arrays.asList(p.aS[j++])));
        }
        return new BiPolyNumber(result);
    }
    
    static List<Rational> addRows(Rational[] a, Rational[] b) {
        int aLength = a.length;
        int bLength = b.length;
        List<Rational> result = new List<>();
        int i = 0;
        int j = 0;
        while (i < aLength && j < bLength) {
            result.add(a[i++].add(b[j++]));
        }
        while (i < aLength) {
            result.add(a[i++]);
        }
        while (j < bLength) {
            result.add(b[j++]);
        }
        return result;
    }

     public BiPolyNumber sub(BiPolyNumber p) {
        List<List<Rational>> result = new List<>();
        int maxThis = aS.length;
        int maxOther = p.aS.length;
        int i = 0;
        int j = 0;
        while (i < maxThis && j < maxOther) {
            result.add(subRows(aS[i++],p.aS[j++]));
        }
        while (i < maxThis) {
            result.add(new List<>(Arrays.asList(aS[i++])));
        }
        while (j < maxOther) {
            result.add(new List<>(Arrays.asList(p.aS[j++])));
        }
        return new BiPolyNumber(result);
    }
    
    static List<Rational> subRows(Rational[] a, Rational[] b) {
        int aLength = a.length;
        int bLength = b.length;
        List<Rational> result = new List<>();
        int i = 0;
        int j = 0;
        while (i < aLength && j < bLength) {
            result.add(a[i++].sub(b[j++]));
        }
        while (i < aLength) {
            result.add(a[i++]);
        }
        while (j < bLength) {
            result.add(b[j++]);
        }
        return result;
    }
   
    public BiPolyNumber mul(Rational lambda) {
        Rational[][] newAs = new Rational[aS.length][];
        for (int i = 0; i < aS.length; i++) {
            Rational[] row = aS[i];
            newAs[i] = new Rational[row.length];
            for (int j = 0; j < row.length; j++) {
                newAs[i][j] = lambda.mul(aS[i][j]);
            }
        }
        return new BiPolyNumber(newAs);
    }


/*    
    public BiPolyNumber shift(int k) {
        List<Rational> result = new List<>();
        for (int i = 0; i < k; i++) {
            result.add(Rational.ZERO);
        }
        for (var r:aS) {
            result.add(r);
        }
        return new BiPolyNumber(result);
    }
    
    public BiPolyNumber mul(BiPolyNumber p) {
        BiPolyNumber result = new BiPolyNumber(Rational.ZERO);
        for (int i = 0; i < aS.length; i++) {
            BiPolyNumber term = p.mul(aS[i]).shift(i);
            result = result.add(term);
        }
        return result;
    }
    
    public Rational eval(Rational x) {
        Rational result = Rational.ZERO;
        for (int i = aS.length-1; i >= 0; i--) {
            result = result.mul(x).add(aS[i]);
        }
        return result;
    }

    public BiPolyNumber eval(BiPolyNumber x) {
        BiPolyNumber result = new BiPolyNumber(0);
        for (int i = aS.length-1; i >= 0; i--) {
            result = result.mul(x).add(new BiPolyNumber(aS[i]));
        }
        return result;
    }
    
    public BiPolyNumber D() {
        List<Rational> result = new List<>();
        for (int i = 1; i < aS.length; i++) {
            result.add(aS[i].mul(new Rational(i)));
        }
        if (result.isEmpty()) {
            result.add(Rational.ZERO);
        }
        return new BiPolyNumber(result);
    }
    
    public BiPolyNumber S() {
        List<Rational> result = new List<>();
        result.add(Rational.ZERO);
        for (int i = 0; i < aS.length; i++) {
            result.add(aS[i].mul(new Rational(1, i+1)));
        }
        return new BiPolyNumber(result);
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
            return Arrays.equals(aS, ((BiPolyNumber)o).aS);
        } else {
            return false;
        }
    }
 */
    
}

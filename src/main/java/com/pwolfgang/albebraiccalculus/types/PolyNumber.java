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
    
    private final List<Rational> list;
    
    public PolyNumber(Rational... r) {
        List<Rational> tempList = new List<>(Arrays.asList(r));
        trimTrailingZeros(tempList);
        list = tempList;
    }
    
    public PolyNumber(long... n) {
        List<Rational> tempList = new List<>();
        for (int i = 0; i < n.length; i++) {
            tempList.add(new Rational(n[i]));
        }
        trimTrailingZeros(tempList);
        list = tempList;
    }
    
    public PolyNumber(List<Rational> list) {
        List<Rational> tempList = new List<>(list);
        trimTrailingZeros(tempList);
        this.list = tempList;
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
        int maxThis = list.size();
        int maxOther = p.list.size();
        int i = 0;
        int j = 0;
        while (i < maxThis && j < maxOther) {
            result.add(list.get(i++).add(p.list.get(j++)));
        }
        while (i < maxThis) {
            result.add(list.get(i++));
        }
        while (j < maxOther) {
            result.add(p.list.get(j++));
        }
        return new PolyNumber(result);
    }

    public PolyNumber sub(PolyNumber p) {
        List<Rational> result = new List<>();
        int maxThis = list.size();
        int maxOther = p.list.size();
        int i = 0;
        int j = 0;
        while (i < maxThis && j < maxOther) {
            result.add(list.get(i++).sub(p.list.get(j++)));
        }
        while (i < maxThis) {
            result.add(list.get(i++));
        }
        while (j < maxOther) {
            result.add(p.list.get(j++));
        }
        return new PolyNumber(result);
    }
    
    public PolyNumber mul(Rational lambda) {
        List<Rational> result = new List<>();
        list.forEach(r -> result.add(r.mul(lambda)));
        return new PolyNumber(result);
    }
    
    public PolyNumber shift(int k) {
        List<Rational> result = new List<>();
        for (int i = 0; i < k; i++) {
            result.add(Rational.ZERO);
        }
        list.forEach(r -> result.add(r));
        return new PolyNumber(result);
    }
    
    public PolyNumber mul(PolyNumber p) {
        PolyNumber result = new PolyNumber(Rational.ZERO);
        for (int i = 0; i < list.size(); i++) {
            PolyNumber term = p.mul(list.get(i)).shift(i);
            result = result.add(term);
        }
        return result;
    }
    
    public Rational eval(Rational x) {
        Rational result = Rational.ZERO;
        for (int i = list.size()-1; i >= 0; i--) {
            result = result.mul(x).add(list.get(i));
        }
        return result;
    }

    public PolyNumber eval(PolyNumber x) {
        PolyNumber result = new PolyNumber(0);
        for (int i = list.size()-1; i >= 0; i--) {
            result = result.mul(x).add(new PolyNumber(list.get(i)));
        }
        return result;
    }
    
    public PolyNumber D() {
        List<Rational> result = new List<>();
        for (int i = 1; i < list.size(); i++) {
            result.add(list.get(i).mul(new Rational(i)));
        }
        if (result.isEmpty()) {
            result.add(Rational.ZERO);
        }
        return new PolyNumber(result);
    }
    
    public String toString() {
        var sj = new StringJoiner(" + ");
        for (int i = 0; i < list.size(); i++) {
            Rational term = list.get(i);
            if (!Rational.ZERO.equals(term)) {
                if (Rational.ONE.equals(term)) {
                    sj.add(String.format("α^%d",i));
                } else {
                    sj.add(String.format("%sα^%d",term, i));
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
            return list.equals(((PolyNumber)o).list);
        } else {
            return false;
        }
    }
    
}

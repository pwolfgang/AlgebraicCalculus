/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class KPoly extends PolyNumber {
    
    public KPoly(int deg, Rational[] aS) {
        super(deg, aS);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() == this.getClass()) {
            KPoly other = (KPoly)o;
            return Arrays.equals(aS, other.aS);
        } else {
            return false;
        }
    }
    
    /**
     * Create a String representation of the KPoly.
     *
     * @return a String representation of this KPoly.
     */
    @Override
    public String toString() {
        var sj = new StringJoiner(" + ");
        for (int i = 0; i < aS.length; i++) {
            Rational term = aS[i];
            if (!Rational.ZERO.equals(term)) {
                if (Rational.ONE.equals(term)) {
                    switch (i) {
                        case 0 -> sj.add("1");
                        case 1 -> sj.add("ε");
                        default -> sj.add(String.format("ε^%d", i));
                    }
                } else if (Rational.MINUS_ONE.equals(term)) {
                    switch (i) {
                        case 0 -> sj.add("-1");
                        case 1 -> sj.add("-ε");
                        default -> sj.add(String.format("-ε^%d", i));
                    }
                } else {
                    switch (i) {
                        case 0 -> sj.add(String.format("%s", term));
                        case 1 -> sj.add(String.format("%sε", term));
                        default -> sj.add(String.format("%sε^%d", term, i));
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
}

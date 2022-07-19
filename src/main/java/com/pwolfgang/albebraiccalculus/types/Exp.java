/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import java.util.Iterator;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class Exp extends PolySeries {
    
       
    public Iterator<PolyNumber> iterator() {
        return new Itr();
    }
    
    private class Itr implements Iterator<PolyNumber> {
        
        Rational current;
        
        int n;
        
        public Itr() {
            current = Rational.ONE;
            n = 0;
        }
        
        public boolean hasNext() {
            return true;
        }
        
        public PolyNumber next() {
            n++;
            var term = Rational.of(1, n);
            var next = current.mul(term);
            Rational[] result = new Rational[n];
            for (int i = 0; i < n-1; i++) {
                result[i] = Rational.ZERO;
            }
            result[n-1] = current;
            current = next;
            return new PolyNumber(result);
        }
    }
    
}

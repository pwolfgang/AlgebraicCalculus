/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class DivPolySeries extends PolySeries {
    
    private final PolyNumber dividend;
    private final PolyNumber divisor;
    
    public DivPolySeries(PolyNumber dividend, PolyNumber divisor) {
        this.dividend = dividend;
        this.divisor = divisor;
    }
    
    @Override
    public Iterator<PolyNumber> iterator() {
        return new DivIterator(dividend, divisor);
    }
    
    /**
     * Class to implement the Iterator returned by dividing one polynumber
     * by another.
     */
    private static class DivIterator implements Iterator<PolyNumber> {
        
        /**
         * The current remainder.
         */
        private PolyNumber r;
        /**
         * The divisor
         */
        private final PolyNumber g;
        /**
         * The index of the current term in the quotient
         */
        private int index;
        
        /**
         * Constructor;
         * @param p The dividend
         * @param g The divisior
         */
        public DivIterator(PolyNumber p, PolyNumber g) {
            this.r = new PolyNumber(p.aS);
            this.g = g;
            this.index = 0;
        }
        
        /**
         * Compute the next term in the quotient.
         * @return The next term in the quotient.
         */
        @Override
        public PolyNumber next() {
            if (hasNext()) {
                var t = r.aS[index].div(g.aS[0]);
                Rational[] a = new Rational[index+1];
                for (int i = 0; i < index; i++) {
                    a[i] = Rational.ZERO;
                }
                a[index] = t;
                var p = new PolyNumber(a);
                r = r.sub(p.mul(g));
                index++;
                return p;
            } else {
                throw new NoSuchElementException();
            }
        }
        
        /**
         * Return true if the remainder is not zero.
         * @return true if the remainder is not zero
         */
        @Override
        public boolean hasNext() {
            return !r.equals(PolyNumber.ZERO);
        }
    }
    
    
    
    
}

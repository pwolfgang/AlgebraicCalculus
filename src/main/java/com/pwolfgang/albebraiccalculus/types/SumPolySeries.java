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
public class SumPolySeries extends PolySeries {
    
    final PolySeries p;
    final PolySeries q;
    
    public SumPolySeries(PolySeries p, PolySeries q) {
        this.p = p;
        this.q = q;
    }
    
    public Iterator<PolyNumber> iterator() {
        return new SumIterator();
    }
    
    class SumIterator implements Iterator<PolyNumber> {
        Iterator<PolyNumber> pItr;
        Iterator<PolyNumber> qItr;
        public SumIterator() {
            pItr = p.iterator();
            qItr = q.iterator();
        }
        
        public boolean hasNext() {
            return pItr.hasNext() || qItr.hasNext();
        }
        
        public PolyNumber next() {
            if (pItr.hasNext()) {
                if (qItr.hasNext()) {
                    return pItr.next().add(qItr.next());
                } else {
                    return pItr.next();
                }
            } else {
                if (qItr.hasNext()) {
                    return qItr.next();
                }
                throw new NoSuchElementException();
            }
        }
    }
}

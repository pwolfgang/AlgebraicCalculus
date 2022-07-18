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
public class ProdPolySeries extends PolySeries {
    
    final PolySeries p;
    final PolySeries q;
    
    public ProdPolySeries(PolySeries p, PolySeries q) {
        this.p = p;
        this.q = q;
    }
    
    public Iterator<PolyNumber> iterator() {
        return new ProdIterator();
    }
    
    class ProdIterator implements Iterator<PolyNumber> {
        
        int currentDepth;
        int maxPdepth;
        int maxQdepth;
        
        public ProdIterator() {
            currentDepth = 0;
            maxPdepth = Integer.MAX_VALUE;
            maxQdepth = Integer.MAX_VALUE;
        }
        
        public boolean hasNext() {
            return false;
        }
        
        public PolyNumber next() {
            return null;
        }
    }
}

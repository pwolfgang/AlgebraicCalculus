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
    
    
    public ProdPolySeries(PolySeries p, PolySeries q) {
    }
    
    @Override
    public Iterator<PolyNumber> iterator() {
        return new ProdIterator();
    }
    
    class ProdIterator implements Iterator<PolyNumber> {
        
        
        public ProdIterator() {
        }
        
        @Override
        public boolean hasNext() {
            return false;
        }
        
        @Override
        public PolyNumber next() {
            return null;
        }
    }
}

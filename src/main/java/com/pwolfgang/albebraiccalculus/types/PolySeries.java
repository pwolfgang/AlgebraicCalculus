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
public abstract class PolySeries {
    
    public PolySeries() {}
    
    public abstract Iterator<PolyNumber> iterator();
    
    public PolyNumber truncate(int deg) {
        PolyNumber result = PolyNumber.ZERO;
        int n = 0;
        var itr = this.iterator();
        while (n < deg && itr.hasNext()) {
            result = result.add(itr.next());
            n++;
        }
        return result;
    }
    
    public PolySeries add(PolySeries q) {
        return new SumPolySeries(this, q);
    }
    
}

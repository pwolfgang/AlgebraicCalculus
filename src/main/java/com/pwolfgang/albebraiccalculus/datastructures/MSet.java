/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.datastructures;

import java.util.Collection;
import java.util.StringJoiner;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 * @param <T>
 */
@SuppressWarnings("serial")
public class MSet<T> extends java.util.ArrayList<T> {
    
    public MSet() {
        super();
    }
    
    public MSet(Collection<T> c) {
        super(c);
    }
    
    @Override
    public String toString() {
        var sj = new StringJoiner(" ", "[", "]");
        forEach((T t) -> sj.add(t.toString()));
        return sj.toString();
    }
    
}

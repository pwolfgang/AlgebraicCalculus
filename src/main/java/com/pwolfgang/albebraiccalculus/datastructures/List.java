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
public class List<T> extends java.util.ArrayList<T> {
    
    public List() {
        super();
    }
    
    public List(Collection<T> c) {
        super(c);
    }
    
    @Override
    public String toString() {
        var sj = new StringJoiner(",", "[", "]");
        this.forEach((T t) -> sj.add(t.toString()));
        return sj.toString();
    }
    
}

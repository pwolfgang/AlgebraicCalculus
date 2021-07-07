/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus;

import java.util.StringJoiner;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 * @param <T>
 */
public class List<T> extends java.util.LinkedList<T> {
    
    @Override
    public String toString() {
        var sj = new StringJoiner(", ", "[", "]");
        this.forEach((T t) -> sj.add(t.toString()));
        return sj.toString();
    }
    
}

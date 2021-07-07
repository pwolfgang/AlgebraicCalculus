/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.datastructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.StringJoiner;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 * @param <T>
 */
public class OSet<T> extends java.util.AbstractCollection<T> implements java.util.Collection<T> {

    private final LinkedHashSet<T> data;

    public OSet(int initialCapacity) {
        data = new LinkedHashSet<>(initialCapacity);
    }
    
    public OSet(Collection<T> c) {
        data = new LinkedHashSet<>(c.size());
        data.addAll(c);
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr<>(data.spliterator());
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean add(T t) {
        return data.add(t);
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (this == other) return true;
        if (this.getClass() == other.getClass()) {
            var o = (OSet)other;
            return data.equals(o.data);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.data);
        return hash;
    }
    
    @Override
    public String toString() {
        var sj = new StringJoiner(",", "{", "}");
        data.forEach((T t) -> sj.add(t.toString()));
        return sj.toString();
    }

    private static class Itr<T> implements Iterator<T> {

        private final Spliterator<T> spliterator;
        private boolean hasNextCalled;
        private boolean hasNextResult;
        private T nextValue;

        public Itr(Spliterator<T> spliterator) {
            this.spliterator = spliterator;
        }

        @Override
        public boolean hasNext() {
            if (!hasNextCalled) {
                hasNextResult = spliterator.tryAdvance((T t) -> {
                    nextValue = t;
                    hasNextCalled = true;
                });
            }
            return hasNextResult;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            hasNextCalled = false;
            return nextValue;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}

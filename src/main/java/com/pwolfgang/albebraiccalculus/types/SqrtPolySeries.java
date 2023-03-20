/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import static com.pwolfgang.albebraiccalculus.types.PolyNumber.ONE;
import java.util.Iterator;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class SqrtPolySeries extends PolySeries {

    private final PolyNumber polyNumber;

    public SqrtPolySeries(PolyNumber polyNumber) {
        this.polyNumber = polyNumber;
    }

    @Override
    public Iterator<PolyNumber> iterator() {
        return new SqrtIterator(polyNumber);
    }

    private static class SqrtIterator implements Iterator<PolyNumber> {

        private final PolyNumber polyNumber;
        private PolyNumber p;
        private PolyNumber pSq;

        public SqrtIterator(PolyNumber polyNumber) {
            this.polyNumber = polyNumber;
            p = ONE;
            pSq = ONE;
        }

        @Override
        public boolean hasNext() {
            return (!pSq.equals(polyNumber));
        }

        @Override
        public PolyNumber next() {
            var pp = polyNumber.sub(pSq);
            var r = pp.div(Rational.TWO);
            p = p.add(r);
            pSq = p.mul(p);
            return r;
        }
    }

}

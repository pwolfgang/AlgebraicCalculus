/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class Int {
    
    /**
     * Perform integer division of a and b
     *
     * @param a
     * @param b
     * @return the array [q, r] where q is the quotient and r is the remainder.
     */
    public static long[] div(long a, long b) {
        long q = a / b;
        long r = a - q * b;
        return new long[]{q, r};
    }
    
    /**
     * Compute the GCD of two integers
     * 
     * @param a One integer
     * @param b The other integer
     */
    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a%b);
        }
    }

    /**
     * Compute the extended GCD of integers a and b.
     *
     * @param a
     * @param b
     * @return the array [g, x, y] where ax+by=g.
     */
    public static long[] eGCD(long a, long b) {
        return eGCD(a, b, 1, 0, 0, 1);
    }

    /**
     * Recursive extended GCD for integers. At each call to the recursive
     * function the following invariants are preserved. If A and B are the
     * initial values of a and b, then gcd(a,b)==gcd(A,B). a == A*ax + B*ay b ==
     * A*bx + B*by The algorithm terminates when b divides a.
     *
     * @param a One integer
     * @param b The other integer.
     * @param ax The current value of ax
     * @param ay The current value of ay
     * @param bx The current value of bx
     * @param by The current value of by
     * @return The values [g, x, y].
     */
    public static long[] eGCD(long a, long b, long ax, long ay, long bx, long by) {
        long[] qr = div(a, b);
        long q = qr[0];
        long r = qr[1];
        if (r == 0) {
            return new long[]{b, bx, by};
        }
        long x = ax - q * bx;
        long y = ay - q * by;
        return eGCD(b, r, bx, by, x, y);
    }

}

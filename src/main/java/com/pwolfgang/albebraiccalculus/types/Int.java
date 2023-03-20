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
     * @return The gcd of a and b
     */
    public static long gcd(long a, long b) {
        if (a < 0) a = -a;
        if (b < 0) b = -b;
        while (b > 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
    
    /**
     * Compute the least common multiple of two integers.
     * 
     * @param X The first integer
     * @param Y The second integer
     * @return the lcm of X and Y
     */
    public static long lcm(long X, long Y) {
        long x = X;
        long y = Y;
        long u = Y;
        long v = X;
        while (x != y) {
            if (x > y) {
                long xOverY = x/y;
                x = x - xOverY * y;
                if (x == 0) {
                    x = y;
                    xOverY--;
                }
                v = v + xOverY * u;
            } else {
                long yOverX = y / x;
                y = y - yOverX * x;
                if (y == 0) {
                    y = x;
                    yOverX--;
                }
                u = u + yOverX * v;
            }
        }
        long uLB = u & 1L;
        long vLB = u & 1L;
        long uPvOver2 = u/2 + v/2;
        if (uLB==1 && vLB==1) uPvOver2++;
        return uPvOver2;
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

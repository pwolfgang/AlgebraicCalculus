/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import com.pwolfgang.albebraiccalculus.SqMatrix;
import com.pwolfgang.albebraiccalculus.datastructures.List;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * The PolyNumber type is used by Wildberger as alternative to polynomials,
 * eliminating concept of a variable or unknown. A PolyNumber is a list of
 * Rational numbers with trailing zeros as required. The index of the last
 * non-zero entry is the degree of the PolyNumber. Arithmetic on PolyNumbers is
 * the same as arithmetic on polynomials. PolyNumbers can be written as
 * polynomials on the special PolyNumber α where α = [0, 1].
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class PolyNumber {

    /**
     * The PolyNumber is internally represented by an array.
     */
    final Rational[] aS;

    /**
     * The PolyNumber zero.
     */
    public static final PolyNumber ZERO = new PolyNumber(0);
    /**
     * The PolyNumber one.
     */
    public static final PolyNumber ONE = new PolyNumber(1);
    
    /**
     * The PolyNumber alpha.
     */
    public static final PolyNumber ALPHA = new PolyNumber(0, 1);

    /**
     * Package private constructor. Creates an empty PolyNumber.
     */
    PolyNumber() {
        aS = null;
    }
    
    /**
     * Package private constructor to create a KPoly.
     */
    PolyNumber(int deg, Rational[] aS) {
        this.aS = aS;
    }

    /**
     * Construct a PolyNumber from an array of Rational numbers. Trailing zeros
     * are removed.
     *
     * @param r The input array of Rationals.
     */
    public PolyNumber(Rational... r) {
        List<Rational> tempList = new List<>(Arrays.asList(r));
        trimTrailingZeros(tempList);
        aS = tempList.toArray(Rational[]::new);
    }

    /**
     * Construct a PolyNumber from an array of long numbers. Trailing zeros are
     * removed and each entry is converted to a Rational
     *
     * @param n The input array of longs.
     */
    public PolyNumber(long... n) {
        List<Rational> tempList = new List<>();
        for (int i = 0; i < n.length; i++) {
            tempList.add(Rational.of(n[i]));
        }
        trimTrailingZeros(tempList);
        aS = tempList.toArray(Rational[]::new);
    }

    /**
     * Construct a PolyNumber from a List of Rationals. Trailing zeros are
     * removed.
     *
     * @param list The input list of Rationals.
     */
    public PolyNumber(List<Rational> list) {
        List<Rational> tempList = new List<>(list);
        trimTrailingZeros(tempList);
        aS = tempList.toArray(Rational[]::new);
    }

    private void trimTrailingZeros(List<Rational> list) {
        int i = list.size() - 1;
        while (i > 0 && list.get(i).equals(Rational.ZERO)) {
            list.remove(i);
            i--;
        }
    }
    
    /**
     * Truncate a PolyNumber to a maximum degree.
     * @param deg The maximum degree
     * @return the truncated polynumber
     */
    public KPoly truncate(int deg) {
        Rational[] newAs = Arrays.copyOf(aS, deg+1);
        for (int i = deg; i >= 0; i--) {
            if (newAs[i] == null) {
                newAs[i] = Rational.ZERO;
            }
        }
        return new KPoly(deg, newAs);
    }
    
    /**
     * Return the ith coefficient.
     * @param i The index of the coefficient
     * @return  The coefficient or ZERO if i &gt; degree.
     */
    public Rational getCoef(int i) {
        if (i < aS.length) {
            return aS[i];
        } else {
            return Rational.ZERO;
        }
    }

    /**
     * Create a PolyNumber that represents the polynomial function that passes
     * through a list of points.
     *
     * @param points The array containing the points.
     * @return A PolyNumber that represents the polynomial function.
     * @throws IllegalArgumentExcewption if the input is empty.
     */
    public static PolyNumber fromPoints(Point... points) {
        if (points.length == 0) {
            throw new IllegalArgumentException("Number of points is zero");
        }
        if (points.length == 1) {
            return new PolyNumber(points[0].getY().div(points[0].getX()));
        }
        int numPoints = points.length;
        Rational[] xS = new Rational[numPoints * numPoints];
        Rational[] yS = new Rational[numPoints];
        for (int i = 0; i < numPoints; i++) {
            var point = points[i];
            var x = point.getX();
            var y = point.getY();
            yS[i] = y;
            int row = numPoints * i;
            xS[row] = Rational.ONE;
            for (int j = row + 1; j < row + numPoints; j++) {
                xS[j] = xS[j - 1].mul(x);
            }
        }
        var m = new SqMatrix(xS, numPoints);
        Rational[] aS = m.inv().mul(yS);
        return new PolyNumber(aS);
    }

    /**
     * Add a PolyNumber to this PolyNumber. Addition is pair-wise. Trailing
     * zeros are assumed where necessary.
     *
     * @param p The other PolyNumber.
     * @return The sum of this PolyNumber and the PolyNumber p.
     */
    public PolyNumber add(PolyNumber p) {
        List<Rational> result = new List<>();
        int maxThis = aS.length;
        int maxOther = p.aS.length;
        int i = 0;
        int j = 0;
        while (i < maxThis && j < maxOther) {
            result.add(aS[i++].add(p.aS[j++]));
        }
        while (i < maxThis) {
            result.add(aS[i++]);
        }
        while (j < maxOther) {
            result.add(p.aS[j++]);
        }
        return new PolyNumber(result);
    }

    /**
     * Subtract a PolyNumber from this PolyNumber. Subtraction is pair-wise.
     * Trailing zeros are assumed where necessary.
     *
     * @param p The other PolyNumber.
     * @return The difference of this PolyNumber and the PolyNumber p.
     */
    public PolyNumber sub(PolyNumber p) {
        List<Rational> result = new List<>();
        int maxThis = aS.length;
        int maxOther = p.aS.length;
        int i = 0;
        int j = 0;
        while (i < maxThis && j < maxOther) {
            result.add(aS[i++].sub(p.aS[j++]));
        }
        while (i < maxThis) {
            result.add(aS[i++]);
        }
        while (j < maxOther) {
            result.add(p.aS[j++].neg());
        }
        return new PolyNumber(result);
    }

    /**
     * Scale this PolyNumber by a factor.
     *
     * @param lambda The factor
     * @return A new PolyNumber where each entry is multiplied by lambda.
     */
    public PolyNumber mul(Rational lambda) {
        Rational[] result = new Rational[aS.length];
        for (int i = 0; i < aS.length; i++) {
            result[i] = lambda.mul(aS[i]);
        }
        return new PolyNumber(result);
    }

    /**
     * Scale this PolyNumber by a factor.
     *
     * @param lambda The factor
     * @return A new PolyNumber where each entry is multiplied by lambda.
     */
    public PolyNumber mul(long lambda) {
        return mul(Rational.of(lambda));
    }

    /**
     * Shift each entry in the PolyNumber.
     *
     * @param k The number of spaces to shift.
     * @return A copy of this PolyNumber with k leading zeros followed by the
     * values of the original.
     */
    public PolyNumber shift(int k) {
        Rational[] result = new Rational[aS.length + k];
        for (int i = 0; i < k; i++) {
            result[i] = Rational.ZERO;
        }
        System.arraycopy(aS, 0, result, k, aS.length);
        return new PolyNumber(result);
    }

    /**
     * Compute the product of this PolyNumber an another PolyNumber. PolyNumber
     * multiplication is the same as polynomial multiplication.
     *
     * @param p The other PolyNumber
     * @return The resulting product.
     */
    public PolyNumber mul(PolyNumber p) {
        PolyNumber result = new PolyNumber(Rational.ZERO);
        for (int i = 0; i < aS.length; i++) {
            PolyNumber term = p.mul(aS[i]).shift(i);
            result = result.add(term);
        }
        return result;
    }

    /**
     * Return the degree of this PolyNumber.
     *
     * @return The degree is the index of the last non-zero value.
     */
    public int deg() {
        return aS.length - 1;
    }

    /**
     * Divide this PolyNumber by another PolyNumber.
     *
     * @param g The other PolyNumber.
     * @return An array. The [0] entry is the quotient and the [1] entry is the
     * remainder.
     */
    public PolyNumber[] divWithRemainder(PolyNumber g) {
        PolyNumber r = new PolyNumber(aS);
        PolyNumber q = new PolyNumber(0);
        while (r.deg() >= g.deg() && !PolyNumber.ZERO.equals(r)) {
            var t = r.aS[r.deg()].div(g.aS[g.deg()]);
            int d = r.deg() - g.deg();
            Rational[] a = new Rational[d + 1];
            for (int i = 0; i < d; i++) {
                a[i] = Rational.ZERO;
            }
            a[d] = t;
            var p = new PolyNumber(a);
            r = r.sub(p.mul(g));
            q = q.add(p);
        }
        return new PolyNumber[]{q, r};
    }
       
    /**
     * Divide this PolyNumber by another PolyNumber. The result is returned
     * as an Iterator&lt;PolyNumber&gt; Each call to next on the iterator
     * returns the next term in the quotient. The hasNext returns false if
     * the division is exact and the last term has been returned.
     * @param g The divisor
     * @return The quotient as an Iterator.
     */
    public DivPolySeries div(PolyNumber g) {
        return new DivPolySeries(this, g);
    }
    
    
    public SqrtPolySeries sqrt() {
            if (!aS[0].equals(Rational.ONE)) {
                throw new IllegalArgumentException("Zeroth codfficient not equal to one");
            }
        return new SqrtPolySeries(this);
    }   

    /**
     * Compute the greatest common divisor of two PolyNumbers.
     *
     * @param a One PolyNumber
     * @param b The other PolyNumber
     * @return gcd(a,b)
     */
    public static PolyNumber gcd(PolyNumber a, PolyNumber b) {
        System.out.printf("gcd(%s,%s)%n",a, b);
        if (b.equals(PolyNumber.ZERO)) {
            return a;
        }
        var r = a.divWithRemainder(b)[1];
        return gcd(b, r);
    }

    /**
     * Compute the extended gcd of two PolyNumbers. The extended gcd results in
     * three values: gcd(a,b) and the values x and y such that ax + by =
     * gcd(a,b).
     *
     * @param a One PolyNumber
     * @param b The other PolyNumber.
     * @return The values [g, x, y].
     */
    public static PolyNumber[] extendedGCD(PolyNumber a, PolyNumber b) {
        return eGCD(a, b, PolyNumber.ONE, PolyNumber.ZERO, 
                PolyNumber.ZERO, PolyNumber.ONE);
    }

    /**
     * Recursive function to compute the extended gcd of two PolyNumbers. The
     * extended gcd results in three values: gcd(a,b) and the values x and y
     * such that ax + by = gcd(a,b). At each call to the recursive function the
     * following invariants are preserved. If A and B are the initial values of
     * a and b, then gcd(a,b)==gcd(A,B). a == A*ax + B*ay b == A*bx + B*by The
     * algorithm terminates when b divides a.
     *
     * @param a One PolyNumber
     * @param b The other PolyNumber.
     * @param ax The current value of ax
     * @param ay The current value of ay
     * @param bx The current value of bx
     * @param by The current value of by
     * @return The values [g, x, y].
     */
    public static PolyNumber[] eGCD(PolyNumber a, PolyNumber b, PolyNumber ax, 
            PolyNumber ay, PolyNumber bx, PolyNumber by) {
        PolyNumber[] qr = a.divWithRemainder(b);
        PolyNumber q = qr[0];
        PolyNumber r = qr[1];
        if (r.equals(PolyNumber.ZERO)) {
            return new PolyNumber[]{b, bx, by};
        }
        PolyNumber x = ax.sub(q.mul(bx));
        PolyNumber y = ay.sub(q.mul(by));
        return eGCD(b, r, bx, by, x, y);
    }


    /**
     * Scale this PolyNumber by dividing each element by x
     *
     * @param x
     * @return The scaled PolyNumber.
     */
    public PolyNumber div(Rational x) {
        List<Rational> q = new List<>();
        int j = 0;
        while (j < aS.length && aS[j].equals(Rational.ZERO)) {
            q.add(Rational.ZERO);
            j++;
        }
        if (j < aS.length) {
            q.add(aS[j].div(x));
        }
        return new PolyNumber(q);
    }

    /**
     * Evaluate this PolyNumber as a polynomial
     *
     * @param x The value of x
     * @return Value of this PolyNumber as if alpha was replaced by x.
     */
    public Rational eval(long x) {
        return eval(Rational.of(x));
    }

    /**
     * Evaluate this PolyNumber as a polynomial
     *
     * @param x The value of x
     * @return Value of this PolyNumber as if alpha was replaced by x.
     */
    public Rational eval(Rational x) {
        Rational result = Rational.ZERO;
        for (int i = aS.length - 1; i >= 0; i--) {
            result = result.mul(x).add(aS[i]);
        }
        return result;
    }

    /**
     * Evaluate this PolyNumber as a polynomial
     *
     * @param x The value of x
     * @return Value of this PolyNumber as if alpha was replaced by x.
     */
    public PolyNumber eval(PolyNumber x) {
        PolyNumber result = new PolyNumber(0);
        for (int i = aS.length - 1; i >= 0; i--) {
            result = result.mul(x).add(new PolyNumber(aS[i]));
        }
        return result;
    }

    /**
     * Compute the Faulhaber derivative of the PolyNumber.
     *
     * @return The Faulhaber derivative of this PolyNumber.
     */
    public PolyNumber D() {
        if (aS.length == 1) {
            return new PolyNumber(new Rational[]{Rational.ZERO});
        }
        Rational[] result = new Rational[aS.length - 1];
        for (int i = 1; i < aS.length; i++) {
            result[i - 1] = aS[i].mul(Rational.of(i));
        }
        return new PolyNumber(result);
    }

    /**
     * Evaluate this PolyNumber at (1+α) and subtract this from the result.
     *
     * @return p(1+α)-p
     */
    public PolyNumber delta() {
        return eval(new PolyNumber(1, 1)).sub(this);
    }

    /**
     * Evaluate this PolyNumber at (-1+α) and subtract the result from this.
     *
     * @return p-p(-1+α)
     */
    public PolyNumber del() {
        return this.sub(eval(new PolyNumber(-1, 1)));
    }

    /**
     * Compute the Faulhaber integral of this PolyNumber.
     *
     * @return The Faulhaber integral
     */
    public PolyNumber S() {
        Rational[] result = new Rational[aS.length + 1];
        result[0] = Rational.ZERO;
        for (int i = 0; i < aS.length; i++) {
            result[i + 1] = aS[i].mul(Rational.of(1, i + 1));
        }
        return new PolyNumber(result);
    }

    /**
     * Expand this PolyNumber by adding sufficient trailing zeros.
     *
     * @param d The desired degree
     * @return The expanded PolyNumber.
     */
    public Rational[] expandToDegree(int d) {
        Rational[] r = new Rational[d];
        int n = aS.length;
        for (int i = 0; i < n && i < d; i++) {
            r[i] = aS[i];
        }
        for (int i = n; i < d; i++) {
            r[i] = Rational.ZERO;
        }
        return r;
    }

    /**
     * Create a String representation of the PolyNumber.
     *
     * @return a String representation of this PolyNumber.
     */
    @Override
    public String toString() {
        var sj = new StringJoiner(" + ");
        for (int i = 0; i < aS.length; i++) {
            Rational term = aS[i];
            if (!Rational.ZERO.equals(term)) {
                if (Rational.ONE.equals(term)) {
                    switch (i) {
                        case 0 -> sj.add("1");
                        case 1 -> sj.add("α");
                        default -> sj.add(String.format("α^%d", i));
                    }
                } else if (Rational.MINUS_ONE.equals(term)) {
                    switch (i) {
                        case 0 -> sj.add("-1");
                        case 1 -> sj.add("-α");
                        default -> sj.add(String.format("-α^%d", i));
                    }
                } else {
                    switch (i) {
                        case 0 -> sj.add(String.format("%s", term));
                        case 1 -> sj.add(String.format("%sα", term));
                        default -> sj.add(String.format("%sα^%d", term, i));
                    }
                }
            }
        }
        var s = sj.toString();
        if (s.isBlank()) {
            return "0";
        } else {
            return s;
        }
    }

    /**
     * Create a String representation of this PolyNumber with each coefficient
     * converted to double.
     *
     * @return String representation of this PolyNumber with double
     * coefficients.
     */
    public String toStringDouble() {
        var sj = new StringJoiner(" + ");
        for (int i = 0; i < aS.length; i++) {
            Rational term = aS[i];
            if (!Rational.ZERO.equals(term)) {
                if (Rational.ONE.equals(term)) {
                    switch (i) {
                        case 0 -> sj.add("1");
                        case 1 -> sj.add("α");
                        default -> sj.add(String.format("α^%d", i));
                    }
                } else {
                    switch (i) {
                        case 0 -> sj.add(String.format("%f", term.toDouble()));
                        case 1 -> sj.add(String.format("%fα", term.toDouble()));
                        default -> sj.add(String.format("%fα^%d", term.toDouble(), i));
                    }
                }
            }
        }
        var s = sj.toString();
        if (s.isBlank()) {
            return "0";
        } else {
            return s;
        }
    }

    /**
     * Determine if another PolyNumber is equal to this PolyNumber.
     *
     * @param o The other object
     * @return True if the PolyNumbers are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (this.getClass() == o.getClass()) {
            return Arrays.equals(aS, ((PolyNumber) o).aS);
        } else {
            return false;
        }
    }

    /**
     * Compute the hashCode of this PolyNumber
     *
     * @return the hashCode.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(aS);
    }

}

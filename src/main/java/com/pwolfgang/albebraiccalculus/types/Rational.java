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
public class Rational implements Comparable<Rational> {

    public final long num;
    public final long den;

    public Rational(long num, long den) { 
        if (den == 0) {
            throw new ArithmeticException("Div by Zero");
        }
        long[] norm = normalize(num, den);
        this.num = norm[0];
        if (this.num == 0) {
            this.den = 1;
        } else {
            this.den = norm[1];
        }
    }

    private long[] normalize(long num1, long den1) {
        long g = gcd(num1, den1);
        long n = num1 / g;
        long d = den1 / g;
        if (d < 0) {
            n = -n;
            d = -d;
        }
        return new long[]{n, d};
    }

    public Rational(long num) {
        this(num, 1);
    }
    
    public Rational(String s) {
        String[] tokens = s.split("/");
        if (tokens.length == 1) {
            num = Long.parseLong(s);
            den = 1;
        } else if (tokens.length == 2) {
            long n = Long.parseLong(tokens[0]);
            long d = Long.parseLong(tokens[1]);
            long[] norm = normalize(n, d);
            this.num = norm[0];
            this.den = norm[1];
        } else {
            throw new NumberFormatException(String.format("Cannot parse %s as Rational", s));
        }
    }

    public Rational(Double d) {
        long bits = Double.doubleToLongBits(d);
        long mantissa = (bits & 0x000fffffffffffffL) + 0x0010000000000000L;
        long sign = bits & 0x8000000000000000L;
        long exp = ((bits & 0x7ff0000000000000L) >> 52);
        exp -= 1023;
        if (sign < 0) {
            mantissa = -mantissa;
        }
        long div = 0x0010000000000000L;
        if (exp < 0) {
            if (exp < -10) {
                int delta = (int) (-exp - 10);
                exp = -10;
                mantissa >>= delta;
            }
            div <<= -exp; 
        }else if (exp < 52) {
            div >>= exp;
        } else if (exp < 63) {
            int delta2 = (int) (exp - 52);
            mantissa <<= delta2;
            div = 1;
        } else {
            throw new IllegalArgumentException(d + " is too big to be expressed as a Rational");
        }
        long[] norm = normalize(mantissa, div);
        while (norm[0] > 0x000000007fffffffL) {
            norm[0] >>= 1;
            norm[1] >>= 1;
        }
        while (norm[0] < 0xffffffff80000001L) {
            norm[0] >>= 1;
            norm[1] >>= 1;
        }
        while (norm[1] > 0x0000000080000000L) {
            norm[0] >>= 1;
            norm[1] >>= 1;
        }
        this.num = norm[0];
        this.den = norm[1];
   }

    public double toDouble() {
        return (double) num / (double) den;
    }

    @Override
    public String toString() {
        if (den == 1) {
            return Long.toString(num);
        } else {
            return num + "/" + den;
        }
    }

    public static Rational mediant(Rational left, Rational right) {
        return new Rational(left.num + right.num, left.den + right.den);
    }
    
    public static final Rational ZERO = new Rational(0, 1);
    public static final Rational ONE = new Rational(1, 1);
    public static final Rational MINUS_ONE = new Rational(-1, 1);
    public static final Rational TWO = new Rational(2, 1);
    public static final Rational THREE = new Rational(3,1);
    public static final Rational HALF = new Rational(1, 2);

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
        return (u + v)/2;
    }

    public Rational add(Rational other) {
        long commonD = lcm(den, other.den);
        long n1 = num * (commonD / den);
        long n2 = other.num * (commonD / other.den);
        return new Rational(n1 + n2, commonD);
    }

    public Rational neg() {
        return new Rational(-num, den);
    }

    public Rational sub(Rational other) {
        return add(other.neg());
    }

    public Rational mul(Rational other) {
        Rational x = new Rational(num, other.den);
        Rational y = new Rational(other.num, den);
        try {
            return new Rational(x.num*y.num, x.den*y.den);
        } catch (ArithmeticException ex) {
            throw new IllegalArgumentException(String.format("%s.mul(%s)", this.toString(), other.toString()), ex); 
        }
    }

    public Rational div(Rational other) {
        return new Rational(num * other.den, den * other.num);
    }

    @Override
    public int compareTo(Rational other) {
        Rational delta = sub(other);
        if (delta.num < 0) {
            return -1;
        }
        if (delta.num > 0) {
            return +1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o.getClass() == Rational.class) {
            Rational r = (Rational) o;
            return num * r.den == den * r.num;
        } else {
            return false;
        }
    }

    public long longValue() {
        return num/den;
    }
    
    public int intValue() {
        return (int)longValue();
    }
    
    @Override
    public int hashCode() {
        long[] norm = normalize(this.num, this.den);
        int hash = 5;
        hash = 59 * hash + (int) (norm[0] ^ (norm[0] >>> 32));
        hash = 59 * hash + (int) (norm[1] ^ (norm[1] >>> 32));
        return hash;
    }
}

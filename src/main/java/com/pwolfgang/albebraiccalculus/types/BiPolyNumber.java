/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import com.pwolfgang.albebraiccalculus.datastructures.List;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class BiPolyNumber {

    final Rational[][] aS;

    public BiPolyNumber(Rational[][] r) {
        List<List<Rational>> tempList = new List<>();
        for (Rational[] r1 : r) {
            List<Rational> rowList = new List<>(Arrays.asList(r1));
            trimTrailingZeros(rowList);
            tempList.add(rowList);
        }
        trimTrailingZeros(tempList);
        aS = new Rational[tempList.size()][];
        for (int i = 0; i < tempList.size(); i++) {
            var rowList = tempList.get(i);
            aS[i] = rowList.toArray(Rational[]::new);
        }
    }

    public BiPolyNumber(long[][] n) {
        List<List<Rational>> tempList = new List<>();
        for (long[] n1 : n) {
            List<Rational> rowList = new List<>();
            for (int j = 0; j < n1.length; j++) {
                rowList.add(Rational.of(n1[j]));
            }
            trimTrailingZeros(rowList);
            tempList.add(rowList);
        }
        aS = new Rational[tempList.size()][];
        for (int i = 0; i < tempList.size(); i++) {
            var rowList = tempList.get(i);
            aS[i] = rowList.toArray(Rational[]::new);
        }
    }

    public BiPolyNumber(long n) {
        this(new Rational[][]{{Rational.of(n)}});
    }

    public BiPolyNumber(Rational r) {
        this(new Rational[][]{{r}});
    }
    
    public static BiPolyNumber ZERO = new BiPolyNumber(0);

    public BiPolyNumber(List<List<Rational>> list) {
        List<Rational> tempList = new List<>();
        list.forEach(row -> trimTrailingZeros(row));
        trimTrailingZeros(tempList);
        aS = new Rational[list.size()][];
        for (int j = 0; j < list.size(); j++) {
            var row = list.get(j);
            if (row.isEmpty()) {
                aS[j] = new Rational[]{Rational.ZERO};
            } else {
                aS[j] = row.toArray(Rational[]::new);
            }
        }
    }
    
    public Rational get(int alpha, int beta) {
        if (alpha >= aS.length) {
            return Rational.ZERO;
        }
        if (beta >= aS[0].length) {
            return Rational.ZERO;
        }
        return aS[alpha][beta];
    }

    private void trimTrailingZeros(List<?> list) {
        int i = list.size() - 1;
        while (i > 0) {
            Object o = list.get(i);
            if (o.getClass() == Rational.class) {
                if (Rational.ZERO.equals(o)) {
                    list.remove(i);
                    i--;
                } else {
                    break;
                }
            } else if (o.getClass() == List.class) {
                List<?> row = (List<?>) o;
                trimTrailingZeros(row);
                if (row.size() == 1 && Rational.ZERO.equals(row.get(0))) {
                    list.remove(i);
                    i--;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
    }

    public BiPolyNumber add(BiPolyNumber p) {
        List<List<Rational>> result = new List<>();
        int maxThis = aS.length;
        int maxOther = p.aS.length;
        int i = 0;
        int j = 0;
        while (i < maxThis && j < maxOther) {
            result.add(addRows(aS[i++], p.aS[j++]));
        }
        while (i < maxThis) {
            result.add(new List<>(Arrays.asList(aS[i++])));
        }
        while (j < maxOther) {
            result.add(new List<>(Arrays.asList(p.aS[j++])));
        }
        return new BiPolyNumber(result);
    }

    static List<Rational> addRows(Rational[] a, Rational[] b) {
        int aLength = a.length;
        int bLength = b.length;
        List<Rational> result = new List<>();
        int i = 0;
        int j = 0;
        while (i < aLength && j < bLength) {
            result.add(a[i++].add(b[j++]));
        }
        while (i < aLength) {
            result.add(a[i++]);
        }
        while (j < bLength) {
            result.add(b[j++]);
        }
        return result;
    }

    public BiPolyNumber sub(BiPolyNumber p) {
        List<List<Rational>> result = new List<>();
        int maxThis = aS.length;
        int maxOther = p.aS.length;
        int i = 0;
        int j = 0;
        while (i < maxThis && j < maxOther) {
            result.add(subRows(aS[i++], p.aS[j++]));
        }
        while (i < maxThis) {
            result.add(new List<>(Arrays.asList(aS[i++])));
        }
        while (j < maxOther) {
            result.add(new List<>(Arrays.asList(p.aS[j++])));
        }
        return new BiPolyNumber(result);
    }

    static List<Rational> subRows(Rational[] a, Rational[] b) {
        int aLength = a.length;
        int bLength = b.length;
        List<Rational> result = new List<>();
        int i = 0;
        int j = 0;
        while (i < aLength && j < bLength) {
            result.add(a[i++].sub(b[j++]));
        }
        while (i < aLength) {
            result.add(a[i++]);
        }
        while (j < bLength) {
            result.add(b[j++]);
        }
        return result;
    }
    
    public BiPolyNumber mul(long lambda) {      
            return mul(Rational.of(lambda));
    }

    public BiPolyNumber mul(Rational lambda) {
        Rational[][] newAs = new Rational[aS.length][];
        for (int i = 0; i < aS.length; i++) {
            Rational[] row = aS[i];
            newAs[i] = new Rational[row.length];
            for (int j = 0; j < row.length; j++) {
                newAs[i][j] = lambda.mul(aS[i][j]);
            }
        }
        return new BiPolyNumber(newAs);
    }

    public BiPolyNumber shift(int k, int l) {
        Rational[][] result = new Rational[aS.length + k][];
        for (int i = 0; i < k; i++) {
            result[i] = new Rational[]{Rational.ZERO};
        }
        for (int i = 0; i < aS.length; i++) {
            Rational[] row = aS[i];
            result[i+k] = new Rational[row.length + l];
            for (int j = 0; j < l; j++) {
                result[i+k][j] = Rational.ZERO;
            }
            System.arraycopy(row, 0, result[i+k], l, row.length);
        }
        return new BiPolyNumber(result);
    }

    public BiPolyNumber mul(BiPolyNumber p) {
        BiPolyNumber result = new BiPolyNumber(new Rational[][]{{Rational.ZERO}});
        for (int i = 0; i < aS.length; i++) {
            for (int j = 0; j < aS[i].length; j++) {
                BiPolyNumber term = p.mul(aS[i][j]).shift(i, j);
                result = result.add(term);
            }
        }
        return result;
    }
    
    public Rational eval(Point p) {
        return eval(p.getX(), p.getY());
    }

    public Rational eval(Rational x, Rational y) {
        Rational result = Rational.ZERO;
        for (int i = aS.length - 1; i >= 0; i--) {
            PolyNumber row = new PolyNumber(aS[i]);
            result = result.mul(x);
            result = result.add(row.eval(y));
        }
        return result;
    }

    public BiPolyNumber eval(BiPolyNumber x, BiPolyNumber y) {
        PolyNumber yPN = new PolyNumber(y.aS[0]);
        BiPolyNumber result = BiPolyNumber.ZERO;
        for (int i = aS.length-1; i >= 0; i--) {
            PolyNumber row = new PolyNumber(aS[i]);
            row = row.eval(yPN);
            BiPolyNumber rowBPN = new BiPolyNumber(new Rational[][]{row.aS});
            var resultTimesX = result.mul(x);
            result = resultTimesX.add(rowBPN);
       }
        return result;
    }
    
    public BiPolyNumber tangentAt(Point p) {
        var pX = p.getX();
        var pY = p.getY();
        var x = new BiPolyNumber(new Rational[][]{{pX},{Rational.ONE}});
        var y = new BiPolyNumber(new Rational[][]{{pY, Rational.ONE}});
        var translated = this.eval(x, y);
        var tanget = new BiPolyNumber(new Rational[][]
        {
            {translated.aS[0][0], translated.aS[0][1]},
            {translated.aS[1][0]}
        });
        var xP = new BiPolyNumber(new Rational[][]{{pX.neg()},{Rational.ONE}});
        var yP = new BiPolyNumber(new Rational[][]{{pY.neg(), Rational.ONE}});
        return tanget.eval(xP, yP);
    }
    
    @Override
    public String toString() {
        var sj = new StringJoiner(" + ");
        for (int i = 0; i < aS.length; i++) {
            for (int j = 0; j < aS[i].length; j++) {
                Rational term = aS[i][j];
                if (!Rational.ZERO.equals(term)) {
                    if (i == 0 && j == 0) {
                        sj.add(term.toString());
                    } else if (Rational.ONE.equals(term)) {
                        sj.add(formatAlphaBeta(i, j));
                    } else {
                        sj.add(term.toString()+formatAlphaBeta(i, j));
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
    
    String formatAlphaBeta(int i, int j) {
        var sb = new StringBuilder();
        if (i > 0) {
            if (i == 1) {
                sb.append("α");
            } else {
                sb.append(String.format("α^%d", i));
            }
        }
        if (j > 0) {
            if (j == 1) {
                sb.append("β");
            } else {
                sb.append(String.format("β^%d", j));
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (this.getClass() == o.getClass()) {
            Rational[][] otherAs = ((BiPolyNumber)o).aS;
            if (aS.length != otherAs.length) return false;
            for (int i = 0; i < aS.length; i++) {
                if (!Arrays.equals(aS[i], otherAs[i])) return false;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Arrays.deepHashCode(this.aS);
        return hash;
    }

}

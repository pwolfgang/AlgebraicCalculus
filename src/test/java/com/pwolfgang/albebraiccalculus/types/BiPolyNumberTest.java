/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus.types;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class BiPolyNumberTest {
    
    @BeforeEach
    public void init() {
        try {
            PrintStream p = new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8");
            System.setOut(p);          
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
    public BiPolyNumberTest() {
    }

    @Test
    public void testAdd() {
        var p = new BiPolyNumber(new long[][]
        {
            {-1, 0, 1, 2},
            {0, -2, 0, 3},
            {1, 0, 0, 1}
        });
        var q = new BiPolyNumber(new long[][]
        {
            {2, -2},
            {7, -3}
        });
        var r = new BiPolyNumber(new long[][]
        {
            {0, 1, -4},
            {-1, -3}
        });
        var result = new BiPolyNumber(new long[][]
        {
            {0, -5, 14, 4},
            {10, 2, 0, 6},
            {2, 0, 0, 2}
        });
        var px2 = p.mul(2);
        var rx3 = r.mul(3);
        var px2PlusQ = px2.add(q);
        var px2PlusQsubRx3 = px2PlusQ.sub(rx3);
        assertEquals(result, p.mul(2).add(q).sub(r.mul(3)));
    }

 
    @Test
    public void testMul_BiPolyNumber() {
        var p = new BiPolyNumber(new long[][]
        {
            {-3, 1, 2},
            {1, -1, -2}
        });
        var q = new BiPolyNumber(new long[][]
        {
            {1, -5},
            {-2, 3},
            {0, -1}        
        });
        var pq = new BiPolyNumber(new long[][]
        {
            {-3, 16, -3, -10},
            {7, -17, 2, 16},
            {-2, 8, 0, -8},
            {0, -1, 1, 2}
        });
        assertEquals(pq, p.mul(q));
    }
    @Test

    public void testMul_BiPolyNumber2() {
        var p = new BiPolyNumber(new long[][]
        {
            {2, 3},
            {-1}
        });
        var q = new BiPolyNumber(new long[][]
        {
            {1, 4},
            {5, 0, 1},
            {2}        
        });
        var pq = new BiPolyNumber(new long[][]
        {
            {2, 11, 12},
            {9, 11, 2, 3},
            {-1, 6, -1},
            {-2}
        });
        assertEquals(pq, p.mul(q));
    }
    
    @Test
    public void testEval_Point() {
        var p = new BiPolyNumber(new long[][]
        {
            {-7, -5},
            {-1, 1, -2},
            {4}     
        });
        assertEquals(Rational.ZERO, p.eval(new Point(2, 1)));
    }

    @Test
    public void testEval_BiPolyNumber_BiPolyNumber() {
        var p = new BiPolyNumber(new long[][]
        {
            {-7, -5},
            {-1, 1, -2},
            {4}     
        });
        var x = new BiPolyNumber(new long[][]
        {
            {2},
            {1}
        });
        var y = new BiPolyNumber(new long[][]
        {
            {1, 1}
        });
        var r = new BiPolyNumber(new long[][]
        {
            {0, -11, -4},
            {14, -3, -2},
            {4}
        });
        assertEquals(r, p.eval(x, y));
       
    }
    
    @Test
    public void testTangentAt() {
        var curve = new BiPolyNumber(new long[][]
        {
            {10, -2},
            {1, -3},
            {1}
        });
        var p = new Point(3,2);
        var expected = new BiPolyNumber(new long[][]
        {
            {19, -11},
            {1}
        });
        assertEquals(expected, curve.tangentAt(p));
    }
    
    @Test
    public void testFolium() {
        var folium = new BiPolyNumber(new Rational[][]
        {
            {Rational.ZERO, Rational.ZERO, Rational.ZERO, Rational.of(1,27)},
            {Rational.ZERO, Rational.of(-1,3)},
            {Rational.ZERO},
            {Rational.of(1,27)}
        });
        System.out.printf("f = %s%n", folium.toString());
        var p1 = new Point(Rational.of(4), Rational.of(2));
        var p2 = new Point(Rational.of(2), Rational.of(4));
        var p3 = new Point(Rational.of(9,2),Rational.of(9,2));
        System.out.printf("Tangent at %s is %s%n", p1.toString(), folium.tangentAt(p1).toString());
        System.out.printf("Tangent at %s is %s%n", p2.toString(), folium.tangentAt(p2).toString());
        System.out.printf("Tangent at %s is %s%n", p3.toString(), folium.tangentAt(p3).toString());
        
    }
    
    @Test
    public void testMulByAlphaPlusBeta () {
        var p = new BiPolyNumber(new long[][]{{3, 1, 2},{0, 4,5}});
        var ab = new BiPolyNumber(new long[][]{{0, 1},{1}});
        var c = new BiPolyNumber(new long[][]{{0, 3, 1, 2},{3, 1, 6, 5},{0, 4, 5}});
        assertEquals(c, p.mul(ab));
        assertEquals(c, ab.mul(p));
    }
    
    @Test
    public void testMul2() {
        var p = new BiPolyNumber(new long[][]{{1, 2},{5, 3}});
        var q = new BiPolyNumber(new long[][]{{4, 5, 1},{2, 1, 3}});
        var r = new BiPolyNumber(new long[][]{{4, 13, 11, 2},{22, 42, 25, 9},{10, 11, 18, 9}});
        assertEquals(r, p.mul(q));
    }
    
    @Test
    public void p6p2p16() {
        System.out.println("P6.2.16");
        var p = new BiPolyNumber(new long[][]{{-7, -5},{-1, 1, -2},{4}});
        System.out.println(p);
        System.out.println(p.eval(new Point(2,1)));
        var x = new BiPolyNumber(new long[][]{{2},{1}});
        var y = new BiPolyNumber(new long[][]{{1,1}});
        var d = p.eval(x, y);
        System.out.println(d);
        System.out.println(p.tangentAt(new Point(2,1)));
    }
    
    @Test
    public void p6p2p23() {
        System.out.println("P6.2.23");
        var p = new BiPolyNumber(new long[][]{{-1, 0, 0, 1},{0},{0},{1}});
        System.out.println(p);
        System.out.println(p.tangentAt(new Point(0,1)));
    }
    
    @Test
    public void lecture4Example() {
        System.out.println("Lecture 4 example");
        var p = new BiPolyNumber(new long[][]{{-1,0,4},{-1,4},{1}});
        System.out.println(p);
        System.out.println(p.tangentAt(new Point(Rational.ZERO, Rational.HALF)));
        System.out.println((p.tangentAt(new Point(Rational.ZERO, Rational.HALF.neg()))));
        System.out.println(p.tangentAt(new Point(Rational.of(-51,100), Rational.of(605,1000))));
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainpgms;

import com.pwolfgang.albebraiccalculus.examples.Newton;
import com.pwolfgang.albebraiccalculus.types.BiPolyNumber;
import com.pwolfgang.albebraiccalculus.types.Point;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class Main4 {
    
    public static void main(String... args) {
        var fermatCurve = new BiPolyNumber(new long[][] {
            {-1, 0, 0, 1},
            {0, 0, 0,0},
            {0, 0, 0, 0},
            {1, 0, 0, 0}
        });
        var bernoulli = new BiPolyNumber(new long[][] {
            {0, 0, 2, 0, 1},
            {0, 0, 0, 0, 0},
            {-2, 0, 2, 0, 0},
            {0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0}
        });
        Point a0 = new Point(2,-1);
        Point a1 = Newton.newtonIteration(fermatCurve, bernoulli, a0);
        System.out.println(a1.toString());
        Point a2 = Newton.newtonIteration(fermatCurve, bernoulli, a1);
        System.out.println(a2.toString());
        Point a3 = Newton.newtonIteration(fermatCurve, bernoulli, a2  );
        System.out.println(a3.toString());
        
        
    }
    
}

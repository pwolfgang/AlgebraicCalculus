/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pwolfgang.albebraiccalculus.examples;

import com.pwolfgang.albebraiccalculus.SqMatrix;
import com.pwolfgang.albebraiccalculus.types.BiPolyNumber;
import com.pwolfgang.albebraiccalculus.types.Point;
import com.pwolfgang.albebraiccalculus.types.Rational;

/**
 * Class to find the approximate meet of two algebraic curves using
 * Newton's method.
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class Newton {
    
    /**
     * Method to perform one iteration
     */
    public static Point newtonIteration(BiPolyNumber c1, BiPolyNumber c2, Point a) {
        // Find the tangent planes
        var c1Tangent = c1.tangentAt(a);
        var c2Tangent = c2.tangentAt(a);
        // Convert to two simultaneous linear quations
        SqMatrix m = new SqMatrix(new Rational[] 
        {c1Tangent.get(1, 0), c1Tangent.get(0,1),
         c2Tangent.get(1, 0), c2Tangent.get(0, 1)}, 2);
        var aPrime = m.inv().mul(c1Tangent.get(0,0).neg(), c2Tangent.get(0, 0).neg());
        return new Point(aPrime[0], aPrime[1]);        
    }
    
}

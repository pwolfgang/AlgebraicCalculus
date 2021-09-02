/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwolfgang.albebraiccalculus;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class Pascal {
    
    long[][] triangle;
    int maxN;
    
    public Pascal(int maxN) {
        this.maxN = maxN;
        buildTriangle();
    }
    
    public Pascal() {
        this(5);
    }
    
    private void buildTriangle() {
        triangle = new long[maxN+1][];
        triangle[0] = new long[]{1};
        triangle[1] = new long[]{1,1};
        for (int i = 2; i < maxN+1; i++) {
            triangle[i] = new long[i+1];
            triangle[i][0] = 1;
            for (int j = 1; j < i; j++) {
                triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j];
            }
            triangle[i][i+1] = 1;
        }
    }
    
}

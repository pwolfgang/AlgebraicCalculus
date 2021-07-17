/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpgms;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import com.pwolfgang.albebraiccalculus.types.Rational;
import com.pwolfgang.albebraiccalculus.Canvas;
import com.pwolfgang.albebraiccalculus.types.DCBcurve;
import com.pwolfgang.albebraiccalculus.types.Point;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class Main {
    
    public static void main(String... args) {
        int numSteps;
        int radius;
        if (args.length < 2) {
            numSteps = 512;
            radius = 256;
        } else {
            numSteps = Integer.parseInt(args[0]);
            radius = Integer.parseInt(args[1])/2;
        }
        double log2radius = Math.log(radius)/Math.log(2);
        int canvasSize = 1 << ((int)Math.ceil(log2radius)+1);
        int offset = canvasSize/2;
        radius /= 2;
//        int offset = canvasSize*3/4;
//        radius /= 16;
        Canvas canvas = Canvas.newInstance(0., canvasSize-1, 0., canvasSize-1, canvasSize, canvasSize);
        JFrame frame = new JFrame();
        frame.getContentPane().add(canvas);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        int m = numSteps;
        int mSq = m*m;
        double sqrt2over2 = Math.sqrt(2)/2.0;
        Rational lowBound = Rational.ZERO;
        Rational hiBound = Rational.ONE;
        for (int n = 1; n < numSteps; n++) {
            int nSq = n*n;
            int twoNM = 2*n*m;
            int mSqPlusNsq = mSq+nSq;
            int mSqMinusNsq = mSq-nSq;
            int p1 = (twoNM * radius)/mSqPlusNsq;
            int p2 = (mSqMinusNsq * radius)/mSqPlusNsq;
            
            canvas.plotPixel(p1 + offset, p2 + offset, Color.BLUE, 2);
            canvas.plotPixel(p2 + offset, p1 + offset, Color.BLUE, 2);
            canvas.plotPixel(p1 + offset, -p2 + offset, Color.BLUE, 2);
            canvas.plotPixel(p2 + offset, -p1 + offset, Color.BLUE, 2);
            canvas.plotPixel(-p1 + offset, -p2 + offset, Color.BLUE, 2);
            canvas.plotPixel(-p2 + offset, -p1 + offset, Color.BLUE, 2);
            canvas.plotPixel(-p1 + offset, p2 + offset, Color.BLUE, 2);
            canvas.plotPixel(-p2 + offset, p1 + offset, Color.BLUE, 2);
        }
        Rational rationalA = (new Rational(Math.sqrt(2)).mul(new Rational(4))).sub(new Rational(4)).div(new Rational(3));
        var p0 = new Point(1, 0, 1);
        var p1 = new Point(rationalA, Rational.ONE);
        var p2 = new Point(Rational.ONE, rationalA);
        var p3 = new Point(1, 1, 0);
        var curve = new DCBcurve(p0, p1, p2, p3);
        for (int i = 0; i <= 114*numSteps/32; i++) {
            var lambda = new Rational(i, numSteps).sub(new Rational(41,32));
            var p = curve.r(lambda);
            int px = (int)(p.getX().toDouble()*radius + offset);
            int py = (int)(p.getY().toDouble()*radius + offset);
            canvas.plotPixel(px, py, Color.GREEN, 2);
        }
        for (int i = 0; i < canvasSize; i++) {
            canvas.plotPixel(i, i, Color.RED);
        }
        for (int i = 0; i < canvasSize; i++) {
            canvas.plotPixel(i, offset, Color.BLACK);
            canvas.plotPixel(offset, i, Color.BLACK);
        }
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }
    
}

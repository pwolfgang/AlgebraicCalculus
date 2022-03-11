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
import com.pwolfgang.albebraiccalculus.types.PolyNumber;
import java.io.PrintStream;

/**
 *
 * @author Paul Wolfgang <paul@pwolfgang.com>
 */
public class Main {
    
    public static void main(String... args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        int numSteps;
        int radius;
        Rational scaleFactor;
        Rational offsetFactor;
        Rational lambdaMin;
        Rational lambdaMax;
        if (args.length < 6) {
            numSteps = 1024;
            radius = 512;
            scaleFactor = Rational.of(1, 16);
            offsetFactor = Rational.of(3, 4);
            lambdaMin = Rational.of(-3);
            lambdaMax = Rational.of(4);
        } else {
            numSteps = Integer.parseInt(args[0]);
            radius = Integer.parseInt(args[1])/2;
            scaleFactor = Rational.of(args[2]);
            offsetFactor = Rational.of(args[3]);
            lambdaMin = Rational.of(args[4]);
            lambdaMax = Rational.of(args[5]);
        }
        double log2radius = Math.log(radius)/Math.log(2);
        int canvasSize = 1 << ((int)Math.ceil(log2radius)+1);
        int offset = Rational.of(canvasSize).mul(offsetFactor).intValue();
        radius = Rational.of(radius).mul(scaleFactor).intValue();
        Canvas canvas = Canvas.newInstance(0., canvasSize-1, 0., canvasSize-1, canvasSize, canvasSize);
        JFrame frame = new JFrame();
        frame.getContentPane().add(canvas);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        int m = numSteps;
        int mSq = m*m;
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
//        Rational rationalA = (Rational.of(Math.sqrt(2)).mul(Rational.of(4))).sub(Rational.of(4)).divWithRemainder(Rational.of(3));
        var rationalA = Rational.of(11,20);
        var p0 = new Point(1, 0, 1);
        var p1 = new Point(rationalA, Rational.ONE);
        var p2 = new Point(Rational.ONE, rationalA);
        var p3 = new Point(1, 1, 0);
        var curve = new DCBcurve(p0, p1, p2, p3);
        PolyNumber[] pn = curve.toPolyNumber();
        System.out.printf("x{t} = %s%n", pn[0].toStringDouble());
        System.out.printf("y[t] = %s%n", pn[1].toStringDouble());
        System.out.printf("x{t} = %s%n", pn[0].toString());
        System.out.printf("y[t] = %s%n", pn[1].toString());
        Rational deltaLambda = lambdaMax.sub(lambdaMin);
        for (int i = 0; i <= deltaLambda.intValue()*numSteps; i++) {
            var lambda = Rational.of(i, numSteps).add(lambdaMin);
            var p = curve.r(lambda);
            double px = p.getX().mul(Rational.of(radius)).add(Rational.of(offset)).toDouble();
            double py = p.getY().mul(Rational.of(radius)).add(Rational.of(offset)).toDouble();
            canvas.plotPixel(px, py, Color.GREEN, 2);
        }
        for (int i = 0; i < canvasSize; i++) {
            canvas.plotPixel(i, i, Color.RED);
        }
        for (int i = 0; i < canvasSize; i++) {
            canvas.plotPixel(i, offset, Color.BLACK);
            canvas.plotPixel(offset, i, Color.BLACK);
        }
        canvas.writeImage("ExtendedCircle3.png");
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }
    
}

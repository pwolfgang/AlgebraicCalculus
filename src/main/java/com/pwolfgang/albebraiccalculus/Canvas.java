package com.pwolfgang.albebraiccalculus;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Paul
 */
@SuppressWarnings("serial")
public class Canvas extends JPanel {
    
    private final int xOrigin;
    private final int yOrigin;
    private final double scaleFactor;
    private final int w;
    private final int h;
    private final BufferedImage image;
    
    public Canvas (int xOrigin, int yOrigin, double scaleFactor, int w, int h) {
        super();
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        this.scaleFactor = scaleFactor;
        this.w = w;
        this.h = h;
        Dimension size = new Dimension(w, h);
        setPreferredSize(size);
        image = new BufferedImage(w, h, TYPE_INT_ARGB);
        for (int x = 0; x < w; x++)
            for (int y = 0; y < h; y++)
                image.setRGB(x, y, Color.WHITE.getRGB());
    }
    
    public static Canvas newInstance(double minX, double maxX, double minY, double maxY, int w, int h) {
        double deltaX = maxX - minX;
        double deltaY = maxY - minY;
        double sfX = w/deltaX;
        double sfY = h/deltaY;
        double sf = sfX > sfY ? sfX : sfY;
        double xOrigin = w - maxX*w/deltaX;
        double yOrigin = h - maxY*h/deltaY;
        return new Canvas((int)xOrigin, (int)yOrigin, sf, w, h);
    }
    
    public void writeImage(String fileName) {
        File file = new File(fileName);
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);   
    }
    
    int[] convertPoint(double x, double y) {
        int newX = (int)(x*scaleFactor + xOrigin);
        int newY = h - (int)(y*scaleFactor + yOrigin);
        if (newX < 0) newX = 0;
        if (newX > w-1) newX = w-1;
        if (newY < 0) newY = 0;
        if (newY > h-1) newY = h-1;
        return new int[]{newX, newY};
    }
    
    public void plotPixel(double x, double y, Color c) {
        int[] p = convertPoint(x, y);
        image.setRGB(p[0], p[1], c.getRGB());
        repaint();
    }
    
    public void plotPixel(double x, double y, Color c, int size) {
        int[] p = convertPoint(x, y);
        for (int k = -size/2; k <= size/2; k++) {
            int pX = p[0];
            int pY = p[1];
            if ((pX + k) >=0 && (pX + k) < w) {
                image.setRGB(pX+k, pY, c.getRGB());
            }
            if ((pY + k) >=0 && (pY + k) < h) {
                image.setRGB(pX, pY+k, c.getRGB());
            }
        }
       repaint();
    }
}

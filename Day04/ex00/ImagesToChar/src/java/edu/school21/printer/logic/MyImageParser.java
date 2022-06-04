package edu.school21.printer.logic;

import java.awt.image.BufferedImage;

public class MyImageParser {
    private static final int WHITE = -1;

    public static void printToConsole(BufferedImage image, String whiteChar, String blackChar) {
        for (int j = 0; j < image.getHeight(); j++) {
            for (int i = 0; i < image.getWidth(); i++) {
                if (image.getRGB(i, j) == WHITE) {
                    System.out.print(whiteChar);
                } else {
                    System.out.print(blackChar);
                }
            }
            System.out.println();
        }
    }
}

package edu.school21.printer.logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MyImageParser {
    public static void printToConsole(BufferedImage image, Ansi.BColor whiteColor, Ansi.BColor blackColor) {
        ColoredPrinter cp = new ColoredPrinter.Builder(1, false).build();

        for (int j = 0; j < image.getHeight(); j++) {
            for (int i = 0; i < image.getWidth(); i++) {
                Color color = new Color(image.getRGB(i, j));

                if (color.getRed() == 255 && color.getBlue() == 255 && color.getGreen() == 255) {
                    cp.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, whiteColor);
                } else {
                    cp.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, blackColor);
                }
            }
            System.out.println();
        }
    }
}

package edu.school21.printer.app;

import edu.school21.printer.logic.MyImageParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Bad args");
            return;
        }

        try {
            BufferedImage image = ImageIO.read(new File(args[2]));
            MyImageParser.printToConsole(image, args[0], args[1]);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

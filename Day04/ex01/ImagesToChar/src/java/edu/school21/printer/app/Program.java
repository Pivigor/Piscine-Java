package edu.school21.printer.app;

import edu.school21.printer.logic.MyImageParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Program {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Bad args");
            return;
        }

        try {
            InputStream inputStream = Program.class.getResourceAsStream("/resources/image.bmp");
            if (inputStream == null) {
                inputStream = Program.class.getClassLoader().getResourceAsStream("image.bmp");
            }

            if (inputStream != null) {
                BufferedImage image = ImageIO.read(inputStream);
                MyImageParser.printToConsole(image, args[0], args[1]);
            } else {
                System.err.println("Cannot access to resources file");
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

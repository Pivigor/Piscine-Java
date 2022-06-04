package edu.school21.printer.app;

import com.diogonunes.jcdp.color.api.Ansi;
import edu.school21.printer.logic.MyArgsParser;
import edu.school21.printer.logic.MyImageParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Program {
    public static void main(String[] args) {
        try {
            MyArgsParser argsParser = new MyArgsParser(args);

            InputStream inputStream = Program.class.getResourceAsStream("/resources/image.bmp");
            if (inputStream == null) {
                inputStream = Program.class.getClassLoader().getResourceAsStream("image.bmp");
            }

            if (inputStream != null) {
                BufferedImage image = ImageIO.read(inputStream);
                MyImageParser.printToConsole(image,
                        Ansi.BColor.valueOf(argsParser.white),
                        Ansi.BColor.valueOf(argsParser.black));
            } else {
                System.err.println("Cannot access to resources file");
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

package edu.school21.app;

import edu.school21.logic.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {
        /*
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
        printer.setPrefix("Prefix ");
        printer.print("Hello!");*/

        /*
        PreProcessor preProcessor = new PreProcessorToLower();
        Renderer renderer = new RendererStandardImpl(preProcessor);
        Printer printer = new PrinterWithDateTimeImpl(renderer);
        //printer.setPrefix("Prefix ");
        printer.print("Hello!");*/


        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("printerWithPrefix", Printer.class);
        printer.print ("Hello!");
    }
}

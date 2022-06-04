package edu.school21.logic;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {
    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String text) {
        renderer.render(LocalDateTime.now() + " " + text);
    }

    private final Renderer renderer;
}

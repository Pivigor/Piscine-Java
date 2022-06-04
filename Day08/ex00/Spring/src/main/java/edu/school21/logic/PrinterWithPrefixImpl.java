package edu.school21.logic;

public class PrinterWithPrefixImpl implements Printer {
    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
        this.prefix = "<prefix>";
    }

    @Override
    public void print(String text) {
        renderer.render(prefix + text);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    private final Renderer renderer;
    private String prefix;
}

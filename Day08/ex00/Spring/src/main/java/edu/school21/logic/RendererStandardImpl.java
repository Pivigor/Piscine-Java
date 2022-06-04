package edu.school21.logic;

public class RendererStandardImpl implements Renderer {
    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void render(String text) {
        System.out.println(preProcessor.preprocess(text));
    }

    private final PreProcessor preProcessor;
}

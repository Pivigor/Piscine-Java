package edu.school21.logic;

public class RendererErrImpl implements Renderer {
    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void render(String text) {
        System.err.println(preProcessor.preprocess(text));
    }

    private final PreProcessor preProcessor;
}

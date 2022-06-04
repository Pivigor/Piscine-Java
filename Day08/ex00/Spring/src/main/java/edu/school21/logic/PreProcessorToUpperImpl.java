package edu.school21.logic;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String preprocess(String text) {
        return text.toUpperCase();
    }
}

package edu.school21.logic;

public class PreProcessorToLower implements PreProcessor {
    @Override
    public String preprocess(String text) {
        return text.toLowerCase();
    }
}

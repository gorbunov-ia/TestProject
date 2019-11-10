package ru.gorbunov.test.textanalizer;

public class NegativeTextAnalyzer extends KeywordAnalyzer {

    private static final String[] KEYWORDS = {":(", "=(", ":|"};

    public NegativeTextAnalyzer() {
    }

    @Override
    protected String[] getKeywords() {
        return KEYWORDS;
    }

    @Override
    protected Label getLabel() {
        return Label.NEGATIVE_TEXT;
    }
}

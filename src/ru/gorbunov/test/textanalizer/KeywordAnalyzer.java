package ru.gorbunov.test.textanalizer;

public abstract class KeywordAnalyzer implements TextAnalyzer {

    protected abstract String[] getKeywords();

    protected abstract Label getLabel();

    @Override
    public Label processText(String text) {
        for (String smile : getKeywords()) {
            if (text.contains(smile)) {
                return getLabel();
            }
        }
        return Label.OK;
    }
}

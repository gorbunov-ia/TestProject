package ru.gorbunov.test.textanalizer;

public class Main {
    public static void main(String[] args) {
        TextAnalyzer[] analyzers = new TextAnalyzer[3];
        analyzers[0] = new SpamAnalyzer(new String[]{"qqq","www"});
        analyzers[1] = new NegativeTextAnalyzer();
        analyzers[2] = new TooLongTextAnalyzer(4);
        String text = "qq=(eee";
        System.out.println(new Main().checkLabels(analyzers, text));
    }

    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer textAnalyzer : analyzers) {
            Label label = textAnalyzer.processText(text);
            if (label != Label.OK) {
                return label;
            }
        }
        return Label.OK;
    }
}









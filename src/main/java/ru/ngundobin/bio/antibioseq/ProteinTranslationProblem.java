package ru.ngundobin.bio.antibioseq;

import ru.ngundobin.bio.model.Genome;

import java.util.HashMap;

public class ProteinTranslationProblem {

    private final HashMap<String, String> dictionary;

    public ProteinTranslationProblem(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;
    }

    public String translate(Genome rnaGenome) {
        StringBuilder resultBuilder = new StringBuilder();
        int cursor = 0;
        while (cursor < (rnaGenome.size() - 2)) {
            String codon = rnaGenome.subSequence(cursor, cursor + 2).toString();
            resultBuilder.append(dictionary.get(codon));
            cursor += 3;
        }
        return resultBuilder.toString();
    }
}

package ru.ngundobin.bio.antibioseq;

import ru.ngundobin.bio.dnarepl.rcp.ComplementStrandReverser;
import ru.ngundobin.bio.model.Genome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PeptideEncodingProblem {

    private final Genome rnaGenome;
    private final HashMap<String, String> dictionary;
    private final ComplementStrandReverser reverser;

    public PeptideEncodingProblem(Genome dnaGenome, HashMap<String, String> dictionary) {
        reverser = new ComplementStrandReverser();
        this.rnaGenome = dnaGenome.convertToRna();
        this.dictionary = dictionary;
    }

    public List<Genome> encode(String peptide) {
        List<Genome> result = new ArrayList<>();
        int length = peptide.length();
        int cursor = 0;
        ProteinTranslationProblem problem = new ProteinTranslationProblem(dictionary);
        while (cursor < (rnaGenome.size() - length * 3 + 1)) {
            Genome rnaSequence = rnaGenome.subSequence(cursor, cursor + length * 3 - 1);
            Genome rnaSequenceComplement = reverser.reverse(rnaSequence).convertToRna();
            String translation = problem.translate(rnaSequence);
            if (peptide.equals(translation)) {
                result.add(rnaSequence.convertToDna());
            }
            translation = problem.translate(rnaSequenceComplement);
            if (peptide.equals(translation)) {
                result.add(rnaSequence.convertToDna());
            }
            cursor++;
        }
        return result;
    }
}

package ru.ngundobin.bio.antibioseq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TheoreticalSpectrumGenerator {

    private Map<String, Integer> massTable;

    public TheoreticalSpectrumGenerator(Map<String, Integer> massTable) {
        this.massTable = massTable;
    }

    public List<Integer> generate(String peptide) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        List<String> subStrings = findAllSubStrings(peptide);
        for (String s : subStrings) {
            result.add(countMass(s));
        }
        Collections.sort(result);
        return result;
    }

    private List<String> findAllSubStrings(String peptide) {
        List<String> result = new ArrayList<>();
        String extendedPeptide = peptide + peptide;
        int length = 1;
        while (length < peptide.length()) {
            for (int i = 0; i < peptide.length(); i++) {
                result.add(extendedPeptide.substring(i, i + length));
            }
            length++;
        }
        result.add(peptide);
        return result;
    }

    private int countMass(String sequence) {
        int sum = 0;
        for (char acid : sequence.toCharArray()) {
            String sAcid = String.valueOf(acid);
            sum += massTable.get(sAcid);
        }
        return sum;
    }

}

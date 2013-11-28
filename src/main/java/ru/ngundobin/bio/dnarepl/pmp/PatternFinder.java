package ru.ngundobin.bio.dnarepl.pmp;

import ru.ngundobin.bio.model.Genome;

import java.util.LinkedHashSet;
import java.util.Set;

public class PatternFinder {

    private final Genome genome;

    public PatternFinder(Genome genome) {
        if (genome == null) throw new IllegalArgumentException("'genome' == null");
        this.genome = genome;
    }

    public Set<Integer> findPatternPositions(String pattern) {
        if (pattern == null) throw new IllegalArgumentException("'pattern' == null");
        if (pattern.isEmpty()) throw new IllegalArgumentException("'pattern' is empty");

        String genomeString = genome.toString();
        Set<Integer> result = new LinkedHashSet<>();
        int currentPos;
        int foundIndex = genomeString.indexOf(pattern);
        while (foundIndex != -1) {
            result.add(foundIndex);
            currentPos = foundIndex + 1;
            foundIndex = genomeString.indexOf(pattern, currentPos);
        }
        return result;
    }

    public Set<Integer> findPatternPositions(String pattern, int mismatches) {
        if (pattern == null) throw new IllegalArgumentException("'pattern' == null");
        if (pattern.isEmpty()) throw new IllegalArgumentException("'pattern' is empty");
        if (mismatches < 0) throw new IllegalArgumentException("'mismatches' < 0");

        String genomeString = genome.toString();
        Set<Integer> result = new LinkedHashSet<>();
        int currentPos;
        int foundIndex = findNextPattern(genomeString, pattern, 0, mismatches);
        while (foundIndex != -1) {
            result.add(foundIndex);
            currentPos = foundIndex + 1;
            foundIndex = findNextPattern(genomeString, pattern, currentPos, mismatches);
        }
        return result;
    }

    private int findNextPattern(String genomeString, String pattern, int currentPos, int mismatches) {
        while (currentPos < genomeString.length() - pattern.length() + 1) {
            String candidate = genomeString.substring(currentPos, currentPos + pattern.length());
            if (isMatch(candidate, pattern, mismatches)) {
                return currentPos;
            }
            currentPos++;
        }
        return -1;
    }

    private boolean isMatch(String candidate, String pattern, int mismatches) {
        char[] candidateArray = candidate.toCharArray();
        char[] patternArray = pattern.toCharArray();
        int totalMismatches = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (candidateArray[i] != patternArray[i]) totalMismatches++;
        }
        return totalMismatches <= mismatches;
    }
}

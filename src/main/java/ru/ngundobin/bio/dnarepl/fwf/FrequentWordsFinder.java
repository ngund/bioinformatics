package ru.ngundobin.bio.dnarepl.fwf;

import ru.ngundobin.bio.dnarepl.pmp.PatternFinder;
import ru.ngundobin.bio.dnarepl.rcp.ComplementStrandReverser;
import ru.ngundobin.bio.model.Genome;

import java.util.*;

public class FrequentWordsFinder {

    private final Genome genome;

    public FrequentWordsFinder(Genome genome) {
        if (genome == null) throw new IllegalArgumentException("'genome' == null");

        this.genome = genome;
    }

    public Set<String> findFrequentWords(int wordLength, int minOccurrences) {
        if (wordLength <= 0) throw new IllegalArgumentException("'wordLength' <= 0");
        if (minOccurrences <= 1) throw new IllegalArgumentException("'wordLength' <= 1");

        Map<String, Integer> counterMap = count(wordLength);

        return getMostFrequentWords(counterMap, minOccurrences);
    }

    public Set<String> findFrequentWords(int wordLength) {
        if (wordLength <= 0) throw new IllegalArgumentException("'wordLength' <= 0");

        Map<String, Integer> counterMap = count(wordLength);

        return getMostFrequentWords(counterMap);
    }

    private Map<String, Integer> count(int wordLength) {
        Map<String, Integer> result = new HashMap<>();
        int currentPosition;
        for (currentPosition = 0; currentPosition < genome.size() - wordLength + 1; currentPosition++) {
            String word = genome.subSequence(currentPosition, currentPosition + wordLength - 1).toString();
            if (!result.containsKey(word)) {
                result.put(word, 1);
            }
            else {
                int newValue = result.get(word) + 1;
                result.put(word, newValue);
            }
        }
        return result;
    }

    private Set<String> getMostFrequentWords(Map<String, Integer> counterMap, int minOccurrences) {
        Set<String> result = new HashSet<>();
        for (String word : counterMap.keySet()) {
            if (counterMap.get(word) >= minOccurrences) result.add(word);
        }
        return result;
    }

    private Set<String> getMostFrequentWords(Map<String, Integer> counterMap) {
        Set<String> result = new HashSet<>();
        int maximumOccurrences = findMax(counterMap);
        for (String word : counterMap.keySet()) {
            if (counterMap.get(word).equals(maximumOccurrences)) result.add(word);
        }
        return result;
    }

    private int findMax(Map<String, Integer> counterMap) {
        return Collections.max(counterMap.values());
    }

    public Set<String> findFrequentWordsWithMismatches(int wordLength, int mismatches) {
        PatternFinder patternFinder = new PatternFinder(genome);
        long patternsCount = Math.round(Math.pow(4, wordLength));
        Map<String, Integer> countMap = new HashMap<>();
        for (long i = 0; i < patternsCount; i++) {
            String pattern = getPattern(i, wordLength);
            int occurrences = patternFinder.findPatternPositions(pattern, mismatches).size();
            countMap.put(pattern, occurrences);
        }
        return getMostFrequentWords(countMap);
    }

    private String getPattern(long i, int wordLength) {
        String radix4 = getCorrectedRadixString(i, 4, wordLength);
        StringBuilder resultBuilder = new StringBuilder();

        for (char c : radix4.toCharArray()) {
            switch (c) {
                case '0':
                    resultBuilder.append("A");
                    break;
                case '1':
                    resultBuilder.append("T");
                    break;
                case '2':
                    resultBuilder.append("C");
                    break;
                case '3':
                    resultBuilder.append("G");
                    break;
                default:
                    break;
            }
        }

        return resultBuilder.toString();
    }

    private String getCorrectedRadixString(long value, int radix, int wordLength) {
        String string = Long.toString(value, radix);
        if (string.length() == wordLength) {
            return string;
        }

        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < wordLength - string.length(); i++) {
            resultBuilder.append("0");
        }
        resultBuilder.append(string);
        return resultBuilder.toString();
    }

    public Set<String> findFrequentWordsWithMismatchesComplement(int wordLength, int mismatches) {
        PatternFinder patternFinder = new PatternFinder(genome);
        ComplementStrandReverser reverser = new ComplementStrandReverser();

        long patternsCount = Math.round(Math.pow(4, wordLength));
        Map<String, Integer> countMap = new HashMap<>();
        for (long i = 0; i < patternsCount; i++) {
            String pattern = getPattern(i, wordLength);
            if (countMap.containsKey(pattern)) continue;
            String complementPattern = reverser.reverse(new Genome(pattern)).toString();
            int occurrences = patternFinder.findPatternPositions(pattern, mismatches).size();
            int occurrencesPattern = patternFinder.findPatternPositions(complementPattern, mismatches).size();
            countMap.put(pattern, occurrences + occurrencesPattern);
            countMap.put(complementPattern, occurrences + occurrencesPattern);
        }
        return getMostFrequentWords(countMap);
    }
}

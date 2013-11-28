package ru.ngundobin.bio.antibioseq;

import ru.ngundobin.bio.dnarepl.util.FactorialCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static java.lang.System.out;

public class PeptidesWithGivenMassCounter {

    private final HashMap<String, Integer> massTable;
    private final List<Integer> masses;

    public PeptidesWithGivenMassCounter(HashMap<String, Integer> massTable) {
        this.massTable = massTable;
        this.masses = getMasses();
    }

    private List<Integer> getMasses() {
        List<Integer> result = new ArrayList<>(massTable.values());
        Collections.sort(result);
        return result;
    }

    public int countForMass(int mass) {
        int minTokens = (int) Math.ceil(((double) mass / (double) masses.get(masses.size() - 1)));
        int maxTokens = (int) Math.floor(((double) mass / (double) masses.get(0)));
        int result = 0;
        for (int t = minTokens; t <= maxTokens; t++) {
            result += countForTokens(mass, t);
        }
        return result;
    }

    private int getMassByTokens(List<Integer> tokenArray) {
        int cumMass = 0;
        for (int i = 0; i < masses.size(); i++) {
            cumMass += masses.get(i) * tokenArray.get(i);
        }
        return cumMass;
    }

    private List<Integer> generateTokenArray(int arraySize, int tokens, int cursor) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < arraySize; i++) {
            if (i == cursor) result.add(tokens);
            else result.add(0);
        }
        return result;
    }

    private int countForTokens(int mass, int tokens) {
        int result = 0;
        for (int t = masses.size() - 1; t >= 0; t--) {
            List<Integer> tokenArray = generateTokenArray(masses.size(), tokens, t);
            result += count(mass, tokenArray, t, t);
        }
        return result;
    }

    private int count(int mass, List<Integer> tokenArray, int cursorIndex, int leadElementIndex) {
        int count = 0;
        boolean skipMassCount = false;
        while (tokenArray.get(cursorIndex) != 0) {
            if (!skipMassCount) {
                out.print(tokenArray);
                if (getMassByTokens(tokenArray) == mass) {
                    int delta = countCommutations(tokenArray);
                    count += delta;
                    out.println(" * " + delta);
                }
                else {
                    out.println(" <>");
                }
            }
            else {
                skipMassCount = false;
            }
            if (cursorIndex == 0) return count;
            int cursorTokens = tokenArray.get(cursorIndex);
            int beforeCursorTokens = tokenArray.get(cursorIndex - 1);
            tokenArray.set(cursorIndex, cursorTokens - 1);
            tokenArray.set(cursorIndex - 1, beforeCursorTokens + 1);
            count += count(mass, tokenArray, cursorIndex - 1, leadElementIndex);
            if (cursorIndex == leadElementIndex && tokenArray.get(leadElementIndex) <= 1) return count;
            if (cursorIndex == leadElementIndex) {
                skipMassCount = true;
                int allTokens = 0;
                for (int i = 0; i < tokenArray.size(); i++) {
                    allTokens += tokenArray.get(i);
                    if (i < cursorIndex) tokenArray.set(i, 0);
                }
                tokenArray.set(cursorIndex - 1, allTokens - tokenArray.get(cursorIndex));
            }
        }
        return count;
    }

    private int countCommutations(List<Integer> tokenArray) {
        int allTokens = 0;
        int denominator = 1;
        for (Integer i : tokenArray) {
            if (i == 0) continue;
            allTokens += i;
            denominator *= FactorialCalculator.calculate(i);
        }
        return FactorialCalculator.calculate(allTokens) / denominator;
    }
}

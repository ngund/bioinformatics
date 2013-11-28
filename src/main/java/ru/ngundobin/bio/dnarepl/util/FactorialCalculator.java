package ru.ngundobin.bio.dnarepl.util;

import java.util.Map;
import java.util.TreeMap;

public class FactorialCalculator {

    private static Map<Integer, Integer> factorialTable = new TreeMap<>();

    public static int calculate(int n) {
        if (factorialTable.containsKey(n)) return factorialTable.get(n);
        if (n == 0) return add(0, 1);

        return n * calculate(n - 1);
    }

    private static int add(int n, int value) {
        factorialTable.put(n, value);
        return value;
    }
}

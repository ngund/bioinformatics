package ru.ngundobin.bio.antibioseq;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.out;

public class PerfTester {

    public static void main(String[] args) {
        List<Integer> list = generateList(100);
        Random rnd = new Random();
        out.println("Starting loop");
        long N = 10000000000L;
        int percent = 0;

        long start = System.currentTimeMillis();
        for (long i = 0L; i < N; i++) {
            int index1 = rnd.nextInt(100);
            int index2 = rnd.nextInt(100);
            int index3 = rnd.nextInt(100);
            int s = list.get(index1) + list.get(index2) + list.get(index3);
            int newPercent = (int) ((i * 100) / N);
            if (newPercent != percent) {
                percent = newPercent;
                out.println(percent + "% complete");
            }
        }
        long end = System.currentTimeMillis();
        out.println("Finished in:");
        out.println((end - start) + " ms");
        out.println((end - start) / 1000 + " s");
        out.println((end - start) / 60000 + " m");
    }

    private static List<Integer> generateList(int n) {
        Random rnd = new Random();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(rnd.nextInt(1000));
        }
        return result;
    }
}

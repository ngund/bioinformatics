package ru.ngundobin.bio.dnarepl.cfp;

import ru.ngundobin.bio.dnarepl.fwf.FrequentWordsFinder;
import ru.ngundobin.bio.model.Genome;

import java.util.LinkedHashSet;
import java.util.Set;

public class ClumpFinder {

    private final Genome genome;

    public ClumpFinder(Genome genome) {
        if (genome == null) throw new IllegalArgumentException("'genome' == null");
        this.genome = genome;
    }

    public Set<String> findClumps(int k, int L, int t) {
        if (k < 1) throw new IllegalArgumentException("'k' < 1");
        if (L < 1) throw new IllegalArgumentException("'L' < 1");
        if (L < k) throw new IllegalArgumentException("'L' < 'k'");
        if (t < 0) throw new IllegalArgumentException("'t' < 0");

        Set<String> result = new LinkedHashSet<>();
        int currentWindowStart;
        for (currentWindowStart = 0; currentWindowStart < genome.size() - L; currentWindowStart++) {
            Genome window = genome.subSequence(currentWindowStart, currentWindowStart + L);
            result.addAll(findClumps(window, k, t));
        }
        return result;
    }

    private Set<String> findClumps(Genome window, int k, int t) {
        FrequentWordsFinder finder = new FrequentWordsFinder(window);
        return finder.findFrequentWords(k, t);
    }
}

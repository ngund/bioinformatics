package ru.ngundobin.bio.dnarepl.msp;

import ru.ngundobin.bio.model.Genome;
import ru.ngundobin.bio.model.Nucleotide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MinimumSkewFinder {

    private final Genome genome;

    public MinimumSkewFinder(Genome genome) {
        if (genome == null) throw new IllegalArgumentException("'genome' == null");
        this.genome = genome;
    }

    public List<Integer> findMinimumSkew() {
        List<Integer> result = new ArrayList<>();
        ArrayList<Integer> skew = new ArrayList<>();
        int balance = 0;
        Iterator<Nucleotide> iterator = genome.iterator();

        while (iterator.hasNext()) {
            skew.add(balance);
            Nucleotide n = iterator.next();
            switch (n) {
                case C:
                    balance--;
                    break;
                case G:
                    balance++;
                    break;
                default:
                    break;
            }
        }
        skew.add(balance);

        int minimumSkewValue = Collections.min(skew);

        int minimumSkewIndex = skew.indexOf(minimumSkewValue);
        while (minimumSkewIndex != -1) {
            result.add(minimumSkewIndex);
            int currentPos = minimumSkewIndex + 1;
            minimumSkewIndex = -1;
            for (; currentPos < skew.size(); currentPos++) {
                if (skew.get(currentPos) == minimumSkewValue) {
                    minimumSkewIndex = currentPos;
                    break;
                }
            }
        }
        return result;
    }
}

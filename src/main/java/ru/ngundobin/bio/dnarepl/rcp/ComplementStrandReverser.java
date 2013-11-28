package ru.ngundobin.bio.dnarepl.rcp;

import ru.ngundobin.bio.model.Genome;
import ru.ngundobin.bio.model.Nucleotide;

import java.util.ListIterator;

public class ComplementStrandReverser {

    public Genome reverse(Genome source) {
        if (source == null) throw new IllegalArgumentException("'source' == null");
        Genome result = new Genome();
        ListIterator<Nucleotide> reverseIterator = source.listIterator(source.size());
        while (reverseIterator.hasPrevious()) {
            result.add(reverseIterator.previous().complement());
        }
        return result;
    }
}

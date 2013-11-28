package ru.ngundobin.bio.model;

import ru.ngundobin.bio.dnarepl.util.LargeArray;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class Genome implements Iterable<Nucleotide> {

    private final LargeArray<Nucleotide> sequence;

    public Genome() {
        sequence = new LargeArray<>();
    }

    private Genome(LargeArray<Nucleotide> sequence) {
        if (sequence == null) throw new IllegalArgumentException("'sequence' == null");
        this.sequence = sequence;
    }

    public Genome(Scanner scanner) {
        if (scanner == null) throw new IllegalArgumentException("'scanner' == null");
        sequence = readSequence(scanner);
    }

    private LargeArray<Nucleotide> readSequence(Scanner scanner) {
        LargeArray<Nucleotide> sequence = new LargeArray<>();
        while (scanner.hasNext()) {
            String line = scanner.next();
            for (char c : line.toCharArray()) {
                Nucleotide n = Nucleotide.valueOf(c);
                sequence.add(n);
            }
        }
        return sequence;
    }

    public Genome(String source) {
        if (source == null) throw new IllegalArgumentException("'source' == null");
        sequence = new LargeArray<>();
        for (char c : source.toCharArray()) {
            sequence.add(Nucleotide.valueOf(c));
        }
    }

    public void add(Nucleotide element) {
        if (element == null) throw new IllegalArgumentException("'element' == null");
        sequence.add(element);
    }

    public long size() {
        return sequence.longSize();
    }

    public Genome subSequence(int startIndex, int endIndex) {
        LargeArray<Nucleotide> subSequence = sequence.subSequence(startIndex, endIndex);
        return new Genome(subSequence);
    }

    public ListIterator<Nucleotide> listIterator() {
        return sequence.listIterator();
    }

    public ListIterator<Nucleotide> listIterator(long index) {
        return sequence.listIterator(index);
    }

    public Genome convertToRna() {
        Genome result = new Genome();
        for (Nucleotide n : this) {
            if (n == Nucleotide.T) result.add(Nucleotide.U);
            else result.add(n);
        }
        return result;
    }

    public Genome convertToDna() {
        Genome result = new Genome();
        for (Nucleotide n : this) {
            if (n == Nucleotide.U) result.add(Nucleotide.T);
            else result.add(n);
        }
        return result;
    }

    public Genome complement() {
        Genome result = new Genome();
        for (Nucleotide n : this) {
            result.add(n.complement());
        }
        return result;
    }

    @Override
    public Iterator<Nucleotide> iterator() {
        return sequence.iterator();
    }

    @Override
    public String toString() {
        if (size() > Integer.MAX_VALUE) return "error: genome is too long";
        StringBuilder builder = new StringBuilder();
        for (Nucleotide n : sequence) {
            builder.append(n.toString());
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genome genome = (Genome) o;

        return sequence.equals(genome.sequence);
    }

    @Override
    public int hashCode() {
        return sequence.hashCode();
    }
}

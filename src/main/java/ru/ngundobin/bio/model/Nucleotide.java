package ru.ngundobin.bio.model;

import java.util.NoSuchElementException;

public enum Nucleotide {
    A('A'),
    C('C'),
    G('G'),
    T('T'),
    U('U');

    static {
        A.setComplement(T);
        C.setComplement(G);
        G.setComplement(C);
        T.setComplement(A);
        U.setComplement(A);
    }

    public static Nucleotide valueOf(char ch) {
        for (Nucleotide n : Nucleotide.values()) {
            if (n.associatedChar == ch) return n;
        }
        throw new NoSuchElementException();
    }

    private final char associatedChar;
    private Nucleotide complement;

    private Nucleotide(char associatedChar) {
        this.associatedChar = associatedChar;
    }

    private void setComplement(Nucleotide n) {
        complement = n;
    }

    public Nucleotide complement() {
        return complement;
    }

    public char asChar() {
        return associatedChar;
    }

    @Override
    public String toString() {
        return String.valueOf(associatedChar);
    }
}

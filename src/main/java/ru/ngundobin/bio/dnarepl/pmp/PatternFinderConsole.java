package ru.ngundobin.bio.dnarepl.pmp;

import ru.ngundobin.bio.model.Genome;

import java.util.Set;

import static java.lang.System.out;

public class PatternFinderConsole {

    public static void main(String[] args) {
        String text = "ATTTGGCCAACATGGCATTCCGA";
        Genome genome = new Genome(text);
        String pattern = "CTTGATCAT";

        PatternFinder finder = new PatternFinder(genome);

        Set<Integer> result = finder.findPatternPositions(pattern);
        for (int integer : result) {
            out.print(integer + " ");
        }
    }
}

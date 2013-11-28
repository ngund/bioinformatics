package ru.ngundobin.bio.dnarepl.fwf;

import ru.ngundobin.bio.model.Genome;

import javax.swing.*;
import java.io.File;
import java.util.Collection;
import java.util.Scanner;
import java.util.Set;

public class FrequentWordsFinderRealTest {

    private FrequentWordsFinderRealTest() {
        throw new AssertionError("Access denied");
    }

    public static void main(String[] args) {
        int mismatches = 3;
        int wordLength = 10;
        JFileChooser chooser = new JFileChooser();
        int choice = chooser.showOpenDialog(null);
        if (choice == JFileChooser.CANCEL_OPTION) System.exit(0);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
                Genome genome = new Genome(scanner);
                FrequentWordsFinder finder = new FrequentWordsFinder(genome);
                Set<String> result = finder.findFrequentWordsWithMismatchesComplement(wordLength, mismatches);
                print(result);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            } finally {
                if (scanner != null) scanner.close();
            }
        }
    }

    private static void print(Collection<String> result) {
        StringBuilder builder = new StringBuilder();
        for (String i : result) {
            builder.append(i).append(" ");
        }
        System.out.print(builder);
    }
}
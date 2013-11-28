package ru.ngundobin.bio.dnarepl.pmp;

import ru.ngundobin.bio.model.Genome;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;
import java.util.Set;

public class PatternFinderRealTest {

    private PatternFinderRealTest() {
        throw new AssertionError("Access denied");
    }

    public static void main(String[] args) {
        String pattern = "AGACGAA";
        int mismatches = 6;
        JFileChooser chooser = new JFileChooser();
        int choice = chooser.showOpenDialog(null);
        if (choice == JFileChooser.CANCEL_OPTION) System.exit(0);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
                Genome genome = new Genome(scanner);
                PatternFinder finder = new PatternFinder(genome);
                Set<Integer> result = finder.findPatternPositions(pattern, mismatches);
                print(result);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            } finally {
                if (scanner != null) scanner.close();
            }
        }
    }

    private static void print(Set<Integer> result) {
        StringBuilder builder = new StringBuilder();
        for (Integer i : result) {
            builder.append(i).append(" ");
        }
        System.out.print(builder);
    }
}
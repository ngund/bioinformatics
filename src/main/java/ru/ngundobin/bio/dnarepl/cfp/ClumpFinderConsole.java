package ru.ngundobin.bio.dnarepl.cfp;

import ru.ngundobin.bio.model.Genome;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;
import java.util.Set;

public class ClumpFinderConsole {

    public static void main(String[] args) {
        int k = 9;
        int L = 500;
        int t = 3;
        JFileChooser chooser = new JFileChooser();
        int choice = chooser.showOpenDialog(null);
        if (choice == JFileChooser.CANCEL_OPTION) System.exit(0);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
                System.out.println("Scanner created");
                Genome genome = new Genome(scanner);
                System.out.println("Genome created");
                ClumpFinder finder = new ClumpFinder(genome);
                Set<String> result = finder.findClumps(k, L, t);
                System.out.println("Result found: " + result.size());
                //print(result);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            } finally {
                if (scanner != null) scanner.close();
            }
        }
    }

    private static void print(Set<String> result) {
        StringBuilder builder = new StringBuilder();
        for (String i : result) {
            builder.append(i).append(" ");
        }
        System.out.print(builder);
    }
}

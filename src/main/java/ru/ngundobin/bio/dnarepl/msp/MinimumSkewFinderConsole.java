package ru.ngundobin.bio.dnarepl.msp;

import ru.ngundobin.bio.model.Genome;

import javax.swing.*;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class MinimumSkewFinderConsole {

    private MinimumSkewFinderConsole() {
        throw new AssertionError("Access denied");
    }

    public static void main(String[] args) {
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
                MinimumSkewFinder finder = new MinimumSkewFinder(genome);
                List<Integer> result = finder.findMinimumSkew();
                print(result);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            } finally {
                if (scanner != null) scanner.close();
            }
        }
    }


    private static void print(Collection<Integer> result) {
        StringBuilder builder = new StringBuilder();
        for (Integer i : result) {
            builder.append(i).append(" ");
        }
        System.out.print(builder);
    }
}
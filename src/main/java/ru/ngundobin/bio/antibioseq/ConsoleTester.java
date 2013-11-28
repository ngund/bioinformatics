package ru.ngundobin.bio.antibioseq;

import ru.ngundobin.bio.model.AminoAcidMassMapBuilder;
import ru.ngundobin.bio.model.Genome;
import ru.ngundobin.bio.model.RNACodonToAcidMapBuilder;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

public class ConsoleTester {

    private ConsoleTester() {
        throw new AssertionError("Access denied");
    }

    private static final String DICT_FILE = "src/main/resources/RNACodonTable.txt";

    public static void main(String[] args)
            throws FileNotFoundException {
        printCollection(theoreticalSpectrumProblem("GPQSKEYRVDEEQ"), " ");
    }

    private static String proteinTranslationProblem() {
        HashMap<String, String> dictionary = loadAcidDictionary();
        Genome rnaGenome = loadGenome();
        ProteinTranslationProblem problem = new ProteinTranslationProblem(dictionary);

        return problem.translate(rnaGenome);
    }

    private static HashMap<String, String> loadAcidDictionary() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(DICT_FILE));
            RNACodonToAcidMapBuilder builder = new RNACodonToAcidMapBuilder(scanner);
            return builder.map();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (scanner != null) scanner.close();
        }

        throw new AssertionError("wtf");
    }

    private static Genome loadGenome() {
        Genome genome = null;
        JFileChooser chooser = new JFileChooser();
        int choice = chooser.showOpenDialog(null);
        if (choice == JFileChooser.CANCEL_OPTION) System.exit(0);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
                out.println("Scanner created");
                genome = new Genome(scanner);
                out.println("Genome created");
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            } finally {
                if (scanner != null) scanner.close();
            }
        }
        return genome;
    }

    private static List<Genome> peptideEncodingProblem(String peptide) {
        HashMap<String, String> dictionary = loadAcidDictionary();
        Genome genome = loadGenome();
        PeptideEncodingProblem problem = new PeptideEncodingProblem(genome, dictionary);

        return problem.encode(peptide);
    }

    private static List<Integer> theoreticalSpectrumProblem(String peptide)
            throws FileNotFoundException {
        Map<String, Integer> massTable = new AminoAcidMassMapBuilder().map();
        TheoreticalSpectrumGenerator generator = new TheoreticalSpectrumGenerator(massTable);
        return generator.generate(peptide);
    }

    private static void printCollection(Iterable<?> collection, String delimiter) {
        for (Object element : collection) {
            out.print(element.toString());
            out.print(delimiter);
        }
    }
}
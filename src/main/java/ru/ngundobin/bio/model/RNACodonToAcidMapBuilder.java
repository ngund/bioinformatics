package ru.ngundobin.bio.model;

import java.util.HashMap;
import java.util.Scanner;

public class RNACodonToAcidMapBuilder {

    private HashMap<String, String> map;

    public RNACodonToAcidMapBuilder(Scanner scanner) {
        map = buildMap(scanner);
    }

    private HashMap<String, String> buildMap(Scanner scanner) {
        HashMap<String, String> map = new HashMap<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] lineParts = line.split(" ");
            String codon = lineParts[0];
            String aminoAcid = "";
            if (lineParts.length == 2) aminoAcid = lineParts[1];
            map.put(codon, aminoAcid);
        }
        return map;
    }

    public HashMap<String, String> map() {
        return map;
    }
}

package ru.ngundobin.bio.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AminoAcidMassMapBuilder {
    private static final File FILE = new File("src/main/resources/AminoAcidMassTable.txt");
    private final Map<String, Integer> massMap;

    public AminoAcidMassMapBuilder()
            throws FileNotFoundException {
        Scanner scanner = new Scanner(FILE);
        massMap = buildMap(scanner);
        scanner.close();
    }

    private Map<String, Integer> buildMap(Scanner scanner) {
        Map<String, Integer> map = new HashMap<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] lineParts = line.split(" ");
            String acid = lineParts[0];
            int mass = Integer.parseInt(lineParts[1]);
            map.put(acid, mass);
        }
        return map;
    }

    public Map<String, Integer> map() {
        return massMap;
    }
}

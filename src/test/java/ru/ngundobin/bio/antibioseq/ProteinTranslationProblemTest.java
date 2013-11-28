package ru.ngundobin.bio.antibioseq;

import org.testng.annotations.Test;
import ru.ngundobin.bio.model.Genome;
import ru.ngundobin.bio.model.RNACodonToAcidMapBuilder;

import java.io.File;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;

public class ProteinTranslationProblemTest {

    @Test
    public void testTranslate_smallTest()
            throws Exception {
        File dictFile = new File("src/main/resources/RNACodonTable.txt");
        System.out.println(dictFile.getAbsolutePath());

        RNACodonToAcidMapBuilder dictionary = new RNACodonToAcidMapBuilder(new Scanner(dictFile));
        Genome rnaGenome = new Genome("AUGGCCAUGGCGCCCAGAACUGAGAUCAAUAGUACCCGUAUUAACGGGUGA");
        ProteinTranslationProblem translationProblem = new ProteinTranslationProblem(dictionary.map());
        String expected = "MAMAPRTEINSTRING";

        String result = translationProblem.translate(rnaGenome);

        assertEquals(result, expected);
    }
}

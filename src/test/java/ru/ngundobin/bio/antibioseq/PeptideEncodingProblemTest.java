package ru.ngundobin.bio.antibioseq;

import org.testng.annotations.Test;
import ru.ngundobin.bio.model.Genome;
import ru.ngundobin.bio.model.RNACodonToAcidMapBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;

public class PeptideEncodingProblemTest {

    @Test
    public void testEncode_smallTest()
            throws Exception {
        File dictFile = new File("src/main/resources/RNACodonTable.txt");
        RNACodonToAcidMapBuilder dictionary = new RNACodonToAcidMapBuilder(new Scanner(dictFile));
        Genome dnaGenome = new Genome("ATGGCCATGGCCCCCAGAACTGAGATCAATAGTACCCGTATTAACGGGTGA");
        String peptide = "MA";
        PeptideEncodingProblem problem = new PeptideEncodingProblem(dnaGenome, dictionary.map());
        List<Genome> expected = new ArrayList<>();
        expected.add(new Genome("ATGGCC"));
        expected.add(new Genome("GGCCAT"));
        expected.add(new Genome("ATGGCC"));

        List<Genome> result = problem.encode(peptide);

        assertEquals(result, expected);
    }
}

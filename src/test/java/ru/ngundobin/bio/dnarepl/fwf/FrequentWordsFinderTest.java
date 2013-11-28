package ru.ngundobin.bio.dnarepl.fwf;

import org.testng.annotations.Test;
import ru.ngundobin.bio.model.Genome;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FrequentWordsFinderTest {

    @Test
    public void findFrequentWords_smallTest()
            throws Exception {
        String sourceText = "ACGTTGCATGTCGCATGATGCATGAGAGCT";
        Genome genome = new Genome(sourceText);
        int wordLength = 4;
        String expected1 = "CATG";
        String expected2 = "GCAT";
        FrequentWordsFinder finder = new FrequentWordsFinder(genome);

        Set<String> result = finder.findFrequentWords(wordLength);

        assertEquals(result.size(), 2);
        assertTrue(result.contains(expected1));
        assertTrue(result.contains(expected2));
    }

    @Test
    public void findFrequentWords_bigTest()
            throws Exception {
        String sourceText =
                "CGGAAGCGAGATTCGCGTGGCGTGATTCCGGCGGGCGTGGAGAAGCGAGATTCATTCAAGCCGGGAGGCGTGGCGTGGCGTGGCGTGCGG" +
                "ATTCAAGCCGGCGGGCGTGATTCGAGCGGCGGATTCGAGATTCCGGGCGTGCGGGCGTGAAGCGCGTGGAGGAGGCGTGGCGTGCGGGAG" +
                "GAGAAGCGAGAAGCCGGATTCAAGCAAGCATTCCGGCGGGAGATTCGCGTGGAGGCGTGGAGGCGTGGAGGCGTGCGGCGGGAGATTCAA" +
                "GCCGGATTCGCGTGGAGAAGCGAGAAGCGCGTGCGGAAGCGAGGAGGAGAAGCATTCGCGTGATTCCGGGAGATTCAAGCATTCGCGTGC" +
                "GGCGGGAGATTCAAGCGAGGAGGCGTGAAGCAAGCAAGCAAGCGCGTGGCGTGCGGCGGGAGAAGCAAGCGCGTGATTCGAGCGGGCGTG" +
                "CGGAAGCGAGCGG";
        Genome genome = new Genome(sourceText);
        int wordLength = 12;
        Set<String> expected = new HashSet<>();
        expected.add("CGGCGGGAGATT");
        expected.add("CGGGAGATTCAA");
        expected.add("CGTGCGGCGGGA");
        expected.add("CGTGGAGGCGTG");
        expected.add("CGTGGCGTGCGG");
        expected.add("GCGTGCGGCGGG");
        expected.add("GCGTGGAGGCGT");
        expected.add("GCGTGGCGTGCG");
        expected.add("GGAGAAGCGAGA");
        expected.add("GGAGATTCAAGC");
        expected.add("GGCGGGAGATTC");
        expected.add("GGGAGATTCAAG");
        expected.add("GTGCGGCGGGAG");
        expected.add("TGCGGCGGGAGA");

        FrequentWordsFinder finder = new FrequentWordsFinder(genome);

        Set<String> result = finder.findFrequentWords(wordLength);

        assertEquals(result.size(), expected.size());
        for (String expectedWord : expected) {
            assertTrue(result.contains(expectedWord));
        }
    }

    @Test
    public void findFrequentWordsWithMismatches_smallTest()
            throws Exception {
        String sourceText = "ACGTTGCATGTCGCATGATGCATGAGAGCT";
        Genome genome = new Genome(sourceText);
        int wordLength = 4;
        int mismatches = 1;
        FrequentWordsFinder finder = new FrequentWordsFinder(genome);

        Set<String> result = finder.findFrequentWordsWithMismatches(wordLength, mismatches);

        assertEquals(result.size(), 3);
        assertTrue(result.contains("GATG"));
        assertTrue(result.contains("ATGC"));
        assertTrue(result.contains("ATGT"));
    }

    @Test
    public void findFrequentWordsWithMismatchesComplement_smallTest()
            throws Exception {
        String sourceText = "ACGTTGCATGTCGCATGATGCATGAGAGCT";
        Genome genome = new Genome(sourceText);
        int wordLength = 4;
        int mismatches = 1;
        FrequentWordsFinder finder = new FrequentWordsFinder(genome);

        Set<String> result = finder.findFrequentWordsWithMismatchesComplement(wordLength, mismatches);

        assertEquals(result.size(), 2);
        assertTrue(result.contains("ACAT"));
        assertTrue(result.contains("ATGT"));
    }
}
